import drawloop.ExtendedCanvas;
import drawloop.Overlay;
import drawloop.Screen;
import model.draw.Model;
import model.mapload.MapRegister;
import model.sprites.Register;

public class main {



    public static void main(String[] args) {
        Register register = Register.getInstance();
        register.registerSheet("first","/64sprites3.png",8,8);

        MapRegister mapRegister = MapRegister.getInstance();
        mapRegister.registerMap("firstMap","maps/map_2.json");

        Model model = new Model();
        model.setMap(mapRegister.getMap("firstMap"));

        new Thread (new Screen( new ExtendedCanvas(model),new Overlay())).start();
    }
}
