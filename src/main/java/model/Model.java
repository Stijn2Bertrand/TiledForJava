package model;

import drawloop.Drawable;
import drawloop.ExtendedCanvas;
import model.sprites.Register;
import model.sprites.Sprite;

import java.awt.*;

public class Model implements Drawable {

    private Map map;

    public Model() {}

    @Override
    public void draw(Graphics graphics){
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,this.getWidth(),this.getHeight());
        if(map != null){
            map.forEachTile( tile ->{
                if (tile != null) {
                    graphics.drawImage(
                            tile.getSprite().getImage(),
                            tile.getSprite().getX(),
                            tile.getSprite().getY(),
                            tile.getSprite().getHeight(),
                            tile.getSprite().getWidth(),
                            null
                    );
                    //todo: maybe I need a part for animation here?
                }
            });
        }
    }

    @Override
    public int getHeight() {
        if(map!= null)return map.getMapDim()[1];
        return 0;
    }

    @Override
    public int getWidth() {
        if(map!= null)return map.getMapDim()[0];
        return 0;
    }

    int width;
    int height;
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        int[] dim = map.getMapDim();
        this.width = dim[0];
        this.height = dim[1];
    }
}
