package mygame;

import model.map.Map;
import model.map.tiles.MovableTile;
import model.map.tiles.Tile;
import model.sprites.AnimatedSprite;
import java.util.function.Consumer;

public class Wizard extends Unit {

    public Wizard() {
        super(new AnimatedSprite("first",0));
        this.getSprite().addAnimation("moving", 0,0,0);

    }


    public void spawnFireball(int targetI, int targetJ){
        this.getMap().ifPresent((map)->{
            Fireball fireball = new Fireball(targetI,targetJ);

            //todo supply a better start position
            map.addTile(2,this.getI(),this.getJ()+1, fireball);
        });
    }
}

class Fireball extends MovableTile{

    private int targetI,targetJ;

    public Fireball(int targetI,int targetJ) {
        super(new AnimatedSprite("first",5));
        this.targetI = targetI;
        this.targetJ = targetJ;
        this.getSprite().addAnimation("moving", 5,6,7,6);
    }

    @Override
    public void setMap(Map map, int layer, int i, int j){
        super.setMap(map, layer, i, j);
        //this makes the fireball move the moment that the map is set
        int[] cor =  map.getStrategy().toWorldCoordinates(targetI,targetJ);
        this.smoothMove(cor[0],cor[1],100, this::explode);
    }


    public void explode(){
        this.getSprite().stopAnimation();
        hexCircle((tile)->{
           if(tile instanceof Unit){
               ((Unit)tile).takeDamage(30);
           }
        },
                2,
                this.getI(),
                this.getJ());
        this.getMap().ifPresent((map)->map.removeTile(this));
    }

    private void hexCircle(Consumer<Tile> function, int layer, int i, int j){
        this.getMap().ifPresent((map)->{
            //todo pick the 6 righth ones
            //todo: out of bounds detection
            function.accept(map.getTile(layer,i+1,j));
            function.accept(map.getTile(layer,i+1,j-1));
            function.accept(map.getTile(layer,i+1,j+1));

            function.accept(map.getTile(layer,i-1,j));
            function.accept(map.getTile(layer,i-1,j-1));
            //map.getTile(this.getLayer(),this.getI()-1,this.getJ()+1);

            //map.getTile(this.getLayer(),this.getI(),this.getJ());
            //map.getTile(this.getLayer(),this.getI(),this.getJ()-1);
            //map.getTile(this.getLayer(),this.getI(),this.getJ()+1);
        });
    }
}