package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;



public class EditProfileActivity extends AppCompatActivity {
    TextInputLayout fullName,email,age,height,weight,gender,mobile;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference myRef;
    private String uid,Fullname,Email,Age,Height,Weight,Gender,Mobile;
    private Button save;
    private ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        fullName=findViewById(R.id.editTextFullName);
        email=findViewById(R.id.editTextEmail);
        mobile=findViewById(R.id.editTextMobile);
        age=findViewById(R.id.editTextAge);
        gender=findViewById(R.id.editTextGender);
        height=findViewById(R.id.editTextHeight);
        weight=findViewById(R.id.editTextWeight);

        save = findViewById(R.id.buttonSave);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        myRef = database.getReference("Users").child(user.getUid());
        pb = findViewById(R.id.progressbar);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEditedInfo();
            }
        });



    }

    private void saveEditedInfo() {
        Fullname=fullName.getEditText().getText().toString();
        Email=email.getEditText().getText().toString();
        Mobile=mobile.getEditText().getText().toString();
        Age=age.getEditText().getText().toString();
        Gender=gender.getEditText().getText().toString();
        Weight=weight.getEditText().getText().toString();
        Height=height.getEditText().getText().toString();

        if(user!=null){
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User myUser = dataSnapshot.getValue(User.class);
                    //System.out.println("$$$$$$$$$$"+myUser.getFullname());
                    myUser.setFullname(Fullname);
                    myUser.setEmail(Email);
                    myUser.setMobile(Mobile);
                    myUser.setA(Age);
                    myUser.setG(Gender);
                    myUser.setH(Height);
                    myUser.setW(Weight);
                    Toast.makeText(getApplicationContext(),"Profile Updated Successfully",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


}
    /*private void saveUserInformation() {

        if(user!=null && profileImgUrl!=null){
            pb.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                    .setPhotoUri(Uri.parse(profileImgUrl))
                    .setDisplayName(fullname)
                    .build();
            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()){
                              pb.setVisibility(View.GONE);
                              Toast.makeText(ProfileActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();


                          }
                          else{
                              Toast.makeText(ProfileActivity.this,"Profile Pic not Updated",Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
        }
    }*/







   // @Override
   /* protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK&& data!=null&& data.getData()!=null){
            uriProfileImage=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                profile.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void uploadImageToFirebaseStorage(){
        StorageReference profileImage = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
        if(uriProfileImage!=null){
            pb.setVisibility(View.VISIBLE);
            profileImage.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pb.setVisibility(View.GONE);
                    profileImgUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pb.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageChooser(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),101);
    }*/
