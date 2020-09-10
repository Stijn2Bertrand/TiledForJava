package model.map.tiles;

import model.sprites.AnimatedSprite;

import java.awt.*;
import java.util.List;

public class MovableTile extends Tile {

    // a continuation interface
    public interface Continuation {
        public abstract void cont();
    }

    //Instance variables:
    private double dx = 0;
    private double dy = 0;
    private Continuation continuation = ()->{};

    //Constructor
    public MovableTile(AnimatedSprite sprite) {
        super(sprite);
    }

    private boolean moving = false;
    private int[] destination;

    @Override
    public void draw(Graphics graphics) {
        if(moving){
            this.setX((this.getX()-dx));
            this.setY((this.getY()-dy));

            this.getMap().ifPresent((map)->{
                //we want to check if the center of the sprite is in a new IJ location
                int[] cor = map.getStrategy().toBoardCoordinates(
                        (int)this.getX()+ (this.getSprite().getHeight()/2),
                        (int)this.getY()+ (this.getSprite().getWidth()/2)
                );

                if((map.getRows()<=cor[0]) || (map.getColums()<=cor[1])){
                    //the new position is out of bounds
                    map.removeTile(this);
                }else if( cor[0] != this.getI() || cor[1] != this.getJ()){
                    //check if the location is empty
                    if(map.getTile(this.getLayer(),cor[0],cor[1])!= null){
                        map.teleport(this,this.getI(),this.getJ());
                        this.moving = false;
                    }else{
                        map.moveTile(this,cor[0],cor[1]);
                    }
                }
            });

            //when we are close enough to the destination we stop
            if(     //x²+y²< R² (the equation for a circle)
                    ((destination[0]-this.getX())*(destination[0]-this.getX()))+
                    ((destination[1]-this.getY())*(destination[1]-this.getY())) < 100){
                this.moving = false;
                Continuation c = continuation;
                continuation = ()->{};
                c.cont();
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
                    32);
        });
    }

    //a function that makes the tile follow a path
    public void followPath(List<int[]> path){
        if(path.isEmpty())return;
        this.getMap().ifPresent((map)-> {
            int[] newCor = map.getStrategy().toWorldCoordinates(path.get(0)[0], path.get(0)[1]);

            this.smoothMove(
                    newCor[0],
                    newCor[1],
                    32,
                    () -> {
                        if (path.size() > 1) this.followPath(path.subList(1, path.size()));
                    });
        });
    }

    //we might want to make this method  package private
    protected void smoothMove(int x, int y, int iterations){
        this.smoothMove(x,y,iterations,()->{});
    }

    //set dx and dy here and the position is automatically updated.
    public void smoothMove(int x, int y, int iterations, Continuation continuation){
        this.getSprite().loadAnimation("moving");
        this.continuation = continuation;
        this.destination = new int[]{x,y};
        this.dx = (this.getX() - x)/ iterations;
        this.dy = (this.getY() - y)/ iterations;
        this.moving= true;
    }
}

