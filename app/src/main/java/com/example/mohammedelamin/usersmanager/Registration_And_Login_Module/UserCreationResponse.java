package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammed Elamin on 10/10/2018.
 */

public class UserCreationResponse {

    @SerializedName("error")
    private  boolean error;
    @SerializedName("message")
    private  String message;
    public UserCreationResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
