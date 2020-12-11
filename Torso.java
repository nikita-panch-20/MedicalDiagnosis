package com.gs.medicaldiagnosisexpertsystem;

public class Torso {
    String dull,burn,burp,nausea,beat,weak,dia,muscle,breathe,sweat,loss,abdomen,uid;
    public Torso(){

    }

    public Torso(String dull, String burn, String burp, String nausea, String beat, String weak, String dia, String muscle, String breathe, String sweat, String loss, String abdomen, String uid) {
        this.dull = dull;
        this.burn = burn;
        this.burp = burp;
        this.nausea = nausea;
        this.beat = beat;
        this.weak = weak;
        this.dia = dia;
        this.muscle = muscle;
        this.breathe = breathe;
        this.sweat = sweat;
        this.loss = loss;
        this.abdomen = abdomen;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDull() {
        return dull;
    }

    public void setDull(String dull) {
        this.dull = dull;
    }

    public String getBurn() {
        return burn;
    }

    public void setBurn(String burn) {
        this.burn = burn;
    }

    public String getBurp() {
        return burp;
    }

    public void setBurp(String burp) {
        this.burp = burp;
    }

    public String getNausea() {
        return nausea;
    }

    public void setNausea(String nausea) {
        this.nausea = nausea;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public String getWeak() {
        return weak;
    }

    public void setWeak(String weak) {
        this.weak = weak;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe;
    }

    public String getSweat() {
        return sweat;
    }

    public void setSweat(String sweat) {
        this.sweat = sweat;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }
}
