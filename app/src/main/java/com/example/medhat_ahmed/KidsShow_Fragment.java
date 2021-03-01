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

public class KidsShow_Fragment extends Fragment {

    private RecyclerView kidsShow_recyclerview;

    private List<KidsShow> listKidsShow;

    private KidsShow kidsShow;

    DatabaseReference dBreferenceKidsShow= FirebaseDatabase.getInstance().getReference("KidsShow");


    int[] kidsShowImages ={

            R.drawable.mohamed_el_soghayr,
            R.drawable.tarek_el_soghayr


    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kidsshow_layout, container, false);


        kidsShow_recyclerview=(RecyclerView)v.findViewById(R.id.kidsShow_recyclerview);

        listKidsShow=new ArrayList<>();

        RecyclerViewAdapterForKidsShow recyclerViewAdapterForKidsShow=new RecyclerViewAdapterForKidsShow(getContext(),listKidsShow);
        kidsShow_recyclerview.setAdapter(recyclerViewAdapterForKidsShow);



        kidsShow_recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));



        kidsShow_recyclerview.setHasFixedSize(true);
        kidsShow_recyclerview.setPadding(0,50,0,0);




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
        kidsShow=new KidsShow();

        kidsShow.setKidsShowName("Kids Zone");
        kidsShow.setNumber("03 4578422");
        kidsShow.setVideoUrl("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/video-1610994842.mp4?alt=media&token=561c9ceb-3456-4729-aab9-da9d8e37ec14");
        kidsShow.setKidsshowImage(R.drawable.kids_1);
        dBreferenceKidsShow.push().setValue(kidsShow);


        kidsShow.setKidsShowName("Primark kids");
        kidsShow.setNumber("03 3838493");
        kidsShow.setVideoUrl("empty");
        kidsShow.setKidsshowImage(R.drawable.kids_2);
        dBreferenceKidsShow.push().setValue(kidsShow);

        kidsShow.setKidsShowName("Kids 1");
        kidsShow.setNumber("03 74833749");
        kidsShow.setVideoUrl("empty");
        kidsShow.setKidsshowImage(R.drawable.kids_3);
        dBreferenceKidsShow.push().setValue(kidsShow);

        kidsShow.setKidsShowName("Pl Kids");
        kidsShow.setNumber("03 7348293");
        kidsShow.setVideoUrl("empty");
        kidsShow.setKidsshowImage(R.drawable.kids_4);
        dBreferenceKidsShow.push().setValue(kidsShow);





    }


    public void callFireBase()
    {

        kidsShow=new KidsShow();
        listKidsShow = new ArrayList<>();

        dBreferenceKidsShow.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    kidsShow=ds.getValue(KidsShow.class);


                    listKidsShow.add(kidsShow);

                }


                RecyclerViewAdapterForKidsShow recyclerViewAdapterForKidsShow=new RecyclerViewAdapterForKidsShow(getContext(),listKidsShow);
                kidsShow_recyclerview.setAdapter(recyclerViewAdapterForKidsShow);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
