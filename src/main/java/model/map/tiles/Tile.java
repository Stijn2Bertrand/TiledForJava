package model.map.tiles;

import model.map.CoordinateStrategy;
import model.sprites.Sprite;

import java.awt.*;

public class Tile {

    private int i=0;
    private int j=0;
    private int layer=0;
    private Sprite sprite;
    protected CoordinateStrategy strategy;

    private int x;
    private int y;



    public Tile( Sprite sprite) {
        this.sprite = sprite;
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    public int getLayer() {
        return layer;
    }
    public Sprite getSprite() {
        return this.sprite;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int layer, int i, int j) {
        this.i = i;
        this.j = j;
        this.layer = layer;
        int[] cor = strategy.toWorldCoordinates(i,j);
        this.setX(cor[0]);
        this.setY(cor[1]);
    }

    public void setCoordinateStrategy(CoordinateStrategy strategy) {
        this.strategy=strategy;
    }

    public void draw(Graphics graphics) {
            graphics.drawImage(
                    this.getSprite().getImage(),
                    this.getX(),
                    this.getY(),
                    this.getSprite().getHeight(),
                    this.getSprite().getWidth(),
                    null);
    }
}
