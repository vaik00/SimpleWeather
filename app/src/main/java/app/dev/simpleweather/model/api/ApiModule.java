package app.dev.simpleweather.model.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class ApiModule {
    private static ApiInterface service;
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static ApiInterface getService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient.Builder().build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiInterface.class);
            return service;
        } else {
            return service;
        }
    }
}
