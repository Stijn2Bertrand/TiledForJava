package mygame;

import model.map.Map;
import model.map.MovingMap;
import model.map.tiles.MovableTile;
import model.sprites.AnimatedSprite;

import java.io.FileReader;

public class Wizard extends MovableTile {

    public Wizard() {
        super(new AnimatedSprite("first",0));
        ((AnimatedSprite)this.getSprite()).addAnimation("moving", 0,0,0);

    }



    public void spawnFireball(MovingMap map, int targetI, int targetJ){
        Fireball fireball = new Fireball();

        //todo supply a better start position
        map.addTile(2,this.getI(),this.getJ()+1, fireball);

        map.smoothMove(fireball,targetI,targetJ);
    }
}

class Fireball extends MovableTile{
    public Fireball() {
        super(new AnimatedSprite("first",0));
        ((AnimatedSprite)this.getSprite()).addAnimation("moving", 5,6,7,6);
    }

}