package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DentalActivity extends AppCompatActivity {
    CheckBox Yellow,Toothache,DryMouth,Bleeding,BadBreathe,Sores,Sensitive;

    String yellow=new String();String ache=new String();String  dry=new String();String bleed= new String();
    String bad=new String();String sore=new String();String sense=new String();

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Dental");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();
    String key = reference.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental);

        Yellow=findViewById(R.id.checkBoxYellow);
        Toothache=findViewById(R.id.checkBoxToothAche);
        DryMouth=findViewById(R.id.checkBoxDryMouth);
        Bleeding=findViewById(R.id.checkBoxBleed);
        BadBreathe=findViewById(R.id.checkBoxBadBreathe);
        Sores=findViewById(R.id.checkBoxSore);
        Sensitive=findViewById(R.id.checkBoxSensitive);

    }
    public void onCheckboxClicked(View view){
        boolean checked=((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkBoxYellow:
                if(checked){yellow=Yellow.getText().toString();}
                else {yellow="";}
                break;
            case R.id.checkBoxToothAche:
                if(checked){ache=Toothache.getText().toString();}
                else {ache="";}
                break;
            case R.id.checkBoxDryMouth:
                if(checked){dry=DryMouth.getText().toString();}
                else{dry="";}
                break;
            case R.id.checkBoxBleed:
                if(checked){bleed=Bleeding.getText().toString();}
                else{bleed="";}
                break;
            case R.id.checkBoxBadBreathe:
                if(checked){bad=BadBreathe.getText().toString();}
                else{bad="";}
                break;
            case R.id.checkBoxSore:
                if(checked){sore=Sores.getText().toString();}
                else {sore="";}
                break;
            case R.id.checkBoxSensitive:
                if(checked){sense=Sensitive.getText().toString();}
                else {sense="";}
                break;
        }
    }
    public void OnSubmitTapped(View view){

        if(yellow.isEmpty()&&bleed.isEmpty()&&sore.isEmpty()&&sense.isEmpty()&&ache.isEmpty()&&dry.isEmpty()&&bad.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Symptoms Noted",Toast.LENGTH_SHORT).show();
        }
        else {
            Dental dental=new Dental(yellow,ache,dry,bleed,bad,sore,sense,user.getUid());
            reference.child(key).setValue(dental);
            Toast.makeText(getApplicationContext(),"Symptoms Noted Successfully",Toast.LENGTH_SHORT).show();

        }
    }
    public void OnGetReportTapped(View view){
        startActivity(new Intent(this,DentalReportActivity.class));
        finish();

    }
}