package com.example.medhat_ahmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MyBeachClubAdapter extends PagerAdapter {

    List<Integer> listImages;

    Context context;
    LayoutInflater layoutInflater;

    public MyBeachClubAdapter(List<Integer> listImages, Context context) {
        this.listImages = listImages;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.card_item_beachclub,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(listImages.get(position));
        container.addView(view);





        return view;
    }
}
