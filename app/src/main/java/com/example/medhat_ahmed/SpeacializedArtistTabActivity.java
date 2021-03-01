package com.example.medhat_ahmed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class SpeacializedArtistTabActivity extends AppCompatActivity {


    private int[] tabIcons = {
            R.drawable.hairdresser_icon,
            R.drawable.makeup_icon,
            R.drawable.photographer_icon
    };

    RecyclerViewAdapterForHairdresser recyclerViewAdapterForHairdresser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speacialized_artist_tab);



        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        recyclerViewAdapterForHairdresser  = new RecyclerViewAdapterForHairdresser();

        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
        tabs.getTabAt(2).setIcon(tabIcons[2]);






    }





}