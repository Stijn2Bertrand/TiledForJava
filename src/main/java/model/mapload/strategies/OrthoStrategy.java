package model.mapload.strategies;

import model.mapload.CoordinateStrategy;

public class OrthoStrategy implements CoordinateStrategy {

    private int spriteWidth;
    private int spriteHeight;

    public OrthoStrategy(int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    @Override
    public int[] toBoardCoordinates(int x, int y){
        int J = x / spriteWidth;
        int I = y / spriteHeight;

        return new int[]{I, J};
    }

    @Override
    public int[] toWorldCoordinates(int i, int j){
        int x = j*(spriteWidth );//x is horizontal
        int y = i*(spriteHeight);
        return new int[]{ x, y};
    }

    @Override
    public int[] getMapDim(int rows, int columns){
        int[] result = toWorldCoordinates(rows, columns);
        return result;
    }
}
