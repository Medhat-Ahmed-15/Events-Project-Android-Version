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

public class RecyclerViewAdapterForMakeupArtist extends RecyclerView.Adapter<RecyclerViewAdapterForMakeupArtist.MyViewHolder> {

    static  Context mMakeupArtistContext;
    List<MakeupArtist> mDataMakeupArtist;



    public RecyclerViewAdapterForMakeupArtist(Context mMakeupArtistContext, List<MakeupArtist> mDataMakeupArtist) {
        this.mMakeupArtistContext = mMakeupArtistContext;
        this.mDataMakeupArtist = mDataMakeupArtist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mMakeupArtistContext).inflate(R.layout.item_makeupartist,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);



        vHolder.makeupartist_Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                ShowMakeupArtistDetails(vHolder);

            }
        });





        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.makeupartist_name.setText(mDataMakeupArtist.get(position).getName());

        holder.makeupartist_image.setImageResource(mDataMakeupArtist.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return mDataMakeupArtist.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }

    public void filterList(ArrayList<MakeupArtist> filteredList) {
        mDataMakeupArtist = filteredList;
        notifyDataSetChanged();
    }

    public void ShowMakeupArtistDetails(RecyclerViewAdapterForMakeupArtist.MyViewHolder vHolder)
    {
        Intent makeupArtistIntent=new Intent(mMakeupArtistContext, ShowMakeupArtistDetails.class);
        makeupArtistIntent.putExtra("name", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getName());
        makeupArtistIntent.putExtra("number",mDataMakeupArtist.get(vHolder.getAdapterPosition()).getNumber());
        makeupArtistIntent.putExtra("facebook", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getFacebook());
        makeupArtistIntent.putExtra("instagramPage", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getInstagram_page());
        makeupArtistIntent.putExtra("website", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getWebsite());

        makeupArtistIntent.putExtra("style1", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getStyleImage1());
        makeupArtistIntent.putExtra("style2", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getStyleImage2());
        makeupArtistIntent.putExtra("style3", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getStyleImage3());
        makeupArtistIntent.putExtra("style4", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getStyleImage4());
        makeupArtistIntent.putExtra("longitude", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getLongitude());
        makeupArtistIntent.putExtra("latitude", mDataMakeupArtist.get(vHolder.getAdapterPosition()).getLatitude());


        mMakeupArtistContext.startActivity(makeupArtistIntent);
    }






    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView makeupartist_image;
        TextView makeupartist_name;
        ConstraintLayout makeupartist_Layout;
        View view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            makeupartist_Layout = (ConstraintLayout) itemView.findViewById(R.id.makeupartist_Layout);
            makeupartist_name = (TextView) itemView.findViewById(R.id.makeupartist_name);
            makeupartist_image = (CircleImageView) itemView.findViewById(R.id.makeupartist_image);

           view=itemView;



        }






    }





///////////////////////////////////////////////////////////////////////////////////////////////////////


}
