package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForKidsShow extends RecyclerView.Adapter<RecyclerViewAdapterForKidsShow.MyViewHolder> {

    static  Context kidsShowContext;
    List<KidsShow> listKidsShow;

    Dialog dialog;





    public RecyclerViewAdapterForKidsShow(Context kidsShowContext, List<KidsShow> listKidsShow) {
        this.kidsShowContext = kidsShowContext;
        this.listKidsShow = listKidsShow;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(kidsShowContext).inflate(R.layout.item_kidsshow,parent,false);


        final MyViewHolder vHolder=new MyViewHolder(v);




        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                ShowKidsDetails(vHolder);

            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.kidsShowName.setText(listKidsShow.get(position).getKidsShowName());

        holder.kidsShowImage.setImageResource(listKidsShow.get(position).getKidsshowImage());




    }

    @Override
    public int getItemCount() {
        return listKidsShow.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }


    public void ShowKidsDetails(MyViewHolder vHolder)
    {

        Intent kidsIntent=new Intent(kidsShowContext, KidsShowDetails.class);

        kidsIntent.putExtra("videoUrl", listKidsShow.get(vHolder.getAdapterPosition()).getVideoUrl());


        kidsShowContext.startActivity(kidsIntent);

    }



    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {


        View view;

        LinearLayout Layout;
        CircleImageView kidsShowImage;
        TextView kidsShowName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Layout = (LinearLayout) itemView.findViewById(R.id.Layout);

            kidsShowImage = (CircleImageView) itemView.findViewById(R.id.kidsShowImage);

            kidsShowName = (TextView) itemView.findViewById(R.id.kidsShowName);



           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
