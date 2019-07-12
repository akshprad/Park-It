package com.example.parkitfinal;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.*;
import static com.example.parkitfinal.R.*;
import static com.example.parkitfinal.R.id.*;
import static com.example.parkitfinal.R.id.google_sign_in;
import static com.example.parkitfinal.R.id.loginBtnLogin;
import static com.example.parkitfinal.R.id.loginBtnRegister;
import static com.example.parkitfinal.R.id.loginEtPassword;
import static com.example.parkitfinal.R.layout.activity_main;


public class LogIn extends AppCompatActivity  {

     Button btnLogin, btnRegister, signInButton;
     EditText edtEmail;
     EditText edtPassword;
     FirebaseAuth mAuth;
     GoogleApiClient googleApiClient;

    private static final String TAG = LogIn.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;

    private String name = "", email = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_log_in);

      /*  Button b = (Button)findViewById(google_sign_in);

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, ParkingActivity.class));
            }
        });*/
        Button b1 = (Button)findViewById(loginBtnRegister);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, regi.class));

            }
        });

        Button b2 =(Button) findViewById(loginBtnLogin);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String strEmail, strPassword ;
                EditText edtEmail=(EditText) findViewById(loginEtEmail);
                EditText edtPassword=(EditText) findViewById(loginEtPassword);
                strEmail = edtEmail.getText().toString();
                strPassword = edtPassword.getText().toString();
                startActivity(new Intent(LogIn.this, ParkingActivity.class));
               /* boolean dataValidate() {
                    boolean isDataValid = true;

                    if (strEmail.equals("") || strPassword.equals("")) {
                        isDataValid = false;
                    }

                    return isDataValid;
                }
*/
                if (strEmail.equals("") || strPassword.equals("")) {
                    final Task<AuthResult> authResultTask = (Task<AuthResult>) mAuth.signInWithEmailAndPassword(strEmail, strPassword)
                            .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent loginIntent = new Intent(LogIn.this, LogIn.class);
                                        startActivity(loginIntent);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        makeText(LogIn.this, "Authentication failed.", LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

/*
        super.onCreate(savedInstanceState);
*/

/*
        setContentView(activity_main);
*/

/*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/

        mAuth = FirebaseAuth.getInstance();


     /*   Button edtEmail =(Button) findViewById(R.id.loginEtEmail);
        Button edtPassword = (Button)findViewById(R.id.loginEtPassword);*/

      /*loginBtnLogin.setOnClickListener(this);
        loginBtnRegister.setOnClickListener(this);
        signInButton.setOnClickListener(this);*/


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("177024362875-47c182nbkasvaifgd8lto0p4eeqjaepu.apps.googleusercontent.com")
                .build();

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(Auth.GOOGLE_SIGN_IN_API, gso);
        googleApiClient = builder.build();
    }



    /*public void onClick(View view) {
        switch (view.getId()) {
            case loginBtnLogin:
                extractData();
                if (dataValidate()) {
                    mAuth.signInWithEmailAndPassword(strEmail, strPassword)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent loginIntent = new Intent(LogIn.this, LogIn.class);
                                        startActivity(loginIntent);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LogIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
                break;
            case R.id.loginBtnRegister:
                Toast.makeText(this, "Register Clicked", Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(LogIn.this, regi.class);
                startActivity(registerIntent);
                break;
            case google_sign_in:
                signIn();
                break;
            default:

                break;
        }
    }*/

   /* protected void extractData() {
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
    }*/



    void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                email = account.getEmail();
                name = account.getDisplayName();
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "onComplete: UserID : " + user.getUid());
                            checkIfFirstTimeLogin();
                            //Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            //startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            makeText(LogIn.this, "Authentication Failed.", LENGTH_SHORT).show();
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    void checkIfFirstTimeLogin() {
        String userId = mAuth.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User").child(userId);
        myRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Intent intent = new Intent(LogIn.this, LogIn.class);
                    startActivity(intent);
                } else {
                    //First time signing in get the user details
                    Intent intent = new Intent(LogIn.this, GoogleSignInExtra.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }


            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}