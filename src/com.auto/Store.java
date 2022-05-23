package com.auto;

import java.util.ArrayList;

public class Store implements java.io.Serializable {

    public ArrayList<CarUserRelation> list = new ArrayList<>();

    public void add(CarUserRelation carUserRelation) {
        this.list.add(carUserRelation);
    }
}
