package model.drawables;

import drawloop.InputRegister;
import model.map.tiles.Tile;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class ListeningModel extends Model {

    private Tile hoveredTile;

    public ListeningModel() {
        InputRegister.getInstance().registerMouse("select",1,(mapcor, event)->{
            int[] cor = this.getMap().getStrategy().toBoardCoordinates(mapcor[0],mapcor[1]);
            Tile tile = this.getMap().getTile(cor[0],cor[1]);
            synchronized(this){
                getOnTileClicked().accept(tile,event);
            }
        });

        InputRegister.getInstance().registerMouse("hover",4,(mapcor, event)->{
            int[] cor = this.getMap().getStrategy().toBoardCoordinates(mapcor[0],mapcor[1]);
            Tile tile = this.getMap().getTile(cor[0],cor[1]);
            synchronized(this){
                if(this.hoveredTile != tile){
                    this.hoveredTile = tile;
                    getOnTileHovered().accept(tile,event);
                }
            }
        });
    }

    //these methods should not be called manually, hence protected access
    protected abstract BiConsumer<Tile,MouseEvent> getOnTileClicked();
    protected abstract BiConsumer<Tile,MouseEvent> getOnTileHovered();

}
