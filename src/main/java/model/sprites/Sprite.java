package model.sprites;

import java.awt.*;
import java.util.function.Supplier;

public class Sprite {

    private int width;
    private int height;
    private Supplier<Image> imageSupplier;

    public Sprite(int width,int height , Supplier<Image> imageSupplier) {
        this.width = width;
        this.height = height;
        this.imageSupplier = imageSupplier;
    }

    public Image getImage() {
        return imageSupplier.get();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
