package com.example.parkitfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = (Button) findViewById(R.id.b1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this,
                        LogIn.class);

                startActivity(myIntent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        Button button1 = (Button) findViewById(R.id.b1);
        button1.setEnabled(true);
    }
}