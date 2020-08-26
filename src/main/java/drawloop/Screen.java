package drawloop;


import gui.Util;

import javax.swing.*;
import java.awt.*;


public class Screen extends JFrame implements Runnable{


    private ExtendedCanvas canvas;

    public Screen(ExtendedCanvas canvas, Overlay overlay) throws HeadlessException {
        super("Title");
        this.canvas = canvas;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set the dimention of the frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(new Rectangle(screenSize.width,screenSize.height));

        this.getContentPane().add(canvas);
        if (overlay != null){ //todo: improve the handeling of the overlay
            overlay.setBackgroundSize(screenSize.width,screenSize.height);
            this.getContentPane().add(overlay);
            this.getContentPane().setComponentZOrder(overlay,0);
            this.getContentPane().setComponentZOrder(canvas,1);
        }

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
