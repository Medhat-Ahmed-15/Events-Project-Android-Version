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

public class Photographer_Fragment extends Fragment {



    private RecyclerView photographer_recyclerview;
    private List<Photographer> listphotographer;

    private Photographer photographer;

    EditText editText;

    DatabaseReference photographerdBreference= FirebaseDatabase.getInstance().getReference("Photographer");

    int[] photographers ={
            R.drawable.studio_farah,
            R.drawable.studio_ghazal

    };


    int[] photographerStyles ={
            R.drawable.studio_farah_style1,
            R.drawable.studio_farah_style2,
            R.drawable.studio_farah_style3,
            R.drawable.studio_farah_style4
    };


    RecyclerViewAdapterForPhotographer recyclerViewAdapterPhotographer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.photographer_layout, container, false);



        photographer_recyclerview=(RecyclerView)v.findViewById(R.id.photographer_recyclerview);


        recyclerViewAdapterPhotographer=new RecyclerViewAdapterForPhotographer(getContext(),listphotographer);
        photographer_recyclerview.setAdapter(recyclerViewAdapterPhotographer);

        photographer_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        photographer_recyclerview.setHasFixedSize(true);
        photographer_recyclerview.setPadding(0,50,0,0);


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
        ArrayList<Photographer> filteredList = new ArrayList<>();
        for (Photographer item : listphotographer) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterPhotographer.filterList(filteredList);
        photographer_recyclerview.setAdapter(recyclerViewAdapterPhotographer);
    }


    public void addFirebase()
    {
        photographer=new Photographer();


        photographer.setStyleImage1(photographerStyles[0]);
        photographer.setStyleImage2(photographerStyles[1]);
        photographer.setStyleImage3(photographerStyles[2]);
        photographer.setStyleImage4(photographerStyles[3]);
        photographer.setImage(photographers[0]);
        photographer.setName("Studio Farah");
        photographer.setNumber("01003933339");
        photographer.setWebsite("empty");
        photographer.setInstagram_page("empty");
        photographer.setLocationLongitude("empty");
        photographer.setLocationLatitude("empty");
        photographer.setFacebook_page("https://www.facebook.com/photofarah/");
        photographerdBreference.push().setValue(photographer);




        photographer.setStyleImage1(photographerStyles[0]);
        photographer.setStyleImage2(photographerStyles[1]);
        photographer.setStyleImage3(photographerStyles[2]);
        photographer.setStyleImage4(photographerStyles[3]);
        photographer.setImage(photographers[1]);
        photographer.setName("Studio Ghazal");
        photographer.setNumber("03 5922745");
        photographer.setWebsite("empty");
        photographer.setLocationLongitude("empty");
        photographer.setLocationLatitude("empty");
        photographer.setInstagram_page("https://www.instagram.com/ghazal_studios/");
        photographer.setFacebook_page("https://www.facebook.com/GhazalStudio/");
        photographerdBreference.push().setValue(photographer);

    }




    public void callFireBase()
    {

        photographer=new Photographer();
        listphotographer = new ArrayList<>();

        photographerdBreference.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    photographer=ds.getValue(Photographer.class);


                    listphotographer.add(photographer);

                }
                RecyclerViewAdapterForPhotographer recyclerViewAdapterPhotographer=new RecyclerViewAdapterForPhotographer(getContext(),listphotographer);
                photographer_recyclerview.setAdapter(recyclerViewAdapterPhotographer);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }



}