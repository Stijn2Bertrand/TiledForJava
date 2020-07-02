package model.mapload;

public class CoordinateStrategy {


    private int spriteWidth;
    private int spriteHeight;

    public CoordinateStrategy(int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    //todo: this 24 is only for hextiles of size 128
    public int[] toBoardCoordinates(int x, int y){
        int J = (x+spriteWidth/2) / spriteWidth;
        int I = (y+24) / (spriteHeight - 24);

        int a = (x+(spriteWidth/2))%spriteWidth;
        int b = (y+(24))%(spriteHeight - 24);
        //Split the region in 2 pieces
        if( (a*a + b*b) < (((spriteHeight/2)*(spriteHeight/2))+(spriteWidth/2)*(spriteWidth/2))){

        }else if( ( (spriteWidth-a )*(spriteWidth-a ) + (spriteHeight-b)*(spriteHeight-b))
            < (((spriteHeight/2)*(spriteHeight/2))+(spriteWidth/2)*(spriteWidth/2))
            ){//split again
            J++;
        }else{
            I++;J++;
        };
        return new int[]{I, J};
    }

    public int[] toWorldCoordinates(int i, int j){
        //in case of j being the colums:
        int x = (-spriteWidth/2 + j*(spriteWidth ));//x is horizontal
        int y = (-24 + i*(spriteHeight - 24));

        if( i %2 == 1){
            x += (spriteWidth/2);
        }
        return new int[]{ x, y};
    }


    public int[] getMapDim(int rows, int columns){
        int[] result = toWorldCoordinates(rows, columns);
        return result;
    }

}
