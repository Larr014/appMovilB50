package com.example.appmovilb50;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmovilb50.Modelo.Alumno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    Button btnAgregar,btnMostrarAlumnos;
    EditText etNombre,etApellido,etEdad,etRut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etEdad = (EditText)  findViewById(R.id.etEdad);
        etRut = (EditText) findViewById(R.id.etRut);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                insertar();
            }
        });
        llamadoActivityMostrarAlumnos();

    }

    private void llamadoActivityMostrarAlumnos() {
        btnMostrarAlumnos = (Button) findViewById(R.id.btnMostrarAlumnos);
        btnMostrarAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListadoAlumnos.class);
                startActivity(i);
            }
        });
    }

    public void insertar(){
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String rut = etRut.getText().toString();
        String key = UUID.randomUUID().toString();
        Alumno a = new Alumno(key,rut,nombre,apellido,edad);


        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Alumno");
        myRef.child(key).setValue(a);
    }
}























