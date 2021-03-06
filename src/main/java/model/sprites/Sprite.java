package model.sprites;

import java.awt.*;
import java.util.function.Supplier;

public class Sprite {

    private int width;
    private int height;
    private SpriteSheet sheet;
    private Supplier<Image> deafaultImage;

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

    protected SpriteSheet getSpriteSheet(){
        return sheet;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }



}
