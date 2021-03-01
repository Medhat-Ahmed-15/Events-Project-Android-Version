package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class DashBoard extends AppCompatActivity {
    String n;
    Toolbar menu_toolbar;

    DrawerLayout Layout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Button addEvent_button;
    Button mangeEvent_button;
    Button checkImageButton;

    String currentUser;

    NavigationView navigationView;


    TextView userName_textView;
    TextView welcome_text;

    private User objUser;
    private List<User> listUser;
    DatabaseReference dBreferenceUSerInfo=FirebaseDatabase.getInstance().getReference().child("User_Info");
    DatabaseReference dBreferenceCurrentUser=FirebaseDatabase.getInstance().getReference().child("Current User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getWindow().setFlags(WindowManager.LayoutParams.ALPHA_CHANGED,WindowManager.LayoutParams.ALPHA_CHANGED);



        Layout = (DrawerLayout) findViewById(R.id.Layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) Layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        navigationView=(NavigationView)findViewById(R.id.navigationview);
        menu_toolbar=(Toolbar)findViewById(R.id.menu_toolbar);
        addEvent_button=(Button) findViewById(R.id.addEvent_button);
        userName_textView = (TextView) findViewById(R.id.userName_textView);
        welcome_text = (TextView) findViewById(R.id.welcome_text);
        mangeEvent_button=(Button) findViewById(R.id.mangeEvent_button);
        checkImageButton=(Button) findViewById(R.id.checkImageButton);
        welcome_text.setVisibility(View.INVISIBLE);

        setSupportActionBar(menu_toolbar);//This method sets the toolbar as the app bar for the activity


        //takes 4 paramaters the first one is the activity,next is the  drawer layout and then we have to put 2 strings first is the open,second is the close
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,Layout,R.string.open,R.string.close);//what is the open and close strings used for ??

        Layout.addDrawerListener(actionBarDrawerToggle);//what is this method used for??


        //Call syncState() from your Activity's onPostCreate to synchronize
        // the indicator with the state of the linked DrawerLayout after onRestoreInstanceState has occurred.
        //dee el bat3mlee el button el howa talat shorat taht b3d
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dee bat5alee law fee button asln


        callFireBase();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                 if(id==R.id.settings)
                {
                    openSettingsActivity();
                }

                else if(id==R.id.offers)
                {
                    openOffersActivity();
                }

                else if(id==R.id.signIn_signUp)
                {
                    openLoginActivity();
                }

                 else if(id==R.id.favourites)
                 {
                     openFavouritesActivity();
                 }


                return false;
            }
        });

        addEvent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAddEventActivity();

            }
        });


        mangeEvent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openManageEventsActivity();


            }
        });






        checkImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openCheckeventsActivity();


            }
        });




    }

    //todo
     public void callFireBase()
    {

        objUser=new User();
        listUser = new ArrayList<>();

        dBreferenceUSerInfo.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    objUser=ds.getValue(User.class);
                    listUser.add(objUser);
                }

                dBreferenceCurrentUser.child("userID").addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."

                        currentUser=(String)snapshot.getValue();

                        for (int i=0;i<listUser.size();i++)
                        {
                            if(listUser.get(i).getUserName().equals(currentUser))
                            {
                                welcome_text.setVisibility(View.VISIBLE);
                                userName_textView.setText(listUser.get(i).getFirstname() +" "+listUser.get(i).getSecondName());
                            }

                            /*if(currentUser.equals(""))
                            {
                                userName_textView.setText("");
                                welcome_text.setVisibility(View.INVISIBLE);
                            }*/
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }



    //el method dee haya el bat create el button (el 3 dots el fo2 b3d) plus brdo 2naha maynf3sh ta3ml create 2la lama yab2 fee toolbar el howa ana 3amlo el howa menu_toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);// 3ayz 2aftkr inflate dee bat3ml eih
        //menu_toolbar.setTranslationY(1925);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {// el method dee ba2 el onClick listner bata3t el items el fal tool bar
        int id=item.getItemId();

        if(id==R.id.contact)
        {
            Toast.makeText(DashBoard.this,"BUTTON CONTACT US CLICKED",Toast.LENGTH_LONG).show();
        }



        else if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {

            return  true;
        }



        return  true;

    }




    public void openLoginActivity(){
        Intent intent=new Intent(this,LoginPage.class);
        startActivity(intent);
    }

    public void openDashboardActivity(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }


    public void openCheckeventsActivity(){
        Intent intent=new Intent(this, CheckEvents.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){
        Intent intent=new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openOffersActivity(){
        Intent intent=new Intent(this, Offers.class);
        startActivity(intent);
    }


    public void openAddEventActivity(){
        Intent intent=new Intent(this, AddEvent.class);
        startActivity(intent);
    }

    public void openFavouritesActivity(){
        Intent intent=new Intent(this, Favourites.class);
        startActivity(intent);
    }


    public void openManageEventsActivity(){
        Intent intent=new Intent(this, ManageEvents.class);
        startActivity(intent);
    }



}