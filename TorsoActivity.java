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

public class TorsoActivity extends AppCompatActivity {
    CheckBox DullStomach,Burn,Burp,Nausea,HeartBeat,Weakness,Sweating,WeightLoss,AbdominalPain,Dia,MusclePain,Breathless;
    String dS=new String(); String b=new String();String bp=new String();
    String nau=new String();String hB=new String();String w=new String();
    String s=new String();String wL=new String();String aP=new String();
    String d=new String();String mP=new String();String bL=new String();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Torso Region");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();
    String key = reference.push().getKey();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torso);
        DullStomach=findViewById(R.id.checkBoxPainStomach);
        Burn=findViewById(R.id.checkBoxBurn);
        Burp=findViewById(R.id.checkBoxBurp);
        Nausea=findViewById(R.id.checkBoxNausea);
        HeartBeat=findViewById(R.id.checkBoxHeartBeat);
        Weakness=findViewById(R.id.checkBoxWeakness);
        Sweating=findViewById(R.id.checkBoxSweat);
        WeightLoss=findViewById(R.id.checkBoxAppetite);AbdominalPain=findViewById(R.id.checkBoxAbdomen);
        Dia=findViewById(R.id.checkBoxDiarrhea);
        MusclePain=findViewById(R.id.checkBoxMuscles);
        Breathless=findViewById(R.id.checkBoxBreathe);

    }
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBoxPainStomach:
                if(checked){
                    dS=DullStomach.getText().toString();
                }
                else{dS="";
                }
                break;
            case R.id.checkBoxBurn:
                if(checked){
                    b=Burn.getText().toString();
                }
                else{b="";}
                break;
            case R.id.checkBoxBurp:
                if(checked){
                    bp=Burp.getText().toString();
                }
                else{bp="";}
                break;
            case R.id.checkBoxNausea:
                if(checked){
                    nau=Nausea.getText().toString();}
                else{nau="";}
                break;
            case R.id.checkBoxHeartBeat:
                if(checked){hB=HeartBeat.getText().toString();}
                else{hB="";}
                break;
            case R.id.checkBoxWeakness:
                if(checked){w=Weakness.getText().toString();}
                else{w="";}
                break;
            case R.id.checkBoxDiarrhea:
                if(checked){d=Dia.getText().toString();}
                else{d="";}
                break;
            case R.id.checkBoxMuscles:
                if(checked){mP=MusclePain.getText().toString();}
                else{mP="";}
                break;
            case R.id.checkBoxBreathe:
                if(checked){bL=Breathless.getText().toString();}
                else{bL="";}
            case R.id.checkBoxSweat:
                if(checked){s=Sweating.getTextColors().toString();}
                else{s="";}
                break;
            case R.id.checkBoxAppetite:
                if(checked){wL=WeightLoss.getText().toString();}
                else {wL="";}
                break;
            case R.id.checkBoxAbdomen:
                if(checked){aP=AbdominalPain.getText().toString();}
                else{aP="";}
                break;
        }


    }
    public void OnTap(View view){
        if(dS.isEmpty()&&b.isEmpty()&&bp.isEmpty()&&nau.isEmpty()&&hB.isEmpty()&&w.isEmpty()&&d.isEmpty()&&mP.isEmpty()&&bL.isEmpty()&&s.isEmpty()&&wL.isEmpty()&&aP.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Symptoms Noted",Toast.LENGTH_SHORT).show();
        }
        else{
            Torso torso=new Torso(dS,b,bp,nau,hB,w,d,mP,bL,s,wL,aP,user.getUid());
            reference.child(key).setValue(torso);
            Toast.makeText(getApplicationContext(),"Symptoms Noted Successfully",Toast.LENGTH_SHORT).show();
        }
    }
    public void OnGetReportTapped(View view){
        startActivity(new Intent(this,TorsoReportActivity.class));
        finish();

    }
}