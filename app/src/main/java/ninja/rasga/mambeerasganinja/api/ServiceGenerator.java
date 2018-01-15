package ninja.rasga.mambeerasganinja.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by magdiel on 14/01/18.
 */

public class ServiceGenerator {

    public static final String BASE_URL = "http://rasga.ninja:8888/api/";
    public static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass) {
        System.setProperty("http.keepAlive", "false");
        System.setProperty("Connection", "close");

        //Instancia do interceptador das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS);

        httpClient.addInterceptor(loggingInterceptor);

        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.S")
                .create();

        //Instância do retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);
    }
}

