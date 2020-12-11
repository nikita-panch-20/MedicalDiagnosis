package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntActivity extends AppCompatActivity {
    CheckBox EyeBurn,RedEye,Water,DryEye,eyeSight,Nasal,NoseBleed,NoseAllergy,ThroatPain,Voice,SoreThroat,DryThroat,ThroatAllergy,EarAche,HearLoss,EarRing,Balance,EarFull;
    EditText eye,nose,throat;
    String eyeText=new String();String noseText= new String();String throatText=new String();
    String eB=new String();String rE=new String();
             String w=new String();String dE=new String();String eS=new String();String n=new String();String nB=new String();
             String nA=new String();String tP=new String();String v=new String();String sT=new String();String dT=new String();
             String tA=new String();String eA=new String();String hL=new String();String eR=new String();String b=new String();String eF=new String();

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Ear-Eye-Nose-Throat");
    FirebaseAuth auth;
    FirebaseUser user;
    String key = reference.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent);
        EyeBurn=findViewById(R.id.checkBoxEyeBurn);
        RedEye=findViewById(R.id.checkBoxRedEye);
        Water=findViewById(R.id.checkBoxTearEye);
        DryEye=findViewById(R.id.checkBoxDryEye);
        eyeSight=findViewById(R.id.checkBoxSight);
        Nasal=findViewById(R.id.checkBoxNasalCongestion);
        NoseBleed=findViewById(R.id.checkBoxBleeding);
        NoseAllergy=findViewById(R.id.checkBoxNasalAllergy);
        ThroatPain=findViewById(R.id.checkBoxIrritation);
        Voice=findViewById(R.id.checkBoxVoice);
        SoreThroat=findViewById(R.id.checkBoxSoreThroat);
        DryThroat=findViewById(R.id.checkBoxDryness);
        ThroatAllergy=findViewById(R.id.checkBoxThroatAllergy);
        EarAche=findViewById(R.id.checkBoxEarAche);
        HearLoss=findViewById(R.id.checkBoxHearLoss);
        EarRing=findViewById(R.id.checkBoxRingEar);
        Balance=findViewById(R.id.checkBoxBalance);
        EarFull=findViewById(R.id.checkBoxEarFull);


        eye=findViewById(R.id.editTextEye);
        nose=findViewById(R.id.editTextNasal);
        throat=findViewById(R.id.editTextThroat);

        eyeText=eye.getText().toString();
        noseText=nose.getText().toString();
        throatText=throat.getText().toString();

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxEyeBurn:
                if (checked){
                    eB=EyeBurn.getText().toString();
                }
                // Put some meat on the sandwich
                else{
                    eB="";
                }
                // Remove the meat
                break;
            case R.id.checkBoxRedEye:
                if (checked){
                    rE=RedEye.getText().toString();

                }
                // Cheese me
                else{
                    rE="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxTearEye:
                if (checked){
                    w=Water.getText().toString();
                }
                // Cheese me
                else{
                    w="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxDryEye:
                if (checked){
                    dE=DryEye.getText().toString();
                }
                // Cheese me
                else{
                    dE="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxSight:
                if (checked){
                    if(!TextUtils.isEmpty(eyeText)){
                        eye.setError(null);
                        eye.requestFocus();
                        return;
                    }
                    if(eyeText.isEmpty()){
                        eye.setError("This field cannot be empty");
                        eye.requestFocus();
                    }
                    eyeText=eye.getText().toString();
                }
                // Cheese me
                else{
                    eyeText="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxNasalCongestion:
                if (checked){
                    n=Nasal.getText().toString();
                }
                // Cheese me
                else{
                    n="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxBleeding:
                if (checked){
                    nB=NoseBleed.getText().toString();
                }
                // Cheese me
                else{
                    nB="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxNasalAllergy:
                if (checked){
                    if(!TextUtils.isEmpty(noseText)){
                        nose.setError(null);
                        nose.requestFocus();
                        return;
                    }
                    if(noseText.isEmpty()){
                        nose.setError("This field cannot be empty");
                        nose.requestFocus();
                    }
                    noseText=nose.getText().toString();
                }
                // Cheese me
                else{
                    noseText="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxIrritation:
                if (checked)
                    tP=ThroatPain.getText().toString();
                    // Cheese me
                else{
                    tP="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxVoice:
                if (checked){
                    v=Voice.getText().toString();
                }
                // Cheese me
                else{
                    v="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxSoreThroat:
                if (checked){

                        sT=SoreThroat.getText().toString();
                }
                // Cheese me
                else{
                    sT="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxDryness:
                if (checked){
                    dT=DryThroat.getText().toString();
                }
                // Cheese me
                else{
                    dT="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxThroatAllergy:
                if (checked){
                    if(!TextUtils.isEmpty(throatText)){
                        throat.setError(null);
                        throat.requestFocus();
                        return;
                    }
                    if(throatText.isEmpty()){
                        throat.setError("This field cannot be empty");
                        throat.requestFocus();
                    }
                    throatText=throat.getText().toString();
                }
                // Cheese me
                else{
                    throatText="";
                }
                // I'm lactose intolerant
                break;       // TODO: Veggie sandwich
            case R.id.checkBoxEarAche:
                if (checked){
                    eA=EarAche.getText().toString();                }
                // Cheese me
                else{
                    eA="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxHearLoss:
                if (checked){
                    hL=HearLoss.getText().toString();
                }
                // Cheese me
                else{
                    hL ="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxRingEar:
                if (checked){
                    eR=EarRing.getText().toString();
                }
                // Cheese me
                else{
                    eR="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxBalance:
                if (checked){
                    b=Balance.getText().toString();
                }
                // Cheese me
                else{
                    b="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxEarFull:
                if (checked){
                    eF=EarFull.getText().toString();
                }
                // Cheese me
                else{
                    eF="";
                }
                // I'm lactose intolerant
                break;
        }

    }
    public void OnSubmit(View view){



        if(noseText.isEmpty()&&eyeText.isEmpty()&&throatText.isEmpty()&&eB.isEmpty()&&rE.isEmpty()&&w.isEmpty()&&dE.isEmpty()&&n.isEmpty()&&nB.isEmpty()&&tP.isEmpty()&&v.isEmpty()&&sT.isEmpty()&&dT.isEmpty()&&eA.isEmpty()&&hL.isEmpty()&&eR.isEmpty()&&b.isEmpty()&&eF.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Symptoms Noted",Toast.LENGTH_SHORT).show();
        }
        else {
            Ent ent=new Ent(eB, rE, w, dE, eyeText, n, nB, noseText, tP,v, sT, dT, throatText, eA, hL,eR,b,eF,user.getUid());
            reference.child(key).setValue(ent);
            Toast.makeText(getApplicationContext(),"Symptoms Noted Successfully",Toast.LENGTH_SHORT).show();

        }
    }
    public void OnGetReportTapped(View view){
        startActivity(new Intent(this,EntReportActivity.class));
        finish();

    }
}