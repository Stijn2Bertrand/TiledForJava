package model.map.tiles.arrow;

import model.sprites.Sprite;

import java.awt.*;
import java.util.function.Supplier;

class ArrowSprite extends Sprite {

    public ArrowSprite() {
        super("arrows" , 0);
    }

    public ArrowSprite(String sheetName, int defaultSprite) {
        super(sheetName, defaultSprite);
    }




    public void setBody(int enter, int exit){
        if(enter<exit){
            this.imageSupplier = this.getSpriteSheet().getImageSupplier(8*enter + (exit-enter));
        }
        if(exit<enter){
            this.imageSupplier = this.getSpriteSheet().getImageSupplier(8*exit + (enter-exit));
        }
    }


    public void setHead(int enter){
        //set it to the arrow
        this.imageSupplier = this.getSpriteSheet().getImageSupplier(enter*8);
    }

    private Supplier<Image> imageSupplier = super::getImage;

    @Override
    public Image getImage() {
        return imageSupplier.get();
    }

}
