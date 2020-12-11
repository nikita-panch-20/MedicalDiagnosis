package com.gs.medicaldiagnosisexpertsystem;

public class Skin {
    String pimple,rash,itch,swell,shingle,sunBurn,skinPeel,skinRing,pricky,allergy,uid;
    public Skin(){

    }

    public Skin(String pimple, String rash, String itch, String swell, String shingle, String sunBurn, String skinPeel, String skinRing, String pricky, String allergy, String uid) {
        this.pimple = pimple;
        this.rash = rash;
        this.itch = itch;
        this.swell = swell;
        this.shingle = shingle;
        this.sunBurn = sunBurn;
        this.skinPeel = skinPeel;
        this.skinRing = skinRing;
        this.pricky = pricky;
        this.allergy = allergy;
        this.uid = uid;
    }

    public String getPimple() {
        return pimple;
    }

    public void setPimple(String pimple) {
        this.pimple = pimple;
    }

    public String getRash() {
        return rash;
    }

    public void setRash(String rash) {
        this.rash = rash;
    }

    public String getItch() {
        return itch;
    }

    public void setItch(String itch) {
        this.itch = itch;
    }

    public String getSwell() {
        return swell;
    }

    public void setSwell(String swell) {
        this.swell = swell;
    }

    public String getShingle() {
        return shingle;
    }

    public void setShingle(String shingle) {
        this.shingle = shingle;
    }

    public String getSunBurn() {
        return sunBurn;
    }

    public void setSunBurn(String sunBurn) {
        this.sunBurn = sunBurn;
    }

    public String getSkinPeel() {
        return skinPeel;
    }

    public void setSkinPeel(String skinPeel) {
        this.skinPeel = skinPeel;
    }

    public String getSkinRing() {
        return skinRing;
    }

    public void setSkinRing(String skinRing) {
        this.skinRing = skinRing;
    }

    public String getPricky() {
        return pricky;
    }

    public void setPricky(String pricky) {
        this.pricky = pricky;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
