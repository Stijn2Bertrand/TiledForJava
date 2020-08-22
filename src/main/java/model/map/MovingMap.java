package model.map;

import model.map.tiles.MovableTile;
import model.map.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class MovingMap extends Map{


    public MovingMap(int layers, int rows, int columns, CoordinateStrategy strategy) {
        super(layers, rows, columns, strategy);
    }

    public void smoothMove(int layer, int i, int j, int toI,int toJ){
        Tile tile = this.getTile(layer,i,j);
        if(tile instanceof MovableTile){
            this.smoothMove( (MovableTile) tile, toI, toJ);
        }
    }

    public void smoothMove(MovableTile tile, int toI,int toJ){
        int[] newCor = this.getStrategy().toWorldCoordinates(toI,toJ);
        tile.smoothMove(
                newCor[0],
                newCor[1],
                30,
                ()->super.teleport(tile, toI, toJ));

    }

    //todo: collision detection
    public void followPath(MovableTile tile, List<int[]> path){
        if(path.isEmpty())return;
        int[] newCor = this.getStrategy().toWorldCoordinates(path.get(0)[0],path.get(0)[1]);

        tile.smoothMove(
                newCor[0],
                newCor[1],
                30,
                ()->{
                    super.teleport(tile, path.get(0)[0], path.get(0)[1]);
                    if(path.size()>1)this.followPath(tile, path.subList(1,path.size()));
                });
    }

    //todo: add extra moving functions
}
