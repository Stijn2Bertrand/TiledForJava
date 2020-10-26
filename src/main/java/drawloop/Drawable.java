package drawloop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

public interface Drawable {

    void draw(Graphics graphics);
    int getHeight();
    int getWidth();

}
