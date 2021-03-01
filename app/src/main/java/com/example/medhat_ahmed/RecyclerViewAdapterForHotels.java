package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForHotels extends RecyclerView.Adapter<RecyclerViewAdapterForHotels.MyViewHolder> {

    static  Context hotelsContext;
    List<Hotels> listHotels;




    int clicked=1;

    public RecyclerViewAdapterForHotels(Context hotelsContext, List<Hotels> listHotels) {
        this.hotelsContext = hotelsContext;
        this.listHotels = listHotels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(hotelsContext).inflate(R.layout.item_hotel,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.hotel_Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                ShowHotelsDetails(vHolder);


            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.hotel_name.setText(listHotels.get(position).getName());

        holder.hotel_image.setImageResource(listHotels.get(position).getImage());

        holder.hotel_rate.setText(listHotels.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return listHotels.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }

    public void filterList(ArrayList<Hotels> filteredList) {
        listHotels = filteredList;
        notifyDataSetChanged();
    }


    public void ShowHotelsDetails(RecyclerViewAdapterForHotels.MyViewHolder vHolder)
    {
        Intent hotelIntent=new Intent(hotelsContext, ShowHotelDetails.class);
        hotelIntent.putExtra("name", listHotels.get(vHolder.getAdapterPosition()).getName());
        hotelIntent.putExtra("number",listHotels.get(vHolder.getAdapterPosition()).getNumber());
        hotelIntent.putExtra("website", listHotels.get(vHolder.getAdapterPosition()).getWebsite());
        hotelIntent.putExtra("instagramPage", listHotels.get(vHolder.getAdapterPosition()).getInstagram());
        hotelIntent.putExtra("facebookPage", listHotels.get(vHolder.getAdapterPosition()).getFacebook());
        hotelIntent.putExtra("longitude", listHotels.get(vHolder.getAdapterPosition()).getLocationLongitude());
        hotelIntent.putExtra("latitude", listHotels.get(vHolder.getAdapterPosition()).getLocationLatitude());



        hotelIntent.putExtra("hall1", listHotels.get(vHolder.getAdapterPosition()).getHall1());
        hotelIntent.putExtra("hall2", listHotels.get(vHolder.getAdapterPosition()).getHall2());
        hotelIntent.putExtra("hall3", listHotels.get(vHolder.getAdapterPosition()).getHall3());

        hotelIntent.putExtra("style1", listHotels.get(vHolder.getAdapterPosition()).getImageStyle1());
        hotelIntent.putExtra("style2", listHotels.get(vHolder.getAdapterPosition()).getImageStyle2());
        hotelIntent.putExtra("style3", listHotels.get(vHolder.getAdapterPosition()).getImageStyle3());
        hotelIntent.putExtra("style4", listHotels.get(vHolder.getAdapterPosition()).getImageStyle4());

        hotelsContext.startActivity(hotelIntent);
    }




    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView hotel_image;
        TextView hotel_name;
        TextView hotel_rate;
        ConstraintLayout hotel_Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hotel_Layout = (ConstraintLayout) itemView.findViewById(R.id.hotel_Layout);
            hotel_name = (TextView) itemView.findViewById(R.id.hotel_name);
            hotel_rate = (TextView) itemView.findViewById(R.id.hotel_rate);
            hotel_image =  itemView.findViewById(R.id.hotel_image);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
