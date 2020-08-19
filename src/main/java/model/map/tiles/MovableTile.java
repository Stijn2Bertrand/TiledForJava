package model.map.tiles;

import model.map.Map;
import model.sprites.AnimatedSprite;
import model.sprites.Sprite;

import java.awt.*;

public class MovableTile extends Tile {

    public MovableTile(AnimatedSprite sprite) {
        super(sprite);
    }

    //we might want to make this method  package private
    public void smoothMove(int x, int y, int iterations){
        this.smoothMove(x,y,iterations,()->{});
    }

    public void smoothMove(int x, int y, int iterations, Continuation continuation){
        this.dx = (this.getX() - x)/ iterations;
        this.dy = (this.getY() - y)/ iterations;
        this.counter = iterations;
        //the sprite is always an animated sprite because the constructor takes an animated sprite
        ((AnimatedSprite)this.getSprite()).loadAnimation("moving");

        this.continuation = continuation;
    }

    private int counter = 0;
    private int dx = 0;
    private int dy = 0;
    private Continuation defaultContinuation = ()->{
            ((AnimatedSprite)this.getSprite()).stopAnimation();
            dx = 0;
            dy = 0;
    };
    private Continuation continuation = ()->{};

    @Override
    public void draw(Graphics graphics) {
        if(counter>0){
            this.setX(this.getX()-dx);
            this.setY(this.getY()-dy);
            counter--;
            if(counter==0){
                defaultContinuation.cont();
                continuation.cont();
            }
        }
        super.draw(graphics);
    }

    public interface Continuation {
        public abstract void cont();
    }
}

