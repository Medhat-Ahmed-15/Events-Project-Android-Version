package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Offers extends AppCompatActivity {


    ImageView back_imageView;
    ImageView home_imageView;

    Fragment selectedFragment;



    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        selectedFragment=new Fragment_Offer_Hairdresser();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        back_imageView = (ImageView) findViewById(R.id.back_imageView);
        home_imageView = (ImageView) findViewById(R.id.home_imageView);





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

    private BottomNavigationView.OnNavigationItemSelectedListener navListner=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    selectedFragment=null;

                    int id=item.getItemId();

                    if(id==R.id.hairdresserOffer)
                    {
                        selectedFragment=new Fragment_Offer_Hairdresser();
                    }

                    else if(id==R.id.makeupArtistOffer)
                    {
                        selectedFragment=new Fragment_Offer_MakeupArtist();
                    }

                    else if(id==R.id.hotelsOffer)
                    {
                        selectedFragment=new Fragment_Offer_Hotel();
                    }

                    else if(id==R.id.beachClubsOffer)
                    {
                        selectedFragment=new Fragment_Offer_BeachCLubs();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };


    public void back() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void home() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

}