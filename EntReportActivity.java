package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EntReportActivity extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Ear-Eye-Nose-Throat");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();

    TextView EyeBurn,RedEye,Water,DryEye,eyeSight,NasalCong,NoseBleed,NoseAllergy,ThroatPain,Voice,SoreThroat,DryThroat,ThroatAllergy,EarAche,HearLoss,EarRing,Balance,EarFull;
    TextView Disease;
    String eyeText=new String();String noseText= new String();String throatText=new String();
    String eB=new String();String rE=new String();
    String w=new String();String dE=new String();String eS=new String();String n=new String();String nB=new String();
    String nA=new String();String tP=new String();String v=new String();String sT=new String();String dT=new String();
    String tA=new String();String eA=new String();String hL=new String();String eR=new String();String b=new String();String eF=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_report);
        EyeBurn=findViewById(R.id.textViewEyeBurn);
        RedEye=findViewById(R.id.textViewRedEye);
        Water=findViewById(R.id.textViewTearEye);
        DryEye=findViewById(R.id.textViewDryEye);
        eyeSight=findViewById(R.id.textViewEyeSight);
        NasalCong=findViewById(R.id.textViewNasalCong);
        NoseBleed=findViewById(R.id.textViewNoseBleed);
        NoseAllergy=findViewById(R.id.textViewNoseAllergy);
        ThroatPain=findViewById(R.id.textViewIrritation);
        Voice=findViewById(R.id.textViewVoice);
        SoreThroat=findViewById(R.id.textViewSoreThroat);
        DryThroat=findViewById(R.id.textViewDryThroat);
        ThroatAllergy=findViewById(R.id.textViewThroatAllergy);
        EarAche=findViewById(R.id.textViewEarAche);
        HearLoss=findViewById(R.id.textViewHearLoss);
        EarRing=findViewById(R.id.textViewRingEar);
        Balance=findViewById(R.id.textViewBalance);
        EarFull=findViewById(R.id.textViewEarFull);
        Disease=findViewById(R.id.textViewDisease);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Ent ent = data.getValue(Ent.class);

                    String uid=ent.getUid();
                    if (uid.equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                        System.out.println("$$$$$$$$$$"+ent.getEyeBurn());
                        eB=ent.getEyeBurn();
                        rE=ent.getRedEye();
                        w=ent.getWater();
                        dE=ent.getDryEye();
                        n=ent.getNasal();
                        nB=ent.getNoseBleed();
                        noseText=ent.getNoseAllergy();
                        tP=ent.getThroatPain();
                        v=ent.getVoice();
                        sT=ent.getSoreThroat();
                        dT=ent.getDryThroat();
                       throatText=ent.getThroatAllergy();
                       eA=ent.getEarAche();
                        hL=ent.getHearLoss();
                        eR=ent.getEarRing();
                        b=ent.getBalance();
                        eF=ent.getEarFull();
                        eyeText=ent.getEyeSight();





                        loadReport();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    private void loadReport() {
        if(eyeText.isEmpty()){
          eyeSight.setText("NIL");
        }
        else{
           eyeSight.setText(eyeText);
        }

        if(noseText.isEmpty()){
          NoseAllergy.setText("NIL");
        }
        else{
            NoseAllergy.setText(noseText);
        }

        if(throatText.isEmpty()){
           ThroatAllergy.setText("NIL");
        }
        else{
            ThroatAllergy.setText(throatText);
        }
        if(eB.isEmpty()){
           EyeBurn.setText("No");
        }else {
            EyeBurn.setText("Yes");
        }
        if(rE.isEmpty()){
            RedEye.setText("No");
        }else{
            RedEye.setText("Yes");
        }

        if(w.isEmpty()){
            Water.setText("No");
        }else{
            Water.setText("Yes");
        }

        if(dE.isEmpty()){
            DryEye.setText("No");
        }else{
            DryEye.setText("Yes");
        }
        if(n.isEmpty()){
            NasalCong.setText("No");
        }else
        {
            NasalCong.setText("Yes");
        }
        if(nB.isEmpty()){
            NoseBleed.setText("No");
        }
        else{
            NoseBleed.setText("Yes");
        }
        if(tP.isEmpty()){
            ThroatPain.setText("No");
        }
        else{
            ThroatPain.setText("Yes");
        }
        if(v.isEmpty()){Voice.setText("No");}
        else{Voice.setText("Yes");}

        if(sT.isEmpty()){SoreThroat.setText("No");}
        else {SoreThroat.setText("Yes");}

        if(dT.isEmpty()){DryThroat.setText("No");}
        else{DryThroat.setText("Yes");}

        if(eA.isEmpty()){EarAche.setText("No");}
        else{EarAche.setText("Yes");}

        if(hL.isEmpty()){HearLoss.setText("No");}
        else{HearLoss.setText("Yes");}
        if(eR.isEmpty()){EarRing.setText("No");}
        else{EarRing.setText("Yes");}
       if(b.isEmpty()){Balance.setText("No");}
       else{Balance.setText("Yes");}
       if(eF.isEmpty()){EarFull.setText("No");}
       else{EarFull.setText("Yes");}
       diseaseDiagnose();
    }

    private void diseaseDiagnose() {

         if((!b.isEmpty()||!hL.isEmpty()||!eR.isEmpty())||!eF.isEmpty()||!eA.isEmpty()){

            if((!b.isEmpty()&&!hL.isEmpty()&&!eR.isEmpty())||(!b.isEmpty()&&!hL.isEmpty())||!b.isEmpty())
                Disease.setText("Predicted Meniere's disease Symptoms\n This is the disorder of inner ear, affects only one ear\n Consultation of ENT specialist is necessary.");

            else if((!eA.isEmpty()&&!hL.isEmpty())){
                Disease.setText(" Predicted Ear Infection Symptoms\n Consult an ENT specialist immediately." );
            }
            else if(!eA.isEmpty()){
                Disease.setText("Excess of ear wax build up\n Consult an ENT specialist in severe pains in ear.");
            }

            else if(!eR.isEmpty()||!eF.isEmpty()||(!eR.isEmpty()&&!eF.isEmpty()))
                Disease.setText("Predicted Tinnitus Symptoms\n consult a physician or ENT Specialist for guidances. ");

            else
                Disease.setText("Predicted Hear Loss Symptoms\n Consult an ENT specialist for guidances.");

        }

         else if(!n.isEmpty()||!sT.isEmpty()||!nB.isEmpty()||!noseText.isEmpty()||!tP.isEmpty()||!v.isEmpty()||!throatText.isEmpty()||!dT.isEmpty()){

             if((!n.isEmpty()&&!sT.isEmpty()&&!nB.isEmpty())||(!n.isEmpty()&&!sT.isEmpty())||(!n.isEmpty()&&!nB.isEmpty())||!nB.isEmpty()) {
                 Disease.setText("Predicted Sinusitis Symptoms. \n Treatment : Consult an ENT specialist in severe cases. Cover your nose ,while going out in winter times.");
             }
             else if((!n.isEmpty()&&!nB.isEmpty()&&!noseText.isEmpty())||(!n.isEmpty()&!noseText.isEmpty())||(!n.isEmpty()&&!noseText.isEmpty())||!noseText.isEmpty()){
                 Disease.setText("Monitor the symptoms and Consult your ENT specialist for his/her guidances.");
             }
             else if((!tP.isEmpty()&&!sT.isEmpty()&&!v.isEmpty())||(!tP.isEmpty()&&!sT.isEmpty())){
                 Disease.setText("Predicted Tonsillitis symptoms \n Consult a physician or ENT specialist.");
             }
             else if(!sT.isEmpty()||(!tP.isEmpty()&&!v.isEmpty())||(!tP.isEmpty()&&!dT.isEmpty())||!tP.isEmpty()||!dT.isEmpty() || !v.isEmpty()){
                 Disease.setText("Predicted General Throat Infection or Allergic Condition Symptoms\n Consult a physician or ENT specialist and continuous monitoring is required");
             }

             else if((!tP.isEmpty()&&!throatText.isEmpty()&&!v.isEmpty()||!sT.isEmpty())||(!tP.isEmpty()&&!throatText.isEmpty())||(!dT.isEmpty()&&!throatText.isEmpty())||!throatText.isEmpty()||(!throatText.isEmpty()&&!sT.isEmpty())){
                 Disease.setText("Monitor the symptoms and Consult your ENT specialist for his/her guidances.");
             }
             else {
                 Disease.setText("Predicted General Viral Infection Symptoms \n Consult a physician. Maintain distance from surrounding people.");
             }

         }

         else if(!eB.isEmpty()||rE.isEmpty()||!w.isEmpty()||!dE.isEmpty()||!eyeText.isEmpty()){
             if((!rE.isEmpty()&&!eB.isEmpty()&&!w.isEmpty())){
                 Disease.setText("Predicted Conjuctivitis symptoms\n Treatment: Cover your eye with glasses, which does not allow to spread. Consult a Ophthalmologist immediately and for proper medication.");
             }
             else  if((!eB.isEmpty()&&!eyeText.isEmpty())||(!rE.isEmpty()&&!eyeText.isEmpty())||(!w.isEmpty()&&!eyeText.isEmpty())||!eyeText.isEmpty()){
                 Disease.setText("Consult your Eye Specialist for his/her guidelines.");
             }
             else if(!dE.isEmpty()){
                 Disease.setText("Dry Eyes: \n Use eye drops under proper guidance of an Ophthalmologist. Protect your eye from dust and winds by using glasses.");
             }
             else {

                 if ((!rE.isEmpty() && !eB.isEmpty()) || !eB.isEmpty() || rE.isEmpty() || (eB.isEmpty() && !w.isEmpty()) || !w.isEmpty() || (!eB.isEmpty() && !dE.isEmpty()) || (!rE.isEmpty() && !dE.isEmpty())) {
                     Disease.setText("Predicted Eye Strain Symptoms:\n Treatment: Have enough amount of sleep. Take short breaks while working on PCs and laptops. Consult an eye Specialist in severe cases.");
                 }
             }

        }
        else {
            Disease.setText("Could not Predict, Consult a nearby physician for emergency cases.");
        }
    }
}