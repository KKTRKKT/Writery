package com.example.writery;

import java.util.ArrayList;

public class nobelItem {
    private int image;
    ArrayList<WriteItem> writeItem;

    public nobelItem(int image, ArrayList<WriteItem> writeItem){
        this.image = image;
        this.writeItem = writeItem;
    }

    public nobelItem(){
        this.image = R.drawable.front;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
