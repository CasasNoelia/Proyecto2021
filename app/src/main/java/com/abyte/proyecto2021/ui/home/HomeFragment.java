package com.abyte.proyecto2021.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abyte.proyecto2021.Adapter.AdapterMascotas;
import com.abyte.proyecto2021.BD.dboMascotas;
import com.abyte.proyecto2021.Class.Mascota;
import com.abyte.proyecto2021.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Mascota> lista;
    RecyclerView recyclermascotas;
    dboMascotas dbo;
    Context c;
    String datos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        lista = new ArrayList<>();
        recyclermascotas= (RecyclerView) root.findViewById(R.id.RecyclerId);
        recyclermascotas.setLayoutManager(new LinearLayoutManager(c));

        ConsulaMascotas();
        AdapterMascotas adapter = new AdapterMascotas(lista);
        recyclermascotas.setAdapter(adapter);

        return root;
    }

    private void ConsulaMascotas() {
        dbo = dboMascotas.getInstance(c);
        lista=dbo.selectMascota();
    }
}