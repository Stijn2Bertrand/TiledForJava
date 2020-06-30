package model;

import model.sprites.Sprite;

import java.awt.*;

public class Tile {

    private int i;
    private int j;
    private Sprite sprite;

    public Tile( Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return this.sprite;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
