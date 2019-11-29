package com.example.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class listsnotes extends AppCompatActivity {
Button tuviaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsnotes);

       tuviaje = (Button) findViewById(R.id.tuviaje);
       tuviaje.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(listsnotes.this, tuviaje.class));
           }
       });


    }
}
