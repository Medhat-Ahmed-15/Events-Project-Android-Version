package com.example.medhat_ahmed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medhat_ahmed.R;
import com.example.medhat_ahmed.RecyclerViewAdapterForPhotographer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Decorations_Fragment extends Fragment {

    Button graduation_button;
    Button birthday_button;
    Button wedding_button;

    private List<Decorations> listdecorations;

    private List<DecorationShops> listDecorationShops;



    private Decorations decorations;

    private DecorationShops objDecorationShops;

    DatabaseReference dBreferenceDecorations= FirebaseDatabase.getInstance().getReference("Decorations");

    DatabaseReference dBreferenceDecorationShops= FirebaseDatabase.getInstance().getReference("Decoration Shops");

    private RecyclerView recomendedDecorations_recyclerview;

    int[] decorationShops ={

            R.drawable.decoration_shop1,
            R.drawable.decoration_shop2,
            R.drawable.decoration_shop3
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.decorations_layout, container, false);


        recomendedDecorations_recyclerview=(RecyclerView)v.findViewById(R.id.recomendedDecorations_recyclerview);

        RecyclerViewAdapterForDecorationShops recyclerViewAdapterForDecorationShops=new RecyclerViewAdapterForDecorationShops(getContext(),listDecorationShops);
        recomendedDecorations_recyclerview.setAdapter(recyclerViewAdapterForDecorationShops);

        graduation_button=(Button)v.findViewById(R.id.graduation_button);
        birthday_button=(Button)v.findViewById(R.id.birthday_button);
        wedding_button=(Button)v.findViewById(R.id.wedding_button);




        recomendedDecorations_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        recomendedDecorations_recyclerview.setHasFixedSize(true);
        recomendedDecorations_recyclerview.setPadding(100,100,100,100);


        graduation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //todo
                openDecorationThreeButtonsActivity("Graduation");


            }
        });


        birthday_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //todo
                openDecorationThreeButtonsActivity("Birthday");

            }
        });



        wedding_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //todo
                openDecorationThreeButtonsActivity("Wedding");
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


    public void addFirebase()
    {
        decorations=new Decorations();

        decorations.setImage(decorationShops[0]);
        decorations.setName("Rose Decor");
        decorations.setNumber("01282914670");
        decorations.setType("Birthday");
        dBreferenceDecorations.push().setValue(decorations);

        decorations.setImage(decorationShops[1]);
        decorations.setName("Ballons Events");
        decorations.setNumber("01282914670");
        decorations.setType("Graduation");
        dBreferenceDecorations.push().setValue(decorations);

        decorations.setImage(decorationShops[2]);
        decorations.setName("Miro Events");
        decorations.setNumber("01282914670");
        decorations.setType("Wedding");
        dBreferenceDecorations.push().setValue(decorations);



    }


    public void callFireBase()
    {

        objDecorationShops = new DecorationShops();
        listDecorationShops = new ArrayList<>();


        dBreferenceDecorationShops.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for (DataSnapshot ds : snapshot.getChildren()) {

                    objDecorationShops = ds.getValue(DecorationShops.class);

                    listDecorationShops.add(objDecorationShops);

                }

                RecyclerViewAdapterForDecorationShops recyclerViewAdapterForDecorationShops=new RecyclerViewAdapterForDecorationShops(getContext(),listDecorationShops);
                recomendedDecorations_recyclerview.setAdapter(recyclerViewAdapterForDecorationShops);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });




    }


    public void openDecorationThreeButtonsActivity(String type)
    {
        Intent intent=new Intent(getContext(), DecorationsThreeButtons.class);

        intent.putExtra("type", type);

        startActivity(intent);
    }

}
