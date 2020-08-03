package model.draw;

import model.mapload.CoordinateStrategy;

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


    public Tile getTile(int layer,int i, int j){
        return map[layer][i][j];
    }

    //returns the highest none null tile or null is there are no tiles
    public Tile getTile(int i, int j){
        for(int layer = map.length-1; layer >=0; layer --)
        if(map[layer][i][j]!= null){
            return map[layer][i][j];
        }
        return null;
    }

    public void addTile(int layer,int i, int j, Tile tile){
        int[] cor = strategy.toWorldCoordinates(i,j);
        tile.setI(i);
        tile.setJ(j);
        tile.getSprite().setX(cor[0]);
        tile.getSprite().setY(cor[1]);
        map[layer][i][j] = tile;
    }

    public CoordinateStrategy getStrategy() {
        return strategy;
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
