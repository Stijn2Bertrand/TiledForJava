package model;

import drawloop.ExtendedCanvas;

import java.awt.*;

public class Model extends ExtendedCanvas {

    private Map map;

    @Override
    public void draw(Graphics graphics){
        if(map != null){
            map.forEachTile( tile ->{
                graphics.drawImage(
                        tile.getSprite().getImage(),
                        tile.getSprite().getX(),
                        tile.getSprite().getY(),
                        tile.getSprite().getHeight(),
                        tile.getSprite().getWidth(),
                        null
                        );
                //todo: maybe I need a part for animation here?
            });
        }
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
