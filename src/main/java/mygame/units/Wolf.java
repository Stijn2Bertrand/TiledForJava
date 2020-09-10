package mygame.units;

import model.sprites.AnimatedSprite;
import mygame.Unit;

public class Wolf extends Unit {

    public Wolf() {
        super(new AnimatedSprite("first",3));
        this.getSprite().addAnimation("moving",3);
    }
}
