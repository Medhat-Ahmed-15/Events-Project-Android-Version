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

public class ShowHairdresserDetails extends AppCompatActivity {

    Button hairdresserShowWebsite_button;
    Button  hairdresserShowInstagram_button;
    Button hairdresserShowLocation_button;
    Button hairdresserCall_button;
    Button hairdresserShowFacebook_button;

    TextView hairdresserName;

    ViewFlipper hairdresserImageSliderViewFlipper;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__hairdresser__details);

        hairdresserShowWebsite_button=(Button)findViewById(R.id.hairdresserShowWebsite_button);
        hairdresserShowInstagram_button=(Button)findViewById(R.id.hairdresserShowInstagram_button);
        hairdresserShowLocation_button=(Button)findViewById(R.id.hairdresserShowLocation_button);
        hairdresserCall_button=(Button)findViewById(R.id.hairdresserCall_button);
        hairdresserShowFacebook_button=(Button)findViewById(R.id.hairdresserShowFacebook_button);
        hairdresserImageSliderViewFlipper=(ViewFlipper) findViewById(R.id.hairdresserImageSliderViewFlipper);
        hairdresserName=(TextView) findViewById(R.id.hairdresserName);



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




        hairdresserName.setText(name);

        for(int i=0;i<4;i++)
        {
            flipperImages(images[i]);
        }


        hairdresserShowWebsite_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(website.equals("empty")){
                    Toast.makeText(ShowHairdresserDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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



        hairdresserShowInstagram_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(instagramPage.equals("empty")){
                    Toast.makeText(ShowHairdresserDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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


        hairdresserShowFacebook_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(facebookPage.equals("empty")){
                    Toast.makeText(ShowHairdresserDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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




        hairdresserShowLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(latitude.equals("empty")||longitude.equals("empty")){
                    Toast.makeText(ShowHairdresserDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
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




        hairdresserCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.equals("empty")){
                    Toast.makeText(ShowHairdresserDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();

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

        hairdresserImageSliderViewFlipper.addView(imageView);
        hairdresserImageSliderViewFlipper.setFlipInterval(4000);//4 sec
        hairdresserImageSliderViewFlipper.setAutoStart(true);

        //animations
        hairdresserImageSliderViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        hairdresserImageSliderViewFlipper.setOutAnimation(this, android.R.anim.fade_out);


    }
}