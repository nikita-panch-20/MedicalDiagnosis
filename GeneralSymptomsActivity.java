package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GeneralSymptomsActivity extends AppCompatActivity {
    CheckBox headAche,cold, cough,tempNormal,tempHigh,coldRunning,coldBreathing,coughDry,coughWet,coughWhooping,headAcheSevere,headAcheMild;
    EditText HeadAche,Cold, Cough;
    String ache=new String();String cold1=new String();String cough1=new String();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = database.getReference("Symptoms").child("General Tiredness");
    FirebaseAuth auth;
    FirebaseUser user;
    String key = reference.push().getKey();
    String normalTemp=new String();String highTemp= new String();String coldRun=new String();
   String coldBreathe=new String();String dryCough=new String();String wetCough=new String();
   String whoopCough=new String();String severeAche=new String();String mildAche=new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_symptoms);
        headAche = findViewById(R.id.checkBoxEyeSight);
        cold = findViewById(R.id.checkBoxColdAllergy);
        cough = findViewById(R.id.checkBoxCoughAllergy);
        tempNormal=findViewById(R.id.checkBoxNormal);
        tempHigh=findViewById(R.id.checkBoxHigh);
        coldRunning=findViewById(R.id.checkBoxRunningNose);
        coldBreathing=findViewById(R.id.checkBoxBreathing);
        coughDry=findViewById(R.id.checkBoxDryCough);
        coughWet=findViewById(R.id.checkBoxWetCough);
        coughWhooping=findViewById(R.id.checkBoxWhoopingCough);
        headAcheMild=findViewById(R.id.checkBoxMildHeadAche);
        headAcheSevere=findViewById(R.id.checkBoxSevereHeadAche);

        HeadAche = findViewById(R.id.editTextheadAche);
        Cold = findViewById(R.id.editTextCold);
        Cough = findViewById(R.id.editTextCough);



        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxEyeSight:
                if (checked){
                    if (!TextUtils.isEmpty(ache)) {
                        ache = HeadAche.getText().toString();
                    }

                    if (ache.isEmpty()) {
                        HeadAche.setError("This Field cannot be empty");
                        HeadAche.requestFocus();
                        return;
                    }


                }

                else{
                    ache="";
                }

                break;
            case R.id.checkBoxColdAllergy:
                if (checked){
                    if (cold1.isEmpty()) {
                        Cold.setError("This Field cannot be empty");
                        Cold.requestFocus();
                        return;
                    }
                    if (!TextUtils.isEmpty(cold1)) {
                        cold1 = Cold.getText().toString();
                    }


                }

                else{
                    cold1="";
                }

                break;
            case R.id.checkBoxCoughAllergy:
                if (checked){
                    if (!TextUtils.isEmpty(cough1)) {
                        cough1 = Cough.getText().toString();
                    }
                    if (cough1.isEmpty()) {
                        Cough.setError("This Field cannot be empty");
                        Cough.requestFocus();
                        return;
                    }

                }

                else{
                    cough1="";
                }

                break;
            case R.id.checkBoxNormal:
                if (checked){
                    normalTemp=tempNormal.getText().toString();
                }

                else{
                    normalTemp="";
                }

                    break;
            case R.id.checkBoxHigh:
                if (checked){
                    highTemp=tempHigh.getText().toString();
                }

                else{
                    highTemp="";
                }

                    break;
            case R.id.checkBoxRunningNose:
                if (checked){
                    coldRun=coldRunning.getText().toString();
                }

                else{
                    coldRun="";
                }

                    break;
            case R.id.checkBoxBreathing:
                if (checked){
                    coldBreathe=coldBreathing.getText().toString();
                }

                else{
                    coldBreathe="";
                }

                    break;
            case R.id.checkBoxWetCough:
                if (checked){
                    wetCough=coughWet.getText().toString();
                }

                else{
                    wetCough="";
                }

                    break;
            case R.id.checkBoxDryCough:
                if (checked)
                    dryCough=coughDry.getText().toString();

                else{
                    dryCough="";
                }

                    break;
            case R.id.checkBoxWhoopingCough:
                if (checked){
                    whoopCough=coughWhooping.getText().toString();
                }

                else{
                    whoopCough="";
                }

                    break;
            case R.id.checkBoxMildHeadAche:
                if (checked){
                    mildAche=headAcheMild.getText().toString();
                }

                else{
                    mildAche="";
                }
                                    break;
            case R.id.checkBoxSevereHeadAche:
                if (checked){
                    severeAche=headAcheSevere.getText().toString();
                }

                else{
                    severeAche="";
                }

                    break;
        }
    }

    public void OnSubmitTapped(View view) {

        ache=HeadAche.getText().toString();
        cold1=Cold.getText().toString();
        cough1=Cough.getText().toString();
        System.out.println("when submitted "+ache);

        if(ache.equals("")&&cold1.equals("")&&cough1.equals("")&&highTemp.equals("")&&normalTemp.equals("")&&coldRun.equals("")&&coldBreathe.equals("")&&dryCough.equals("")&&wetCough.equals("")&&whoopCough.equals("")&&severeAche.equals("")&&mildAche.equals("")){

            Toast.makeText(getApplicationContext(), "No Symptoms Noted", Toast.LENGTH_SHORT).show();
        }
        else {
            GeneralSymptoms generalSymptoms = new GeneralSymptoms(highTemp, normalTemp, coldRun,coldBreathe, dryCough, wetCough, whoopCough, severeAche, mildAche, ache, cold1, cough1, user.getUid());
            reference.child(key).setValue(generalSymptoms);
            Toast.makeText(getApplicationContext(), "Symptoms Noted Successfully", Toast.LENGTH_SHORT).show();
        }

    }
    public void OnGetReportTapped(View view){
        startActivity(new Intent(this,GeneralSymptomsReportActivity.class));
        finish();

    }
}