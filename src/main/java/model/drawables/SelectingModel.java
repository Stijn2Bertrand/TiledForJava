package model.drawables;

import model.map.Map;
import model.map.tiles.MovableTile;
import model.map.tiles.Tile;
import model.map.tiles.arrow.ArrowTile;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

public class SelectingModel extends ListeningModel {

    MovableTile selectedTile = null;


    //todo: if I want to make this synchronized I also need to make the "setting" of the selected tile synchronized
    public Optional<MovableTile> getSelectedTile() {
        if(selectedTile == null)return Optional.empty();
        return Optional.of(selectedTile);
    }

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileClicked() {
        return (tile, mouseEvent)->{

            if(mouseEvent.getButton() == 3 ){
                if(arrow != null){
                    this.getMap().removeTile(arrow.getLayer(),arrow.getI(),arrow.getJ());
                    this.selectedTile = null;
                    arrow = null;
                }
            }else if(mouseEvent.getButton() == 1){
                //System.out.println("layer: " + tile.getLayer() + " I:"  + tile.getI() + "  J:" + tile.getJ());

                //set the selected tile:
                if(selectedTile == null){
                    // makes it so that we can only select tiles from the second layer
                    if(this.getMap().getTile(1,tile.getI(),tile.getJ()) instanceof MovableTile){
                        selectedTile = (MovableTile)this.getMap().getTile(1,tile.getI(),tile.getJ());
                        if(selectedTile != null){
                            //create a new arrow for the selected tile
                            this.arrow = new ArrowTile(5);
                            this.getMap().addTile(2,tile.getI(),tile.getJ(),this.arrow);
                        }
                    }
                } else if(selectedTile.equals(this.getMap().getTile(1,tile.getI(),tile.getJ()))){// deselect
                    this.selectedTile = null;
                }else {
                    try{
                        //remove the arrow from the map
                        this.getMap().removeTile(arrow);
                        //make the movable tile move
                        this.selectedTile.followPath(arrow.getPath());
                        this.arrow = null;
                    }catch (Exception e){
                        // filters out the none Moving Maps
                    }
                    this.selectedTile=null;
                    //todo: turn off arrow
                }
            }
        };
    }

    private ArrowTile arrow;

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileHovered() {
        return (tile, event)->{
            if(arrow != null){
                if(tile.getLayer()<2){
                    arrow.addPart(tile.getI(),tile.getJ());
                }
            }
        };
    }
}
