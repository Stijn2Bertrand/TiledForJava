package model.mapload;

import model.draw.Map;

import java.util.HashMap;
import java.util.function.Supplier;

public class MapRegister {

    private static MapRegister register = new MapRegister();

    public static MapRegister getInstance() {
        return register;
    }

    private HashMap<String, Supplier<Map>> hashMap;
    private MapLoader loader = new MapLoader();

    private MapRegister(){
        this.hashMap = new HashMap<>();
    }

    public void registerMap(String name, String path){
        // using a supplier here means the map is loaded when it is needed
        this.hashMap.put(name, ()-> loader.LoadMap(path));
    }

    public Map getMap(String name){
        return hashMap.get(name).get();
    }
}
