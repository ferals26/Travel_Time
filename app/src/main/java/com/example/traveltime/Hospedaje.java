package com.example.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Hospedaje extends AppCompatActivity {
    Button boton1;
    Button boton6;
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate1;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private EditText NombredelHotel;
    private EditText Personas;
    private EditText Habitaciones;
    private TextView Fecha1;
    private TextView Fecha2;
    private Button  btnCrear;
    private FirebaseAuth firebaseAuth;
    private  Spinner spinner;




    private DatabaseReference mDatabase;
    private DatePickerDialog.OnDateSetListener mDateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospedaje);

        NombredelHotel = (EditText) findViewById(R.id.NombreViaje);
        Personas = (EditText) findViewById(R.id.persona);
        Habitaciones = (EditText) findViewById(R.id.habita);
        Fecha1 = (TextView)findViewById(R.id.fecha3);
        Fecha2 = (TextView) findViewById(R.id.fecha4);
        btnCrear = (Button) findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        boton6 = (Button)findViewById(R.id.button6);
        boton6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospedaje.this, Lista_hospedaje.class));
            }
        });
        boton1= (Button) findViewById(R.id.button1);
        boton1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospedaje.this, inicio.class));
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NombreViaje = NombredelHotel.getText().toString();
                String Persona = Personas.getText().toString();
                String Habitacion = Habitaciones.getText().toString();
                String FechaLlegada = Fecha1.getText().toString();
                String FechaIda = Fecha2.getText().toString();
                Spinner spinner = (Spinner)findViewById(R.id.spinner3);
                String reservacion = spinner.getSelectedItem().toString();
                Map<String, Object> DatosHotel = new HashMap<>();
                DatosHotel.put("NombreViajes", NombreViaje);
                DatosHotel.put("Personas", Persona);
                DatosHotel.put("Habitaciones", Habitacion);
                DatosHotel.put("Check-in", FechaLlegada);
                DatosHotel.put("Check-on", FechaIda);
                DatosHotel.put("Reservaciones", reservacion);
                mDatabase.child("Hospedaje").push().setValue(DatosHotel);
                startActivity(new Intent(Hospedaje.this, Lista_hospedaje.class));


            }
        });

        mDisplayDate = (TextView) findViewById(R.id.fecha3);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Hospedaje.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        } );

        mDisplayDate1 = (TextView) findViewById(R.id.fecha4);
        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Hospedaje.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener1, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy" + month + "/" + day + "/" + year);
                String date =  + month + "/" + day + "/" + year;
                mDisplayDate1.setText(date);
            }
        };


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy" + month + "/" + day + "/" + year);
                String date =  + month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        spinner = (Spinner)findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Reservaciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
