package com.mygames.mygames.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fedosov on 9/6/16.
 */
public class LoginRequestJson {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("password")
    @Expose
    private String password;
    public LoginRequestJson(String login, String password) {
        this.login = login;
        this.password = password;
    }

}