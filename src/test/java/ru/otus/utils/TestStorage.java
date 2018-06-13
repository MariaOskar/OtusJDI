package ru.otus.utils;

import java.util.HashMap;
import java.util.Map;

public class TestStorage {

    public static Map<String,Object> map = new HashMap<>();

    public static void put (String key, Object value){
        map.put(key,value);
    }

    public static Object get (String key){
        return map.get(key);
    }

    public static boolean contains(String key){
        return map.containsKey(key);
    }

    public static String getString(String key){
        return map.get(key).toString();
    }

    public static float getFloat(String key) { return Float.parseFloat(map.get(key).toString());}


}
