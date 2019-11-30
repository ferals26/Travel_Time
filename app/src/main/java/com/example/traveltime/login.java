package com.example.traveltime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText editContra;
    private EditText editemail;
    private Button btnIniciar;

    private String email = "";
    private String contraseña = "";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editContra = (EditText) findViewById(R.id.editContra);
        editemail = (EditText) findViewById(R.id.editemail);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editemail.getText().toString();
                contraseña = editContra.getText().toString();

                if(!email.isEmpty() && !contraseña.isEmpty()){
                loginUser();
                }
                else
                {
                    Toast.makeText(login.this,"Completa los campos",Toast.LENGTH_LONG).show();
                }

            }
        });



        Button Registrar = findViewById(R.id.btnRegistrarse);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, registrarusuario.class));
            }
        });

    }

    private void loginUser(){
            mAuth.signInWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   startActivity(new Intent(login.this, inicio.class));
                   finish();
               }
               else {
                   Toast.makeText(login.this,"No se pudo iniciar sesion, compruebe los datos" ,Toast.LENGTH_SHORT).show();
               }
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(login.this, inicio.class) );
            finish();
        }

    }
}
