package com.example.medhat_ahmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MarketingAgenciesAndSponsorsActivity extends AppCompatActivity {

    ImageView back_imageView;
    ImageView home_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_agencies_and_sponsors);

        back_imageView=(ImageView) findViewById(R.id.back_imageView);
        home_imageView=(ImageView) findViewById(R.id.home_imageView);




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
    }




    public void back(){
        Intent intent=new Intent(this, ManageEvents.class);
        startActivity(intent);
    }

    public void home(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}