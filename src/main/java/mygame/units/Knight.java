package mygame.units;

import model.sprites.AnimatedSprite;
import mygame.Unit;

public class Knight extends Unit {

    public Knight() {
        super(new AnimatedSprite("first",2));
        this.getSprite().addAnimation("moving",2);
    }
}
