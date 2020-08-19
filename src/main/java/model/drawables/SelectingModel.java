package model.drawables;

import model.map.Map;
import model.map.MovingMap;
import model.map.tiles.Tile;

import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

public class SelectingModel extends ListeningModel {

    Tile selectedTile = null;

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileClicked() {
        return (tile, mouseEvent)->{
            //System.out.println("layer: " + tile.getLayer() + " I:"  + tile.getI() + "  J:" + tile.getJ());
            if(selectedTile == null){
                if(tile.getLayer()>0){//todo: make this more generic
                    this.selectedTile = tile;
                }
            } else if(selectedTile.equals(tile)){
                this.selectedTile = null;
            }else if(tile.getLayer()==0){//todo: make this more generic
                try{
                    ((MovingMap)this.getMap()).smoothMove(selectedTile.getLayer(),selectedTile.getI(),selectedTile.getJ(),tile.getI(),tile.getJ());
                }catch (Exception e){
                    // filters out the none Moving Maps
                }
                //this.getMap().teleport(selectedTile.getLayer(),selectedTile.getI(),selectedTile.getJ(),selectedTile.getLayer(),tile.getI(),tile.getJ());
                this.selectedTile=null;
            }
        };
    }

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileHovered() {
        return (tile, event)->{};
    }
}
