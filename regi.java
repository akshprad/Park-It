package com.example.parkitfinal;


import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class regi extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = regi.class.getSimpleName();
    private Button btnNewRegister;
    private EditText name, email, password, phoneNumber, carNumber;
    private Spinner city;
    private String strName, strEmail, strPassword, strPhoneNumber, strCarNumber, strCity;

    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi);

        Button registerBtnRegister = (Button)findViewById(R.id.registerBtnRegister);

        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(regi.this, ParkingActivity.class);
                startActivity(intent);

            }
        });


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnNewRegister = findViewById(R.id.registerBtnRegister);
        name = findViewById(R.id.registerEtName);
        email = findViewById(R.id.registerEtEmail);
        password = findViewById(R.id.registerEtPassword);
        phoneNumber = findViewById(R.id.registerEtPhone);
        carNumber = findViewById(R.id.registerEtCatPlateNumber);
       /* ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        // URL you want to redirect back to. The domain (www.example.com) for this
                        // URL must be whitelisted in the Firebase Console.
                        .setUrl("https://console.firebase.google.com/u/0/project/parkitfinal/database")
                        // This must be true
                        .setHandleCodeInApp(true)
                        .setIOSBundleId("com.example.ios")
                        .setAndroidPackageName(
                                "com.example.android",
                                true, *//* installIfNotAvailable *//*
                                "12"    *//* minimumVersion *//*)
                        .build();
*/


        mAuth = FirebaseAuth.getInstance();

        btnNewRegister.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerBtnRegister:
                extractData();
                if (verifyData()){
                    mAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        String userId = user.getUid();

                                        /*DateFormat dateFormatDate = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA);
                                        DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);
                                        Calendar cal = Calendar.getInstance();
                                        String date = dateFormatDate.format(cal.getTime());
                                        String time = dateFormatTime.format(cal.getTime());*/


                                        // Write a message to the database
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference();
                                       HashMap<String, String> map = new HashMap<>();
                                        map.put("car_number", strCarNumber);
                                        map.put("email", strEmail);

                                        map.put("name", strName);
                                        map.put("number", strPhoneNumber);
                                        myRef.child("User").child(userId).setValue(map);
                                        Toast.makeText(regi.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(regi.this, regi.class);
                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(regi.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                } else {
                    Toast.makeText(this, "Error in the form", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    void extractData(){
        strName = name.getText().toString();
        strEmail = email.getText().toString();
        strPassword = password.getText().toString();
        strPhoneNumber = phoneNumber.getText().toString();
        strCarNumber = carNumber.getText().toString();
    }

    boolean verifyData(){
        boolean isDataTrue = true;

        if (strName.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty() || strPhoneNumber.isEmpty() || strCarNumber.isEmpty() ){
            isDataTrue = false;
        }

        return isDataTrue;
    }

}

