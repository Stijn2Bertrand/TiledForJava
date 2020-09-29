package drawloop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

public final class KeyRegister implements KeyListener {
    public interface Trigger{
        void trigger(KeyEvent event);
    }

    /*
    *   Singelton Patern
    * */
    private static KeyRegister INSTANCE = new KeyRegister();

    public static KeyRegister getInstance(){
        return INSTANCE;
    }

    private KeyRegister(){}

    private HashMap<String, Boolean> groupStatus = new HashMap<>();
    private HashMap<Trigger, String> triggerMapping = new HashMap<>();
    private HashMap<Integer, ArrayList<Trigger>> keyMapping = new HashMap();


    public void registerKey(String group, int keycode, Trigger trigger){
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

    public void TurnOnGroup(String group){
        groupStatus.replace(group,true);
    }

    public void TurnOffGroup(String group){
        groupStatus.replace(group,false);
    }

    /*
    * KeyListener Implementation
    * */
    @Override
    public final void keyTyped(KeyEvent e) {

    }

    @Override
    public final void keyPressed(KeyEvent e) {

    }

    @Override
    public final void keyReleased(KeyEvent e) {
        keyMapping.get(e.getKeyCode()).forEach((trigger)->{
            //only trigger when the group is active
            if(groupStatus.get(triggerMapping.get(trigger))){
                //run the trigger in a separate thread
                new Thread(()-> trigger.trigger(e)).start();
            }
        });
    }

}
