import drawloop.ExtendedCanvas;
import drawloop.Screen;
import model.Model;

public class main {



    public static void main(String[] args) {
        new Thread (new Screen( new Model())).start();
    }
}
