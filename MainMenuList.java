package com.gs.medicaldiagnosisexpertsystem;

public class MainMenuList {

    private int id,image;
    private String title,desc;

    public MainMenuList(int id, int image, String title, String desc) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
