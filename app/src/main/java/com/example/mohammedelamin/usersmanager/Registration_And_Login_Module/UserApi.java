package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Mohammed Elamin on 10/9/2018.
 */

public  interface UserApi {
//    post request for adding new user
    @FormUrlEncoded
    @POST("UserCreate")
     Call<UserCreationResponse> UserCreate(
             @Field("email") String email,
             @Field("password") String password,
             @Field("name") String name,
             @Field("school") String school
    );


    @FormUrlEncoded
    @POST("UserLogin")
    Call<UserLoginResponse> UserLogin(
            @Field("email") String email,
            @Field("password") String password
    );


}
