package model.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private String path;
    //dimentions of the sheet in sprites
    private int rows;
    private int colums;
    private BufferedImage image;
    //dimensions of a single sprite in pixels
    private int spriteWidth;
    private int spriteHeight;


    public SpriteSheet(String path,int rows,int colums){
        this.path = path;
        this.rows = rows;
        this.colums = colums;
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
                ()-> image.getSubimage(id%rows,id/rows,getSpriteWidth() ,getSpriteHeight()) );
        return sprite;
    }

    //load the image
    private synchronized void load(){
        try {
            //I might want to make a separate thread for this
            this.image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = this.image.getWidth();
            this.spriteWidth = w/colums;
            int h = image.getHeight();
            this.spriteHeight = h/rows;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //missing some code for in case the asked sprite does not exist
    public Sprite getSprite(int row,int colum){
        Sprite sprite = new Sprite(
                getSpriteWidth(),
                getSpriteHeight(),
                ()-> image.getSubimage(row,colum,getSpriteWidth() ,getSpriteHeight()) );

        return sprite;
    }




}
