package com.example.medhat_ahmed;

import android.os.Bundle;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapterForHotelsAndSpecialPlaces;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapter;

public class HotelsAndSpecialPlacesTabActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.hotels_icon,
            R.drawable.beachclubs_icon
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_and_special_places_tab);

        SectionsPagerAdapterForHotelsAndSpecialPlaces sectionsPagerAdapter = new SectionsPagerAdapterForHotelsAndSpecialPlaces(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);



        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);

        /*FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}