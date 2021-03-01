package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DecorationsThreeButtons extends AppCompatActivity {

    private RecyclerView decorations_recyclerview;

    private List<Decorations> listdecorations;


    String type;

    private List<Decorations> listdecorationsGraduation;
    private List<Decorations> listdecorationsWedding;
    private List<Decorations> listdecorationsBirthday;


    private Decorations decorations;

    DatabaseReference dBreferenceDecorations= FirebaseDatabase.getInstance().getReference("Decorations");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorations_three_buttons);


        decorations_recyclerview=(RecyclerView)findViewById(R.id.decorations_recyclerview);

        Bundle bundle = getIntent().getExtras();
        type = bundle.getString("type");

        callFireBase();



        if(type.equals("Birthday")){
        RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(this,listdecorationsBirthday);
        decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}


        if(type.equals("Wedding")){
            RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(this,listdecorationsWedding);
            decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}

        if(type.equals("Graduation")){
            RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(this,listdecorationsGraduation);
            decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}


        decorations_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        decorations_recyclerview.setHasFixedSize(true);
        decorations_recyclerview.setPadding(50,50,50,50);




    }



    public void callFireBase()
    {

        decorations = new Decorations();
        listdecorations = new ArrayList<>();
        listdecorationsWedding=new ArrayList<>();
        listdecorationsBirthday=new ArrayList<>();
        listdecorationsGraduation=new ArrayList<>();

        dBreferenceDecorations.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for (DataSnapshot ds : snapshot.getChildren()) {

                    decorations = ds.getValue(Decorations.class);

                    if(decorations.getType().equals("Graduation"))
                    {
                        listdecorationsGraduation.add(decorations);
                    }

                    if(decorations.getType().equals("Birthday"))
                    {
                        listdecorationsBirthday.add(decorations);
                    }

                    if(decorations.getType().equals("Wedding"))
                    {
                        listdecorationsWedding.add(decorations);
                    }


                }

                if(type.equals("Birthday")){
                    RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(DecorationsThreeButtons.this,listdecorationsBirthday);
                    decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}


                if(type.equals("Wedding")){
                    RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(DecorationsThreeButtons.this,listdecorationsWedding);
                    decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}

                if(type.equals("Graduation")){
                    RecyclerViewAdapterForDecorations recyclerViewAdapterDecorations=new RecyclerViewAdapterForDecorations(DecorationsThreeButtons.this,listdecorationsGraduation);
                    decorations_recyclerview.setAdapter(recyclerViewAdapterDecorations);}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });




    }


}