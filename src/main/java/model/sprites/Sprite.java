package model.sprites;

import java.awt.*;
import java.util.function.Supplier;

public class Sprite {

    private int width;
    private int height;
    SpriteSheet sheet;
    Supplier<Image> deafaultImage;

    public Sprite(SpriteSheet sheet, int defaultSprite) {
        this.sheet = sheet;
        this.width = sheet.getSpriteWidth();
        this.height = sheet.getSpriteHeight();
        this.deafaultImage = sheet.getImageSupplier(defaultSprite);
    }

    public Sprite(String sheetName, int defaultSprite ) {
        this( Register.getInstance().getSpriteSheet(sheetName),defaultSprite);
    }

    public Image getImage() {
        return deafaultImage.get();
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
