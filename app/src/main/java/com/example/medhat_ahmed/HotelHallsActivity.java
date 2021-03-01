package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HotelHallsActivity extends AppCompatActivity {


    HorizontalInfiniteCycleViewPager hotelHallSlider;
    List<Integer> listImages;
    List<Hotels> listHotels;
    List<HotelHalls> listHotelHalls;


    int hall1;
    int hall2;
    int hall3;

    String name;

    String hall1details;
    String hall2details;
    String hall3details;


    private Hotels objHotels;
    private HotelHalls objHotelHalls;

    DatabaseReference dBreferenceHotels= FirebaseDatabase.getInstance().getReference("Hotels");

    DatabaseReference dBreferenceHotelHalls= FirebaseDatabase.getInstance().getReference("HotelHalls");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_halls);


        addFirebase();
        callFireBase();


        Intent mIntent = getIntent();

        hall1 = mIntent.getIntExtra("hall1",0);
        hall2 = mIntent.getIntExtra("hall2",0);
        hall3 = mIntent.getIntExtra("hall3",0);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        hall1details = bundle.getString("hall1details");
        hall2details = bundle.getString("hall2details");
        hall3details = bundle.getString("hall3details");




        listImages=new ArrayList<>();

        listImages.add(hall1);
        listImages.add(hall2);
        listImages.add(hall3);


        hotelHallSlider = (HorizontalInfiniteCycleViewPager)findViewById(R.id.hotelHallSlider);

        MyHotelHallAdapter adapter = new MyHotelHallAdapter(listImages,listHotels,listHotelHalls,name,getBaseContext());
        hotelHallSlider.setAdapter(adapter);



    }



    public void callFireBase()
    {

        objHotels=new Hotels();
        objHotelHalls=new HotelHalls();

        listHotels = new ArrayList<>();
        listHotelHalls = new ArrayList<>();

        dBreferenceHotels.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objHotels=ds.getValue(Hotels.class);


                    listHotels.add(objHotels);

                }

                MyHotelHallAdapter adapter = new MyHotelHallAdapter(listImages,listHotels,listHotelHalls,name,getBaseContext());

                hotelHallSlider.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        //todo
        //Call Hotel Halls Table
        dBreferenceHotelHalls.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objHotelHalls=ds.getValue(HotelHalls.class);


                    listHotelHalls.add(objHotelHalls);

                }

                MyHotelHallAdapter adapter = new MyHotelHallAdapter(listImages,listHotels,listHotelHalls,name,getBaseContext());

                hotelHallSlider.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

    public void addFirebase()
    {
        objHotelHalls=new HotelHalls();

        objHotelHalls.setHotelName("Four Seasons");
        objHotelHalls.setHallName("Winter Beach");
        objHotelHalls.setHallMaxNumberOfTabels("200");
        objHotelHalls.setHallMaxNumberOfPeople("100");
        objHotelHalls.setPosition(0);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);


        objHotelHalls.setHotelName("Four Seasons");
        objHotelHalls.setHallName("Royal Hall");
        objHotelHalls.setHallMaxNumberOfTabels("2000");
        objHotelHalls.setHallMaxNumberOfPeople("1000");
        objHotelHalls.setPosition(1);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);


        objHotelHalls.setHotelName("Four Seasons");
        objHotelHalls.setHallName("Grand Hall");
        objHotelHalls.setHallMaxNumberOfTabels("100");
        objHotelHalls.setHallMaxNumberOfPeople("50");
        objHotelHalls.setPosition(2);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);



        objHotelHalls.setHotelName("Sheraton");
        objHotelHalls.setHallName("Summer Beach");
        objHotelHalls.setHallMaxNumberOfTabels("2000");
        objHotelHalls.setHallMaxNumberOfPeople("1000");
        objHotelHalls.setPosition(0);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);



        objHotelHalls.setHotelName("Sheraton");
        objHotelHalls.setHallName("Tulip Hall");
        objHotelHalls.setHallMaxNumberOfTabels("500");
        objHotelHalls.setHallMaxNumberOfPeople("250");
        objHotelHalls.setPosition(1);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);



        objHotelHalls.setHotelName("Sheraton");
        objHotelHalls.setHallName("Garden Hall");
        objHotelHalls.setHallMaxNumberOfTabels("1000");
        objHotelHalls.setHallMaxNumberOfPeople("500");
        objHotelHalls.setPosition(2);
        dBreferenceHotelHalls.push().setValue(objHotelHalls);



    }



}