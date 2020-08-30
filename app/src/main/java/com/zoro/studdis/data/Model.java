package com.zoro.studdis.data;

import java.util.HashMap;

public class Model {

    private int id;

    public Model(int id) {
        this.id = id;
    }

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return map;
    }

    public static Model fromMap(HashMap<String, Object> map) {
        return new Model(
                ((int) map.get("id"))
        );
    }

}
