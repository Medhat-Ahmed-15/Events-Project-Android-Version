package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForHairdresser extends RecyclerView.Adapter<RecyclerViewAdapterForHairdresser.MyViewHolder>  {

    static  Context hairdresserContext;
    List<HairDresser> mDataHairdresser;





    int clicked=1;

    public RecyclerViewAdapterForHairdresser(Context hairdresserContext, List<HairDresser> mDataHairdresser) {
        this.hairdresserContext = hairdresserContext;
        this.mDataHairdresser = mDataHairdresser;


    }


    public RecyclerViewAdapterForHairdresser()
    {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(hairdresserContext).inflate(R.layout.item_hairdresser,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.hairdresser_Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                ShowHairDresserDetails(vHolder);


            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.hairdresser_name.setText(mDataHairdresser.get(position).getName());

        holder.hairdresser_image.setImageResource(mDataHairdresser.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mDataHairdresser.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }


    public void filterList(ArrayList<HairDresser> filteredList) {
        mDataHairdresser = filteredList;
        notifyDataSetChanged();
    }


    public void ShowHairDresserDetails(RecyclerViewAdapterForHairdresser.MyViewHolder vHolder)
    {
        Intent hairdresserIntent=new Intent(hairdresserContext, ShowHairdresserDetails.class);
        hairdresserIntent.putExtra("name", mDataHairdresser.get(vHolder.getAdapterPosition()).getName());
        hairdresserIntent.putExtra("number",mDataHairdresser.get(vHolder.getAdapterPosition()).getNumber());
        hairdresserIntent.putExtra("website", mDataHairdresser.get(vHolder.getAdapterPosition()).getWebsite());
        hairdresserIntent.putExtra("instagramPage", mDataHairdresser.get(vHolder.getAdapterPosition()).getInstagram_page());
        hairdresserIntent.putExtra("facebookPage", mDataHairdresser.get(vHolder.getAdapterPosition()).getFacebook_page());
        hairdresserIntent.putExtra("longitude", mDataHairdresser.get(vHolder.getAdapterPosition()).getLocationLongitude());
        hairdresserIntent.putExtra("latitude", mDataHairdresser.get(vHolder.getAdapterPosition()).getLocationLatitude());

        hairdresserIntent.putExtra("style1", mDataHairdresser.get(vHolder.getAdapterPosition()).getStyleImage1());
        hairdresserIntent.putExtra("style2", mDataHairdresser.get(vHolder.getAdapterPosition()).getStyleImage2());
        hairdresserIntent.putExtra("style3", mDataHairdresser.get(vHolder.getAdapterPosition()).getStyleImage3());
        hairdresserIntent.putExtra("style4", mDataHairdresser.get(vHolder.getAdapterPosition()).getStyleImage4());

        hairdresserContext.startActivity(hairdresserIntent);
    }


    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView hairdresser_image;
        TextView hairdresser_name;
        ConstraintLayout hairdresser_Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hairdresser_Layout = (ConstraintLayout) itemView.findViewById(R.id.hairdresser_Layout);
            hairdresser_name = (TextView) itemView.findViewById(R.id.hairdresser_name);
            hairdresser_image = (CircleImageView) itemView.findViewById(R.id.hairdresser_image);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
