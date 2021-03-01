package com.example.medhat_ahmed;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hotels_Fragment extends Fragment {

    private RecyclerView hotels_recyclerview;

    private List<Hotels> listHotels;

    private Hotels objHotels;

    ConstraintLayout Layout;

    DatabaseReference dBreferenceHotels= FirebaseDatabase.getInstance().getReference("Hotels");

    EditText editText;

    int[] hotels ={

            R.drawable.sheraton,
            R.drawable.fourseasons
    };


    int[] hotelStyles ={

            R.drawable.sheraton1,
            R.drawable.sheraton2,
            R.drawable.sheraton3,
            R.drawable.sheraton4,

            R.drawable.fourseasons_1,
            R.drawable.fourseasons_2,
            R.drawable.fourseasons_3,
            R.drawable.fourseasons_4



    };


    int[] hotelHalls ={


            R.drawable.sheraton_hall1,
            R.drawable.sheraton_hall2,
            R.drawable.sheraton_hall3,

            R.drawable.fourseasons_hall1,
            R.drawable.fourseasons_hall2,
            R.drawable.fourseasons_hall3


    };

    RecyclerViewAdapterForHotels recyclerViewAdapterHotels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hotels_layout, container, false);


        hotels_recyclerview=(RecyclerView)v.findViewById(R.id.hotels_recyclerview);

        recyclerViewAdapterHotels=new RecyclerViewAdapterForHotels(getContext(),listHotels);
        hotels_recyclerview.setAdapter(recyclerViewAdapterHotels);

        Layout=(ConstraintLayout) v.findViewById(R.id.Layout);



        hotels_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        hotels_recyclerview.setHasFixedSize(true);
        hotels_recyclerview.setPadding(0,50,0,0);

        EditText editText=(EditText) v.findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filter(s.toString());

            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addFirebase();
        callFireBase();


    }

    private void filter(String text) {
        ArrayList<Hotels> filteredList = new ArrayList<>();
        for (Hotels item : listHotels) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterHotels.filterList(filteredList);
        hotels_recyclerview.setAdapter(recyclerViewAdapterHotels);
    }


    public void addFirebase()
    {
        objHotels=new Hotels();

        objHotels.setImage(hotels[0]);
        objHotels.setImageStyle1(hotelStyles[0]);
        objHotels.setImageStyle2(hotelStyles[1]);
        objHotels.setImageStyle3(hotelStyles[2]);
        objHotels.setImageStyle4(hotelStyles[3]);
        objHotels.setRate("⭐⭐⭐⭐");
        objHotels.setName("Sheraton");


        objHotels.setHall1(hotelHalls[0]);
        objHotels.setHall2(hotelHalls[1]);
        objHotels.setHall3(hotelHalls[2]);

        objHotels.setLocationLatitude("31.2812");
        objHotels.setLocationLongitude(" 30.0115");
        objHotels.setNumber("035480550");
        objHotels.setWebsite("https://sheraton.marriott.com/");
        objHotels.setInstagram("https://www.instagram.com/sheratonhotels/?hl=en");
        objHotels.setFacebook("https://www.facebook.com/Sheraton/");

        dBreferenceHotels.push().setValue(objHotels);




        objHotels.setImage(hotels[1]);
        objHotels.setImageStyle1(hotelStyles[4]);
        objHotels.setImageStyle2(hotelStyles[5]);
        objHotels.setImageStyle3(hotelStyles[6]);
        objHotels.setImageStyle4(hotelStyles[7]);
        objHotels.setHall1(hotelHalls[3]);
        objHotels.setHall2(hotelHalls[4]);
        objHotels.setHall3(hotelHalls[5]);



        objHotels.setHall1(hotelHalls[3]);
        objHotels.setHall2(hotelHalls[4]);
        objHotels.setHall3(hotelHalls[5]);

        objHotels.setName("Four Seasons");
        objHotels.setRate("⭐⭐⭐⭐⭐");
        objHotels.setLocationLatitude("31.2453");
        objHotels.setLocationLongitude("29.9669");
        objHotels.setNumber("035818000");
        objHotels.setWebsite("https://www.fourseasons.com/");
        objHotels.setInstagram("https://www.instagram.com/fourseasons/");
        objHotels.setFacebook("https://www.facebook.com/FourSeasons");

        dBreferenceHotels.push().setValue(objHotels);

    }


    public void callFireBase()
    {

        objHotels=new Hotels();
        listHotels = new ArrayList<>();

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
                RecyclerViewAdapterForHotels recyclerViewAdapterHotels=new RecyclerViewAdapterForHotels(getContext(),listHotels);
                hotels_recyclerview.setAdapter(recyclerViewAdapterHotels);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
