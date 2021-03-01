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

public class ShowPhotographerDetails extends AppCompatActivity {

    Button photographerShowWebsite_button;
    Button  photographerShowInstagram_button;
    Button photographerShowLocation_button;
    Button photographerCall_button;
    Button photographerShowFacebook_button;

    TextView photographerName;

    ViewFlipper photographerImageSliderViewFlipper;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photographer_details);

        photographerShowWebsite_button=(Button)findViewById(R.id.photographerShowWebsite_button);
        photographerShowInstagram_button=(Button)findViewById(R.id.photographerShowInstagram_button);
        photographerShowLocation_button=(Button)findViewById(R.id.photographerShowLocation_button);
        photographerCall_button=(Button)findViewById(R.id.photographerCall_button);
        photographerShowFacebook_button=(Button)findViewById(R.id.photographerShowFacebook_button);
        photographerImageSliderViewFlipper=(ViewFlipper) findViewById(R.id.photographerImageSliderViewFlipper);
        photographerName=(TextView) findViewById(R.id.photographerName);



        Intent mIntent = getIntent();
        int style1 = mIntent.getIntExtra("style1",0);
        int style2 = mIntent.getIntExtra("style2",0);
        int style3 = mIntent.getIntExtra("style3",0);
        int style4 = mIntent.getIntExtra("style4",0);


        int images[]={style1,style2,style3,style4};

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        final String number = bundle.getString("number");
        final String website = bundle.getString("website");
        final String instagramPage = bundle.getString("instagramPage");
        final String facebookPage = bundle.getString("facebookPage");
        final String longitude = bundle.getString("longitude");
        final String latitude = bundle.getString("latitude");




        photographerName.setText(name);

        for(int i=0;i<4;i++)
        {
            flipperImages(images[i]);
        }


        photographerShowWebsite_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo

                if(website.equals("empty")){
                    Toast.makeText(ShowPhotographerDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                else {

                    String url = website;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }



            }
        });



        photographerShowInstagram_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo
                if(instagramPage.equals("empty")){
                    Toast.makeText(ShowPhotographerDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
                }

                else {

                    String url = instagramPage;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }

            }
        });



        photographerShowFacebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo

                if(facebookPage.equals("empty")){
                    Toast.makeText(ShowPhotographerDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
                }

                else {

                    String url = facebookPage;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }

            }
        });




        photographerShowLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(latitude.equals("empty")||longitude.equals("empty")){
                    Toast.makeText(ShowPhotographerDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
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




        photographerCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.equals("empty")){
                    Toast.makeText(ShowPhotographerDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
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


    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        photographerImageSliderViewFlipper.addView(imageView);
        photographerImageSliderViewFlipper.setFlipInterval(4000);//4 sec
        photographerImageSliderViewFlipper.setAutoStart(true);

        //animations
        photographerImageSliderViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        photographerImageSliderViewFlipper.setOutAnimation(this, android.R.anim.fade_out);


    }
}