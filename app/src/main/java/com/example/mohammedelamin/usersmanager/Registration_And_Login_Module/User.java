package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammed Elamin on 10/9/2018.
 */


// UserBuilder Pattern
public class User {
    @SerializedName("email")
    private  String Email;
    @SerializedName("password")
    private  String password;
    @SerializedName("school")
    private  String school;
    @SerializedName("name")
    private  String username;
    @SerializedName("id")
    private  int id;
    public User(String email, String password, String school, String username, int id) {
        Email = email;
        this.password = password;
        this.school = school;
        this.username = username;
        this.id = id;
    }
    //    user loginAdmin
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //    user registration


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public  User(UserBuilder UserBuilder)
    {
        setEmail(UserBuilder.email);
        setPassword(UserBuilder.password);
        setSchool(UserBuilder.school);
        setUsername(UserBuilder.username);
        setId(UserBuilder.id);

    }
    public  static UserBuilder newUser(){
        return  new UserBuilder();
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getEmail() {
        return this.Email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }





    public  static class  UserBuilder{
        private  String email;
        private  String password;
        private  int id;
        private  String school;
        private  String username;
        private UserBuilder()
        {
        }
        public  UserBuilder email(String email)
        {
            this.email=email;
            return  this;
        }
        public  UserBuilder password(String password)
        {
            this.password=password;
            return  this;
        }


        public  UserBuilder school(String school)
        {
            this.school=school;
            return  this;
        }

        public  UserBuilder username(String username)
        {
            this.username=username;
            return  this;
        }

        public  UserBuilder id(int id)
        {
            this.id=id;
            return  this;
        }
        //        TRUE FOR ADMIN FALSE FOR SELLER

        public  User build(){
            return  new User(this);
        }




    }
}
