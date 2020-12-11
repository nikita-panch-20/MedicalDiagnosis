package com.gs.medicaldiagnosisexpertsystem;

public class GeneralSymptoms {
    String highTemp,normTemp,runNose,breatheNose,dryCough,wetCough,whoopCough,severeAche,mildAche,HeadAche,Cold,Cough,uid;

    public GeneralSymptoms() {

    }

    public GeneralSymptoms(String highTemp, String normTemp, String runNose, String breatheNose, String dryCough, String wetCough, String whoopCough, String severeAche, String mildAche, String headAche, String cold, String cough, String uid) {
        this.highTemp = highTemp;
        this.normTemp = normTemp;
        this.runNose = runNose;
        this.breatheNose = breatheNose;
        this.dryCough = dryCough;
        this.wetCough = wetCough;
        this.whoopCough = whoopCough;
        this.severeAche = severeAche;
        this.mildAche = mildAche;
        HeadAche = headAche;
        Cold = cold;
        Cough = cough;
        this.uid = uid;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getNormTemp() {
        return normTemp;
    }

    public void setNormTemp(String normTemp) {
        this.normTemp = normTemp;
    }

    public String getRunNose() {
        return runNose;
    }

    public void setRunNose(String runNose) {
        this.runNose = runNose;
    }

    public String getBreatheNose() {
        return breatheNose;
    }

    public void setBreatheNose(String breatheNose) {
        this.breatheNose = breatheNose;
    }

    public String getDryCough() {
        return dryCough;
    }

    public void setDryCough(String dryCough) {
        this.dryCough = dryCough;
    }

    public String getWetCough() {
        return wetCough;
    }

    public void setWetCough(String wetCough) {
        this.wetCough = wetCough;
    }

    public String getWhoopCough() {
        return whoopCough;
    }

    public void setWhoopCough(String whoopCough) {
        this.whoopCough = whoopCough;
    }

    public String getSevereAche() {
        return severeAche;
    }

    public void setSevereAche(String severeAche) {
        this.severeAche = severeAche;
    }

    public String getMildAche() {
        return mildAche;
    }

    public void setMildAche(String mildAche) {
        this.mildAche = mildAche;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHeadAche() {
        return HeadAche;
    }

    public void setHeadAche(String headAche) {
        HeadAche = headAche;
    }

    public String getCold() {
        return Cold;
    }

    public void setCold(String cold) {
        Cold = cold;
    }

    public String getCough() {
        return Cough;
    }

    public void setCough(String cough) {
        Cough = cough;
    }

}
