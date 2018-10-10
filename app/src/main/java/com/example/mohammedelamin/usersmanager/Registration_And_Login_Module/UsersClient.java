package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohammed Elamin on 10/9/2018.
 */

public class UsersClient {
    private static final String BASE_URL = "http://192.168.43.252/projects/MyApi/public/";
    private static final UsersClient ourInstance = new UsersClient();
    private Retrofit retrofit;

    public static synchronized UsersClient getInstance() {
        return ourInstance;
    }

    private UsersClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)  /*add the base url to your instance */
                .addConverterFactory(GsonConverterFactory.create())   /*add factory that parse your the data*/
                .build();

    }
    public  UserApi getUserApi()
    {
        return  retrofit.create(UserApi.class);
    }


}
