package com.gs.medicaldiagnosisexpertsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.circularImageView);
        logo = findViewById(R.id.textViewProject);
        slogan = findViewById(R.id.textView3);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next =new Intent(SplashActivity.this,MainActivity.class);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logoImage");
                pairs[1]=new Pair<View,String>(logo,"logoText");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                    startActivity(next,options.toBundle());
                    finish();
                }

            }
        },SPLASH_SCREEN);
        /*Runnable updateCurrentTime = new Runnable() {
            @Override
            public void run() {

                h.postDelayed(this, SPLASH_SCREEN);
                Intent next =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(next);
                finish();
            }
        };
        h.postDelayed(updateCurrentTime, SPLASH_SCREEN);*/
        /*new Handler().postDelayed({
                 next =new Intent(SplashActivity.this,MainActivity.class);
        startActivity(next);
        finish();
        },SPLASH_SCREEN);*/

    }
}