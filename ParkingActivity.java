package com.example.parkitfinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;



public class ParkingActivity extends AppCompatActivity  {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);



         final Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                String slot_n="1";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                HashMap<String, String> map = new HashMap<>();
                map.put("coordinates", slot_n);
                b.setBackgroundColor(Color.RED);

                        Intent intent=new Intent(ParkingActivity.this, ParkingReceiptActivity.class);
                        String slot=null;
                        intent.putExtra("slot-1", slot);
                        startActivity(intent);





            }
        });
        final Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                b2.setBackgroundColor(Color.RED);

                int slot_n=2;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("coordinates", slot_n);
                startActivity(new Intent(ParkingActivity.this, ParkingReceiptActivity.class));


            }
        });
        final Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                b3.setBackgroundColor(Color.RED);


                int slot_n=3;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("coordinates", slot_n);
                startActivity(new Intent(ParkingActivity.this, ParkingReceiptActivity.class));



            }
        });
        final Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

               /* if (ActivityCompat.checkSelfPermission(ParkingActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }*/
                b4.setBackgroundColor(Color.RED);

                int slot_n=4;
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("coordinates", slot_n);
                startActivity(new Intent(ParkingActivity.this, ParkingReceiptActivity.class));


                /*client.getLastLocation().addOnSuccessListener(ParkingActivity.this, new OnSuccessListener<Location>() {

                    public void onSuccess(Location location) {

                        if (location != null) {
                            TextView loc4 = findViewById(R.id.button4);
                            loc4.setText(location.toString());
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference();
                            HashMap<String, Integer> map = new HashMap<>();
                            map.put("coordinates", finalLatitude);
                        }

                    }
                });*/

            }
        });

    }



}

  /*  private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}*/


