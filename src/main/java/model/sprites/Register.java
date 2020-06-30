package model.sprites;

import java.util.HashMap;

public class Register {




    private HashMap<String, SpriteSheet> sheets;

    public Register() {
        this.sheets =  new HashMap<>();

        this.sheets.put("first", new SpriteSheet("/64sprites3.png",8,8 ));

    }

    public Sprite getSprite(String sheetName, int id){
        return this.sheets.get(sheetName).getSprite(id);
    }


}
