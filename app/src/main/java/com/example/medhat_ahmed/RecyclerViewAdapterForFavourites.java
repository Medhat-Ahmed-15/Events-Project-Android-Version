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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RecyclerViewAdapterForFavourites extends RecyclerView.Adapter<RecyclerViewAdapterForFavourites.MyViewHolder> implements TimePickerDialog.OnTimeSetListener {

    static  Context mContextFavourites;
    List<FavouritesData> mDataFavourites;

    Dialog dialog;

    public RecyclerViewAdapterForFavourites(Context mContextFavourites, List<FavouritesData> mDataFavourites) {
        this.mContextFavourites = mContextFavourites;
        this.mDataFavourites = mDataFavourites;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vfavourites;
        vfavourites= LayoutInflater.from(mContextFavourites).inflate(R.layout.item_favourites,parent,false);

        final MyViewHolder vHolder=new MyViewHolder(vfavourites);

        //DIALOG INI
        dialog=new Dialog(mContextFavourites);
        dialog.setContentView(R.layout.dialog_favourites);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        vHolder.event_checkbox_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {

                if ( isChecked )
                {

                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(((FragmentActivity)mContextFavourites).getSupportFragmentManager(), "time picker");
                    Toast.makeText(mContextFavourites,"Set Your Reminder Time",Toast.LENGTH_LONG).show();


                }

            }
        });



        vHolder.Layout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                TextView description = (TextView) dialog.findViewById(R.id.description);
                TextView numberofGuests = (TextView) dialog.findViewById(R.id.numberofGuests);
                TextView date = (TextView) dialog.findViewById(R.id.date);
                TextView time = (TextView) dialog.findViewById(R.id.time);
                TextView location = (TextView) dialog.findViewById(R.id.location);

                description.setText("DESCRIPTION: " + mDataFavourites.get(vHolder.getAdapterPosition()).getEventDescription());
                numberofGuests.setText("NUMBER OF GUESTS: " + mDataFavourites.get(vHolder.getAdapterPosition()).getEventNumberOfGuests());
                date.setText("DATE: " + mDataFavourites.get(vHolder.getAdapterPosition()).getEventdate());
                time.setText("TIME: " + mDataFavourites.get(vHolder.getAdapterPosition()).getEventTime());
                location.setText("LOCATION: " + mDataFavourites.get(vHolder.getAdapterPosition()).getEventLocation());

                dialog.show();


            }
        });




        return  vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.event_name_food.setText(mDataFavourites.get(position).getEventName());

        holder.container_image.setImageResource(mDataFavourites.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return mDataFavourites.size();//keda mazboot 3ashan hay count 3ala hasab kam item hada5alhom
    }

    public void filterList(ArrayList<FavouritesData> filteredList) {
        mDataFavourites = filteredList;
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
        AlarmManager alarmManager = (AlarmManager) mContextFavourites.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContextFavourites, AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContextFavourites, 1, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView event_name_food;
        ImageView container_image;
        CheckBox event_checkbox_food;
        ConstraintLayout Layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            event_name_food = (TextView) itemView.findViewById(R.id.event_name);
            container_image = (ImageView) itemView.findViewById(R.id.container_image);
            event_checkbox_food = (CheckBox) itemView.findViewById(R.id.event_checkbox);
            Layout = (ConstraintLayout) itemView.findViewById(R.id.Layout);



        }

    }

}
