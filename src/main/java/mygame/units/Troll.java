package mygame.units;

import model.sprites.AnimatedSprite;
import mygame.Unit;

public class Troll extends Unit {
    public Troll() {
        super(new AnimatedSprite("first",1));
        this.getSprite().addAnimation("moving",1);
    }

}
