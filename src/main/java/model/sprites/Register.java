package model.sprites;

import java.util.HashMap;

public class Register {

    private static Register INSTANCE = new Register();

    public static Register getInstance(){
        return INSTANCE;
    }

    private HashMap<String, SpriteSheet> sheets;

    private Register() {
        this.sheets =  new HashMap<>();
    }

    public void registerSheet(String name, String path, int rows, int columns){
        this.sheets.put(name, new SpriteSheet(path,rows,columns ));
    }

    SpriteSheet getSpriteSheet(String sheetName){
        return this.sheets.get(sheetName);
    }

    /*public Sprite getSprite(String sheetName, int id){
        return this.sheets.get(sheetName).getSprite(id);
    }*/


}
