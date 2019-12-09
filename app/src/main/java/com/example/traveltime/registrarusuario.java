package com.example.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registrarusuario extends AppCompatActivity implements View.OnClickListener {

     private Button btnTengocuenta;
     private Button btnRegistrar;
     private EditText editContra;
     private EditText editUsuario;
     private EditText editemail;
     private EditText editNombre;
     private ProgressDialog progressDialog;
     private FirebaseAuth firebaseAuth;
     DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarusuario);

      firebaseAuth = FirebaseAuth.getInstance();
      mDatabase = FirebaseDatabase.getInstance().getReference();



      editContra = (EditText) findViewById(R.id.editContra);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editemail = (EditText) findViewById(R.id.editemail);
        editNombre = (EditText) findViewById(R.id.editNombre);
        btnTengocuenta = (Button) findViewById(R.id.btnTengocuenta);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        progressDialog = new ProgressDialog(this);
        btnRegistrar.setOnClickListener(this);

        btnTengocuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registrarusuario.this, login.class));
                //finish();
            }
        });
    }

    private void registrarUsuario(){
        //obtenemos los datos desde la caja de datos del texto
        final String Nombre = editNombre.getText().toString().trim();
        final String Email = editemail.getText().toString().trim();
        final String Usuario = editUsuario.getText().toString().trim();
        final String Contraseña = editContra.getText().toString().trim();


        //Verificamos si las cajas estan vacias
        if(TextUtils.isEmpty(Nombre)){
            Toast.makeText(this,"Se debe ingresar un Nombre",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Se debe ingresar un Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Usuario)){
            Toast.makeText(this,"Se debe ingresar un Usuario",Toast.LENGTH_LONG).show();
            return;
        }//confiurar para validaciones
        if(TextUtils.isEmpty(Contraseña)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email,Contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){

                   Map<String, Object> map = new HashMap<>();
                   map.put("Nombre", Nombre);
                   map.put("Email", Email);
                   map.put("Usuario", Usuario);
                   map.put("Contraseña", Contraseña);

                   String id = firebaseAuth.getCurrentUser().getUid();
                   mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task2) {
                           if (task2.isSuccessful()) {
                               startActivity(new Intent(registrarusuario.this, login.class));

                           } else {
                               Toast.makeText(registrarusuario.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_LONG).show();
                           }
                       }
                   });
               }
               else{
                   Toast.makeText(registrarusuario.this,"No se pudo registrar el usuario",Toast.LENGTH_LONG).show();
               }
               progressDialog.dismiss();
            }
        });



    }

    @Override
    public void onClick(View v) {
    registrarUsuario();
    }


}
