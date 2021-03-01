package com.example.medhat_ahmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class ShowBeachClubsDetails extends AppCompatActivity {

    Button beachClubsShowWebsite_button;
    Button beachClubsShowInstagram_button;
    Button beachClubsShowLocation_button;
    Button beachClubsCall_button;
    Button beachClubsShowFacebook_button;

    HorizontalInfiniteCycleViewPager beachClubsSlider;

    int style1;
    int style2;
    int style3;
    int style4;


    TextView beachClubsName;


    List<Integer> listImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_beach_clubs_details);

        beachClubsShowWebsite_button=(Button)findViewById(R.id.beachClubsShowWebsite_button);
        beachClubsShowInstagram_button=(Button)findViewById(R.id.beachClubsShowInstagram_button);
        beachClubsShowLocation_button=(Button)findViewById(R.id.beachClubsShowLocation_button);
        beachClubsCall_button=(Button)findViewById(R.id.beachClubsCall_button);
        beachClubsShowFacebook_button=(Button)findViewById(R.id.beachClubsShowFacebook_button);
        beachClubsName=(TextView) findViewById(R.id.beachClubsName);



        Intent mIntent = getIntent();
        style1 = mIntent.getIntExtra("style1",0);
        style2 = mIntent.getIntExtra("style2",0);
        style3 = mIntent.getIntExtra("style3",0);
        style4 = mIntent.getIntExtra("style4",0);




        int images[]={style1,style2,style3,style4};

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        final String number = bundle.getString("number");
        final String website = bundle.getString("website");
        final String instagramPage = bundle.getString("instagramPage");
        final String facebookPage = bundle.getString("facebookPage");
        final String longitude = bundle.getString("longitude");
        final String latitude = bundle.getString("latitude");




        listImages=new ArrayList<>();

        listImages.add(style1);
        listImages.add(style2);
        listImages.add(style3);
        listImages.add(style4);


        beachClubsSlider = (HorizontalInfiniteCycleViewPager)findViewById(R.id.beachClubsSlider);
        MyBeachClubAdapter adapter = new MyBeachClubAdapter(listImages,getBaseContext());
        beachClubsSlider.setAdapter(adapter);


        beachClubsName.setText(name);


        beachClubsShowWebsite_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(website.equals("empty")){
                    Toast.makeText(ShowBeachClubsDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {

                    String url = website;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }



            }
        });



        beachClubsShowInstagram_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(instagramPage.equals("empty")){
                    Toast.makeText(ShowBeachClubsDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {

                    String url = instagramPage;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

            }
        });


        beachClubsShowFacebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(facebookPage.equals("empty")){
                    Toast.makeText(ShowBeachClubsDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {

                    String url = facebookPage;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

            }
        });




        beachClubsShowLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(latitude.equals("empty")||longitude.equals("empty")){
                    Toast.makeText(ShowBeachClubsDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
                }



                //todo
                else {
                    Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?z=17");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

            }
        });




        beachClubsCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.equals("empty")){
                    Toast.makeText(ShowBeachClubsDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }


            }
        });




    }




}