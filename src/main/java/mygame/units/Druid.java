package mygame.units;

import model.sprites.AnimatedSprite;
import mygame.Unit;

public class Druid extends Unit {
    public Druid() {
        super(new AnimatedSprite("first",13));
        this.getSprite().addAnimation("moving",13);
    }
}
