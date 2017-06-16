package com.whu.faithfish.pojo;

/**
 * Created by faithfish on 17-6-15.
 */

public class Fruit {

    private String fruit_name;
    private int img_id;

    public Fruit(String _name, int _img){
        this.fruit_name = _name;
        this.img_id = _img;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }
}
