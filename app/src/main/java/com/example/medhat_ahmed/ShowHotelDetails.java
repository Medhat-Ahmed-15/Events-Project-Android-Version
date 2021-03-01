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

public class ShowHotelDetails extends AppCompatActivity {

    Button hotelShowWebsite_button;
    Button  hotelShowInstagram_button;
    Button hotelShowLocation_button;
    Button hotelCall_button;
    Button hotelShowFacebook_button;
    Button showHalls_button;

    HorizontalInfiniteCycleViewPager pager;

    int style1;
    int style2;
    int style3;
    int style4;

    int hall1;
    int hall2;
    int hall3;

    TextView hotelName;


    List<Integer> listImages;

    String name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hotel_details);

        hotelShowWebsite_button=(Button)findViewById(R.id.hotelShowWebsite_button);
        hotelShowInstagram_button=(Button)findViewById(R.id.hotelShowInstagram_button);
        hotelShowLocation_button=(Button)findViewById(R.id.hotelShowLocation_button);
        hotelCall_button=(Button)findViewById(R.id.hotelCall_button);
        showHalls_button=(Button)findViewById(R.id.showHalls_button);
        hotelShowFacebook_button=(Button)findViewById(R.id.hotelShowFacebook_button);
        hotelName=(TextView) findViewById(R.id.hotelName);



         Intent mIntent = getIntent();
         style1 = mIntent.getIntExtra("style1",0);
         style2 = mIntent.getIntExtra("style2",0);
         style3 = mIntent.getIntExtra("style3",0);
         style4 = mIntent.getIntExtra("style4",0);

         hall1 = mIntent.getIntExtra("hall1",0);
         hall2 = mIntent.getIntExtra("hall2",0);
         hall3 = mIntent.getIntExtra("hall3",0);


        int images[]={style1,style2,style3,style4};

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
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


        pager = (HorizontalInfiniteCycleViewPager)findViewById(R.id.hotelSlider);
        MyHotelAdapter adapter = new MyHotelAdapter(listImages,getBaseContext());
        pager.setAdapter(adapter);


        hotelName.setText(name);


        hotelShowWebsite_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(website.equals("empty")){
                    Toast.makeText(ShowHotelDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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



        hotelShowInstagram_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(instagramPage.equals("empty")){
                    Toast.makeText(ShowHotelDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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


        hotelShowFacebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(facebookPage.equals("empty")){
                    Toast.makeText(ShowHotelDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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

        showHalls_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

             //todo

                openHallsActivity();



            }
        });


        hotelShowLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(latitude.equals("empty")||longitude.equals("empty")){
                    Toast.makeText(ShowHotelDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
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




        hotelCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.equals("empty")){
                    Toast.makeText(ShowHotelDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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


    public void openHallsActivity(){

        Intent hotelIntent=new Intent(this, HotelHallsActivity.class);


        hotelIntent.putExtra("hall1", hall1);
        hotelIntent.putExtra("hall2", hall2);
        hotelIntent.putExtra("hall3", hall3);
        hotelIntent.putExtra("name", name);


        startActivity(hotelIntent);
    }


}