package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity implements SymptomsAdapter.OnSymptomsListener {
    RecyclerView recyclerViewS;
    SymptomsAdapter adapter;
    List<SymptomsList> symptomsLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        symptomsLists=new ArrayList<>();

        recyclerViewS=findViewById(R.id.recyclerViewSymptoms);
        recyclerViewS.setHasFixedSize(true);

        recyclerViewS.setLayoutManager(new LinearLayoutManager(this));

        symptomsLists.add(
                new SymptomsList(
                        1,

                        R.drawable.fever,
                        "General Tiredness?",
                        "Fever,mild cold or cough,HeadAche"

                ));
        symptomsLists.add(
                new SymptomsList(
                        1,
                        R.drawable.torso,
                        "Any issues in torso region?",
                        "Neck,Chest and Abdominal regions"

                ));
        symptomsLists.add(
                new SymptomsList(
                        1,
                        R.drawable.skin,
                        "Skin Related Issues?",
                        "Skin pores,rashes,Allergies"

                ));
        symptomsLists.add(
                new SymptomsList(
                        1,
                        R.drawable.dental,
                        "Dental Issues?",
                        "Tooth aches,Swelling and Bleeding of gums"
                ));
        symptomsLists.add(
                new SymptomsList(
                        1,
                        R.drawable.ent,
                        "Any issues in Eye,Nose,ear,Throat?",
                        "Ear pain,Throat pain,Bleeding of nose"
                ));
        adapter=new SymptomsAdapter(this,symptomsLists,this);
        recyclerViewS.setAdapter(adapter);


    }

    @Override
    public void onSymptomsClick(int position) {
        String index= symptomsLists.get(position).getTitle();
        switch (index){
            case "General Tiredness?":
                startActivity(new Intent(SymptomsActivity.this,GeneralSymptomsActivity.class));
                break;
            case "Any issues in Eye,Nose,ear,Throat?":
                startActivity(new Intent(SymptomsActivity.this,EntActivity.class));
                break;
            case "Skin Related Issues?":
                startActivity(new Intent(SymptomsActivity.this,SkinActivity.class));
                break;
            case "Dental Issues?":
                startActivity(new Intent(SymptomsActivity.this,DentalActivity.class));
                break;
            case "Any issues in torso region?":
                startActivity(new Intent(SymptomsActivity.this,TorsoActivity.class));
                break;
        }

    }
}