package drawloop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

public interface Drawable {

    void draw(Graphics graphics);
    int getHeight();
    int getWidth();

    default BiConsumer<int[],MouseEvent> getMouseClickedListener(){
        return (cor,event)-> {};
    }

    default BiConsumer<int[],MouseEvent> getMouseMovedListener(){
        return (cor,event)-> {};
    }

}
