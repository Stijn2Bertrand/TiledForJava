import drawloop.ExtendedCanvas;
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
import mygame.Wizard;

public class main {



    public static void main(String[] args) {
        //register sprite sheets
        Register register = Register.getInstance();
        register.registerSheet("first","/64sprites3.png",8,8);
        register.registerSheet("arrows","/arrows.png",8,8);

        //register maps
        MapRegister mapRegister = MapRegister.getInstance();
        mapRegister.registerMap("firstMap","maps/map_2.json");

        //Background for the startscreen
        //Background background = new Background("/startscreenBackground.png");

        // a Model for a map
        Model model = new SelectingModel();
       // Map map = mapRegister.getMap("firstMap");
        Map map = mapRegister.getMap("firstMap");

        //creating an animated sprite
        AnimatedSprite sprite = new AnimatedSprite("first",0);
        sprite.addAnimation("name",5,2,3,4);
        sprite.loadAnimation("name");
        //adding a tile with the animated sprite to the map
        Tile tile = new Tile(sprite);
        map.addTile(1,10,10 ,tile);

        //adding a Moving tile to the map
        //AnimatedSprite movingSprite = new AnimatedSprite("first",0);
        //movingSprite.addAnimation("moving",5,2,3,4);
        //MovableTile movableTile = new MovableTile(movingSprite);
        //map.addTile(1,2,4 ,movableTile);


        //adding a Wizard to the map
        Wizard wizard = new Wizard();
        wizard.enterMap(map,1,2,4);
        wizard.enterMap(map,1,4,4);

        model.setMap(map);

        new Thread (new Screen( new ExtendedCanvas(model),null)).start();
        //new Thread (new Screen( new ExtendedCanvas(model),new Overlay())).start();

        wizard.spawnFireball(15,15);
    }
}
