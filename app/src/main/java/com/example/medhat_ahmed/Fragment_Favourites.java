package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
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


public class Fragment_Favourites extends Fragment {


    RecyclerView favourites_recyclerview_sidebar;

    SwipeRefreshLayout refreshLayout;

    List<FavouritesData> listfavourites = new ArrayList<>();

    private FavouritesData favouritesData;
    DatabaseReference dBreferenceFavouritesData;

    public String currentUser;
    DatabaseReference dBreferenceCurrentUser=FirebaseDatabase.getInstance().getReference("Current User");

    RecyclerViewAdapterForFavourites recyclerViewAdapterForFavourites;

    View vFavourites;







    public Fragment_Favourites() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vFavourites= inflater.inflate(R.layout.fragment_favourites,container,false);


        favourites_recyclerview_sidebar=(RecyclerView) vFavourites.findViewById(R.id.favourites_recyclerview_sidebar);
        favourites_recyclerview_sidebar.setLayoutManager(new LinearLayoutManager(getContext()));
        favourites_recyclerview_sidebar.setHasFixedSize(true);
        favourites_recyclerview_sidebar.setPadding(50,50,50,50);


        /******************************************************************************************/
        //BAGEEB EL 2WL HNA EL CURRENT USER  EL SIGNED IN

        dBreferenceCurrentUser.child("userID").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                currentUser=snapshot.getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /******************************************************************************************/



        refreshLayout=(SwipeRefreshLayout)vFavourites.findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                Transfer();
                Toast.makeText(getContext(),"Refreshed",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);

            }
        });

        EditText editText=(EditText) vFavourites.findViewById(R.id.editText);

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


        return vFavourites;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Transfer();

    }


    private void filter(String text) {
        ArrayList<FavouritesData> filteredList = new ArrayList<>();
        for (FavouritesData item : listfavourites) {
            if (item.getEventName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        recyclerViewAdapterForFavourites.filterList(filteredList);
        favourites_recyclerview_sidebar.setAdapter(recyclerViewAdapterForFavourites);
    }


    public void Transfer()
    {
        favouritesData=new FavouritesData();
        listfavourites = new ArrayList<>();
        dBreferenceFavouritesData= FirebaseDatabase.getInstance().getReference().child("Favourite_Events");

        dBreferenceFavouritesData.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    favouritesData =ds.getValue(FavouritesData.class);


                    if(favouritesData.getUserID().equals(currentUser))
                    {
                        listfavourites.add(favouritesData);
                    }

                }
                recyclerViewAdapterForFavourites=new RecyclerViewAdapterForFavourites(getContext(),listfavourites);
                favourites_recyclerview_sidebar.setAdapter(recyclerViewAdapterForFavourites);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
