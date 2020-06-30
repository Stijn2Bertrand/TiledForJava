package model;

import drawloop.ExtendedCanvas;
import model.sprites.Register;
import model.sprites.Sprite;

import java.awt.*;

public class Model extends ExtendedCanvas {

    private Register register = new Register();

    private Map map;

    public Model() {
        this.map = new Map(2,3,3);

        Sprite sprite = register.getSprite("first", 9);
        Tile tile = new Tile(sprite);
        this.map.addTile(0,2,2, tile);
    }

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
    }
}
