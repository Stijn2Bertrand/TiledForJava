package model;

import model.mapload.CoordinateStrategy;

import java.util.function.Consumer;

public class Map {


    private Tile[][][] map;
    private CoordinateStrategy strategy = new CoordinateStrategy();


    public Map(int layers, int rows, int columns){
        this.map = new Tile[layers][rows][columns];
    }


    public Tile getTile(int layer,int i, int j){
        return map[layer][i][j];
    }

    public void addTile(int layer,int i, int j, Tile tile){
        //todo get the 128 from somewhere else
        int[] cor = strategy.toWorldCoordinates(i,j,128,128);
        tile.setI(i);
        tile.setJ(j);
        tile.getSprite().setX(cor[0]);
        tile.getSprite().setY(cor[1]);
        map[layer][i][j] = tile;
    }

    public CoordinateStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CoordinateStrategy strategy) {
        this.strategy = strategy;
    }

    public void forEachTile(Consumer<Tile> c){
        for(Tile[][] layer: map){
            for(Tile[] row: layer){
                for(Tile t : row){
                    c.accept(t);
                }
            }
        }
    }
}
