package model.map;

import model.map.strategies.HexStrategy;
import model.map.strategies.OrthoStrategy;

public interface CoordinateStrategy {

    static CoordinateStrategy getHexTileCoordinateStrategy(int spriteWidth, int spriteHeight){
        return new HexStrategy(spriteWidth,spriteHeight);
    }

    static CoordinateStrategy getOrthogonalTileCoordinateStrategy(int spriteWidth, int spriteHeight){
        return new OrthoStrategy(spriteWidth,spriteHeight);
    }

    int[] toBoardCoordinates(int x, int y);
    int[] toWorldCoordinates(int i, int j);
    int[] getMapDim(int rows, int columns);

}
