package drawloop;


import javax.swing.*;
import java.awt.*;


public class Screen extends JFrame implements Runnable{


    private ExtendedCanvas canvas;

    public Screen(ExtendedCanvas canvas) throws HeadlessException {
        super("Title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the dimention of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(new Rectangle(screenSize.width,screenSize.height));

        this.canvas = canvas;
        this.getContentPane().add(canvas);
        //show the frame
        this.setVisible(true);
        this.canvas.init();
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
                Thread.sleep(32);//todo
                canvas.myRender();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //close and dispose the screen
        this.setVisible(false);
        this.dispose();
    }


}
