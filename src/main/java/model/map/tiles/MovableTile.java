package model.map.tiles;

import model.map.Map;
import model.sprites.AnimatedSprite;
import model.sprites.Sprite;

import java.awt.*;
import java.util.List;

public class MovableTile extends Tile {

    // a continuation interface
    public interface Continuation {
        public abstract void cont();
    }

    //Instance variables:
    private int counter = 0;
    private int dx = 0;
    private int dy = 0;
    private Continuation defaultContinuation = ()->{
            this.getSprite().stopAnimation();
            dx = 0;
            dy = 0;
    };
    private Continuation continuation = ()->{};

    //Constructor
    public MovableTile(AnimatedSprite sprite) {
        super(sprite);
    }

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

    @Override
    public AnimatedSprite getSprite(){
        return (AnimatedSprite)super.getSprite();
    }



    // a function that makes the tile move smoothly to the destination
    public void smoothMove(int toI, int toJ){
        this.getMap().ifPresent((map)->{
            int[] newCor = map.getStrategy().toWorldCoordinates(toI,toJ);
            this.smoothMove(
                    newCor[0],
                    newCor[1],
                    30,
                    ()->map.teleport(this, toI, toJ));
        });


    }

    //a function that makes the tile follow a path
    //todo: collision detection
    public void followPath(List<int[]> path){
        if(path.isEmpty())return;
        this.getMap().ifPresent((map)-> {
            int[] newCor = map.getStrategy().toWorldCoordinates(path.get(0)[0], path.get(0)[1]);

            this.smoothMove(
                    newCor[0],
                    newCor[1],
                    30,
                    () -> {
                        map.teleport(this, path.get(0)[0], path.get(0)[1]);
                        if (path.size() > 1) this.followPath(path.subList(1, path.size()));
                    });
        });
    }

    //we might want to make this method  package private
    protected void smoothMove(int x, int y, int iterations){
        this.smoothMove(x,y,iterations,()->{});
    }

    public void smoothMove(int x, int y, int iterations, Continuation continuation){
        this.dx = (this.getX() - x)/ iterations;
        this.dy = (this.getY() - y)/ iterations;
        this.counter = iterations;
        this.getSprite().loadAnimation("moving");
        this.continuation = continuation;
    }
}

