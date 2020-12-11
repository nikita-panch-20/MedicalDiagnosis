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

public class SkinReportActivity extends AppCompatActivity {
    TextView Pimple,Rash,Itch,Swell,Shingle,SunBurn,SkinPeel,SkinRing,Pricky,Allergy,Disease;
    String pimple=new String();String rash=new String();String itch=new String();String swell=new String();
    String shingle=new String();String sun=new String();String peel=new String();String ring=new String();
    String prick=new String();
    String allergy=new String(); String disease=new String();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Skin Related");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_report);
        Pimple=findViewById(R.id.textViewPimple);
        Rash=findViewById(R.id.textViewRash);
        Itch=findViewById(R.id.textViewItch);
        Swell=findViewById(R.id.textViewSwell);
        Shingle=findViewById(R.id.textViewShingles);
        SunBurn=findViewById(R.id.textViewSunBurn);
        SkinPeel=findViewById(R.id.textViewPeel);
        SkinRing=findViewById(R.id.textViewSkinRing);
        Pricky=findViewById(R.id.textViewPrick);
        Allergy=findViewById(R.id.textViewSkinAllergy);
        Disease=findViewById(R.id.textViewDisease);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Skin skin = data.getValue(Skin.class);

                    String uid=skin.getUid();
                    if (uid.equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                        System.out.println("$$$$$$$$$$"+skin.getPimple());
                       pimple=skin.getPimple();
                       rash=skin.getRash();
                       itch=skin.getItch();
                       swell=skin.getSwell();
                       shingle=skin.getShingle();
                       sun=skin.getSunBurn();
                       peel=skin.getSkinPeel();
                       ring=skin.getSkinRing();
                       prick=skin.getPricky();
                       allergy=skin.getAllergy();



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
        if(pimple.isEmpty()){Pimple.setText("No");}
        else{Pimple.setText("Yes");}

        if(rash.isEmpty()){Rash.setText("No");}
        else{Rash.setText("Yes");}

        if(itch.isEmpty()){Itch.setText("No");}
        else{Itch.setText("Yes");}

        if(swell.isEmpty()){Swell.setText("No");}
        else{Swell.setText("Yes");}

        if(shingle.isEmpty()){Shingle.setText("No");}
        else{Shingle.setText("Yes");}

        if(sun.isEmpty()){SunBurn.setText("No");}
        else{SunBurn.setText("Yes");}

        if(peel.isEmpty()){SkinPeel.setText("No");}
        else{SkinPeel.setText("Yes");}

        if(ring.isEmpty()){SkinRing.setText("No");}
        else{SkinRing.setText("Yes");}

        if(prick.isEmpty()){Pricky.setText("No");}
        else{Pricky.setText("Yes");}

        if(allergy.isEmpty()){Allergy.setText("No");}
        else{Allergy.setText(allergy);}
        diseaseDiagnose();

    }

    private void diseaseDiagnose() {
     if((!prick.isEmpty()&&!shingle.isEmpty()&&!itch.isEmpty()&&!rash.isEmpty())){
         Disease.setText("Predicted Chickenpox Symptoms\n Treatment: Keep yourself isolated. Consult a Nearby physician immediately.");
     }
     else if((!ring.isEmpty()&&!rash.isEmpty()&&!itch.isEmpty())||(!ring.isEmpty()&&!itch.isEmpty())||!ring.isEmpty()){
         Disease.setText("Predicted Ringworm Symptoms(Fungal infection)\n Treatment : Consult a Dermatologist for proper medication.");
     }
     else if((!itch.isEmpty()&&!shingle.isEmpty()&&!swell.isEmpty())||(!itch.isEmpty()&!shingle.isEmpty())||(!itch.isEmpty()&&!swell.isEmpty())){
         Disease.setText("Predicted Dermatitis Symptoms\n Treatment: Try to identify and avoid substances that irritate your skin or cause an allergic reaction.\nConsult a Dermatologist for proper medication , and when the rash is uncomfortable,painful and severe.");
     }
     else if((!swell.isEmpty())||!itch.isEmpty()||rash.isEmpty()||!pimple.isEmpty()){
         Disease.setText("Predicted Allergic contact dermatitis\n Treatment:Wear protective clothing or gloves. Use moisturizer and a barrier cream or gel.\n Consult a Dermatologist for proper medication , and when the rash is uncomfortable,painful and severe.");
     }
     else{
         Disease.setText("Could not predict. Consult a dermatologist in case of emergency.");
     }

    }
}