package drawloop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public interface Drawable {

    void draw(Graphics graphics);
    int getHeight();
    int getWidth();

}
