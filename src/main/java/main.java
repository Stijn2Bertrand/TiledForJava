import drawloop.ExtendedCanvas;
import drawloop.Overlay;
import drawloop.Screen;
import model.drawables.Background;
import model.drawables.SelectingModel;
import model.map.Map;
import model.drawables.Model;
import model.map.tiles.Tile;
import model.map.MapRegister;
import model.sprites.AnimatedSprite;
import model.sprites.Register;

public class main {



    public static void main(String[] args) {
        Register register = Register.getInstance();
        register.registerSheet("first","/64sprites3.png",8,8);

        MapRegister mapRegister = MapRegister.getInstance();
        mapRegister.registerMap("firstMap","maps/map_2.json");

        //Background for the startscreen
        //Background background = new Background("/startscreenBackground.png");

        // a Model for a map
        Model model = new SelectingModel();
        Map map = mapRegister.getMap("firstMap");

        //creating an animated sprite to the map
        AnimatedSprite sprite = new AnimatedSprite("first",0);
        sprite.addAnimation("name",5,2,3,4);
        sprite.loadAnimation("name");

        //adding a tile with the animated sprite to the map
        Tile tile = new Tile(sprite);
        map.addTile(1,10,10 ,tile);


        model.setMap(map);

        //new Thread (new Screen( new ExtendedCanvas(model),null)).start();
        new Thread (new Screen( new ExtendedCanvas(model),new Overlay())).start();
    }
}
