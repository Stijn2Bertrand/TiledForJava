package drawloop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Optional;

public class KeyRegister {


    private Canvas canvas;
    private HashMap<Integer, Optional<Trigger>> map = new HashMap(){
        @Override
        public Object get(Object key) {
            if(!this.containsKey(key))return Optional.empty();
            return super.get(key);
        }
    };

    public KeyRegister(Canvas canvas) {
        this.canvas=canvas;

        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                map.get(e.getKeyCode()).ifPresent((value)->value.trigger(e));
            }
        });

    }

    public void addKeyListener(int keycode, Trigger trigger){
        map.put(keycode,Optional.of(trigger));
    }


    public interface Trigger{
        void trigger(KeyEvent event);
    }

}
