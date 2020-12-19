package com.pe.project.mobile.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.pe.project.mobile.food.model.Membresia;
import com.pe.project.mobile.food.rest.ApiService;
import com.pe.project.mobile.food.rest.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnObtenerMembresias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnObtenerMembresias = findViewById(R.id.btnObtenerMembresias);

        btnObtenerMembresias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarServicioMembresia();
            }
        });
    }

    private void llamarServicioMembresia() {
        ApiService apiService = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Membresia>> llamada = apiService.obtenerMembresias();
        llamada.enqueue(new Callback<List<Membresia>>() {
            @Override
            public void onResponse(Call<List<Membresia>> call, Response<List<Membresia>> response) {
                if (response.isSuccessful()) {
                    List<Membresia> listaMembresias = response.body();
                    Toast.makeText(MainActivity.this, "Se encontraron: " + listaMembresias.size(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Membresia>> call, Throwable t) {

            }
        });
    }
}