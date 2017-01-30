package com.mygames.mygames.api.service;

import com.mygames.mygames.api.json.LoginRequestJson;
import com.mygames.mygames.api.json.User;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Max on 31.01.2017.
 */
public interface ImgdbService {

    @POST("/reg")
    rx.Observable<String> reg(@Body  HashMap<String, Object> body);

    @POST("/im")
    rx.Observable<User> im();

    @POST("/im/upload")
    rx.Observable<String> uploadImage(@Body  HashMap<String, Object> body);

    @POST("/im/addinsta")
    rx.Observable<String> addinsta(@Body HashMap<String, Object> body);

    @GET("/im")
    rx.Observable<User> getUsers(@Header("x-access-token") String token);

    @POST("login")
    rx.Observable<User> loginToken(@Body LoginRequestJson body);
}
