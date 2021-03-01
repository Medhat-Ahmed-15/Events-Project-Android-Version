package com.example.medhat_ahmed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class BeachClubs_Fragment extends Fragment {

    private RecyclerView beachclubs_recyclerview;

    private List<BeachClubs> listBeachClubs;

    private BeachClubs objBeachClubs;

    DatabaseReference dBreferenceBeachClubs= FirebaseDatabase.getInstance().getReference("BeachClubs");


    int[] beachClubs ={

            R.drawable.yacht_club,
            R.drawable.greek_club
    };

    EditText editText;

    int[] beachClubsStyles ={
            R.drawable.yacht_club_1,
            R.drawable.yacht_club_2,
            R.drawable.yacht_club_3,
            R.drawable.yacht_club_4,

            R.drawable.greek_club1,
            R.drawable.greek_club2,
            R.drawable.greek_club3,
            R.drawable.greek_club4

    };


    RecyclerViewAdapterForBeachClubs recyclerViewAdapterBeachClubs;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.beachclubs_layout, container, false);


        beachclubs_recyclerview=(RecyclerView)v.findViewById(R.id.beachclubs_recyclerview);


        recyclerViewAdapterBeachClubs=new RecyclerViewAdapterForBeachClubs(getContext(),listBeachClubs);
        beachclubs_recyclerview.setAdapter(recyclerViewAdapterBeachClubs);

        editText=(EditText) v.findViewById(R.id.editText);

        beachclubs_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        beachclubs_recyclerview.setHasFixedSize(true);
        beachclubs_recyclerview.setPadding(0,50,0,0);

        editText=(EditText) v.findViewById(R.id.editText);

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
        ArrayList<BeachClubs> filteredList = new ArrayList<>();
        for (BeachClubs item : listBeachClubs) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterBeachClubs.filterList(filteredList);
        beachclubs_recyclerview.setAdapter(recyclerViewAdapterBeachClubs);
    }


    public void addFirebase()
    {
        objBeachClubs=new BeachClubs();

        objBeachClubs.setImage(beachClubs[0]);
        objBeachClubs.setImageStyle1(beachClubsStyles[0]);
        objBeachClubs.setImageStyle2(beachClubsStyles[1]);
        objBeachClubs.setImageStyle3(beachClubsStyles[2]);
        objBeachClubs.setImageStyle4(beachClubsStyles[3]);
        objBeachClubs.setRate("⭐⭐⭐⭐⭐");
        objBeachClubs.setName("Yacht Club");
        objBeachClubs.setLocationLatitude("empty");
        objBeachClubs.setLocationLongitude("empty");
        objBeachClubs.setNumber("empty");
        objBeachClubs.setWebsite("empty");
        objBeachClubs.setInstagram("empty");
        objBeachClubs.setFacebook("empty");

        dBreferenceBeachClubs.push().setValue(objBeachClubs);




        objBeachClubs.setImage(beachClubs[1]);
        objBeachClubs.setImageStyle1(beachClubsStyles[4]);
        objBeachClubs.setImageStyle2(beachClubsStyles[5]);
        objBeachClubs.setImageStyle3(beachClubsStyles[6]);
        objBeachClubs.setImageStyle4(beachClubsStyles[7]);
        objBeachClubs.setName("Greek Club");
        objBeachClubs.setRate("⭐⭐⭐⭐");
        objBeachClubs.setLocationLatitude("empty");
        objBeachClubs.setLocationLongitude("empty");
        objBeachClubs.setNumber("empty");
        objBeachClubs.setWebsite("empty");
        objBeachClubs.setInstagram("empty");
        objBeachClubs.setFacebook("empty");

        dBreferenceBeachClubs.push().setValue(objBeachClubs);

    }


    public void callFireBase()
    {

        objBeachClubs=new BeachClubs();
        listBeachClubs = new ArrayList<>();

        dBreferenceBeachClubs.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objBeachClubs=ds.getValue(BeachClubs.class);


                    listBeachClubs.add(objBeachClubs);

                }
                RecyclerViewAdapterForBeachClubs recyclerViewAdapterBeachClubs=new RecyclerViewAdapterForBeachClubs(getContext(),listBeachClubs);
                beachclubs_recyclerview.setAdapter(recyclerViewAdapterBeachClubs);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
