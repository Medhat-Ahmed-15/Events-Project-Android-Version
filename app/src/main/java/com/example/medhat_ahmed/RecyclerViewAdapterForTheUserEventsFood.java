package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class RecyclerViewAdapterForTheUserEventsFood extends RecyclerView.Adapter<RecyclerViewAdapterForTheUserEventsFood.MyViewHolder>   {

    static  Context theUserEventsContext;
    List<Food> theUserEventsData;

    Dialog dialog;

    public RecyclerViewAdapterForTheUserEventsFood(Context theUserEventsContext, List<Food> theUserEventsData) {
        this.theUserEventsContext = theUserEventsContext;
        this.theUserEventsData = theUserEventsData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(theUserEventsContext).inflate(R.layout.item_the_user_events,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);

        //DIALOG INI
        dialog=new Dialog(theUserEventsContext);
        dialog.setContentView(R.layout.dialog_the_user_events);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));






        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                TextView description = (TextView) dialog.findViewById(R.id.description);
                TextView numberofGuests = (TextView) dialog.findViewById(R.id.numberofGuests);
                TextView date = (TextView) dialog.findViewById(R.id.date);
                TextView time = (TextView) dialog.findViewById(R.id.time);
                TextView location = (TextView) dialog.findViewById(R.id.location);

                description.setText("DESCRIPTION: " + theUserEventsData.get(vHolder.getAdapterPosition()).getEventDescription());
                numberofGuests.setText("NUMBER OF GUESTS: " + theUserEventsData.get(vHolder.getAdapterPosition()).getEventNumberOfGuests());
                date.setText("DATE: " + theUserEventsData.get(vHolder.getAdapterPosition()).getEventdate());
                time.setText("TIME: " + theUserEventsData.get(vHolder.getAdapterPosition()).getEventTime());
                location.setText("LOCATION: " + theUserEventsData.get(vHolder.getAdapterPosition()).getEventLocation());

                dialog.show();




            }
        });




        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.event_name.setText(theUserEventsData.get(position).getEventName());



        holder.image.setImageResource(theUserEventsData.get(position).getImage());

       // holder.Layout.setBackgroundResource(holder.images[position]);

    }

    @Override
    public int getItemCount() {
        return theUserEventsData.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }





    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView event_name;
        ImageView image;
        ConstraintLayout Layout;
        DatabaseReference dBreferenceFavourites;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            event_name = (TextView) itemView.findViewById(R.id.event_name);
            image = (ImageView) itemView.findViewById(R.id.image);
            Layout = (ConstraintLayout) itemView.findViewById(R.id.Layout);


        }



    }

///////////////////////////////////////////////////////////////////////////////////////////////////////


}
