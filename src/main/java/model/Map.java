package model;

import java.util.function.Consumer;

public class Map {


    private Tile[][][] map;

    public Map(int layers, int rows, int columns){
        this.map = new Tile[layers][rows][columns];
    }


    public Tile getTile(int layer,int i, int j){
        return map[layer][i][j];
    }

    public void addTile(int layer,int i, int j, Tile tile){
        map[layer][i][j] = tile;
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
