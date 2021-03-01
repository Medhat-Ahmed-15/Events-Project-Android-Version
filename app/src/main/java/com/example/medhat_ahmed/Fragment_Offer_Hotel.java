package com.example.medhat_ahmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Offer_Hotel extends Fragment {

    private RecyclerView myRecyclerview;
    SwipeRefreshLayout refreshLayout;
    private List<Fragment_Offer_Data> list;

    Fragment_Offer_Data obj;

    RecyclerViewAdapterForOffer recyclerViewAdapter;


    DatabaseReference  ref= FirebaseDatabase.getInstance().getReference().child("Hotel Offers");

    View v;



    public Fragment_Offer_Hotel() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_offer_hotel,container,false);


        myRecyclerview=(RecyclerView)v.findViewById(R.id.recyclerview_offer_hotel);
        refreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.refreshLayout);


        //Set the RecyclerView.LayoutManager that this RecyclerView will use.
        //In contrast to other adapter-backed views such as android.widget.ListView or android.widget.GridView,
        // RecyclerView allows client code to provide custom layout arrangements for child views.
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerview.setHasFixedSize(true);
        myRecyclerview.setPadding(50,50,50,50);

        recyclerViewAdapter=new RecyclerViewAdapterForOffer(getContext(),list);
        myRecyclerview.setAdapter(recyclerViewAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                callFireBase();
               // dBreferenceUser.push().setValue(new Food("","","","","","",0));
                Toast.makeText(getContext(),"Refreshed",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);

            }
        });



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callFireBase();
        //addFirebase();

    }



    public void callFireBase()
    {

        obj=new Fragment_Offer_Data();
        list = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    obj=ds.getValue(Fragment_Offer_Data.class);

                    if(obj.getType().equals("hotel"))
                    {
                        list.add(obj);
                    }



                }
                recyclerViewAdapter=new RecyclerViewAdapterForOffer(getContext(),list);
                myRecyclerview.setAdapter(recyclerViewAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


    }


    public void addFirebase()
    {
        obj=new Fragment_Offer_Data();

        obj.setType("hotel");
        obj.setUrlImage("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/Images%2Fhotel_type1.jpeg?alt=media&token=7e97a656-c2f4-48ea-82b5-62fee34eef5c");
        ref.push().setValue(obj);


        obj.setType("hotel");
        obj.setUrlImage("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/Images%2Fhotel_type2.jpeg?alt=media&token=9f1fd316-9117-4867-b601-cf0d05b72dfa");
        ref.push().setValue(obj);

        obj.setType("hairdresser");
        obj.setUrlImage("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/Images%2Fhairdresser_type.jpeg?alt=media&token=8ee9fe8e-b78c-4680-8015-ae88671dfc32");
        ref.push().setValue(obj);

        obj.setType("hotel");
        obj.setUrlImage("https://firebasestorage.googleapis.com/v0/b/eventos-fdc2c.appspot.com/o/Images%2Fhotel_type3.jpeg?alt=media&token=6d65f672-e6a4-472f-b3d8-be5cd398787f");
        ref.push().setValue(obj);

    }


}
