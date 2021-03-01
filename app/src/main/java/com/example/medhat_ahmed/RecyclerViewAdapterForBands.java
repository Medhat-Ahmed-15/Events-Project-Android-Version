package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForBands extends RecyclerView.Adapter<RecyclerViewAdapterForBands.MyViewHolder> {

    static  Context bandsContext;
    List<Bands> listBands;



    Dialog dialog;


    public RecyclerViewAdapterForBands(Context bandsContext, List<Bands> listBands) {
        this.bandsContext = bandsContext;
        this.listBands = listBands;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(bandsContext).inflate(R.layout.item_bands,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);

        dialog=new Dialog(bandsContext);
        dialog.setContentView(R.layout.dialog_bands);

        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                //todo


                ShowBandsDetails(vHolder);

            }
        });



        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bandName.setText(listBands.get(position).getBandName());

        holder.bandImage.setImageResource(listBands.get(position).getBandImage());

    }

    @Override
    public int getItemCount() {
        return listBands.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }


    public void ShowBandsDetails(MyViewHolder vHolder)
    {

        Intent bamdIntent=new Intent(bandsContext, BandsShowDetails.class);

        bamdIntent.putExtra("videoUrl", listBands.get(vHolder.getAdapterPosition()).getVideoUrl());


        bandsContext.startActivity(bamdIntent);

    }




    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {


        View view;

        LinearLayout Layout;
        CircleImageView bandImage;
        TextView bandName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Layout = (LinearLayout) itemView.findViewById(R.id.Layout);

            bandImage = (CircleImageView) itemView.findViewById(R.id.bandImage);

            bandName = (TextView) itemView.findViewById(R.id.bandName);



           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
