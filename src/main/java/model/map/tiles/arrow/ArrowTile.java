package model.map.tiles.arrow;

import model.map.tiles.Tile;
import java.awt.*;
import java.util.ArrayList;

public class ArrowTile extends Tile {

    private int maxLength;
    private ArrayList<int[]> coordinates = new ArrayList();
    private ArrayList<ArrowSprite> arrow = new ArrayList<>();

    public ArrowTile(int maxLength) {
        super(new ArrowSprite());
        this.maxLength=maxLength;
    }

    public ArrayList<int[]> getPath() {
        return coordinates;
    }

    public void addPart(int i, int j){
        synchronized (lock){
            if(arrow.size()< maxLength){
                int[] curr = new int[]{i,j};
                coordinates.add(curr);
                setBody(curr);
                addHead(curr);
            }
        }
    }

    private void setBody(int[] next) {
        //just handling the edge cases where the size of the arrow is 0 and 1
        if(arrow.size() == 0 ){
            return ;
        }
        int[] curr = coordinates.get(arrow.size()-1);
        int[] prev;
        if(arrow.size() > 1 ){
            prev = coordinates.get(arrow.size()-2);
        }else {
            prev = new int[]{super.getI(),super.getJ()};
        }

        ArrowSprite previousSprite = arrow.get(arrow.size()-1);
        previousSprite.setBody(getEnter(prev,curr),getEnter(next,curr));
    }

    Object lock = new Object();
    public synchronized void removeLastPart(){
        synchronized (lock){
            if(!arrow.isEmpty()){
                arrow.remove(arrow.size()-1);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        synchronized (lock){//this is dangerous and can be the cause of problems
            if(!arrow.isEmpty()){
                this.getMap().ifPresent((map)-> {
                    for(int index =0; index<arrow.size(); index++){
                        int cor[] = map.getStrategy().toWorldCoordinates(coordinates.get(index)[0], coordinates.get(index)[1]);
                        graphics.drawImage(
                                arrow.get(index).getImage(),
                                cor[0],
                                cor[1],
                                arrow.get(index).getHeight(),
                                arrow.get(index).getWidth(),
                                null);
                    }
                });
            }
        }
    }


    //region Helper methods
    private void addHead(int[] curr) {
        int[] prev = new int[]{super.getI(),super.getJ()};
        //just handling the edge case
        if(arrow.size() > 0){
            prev = coordinates.get(arrow.size()-1);
        }
        ArrowSprite headSprite = new ArrowSprite();
        headSprite.setHead(getEnter(prev,curr));
        arrow.add(headSprite);
    }

    //returns enter for the curr
    // prev and curr are neighbouring hex tiles
    // draw an arrow from prev to curr
    // the side where the arrow enters curr is given by the function getEnter
    // de sides of the hextile are numbered from 0 to 5
    // 0 being the bottom left side and then counting clockwise
    private int getEnter(int[] prev, int[] curr){
        int i = 0;int j=1;

        //horizontally aligned tiles:
        if(curr[i]==prev[i]){
            if(curr[j]<prev[j]){
                return 4;
            }else{
                return 1;
            }
        }
        if(curr[i]<prev[i]){//0 and 5
            if(curr[j]< prev[j]){
                return 5;
            }
            if(curr[j]> prev[j]){
                return 0;
            }
            if(curr[i]%2==0){
                return 5;
            }else{
                return 0;
            }
        }
        if(curr[i]>prev[i]){//
            if(curr[j]< prev[j]){
                return 3;
            }
            if(curr[j]> prev[j]){
                return 2;
            }
            if(curr[i]%2==0){
                return 3;
            }else{
                return 2;
            }
        }
        return 0;
    }
    //endregion


}

