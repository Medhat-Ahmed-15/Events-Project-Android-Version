package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    String firstName;
    String secondName;
    String username;
    String email;
    String password;
    int profileImage;

    TextView firstName_textView_user;
    TextView secondName_textView_user;
    TextView email_textView_user;
    TextView userName_textView_user;
    ImageView accountImage;
    ImageView back_imageView;
    ImageView home_imageView;

    private RecyclerView userEventsFood_recyclerview;
    private RecyclerView userEventsMusic_recyclerview;
    private RecyclerView userEventsSports_recyclerview;
    private RecyclerView userEventsOther_recyclerview;



    private List<Food> theUserEventsListFood;
    private List<Music> theUserEventsListMusic;
    private List<Sports> theUserEventsListSports;
    private List<Other> theUserEventsListOther;


    DatabaseReference dBreferenceCurrentUser= FirebaseDatabase.getInstance().getReference("Current User");
    public String currentUser;

    private Food objFood;
    private List<Food> listFood;
    DatabaseReference  dBreferenceUserFood= FirebaseDatabase.getInstance().getReference("Food_Events");


    private Music objMusic;
    private List<Music> listMusic;
    DatabaseReference dBreferenceUserMusic=FirebaseDatabase.getInstance().getReference("Music_Events");


    private Sports objSports;
    private List<Sports> listSports;
    DatabaseReference dBreferenceUserSports=FirebaseDatabase.getInstance().getReference("Sports_Events");

    private Other objOther;
    private List<Other> listOther;
    DatabaseReference dBreferenceUserOther=FirebaseDatabase.getInstance().getReference("Other_Events");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


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


        firstName_textView_user=(TextView) findViewById(R.id.firstName_textView_user);
        secondName_textView_user=(TextView) findViewById(R.id.secondName_textView_user);
        email_textView_user=(TextView) findViewById(R.id.email_textView_user);
        userName_textView_user=(TextView) findViewById(R.id.userName_textView_user);
        accountImage=(ImageView) findViewById(R.id.accountImage);
        back_imageView = (ImageView) findViewById(R.id.back_imageView);
        home_imageView = (ImageView) findViewById(R.id.home_imageView);
        userEventsFood_recyclerview=(RecyclerView)findViewById(R.id.userEventsFood_recyclerview);
        userEventsSports_recyclerview=(RecyclerView)findViewById(R.id.userEventsSports_recyclerview);
        userEventsOther_recyclerview=(RecyclerView)findViewById(R.id.userEventsOther_recyclerview);
        userEventsMusic_recyclerview=(RecyclerView)findViewById(R.id.userEventsMusic_recyclerview);


        userEventsFood_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userEventsFood_recyclerview.setHasFixedSize(true);
        userEventsFood_recyclerview.setPadding(50,50,50,50);


        userEventsSports_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userEventsSports_recyclerview.setHasFixedSize(true);
        userEventsSports_recyclerview.setPadding(50,50,50,50);


        userEventsOther_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userEventsOther_recyclerview.setHasFixedSize(true);
        userEventsOther_recyclerview.setPadding(50,50,50,50);


        userEventsMusic_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        userEventsMusic_recyclerview.setHasFixedSize(true);
        userEventsMusic_recyclerview.setPadding(50,50,50,50);


        Bundle bundle = getIntent().getExtras();
        firstName = bundle.getString("firstName");
        secondName = bundle.getString("secondName");
        username = bundle.getString("username");
        email = bundle.getString("email");
        password = bundle.getString("password");
        Intent mIntent = getIntent();
        profileImage = mIntent.getIntExtra("profilePicture", 0);


        firstName_textView_user.setText("Firstname:  "+firstName);
        secondName_textView_user.setText("Lastname:  "+secondName);
        email_textView_user.setText("Email:  "+email);
        userName_textView_user.setText("ID:  "+username);
       // accountImage.setImageResource(profileImage);




        back_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });


        home_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home();
            }
        });

        callFireBase();
    }


    public void callFireBase()
    {
        /**************************************************************************************/
        objMusic=new Music();
        listMusic = new ArrayList<>();
        theUserEventsListMusic=new ArrayList<>();

        dBreferenceUserMusic.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objMusic=ds.getValue(Music.class);

                    if(objMusic.getUserID().equals(currentUser))
                    {
                        theUserEventsListMusic.add(objMusic);
                    }

                }
                RecyclerViewAdapterForTheUserEventsMusic recyclerViewAdapterMusic=
                        new RecyclerViewAdapterForTheUserEventsMusic(UserProfile.this,theUserEventsListMusic);
                userEventsMusic_recyclerview.setAdapter(recyclerViewAdapterMusic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
        /**************************************************************************************/







        /**************************************************************************************/
        objFood=new Food();
        listFood = new ArrayList<>();
        theUserEventsListFood=new ArrayList<>();

        dBreferenceUserFood.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objFood=ds.getValue(Food.class);

                    if(objFood.getUserID().equals(currentUser))
                    {
                        theUserEventsListFood.add(objFood);
                    }

                }
                RecyclerViewAdapterForTheUserEventsFood recyclerViewAdapterFood=
                        new RecyclerViewAdapterForTheUserEventsFood(UserProfile.this,theUserEventsListFood);
                userEventsFood_recyclerview.setAdapter(recyclerViewAdapterFood);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
        /**************************************************************************************/







        /**************************************************************************************/
        objSports=new Sports();
        listSports = new ArrayList<>();
        theUserEventsListSports=new ArrayList<>();

        dBreferenceUserSports.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objSports=ds.getValue(Sports.class);

                    if(objSports.getUserID().equals(currentUser))
                    {
                        theUserEventsListSports.add(objSports);
                    }


                }
                RecyclerViewAdapterForTheUserEventsSports recyclerViewAdapterSports=
                        new RecyclerViewAdapterForTheUserEventsSports(UserProfile.this,theUserEventsListSports);
                userEventsSports_recyclerview.setAdapter(recyclerViewAdapterSports);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
        /**************************************************************************************/











        /**************************************************************************************/
        objOther=new Other();
        listOther = new ArrayList<>();
        theUserEventsListOther=new ArrayList<>();

        dBreferenceUserOther.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objOther=ds.getValue(Other.class);

                    if(objOther.getUserID().equals(currentUser))
                    {
                        theUserEventsListOther.add(objOther);
                    }


                }
                RecyclerViewAdapterForTheUserEventsOther recyclerViewAdapterOther=
                        new RecyclerViewAdapterForTheUserEventsOther(UserProfile.this,theUserEventsListOther);
                userEventsOther_recyclerview.setAdapter(recyclerViewAdapterOther);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
        /**************************************************************************************/





    }





    public void back() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void home() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

}