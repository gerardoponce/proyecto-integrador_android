package com.pe.project.mobile.food.rest;

import com.pe.project.mobile.food.model.Membresia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    String API_BASE_URL = "http://18.206.242.102:8080/api/v1/";

    @GET("membresias")
    Call<List<Membresia>> obtenerMembresias();

}
