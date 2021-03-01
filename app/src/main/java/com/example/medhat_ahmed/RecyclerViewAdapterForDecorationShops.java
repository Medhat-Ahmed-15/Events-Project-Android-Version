package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForDecorationShops extends RecyclerView.Adapter<RecyclerViewAdapterForDecorationShops.MyViewHolder> {

    static  Context decorationSHopContext;
    List<DecorationShops> listDecorationShops;

    DecorationShops decorationShops;

    DatabaseReference dBreferenceDecorationsShops= FirebaseDatabase.getInstance().getReference("Decoration Shops");


    public RecyclerViewAdapterForDecorationShops(Context decorationSHopContext, List<DecorationShops> listDecorationShops) {
        this.decorationSHopContext = decorationSHopContext;
        this.listDecorationShops = listDecorationShops;
    }


    public RecyclerViewAdapterForDecorationShops()
    {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(decorationSHopContext).inflate(R.layout.item_decoration_shop,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                    Toast.makeText(decorationSHopContext,"Number:  "+listDecorationShops.get(vHolder.getAdapterPosition()).getNumber(),Toast.LENGTH_LONG).show();




            }
        });




        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText(listDecorationShops.get(position).getName());

        holder.image.setImageResource(listDecorationShops.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return listDecorationShops.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }









    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView image;
        TextView name;
        ConstraintLayout Layout;
        Button Call_button;
        RatingBar ratingBar;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Layout = (ConstraintLayout) itemView.findViewById(R.id.Layout);
            name = (TextView) itemView.findViewById(R.id.name);
            image =  itemView.findViewById(R.id.image);
            Call_button = (Button) itemView.findViewById(R.id.Call_button);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
