package mygame;

import model.map.tiles.MovableTile;
import model.sprites.AnimatedSprite;

public class Unit extends MovableTile {

    private int life = 100;
    private int maxLife = 100;

    public Unit(AnimatedSprite sprite) {
        super(sprite);
        sprite.addAnimation("dead",2);
    }


    public synchronized void takeDamage(int amount){
        this.life = Math.min(Math.min(this.life-amount,maxLife),0);
        if(this.life == 0) die();
    }


    private void die() {
        this.getSprite().loadAnimation("dead");
    }


}
