package model;

import model.mapload.CoordinateStrategy;

import java.util.function.Consumer;

public class Map {

    private int layers,rows,colums;

    private Tile[][][] map;
    //todo: let mapLoader set the coordinates strategy
    private CoordinateStrategy strategy;


    public Map(int layers, int rows, int columns, CoordinateStrategy strategy){
        this.map = new Tile[layers][rows][columns];
        this.layers = layers;
        this.rows = rows;
        this.colums = columns;
        this.strategy = strategy;
    }


    public Tile getTile(int layer,int i, int j){
        return map[layer][i][j];
    }

    public void addTile(int layer,int i, int j, Tile tile){
        int[] cor = strategy.toWorldCoordinates(i,j);
        tile.setI(i);
        tile.setJ(j);
        tile.getSprite().setX(cor[0]);
        tile.getSprite().setY(cor[1]);
        map[layer][i][j] = tile;
    }

    private CoordinateStrategy getStrategy() {
        return strategy;
    }

    //todo: should probably remove this method??
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

    public int[] getMapDim(){
        return strategy.getMapDim(this.rows,this.colums);
    }

}
