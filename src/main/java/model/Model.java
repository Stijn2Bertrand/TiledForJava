package model;

import drawloop.ExtendedCanvas;
import model.sprites.Register;
import model.sprites.Sprite;

import java.awt.*;

public class Model extends ExtendedCanvas {

    private Map map;

    public Model() {}

    @Override
    public void draw(Graphics graphics){
        super.draw(graphics);
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


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        int[] dim = map.getMapDim();
        this.imaginaryWidth = dim[0];
        this.imaginaryHeight = dim[1];
    }
}
