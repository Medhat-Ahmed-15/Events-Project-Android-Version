package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyHotelHallAdapter extends PagerAdapter {

    List<Integer> listImages;
    List<Hotels> listHotels;
    List<HotelHalls> listHotelHalls;

    String name;




    Context context;
    LayoutInflater layoutInflater;

    Dialog dialog;

    TextView data;

    int flag;



    public MyHotelHallAdapter(List<Integer> listImages,List<Hotels> listHotels, List<HotelHalls> listHotelHalls,String name,Context context)
    {
        this.listImages = listImages;
        this.listHotels = listHotels;
        this.listHotelHalls = listHotelHalls;
        this.name = name;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);


        dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_hotel_halls);




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
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = layoutInflater.inflate(R.layout.card_item_hotel_hall,container,false);


        TextView hall_number = (TextView)view.findViewById(R.id.hall_number);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(listImages.get(position));
        container.addView(view);

        int newPos=position+1;

        hall_number.setText("Hall" + " "+ newPos);

      imageView.setOnClickListener(new View.OnClickListener() {
          @SuppressLint("SetTextI18n")
          @Override
          public void onClick(View v) {


              for(int i=0;i<listHotelHalls.size();i++)
              {
                  if(listHotelHalls.get(i).getHotelName().equals(name)&&
                          listHotelHalls.get(i).getPosition()==position)
                  {
                      Toast toast = Toast.makeText(context, "Hall Name: "+listHotelHalls.get(i).getHallName() + "\n"+ "\n"+ "\n"
                              +"Maximum Number Of People: "+listHotelHalls.get(i).getHallMaxNumberOfPeople() + "\n"+ "\n"+ "\n"+
                              "Maximum Number Of Tabels: "+listHotelHalls.get(i).getHallMaxNumberOfTabels() + "\n", Toast.LENGTH_SHORT);
                      toast.setGravity(Gravity.CENTER, 0, 0);
                      LinearLayout toastLayout = (LinearLayout) toast.getView();
                      TextView toastTV = (TextView) toastLayout.getChildAt(0);
                      toastTV.setTextSize(30);
                      toastTV.setTextColor(Color.BLACK);
                      toast.show();
                  }

              }
          }});

        return view;
    }






}
