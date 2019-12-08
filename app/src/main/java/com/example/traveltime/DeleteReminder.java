package com.example.traveltime;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteReminder extends AppCompatActivity {
    int myear, mdate, mmonth, mmin, mhour;
    int num = 1;
    Button Regreso;
    DataBaseHelper myDb;
    AlarmDatabase myAlarmDb;

    EditText delete_id_text;
    Button button_add_rem;
    Button button_del_rem;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent my_intent;
    Context context;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private TimePickerDialog.OnTimeSetListener mTimeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);
        int number = 1;

        myDb = new DataBaseHelper(this);
        myAlarmDb = new AlarmDatabase(this);


        delete_id_text = (EditText) findViewById(R.id.delete_id_text);
        button_del_rem=(Button)findViewById(R.id.button_del_rem);


        this.context = this;


        // initialize the alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        deleteRem();

        Regreso = (Button) findViewById(R.id.btnRegreso);
        Regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteReminder.this, MainActivity2.class));
            }
        });
    }

    public void deleteRem() {
        button_del_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.Deletedata(delete_id_text.getText().toString());
                if (deleteRows >= 0 ) {
                    Toast.makeText(DeleteReminder.this, "Recordatorio eliminado", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DeleteReminder.this, "No hay recordatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
