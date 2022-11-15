package com.example.appmovilb50;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.appmovilb50.Modelo.Alumno;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ModificarEliminarAlumno extends AppCompatActivity {
    Alumno a;
    EditText etRut,etNombre,etApellido,etEdad;
    Button btnModificar,btnEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_eliminar_alumno);
        obtenerAlumno();
        cargarAlumno();
    }
    public void obtenerAlumno(){
        Bundle b = this.getIntent().getExtras();
        String alumnoString = b.getString("alumno");
        Gson gson = new Gson();
        this.a = (Alumno) gson.fromJson(alumnoString,Alumno.class);
        System.out.println(this.a.nombre);
    }
    public void cargarAlumno(){
        etRut = (EditText) findViewById(R.id.etRutModificar);
        etNombre = (EditText) findViewById(R.id.etNombreModificar);
        etApellido = (EditText) findViewById(R.id.etApellidoModificar);
        etEdad = (EditText) findViewById(R.id.etEdadModificar);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        System.out.println("NOMBRE: "+this.a.nombre);
        etRut.setText(this.a.rut);
        etNombre.setText(this.a.nombre);
        etApellido.setText(this.a.apellido);
        etEdad.setText(""+this.a.edad);

    }
}