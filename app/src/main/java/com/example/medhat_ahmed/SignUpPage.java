package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SignUpPage extends AppCompatActivity {

    ScrollView Layout;

    ImageView back_imageView;
    ImageView home_imageView;
    ImageView imageView;


    Bitmap bitmap;

    EditText firstName_textField;
    EditText secondName_textField;
    EditText userName_textField;
    EditText email_textField;
    EditText password_textField;
    EditText confirmPassword_textField;

    ArrayList<EditText> editTextsList=new ArrayList<EditText>();
    List<User> listUser;

    Button choosePicture_button;
    Button signup_button;

    DatabaseReference dBreferenceUser;// object to connect to our database

    private List<User> listUsers;

    User user;

    int flag;


    //DOL HAGAT EL SEND EMAIL
    GMailSender sender;
    String myEmail="mido1998152014@gmail.com";
    String myPassword="fmaamito15";
    String sb,bd,rp;
    //DOL HAGAT EL SEND EMAIL

    boolean duplicate=false;

    long userId=0;

    Uri uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        Layout = (ScrollView) findViewById(R.id.Layout);
        back_imageView = (ImageView) findViewById(R.id.back_imageView);
        home_imageView = (ImageView) findViewById(R.id.home_imageView);
        imageView = (ImageView) findViewById(R.id.imageView);
        choosePicture_button = (Button) findViewById(R.id.choosePicture_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        firstName_textField = (EditText) findViewById(R.id.firstName_textField);
        secondName_textField = (EditText) findViewById(R.id.secondName_textField);
        userName_textField = (EditText) findViewById(R.id.userName_textField);
        email_textField = (EditText) findViewById(R.id.email_textField);
        password_textField = (EditText) findViewById(R.id.password_textField);
        confirmPassword_textField = (EditText) findViewById(R.id.confirmPassword_textField);
        sender=new GMailSender(myEmail,myPassword);

        AnimationDrawable animationDrawable=(AnimationDrawable) Layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();





        //you can use the firebasedatabase.getinstance.getrefrence and
        // pass the name of the table and this is used to refer our database in the
        // firebase cloud so the name that you give here will be
        // taken out the name of out tree
        dBreferenceUser=FirebaseDatabase.getInstance().getReference("User_Info");

        user=new User();
        listUser=new ArrayList<>();

        dBreferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //exists()-->Returns true if the snapshot contains a non-null value.

                for(DataSnapshot ds:snapshot.getChildren()){

                    user=ds.getValue(User.class);

                    listUser.add(user);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



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


        choosePicture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {//dee bat return akeed rakam integer el howa int which masaln 1
                            //CHOOSE FROM LIBRARY button clicked
                            Intent intent1 = new Intent();
                            intent1.setAction(Intent.ACTION_GET_CONTENT);
                            intent1.setType("image/*");
                            flag = 1;
                            startActivityForResult(intent1, 1);
                        }


                        if (which == DialogInterface.BUTTON_NEGATIVE) {//dee bat return akeed rakam integer el howa int which masaln -1
                            //TAKE PHOTO button clicked
                            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            flag = 2;
                            startActivityForResult(intent2, 2);
                        }

                    }
                };

                //here we build the texts that gonna be written in the interface of the alert box
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpPage.this);
                builder.setMessage("").setPositiveButton("CHOOSE FROM LIBRARY🗂", dialogClickListener)
                        .setNegativeButton("TAKE PHOTO📸", dialogClickListener).show();


            }
        });



        signup_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int checkEmpty =0;





                editTextsList.add(confirmPassword_textField);
                editTextsList.add(password_textField);
                editTextsList.add(email_textField);
                editTextsList.add(firstName_textField);
                editTextsList.add(secondName_textField);
                editTextsList.add(userName_textField);

                for(int j=0;j<editTextsList.size();j++)
                {

                    editTextsList.get(j).setHint("");
                }

                //Todo

                //ana 3arf 2n haya haya law 3araft el strings dee bara
                //bs hna msh hatashta8l law tala3tohom bara ana garbt
                 String Firstname=firstName_textField.getText().toString();
                 String SecondName=secondName_textField.getText().toString();
                  String UserName=userName_textField.getText().toString();
                 String Email=email_textField.getText().toString();
                 String password=password_textField.getText().toString();


                 for(int i=0;i<listUser.size();i++)
                 {
                     if(listUser.get(i).getUserName().equals(UserName))
                     {
                         duplicate=true;
                         break;
                     }
                 }


                     if(!firstName_textField.getText().toString().equals("")
                     &&!secondName_textField.getText().toString().equals("")
                     &&!userName_textField.getText().toString().equals("")
                     &&!email_textField.getText().toString().equals("")
                     &&!password_textField.getText().toString().equals("")
                     &&!confirmPassword_textField.getText().toString().equals("")
                             &&!duplicate

                             &&password_textField.getText().toString().equals(confirmPassword_textField.getText().toString()))
                     {


                         user.setFirstname(Firstname);
                         user.setSecondName(SecondName);
                         user.setUserName(UserName);
                         user.setEmail(Email);
                         user.setPassword(password);
                         user.setProfileImage(imageView.getId());



                         dBreferenceUser.push().setValue(user);

                         sb="Eventos";
                         bd="Heyy,"+firstName_textField.getText().toString()+"  "+secondName_textField.getText().toString()+" "+"Your Account Has Been created Successfull";
                         rp=email_textField.getText().toString();

                         new MyAsyncClass().execute();


                         loginPage();




                         firstName_textField.getText().clear();
                         secondName_textField.getText().clear();
                         userName_textField.getText().clear();
                         email_textField.getText().clear();
                         password_textField.getText().clear();
                         confirmPassword_textField.getText().clear();

                     }

                     else
                         {

                             for(int i=0;i<editTextsList.size();i++)

                             {

                             if (editTextsList.get(i).getText().toString().equals(""))
                             {
                                 editTextsList.get(i).setHint("Required");
                                 editTextsList.get(i).setHintTextColor(Color.RED);
                             }

                             }


                             if(!password_textField.getText().toString().equals(confirmPassword_textField.getText().toString()))
                             {
                                 Toast.makeText(SignUpPage.this,"Password confirmation doesn't match Password",Toast.LENGTH_LONG).show();
                             }

                             if(duplicate)
                             { Toast.makeText(SignUpPage.this,"Username taken",Toast.LENGTH_LONG).show();}


                         }







            }
        });








    }
    ///////////////////////////////////////////////////////////////////////////



    public void back() {

        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void home() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (flag == 1) {

            if (requestCode == 1 && resultCode == RESULT_OK) {
                uri = data.getData();


                try {

                     bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    imageView.setImageBitmap(bitmap);



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        else {


            if (requestCode == 2 && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap photo = (Bitmap) extras.get("data");
                imageView.setImageBitmap(photo);

            }


        }


    }
    public void loginPage(){
        Intent intent=new Intent(this, LoginPage.class);
        startActivity(intent);
    }

/***********************EL CLASS DAH LAL SENDING MAIL****************************/
    class MyAsyncClass extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(SignUpPage.this);
            pDialog.setMessage("Please wait...");
            pDialog.show();

        }

        @Override

        protected Void doInBackground(Void... mApi) {
            try {

                // Add subject, Body, your mail Id, and receiver mail Id.
                sender.sendMail(sb, bd, myEmail, rp);
                Log.d("send", "done");
            }
            catch (Exception ex) {
                Log.d("exceptionsending", ex.toString());
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            pDialog.cancel();

            Toast.makeText(SignUpPage.this, "We've sent to you  a confirmation mail", Toast.LENGTH_SHORT).show();

        }
    }
/***********************EL CLASS DAH LAL SENDING MAIL****************************/
}