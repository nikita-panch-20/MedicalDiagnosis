package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BmiCalciActivity extends AppCompatActivity {
private TextView wts,hts,Bmi,res,HLabel,WLabel;
private Spinner sp;
private Button calculate;
private ArrayList<String> al;
private ArrayAdapter<String> ad;
private FirebaseAuth auth=FirebaseAuth.getInstance();
private FirebaseUser user=auth.getCurrentUser();
private DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
private String weightFromDB,heightFromDB,bmiResult,result,item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalci);
        sp=findViewById(R.id.spinner);
        hts=findViewById(R.id.textViewHeight);
        wts=findViewById(R.id.textViewWeight);
        Bmi=findViewById(R.id.textViewBMI);
        res=findViewById(R.id.textViewResult);
        HLabel=findViewById(R.id.textView6);
        WLabel=findViewById(R.id.textView4);
        calculate=findViewById(R.id.buttonCalculate);

        al= new ArrayList<>();
        al.add("Select a category");
        al.add("Imperial English BMI");
        al.add("Metric BMI");

        ad= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, al);
        sp.setAdapter(ad);

        if(user!=null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User myUser = dataSnapshot.getValue(User.class);
                        heightFromDB = myUser.getH();
                        weightFromDB = myUser.getW();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }


            });
        }
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 item=parent.getItemAtPosition(position).toString();
                 if(item.equals("Select a category")){
                     Toast.makeText(getApplicationContext(),"This category should be selected",Toast.LENGTH_SHORT).show();
                 }
                 else if(item.equals("Imperial English BMI")){
                     HLabel.setText("Your Height(in cms)");
                     WLabel.setText("Your Weight(in Kgs)");
                    hts.setText(heightFromDB);
                    wts.setText(weightFromDB);
                 }
                 else{
                     float weight=Float.parseFloat(weightFromDB);
                     weight=(float)(weight*2.205);
                     float height=Float.parseFloat(heightFromDB);
                     height=(float)(height*0.394);
                     HLabel.setText("Your Height(in Inch)");
                     WLabel.setText("Your Weight(in lbs)");
                     String h=String.valueOf(height);
                     String w=String.valueOf(weight);
                     hts.setText(h);
                     wts.setText(w);
                 }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       calculate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(item.equals("Select an category")){
                   Toast.makeText(getApplicationContext(),"This category should be selected",Toast.LENGTH_SHORT).show();

               }
               else if(item.equals("Imperial English BMI")){
                   calculateEngBMI();
               }
               else{
                   calculateMetricBMI();
               }

           }
       });


    }
    public void calculateMetricBMI() {
        if (heightFromDB != null && !"".equals(heightFromDB)
                && weightFromDB != null  &&  !"".equals(weightFromDB)) {
            float heightValue = Float.parseFloat(heightFromDB) / 100;
            float weightValue = Float.parseFloat(weightFromDB);

            float bmi = weightValue / (heightValue * heightValue);
            bmiResult=String.valueOf(bmi);
            Bmi.setText(bmiResult);
            displayBMI(bmi);
        }
    }
    private void calculateEngBMI() {
        HLabel.setText("Your Height(in Inch):");
        WLabel.setText("Your Weight(in lbs):");
        //converting height cms to meters
        float heightValue = Float.parseFloat(heightFromDB) / 100;

        float weightValue = Float.parseFloat(weightFromDB);

        //converting weight kgs to pounds(lbs)
        weightValue= (float) (weightValue*2.205);

        //converting height mts to inches
        heightValue=(float)(heightValue*39.37);



        if (heightFromDB != null && !"".equals(heightFromDB)
                && weightFromDB != null  &&  !"".equals(weightFromDB)) {


            float bmi = weightValue *703 / (heightValue * heightValue);
            bmiResult=String.valueOf(bmi);
            Bmi.setText(bmiResult);
            displayBMI(bmi);
        }


    }
    private void displayBMI(float bmi) {


        if (Float.compare(bmi, 15f) <= 0) {
            result="Very Severely Underweight";
            res.setText(result);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            result="Severely Underweight";
            res.setText(result);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            result="Underweight";
            res.setText(result);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            res.setText("Normal");
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            result="Overweight";
            res.setText(result);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            result="Obese class I";
            res.setText(result);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            result="Obese Class II";
            res.setText(result);
        } else {
            result="Obese Class III";
            res.setText(result);
        }


    }
}