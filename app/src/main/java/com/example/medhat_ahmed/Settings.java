package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings extends AppCompatActivity {

    ImageView home_imageView;
    ImageView back_imageView;

     ScrollView Layout;

     Button change_password_button;
     Button signout_button;
     Button save_button;
     EditText newPassword_textfield;
     EditText confirmation_textfield;

    String newPassword;
    String confirmPassword;



   public String currentUser;

    private User objUser;
    private List<User> listUser;
    private List<User> listUserafterUpdate;

    DatabaseReference dBreferenceUSerInfo= FirebaseDatabase.getInstance().getReference("User_Info");
    DatabaseReference dBreferenceCurrentUser=FirebaseDatabase.getInstance().getReference("Current User");

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Layout = (ScrollView) findViewById(R.id.Layout);
        back_imageView = (ImageView) findViewById(R.id.back_imageView);
        home_imageView = (ImageView) findViewById(R.id.home_imageView);
        change_password_button = (Button) findViewById(R.id.change_password_button);
        save_button = (Button) findViewById(R.id.save_button);
        newPassword_textfield = (EditText) findViewById(R.id.newPassword_textfield);
        confirmation_textfield = (EditText) findViewById(R.id.confirmation_textfield);
        signout_button = (Button) findViewById(R.id.signout_button);
        listUserafterUpdate=new ArrayList<>();


        AnimationDrawable animationDrawable=(AnimationDrawable) Layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        save_button.setVisibility(View.INVISIBLE);
        newPassword_textfield.setVisibility(View.INVISIBLE);
        confirmation_textfield.setVisibility(View.INVISIBLE);


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
        /*
         *
         * *
         * *
         *
         */
        /******************************************************************************************/
        //BAGEEB TANY HAGA HNA KOL EL USERS EL ACCOUNTS BATA3THOM W 2AHOTAHA FAL LIST

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

            } @Override
            public void onCancelled(@NonNull DatabaseError error) {

            } });
        /******************************************************************************************/



        change_password_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo
                save_button.setVisibility(View.VISIBLE);
                newPassword_textfield.setVisibility(View.VISIBLE);
                confirmation_textfield.setVisibility(View.VISIBLE);

            }
        });


        save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo
                callFireBaseToChangePassword();
                Toast.makeText(Settings.this,"Password Changed",Toast.LENGTH_LONG).show();

            }
        });



        signout_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //todo
                callFireBaseToSignout();

            }
        });



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





    }

    /////////////

    public void callFireBaseToSignout()
    {
              dBreferenceCurrentUser.child("userID").addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                                  currentUser=snapshot.getValue().toString();
                                  snapshot.getRef().setValue("");
                                  snapshot.getRef().setValue("");
                                  home();
                                  Toast.makeText(Settings.this,"Signed Out",Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    /////////////


    public void callFireBaseToChangePassword()
    {
         newPassword=newPassword_textfield.getText().toString();
         confirmPassword=confirmation_textfield.getText().toString();

         if(newPassword.equals(confirmPassword))
         {
             for(int i=0;i<listUser.size();i++)
             {
                 if(listUser.get(i).getUserName().equals(currentUser))
                 {
                     listUser.get(i).setPassword(newPassword);

                 }

             }

             dBreferenceUSerInfo.removeValue();

             for(int i=0;i<listUser.size();i++)
             {
                 dBreferenceUSerInfo.push().setValue(listUser.get(i));
             }
             home();
         }

     else
         {
             Toast.makeText(Settings.this,"Password confirmation doesn't match Password",Toast.LENGTH_LONG).show();
         }


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