package drawloop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class InputRegister implements KeyListener, MouseInterface {

    //region Eager Singelton Patern
    private static InputRegister INSTANCE = new InputRegister();

    public static InputRegister getInstance(){
        return INSTANCE;
    }

    private InputRegister(){}
    //endregion

    //keep track of the status of each individual group
    private HashMap<String, Boolean> groupStatus = new HashMap<>();

    // for each key trigger, keep track of the group
    private HashMap<Consumer<KeyEvent>, String> triggerMapping = new HashMap<>();
    private HashMap<Integer, List<Consumer<KeyEvent>>> keyMapping = new HashMap<>();//Integer corresponds to a key of the keyboard

    // for each mouse trigger, keep track of the group
    private HashMap<BiConsumer<int[], MouseEvent>, String> groupMapping = new HashMap<>();
    private HashMap<Integer , List< BiConsumer<int[], MouseEvent> > > buttonMapping = new HashMap<>();


    // region Basic group controls
    public void TurnOnGroup(String group){
        groupStatus.replace(group,true);
    }

    public void TurnOffGroup(String group){
        groupStatus.replace(group,false);
    }

    //endregion

    // region KeyListener Implementation

    @Override
    public final void keyTyped(KeyEvent e) {

    }

    @Override
    public final void keyPressed(KeyEvent e) {

    }

    @Override
    public final void keyReleased(KeyEvent e) {
        if(keyMapping.containsKey(e.getKeyCode())){
            keyMapping.get(e.getKeyCode()).forEach((trigger)->{
                //only trigger when the group is active
                if(groupStatus.get(triggerMapping.get(trigger))){
                    //run the trigger in a separate thread
                    new Thread(()-> trigger.accept(e)).start();
                }
            });
        }
    }
    //endregion

    //region Mouse Listeners Implementation
    @Override
    public Optional<BiConsumer<int[], MouseEvent>> getMouseClickedListener() {
        return Optional.of((cor,event)->{
                    if(buttonMapping.containsKey(event.getButton())){
                        buttonMapping.get(event.getButton()).forEach((consumer)->{
                            //if group is active
                            if(groupStatus.get(groupMapping.get(consumer))){
                                new Thread(
                                        ()-> consumer.accept(cor, event)
                                ).start();
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Optional<BiConsumer<int[], MouseEvent>> getMouseMovedListener() {
        return Optional.of((cor,event)->{
            if(buttonMapping.containsKey(4)){
                buttonMapping.get(4).forEach((consumer)->{// take 4 as mouse moved
                    //if group is active
                    if(groupStatus.get(groupMapping.get(consumer))){
                        new Thread(
                                ()-> consumer.accept(cor, event)
                        ).start();
                    }
                });
            }
        });
    }

    //endregion

    // region Registering Triggers
    // Register a trigger bolonging to a group, for a key with keycode
    public void registerKey(String group, int keycode, Consumer<KeyEvent> trigger){
        //add a group if necessary
        if(!groupStatus.containsKey(group)){
            groupStatus.put(group,true);
        }
        //keep track of the group of the trigger
        triggerMapping.put(trigger,group);
        // bind the trigger to the key
        if(!keyMapping.containsKey(keycode)){
            keyMapping.put(keycode, new ArrayList<>(Collections.singleton(trigger)));
        }else {
            keyMapping.get(keycode).add(trigger);
        }
    }

    public void registerMouse(String group, int buttonCode, BiConsumer<int[], MouseEvent> trigger){
        //add a group if necessary
        if(!groupStatus.containsKey(group)){
            groupStatus.put(group,true);
        }
        //keep track of the group of the trigger
        groupMapping.put(trigger,group);

        // bind the trigger to the button
        if(!buttonMapping.containsKey(buttonCode)){
            buttonMapping.put(buttonCode, new ArrayList<>(Collections.singleton(trigger)));
        }else {
            buttonMapping.get(buttonCode).add(trigger);
        }
    }
    //endregion
}
