package com.example.writery;

public class EpisodeItem {
    private int ID;
    private int code;
    private String episodeTitle;
    private String contents;

    public EpisodeItem(int code, String episodeTitle, String contents) {
        this.code = code;
        this.episodeTitle = episodeTitle;
        this.contents = contents;
    }

    public EpisodeItem(int code) {
        this.code = code;
    }

    public EpisodeItem(){
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


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}