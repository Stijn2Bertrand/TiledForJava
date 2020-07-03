import drawloop.ExtendedCanvas;
import drawloop.Screen;
import model.Model;
import model.mapload.MapLoader;

public class main {



    public static void main(String[] args) {
        MapLoader mapLoader = new MapLoader();

        Model model = new Model();
        model.setMap(mapLoader.LoadMap("maps/map_2.json" ));

        new Thread (new Screen( new ExtendedCanvas(model))).start();
    }
}
