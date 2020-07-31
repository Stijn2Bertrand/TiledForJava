package model.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    //dimentions of the sheet in sprites
    private int rows;
    private int columns;
    private Image[] sprites;

    //dimensions of a single sprite in pixels
    private int spriteWidth;
    private int spriteHeight;


    public SpriteSheet(String path, int rows, int columns){
        this.path = path;
        this.rows = rows;
        this.columns = columns;
        this.sprites = new Image[rows* columns];
        load();
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public Sprite getSprite(int id) {
        Sprite sprite = new Sprite(
                getSpriteWidth() ,
                getSpriteHeight(),
                ()-> sprites[id]);
        return sprite;
    }

    //load the image
    private synchronized void load(){
        try {
            //I might want to make a separate thread for this
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            this.spriteWidth = w/ columns;
            int h = image.getHeight();
            this.spriteHeight = h/rows;

            for(int index = 0; index < sprites.length ;index++ ){
                sprites[index] = image.getSubimage(
                        (index%rows)*getSpriteWidth(),
                        (index/rows)*getSpriteHeight(),
                        getSpriteWidth() ,
                        getSpriteHeight());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //missing some code for in case the asked sprite does not exist
    public Sprite getSprite(int row, int column){
        Sprite sprite = new Sprite(
                getSpriteWidth(),
                getSpriteHeight(),
                ()-> sprites[row*column]);
        return sprite;
    }




}
