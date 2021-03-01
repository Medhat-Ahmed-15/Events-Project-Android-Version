package com.example.medhat_ahmed;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class KidsShowDetails extends AppCompatActivity {

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    int flag=0;
    String videoUrl;

    VideoView video;
    TextView shakeText;
    GifImageView shakeGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_show_details);

        shakeText = (TextView) findViewById(R.id.shakeText);
        shakeGif = (GifImageView) findViewById(R.id.shakeGif);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;


        Bundle bundle = getIntent().getExtras();
         videoUrl = bundle.getString("videoUrl");


           video = (VideoView) findViewById(R.id.video);


                Uri uri=Uri.parse(videoUrl);
                video.setVideoURI(uri);



                MediaController mediaController=new MediaController(this);

                mediaController.setAnchorView(video);

                video.setMediaController(mediaController);

                //video.start();
                //video.pause();



    }

    //SHAKE

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                if(videoUrl.equals("empty"))
                {
                    Toast.makeText(KidsShowDetails.this,"Not Available currently",Toast.LENGTH_LONG).show();
                }
                else
                    {

                    PlayOrPauseVideo();
                }

            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void PlayOrPauseVideo() {


        shakeGif.setVisibility(View.INVISIBLE);
        shakeText.setVisibility(View.INVISIBLE);

        if(flag==0)
        {
        video.start();
        flag=1;

        }

        else if(flag==1)
        {
            video.pause();
            flag=0;
        }


    }

    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }


}