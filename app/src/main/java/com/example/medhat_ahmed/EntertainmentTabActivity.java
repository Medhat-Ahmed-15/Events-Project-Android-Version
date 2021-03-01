package com.example.medhat_ahmed;

import android.os.Bundle;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapterForEnertainment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapter;

public class EntertainmentTabActivity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.bands_icon,
            R.drawable.clown_icon
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_tab);

        SectionsPagerAdapterForEnertainment sectionsPagerAdapterForEnertainment = new SectionsPagerAdapterForEnertainment(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapterForEnertainment);

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);


        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);

    }
}