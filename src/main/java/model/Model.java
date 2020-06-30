package model;

import drawloop.ExtendedCanvas;
import model.sprites.Register;
import model.sprites.Sprite;

import java.awt.*;

public class Model extends ExtendedCanvas {

    private Map map;

    @Override
    public void draw(Graphics graphics){
        super.draw(graphics);
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
        //test code:
        Register register = new Register();
        Sprite sprite = register.getSprite("first", 2);
        sprite.setX(500);
        sprite.setY(500);
        graphics.drawImage(sprite.getImage(),
                sprite.getX(),
                sprite.getY(),
                sprite.getWidth(),
                sprite.getWidth(),
                null);
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
