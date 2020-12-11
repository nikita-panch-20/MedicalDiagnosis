package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SkinActivity extends AppCompatActivity {
    CheckBox Pimple,Rash,Itch,Swell,Shingle,SunBurn,SkinPeel,SkinRing,Pricky;
    EditText Allergy;
    String pimple=new String();String rash=new String();String itch=new String();String swell=new String();
    String shingle=new String();String sun=new String();String peel=new String();String ring=new String();
    String prick=new String();
    String allergy=new String();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Symptoms").child("Skin Related");
    FirebaseAuth auth;
    FirebaseUser user;
    String key = reference.push().getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);
        Pimple=findViewById(R.id.checkBoxPimple);
        Rash=findViewById(R.id.checkBoxRashes);
        Itch=findViewById(R.id.checkBoxItchy);
        Swell=findViewById(R.id.checkBoxSwelling);
        Shingle=findViewById(R.id.checkBoxRedCluster);
        SunBurn=findViewById(R.id.checkBoxSunBurn);
        SkinPeel=findViewById(R.id.checkBoxPeeling);
        SkinRing=findViewById(R.id.checkBoxClusterSkin);
        Pricky=findViewById(R.id.checkBoxPrick);
        Allergy=findViewById(R.id.editTextSkinAllergy);
        allergy=Allergy.getText().toString();

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();



    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxPimple:
                if (checked){
                    pimple=Pimple.getText().toString();
                }
                // Put some meat on the sandwich
                else{
                    pimple="";
                }
                // Remove the meat
                break;
            case R.id.checkBoxRashes:
                if (checked){
                    rash=Rash.getText().toString();

                }
                // Cheese me
                else{
                    rash="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxItchy:
                if (checked){
                    itch=Itch.getText().toString();
                }
                // Cheese me
                else{
                    itch="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxSwelling:
                if (checked){
                    swell=Swell.getText().toString();
                }
                // Cheese me
                else{
                    swell="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxRedCluster:
                if (checked){
                    shingle=Shingle.getText().toString();
                }
                // Cheese me
                else{
                    shingle="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxSunBurn:
                if (checked){
                    sun=SunBurn.getText().toString();
                }
                // Cheese me
                else{
                    sun="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxPeeling:
                if (checked){
                    peel=SkinPeel.getText().toString();
                }
                // Cheese me
                else{
                    peel="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxClusterSkin:
                if (checked){
                    ring=SkinRing.getText().toString();
                }
                // Cheese me
                else{
                    ring="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxPrick:
                if (checked)
                    prick=Pricky.getText().toString();
                    // Cheese me
                else{
                    prick="";
                }
                // I'm lactose intolerant
                break;
            case R.id.checkBoxSkinAllergy:
                if (checked){
                    if(allergy.isEmpty()){
                        Allergy.setError("This field cannot be field");
                        Allergy.requestFocus();
                        return;
                    }
                    if(!TextUtils.isEmpty(allergy)){
                        Allergy.setError(null);
                        Allergy.requestFocus();
                        return;
                    }
                    allergy=Allergy.getText().toString();
                }
                // Cheese me
                else{
                    allergy="";
                }
                // I'm lactose intolerant
                break;

        }
    }
    public void OnTap(View view){

        if(allergy.equals("")&&pimple.equals("")&&peel.equals("")&&prick.equals("")&&rash.equals("")&&itch.equals("")&&swell.equals("")&&shingle.equals("")&&sun.equals("")&&ring.equals("")){
            Toast.makeText(getApplicationContext(),"No Symptoms Noted",Toast.LENGTH_SHORT).show();

        }
        else{
            Skin skin=new Skin(pimple,rash,itch,swell,shingle,sun,peel,ring,prick,allergy,user.getUid());
            reference.child(key).setValue(skin);
            Toast.makeText(getApplicationContext(),"Symptoms Noted Successfully",Toast.LENGTH_SHORT).show();

        }
    }
    public void OnGetReportTapped(View view){
        startActivity(new Intent(this,SkinReportActivity.class));
        finish();

    }
}