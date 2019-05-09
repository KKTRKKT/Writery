package com.example.writery;

public class NobelItem {
    private int ID;
    private byte[] image;
    private String title;
    private String info;

    public NobelItem(byte[] image, String title, String info) {
        this.image = image;
        this.title = title;
        this.info = info;
    }

    public NobelItem() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitles() {
        return title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

