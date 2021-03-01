package com.example.medhat_ahmed.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.medhat_ahmed.HairDresser_Fragment;
import com.example.medhat_ahmed.MakeupArtist_Fragment;
import com.example.medhat_ahmed.Photographer_Fragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //@StringRes
    //private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new HairDresser_Fragment();
            case 1:
                return new MakeupArtist_Fragment();
            case 2:
                return new Photographer_Fragment();

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "Hairdressr";
            case 1:
                return "Makeupatrist";
            case 2:
                return "photographer";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}