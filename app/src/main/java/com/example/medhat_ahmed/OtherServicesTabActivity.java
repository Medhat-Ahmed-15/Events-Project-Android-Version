package com.example.medhat_ahmed;

import android.os.Bundle;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapterForOtherServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.medhat_ahmed.ui.main.SectionsPagerAdapter;

public class OtherServicesTabActivity extends AppCompatActivity {


    private int[] tabIcons = {
            R.drawable.decorations_icon,
            R.drawable.catering_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_services_tab);

        SectionsPagerAdapterForOtherServices sectionsPagerAdapterForOtherServices = new SectionsPagerAdapterForOtherServices(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setAdapter(sectionsPagerAdapterForOtherServices);

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);


        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);

    }
}