package model.map.tiles;

import model.map.CoordinateStrategy;
import model.map.Map;
import model.sprites.Sprite;

import java.awt.*;
import java.util.Optional;

public class Tile {

    private int i=0;
    private int j=0;
    private int layer=0;
    private Sprite sprite;
    //I want a reference to the map here, which can be null => I need a setter
    private Optional<Map> map = Optional.empty();

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
    public Optional<Map> getMap() {
        return map;
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


    // Use this method to enter the map (alternatively you can use the addTile method off map to do the same thing)
    public void enterMap(Map map, int layer, int i, int j) {
        //1. add the tile to the map
        map.addTile( layer, i,  j, this);
    }

    // I would prefer to make the setMap and the setPosition methods protected/package private such that only Map can use them
    // but then Tile needs to be in the same class as Map
    public void setMap(Map map, int layer, int i, int j) {
        assert map != null;
        //remove from previous map
        this.map.ifPresent((value)->value.removeTile(this));
        this.map = Optional.of(map);
        setPosition(layer, i, j);
    }

    public void setPosition(int layer, int i, int j) {
        this.i = i;
        this.j = j;
        this.layer = layer;
        map.ifPresent((map)->{
            int[] cor = map.getStrategy().toWorldCoordinates(i,j);
            this.setX(cor[0]);
            this.setY(cor[1]);
        } );
    }
}
