package model.functionality;

import model.Model;
import model.Tile;

import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

public abstract class ListeningModel extends Model {

    private Tile clickedTile;
    private Tile hoveredTile;

    @Override
    public BiConsumer<int[],MouseEvent> getMouseClickedListener() {
        return (mapcor, event) -> {
            int[] cor = this.getMap().getStrategy().toBoardCoordinates(mapcor[0],mapcor[1]);
            Tile tile = this.getMap().getTile(cor[0],cor[1]);
            getOnTileClicked().accept(tile,event);
            this.clickedTile = tile;
        };
    }

    @Override
    public BiConsumer<int[],MouseEvent> getMouseMovedListener() {
        return (mapcor, event) -> {
            int[] cor = this.getMap().getStrategy().toBoardCoordinates(mapcor[0],mapcor[1]);
            Tile tile = this.getMap().getTile(cor[0],cor[1]);
            if(this.hoveredTile != tile){
                getOnTileHovered().accept(tile,event);
                this.hoveredTile = tile;
            }
        };
    }

    public Tile getClickedTile() {
        return clickedTile;
    }
    public Tile getHoveredTile() {
        return hoveredTile;
    }

    public abstract BiConsumer<Tile,MouseEvent> getOnTileClicked();
    public abstract BiConsumer<Tile,MouseEvent> getOnTileHovered();

}
