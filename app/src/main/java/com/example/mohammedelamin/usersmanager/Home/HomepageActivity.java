package com.example.mohammedelamin.usersmanager.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedelamin.usersmanager.R;
import com.example.mohammedelamin.usersmanager.Registration_And_Login_Module.User;

public class HomepageActivity extends AppCompatActivity {

    private TextView name;
    private  TextView email;
    private  TextView school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompage);
        name=(TextView)findViewById(R.id.name);
        school=(TextView)findViewById(R.id.school);
        email=(TextView)findViewById(R.id.email);

        Bundle bundle=getIntent().getExtras();
        User user=User.newUser()
                .id(bundle.getInt("id"))
                .username(bundle.getString("name"))
                .email(bundle.getString("email"))
                .school(bundle.getString("school"))
                .build();
        name.setText("Name: \t"+user.getUsername());
        email.setText("Email: \t"+user.getEmail());
        school.setText("School: \t"+user.getSchool());
        
    }
}
