package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammed Elamin on 10/10/2018.
 */

public class UserLoginResponse {

@SerializedName("message")
    private  String message;
    @SerializedName("error")
    private  boolean error;

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public User getUser() {
        return user;
    }

    public UserLoginResponse(String message, boolean error, User user) {
        this.message = message;
        this.error = error;
        this.user = user;
    }

    @SerializedName("user")
    private  User user;
}
