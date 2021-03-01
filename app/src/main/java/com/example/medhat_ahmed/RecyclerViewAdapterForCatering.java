package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterForCatering extends RecyclerView.Adapter<RecyclerViewAdapterForCatering.MyViewHolder>  {

    static  Context catreringContext;
    List<Catering> listCatering;

    Dialog dialog;



    int clicked=1;

    public RecyclerViewAdapterForCatering(Context catreringContext, List<Catering> listCatering) {
        this.catreringContext = catreringContext;
        this.listCatering = listCatering;
    }


    public RecyclerViewAdapterForCatering()
    {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(catreringContext).inflate(R.layout.item_catering,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);

        dialog=new Dialog(catreringContext);
        dialog.setContentView(R.layout.dialog_catering);

        vHolder.Call_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                String number;
                number=listCatering.get(vHolder.getAdapterPosition()).getNumber();

                if(number.equals("empty")){
                    Toast.makeText(catreringContext,"Not Available currently",Toast.LENGTH_LONG).show();

                }

                //todo
                else {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    catreringContext.startActivity(callIntent);
                }



            }
        });



        vHolder.show_menu_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                ImageView menu_imag1 = (ImageView) dialog.findViewById(R.id.menu_imag1);
                ImageView menu_imag2 = (ImageView) dialog.findViewById(R.id.menu_imag2);


                menu_imag1.setImageResource(listCatering.get(vHolder.getAdapterPosition()).getMenuPt1());
                menu_imag2.setImageResource(listCatering.get(vHolder.getAdapterPosition()).getMenuPt2());

                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                dialog.show();

            }
        });




        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(listCatering.get(position).getName());

        holder.image.setImageResource(listCatering.get(position).getRestaurantImage());

    }

    @Override
    public int getItemCount() {
        return listCatering.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }


    public void filterList(ArrayList<Catering> filteredList) {
        listCatering = filteredList;
        notifyDataSetChanged();
    }




    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView image;
        TextView name;
        Button show_menu_button;
        Button Call_button;
        ConstraintLayout Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Layout = (ConstraintLayout) itemView.findViewById(R.id.Layout);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (CircleImageView) itemView.findViewById(R.id.image);
            Call_button = (Button) itemView.findViewById(R.id.Call_button);
            show_menu_button = (Button) itemView.findViewById(R.id.show_menu_button);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
