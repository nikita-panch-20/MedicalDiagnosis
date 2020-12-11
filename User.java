package com.gs.medicaldiagnosisexpertsystem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class User {


    String Fullname;
    String Mobile;
    String Email;
    String Pass;
    String a;
    String w;
    String h;
    String g;


    public User(){

    }

    public User(String fullname, String mobile, String email, String pass, String a, String w, String h, String g) {
        Fullname = fullname;
        Mobile = mobile;
        Email = email;
        Pass = pass;
        this.a = a;
        this.w = w;
        this.h = h;
        this.g = g;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }



    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }
}
