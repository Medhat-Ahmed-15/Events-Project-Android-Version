package com.example.medhat_ahmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Locale;

public class ShowMakeupArtistDetails extends AppCompatActivity {

    Button makeupArtistShowFacebook_button;
    Button  makeupArtistShowInstagram_button;
    Button makeupArtistCall_button;
    Button makeupArtistShowWebsite_button;
    Button makeupArtistShowLocation_button;

    TextView makeupArtistName;

    ViewFlipper makeupArtistImageSliderViewFlipper;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_makeup_artist_details);

        makeupArtistShowFacebook_button=(Button)findViewById(R.id.makeupArtistShowFacebook_button);
        makeupArtistShowInstagram_button=(Button)findViewById(R.id.makeupArtistShowInstagram_button);
        makeupArtistShowLocation_button=(Button)findViewById(R.id.makeupArtistShowLocation_button);
        makeupArtistCall_button=(Button)findViewById(R.id.makeupArtistCall_button);
        makeupArtistShowWebsite_button=(Button)findViewById(R.id.makeupArtistShowWebsite_button);
        makeupArtistImageSliderViewFlipper=(ViewFlipper) findViewById(R.id.makeupArtistImageSliderViewFlipper);
        makeupArtistName=(TextView) findViewById(R.id.makeupArtistName);



        Intent mIntent = getIntent();
        int style1 = mIntent.getIntExtra("style1",0);
        int style2 = mIntent.getIntExtra("style2",0);
        int style3 = mIntent.getIntExtra("style3",0);
        int style4 = mIntent.getIntExtra("style4",0);


        int images[]={style1,style2,style3,style4};

        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        final String number = bundle.getString("number");
        final String facebook = bundle.getString("facebook");
        final String website = bundle.getString("website");
        final String instagramPage = bundle.getString("instagramPage");
        final String longitude = bundle.getString("longitude");
        final String latitude = bundle.getString("latitude");




        makeupArtistName.setText(name);

        for(int i=0;i<4;i++)
        {
            flipperImages(images[i]);
        }


        makeupArtistShowFacebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(facebook.equals("empty")){
                    Toast.makeText(ShowMakeupArtistDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {

                    String url = facebook;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }



            }
        });



        makeupArtistShowInstagram_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(instagramPage.equals("empty")){
                    Toast.makeText(ShowMakeupArtistDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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




        makeupArtistShowWebsite_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(website.equals("empty")){
                    Toast.makeText(ShowMakeupArtistDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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





        makeupArtistCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.equals("empty")){
                    Toast.makeText(ShowMakeupArtistDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                else {

                    //todo
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);

                }


            }
        });



        makeupArtistShowLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(latitude.equals("empty")||longitude.equals("empty")){
                    Toast.makeText(ShowMakeupArtistDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
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



    }


    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        makeupArtistImageSliderViewFlipper.addView(imageView);
        makeupArtistImageSliderViewFlipper.setFlipInterval(4000);//4 sec
        makeupArtistImageSliderViewFlipper.setAutoStart(true);

        //animations
        makeupArtistImageSliderViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        makeupArtistImageSliderViewFlipper.setOutAnimation(this, android.R.anim.fade_out);


    }
}