package model.mapload;

public class CoordinateStrategy {





    public int[] toBoardCoordinates(int x, int y, int spriteWidth,int SpriteHeight){

        int J = Math.round(((x+50)/spriteWidth));
        int I = Math.round(((y+16)/(SpriteHeight - (spriteWidth/6 ))));


        int a = (x+50)%spriteWidth;
        int b = (y+16)%(SpriteHeight - (spriteWidth/6 ));
        //Split the region in 2 pieces
        if( (a*a + b*b) < (((SpriteHeight/2)*(SpriteHeight/2))+(spriteWidth/2)*(spriteWidth/2))){

        }else if( ( (spriteWidth-a )*(spriteWidth-a ) + (SpriteHeight-b)*(SpriteHeight-b))
            < (((SpriteHeight/2)*(SpriteHeight/2))+(spriteWidth/2)*(spriteWidth/2))
            ){//split again
            J++;
        }else{
            I++;J++;
        };
        //console.log("i:" + I + " j: " + J)
        return new int[]{I, J};
    }

    public int[] toWorldCoordinates(int i, int j, int spriteWidth,int SpriteHeight){
        //in case of j being the colums:
        int x = (-50 + j*(spriteWidth  ));//x is horizontaal
        int y = (-16 + i*(SpriteHeight - (spriteWidth/6 ))) ;

        if( i %2 == 1){
            x += (spriteWidth/2);
        }
        return new int[]{ x, y};
    }
}



/*
*
*
*
*
 * used to decode the json file
 *
_base64ToArrayBuffer(base64) {
        const binary_string = window.atob(base64);
        const len = binary_string.length;
        const bytes32 = new Uint32Array(len/4);
    for (let i = 0; i < len; i++) {
        let value = 0;
        for(let j =3;j>=0;j-- ){
            value = (value*8 + binary_string.charCodeAt(4*i+j))
        }
        bytes32[i] = value;
    }
    return bytes32;
}
*
*
*
* spriteDim={w:128,h:128, scale:0.7};
    toWorldCoordinates(i,j){
        //in case of j being the colums:
        let x = this.spriteDim.scale *(-50 + j*(this.spriteDim.w  ));//x is horizontaal
        let y = this.spriteDim.scale *(-16 + i*(this.spriteDim.h - (this.spriteDim.w/6 ))) ;

        if( i %2 === 1){
            x += (this.spriteDim.w/2)*this.spriteDim.scale
        }
        return {x: x, y:y}
    }
    toBoardCoordinates(x, y){

        let J = Math.round(((x/this.spriteDim.scale)+50)/this.spriteDim.w);
        let I = Math.round(((y/this.spriteDim.scale)+16)/(this.spriteDim.h - (this.spriteDim.w/6 )));


        let a = ((x/this.spriteDim.scale)+50)%this.spriteDim.w
        let b = ((y/this.spriteDim.scale)+16)%(this.spriteDim.h - (this.spriteDim.w/6 ))
        //Split the region in 2 pieces
        if( (a**2 + b**2) < (((this.spriteDim.h/2)**2)+(this.spriteDim.w/2)**2)){

        }else if( ( (this.spriteDim.w-a )**2 + (this.spriteDim.h-b)**2)
            < (((this.spriteDim.h/2)**2)+(this.spriteDim.w/2)**2)
            ){//split again
            J++;
        }else{
            I++;J++;
        };
        //console.log("i:" + I + " j: " + J)
        return {i:I,j:J};
    }
* */