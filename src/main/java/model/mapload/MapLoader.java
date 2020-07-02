package model.mapload;

import model.Map;
import model.Tile;
import model.sprites.Register;
import model.sprites.Sprite;
import model.sprites.SpriteSheet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Base64;

public class MapLoader {

    private JSONParser parser = new JSONParser();

    public Map LoadMap(String fileName) {
        try{

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            JSONObject jsonObject = null;
            try(InputStream is = classLoader.getResourceAsStream(fileName);
                InputStreamReader reader = new InputStreamReader(is);
            ){
                jsonObject = (JSONObject) parser.parse(reader);
            }


            if(jsonObject != null){
                //get the layers
                int columns = Integer.parseInt(jsonObject.get("width").toString()) ;
                int rows = Integer.parseInt(jsonObject.get("height").toString()) ;
                JSONArray layers = (JSONArray) jsonObject.get("layers");
                //create the map
                Map map = new Map(layers.size(), rows, columns );

                // loop over the layers and add them to the map
                for(int layer =0;layer< layers.size();layer++){
                    int[] values = deCode( (String)((JSONObject)layers.get(layer)).get("data") );
                    // each value corresponds to a tile
                    // but what with different spritesheets?
                    for(int index = 0; index < values.length; index++){
                        Tile tile = createTile(values[index]);
                        if( tile != null){
                            map.addTile(layer,index /columns,index%columns ,tile);
                        }
                    }
                }
                return map;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Register register = new Register();
    private Tile createTile(int tileId) {
        if(tileId == 0) return null;
        Sprite sprite = register.getSprite("first", tileId-1);
        return new Tile(sprite);
    }


    /*
     * used to decode the json file
     */
    public int[] deCode(String data){
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        int[] bytes32 = new int[decodedBytes.length/4];
        for (int i = 0; i < decodedBytes.length/4; i++) {
            int value = 0;
            for(int j =3;j>=0;j-- ){
                value = (value*8 + decodedBytes[(4*i+j)]);
            }
            bytes32[i] = value;
        }
        return bytes32;
    }
}