import drawloop.ExtendedCanvas;
import drawloop.KeyRegister;
import drawloop.Overlay;
import drawloop.Screen;
import model.drawables.Background;
import model.drawables.SelectingModel;
import model.map.Map;
import model.drawables.Model;
import model.map.tiles.MovableTile;
import model.map.tiles.Tile;
import model.map.MapRegister;
import model.sprites.AnimatedSprite;
import model.sprites.Register;
import mygame.Unit;
import mygame.Wizard;
import mygame.units.Druid;
import mygame.units.Knight;
import mygame.units.Troll;
import mygame.units.Wolf;

import javax.swing.*;

public class main {



    public static void main(String[] args) {
        //register sprite sheets
        Register register = Register.getInstance();
        register.registerSheet("first","/64sprites3.png",8,8);
        register.registerSheet("arrows","/arrows.png",8,8);
        register.registerSheet("annimations","/animations.png",8,8);

        //register maps
        MapRegister mapRegister = MapRegister.getInstance();
        mapRegister.registerMap("firstMap","maps/map_2.json");

        //Background for the startscreen
        //Background background = new Background("/startscreenBackground.png");

        // a Model for a map
        SelectingModel model = new SelectingModel();
       // Map map = mapRegister.getMap("firstMap");
        Map map = mapRegister.getMap("firstMap");

        //creating an animated sprite
        //AnimatedSprite sprite = new AnimatedSprite("annimations",0);
        //sprite.addAnimation("name",0,1,2);
        //sprite.loadAnimation("name");
        //adding a tile with the animated sprite to the map
        //Tile tile = new Tile(sprite);
        //map.addTile(1,10,10 ,tile);

        //adding a Moving tile to the map
        //AnimatedSprite movingSprite = new AnimatedSprite("first",0);
        //movingSprite.addAnimation("moving",5,2,3,4);
        //MovableTile movableTile = new MovableTile(movingSprite);
        //map.addTile(1,2,4 ,movableTile);


        //adding a Wizard to the map
        new Wizard().enterMap(map,1,4,4);
        new Wizard().enterMap(map,1,2,6);

        // adding some trolls
        new Troll().enterMap(map,1,4,10);
        new Troll().enterMap(map,1,12,20);
        new Troll().enterMap(map,1,15,19);
        new Troll().enterMap(map,1,15,21);

        //adding some wolfs
        new Wolf().enterMap(map,1,6,9);
        new Wolf().enterMap(map,1,3,20);

        new Druid().enterMap(map,1,4,5);
        new Knight().enterMap(map,1,4,6);

        model.setMap(map);

        ExtendedCanvas canvas  = new ExtendedCanvas(model);
        Screen screen = new Screen(canvas ,null);
        new Thread (screen).start();


        KeyRegister keyRegister = new KeyRegister(canvas);
        /*Key pressed code=65, char=a
        Key pressed code=90, char=z
        Key pressed code=69, char=e
        Key pressed code=82, char=r
        Key pressed code=84, char=t*/
        keyRegister.addKeyListener(65,(event)->{
            model.getSelectedTile().ifPresent((value)->{
                if(value instanceof Unit){
                    Unit unit = (Unit)value;
                    unit.doQ();
                }
            });
        });
        //new Thread (new Screen( new ExtendedCanvas(model),new Overlay())).start();
    }
}
