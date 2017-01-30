package com.mygames.mygames.api.json;

/**
 * Created by fedosov on 9/6/16.
 */
public class UploadPhotoJson {
    String url;
    String comment;
    String token;

    public UploadPhotoJson(String url,String comment,String token) {
        this.url = url;
        this.comment = comment;
        this.token = token;
    }
}