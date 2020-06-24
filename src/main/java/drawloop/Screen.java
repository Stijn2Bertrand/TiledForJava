package drawloop;


import javax.swing.*;
import java.awt.*;


public class Screen extends JFrame implements Runnable{


    private ExtendedCanvas extendedCanvas;

    public Screen(ExtendedCanvas drawable) throws HeadlessException {
        super("Title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the dimention of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(new Rectangle(screenSize.width,screenSize.height));

        this.extendedCanvas = new ExtendedCanvas();
        this.getContentPane().add(extendedCanvas);
        //show the frame
        this.setVisible(true);
        this.extendedCanvas.init();
    }



    /*
    *       DRAW LOOP
    * */
    private boolean running = true;
    public void stop(){
        running = false;
    }
    public void run() {
        while (running){
            try {
                Thread.sleep(50);//todo
                extendedCanvas.myRender();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //close and dispose the screen
        this.setVisible(false);
        this.dispose();
    }


}
