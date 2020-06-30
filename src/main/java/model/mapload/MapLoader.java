package model.mapload;

import model.Map;
import model.sprites.SpriteSheet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class MapLoader {


    public Map LoadMap(String path) {
        //JSONParser jsonParser = new JSONParser();


        int layers=3,  rows=2,  columns =2;
        Map map = new Map(layers, rows, columns);

        // map.addTile(layer,i,j, tile);

        return map;
    }







}
