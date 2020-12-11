package com.gs.medicaldiagnosisexpertsystem;

public class Dental {
    String yellow,ache,dry,bleed,breathe,sore,sensitive,uid;
    public Dental(){

    }

    public Dental(String yellow, String ache, String dry, String bleed, String breathe, String sore, String sensitive, String uid) {
        this.yellow = yellow;
        this.ache = ache;
        this.dry = dry;
        this.bleed = bleed;
        this.breathe = breathe;
        this.sore = sore;
        this.sensitive = sensitive;
        this.uid = uid;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getAche() {
        return ache;
    }

    public void setAche(String ache) {
        this.ache = ache;
    }

    public String getDry() {
        return dry;
    }

    public void setDry(String dry) {
        this.dry = dry;
    }

    public String getBleed() {
        return bleed;
    }

    public void setBleed(String bleed) {
        this.bleed = bleed;
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe;
    }

    public String getSore() {
        return sore;
    }

    public void setSore(String sore) {
        this.sore = sore;
    }

    public String getSensitive() {
        return sensitive;
    }

    public void setSensitive(String sensitive) {
        this.sensitive = sensitive;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
