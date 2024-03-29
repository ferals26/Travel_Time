package com.example.traveltime;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    int myear, mdate, mmonth, mmin, mhour;
    int num = 1;
    String update_id;
    int id_number;
    Button Regreso;

    DataBaseHelper myDb;
    AlarmDatabase myAlarmDb;

    EditText date_text, time_text, name_text, desc_text;
    TextView id_text;
    Button button_update_rem;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent my_intent;
    Context context;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private TimePickerDialog.OnTimeSetListener mTimeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity_2);
        myDb = new DataBaseHelper(this);
        myAlarmDb = new AlarmDatabase(this);

        date_text = (EditText) findViewById(R.id.date_text);
        time_text = (EditText) findViewById(R.id.time_text);
        id_text = (TextView) findViewById(R.id.id_text);
        name_text = (EditText) findViewById(R.id.name_text);
        desc_text = (EditText) findViewById(R.id.desc_text);

        myDb = new DataBaseHelper(this);

        Regreso = (Button) findViewById(R.id.btnRegreso);
        Regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this, MainActivity2.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            update_id = extras.getString("id");
            //The key argument here must match that used in the other activity
        }
        id_number = Integer.parseInt(update_id);


        int number = 1;


        button_update_rem = (Button) findViewById(R.id.button_update_rem);
        id_text.setText(update_id);


        this.context = this;


        // initialize the alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


      /*
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            //show message
            Toast.makeText(UpdateActivity.this,"No hay recordatorios",Toast.LENGTH_SHORT).show();
            return;
        }
        int result = Integer.parseInt(getString(id_number));

        while (res.moveToPosition(result)) {
            id_text.setText("Id :" + res.getString(0));
            name_text.setText("Name :" + res.getString(1) + "\n");
            date_text.setText("Date :" + res.getString(2) + "\n");
            time_text.setText("Time :" + res.getString(3) + "\n");
            desc_text.setText("Description: "+res.getString(4)+"\n\n");
        }
        //show the data


       */


        button_update_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.UpdateData(id_text.getText().toString(), name_text.getText().toString(), date_text.getText().toString(), time_text.getText().toString(), desc_text.getText().toString());

                if (isUpdate == true) {
                    Toast.makeText(UpdateActivity.this, "Recordatorio actualizado", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UpdateActivity.this, "El recordatorio no se puede actualizar", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }




}
