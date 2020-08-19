package model.map;

import model.map.tiles.Tile;

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


    public Tile getTile(int layer, int i, int j){
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

    public synchronized void addTile(int layer,int i, int j, Tile tile){
        int[] cor = strategy.toWorldCoordinates(i,j);
        tile.setCoordinateStrategy(strategy);
        tile.setPosition(layer,i,j);
        //tile.getSprite().setX(cor[0]);
        //tile.getSprite().setY(cor[1]);
        map[layer][i][j] = tile;
    }

    public synchronized void removeTile(int layer, int i, int j){
        map[layer][i][j] = null;
    }

    public synchronized void teleport(int layer, int i, int j,int toLayer, int toI, int toJ){
        map[toLayer][toI][toJ] = map[layer][i][j];
        map[layer][i][j]= null;
        map[toLayer][toI][toJ].setPosition(toLayer,toI,toJ);
    }

    public synchronized void swap(int layer, int i, int j,int toLayer, int toI, int toJ){
        Tile tile = map[toLayer][toI][toJ];
        map[toLayer][toI][toJ] = map[layer][i][j];
        map[layer][i][j]= tile;

        map[layer][i][j].setPosition(layer,i,j);
        map[toLayer][toI][toJ].setPosition(toLayer,toI,toJ);
    }

    public CoordinateStrategy getStrategy() {
        return strategy;
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

    public int[] getMapDim(){
        return strategy.getMapDim(this.rows,this.colums);
    }

}
