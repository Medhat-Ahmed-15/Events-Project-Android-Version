package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Favourites extends AppCompatActivity {


    ImageView back_imageView;
    ImageView home_imageView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        back_imageView = (ImageView) findViewById(R.id.back_imageView);
        home_imageView = (ImageView) findViewById(R.id.home_imageView);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Favourites()).commit();


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



    public void back() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void home() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }




}