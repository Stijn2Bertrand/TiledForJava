package model.map.tiles;

import model.map.CoordinateStrategy;
import model.sprites.Sprite;

public class Tile {

    private int i=0;
    private int j=0;
    private int layer=0;
    private Sprite sprite;
    private CoordinateStrategy strategy;

    public Tile( Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
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

    public void setPosition(int layer, int i, int j) {
        this.i = i;
        this.j = j;
        this.layer = layer;
        int[] cor = strategy.toWorldCoordinates(i,j);
        this.getSprite().setX(cor[0]);
        this.getSprite().setY(cor[1]);
    }

    public void setCoordinateStrategy(CoordinateStrategy strategy) {
        this.strategy=strategy;
    }
}
