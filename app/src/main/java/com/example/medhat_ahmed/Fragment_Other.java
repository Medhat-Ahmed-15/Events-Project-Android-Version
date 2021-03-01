package com.example.medhat_ahmed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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


public class Fragment_Other extends Fragment {

    private RecyclerView myRecyclerview;
    private List<Other> listOther;

    private Other other;
    DatabaseReference dBreferenceUser;

    SwipeRefreshLayout refreshLayout;

    RecyclerViewAdapterForOther recyclerViewAdapter;

    View v;



    public Fragment_Other() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_other,container,false);


        myRecyclerview=(RecyclerView)v.findViewById(R.id.other_recyclerview);

        refreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.refreshLayout);




        //Set the RecyclerView.LayoutManager that this RecyclerView will use.
        //In contrast to other adapter-backed views such as android.widget.ListView or android.widget.GridView,
        // RecyclerView allows client code to provide custom layout arrangements for child views.
        myRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerview.setHasFixedSize(true);
        myRecyclerview.setPadding(50,50,50,50);



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                callFireBase();
                // dBreferenceUser.push().setValue(new Food("","","","","","",0));
                Toast.makeText(getContext(),"Refreshed",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);

            }
        });


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

        callFireBase();


    }


    private void filter(String text) {
        ArrayList<Other> filteredList = new ArrayList<>();
        for (Other item : listOther) {
            if (item.getEventName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapter.filterList(filteredList);
        myRecyclerview.setAdapter(recyclerViewAdapter);
    }


    public void callFireBase()
    {

        other=new Other();
        listOther = new ArrayList<>();
        dBreferenceUser= FirebaseDatabase.getInstance().getReference().child("Other_Events");

          dBreferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    other=ds.getValue(Other.class);


                    listOther.add(other);




                }
                recyclerViewAdapter=new RecyclerViewAdapterForOther(getContext(),listOther);
                myRecyclerview.setAdapter(recyclerViewAdapter);



            }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }


          });


    }
}
