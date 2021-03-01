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

public class Catering_Fragment extends Fragment {

    private RecyclerView catering_recyclerview;

    private List<Catering> listCatering;

    private Catering objCatering;

    ConstraintLayout Layout;

    DatabaseReference dBreferenceCatering= FirebaseDatabase.getInstance().getReference("Catering");

    EditText editText;

    int[] restaurants ={

           R.drawable.bouche_restaurant_3,
            R.drawable.mann_restaurant_2,
            R.drawable.moucha_restaurant_1
    };


    int[] menus ={

            R.drawable.bouche_menu1,
            R.drawable.bouche_menu2,
            R.drawable.manna_menu1,
            R.drawable.manna_menu2,
            R.drawable.moucha_menu1,
            R.drawable.moucha_menu2

    };


    RecyclerViewAdapterForCatering recyclerViewAdapterCatering;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.catering_layout, container, false);


        catering_recyclerview=(RecyclerView)v.findViewById(R.id.catering_recyclerview);

        recyclerViewAdapterCatering=new RecyclerViewAdapterForCatering(getContext(),listCatering);
        catering_recyclerview.setAdapter(recyclerViewAdapterCatering);

        Layout=(ConstraintLayout) v.findViewById(R.id.Layout);

        editText=(EditText) v.findViewById(R.id.editText);

        catering_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        catering_recyclerview.setHasFixedSize(true);
        catering_recyclerview.setPadding(50,50,50,50);

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
        ArrayList<Catering> filteredList = new ArrayList<>();
        for (Catering item : listCatering) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterCatering.filterList(filteredList);
        catering_recyclerview.setAdapter(recyclerViewAdapterCatering);
    }


    public void addFirebase()
    {
        objCatering=new Catering();

        objCatering.setRestaurantImage(restaurants[0]);
        objCatering.setName("Bouche Catering");
        objCatering.setNumber("03 478865");
        objCatering.setMenuPt1(menus[0]);
        objCatering.setMenuPt2(menus[1]);
        dBreferenceCatering.push().setValue(objCatering);




        objCatering.setRestaurantImage(restaurants[1]);
        objCatering.setName("MAnna Catering");
        objCatering.setNumber("03 478865");
        objCatering.setMenuPt1(menus[2]);
        objCatering.setMenuPt2(menus[3]);
        dBreferenceCatering.push().setValue(objCatering);




        objCatering.setRestaurantImage(restaurants[2]);
        objCatering.setName("Mouche Catering");
        objCatering.setNumber("03 478865");
        objCatering.setMenuPt1(menus[4]);
        objCatering.setMenuPt2(menus[5]);
        dBreferenceCatering.push().setValue(objCatering);


    }


    public void callFireBase()
    {

        objCatering=new Catering();
        listCatering = new ArrayList<>();

        dBreferenceCatering.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objCatering=ds.getValue(Catering.class);


                    listCatering.add(objCatering);

                }
                recyclerViewAdapterCatering=new RecyclerViewAdapterForCatering(getContext(),listCatering);
                catering_recyclerview.setAdapter(recyclerViewAdapterCatering);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }


}
