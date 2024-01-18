package com.example.demo.Logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1, new User("kirill", "lab", 234320.0));
        model.put(2, new User("Andrei", "Ivanov", 212320.0));
    }

    public void add(User user, int id){
        model.put(id,user);
    }


    public Map<Integer, User> getFromList(){
        return model;
    }
}
