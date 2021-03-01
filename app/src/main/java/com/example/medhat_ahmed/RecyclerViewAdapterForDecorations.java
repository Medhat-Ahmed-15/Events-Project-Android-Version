package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForDecorations extends RecyclerView.Adapter<RecyclerViewAdapterForDecorations.MyViewHolder> {

    static  Context decorationContext;
    List<Decorations> listDecorations;

    DecorationShops decorationShops;

    DatabaseReference dBreferenceDecorationsShops= FirebaseDatabase.getInstance().getReference("Decoration Shops");


    public RecyclerViewAdapterForDecorations(Context decorationContext, List<Decorations> listDecorations) {
        this.decorationContext = decorationContext;
        this.listDecorations = listDecorations;
    }


    public RecyclerViewAdapterForDecorations()
    {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(decorationContext).inflate(R.layout.item_decoration,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.Call_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String number;
                number=listDecorations.get(vHolder.getAdapterPosition()).getNumber();

                if(number.equals("empty")){
                    Toast.makeText(decorationContext,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    decorationContext.startActivity(callIntent);
                }


            }
        });


        vHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rate= (int) ratingBar.getRating();

                if(rate==5)
                {
                    decorationShops=new DecorationShops();
                    decorationShops.setImage(listDecorations.get(vHolder.getAdapterPosition()).getImage());
                    decorationShops.setName(listDecorations.get(vHolder.getAdapterPosition()).getName());
                    decorationShops.setNumber(listDecorations.get(vHolder.getAdapterPosition()).getNumber());

                    dBreferenceDecorationsShops.push().setValue(decorationShops);

                }


            }
        });




        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText(listDecorations.get(position).getName());

        holder.image.setImageResource(listDecorations.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return listDecorations.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
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
