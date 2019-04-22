package com.example.writery;

import java.util.ArrayList;

public class WriteItem {
    String title;
    String info;
    int image;
    ArrayList<EpisodeItem> episodeItem;

    public WriteItem(String title, String info, int image, ArrayList<EpisodeItem> episodeItem) {
        this.title = title;
        this.info = info;
        this.image = image;
        this.episodeItem = episodeItem;
    }

    public WriteItem(String title, String info, int image){
        this.title = title;
        this.info = info;
        this.image = image;
    }

}