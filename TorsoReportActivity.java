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

public class TorsoReportActivity extends AppCompatActivity {
    TextView DullStomach,Burn,Burp,Nausea,HeartBeat,Weakness,Sweating,WeightLoss,AbdominalPain,Dia,MusclePain,Breathless,disease;
    String dS=new String(); String b=new String();String bp=new String();
    String nau=new String();String hB=new String();String w=new String();
    String s=new String();String wL=new String();String aP=new String();
    String d=new String();String mP=new String();String bL=new String();
    String Disease=new String();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Torso Region");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torso_report);
        DullStomach=findViewById(R.id.textViewStomachPain);
        Burn=findViewById(R.id.textViewBurn);
        Burp=findViewById(R.id.textViewBurp);
        Nausea=findViewById(R.id.textViewNausea);
        HeartBeat=findViewById(R.id.textViewHeartBeat);
        Weakness=findViewById(R.id.textViewWeakness);
        Sweating=findViewById(R.id.textViewSweat);
        WeightLoss=findViewById(R.id.textViewAppetite);AbdominalPain=findViewById(R.id.textViewAbdomen);
        Dia=findViewById(R.id.textViewDiarrhea);
        MusclePain=findViewById(R.id.textViewMuscles);
        Breathless=findViewById(R.id.textViewBreathe);
        disease=findViewById(R.id.textViewDisease);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Torso torso= data.getValue(Torso.class);

                    String uid=torso.getUid();
                    if (uid.equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);


                        dS=torso.getDull();
                        b=torso.getBurn();
                        bp=torso.getBurp();
                        nau=torso.getNausea();
                        hB=torso.getBeat();
                        w=torso.getWeak();
                        s=torso.getSweat();
                        wL=torso.getLoss();
                        aP=torso.getAbdomen();
                        d=torso.getDia();
                        mP=torso.getMuscle();
                        bL=torso.getBreathe();



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
        if(dS.isEmpty()){DullStomach.setText("No");}
        else{DullStomach.setText("Yes");}

        if(b.isEmpty()){Burn.setText("No");}
        else {Burn.setText("Yes");}


        if(bp.isEmpty()){Burp.setText("No");}
        else{Burp.setText("Yes");}

        if(nau.isEmpty()){Nausea.setText("No");}
        else{Nausea.setText("Yes");}

        if(hB.isEmpty()){HeartBeat.setText("No");}
        else{HeartBeat.setText("Yes");}

        if(w.isEmpty()){Weakness.setText("No");}
        else{Weakness.setText("Yes");}


        if(s.isEmpty()){Sweating.setText("No");}
        else{Sweating.setText("Yes");}

        if(wL.isEmpty()){WeightLoss.setText("No");}
        else{WeightLoss.setText("Yes");}

        if(aP.isEmpty()){AbdominalPain.setText("No");}
        else{AbdominalPain.setText("Yes");}

        if(d.isEmpty()){Dia.setText("No");}
        else{Dia.setText("Yes");}

        if(mP.isEmpty()){MusclePain.setText("No");}
        else{MusclePain.setText("Yes");}

        if(bL.isEmpty()){Breathless.setText("No");}
        else{Breathless.setText("Yes");}
        diseaseDiagnose();



    }

    private void diseaseDiagnose() {
        if((!dS.isEmpty()&&!wL.isEmpty()&&!nau.isEmpty()&&!b.isEmpty())){
            disease.setText("Ulcers:\n Symptoms: Severe Acidity,Uneasiness in chest,Trouble in breathing\n Treatment: Consumptions of daily meals in proper timings. Consult a general physician or gastroenterologist. Avoid eating spicy food.");
        }
        else if (!wL.isEmpty()&&!nau.isEmpty()&&!aP.isEmpty()){
            System.out.println("$$$$$hepatitis");
            disease.setText("Hepatitis C (Liver Infection caused by Hepatitis C virus):\n Symptoms: Joint Pains,Dark Urine,Nausea and Vomitings, fatigue,fever.\n Treatment: Consult a Hepatologist or a gastroenterologist(in mild cases)");
        }
        else if ((!dS.isEmpty() && !nau.isEmpty() && !w.isEmpty() && !d.isEmpty()) || (!dS.isEmpty() && !nau.isEmpty() && w.isEmpty()) || (!nau.isEmpty() && !w.isEmpty() && !d.isEmpty()) || (!dS.isEmpty() && !w.isEmpty() && !d.isEmpty()) || (!dS.isEmpty() && !nau.isEmpty() && !d.isEmpty()) || (!dS.isEmpty() && !nau.isEmpty()) || (!dS.isEmpty() && !d.isEmpty())) {
            System.out.println("$$$$$amoebiasis");
            disease.setText("Amoebiasis : Treatment: Consult a general physician immediately.");
        }
        else if((!w.isEmpty() && !s.isEmpty() && !aP.isEmpty() && !d.isEmpty())){
            System.out.println("$$$typhoid");
            disease.setText("Typhoid : \n Treatment : Consult a general physician immediately.");
        }
        else if (!d.isEmpty()) {
            System.out.println("$$$$diarrhea");
            disease.setText("Diarrhea : \nTreatment : Have plenty of fluids like ORS. Consult a general physician.");
        }
        else if ( (!dS.isEmpty() && !w.isEmpty()) || !dS.isEmpty() || !nau.isEmpty()) {
            System.out.println("$$$$ bacterial");
            disease.setText("Bacterial Infection: \n Treatment : Have soft food and plenty of fluids. Consult a physician. ");
        }
        else if (!w.isEmpty()) {
            System.out.println("$$$weakness");
            disease.setText("General Weakness \n Treatment : Have a Healthy Diet Plan. Exercising is necessary.");
        }

        else if(!aP.isEmpty()){
            System.out.println("$$$$$indigestion");
            disease.setText("Indigestion:\n Treatment: Have plenty of fluids. Consult a gastroenterologist.");
        }
        else if(!mP.isEmpty()){
            System.out.println("$$$muscles pains");
            disease.setText("This can lead to many predictions such as Inappropriate posture, Excessive exercising etc. Consult a general Physician.");
        }
        else if(!hB.isEmpty()||!s.isEmpty()||!bL.isEmpty()||b.isEmpty() || !bp.isEmpty()) {
         if (!hB.isEmpty() && !s.isEmpty() && !b.isEmpty() && !bL.isEmpty()) {
                disease.setText("Cardiac Arrest:\n Symptoms: Immediate shoot up of blood pressure, anxiety. \n Treatment: Consult a Cardiologist Immediately");
            }

         else if((!b.isEmpty()&&!bp.isEmpty()) || !b.isEmpty() || !bp.isEmpty() ){
             disease.setText("Acidity : \n Treatment : Avoid having spicy food. Have luke warm milk ,plenty of fluids. Have daily meals on time. Consult a physician if necessary.");
         }

         else if((!hB.isEmpty()&&!s.isEmpty())||!s.isEmpty()||!hB.isEmpty()||!bL.isEmpty()||(!hB.isEmpty()&&!bL.isEmpty())||(!s.isEmpty()&&!bL.isEmpty())||(!hB.isEmpty()&&!s.isEmpty()&&!bL.isEmpty())){
             disease.setText("High Blood Pressure:\n Treatment :Avoid taking stress And getting anger. Consult a physician.");
         }
         else {
             disease.setText("Could predict. Consult a general physician, if emergency");
         }

        }
        else {

            disease.setText("Could predict. Consult a general physician, if emergency");
            System.out.println("$$$$$$$$ here in else");
        }


    }
}