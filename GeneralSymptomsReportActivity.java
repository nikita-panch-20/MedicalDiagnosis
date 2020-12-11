package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GeneralSymptomsReportActivity extends AppCompatActivity {

    TextView normTemp,hiTemp,runNose,breathe,coughDry,coughWet,coughWhoop,acheSevere,acheMild,Sight,Cold,Cough,Disease;
    String nT=new String();String hT= new String();String cR=new String();
    String cB=new String();String dC=new String();String wC=new String();
    String whC=new String();String sA=new String();String mA=new String();
    String e=new String();String c=new String();String co=new String();

    String disease=new String();
    //String normalTemp,highTemp,coldRun,coldBreathe,dryCough,wetCough,whoopCough,severeAche,mildAche,eyeSight,cold,cough;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("General Tiredness");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_symptoms_report);
        normTemp=findViewById(R.id.textViewNormalTemp);
        hiTemp=findViewById(R.id.textViewHighTemp);
        runNose=findViewById(R.id.textViewRunNose);
        breathe=findViewById(R.id.textViewPhlegm);
        coughDry=findViewById(R.id.textViewDryCough);
        coughWet=findViewById(R.id.textViewWetCough);
        coughWhoop=findViewById(R.id.textViewWhoopCough);
        acheSevere=findViewById(R.id.textViewSevereAche);
        acheMild=findViewById(R.id.textViewMildAche);
        Sight=findViewById(R.id.textViewEyeSight);
        Cold=findViewById(R.id.textViewNoseAllergy);
        Cough=findViewById(R.id.textViewThroatAllergy);
        Disease=findViewById(R.id.textViewDisease);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    GeneralSymptoms generalSymptoms = data.getValue(GeneralSymptoms.class);
                    String uid=generalSymptoms.getUid();

                    if (uid.equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                        System.out.println("inside ref"+generalSymptoms.getNormTemp());
                        nT=generalSymptoms.getNormTemp();
                        hT=generalSymptoms.getHighTemp();
                        cR=generalSymptoms.getRunNose();
                        cB=generalSymptoms.getBreatheNose();
                        dC=generalSymptoms.getDryCough();
                        wC=generalSymptoms.getWetCough();
                        whC=generalSymptoms.getWhoopCough();
                        sA=generalSymptoms.getSevereAche();
                        mA=generalSymptoms.getMildAche();
                        e=generalSymptoms.getHeadAche();
                        c=generalSymptoms.getCold();
                        co=generalSymptoms.getCough();

                      if(nT!=null && !nT.isEmpty()) {

                          normTemp.setText("Yes");

                      }else{
                          normTemp.setText("No");
                      }


                        if (hT !=null && !hT.isEmpty()) {

                            hiTemp.setText("Yes");

                        }
                        else {
                            hiTemp.setText("No");
                        }
                        if (cR !=null && !cR.isEmpty())
                        {

                            runNose.setText("Yes");

                        }
                        else {
                            runNose.setText("No");
                        }
                        if (cB !=null && !cB.isEmpty())
                        {
                            breathe.setText("Yes");

                        }else {breathe.setText("No");}
                        if (dC !=null && !dC.isEmpty())
                        {
                                coughDry.setText("Yes");
                            }else {coughDry.setText("No");}

                        }
                        if (wC !=null && !wC.isEmpty())
                        {

                                coughWet.setText("Yes");

                        }else { coughWet.setText("No");}

                        if (whC !=null && !whC.isEmpty())
                        {

                                coughWhoop.setText("Yes");

                        }else {
                            coughWhoop.setText("No");
                        }
                        if (sA !=null && !sA.isEmpty())
                        {

                                acheSevere.setText("Yes");

                        }else { acheSevere.setText("No");}
                        if (mA !=null && !mA.isEmpty())
                        {

                                acheMild.setText("Yes");

                        }else { acheMild.setText("No");}
                        if (e !=null && !e.isEmpty())
                        {

                                Sight.setText(e);

                        }else { Sight.setText("NIL");}
                        if (c !=null && !c.isEmpty())
                        {
                                Cold.setText(c);

                        }else { Cold.setText("NIL");}
                        if (co !=null && !co.isEmpty())
                        {

                                Cough.setText(co);
                        }else { Cough.setText("NIL");}



                    if ((hT !=null && !hT.isEmpty())||(nT!=null && !nT.isEmpty()) || ((sA !=null && !sA.isEmpty()) && (cR !=null && !cR.isEmpty())) || ((mA !=null && !mA.isEmpty() || (whC !=null && !whC.isEmpty())) && (cR !=null && !cR.isEmpty())) || ((wC !=null && !wC.isEmpty()) && (cR !=null && !cR.isEmpty())) || (whC !=null && !whC.isEmpty()) ) {
                       if ((whC !=null && !whC.isEmpty()) && (sA !=null && !sA.isEmpty()) && (hT !=null && !hT.isEmpty())) {
                            Disease.setText("Predicted Dengue Symptoms \n Treatment :Immediately, Platelet Count tests is required and consult a general physician");
                        }
                        else if (((hT !=null && !hT.isEmpty()) && (whC !=null && !whC.isEmpty()))) {
                            Disease.setText("General Weakness \n Healthy Diet Plan is required.");
                        }

                        else if(whC !=null && !whC.isEmpty()){Disease.setText("General Weakness \n Healthy Diet Plan is required.");}


                       else if((hT !=null && !hT.isEmpty())||((sA !=null && !sA.isEmpty()) && (mA !=null && !mA.isEmpty()) && (hT !=null && !hT.isEmpty()) && (cR !=null && !cR.isEmpty()))||(hT !=null && !hT.isEmpty()) ||((hT !=null && !hT.isEmpty()) && (cB !=null && !cB.isEmpty()))||((hT !=null && !hT.isEmpty()) && (cR !=null && !cR.isEmpty()) && (wC !=null && !wC.isEmpty()))||((cR !=null && !cR.isEmpty()) && (hT !=null && !hT.isEmpty())||(mA !=null && !mA.isEmpty()) &&(hT !=null && !hT.isEmpty()) && (cR !=null && !cR.isEmpty()))||((sA !=null && !sA.isEmpty()) && (hT !=null && !hT.isEmpty()) && (cR !=null && !cR.isEmpty()))) {
                            Disease.setText(" Predicted Viral Fever Symptoms: \n Treatment: Have normal meals, Avoid having cold things. Consult a physician immediately");
                        }

                        else{
                            Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");

                        }
                    }
                    else if ((sA !=null && !sA.isEmpty())|| (mA !=null && !mA.isEmpty())||(cB !=null && !cB.isEmpty()) || (cR !=null && !cR.isEmpty()) ||(wC !=null && !wC.isEmpty()) || (e !=null && !e.isEmpty()) || (c !=null && !c.isEmpty()) || (dC !=null && !dC.isEmpty()) || (co !=null && !co.isEmpty())) {
                        if(((sA !=null && !sA.isEmpty()) && (cB !=null && !cB.isEmpty()))||((mA !=null && !mA.isEmpty()) && (cB !=null && !cB.isEmpty()))){
                            Disease.setText("Predicted Sinusitis Symptoms \n Consult a ENT specialist for medication.");
                        }
                        else if(cB !=null && !cB.isEmpty()){
                            Disease.setText("Infection :\n Treatment :Consult a physician for medication.");
                        }
                        else if (((cB !=null && !cB.isEmpty()) && (cR !=null && !cR.isEmpty()))) {
                            Disease.setText("Infection :\n Treatment :Consult a physician for medication.");
                        }
                        else if((wC !=null && !wC.isEmpty()) && (cB !=null && !cB.isEmpty())){
                            Disease.setText("Infection :\n Treatment :Consult a physician foe medication.");
                        }
                        else if ((e !=null && !e.isEmpty())) {
                            Disease.setText("Treatment : Consult a opthalmologist and Continuous monitoring nearsightedness or farsightedness");
                        }
                        else if((e !=null && !e.isEmpty()) && (sA !=null && !sA.isEmpty())){
                            Disease.setText("Treatment : Consult a opthalmologist and Continuous monitoring nearsightedness or farsightedness");
                        }
                        else if((e !=null && !e.isEmpty()) && (mA !=null && !mA.isEmpty())){
                            Disease.setText("Treatment : Consult a opthalmologist and Continuous monitoring nearsightedness or farsightedness");
                        }
                        else if (((cR !=null && !cR.isEmpty()) && (c !=null && !c.isEmpty()) && (dC !=null && !dC.isEmpty()) && (co !=null && !co.isEmpty()))) {
                            Disease.setText("Allergic Conditions:\n Consult a family physician.");
                        }
                        else if((dC !=null && !dC.isEmpty()) || (c !=null && !c.isEmpty()) || (co !=null && !co.isEmpty())){
                            Disease.setText("Allergic Conditions:\n Consult a family physician.");
                        }
                        else if((dC !=null && !dC.isEmpty()) && (co !=null && !co.isEmpty())){
                            Disease.setText("Allergic Conditions:\n Consult a family physician.");
                        }
                        else if((cR !=null && !cR.isEmpty()) && (c !=null && !c.isEmpty())){
                            Disease.setText("Allergic Conditions:\n Consult a family physician.");
                        }
                        else if((c !=null && !c.isEmpty()) && (dC !=null && !dC.isEmpty())&& ((co !=null && !co.isEmpty()) && (cR !=null && !cR.isEmpty()))){
                            Disease.setText("Allergic Conditions:\n Consult a family physician.");
                        }
                        else{
                        Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
                        }
                    }


                    else{
                        Disease.setText("Could not predict, consult a nearby doctor if case of emergency");
                    }

                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        System.out.println("outside ref" + nT);



    }

    private void loadReport() {





           /* if (hT.equals("")) {
                hiTemp.setText("No");
            } else {
                hiTemp.setText("Yes");
            }


            if (cR.equals("")) {
                runNose.setText("No");
            } else {
                runNose.setText("Yes");
            }

            if(cB.equals("")){
                breathe.setText("No");
            }else{
                breathe.setText("Yes");
            }


            if(dC.equals("")){
                coughDry.setText("No");
            }else{
                coughDry.setText("Yes");
            }



            if(wC.equals("")){
                coughWet.setText("No");
            }else{
                coughWet.setText("Yes");
            }




            if(whC.equals("")){
                coughWhoop.setText("No");
            }else{
                coughWhoop.setText("Yes");
            }


            if(sA.equals("")){
                acheSevere.setText("No");
            }else{
                acheSevere.setText("Yes");
            }


            if(mA.equals("")){
                acheMild.setText("No");
            }else{
                acheMild.setText("Yes");
            }


            if(e.isEmpty()){
                Sight.setText("NIL");

            }
            else {
                Sight.setText(e);
            }


            if(c.isEmpty()){
                Cold.setText("NIL");
            }
            else{
                Cold.setText(c);
            }


            if(co.isEmpty()){
                Cough.setText("NIL");
            }else{
                Cough.setText(co);
            }

        diseaseDiagnose();*/

    }


    private void diseaseDiagnose() {


            if (!nT.isEmpty() || !mA.isEmpty() || (!mA.isEmpty() && !cR.isEmpty()) || (!wC.isEmpty() && !cR.isEmpty())) {
                Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
            } else if (!cR.isEmpty()) {
                Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
            } else if (!sA.isEmpty()) {
                Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
            } else if ((!sA.isEmpty() && !cR.isEmpty())) {
                Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
            } else if (!wC.isEmpty()) {
                Disease.setText("Causes due to Seasonal Changes. Continuous Monitoring is required.");
            } else if ((!sA.isEmpty() && !mA.isEmpty() && !hT.isEmpty() && !cR.isEmpty()) || (!sA.isEmpty() && !hT.isEmpty() && !cR.isEmpty()) || (!mA.isEmpty() && !hT.isEmpty() && !cR.isEmpty()) || (!cR.isEmpty() && !hT.isEmpty()) || (!hT.isEmpty() && !cR.isEmpty() && !wC.isEmpty()) || (!hT.isEmpty() && !cR.isEmpty() && !cB.isEmpty()) || (!hT.isEmpty() && !cB.isEmpty())) {
                Disease.setText(" Predicted Viral Fever Symptoms: \n Treatment: Have normal meals,Avoid having cold things. Consult a physician immediately");
            } else if (!e.isEmpty() || (!e.isEmpty() && !mA.isEmpty()) || (!e.isEmpty() && !sA.isEmpty())) {
                Disease.setText("Treatment : Consult a opthalmologist and Continuous monitoring nearsightedness or farsightedness");
            } else if ((!whC.isEmpty() && !sA.isEmpty() && !hT.isEmpty())) {
                Disease.setText("Predicted Dengue Symptoms \n Treatment :Immediately, Platelet Count tsts is required and consult a general physician");
            } else if ((!cB.isEmpty() && !cR.isEmpty()) || (!wC.isEmpty() && !cB.isEmpty()) || !cB.isEmpty()) {
                Disease.setText("Infection :\n Treatment :Consult a physician foe medication.");
            } else if ((!cR.isEmpty() && !c.isEmpty() && !dC.isEmpty() && !co.isEmpty()) || (!c.isEmpty() && !dC.isEmpty()) && (!co.isEmpty() && !cR.isEmpty()) || (!cR.isEmpty() && c.isEmpty()) || (!dC.isEmpty() && !co.isEmpty()) || !dC.isEmpty() || !c.isEmpty() || !co.isEmpty()) {
                Disease.setText("Allergic Conditions:\n Consult a family physician.");
            } else if ((!sA.isEmpty() && !cB.isEmpty()) || (!mA.isEmpty() && !cB.isEmpty())) {
                Disease.setText("Predicted Sinusitis Symptoms \n Consult a ENT specialist for medication.");
            } else if ((!hT.isEmpty() && !whC.isEmpty()) || !hT.isEmpty() || !whC.isEmpty()) {
                Disease.setText("General Weakness \n Healthy Diet Plan is required.");
            } else {
                Disease.setText("Could not predict. Do consult a physician, in case of emergency.");
            }

    }


}