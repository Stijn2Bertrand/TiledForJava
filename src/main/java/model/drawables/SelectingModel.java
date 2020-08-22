package model.drawables;

import model.map.Map;
import model.map.MovingMap;
import model.map.tiles.Tile;
import model.map.tiles.arrow.ArrowTile;

import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;

public class SelectingModel extends ListeningModel {

    Tile selectedTile = null;

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileClicked() {
        return (tile, mouseEvent)->{

            if(mouseEvent.getButton() == 3 ){
                if(arrow != null){
                    this.getMap().removeTile(arrow.getLayer(),arrow.getI(),arrow.getJ());
                    arrow = null;
                }
            }else if(mouseEvent.getButton() == 1){
                //System.out.println("layer: " + tile.getLayer() + " I:"  + tile.getI() + "  J:" + tile.getJ());

                //set the selected tile:
                if(selectedTile == null){
                    // makes it so that we can only select tiles from the second layer
                    selectedTile = this.getMap().getTile(1,tile.getI(),tile.getJ());
                    if(selectedTile != null){
                        //create a new arrow for the selected tile
                        this.arrow = new ArrowTile(5);
                        this.getMap().addTile(2,tile.getI(),tile.getJ(),this.arrow);
                    }
                } else if(selectedTile.equals(this.getMap().getTile(1,tile.getI(),tile.getJ()))){// deselect
                    this.selectedTile = null;
                }else {
                    /*try{
                        ((MovingMap)this.getMap()).smoothMove(selectedTile.getLayer(),selectedTile.getI(),selectedTile.getJ(),tile.getI(),tile.getJ());
                    }catch (Exception e){
                        // filters out the none Moving Maps
                    }*/
                    //this.getMap().teleport(selectedTile.getLayer(),selectedTile.getI(),selectedTile.getJ(),selectedTile.getLayer(),tile.getI(),tile.getJ());

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
                    if(tile != null){
                        arrow.addPart(tile.getI(),tile.getJ());
                    }
                }
            }
        };
    }
}
