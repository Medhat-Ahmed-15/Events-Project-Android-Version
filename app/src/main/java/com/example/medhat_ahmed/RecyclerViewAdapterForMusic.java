package com.example.medhat_ahmed;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RecyclerViewAdapterForMusic extends RecyclerView.Adapter<RecyclerViewAdapterForMusic.MyViewHolder> implements TimePickerDialog.OnTimeSetListener{

    static  Context mContext;
    List<Music> mData;

    Dialog dialog;
    Dialog dialogReminder;

    public String currentUser;
    DatabaseReference dBreferenceCurrentUser=FirebaseDatabase.getInstance().getReference("Current User");


    int clicked=1;

    public RecyclerViewAdapterForMusic(Context mContext, List<Music> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_music,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(v);

        //DIALOG INI
        dialog=new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_music);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        /******************************************************************************************/
        //BAGEEB EL 2WL HNA EL CURRENT USER  EL SIGNED IN

        dBreferenceCurrentUser.child("userID").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                currentUser=snapshot.getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /******************************************************************************************/




        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                TextView description = (TextView) dialog.findViewById(R.id.description);
                TextView numberofGuests = (TextView) dialog.findViewById(R.id.numberofGuests);
                TextView date = (TextView) dialog.findViewById(R.id.date);
                TextView time = (TextView) dialog.findViewById(R.id.time);
                TextView location = (TextView) dialog.findViewById(R.id.location);

                description.setText("DESCRIPTION: " + mData.get(vHolder.getAdapterPosition()).getEventDescription());
                numberofGuests.setText("NUMBER OF GUESTS: " + mData.get(vHolder.getAdapterPosition()).getEventNumberOfGuests());
                date.setText("DATE: " + mData.get(vHolder.getAdapterPosition()).getEventdate());
                time.setText("TIME: " + mData.get(vHolder.getAdapterPosition()).getEventTime());
                location.setText("LOCATION: " + mData.get(vHolder.getAdapterPosition()).getEventLocation());

                dialog.show();


            }
        });


       vHolder.event_checkbox_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {Toast.makeText(mContext,String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_LONG).show();
                if ( isChecked )
                {



                }

            }
        });


        vHolder.favorite_button_music.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(clicked==1) {

                    vHolder.favorite_button_music.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_baseline_favorite));




                    String name=mData.get(vHolder.getAdapterPosition()).getEventName();
                    String location=mData.get(vHolder.getAdapterPosition()).getEventLocation();
                    String date=mData.get(vHolder.getAdapterPosition()).getEventdate();
                    String time=mData.get(vHolder.getAdapterPosition()).getEventTime();
                    String description=mData.get(vHolder.getAdapterPosition()).getEventDescription();
                    String number=mData.get(vHolder.getAdapterPosition()).getEventNumberOfGuests();
                    int imageId=mData.get(vHolder.getAdapterPosition()).getImage();
                    int key=1;


                    vHolder.favouritesData.setEventName(name);
                    vHolder.favouritesData.setEventLocation(location);
                    vHolder.favouritesData.setEventdate(date);
                    vHolder.favouritesData.setEventTime(time);
                    vHolder.favouritesData.setEventDescription(description);
                    vHolder.favouritesData.setEventNumberOfGuests(number);
                    vHolder.favouritesData.setUserID(currentUser);
                    vHolder.favouritesData.setKey(key);
                    vHolder.favouritesData.setImage(imageId);



                    vHolder.dBreferenceFavourites.push().setValue(vHolder.favouritesData);





                    clicked=2;

                }

                 else if(clicked==2){
                    vHolder.favorite_button_music.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.ic_baseline_favorite_unclicked));
                    clicked=1;

                }
            }
        });


        vHolder.event_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if ( isChecked )
                {

                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(((FragmentActivity)mContext).getSupportFragmentManager(), "time picker");
                    Toast.makeText(mContext,"Set Your Reminder Time",Toast.LENGTH_LONG).show();


                }

            }
        });



        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.event_name_music.setText(mData.get(position).getEventName());



        holder.music_image.setImageResource(mData.get(position).getImage());

       // holder.Layout.setBackgroundResource(holder.images[position]);

    }

    @Override
    public int getItemCount() {
        return mData.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }


    public void filterList(ArrayList<Music> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        startAlarm(c);
    }





    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }


    //////////////////////////INNER CLASS///////////////////////////
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView event_name_music;
        ImageView music_image;
        CheckBox event_checkbox_music;
        ConstraintLayout Layout;
        Button favorite_button_music;
        CheckBox event_checkbox;
        DatabaseReference dBreferenceFavourites;
        FavouritesData favouritesData;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            event_name_music = (TextView) itemView.findViewById(R.id.event_name);
            music_image = (ImageView) itemView.findViewById(R.id.image);
            event_checkbox_music = (CheckBox) itemView.findViewById(R.id.event_checkbox);
            favorite_button_music = (Button) itemView.findViewById(R.id.favorite_button);
            Layout = (ConstraintLayout) itemView.findViewById(R.id.Layout);
            event_checkbox = (CheckBox) itemView.findViewById(R.id.event_checkbox);
            dBreferenceFavourites= FirebaseDatabase.getInstance().getReference().child("Favourite_Events");
            favouritesData=new FavouritesData();


        }



    }

///////////////////////////////////////////////////////////////////////////////////////////////////////


}
