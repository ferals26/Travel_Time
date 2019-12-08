package com.example.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class tuviaje extends AppCompatActivity {
    Button Actividad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuviaje);

        Actividad = (Button) findViewById(R.id.actividadbtn);
        Actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tuviaje.this, MainActivity2.class));
            }
        });




    }
}
