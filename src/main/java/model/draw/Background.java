package model.draw;

import drawloop.Drawable;
import model.sprites.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Background implements Drawable {


    private Image image;
    private int with;
    private int height;

    public Background(String path) {

        load(path);
    }

    //load the image
    private synchronized void load(String path){
        try {
            //I might want to make a separate thread for this
            this.image = ImageIO.read(SpriteSheet.class.getResource(path));
            this.with = image.getWidth(null);
            this.height = image.getHeight(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(
                image,
                0,
                0,
                image.getWidth(null),
                image.getHeight(null),
                null
        );

    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return with;
    }
}
