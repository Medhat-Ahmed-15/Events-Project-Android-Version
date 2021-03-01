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

public class MakeupArtist_Fragment extends Fragment {


    private RecyclerView makeupartist_recyclerview;
    private List<MakeupArtist> listMakeupArtist;

    private MakeupArtist makeupArtist;

    EditText editText;

    DatabaseReference dBreferenceMakeupArtist= FirebaseDatabase.getInstance().getReference("MakeupArtist");

    int[] makeupartists ={
            R.drawable.dina_ragheb,
            R.drawable.sara_el_key

    };


    int[] makeupArtistStyles ={

            R.drawable.dina_ragheb_style1,
            R.drawable.dina_ragheb_style2,
            R.drawable.dina_ragheb_style3,
            R.drawable.dina_ragheb_style4,

            R.drawable.sara_el_key_style1,
            R.drawable.sara_el_key_style2,
            R.drawable.sara_el_key_style3,
            R.drawable.sara_el_key_style4,
    };

    RecyclerViewAdapterForMakeupArtist recyclerViewAdapterMakeupArtist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.makeupartist_layout, container, false);




        makeupartist_recyclerview=(RecyclerView)v.findViewById(R.id.makeupartist_recyclerview);


        recyclerViewAdapterMakeupArtist=new RecyclerViewAdapterForMakeupArtist(getContext(),listMakeupArtist);
        makeupartist_recyclerview.setAdapter(recyclerViewAdapterMakeupArtist);

        makeupartist_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        makeupartist_recyclerview.setHasFixedSize(true);
        makeupartist_recyclerview.setPadding(0,50,0,0);


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
        ArrayList<MakeupArtist> filteredList = new ArrayList<>();
        for (MakeupArtist item : listMakeupArtist) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterMakeupArtist.filterList(filteredList);
         makeupartist_recyclerview.setAdapter(recyclerViewAdapterMakeupArtist);
    }


    public void addFirebase()
    {
        makeupArtist=new MakeupArtist();

        makeupArtist.setStyleImage1(makeupArtistStyles[0]);
        makeupArtist.setStyleImage2(makeupArtistStyles[1]);
        makeupArtist.setStyleImage3(makeupArtistStyles[2]);
        makeupArtist.setStyleImage4(makeupArtistStyles[3]);
        makeupArtist.setImage(makeupartists[0]);
        makeupArtist.setName("Dina Ragheb");
        makeupArtist.setNumber("08264245");
        makeupArtist.setWebsite("empty");
        makeupArtist.setLongitude("empty");
        makeupArtist.setLatitude("empty");
        makeupArtist.setFacebook("https://www.facebook.com/pages/category/Community/Dina-Ragheb-Make-up-artist-669107003134579/");
        makeupArtist.setInstagram_page("https://www.instagram.com/dinaragheb/");
        dBreferenceMakeupArtist.push().setValue(makeupArtist);






        makeupArtist.setStyleImage1(makeupArtistStyles[4]);
        makeupArtist.setStyleImage2(makeupArtistStyles[5]);
        makeupArtist.setStyleImage3(makeupArtistStyles[6]);
        makeupArtist.setStyleImage4(makeupArtistStyles[7]);
        makeupArtist.setImage(makeupartists[1]);
        makeupArtist.setName("Sara el key");
        makeupArtist.setNumber("08264245");
        makeupArtist.setWebsite("empty");
        makeupArtist.setLongitude("empty");
        makeupArtist.setLatitude("empty");
        makeupArtist.setInstagram_page("https://www.instagram.com/saraelkey/?hl=en");
        makeupArtist.setFacebook("https://www.facebook.com/SaraElkeyMakeupArtist/");
        dBreferenceMakeupArtist.push().setValue(makeupArtist);

    }




    public void callFireBase()
    {

        makeupArtist=new MakeupArtist();
        listMakeupArtist = new ArrayList<>();

        dBreferenceMakeupArtist.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    makeupArtist=ds.getValue(MakeupArtist.class);


                    listMakeupArtist.add(makeupArtist);

                }
                RecyclerViewAdapterForMakeupArtist recyclerViewAdapterMakeupArtist=new RecyclerViewAdapterForMakeupArtist(getContext(),listMakeupArtist);
                makeupartist_recyclerview.setAdapter(recyclerViewAdapterMakeupArtist);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }

}