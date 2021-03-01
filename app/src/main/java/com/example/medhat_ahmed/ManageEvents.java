package com.example.medhat_ahmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ManageEvents extends AppCompatActivity {

    ImageView home_imageView;
    ImageView back_imageView;
    CardView speacializedArtists;
    CardView hotelsAndSpecialPlaces;
    CardView entertainment;
    CardView otherServices;

    ScrollView Layout;
    LinearLayout Layout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_events);


        Layout=(ScrollView) findViewById(R.id.Layout);
        Layout2=(LinearLayout) findViewById(R.id.Layout2);
        back_imageView=(ImageView) findViewById(R.id.back_imageView);
        home_imageView=(ImageView) findViewById(R.id.home_imageView);
        speacializedArtists=(CardView) findViewById(R.id.speacializedArtists);
        hotelsAndSpecialPlaces=(CardView) findViewById(R.id.hotelsAndSpecialPlaces);
        entertainment=(CardView) findViewById(R.id.entertainment);
        otherServices=(CardView) findViewById(R.id.otherServices);


        AnimationDrawable animationDrawable=(AnimationDrawable) Layout2.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        back_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });


        home_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                home();
            }
        });



        speacializedArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      SpeacializedArtistsActivity();
            }
        });


        hotelsAndSpecialPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HotelsAndSpecialPlacesActivity();

            }
        });

        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EntertainmentActivity();
            }
        });


        otherServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OtherServicesActivity();

            }
        });



    }


    public void back(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void home(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void MarketingAgenciesAndSponsorsActivity(){
        Intent intent=new Intent(this, MarketingAgenciesAndSponsorsActivity.class);
        startActivity(intent);
    }

   public void SpeacializedArtistsActivity(){
        Intent intent=new Intent(this, SpeacializedArtistTabActivity.class);
        startActivity(intent);
    }

    public void HotelsAndSpecialPlacesActivity(){
        Intent intent=new Intent(this, HotelsAndSpecialPlacesTabActivity.class);
        startActivity(intent);
    }

    public void EntertainmentActivity(){
        Intent intent=new Intent(this, EntertainmentTabActivity.class);
        startActivity(intent);
    }


    public void OtherServicesActivity(){
        Intent intent=new Intent(this, OtherServicesTabActivity.class);
        startActivity(intent);
    }




}