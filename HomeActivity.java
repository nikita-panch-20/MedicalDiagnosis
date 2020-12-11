package com.gs.medicaldiagnosisexpertsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements MainMenuAdapter.OnMainListener {
 RecyclerView recyclerView;
 MainMenuAdapter adapter;
    List<MainMenuList> menuList;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //adding some items to our list
        menuList.add(
                new MainMenuList(
                        1,
                        R.drawable.symptoms,
                        "How do you feel?",
                        "Check your symptoms"
                       ));

        menuList.add(
                new MainMenuList(
                        2,
                        R.drawable.bmi,
                        "Body-Mass-Index Calculator",
                        "Keep an daily health update"
                        ));

        menuList.add(
                new MainMenuList(
                        4,
                        R.drawable.about,
                        "About Us",
                        "Know our history"
                        ));
        menuList.add(
                new MainMenuList(
                        5,
                        R.drawable.contact,
                        "Contact Us",
                        "For further queries"
                        ));
        adapter= new MainMenuAdapter(this,menuList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String data=item.getTitle().toString();
        if(data.equals("My Profile")){
            Intent profileEdit=new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(profileEdit);
        }
        else{
            FirebaseAuth.getInstance().signOut();
            finish();
            Toast.makeText(getApplicationContext(),"Logged out Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onMainClick(int position) {

        String index= menuList.get(position).getTitle();

        switch (index){
            case "How do you feel?":
                startActivity(new Intent(HomeActivity.this,SymptomsActivity.class));
                    break;

            case "Body-Mass-Index Calculator":
                System.out.println("$$$$$$$$$$"+menuList.get(position).getTitle());
                  startActivity(new Intent(HomeActivity.this, BmiCalciActivity.class));
                    break;

            case "About Us":
                startActivity(new Intent(HomeActivity.this,AboutUsActivity.class));
                    break;

            case "Contact Us":
                startActivity(new Intent(HomeActivity.this,ContactActivity.class));
                    break;
        }



    }
}
