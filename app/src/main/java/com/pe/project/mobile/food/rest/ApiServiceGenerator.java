package com.pe.project.mobile.food.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceGenerator {

    // Cliente Http que hace la conexion
    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    // Construye una instancia Retrofit
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiService.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    // Objeto Retrofit para hacer la conexion
    // Es singleton
    private static Retrofit retrofit;

    public ApiServiceGenerator() {

    }

    // Método que permite la generación de un cliente a un servicio a partir del objeto Retrofit
    public static <S> S createService(Class<S> serviceClass) {
        // Se valida si el objeto Retrofit ya ha sido instanciado, caso contrario lo instancia
        if (retrofit == null) {
            // Se instancia un interceptador de la llamada al servicio que mostrará en consola
            // los parámetros y valores de la petición/respuesta
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Se agrega el interceptador
            httpClientBuilder.addInterceptor(httpLoggingInterceptor);

            retrofit = builder.client(httpClientBuilder.build()).build();
        }
        return retrofit.create(serviceClass);
    }

}
