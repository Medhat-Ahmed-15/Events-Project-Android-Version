package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.places.model.PlaceInfoRequestParams;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderPager;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AddEvent extends AppCompatActivity {

    ImageView back_imageView;
    ImageView home_imageView;
    ImageView test_imageView;

    CalendarView calender;


    TimePicker timePicker;

    Button add_button;
    Button addLocation_button;
    Button upload_button;
    Button save_button;

    RadioButton music_radioButton;
    RadioButton food_radioButton;
    RadioButton sports_radioButton;
    RadioButton other_radioButton;
    RadioGroup radiogroup;

    EditText eventName_textField;
    EditText description_textField;
    EditText numberOfguests_textField;

    ArrayList<EditText> editTextsList=new ArrayList<EditText>();


    Bitmap bitmap;


    ScrollView Layout;

    Random random = new Random();

    String location;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 1;
    int flag;
    int flagResultActivity;
    int hour;
    int min;
    int PLACE_PICKER_REQUEST=1;
    int randomFoodImages=random.nextInt(8);
    int randomMusicImages=random.nextInt(6);
    int randomOtherImages=random.nextInt(6);
    int randomSportsImages=random.nextInt(6);

    int[] foodImages ={
            R.drawable.fish,
            R.drawable.burger,
            R.drawable.chicken,
            R.drawable.salad,
            R.drawable.soup,
            R.drawable.pizza,
            R.drawable.food7,
            R.drawable.food8
    };



    int[] musicImages ={
            R.drawable.music1,
            R.drawable.music2,
            R.drawable.music3,
            R.drawable.music4,
            R.drawable.music5,
            R.drawable.music6
    };

    int[] otherImages ={
            R.drawable.other,
            R.drawable.other,
            R.drawable.other,
            R.drawable.other,
            R.drawable.other,
            R.drawable.other
    };


    int[] sportsImages ={
            R.drawable.sports1,
            R.drawable.sports2,
            R.drawable.sports3,
            R.drawable.sports4,
            R.drawable.sports5,
            R.drawable.sports6,
    };



    long eventMusic=0;
    long eventFood=0;
    long eventSports=0;
    long eventother=0;

    String date;
    String time;
    String format="";


    DatabaseReference dataBaseReferencemusic;
    DatabaseReference dataBaseReferencefood;
    DatabaseReference dataBaseReferencesports;
    DatabaseReference dataBaseReferenceother;

    StorageReference storageRef;
    StorageTask uploadTask;

    Music music;
    Food food;
    Sports sports;
    Other other;

    int empty;

    DatabaseReference dBreferenceCurrentUser=FirebaseDatabase.getInstance().getReference("Current User");
    public String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Layout = (ScrollView) findViewById(R.id.Layout);

        AnimationDrawable animationDrawable=(AnimationDrawable) Layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


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



        calender = (CalendarView) findViewById(R.id.calender);
        timePicker = (TimePicker) findViewById(R.id.timePicker);





        add_button = (Button) findViewById(R.id.add_button);
        //save_button = (Button) findViewById(R.id.save_button);
        //upload_button = (Button) findViewById(R.id.upload_button);
        addLocation_button = (Button) findViewById(R.id.addLocation_button);

        back_imageView=(ImageView) findViewById(R.id.back_imageView);
        home_imageView=(ImageView) findViewById(R.id.home_imageView);
        //test_imageView=(ImageView) findViewById(R.id.test_imageView);

        eventName_textField=(EditText) findViewById(R.id.eventName_textField);
        description_textField=(EditText) findViewById(R.id.description_textField);
        numberOfguests_textField=(EditText) findViewById(R.id.numberOfguests_textField);

        music_radioButton=(RadioButton) findViewById(R.id.music_radioButton);
        food_radioButton=(RadioButton) findViewById(R.id.food_radioButton);
        sports_radioButton=(RadioButton) findViewById(R.id.sports_radioButton);
        other_radioButton=(RadioButton) findViewById(R.id.other_radioButton);
        radiogroup=(RadioGroup) findViewById(R.id.radiogroup);




        dataBaseReferencemusic= FirebaseDatabase.getInstance().getReference().child("Music_Events");
        dataBaseReferencesports= FirebaseDatabase.getInstance().getReference().child("Sports_Events");
        dataBaseReferencefood= FirebaseDatabase.getInstance().getReference().child("Food_Events");
        dataBaseReferenceother= FirebaseDatabase.getInstance().getReference().child("Other_Events");

        storageRef= FirebaseStorage.getInstance().getReference("Images");




        music=new Music();
        food=new Food();
        sports=new Sports();
        other=new Other();



        //Add a listener for changes in the data at this location. Each time time the data changes,
        // your listener will be called with an immutable snapshot of the data.

        /*
        if(flag==1) {
            dataBaseReferencemusic.addValueEventListener(new ValueEventListener() {
                @Override
                //A DataSnapshot instance contains data from a Firebase Database location.
                //Any time you read Database data, you receive the data as a DataSnapshot.
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //exists()-->Returns true if the snapshot contains a non-null value.
                    if (snapshot.exists()) {
                        eventMusic = (snapshot.getChildrenCount());//getChildrenCount()-->return The number of immediate children in the this snapshot
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if(flag==2) {
            dataBaseReferencefood.addValueEventListener(new ValueEventListener() {
                @Override
                //A DataSnapshot instance contains data from a Firebase Database location.
                //Any time you read Database data, you receive the data as a DataSnapshot.
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //exists()-->Returns true if the snapshot contains a non-null value.
                    if (snapshot.exists()) {
                        eventFood = (snapshot.getChildrenCount());//getChildrenCount()-->return The number of immediate children in the this snapshot
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if(flag==3) {
            dataBaseReferencesports.addValueEventListener(new ValueEventListener() {
                @Override
                //A DataSnapshot instance contains data from a Firebase Database location.
                //Any time you read Database data, you receive the data as a DataSnapshot.
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //exists()-->Returns true if the snapshot contains a non-null value.
                    if (snapshot.exists()) {
                        eventSports = (snapshot.getChildrenCount());//getChildrenCount()-->return The number of immediate children in the this snapshot
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        if(flag==4) {
            dataBaseReferenceother.addValueEventListener(new ValueEventListener() {
                @Override
                //A DataSnapshot instance contains data from a Firebase Database location.
                //Any time you read Database data, you receive the data as a DataSnapshot.
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //exists()-->Returns true if the snapshot contains a non-null value.
                    if (snapshot.exists()) {
                        eventother = (snapshot.getChildrenCount());//getChildrenCount()-->return The number of immediate children in the this snapshot
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
      */

        back_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                back();
            }
        });


        home_imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home();
            }
        });






        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
                            {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                 date = dayOfMonth + "-" + (month + 1) + "-" + year;




                            }
                        });


        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                int hour = hourOfDay;
                int min = minute;


                if (hour == 0) {
                    hour += 12;
                    format = "AM";
                } else if (hour == 12) {
                    format = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                time=String.valueOf(hour) + ":" + String.valueOf(min) + ":" + format;
            }
        });





        addLocation_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                flagResultActivity=3;

                PlacePicker.IntentBuilder builder =new PlacePicker.IntentBuilder();//The Place Picker UI is a dialog that allows a user to pick a Place using an interactive map.
                try {
                    startActivityForResult(builder.build(AddEvent.this),PLACE_PICKER_REQUEST);


                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });


        /*upload_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                flagResultActivity=2;
                SelectImage();



            }
        });*/


        /*save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(uploadTask!=null&&uploadTask.isInProgress())
                {
                    Toast.makeText(AddEvent.this,"UPLOAD IN PROGRESS",Toast.LENGTH_LONG).show();

                }
                else{ uploadImage();}

            }
        });*/


        add_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                editTextsList.add(eventName_textField);
                editTextsList.add(description_textField);
                editTextsList.add(numberOfguests_textField);

                for(int i=0;i<editTextsList.size();i++)
                {

                    editTextsList.get(i).setHint("");
                }

                if (radiogroup.getCheckedRadioButtonId() != -1&&!eventName_textField.getText().toString().equals("")
                        &&!description_textField.getText().toString().equals("")
                        &&!numberOfguests_textField.getText().toString().equals(""))
                {



                if (music_radioButton.isChecked()) {

                    String eventName = eventName_textField.getText().toString();
                    String eventDescription = description_textField.getText().toString();
                    String eventLocation = location;
                    String numberOfGuests = numberOfguests_textField.getText().toString();
                    String eventDate = date;
                    String eventtime = time;

                    music.setEventName(eventName);
                    music.setEventDescription(eventDescription);
                    music.setEventLocation(eventLocation);
                    music.setEventNumberOfGuests(numberOfGuests);
                    music.setEventdate(eventDate);
                    music.setEventTime(eventtime);
                    music.setUserID(currentUser);
                    music.setImage(musicImages[randomMusicImages]);

                    dataBaseReferencemusic.push().setValue(music);


                    eventName_textField.getText().clear();
                    description_textField.getText().clear();
                    numberOfguests_textField.getText().clear();
                    eventName_textField.getText().clear();

                }


                if (food_radioButton.isChecked()) {
                    String eventName = eventName_textField.getText().toString();
                    String eventDescription = description_textField.getText().toString();
                    String eventLocation = location;
                    String numberOfGuests = numberOfguests_textField.getText().toString();
                    String eventDate = date;
                    String eventtime = time;

                    food.setEventName(eventName);
                    food.setEventDescription(eventDescription);
                    food.setEventLocation(eventLocation);
                    food.setEventNumberOfGuests(numberOfGuests);
                    food.setEventTime(eventtime);
                    food.setEventdate(eventDate);
                    food.setUserID(currentUser);
                    food.setImage(foodImages[randomFoodImages]);


                    dataBaseReferencefood.push().setValue(food);


                    eventName_textField.getText().clear();
                    description_textField.getText().clear();
                    numberOfguests_textField.getText().clear();
                    eventName_textField.getText().clear();

                }


                if (sports_radioButton.isChecked()) {
                    String eventName = eventName_textField.getText().toString();
                    String eventDescription = description_textField.getText().toString();
                    String eventLocation = location;
                    String numberOfGuests = numberOfguests_textField.getText().toString();
                    String eventDate = date;
                    String eventtime = time;

                    sports.setEventName(eventName);
                    sports.setEventDescription(eventDescription);
                    sports.setEventLocation(eventLocation);
                    sports.setEventNumberOfGuests(numberOfGuests);
                    sports.setEventdate(eventDate);
                    sports.setEventTime(eventtime);
                    sports.setUserID(currentUser);
                    sports.setImage(sportsImages[randomSportsImages]);


                    dataBaseReferencesports.push().setValue(sports);


                    eventName_textField.getText().clear();
                    description_textField.getText().clear();
                    numberOfguests_textField.getText().clear();
                    eventName_textField.getText().clear();

                }


                if (other_radioButton.isChecked()) {
                    String eventName = eventName_textField.getText().toString();
                    String eventDescription = description_textField.getText().toString();
                    String eventLocation = location;
                    String numberOfGuests = numberOfguests_textField.getText().toString();
                    String eventDate = date;
                    String eventtime = time;

                    other.setEventName(eventName);
                    other.setEventDescription(eventDescription);
                    other.setEventLocation(eventLocation);
                    other.setEventNumberOfGuests(numberOfGuests);
                    other.setEventdate(eventDate);
                    other.setEventTime(eventtime);
                    other.setUserID(currentUser);
                    other.setImage(otherImages[randomOtherImages]);


                    dataBaseReferenceother.push().setValue(other);


                    eventName_textField.getText().clear();
                    description_textField.getText().clear();
                    numberOfguests_textField.getText().clear();
                    eventName_textField.getText().clear();


                }

            }

                else
                {
                    Toast.makeText(AddEvent.this,"ERROR:CHOOSE A TYPE FOR YOUR EVENT",Toast.LENGTH_LONG).show();

                    for(int i=0;i<editTextsList.size();i++)
                    {
                        if(editTextsList.get(i).getText().toString().equals(""))
                        {
                            editTextsList.get(i).setHint("Required");
                            editTextsList.get(i).setHintTextColor(Color.RED);
                        }

                    }


                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (flagResultActivity==3) {

            if (requestCode == PLACE_PICKER_REQUEST) {
                if (resultCode == RESULT_OK) {

                    Place place = PlacePicker.getPlace(data, this);
                    //String address=String.format("Place: %s",place.getName());

                    StringBuilder stringBuilder = new StringBuilder();

                /*A class for handling geocoding and reverse geocoding. Geocoding is the process of transforming
                 a street address or other description of a location into a (latitude, longitude) coordinate. Reverse
                 geocoding is the process of transforming a (latitude, longitude) coordinate into a (partial) address. */
                    Geocoder geocoder;
                    List<Address> addresses = null;

                /*A Locale object represents a specific geographical,
                political, or cultural region. An operation that requires a Locale
                to perform its task is called locale-sensitive and uses the Locale to tailor information
                for the user. For example, displaying a number is a locale-sensitive operation— the number should
                be formatted according to the customs and conventions of the user's native country, region, or culture.*/
                    geocoder = new Geocoder(this, Locale.getDefault());

                    try {
                        addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()


                    stringBuilder.append(address);


                    Toast.makeText(AddEvent.this, "LOCATION ADDED SUCCEFULLY😃👌", Toast.LENGTH_LONG).show();

                    location = stringBuilder.toString();


                }
            }
        }

        else if(flagResultActivity==2)
        {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                // Get the Uri of data
                filePath = data.getData();
                try {

                    // Setting image on image view using Bitmap
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    test_imageView.setImageBitmap(bitmap);
                }

                catch (IOException e) {
                    // Log the exception
                    e.printStackTrace();
                }
            }

        }
    }








    private String getExtension (Uri uri)

    {
        /*A content provider manages access to a central repository of data. A provider is part
        of an Android application, */

        /*The Content Resolver is the single, global instance in your application that provides access to your
        (and other applications') content providers*/
        ContentResolver cr=getContentResolver();

        /*A MIME type is just a standardized way to define that data type by giving it a unique name. ...
        It also helps a ContentProvider that handles several different types of data to keep things organized,*/
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();

        /*Return the registered extension for the given MIME type. Note that some MIME types map to multiple extensions.
        This call will return the most common extension for the given MIME type.*/
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }


    // UploadImage method
    private void uploadImage() {


            // Code for showing progressDialog while uploading


            //what i'm actually doing here is giving our file a name by time and it's extension and everything to give our file a unique name to avoid duplication
             StorageReference ref = storageRef.child(System.currentTimeMillis()+","+getExtension(filePath));

        uploadTask=ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(AddEvent.this,"IMAGE SAVED SUCCESFULLY",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(AddEvent.this,"FAIL",Toast.LENGTH_LONG).show();
                    }
                });

    }



    // Select Image method
    private void SelectImage()
    {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }


    public void back(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }


    public void home(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }


}