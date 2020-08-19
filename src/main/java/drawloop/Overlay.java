package drawloop;

import gui.Util;

import javax.swing.*;
import java.awt.*;

//todo: define an api for Overlao
// - how to create/tune an Overlay
// - how to customize?? I think this task does not belong here, not certain
public class Overlay extends JPanel {

    private int backgroundWidth;
    private int backgroundHeight;

    private double heightPercentage = 0.50;
    private double widthPercentage = 0.25;

    public Overlay() {
        this.add(createOverlay());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,  5));
    }

    public void setBackgroundSize(int width,int height){
        this.backgroundWidth = width;
        this.backgroundHeight = height;
        setBoundsCenter();
    }


    private void setBoundsCenter(){
        this.setBounds(
                (backgroundWidth-(int)(backgroundWidth*widthPercentage))/2,
                (backgroundHeight-(int)(backgroundHeight*heightPercentage))/2,
                (int)(backgroundWidth*widthPercentage),
                (int)(backgroundHeight*heightPercentage));
    }

    private void setBoundsRight(){
        this.setBounds(
                backgroundWidth-(int)(backgroundWidth*widthPercentage),
                (backgroundHeight-(int)(backgroundHeight*heightPercentage))/2,
                (int)(backgroundWidth*widthPercentage),
                (int)(backgroundHeight*heightPercentage));
    }

    private void setBoundsLeft(){
        this.setBounds(
                0,
                (backgroundHeight-(int)(backgroundHeight*heightPercentage))/2,
                (int)(backgroundWidth*widthPercentage),
                (int)(backgroundHeight*heightPercentage));
    }

    private JPanel createOverlay(){

        JButton button = new JButton("Start");
        button.addActionListener(e -> this.setVisible(false));
        button.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton button2 = new JButton("Exit");
        button.addActionListener(e -> this.setVisible(false));
        button.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JPanel panel = new JPanel(new GridLayout(4,1));
        panel.add(button,0);
        panel.add(button2,1);
        return panel;
    }
}
