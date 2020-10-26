package model.drawables;

import drawloop.InputRegister;
import model.map.Map;
import model.map.tiles.MovableTile;
import model.map.tiles.Tile;
import model.map.tiles.arrow.ArrowTile;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.function.BiConsumer;

public class SelectingModel extends ListeningModel {

    private MovableTile selectedTile = null;

    public SelectingModel() {
        InputRegister.getInstance().registerMouse("select", 3, (cor,event)->{
            // if there is  an arrow, the right mouse button removes it
            synchronized(this){
                if(arrow != null){
                    this.getMap().removeTile(arrow);
                    this.selectedTile = null; //it also deselects the movable tile
                    arrow = null;
                }
            }
        });
    }

    public synchronized Optional<MovableTile> getSelectedTile() {
        if(selectedTile == null)return Optional.empty();
        return Optional.of(selectedTile);
    }

    @Override
    protected synchronized BiConsumer<Tile, MouseEvent> getOnTileClicked() {
        return (tile, mouseEvent)->{
            synchronized(this){
                //todo: remove:
                // System.out.println("layer: " + tile.getLayer() + " I:"  + tile.getI() + "  J:" + tile.getJ());

                // we are waiting for a click
                if(active){
                    this.tile = tile; // keep track of the clicked tile
                    synchronized (lock){
                        lock.notify();
                    }
                    return;
                }

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
                } else if(selectedTile.equals(this.getMap().getTile(1,tile.getI(),tile.getJ()))){
                    // deselect a tile
                    this.selectedTile = null;
                }else {
                    try{
                        //remove the arrow from the map
                        this.getMap().removeTile(arrow);
                        //make the movable tile move
                        this.selectedTile.followPath(arrow.getPath());
                        this.arrow = null;
                    }catch (Exception e){
                        e.printStackTrace();
                        // filters out the none Moving Maps
                    }
                    this.selectedTile=null;
                }
            }

        };
    }

    private ArrowTile arrow;

    @Override
    public BiConsumer<Tile, MouseEvent> getOnTileHovered() {
        return (tile, event)->{
            synchronized (this){
                if(arrow != null){
                    if(tile.getLayer()<2){
                        arrow.addPart(tile.getI(),tile.getJ());
                    }
                }
            }
        };
    }


    private boolean active = false;
    private Tile tile = null;
    private Object lock = new Object();
    public Tile getNextClickedTile(){
        try {
            synchronized (lock) {//not certain if I need to make this synchronized
                active = true;
                lock.wait();//let the game loop wait until the player has moved
                active = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tile;
    }


}
