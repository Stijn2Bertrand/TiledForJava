package drawloop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.ColorModel;
import java.lang.reflect.Array;

public class ExtendedCanvas extends Canvas{


    /*public ExtendedCanvas(GraphicsConfiguration config) {
        super(config);
    }*/

    private Drawable drawable;

    public ExtendedCanvas(Drawable drawable) {
        super();
        this.drawable = drawable;
    }

    public void init(){
        this.createBufferStrategy(3);
        initZoom();
        initDrag();
    }


    public void myRender(){
        Graphics2D graphics = (Graphics2D)this.getBufferStrategy().getDrawGraphics();


        //concatenate the transform to the already present transforms
        AffineTransform translation = new AffineTransform(new double[]{1.0, 0.0, 0.0, 1.0, this.xOffset, this.yOffset});
        graphics.transform( translation);
        graphics.scale(this.scale, this.scale);

        this.drawable.draw( graphics);

        graphics.dispose();
        this.getBufferStrategy().show();
    }



    //region ZOOM:
    private double maxScale = 5;
    private double minScale = 0.5;
    private double scale = 1.5;

    private void setScale(double scale) {
        this.scale =  Math.min(Math.max(scale,minScale),maxScale);
    }

    private void initZoom(){
        this.addMouseWheelListener(e -> {
            double xMouse =  (xOffset - e.getX())/scale;
            double yMouse =  (yOffset - e.getY())/scale;

            if ((e.getWheelRotation() == 1)) setScale(scale - 0.2);
            else setScale(scale + 0.2);

            //keep the mouse at the same place on the map when zooming
            setxOffset((int)(xMouse * scale) + e.getX());
            setyOffset((int)(yMouse * scale) + e.getY());
        });
    }

    //endregion

    //region DRAG:
    private int xOffset =0;
    private int yOffset =0;

    private void setxOffset(int xOffset) {
        this.xOffset = Math.max(
                Math.min(xOffset,0),
                (int)(this.getWidth()- (scale * drawable.getWidth()))
        );
    }

    private void setyOffset(int yOffset) {
        this.yOffset = Math.max(
                Math.min(yOffset ,0),
                (int)(this.getHeight()-( scale * drawable.getHeight() ))
        );
    }

    private void initDrag(){
        DragListener listener = new DragListener() ;
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    private class DragListener implements MouseMotionListener, MouseListener {
        private int x = 0;
        private int y = 0;

        /*unimplemented*/
        @Override
        public void mouseClicked(MouseEvent e) {
            drawable.getMouseClickedListener().accept(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            x=0;
            y=0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            setxOffset(xOffset - x + e.getX());
            setyOffset(yOffset - y + e.getY());

            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            drawable.getMouseMovedListener().accept(e);
        }
    }

    //endregion


}
