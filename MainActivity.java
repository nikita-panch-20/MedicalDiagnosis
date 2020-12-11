package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar pb;
    private FirebaseAuth mAuth;
    private TextInputLayout email, password;
    private ImageView image;
    private TextView logo;

    //String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        image=findViewById(R.id.logoImage);
        logo=findViewById(R.id.textView1);
        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        pb = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();


    }

    private void userLogin() {
        String Email = email.getEditText().getText().toString();
        String Pass = password.getEditText().getText().toString();
        String NoWhiteSpace = "\\A\\w{4,20}\\z";


        if (Email.isEmpty()) {
            email.setError("Enter an email");
            email.requestFocus();
            return;
        }
        if (Pass.isEmpty() || Pass.equals(NoWhiteSpace)) {
            password.setError("Invalid Password");
            password.requestFocus();
            return;
        }
        if (Pass.length() < 6) {
            password.setError("Minimum length should be of 6 characters");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Enter a valid Email!");
            email.requestFocus();
            return;
        }

        //isUser();
        pb.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email, Pass).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pb.setVisibility(View.GONE);

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();


                        }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onClick(View v) {
        pb.setVisibility(View.VISIBLE);
        switch (v.getId()) {
            case R.id.textViewSignup:
                Intent next =new Intent(MainActivity.this,RegisterActivity.class);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logoImage");
                pairs[1]=new Pair<View,String>(logo,"logoText");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(next,options.toBundle());
                    finish();
                }
                pb.setVisibility(View.GONE);
                break;
            case R.id.buttonLogin:
                userLogin();
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));

        }
    }

}

   /* private void isUser() {
        final String EnteredUsername= username.getText().toString().trim();
        final String EnteredPassword=password.getText().toString().trim();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid());

        Query checkUser= reference.orderByChild("username").equalTo(EnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    username.setError(null);
                    pb.setVisibility(View.VISIBLE);

                    String passwordFromDB=dataSnapshot.child(EnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(EnteredPassword)){
                        username.setError(null);

                        /*String fullnameFromDB=dataSnapshot.child("username").child("fullname").getValue(String.class);
                        String usernameFromDB=dataSnapshot.child("username").child("username").getValue(String.class);
                        String heightFromDB=dataSnapshot.child("username").child("h").getValue(String.class);
                        String weightFromDB=dataSnapshot.child("username").child("w").getValue(String.class);
                        String ageFromDB=dataSnapshot.child("username").child("a").getValue(String.class);
                        String genderFromDB=dataSnapshot.child("username").child("g").getValue(String.class);*/

                        /*Intent i1=new Intent(getApplicationContext(),ProfileActivity.class);
                        i1.putExtra("fullname",fullnameFromDB);
                        i1.putExtra("username",usernameFromDB);
                        i1.putExtra("age",ageFromDB);
                        i1.putExtra("height",heightFromDB);
                        i1.putExtra("weight",weightFromDB);
                        i1.putExtra("gender",genderFromDB);
                        pb.setVisibility(View.GONE);


                        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();


                    }
                    else{
                        password.setError("Incorrect Password");
                        password.requestFocus();
                    }

                }
                else{
                    username.setError("No such User Found");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    } */

