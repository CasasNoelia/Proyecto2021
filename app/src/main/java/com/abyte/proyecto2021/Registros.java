package com.abyte.proyecto2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abyte.proyecto2021.BD.dboUsuarios;
import com.abyte.proyecto2021.Class.Usuarios;

import java.util.ArrayList;

public class Registros extends AppCompatActivity implements View.OnClickListener {
    EditText nombreusuario, pass, nombre, tel, mail;
    Button btnAceptar, btnCancelar;
    dboUsuarios dbo;
    ArrayList<Usuarios> lista;
    Usuarios usu = new Usuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        getSupportActionBar().hide();

        nombreusuario=(EditText)findViewById(R.id.nombreusuario);
        pass=(EditText)findViewById(R.id.pass);
        nombre=(EditText)findViewById(R.id.nombre);
        tel=(EditText)findViewById(R.id.tel);
        mail=(EditText)findViewById(R.id.mail);
        dbo= dboUsuarios.getInstance(this);

        btnAceptar=(Button)findViewById(R.id.Aceptar);
        btnCancelar=(Button)findViewById(R.id.cancelar);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Aceptar:
                Regsistarusu(usu);
                break;
            case R.id.cancelar:
                Intent i =new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    public void Regsistarusu(Usuarios usu){
        try {
            usu.setNombreUsuario(nombreusuario.getText().toString());
            usu.setPassword(pass.getText().toString());
            usu.setNombre(nombre.getText().toString());
            usu.setTelefono(tel.getText().toString());
            usu.setMail(mail.getText().toString());

            if (nombreusuario.getText().toString().isEmpty() || pass.getText().toString().isEmpty()||nombre.getText().toString().isEmpty()||tel.getText().toString().isEmpty()||mail.getText().toString().isEmpty()){
                Toast.makeText(this, "Error: Uno de los Campos esta Vacio", Toast.LENGTH_LONG).show();
                return;
            }else if(dbo.registrosdatos(usu)) {
                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                Intent i =new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Usuario Ya Registrado", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }




}