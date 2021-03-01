package com.example.medhat_ahmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bands_Fragment extends Fragment {

    private RecyclerView bands_recyclerview;

    private List<Bands> listBands;

    private Bands bands;

    DatabaseReference dBreferenceBands= FirebaseDatabase.getInstance().getReference("Bands");


    int[] bandsImages ={

            R.drawable.mohamed_el_soghayr,
            R.drawable.tarek_el_soghayr


    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bands_layout, container, false);


        bands_recyclerview=(RecyclerView)v.findViewById(R.id.bands_recyclerview);

        listBands=new ArrayList<>();

        RecyclerViewAdapterForBands recyclerViewAdapterbands=new RecyclerViewAdapterForBands(getContext(),listBands);
        bands_recyclerview.setAdapter(recyclerViewAdapterbands);



        bands_recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));



        bands_recyclerview.setHasFixedSize(true);
        bands_recyclerview.setPadding(0,50,0,0);




        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addFirebase();
        callFireBase();

    }

    public void addFirebase()
    {
        bands=new Bands();

        bands.setBandName("Cairokee");
        bands.setBandImage(R.drawable.cairokee);
        bands.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/cairokee.mp4?alt=media&token=cdc998c9-b812-4513-8502-f6685d5538e3");
        dBreferenceBands.push().setValue(bands);


        bands.setBandName("West El Balad");
        bands.setBandImage(R.drawable.west_el_balad);
        bands.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/weistelbalad.mp4?alt=media&token=a5cd3dd6-221b-4dc4-89ae-2959438a6742");

        dBreferenceBands.push().setValue(bands);

        bands.setBandName("Massar Egbari");
        bands.setBandImage(R.drawable.masar_egbari);
        bands.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/balafeesh.mp4?alt=media&token=16595977-9edd-48e6-9b9c-212daccfa554");

        dBreferenceBands.push().setValue(bands);

        bands.setBandName("Sharmoofers");
        bands.setBandImage(R.drawable.sharmoofers);
        bands.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/sharmoofers.mp4?alt=media&token=c72a29ee-d25d-48f5-9255-b3914c34f6bf");
        dBreferenceBands.push().setValue(bands);

        bands.setBandName("Fouad And Mounib");
        bands.setBandImage(R.drawable.foud_mounib);
        bands.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/foudmounir_video.mp4?alt=media&token=f1ada9c6-c1c4-4891-bbd7-93003837cb3d");
        dBreferenceBands.push().setValue(bands);



    }


    public void callFireBase()
    {

        bands=new Bands();
        listBands = new ArrayList<>();

        dBreferenceBands.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    bands=ds.getValue(Bands.class);


                    listBands.add(bands);

                }


                RecyclerViewAdapterForBands recyclerViewAdapterbands=new RecyclerViewAdapterForBands(getContext(),listBands);
                bands_recyclerview.setAdapter(recyclerViewAdapterbands);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
