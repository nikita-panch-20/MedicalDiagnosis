package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout fullname,mobile,password,age,height,weight,gender,email;
    private ProgressBar pb;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef ;
    private String Mobile,Fullname,Email,Pass,a,h,w,g;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname= findViewById(R.id.editTextFullName);
        mobile=findViewById(R.id.editTextMobile);
        email=findViewById(R.id.editTextEmail);
        password=findViewById(R.id.editTextPassword);
        age=findViewById(R.id.editTextAge);
        height=findViewById(R.id.editTextHeight);
        weight=findViewById(R.id.editTextWeight);
        gender=findViewById(R.id.editTextGender);
        pb=findViewById(R.id.progressbar);

        register=findViewById(R.id.buttonRegister);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        myRef=database.getReference("Users");



    }
    public  void registerUser(View view)
    {
        Mobile=mobile.getEditText().getText().toString();
        Fullname=fullname.getEditText().getText().toString();
        Email=email.getEditText().getText().toString();
        Pass=password.getEditText().getText().toString();
        a=age.getEditText().getText().toString();
        h=height.getEditText().getText().toString();
        w=weight.getEditText().getText().toString();
        g=gender.getEditText().getText().toString();
       // String NoWhiteSpace="\\A\\w{4,20}\\z";
        //String MobilePattern = "[0-9]{10}";
        //String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        /*if(!TextUtils.isEmpty(Email)){
            email.setError(null);
            email.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(Mobile)){
            mobile.setError(null);
            mobile.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(Fullname)){
            fullname.setError(null);
            fullname.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(a)){
            age.setError(null);
            age.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(g)){
            gender.setError(null);
            gender.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(h)){
            height.setError(null);
            height.setEnabled(true);
            return;
        }
        if(!TextUtils.isEmpty(w)){
            weight.setError(null);
            weight.setEnabled(true);
            return;
        }*/

        if(Mobile.isEmpty()){
            mobile.setError("Mobile no. is required");
            mobile.requestFocus();
            return;
        }
        /*if(!Mobile.equals(MobilePattern)){
            mobile.setError("Enter a valid no.");
            mobile.requestFocus();
            return;
        }*/
        if(!Patterns.PHONE.matcher(Mobile).matches()){
            mobile.setError("Enter a valid Mobile No.");
            mobile.requestFocus();
            return;
        }
        if(Email.isEmpty()){
            email.setError("This field cannot be empty");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Enter a valid Email!");
            email.requestFocus();
            return;
        }
        if(Pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(Pass.length()<6){
            password.setError("Minimum length should be of 6 characters");
            password.requestFocus();
            return;
        }
        if(a.isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return;
        }
        if(Fullname.isEmpty()){
            fullname.setError("Name is required");
            fullname.requestFocus();
            return;
        }
        if(h.isEmpty()){
            height.setError("Height is required");
            height.requestFocus();
            return;
        }
        if(w.isEmpty()){
            weight.setError("Weight is required");
            weight.requestFocus();
            return;
        }
        if(g.isEmpty()){
            gender.setError("Gender is required");
            gender.requestFocus();
            return;
        }

       /* User myuser=new User(Fullname,Username,Email,a,w,h,g,Pass);
        myRef.child(Username).setValue(myuser);*/

        pb.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pb.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    String userId=mAuth.getCurrentUser().getUid();
                    //String Id=myRef.push().getKey();
                    User myUser=new User(Fullname,Mobile,Email,Pass,a,w,h,g);
                    System.out.println("$$$$$$"+myUser.getFullname());
                    myRef.child(userId).setValue(myUser);
                    Toast.makeText(getApplicationContext(),"User Registered successfully!",Toast.LENGTH_SHORT).show();
                    Intent intent1 =new Intent(RegisterActivity.this,MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);
                    finish();


                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You're Already Registered!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        /*Toast.makeText(getApplicationContext(),"User Registered successfully!",Toast.LENGTH_SHORT).show();
        Intent intent1 =new Intent(RegisterActivity.this,MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);*/



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;

        }

    }
}
