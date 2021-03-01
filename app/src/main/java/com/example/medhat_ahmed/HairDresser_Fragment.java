package com.example.medhat_ahmed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class HairDresser_Fragment extends Fragment {


    private RecyclerView hairdresser_recyclerview;
    private List<HairDresser> listHairdresser;


    private HairDresser hairDresser;

    EditText editText;

    DatabaseReference dBreferenceHairdresser= FirebaseDatabase.getInstance().getReference("Hairdresser");

    int[] hairdressers ={

            R.drawable.mohamed_el_soghayr,
            R.drawable.tarek_el_soghayr


    };


    int[] hairdresserStyles ={
            R.drawable.tarek_el_soghayr_style1,
            R.drawable.tarek_el_soghayr_style2,
            R.drawable.tarek_el_soghayr_style3,
            R.drawable.tarek_el_soghayr_style4

    };


    RecyclerViewAdapterForHairdresser recyclerViewAdapterHairdresser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hairdresser_layout, container, false);


        hairdresser_recyclerview=(RecyclerView)v.findViewById(R.id.hairdresser_recyclerview);



         recyclerViewAdapterHairdresser=new RecyclerViewAdapterForHairdresser(getContext(),listHairdresser);
         hairdresser_recyclerview.setAdapter(recyclerViewAdapterHairdresser);



        hairdresser_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        hairdresser_recyclerview.setHasFixedSize(true);
        hairdresser_recyclerview.setPadding(0,50,0,0);


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
        ArrayList<HairDresser> filteredList =filteredList = new ArrayList<>();
        for (HairDresser item : listHairdresser) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterHairdresser.filterList(filteredList);
        hairdresser_recyclerview.setAdapter(recyclerViewAdapterHairdresser);
    }


    public void addFirebase()
    {
        hairDresser=new HairDresser();

       hairDresser.setImage(hairdressers[1]);
       hairDresser.setStyleImage1(hairdresserStyles[0]);
       hairDresser.setStyleImage2(hairdresserStyles[1]);
       hairDresser.setStyleImage3(hairdresserStyles[2]);
       hairDresser.setStyleImage4(hairdresserStyles[3]);
       hairDresser.setName("Tarek el soghayr");
       hairDresser.setLocationLatitude("31.23023863");
       hairDresser.setLocationLongitude("29.95098974");
       hairDresser.setNumber("01223425510");
       hairDresser.setWebsite("https://www.tarekelsoghayar.com/");
       hairDresser.setInstagram_page("https://www.instagram.com/tarek.elsoghayar.official/?hl=en");
       hairDresser.setFacebook_page("empty");

        dBreferenceHairdresser.push().setValue(hairDresser);




        hairDresser.setStyleImage1(hairdresserStyles[0]);
        hairDresser.setStyleImage2(hairdresserStyles[1]);
        hairDresser.setStyleImage3(hairdresserStyles[2]);
        hairDresser.setStyleImage4(hairdresserStyles[3]);
        hairDresser.setImage(hairdressers[0]);
        hairDresser.setName("Mohamed el soghayr");
        hairDresser.setNumber("010 9381 1129");
        hairDresser.setWebsite("https://www.mohamedalsagheer.com/branches");
        hairDresser.setInstagram_page("https://www.instagram.com/alsagheersalons/?hl=en");
        hairDresser.setFacebook_page("https://www.facebook.com/alsagheersalons/");

        dBreferenceHairdresser.push().setValue(hairDresser);

    }


    public void callFireBase()
    {

        hairDresser=new HairDresser();
        listHairdresser = new ArrayList<>();

        dBreferenceHairdresser.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    hairDresser=ds.getValue(HairDresser.class);


                    listHairdresser.add(hairDresser);

                }
                RecyclerViewAdapterForHairdresser recyclerViewAdapterHairdresser=new RecyclerViewAdapterForHairdresser(getContext(),listHairdresser);
                hairdresser_recyclerview.setAdapter(recyclerViewAdapterHairdresser);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }



}