package com.example.writery;

public class WriteItem {
    private int ID;
    private String title;
    private String episodeTitle;
    private String contents;

    public WriteItem(int ID, String title, String episodeTitle, String contents) {
        this.ID = ID;
        this.title = title;
        this.episodeTitle = episodeTitle;
        this.contents = contents;
    }

    public WriteItem(){
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}