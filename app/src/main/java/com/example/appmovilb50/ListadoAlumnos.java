package com.example.appmovilb50;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.appmovilb50.Modelo.Alumno;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListadoAlumnos extends AppCompatActivity {
    FirebaseDatabase database;
    ListView lvAlumnos;
    ArrayList<Alumno> alumnos;
    ArrayAdapter<Alumno> adaptadorAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_alumnos);
        lvAlumnos = (ListView) findViewById(R.id.lvAlumnos);
        alumnos = new ArrayList<Alumno>();
        //Esto por cada alumno de la bd
        //Funcion que cargue la bd
        cargarBd();
        //alumnos.add();
        //
    }

    private void cargarBd() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference alumnosRef = database.getReference("Alumno");
        ValueEventListener alumnosListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot d: snapshot.getChildren()) {
                    String key = d.child("key").getValue().toString();
                    String nombre = d.child("nombre").getValue().toString();
                    String apellido = d.child("apellido").getValue().toString();
                    int edad = Integer.parseInt(d.child("edad").getValue().toString());
                    String rut = d.child("rut").getValue().toString();
                    Alumno a = new Alumno(key,rut,nombre,apellido,edad);
                    alumnos.add(a);
                }
                adaptadorAlumnos = new ArrayAdapter<Alumno>(getApplicationContext(), android.R.layout.simple_list_item_1,alumnos);
                lvAlumnos.setAdapter(adaptadorAlumnos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Un error");
            }
        };
        alumnosRef.addValueEventListener(alumnosListener);

    }

}