package com.example.medhat_ahmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginPage extends AppCompatActivity {

    ConstraintLayout Layout;

    private static final int RC_SIGN_IN=1;

    GoogleApiClient mGoogleApiClient;

    TextView createAccount_textView;

    EditText userName_textField;
    EditText password_textField;

    Button signin_button;

    LoginButton loginButton;

    SignInButton googleButton;

    String checkPassword;
    String id;
    String pass;

    User user;

    public CallbackManager mCallbackManager;/****FACEBOOK****/



    private static final String TAG="FACELOG";


    private FirebaseAuth mAuth;/****FACEBOOK****/

    int googleflag;//GOOGLE

    ImageView back_imageView;
    ImageView home_imageView;

    ArrayList<String> arrayListUserName;

    public HashMap<String, String> userAccountHashMap;
    public HashMap<String, String> userFirstNameHashMap;
    List<User> listUser;

    DatabaseReference dbReference;// object to connect to our database
    DatabaseReference dbReferenceCurrentUser;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        mAuth = FirebaseAuth.getInstance();/****FACEBOOK****/



        Layout=(ConstraintLayout) findViewById(R.id.Layout);
        back_imageView=(ImageView) findViewById(R.id.back_imageView);
        home_imageView=(ImageView) findViewById(R.id.home_imageView);
        createAccount_textView=(TextView) findViewById(R.id.createAccount_textView);
        password_textField = (EditText) findViewById(R.id.password_textField);
        userName_textField = (EditText) findViewById(R.id.userName_textField);
        signin_button = (Button) findViewById(R.id.signin_button);
        loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        googleButton = (SignInButton) findViewById(R.id.googleButton);


        AnimationDrawable animationDrawable=(AnimationDrawable) Layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        arrayListUserName=new ArrayList<String>();
        userAccountHashMap = new HashMap<String, String>();
        userFirstNameHashMap= new HashMap<String, String>();

        dbReference=FirebaseDatabase.getInstance().getReference("User_Info");
        dbReferenceCurrentUser=FirebaseDatabase.getInstance().getReference("Current User");

        user=new User();

        listUser=new ArrayList<>();








        //Add a listener for changes in the data at this location. Each time time the data changes,
        // your listener will be called with an immutable snapshot of the data.
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            //A DataSnapshot instance contains data from a Firebase Database location.
            //Any time you read Database data, you receive the data as a DataSnapshot.
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){

                    user=ds.getValue(User.class);
                    listUser.add(user);
                    userAccountHashMap.put(user.getUserName().toString(), user.getPassword().toString());
                    userFirstNameHashMap.put(user.getUserName().toString(), user.getFirstname().toString());

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        createAccount_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpActivity();
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




        signin_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                arrayListUserName.addAll(userAccountHashMap.keySet());


                if(arrayListUserName.contains(userName_textField.getText().toString()))
                {
                    checkPassword= userAccountHashMap.get(userName_textField.getText().toString());

                    if(checkPassword.equals(password_textField.getText().toString()))
                    {


                        Toast.makeText(LoginPage.this,"Signed In",Toast.LENGTH_LONG).show();
                        String username=userName_textField.getText().toString();
                        //todo
                       for(int i=0;i<listUser.size();i++)
                        {
                            if(listUser.get(i).getUserName().equals(username))
                            {
                                openUserProfileActivity(listUser.get(i).getFirstname(),
                                        listUser.get(i).getSecondName(),
                                        listUser.get(i).getUserName(),
                                        listUser.get(i).getEmail(),
                                        listUser.get(i).getPassword(),
                                        listUser.get(i).getProfileImage());

                                //todo
                                //add current user
                                Map<String, Object> updates = new HashMap<String,Object>();
                                updates.put("userID", listUser.get(i).getUserName());

                                dbReferenceCurrentUser.updateChildren(updates);

                                break;
                            }
                        }





                    }

                    else  Toast.makeText(LoginPage.this,"Incorrect Email Or Password",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(LoginPage.this,"Incorrect Email Or Password",Toast.LENGTH_LONG).show();
                }


               // String firstname=userFirstNameHashMap.get(userName_textField.getText().toString());



                userName_textField.getText().clear();
                password_textField.getText().clear();


                /*for (String username : userFirstNameHashMap.keySet()) {
                    System.out.println(username);
                }

                for (String value : userFirstNameHashMap.values()) {
                    System.out.println(value);
                }*/

            }
        });


        /****FACEBOOK****/

        //callback manager create a bridge between your app and facebook that will allow the information to pass
        mCallbackManager = CallbackManager.Factory.create();


        //now these are the permission that required to create an account on firebase which is email and  public profile we can add image,date of birth...
        loginButton.setReadPermissions("email", "public_profile");

        //what will this do  is it will register the callback which is(CallbackManager) that we initialize right over here,,it will request facebook and give the access to login
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
               /****FACEBOOK****/

        //GOOGLE
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                Toast.makeText(LoginPage.this,"YOU GOT AN ERROR",Toast.LENGTH_LONG).show();

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        //GOOGLE
    }
    //...............................................................................................................................................................................
    //...............................................................................................................................................................................
    //...............................................................................................................................................................................
    //...............................................................................................................................................................................
    //...............................................................................................................................................................................


    //GOOGLE
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    //GOOGLE

    /****FACEBOOK****/
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null)
        {
            updateUI();
        }

    }

    private void updateUI() {

        Toast.makeText(LoginPage.this,"YOU ARE LOGGED IN",Toast.LENGTH_LONG).show();
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Toast.makeText(LoginPage.this, "Authentication failed.",
                            //Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }

    /****FACEBOOK****/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(googleflag==1)
        {
            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                    // ...
                }
            }
        }

        // Pass the activity result back to the Facebook SDK
        else mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    //GOOGLE

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        }

                        // ...
                    }
                });
    }


    //GOOGLE

    public void signUpActivity(){
        Intent intent=new Intent(this, SignUpPage.class);
        startActivity(intent);
    }


    public void back(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void home(){
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
    }


    public void openUserProfileActivity(String firstName,String secondName,String username,String email,String password,int prilePicture)
    {
        Intent intent=new Intent(this,UserProfile.class);

        intent.putExtra("firstName", firstName);
        intent.putExtra("secondName", secondName);
        intent.putExtra("username", username);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("profilePicture", prilePicture);

        startActivity(intent);
    }


}