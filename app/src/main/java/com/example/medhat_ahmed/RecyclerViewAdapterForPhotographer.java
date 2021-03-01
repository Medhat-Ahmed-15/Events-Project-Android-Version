package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForPhotographer extends RecyclerView.Adapter<RecyclerViewAdapterForPhotographer.MyViewHolder> {

    static  Context mPhotographerContext;
    List<Photographer> photographerData;




    int clicked=1;

    public RecyclerViewAdapterForPhotographer(Context mPhotographerContext, List<Photographer> photographerData) {
        this.mPhotographerContext = mPhotographerContext;
        this.photographerData = photographerData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mPhotographerContext).inflate(R.layout.item_photographer,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.photographer_Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                ShowPhotographerDetails(vHolder);

            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.photographer_name.setText(photographerData.get(position).getName());

        holder.photographer_image.setImageResource(photographerData.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return photographerData.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }

    public void filterList(ArrayList<Photographer> filteredList) {
        photographerData = filteredList;
        notifyDataSetChanged();
    }


    public void ShowPhotographerDetails(MyViewHolder vHolder)
    {
        Intent intent=new Intent(mPhotographerContext, ShowPhotographerDetails.class);
        intent.putExtra("name", photographerData.get(vHolder.getAdapterPosition()).getName());
        intent.putExtra("number",photographerData.get(vHolder.getAdapterPosition()).getNumber());
        intent.putExtra("website", photographerData.get(vHolder.getAdapterPosition()).getWebsite());
        intent.putExtra("instagramPage", photographerData.get(vHolder.getAdapterPosition()).getInstagram_page());
        intent.putExtra("facebookPage", photographerData.get(vHolder.getAdapterPosition()).getFacebook_page());
        intent.putExtra("longitude", photographerData.get(vHolder.getAdapterPosition()).getLocationLongitude());
        intent.putExtra("latitude", photographerData.get(vHolder.getAdapterPosition()).getLocationLatitude());

        intent.putExtra("style1", photographerData.get(vHolder.getAdapterPosition()).getStyleImage1());
        intent.putExtra("style2", photographerData.get(vHolder.getAdapterPosition()).getStyleImage2());
        intent.putExtra("style3", photographerData.get(vHolder.getAdapterPosition()).getStyleImage3());
        intent.putExtra("style4", photographerData.get(vHolder.getAdapterPosition()).getStyleImage4());

        mPhotographerContext.startActivity(intent);
    }


    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView photographer_image;
        TextView photographer_name;
        ConstraintLayout photographer_Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            photographer_Layout = (ConstraintLayout) itemView.findViewById(R.id.photographer_Layout);
            photographer_name = (TextView) itemView.findViewById(R.id.photographer_name);
            photographer_image = (CircleImageView) itemView.findViewById(R.id.photographer_image);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
