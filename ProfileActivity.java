package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class ProfileActivity extends AppCompatActivity {
    TextInputLayout Fullname, Mobile, Age, Gender, Height, Weight, Email;

    String fullname, mobile, age, height, weight, gender, email;
   // ListView listViewUsers;
    //List<User> users;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference myRef;
    private ImageView imageView;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference reference;
    private String uid;
    private Button upload,edit;
    private ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Fullname = findViewById(R.id.editTextFullName);
        Mobile = findViewById(R.id.editTextMobile);
        Email = findViewById(R.id.editTextEmail);
        Age = findViewById(R.id.editTextAge);
        Gender = findViewById(R.id.editTextGender);
        Height = findViewById(R.id.editTextHeight);
        Weight = findViewById(R.id.editTextWeight);
        imageView = findViewById(R.id.imageViewProfile);
        upload = findViewById(R.id.buttonUpload);
        edit=findViewById(R.id.buttonEdit);


        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        reference = FirebaseDatabase.getInstance().getReference("profilePics");
        pb = findViewById(R.id.progressbar);



      //  listViewUsers = findViewById(R.id.listView);
        //users = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        System.out.println("$$$$$$$$$$$ "+user);
        myRef = database.getReference("Users").child(user.getUid());

        loadImage();

    }

    @Override
    protected void onStart() {
        super.onStart();
      if(user!=null) {
          myRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  User myUser = dataSnapshot.getValue(User.class);
                  System.out.println("$$$$$$$$$$"+myUser.getFullname());

                  Fullname.getEditText().setText(myUser.getFullname());
                  Mobile.getEditText().setText(myUser.getMobile());
                  Email.getEditText().setText(myUser.getEmail());
                  Age.getEditText().setText(myUser.getA());
                  Height.getEditText().setText(myUser.getH());
                  Weight.getEditText().setText(myUser.getW());
                  Gender.getEditText().setText(myUser.getG());
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }

          });
      }
    }
    public void onUploadTapped(View view) {
        pb.setVisibility(View.VISIBLE);
        uploadImage();
    }

    private void uploadImage() {
        final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        pb.setVisibility(View.GONE);
                        Profile profile = new Profile(uri.toString(), uid);
                        String key = reference.push().getKey();
                        reference.child(key).setValue(profile);
                        Toast.makeText(getApplicationContext(), "Display Picture Uploaded successfully", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onImageViewTapped(View view) {
        pickImage();
    }

    private void pickImage() {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pickIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void loadImage() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Profile myProfile = data.getValue(Profile.class);
                    if (myProfile.getUserId().equals(user.getUid())) {
                        //Glide.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                        Picasso.with(getApplicationContext()).load(myProfile.getImageLink()).into(imageView);
                    }
                }
                //System.out.println("sdadas "+stories.size());
                //ImageAdapter adapter = new ImageAdapter(getApplicationContext(),stories,UserFeedActivity.this);
                //recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }
    public void onEditTapped(View view){

        System.out.println("$$$$$$$$ Edit tapped");

        fullname=Fullname.getEditText().getText().toString();
        email=Email.getEditText().getText().toString();
        mobile=Mobile.getEditText().getText().toString();
        age=Age.getEditText().getText().toString();
        gender=Gender.getEditText().getText().toString();
        height=Height.getEditText().getText().toString();
        weight=Weight.getEditText().getText().toString();
        String MobilePattern = "[0-9]{10}";



        if(mobile.isEmpty()){
            Mobile.setError("set a username");
            Mobile.requestFocus();
            return;
        }
        /*if(!Patterns.PHONE.matcher(mobile).matches()){
            Mobile.setError("Enter a valid Mobile No.");
            Mobile.requestFocus();
            return;
        }*/
        if(mobile.equals(MobilePattern)){
            Mobile.setError("Enter a valid no.");
            Mobile.requestFocus();
            return;
        }
        if(email.isEmpty()){
            Email.setError("This field cannot be empty");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Enter a valid Email!");
            Email.requestFocus();
            return;
        }

        if(age.isEmpty()){
            Age.setError("Age is required");
            Age.requestFocus();
            return;
        }
        if(fullname.isEmpty()){
            Fullname.setError("Name is required");
            Fullname.requestFocus();
            return;
        }
        if(height.isEmpty()){
            Height.setError("Height is required");
            Height.requestFocus();
            return;
        }
        if(weight.isEmpty()){
            Weight.setError("Weight is required");
            Weight.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            Gender.setError("Gender is required");
            Gender.requestFocus();
            return;
        }

        if(user!=null) {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User myUser = dataSnapshot.getValue(User.class);
                    System.out.println("$$$$$$$$$$"+myUser.getFullname());

                    if(!(fullname.equals(myUser.getFullname()))){
                        myRef.child("fullname").setValue(fullname);
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();

                    }
                     if(!email.equals(myUser.getEmail())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("email").setValue(email);
                    }
                     if(!mobile.equals(myUser.getMobile())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("mobile").setValue(mobile);
                    }
                     if(!age.equals(myUser.getA())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("a").setValue(age);
                    }
                     if(!height.equals(myUser.getH())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("h").setValue(height);
                    }
                     if(!weight.equals(myUser.getW())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("w").setValue(weight);
                    }
                     if(!gender.equals(myUser.getG())){
                        Toast.makeText(getApplicationContext(),"Profile updated Successfully",Toast.LENGTH_SHORT).show();
                        myRef.child("g").setValue(gender);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }


}
         /*ImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });*/

        /*EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });*/




        /*EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.isEmpty()){
                    Username.setError("Email is required");
                    Username.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                    Username.setError("Enter a valid username!");
                    Username.requestFocus();
                    return;
                }
                if(age.isEmpty()){
                    Age.setError("Age is required");
                    Age.requestFocus();
                    return;
                }
                if(height.isEmpty()){
                    Height.setError("Height is required");
                    Height.requestFocus();
                    return;
                }
                if(weight.isEmpty()){
                    Weight.setError("Weight is required");
                    Weight.requestFocus();
                    return;
                }
                if(gender.isEmpty()){
                    Gender.setError("Gender is required");
                    Gender.requestFocus();
                    return;
                }


                fullname=Fullname.getText().toString();
                username=Username.getText().toString();
                age=Age.getText().toString();
                gender=Gender.getText().toString();
                height=Height.getText().toString();
                weight=Weight.getText().toString();


                myuser.setFullname(fullname);
                myuser.setUsername(username);
                myuser.setA(age);
                myuser.setG(gender);
                myuser.setH(height);
                myuser.setW(weight);

                Toast.makeText(getApplicationContext(),"Profile Updated successfully!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
                finish();




           // }
        //});

    }*/


      //}
       //private void showAllUserInfo () {
        //myRef=database.getReference("Users").child(uid);

          //  myRef.addValueEventListener(new ValueEventListener() {
            //    @Override
              //  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //try {
                //    for (DataSnapshot data : dataSnapshot.getChildren()) {
                  //      if(user!=null){
                    //    User myUser = data.getValue(User.class);
                      //  usernameFromDB= myUser.getUsername();
                       // fullnameFromDB=myUser.getFullname();
                      //  emailFromDB=myUser.getEmail();
                       // ageFromDB=myUser.getA();
                       // heightFromDB=myUser.getH();
                      //  weightFromDB=myUser.getW();
                       // genderFromDB=myUser.getG();


                            /*Fullname.setText(fullnameFromDB);
                            Username.setText(usernameFromDB);
                            Email.setText(emailFromDB);
                            Age.setText(ageFromDB);
                            Height.setText(heightFromDB);
                            Weight.setText(weightFromDB);
                            Gender.setText(genderFromDB);*/
                       // }

                        //}/*catch (Exception e) {
                       // Toast.makeText(getApplicationContext(),(CharSequence)e,Toast.LENGTH_SHORT).show();
                   // }*/
                   // }

              //  @Override
               // public void onCancelled(@NonNull DatabaseError databaseError) {

                //}


                //});
       // }








   /* private void loadUserInformation() {

        if(user!=null){
            if(user.getPhotoUrl()!=null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(profile);
            }


        }

    }*/




        /*Intent i1 = getIntent();
        fullnameFromDB = i1.getStringExtra("fullname");
        usernameFromDB = i1.getStringExtra("username");
        ageFromDB = i1.getStringExtra("age");
        genderFromDB = i1.getStringExtra("gender");
        heightFromDB = i1.getStringExtra("height");
        weightFromDB = i1.getStringExtra("weight");


        Fullname.setText(fullnameFromDB);
        Username.setText(usernameFromDB);
        Age.setText(ageFromDB);
        Height.setText(heightFromDB);
        Weight.setText(weightFromDB);
        Gender.setText(genderFromDB);*/





   /* private void showImageChooser(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),101);
    }*/





       /*private void saveUserInformation() {

        if(user!=null && profileImgUrl!=null){
            pb.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                    .setPhotoUri(Uri.parse(profileImgUrl))
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


    /*private void uploadImageToFirebaseStorage(){
        profileImage = FirebaseStorage.getInstance().getReference("profilePics").child(System.currentTimeMillis()+"."+getFileExtension(uriProfileImage));
        profileRef=FirebaseDatabase.getInstance().getReference("Profile");
        if(uriProfileImage!=null){
            pb.setVisibility(View.VISIBLE);
            profileImage.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pb.setVisibility(View.GONE);
                    profileImgUrl=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                    profileImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Profile profile= new Profile(uri.toString(),uid);
                            profileRef.child(profileId).setValue(profile);
                        }
                    });

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
    }*/
    /*private String getFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }*/


