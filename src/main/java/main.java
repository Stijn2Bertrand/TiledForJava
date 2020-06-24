import drawloop.ExtendedCanvas;
import drawloop.Screen;

public class main {



    public static void main(String[] args) {
        new Thread (new Screen( new ExtendedCanvas())).start();
    }
}
