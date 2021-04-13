package com.abyte.proyecto2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.abyte.proyecto2021.Adapter.AdapterMascotas;
import com.abyte.proyecto2021.BD.dboMascotas;
import com.abyte.proyecto2021.Class.Mascota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    String datos;
    ArrayList<Mascota> lista;
    RecyclerView recyclermascotas;
    dboMascotas dbo;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Llamo al boton Agregar mascota
        FloatingActionButton fab = findViewById(R.id.fab);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            datos = parametros.getString("Usuario");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(view.getContext(), MascotaActivity.class);
                i.putExtra("Usuario",datos);
                startActivity(i);
            }
        });


           /* lista = new ArrayList<>();
            recyclermascotas= (RecyclerView) findViewById(R.id.RecyclerId);
            recyclermascotas.setLayoutManager(new LinearLayoutManager(this));

            AdapterMascotas adapter = new AdapterMascotas(lista);
            recyclermascotas.setAdapter(adapter);*/

        //ConsulaMascotas();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




    }

    private void ConsulaMascotas() {
        dbo = dboMascotas.getInstance(this);
        lista=dbo.selectMascota();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i =new Intent(this, Settings.class);
                i.putExtra("Usuario",datos);
                startActivity(i);
                return true;

            default:
                final boolean b = super.onOptionsItemSelected(item);
                return b;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}