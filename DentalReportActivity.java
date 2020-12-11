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

public class DentalReportActivity extends AppCompatActivity {
    TextView Yellow,Toothache,DryMouth,Bleeding,BadBreathe,Sores,Sensitive;

    TextView disease;

    String yellow=new String();String ache=new String();String  dry=new String();String bleed= new String();
    String bad=new String();String sore=new String();String sense=new String();String Disease=new String();

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Dental");
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_report);

        Yellow=findViewById(R.id.textViewYellow);
        Toothache=findViewById(R.id.textViewToothAche);
        DryMouth=findViewById(R.id.textViewDryMouth);
        Bleeding=findViewById(R.id.textViewBleed);
        BadBreathe=findViewById(R.id.textViewBad);
        Sores=findViewById(R.id.textViewSores);
        Sensitive=findViewById(R.id.textViewSensitive);
        disease=findViewById(R.id.textViewDisease);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Dental dental=data.getValue(Dental.class);

                    String uid=dental.getUid();
                    if (uid.equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                        System.out.println("$$$$$$$$$$"+dental.getSensitive());

                       yellow=dental.getYellow();
                       ache=dental.getAche();
                       dry=dental.getDry();
                       bleed=dental.getBleed();
                       bad=dental.getBreathe();
                       sore=dental.getSore();
                       sense=dental.getSore();
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
        if(yellow.isEmpty()){Yellow.setText("No");}
        else{Yellow.setText("Yes");}

        if(ache.isEmpty()){Toothache.setText("No");}
        else{Toothache.setText("Yes");}

        if(dry.isEmpty()){DryMouth.setText("No");}
        else {DryMouth.setText("Yes");}

        if(bleed.isEmpty()){Bleeding.setText("No");}
        else{Bleeding.setText("Yes");}

        if(bad.isEmpty()){BadBreathe.setText("No");}
        else{BadBreathe.setText("Yes");}

        if(sore.isEmpty()){Sores.setText("No");}
        else {Sores.setText("Yes");}

        if(sense.isEmpty()){Sensitive.setText("No");}
        else {Sensitive.setText("Yes");}
        diseaseDiagnosed();

    }

    private void diseaseDiagnosed() {
        if(!yellow.isEmpty() ||(!yellow.isEmpty()&&!sense.isEmpty())){
            disease.setText("Tooth Erosion: \n Symptoms: Decolouration,Sensitivity,Cracked Tooth(Extreme).\n Treatment:Avoid Excessive soft drink consumption Fruit drinks which contains more acidic properties\n Consult a Dentist immediately. ");
        }
        else if((!ache.isEmpty()&&!dry.isEmpty()&&!sense.isEmpty()&&!bad.isEmpty())||!bad.isEmpty() ||!ache.isEmpty()||!dry.isEmpty() ){
            disease.setText("Tooth Decay(Cavities):\n Symptoms: Severe Tooth Ache, Sensitivity.\n Treatment: Consult a Dentist immediately.Try to have soft food.");
        }
        else if((!bleed.isEmpty()&&!sense.isEmpty())||!bleed.isEmpty()){
            disease.setText("Gum(Periodontal)Disease:\n Symptoms: Gum Bleed,Swollen and reddish gums.\nTreatment: Consult a Dentist Immediately.A good dental care is neccesary.");
        }
        else if(!sore.isEmpty()){
            disease.setText("Mouth Ulcers:\n Treatment: A good dental care is necessary. A Healthy Diet must be followed.\n Consult a dentist for further prevention.");
        }
        else {
            disease.setText("Sensitivity:\n Symptoms: Causing severe pain ,while having something extreme cold or hot.\n Treatment: avoid having Extreme chill or spicy food. Use a proper dental care paste under a dentist guidance.");

        }


    }
}