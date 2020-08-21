package model.sprites;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Supplier;

public class AnimatedSprite extends Sprite {

    // toggle to turn off/on animation
    private boolean animate = false;
    // change the value to change the speed of the animation
    private int animationSpeed = 10;



    private Supplier<Image>[] currentAnimation;
    private int animationIndex = 0;

    public AnimatedSprite(String sheetName, int defaultSprite) {
        super(sheetName,defaultSprite);
    }


    private int speedCounter = 0;
    @Override
    public Image getImage() {
        if(currentAnimation != null && animate){
            //increase speedCounter
            this.speedCounter = (this.speedCounter + 1)% this.animationSpeed;
            //increase animationIndex if needed
            if(speedCounter == 0) this.animationIndex = (this.animationIndex +1) % this.currentAnimation.length;
            //return the image
            return currentAnimation[animationIndex].get();
        }else{
            // otherwise return the default image
            return super.getImage();
        }
    }

    public void continueAnimation(){
        animate = true;
    }

    public void stopAnimation(){
        animate = false;
        speedCounter = 0;
    }

    //todo: make synchronized??
    public void loadAnimation(String animationName){
        this.currentAnimation = animations.get(animationName);
        this.animate = true;
    }

    private HashMap<String, Supplier<Image>[]> animations = new HashMap<>();
    public void addAnimation(String name, int ... animations) {
        // match the integers to an image Supplier
        Supplier<Image>[] a = new Supplier[animations.length];
        for(int i= 0 ;i< animations.length;i++){
            a[i] = this.getSpriteSheet().getImageSupplier(animations[i]);
        }
        //store the image Suppliers
        this.animations.put(name, a);
    }

}
