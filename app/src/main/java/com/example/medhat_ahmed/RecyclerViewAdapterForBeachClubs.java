package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
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

public class RecyclerViewAdapterForBeachClubs extends RecyclerView.Adapter<RecyclerViewAdapterForBeachClubs.MyViewHolder> {

    static  Context beachClubsContext;
    List<BeachClubs> listBeachClubs;




    int clicked=1;

    public RecyclerViewAdapterForBeachClubs(Context beachClubsContext, List<BeachClubs> listBeachClubs) {
        this.beachClubsContext = beachClubsContext;
        this.listBeachClubs = listBeachClubs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(beachClubsContext).inflate(R.layout.item_beachclub,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.beachclub_Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                ShowBeachClubsDetails(vHolder);


            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.beachclub_name.setText(listBeachClubs.get(position).getName());

        holder.beachclub_image.setImageResource(listBeachClubs.get(position).getImage());

        holder.beachclub_rate.setText(listBeachClubs.get(position).getRate());
    }

    @Override
    public int getItemCount() {
        return listBeachClubs.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }

    public void filterList(ArrayList<BeachClubs> filteredList) {
        listBeachClubs = filteredList;
        notifyDataSetChanged();
    }


    public void ShowBeachClubsDetails(RecyclerViewAdapterForBeachClubs.MyViewHolder vHolder)
    {
        Intent beachClubsIntent=new Intent(beachClubsContext, ShowBeachClubsDetails.class);
        beachClubsIntent.putExtra("name", listBeachClubs.get(vHolder.getAdapterPosition()).getName());
        beachClubsIntent.putExtra("number",listBeachClubs.get(vHolder.getAdapterPosition()).getNumber());
        beachClubsIntent.putExtra("website", listBeachClubs.get(vHolder.getAdapterPosition()).getWebsite());
        beachClubsIntent.putExtra("instagramPage", listBeachClubs.get(vHolder.getAdapterPosition()).getInstagram());
        beachClubsIntent.putExtra("facebookPage", listBeachClubs.get(vHolder.getAdapterPosition()).getFacebook());
        beachClubsIntent.putExtra("longitude", listBeachClubs.get(vHolder.getAdapterPosition()).getLocationLongitude());
        beachClubsIntent.putExtra("latitude", listBeachClubs.get(vHolder.getAdapterPosition()).getLocationLatitude());

        beachClubsIntent.putExtra("style1", listBeachClubs.get(vHolder.getAdapterPosition()).getImageStyle1());
        beachClubsIntent.putExtra("style2", listBeachClubs.get(vHolder.getAdapterPosition()).getImageStyle2());
        beachClubsIntent.putExtra("style3", listBeachClubs.get(vHolder.getAdapterPosition()).getImageStyle3());
        beachClubsIntent.putExtra("style4", listBeachClubs.get(vHolder.getAdapterPosition()).getImageStyle4());

        beachClubsContext.startActivity(beachClubsIntent);
    }




    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView beachclub_image;
        TextView beachclub_name;
        TextView beachclub_rate;
        ConstraintLayout beachclub_Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            beachclub_Layout = (ConstraintLayout) itemView.findViewById(R.id.beachclub_Layout);
            beachclub_name = (TextView) itemView.findViewById(R.id.beachclub_name);
            beachclub_rate = (TextView) itemView.findViewById(R.id.beachclub_rate);
            beachclub_image =  itemView.findViewById(R.id.beachclub_image);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
