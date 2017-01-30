package com.mygames.mygames.api;


import com.mygames.mygames.R;
import com.mygames.mygames.api.json.User;
import com.mygames.mygames.api.service.ImgdbService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by fedosov on 9/6/16.
 */
public class RestClient {

    private static ImgdbService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:80/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ImgdbService.class);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    private ImgdbService getWeatherService() {
        return apiService;
    }


    public Observable<User> getUsers(){
       return   apiService.getUsers("token");
    }
}