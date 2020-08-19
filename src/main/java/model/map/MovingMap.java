package model.map;

import model.map.tiles.MovableTile;
import model.map.tiles.Tile;

public class MovingMap extends Map{


    public MovingMap(int layers, int rows, int columns, CoordinateStrategy strategy) {
        super(layers, rows, columns, strategy);
    }

    public void smoothMove(int layer, int i, int j, int toI,int toJ){
        Tile tile = this.getTile(layer,i,j);
        int[] newCor = this.getStrategy().toWorldCoordinates(toI,toJ);
        if(tile instanceof  MovableTile){
            ((MovableTile)tile).smoothMove(
                    newCor[0],
                    newCor[1],
                    30,
                    ()->super.teleport( layer,  i,  j,layer,  toI, toJ));
        }
    }

    //todo: add extra moving functions
}
