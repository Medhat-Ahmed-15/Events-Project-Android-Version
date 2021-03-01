package com.example.medhat_ahmed;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout Layout;



    TextView welcomeTitle_textView;
   // TextView logo_textView;

    ImageView imageView;


    Animation topAnimation;
    Animation bottomAnimation;
    Animation fadeAnimation;


    private static int SPLASH_SCREEN=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Layout=(ConstraintLayout) findViewById(R.id.Layout);
        welcomeTitle_textView=(TextView) findViewById(R.id.welcomeTitle_textView);
        //logo_textView=(TextView) findViewById(R.id.logo_textView);
        imageView=(ImageView) findViewById(R.id.imageView);


        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fadeAnimation= AnimationUtils.loadAnimation(this,R.anim.fade_animation);


       // logo_textView.setAnimation(topAnimation);
        welcomeTitle_textView.setAnimation(bottomAnimation);
        imageView.setAnimation(fadeAnimation);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();

                //Call this when your activity is done and should be closed.
                // The ActivityResult is propagated back to whoever launched you via onActivityResult().
                finish();

            }
        },SPLASH_SCREEN);




    }

    public void nextActivity(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }


}