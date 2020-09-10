package model.map;

import model.map.tiles.Tile;
import model.map.tiles.arrow.ArrowTile;

import java.util.function.Consumer;

public class Map {

    private int layers,rows,colums;

    private Tile[][][] map;
    private CoordinateStrategy strategy;


    public Map(int layers, int rows, int columns, CoordinateStrategy strategy){
        this.map = new Tile[layers][rows][columns];
        this.layers = layers;
        this.rows = rows;
        this.colums = columns;
        this.strategy = strategy;
    }

    public int getRows() {
        return rows;
    }

    public int getColums() {
        return colums;
    }

    //the size of the map in pixels
    public int[] getMapDim(){
        return strategy.getMapDim(this.rows,this.colums);
    }

    public CoordinateStrategy getStrategy() {
        return strategy;
    }

    public Tile getTile(int layer, int i, int j){
        return map[layer][i][j];
    }

    //returns the highest none null tile or null if there are no tiles
    public Tile getTile(int i, int j){
        for(int layer = map.length-1; layer >=0; layer --)
        if(map[layer][i][j]!= null){
            return map[layer][i][j];
        }
        return null;
    }

    public synchronized void addTile(int layer, int i, int j, Tile tile){
        //1. add the tile to the map
        map[layer][i][j] = tile;

        //update the tile
        //2. set map as the map of the tile
        tile.setMap(this,layer,i,j);
    }

    public synchronized void moveTile(Tile tile, int toI, int toJ){
        map[tile.getLayer()][tile.getI()][tile.getJ()] = null;
        map[tile.getLayer()][toI][toJ]= tile;
        tile.setIJ(new int[]{toI,toJ});
    }

    public void removeTile(Tile tile) {
        removeTile(tile.getLayer(),tile.getI(),tile.getJ());
    }

    public synchronized void removeTile(int layer, int i, int j){
        map[layer][i][j] = null;
    }




    public synchronized void forEachTile(Consumer<Tile> c){
        for(Tile[][] layer: map){
            for(Tile[] row: layer){
                for(Tile t : row){
                    c.accept(t);
                }
            }
        }
    }

    /* Other use full methods, maybe these don't belong here*/
    public void teleport(int layer, int i, int j, int toI, int toJ){
        Tile tile = this.getTile(layer,i,j);
        this.teleport(tile,toI,toJ);
    }

    public synchronized void teleport(Tile tile, int toI, int toJ){
        map[tile.getLayer()][tile.getI()][tile.getJ()] = null;
        map[tile.getLayer()][toI][toJ]= tile;
        tile.setPosition(tile.getLayer(),toI,toJ);
    }

    public synchronized void swap(int layer, int i, int j,int toLayer, int toI, int toJ){
        Tile tile = map[toLayer][toI][toJ];
        map[toLayer][toI][toJ] = map[layer][i][j];
        map[layer][i][j]= tile;

        map[layer][i][j].setPosition(layer,i,j);
        map[toLayer][toI][toJ].setPosition(toLayer,toI,toJ);
    }
}
