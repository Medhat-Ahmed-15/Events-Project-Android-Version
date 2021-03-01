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

public class RecyclerViewAdapterForOffer extends RecyclerView.Adapter<RecyclerViewAdapterForOffer.MyViewHolder> {

    static  Context context;
    List<Fragment_Offer_Data> list;




    int clicked=1;

    public RecyclerViewAdapterForOffer(Context context, List<Fragment_Offer_Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(context).inflate(R.layout.item_offer,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);


        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Uri uri=Uri.parse(list.get(position).getUrlImage());

        Picasso.get().load(uri).into(holder.imageOffer);

    }

    @Override
    public int getItemCount() {
        return list.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }



    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageOffer;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            imageOffer =  itemView.findViewById(R.id.imageOffer);





        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
