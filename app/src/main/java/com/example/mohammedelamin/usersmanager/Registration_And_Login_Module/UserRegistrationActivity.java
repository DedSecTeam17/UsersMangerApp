package com.example.mohammedelamin.usersmanager.Registration_And_Login_Module;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedelamin.usersmanager.R;
import com.example.mohammedelamin.usersmanager.Validator.FormValidation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistrationActivity extends AppCompatActivity {


    private EditText user_name;
    private EditText email;
    private EditText password;
    private EditText school;
    private Button sign_up;
    private User user;
    private TextView toSignIn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name = (EditText) findViewById(R.id.reg_username);
        email = (EditText) findViewById(R.id.reg_email);
        school = (EditText) findViewById(R.id.reg_school);
        password = (EditText) findViewById(R.id.reg_password);
        sign_up = (Button) findViewById(R.id.Regsteration_btn);
        toSignIn=(TextView)findViewById(R.id.Sign_in_text);
        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserLoginActivity.class);
                startActivity(intent);
            }
        });
        progressDialog=new ProgressDialog(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {


        user = User.newUser()
                .username(user_name.getText().toString())
                .email(email.getText().toString())
                .school(school.getText().toString())
                .password(password.getText().toString())
                .build();

        if (!user.getUsername().isEmpty() && !user.getEmail().isEmpty() && !user.getSchool().isEmpty() && !user.getPassword().isEmpty()) {

//                    validate data ------->

            if (FormValidation.getInstance().checkEmail(user.getEmail()) && FormValidation.getInstance().checkuserName(user.getUsername()) && FormValidation.getInstance().checkPassword(user.getPassword()) && FormValidation.getInstance().checkuserName(user.getSchool())) {
//                Toast.makeText(getApplicationContext(), "ok lets go", Toast.LENGTH_LONG).show();
                progressDialog.setMessage("wait..");
                progressDialog.setTitle("Sign Up");
                progressDialog.show();
                Call<UserCreationResponse> create_user_call = UsersClient.getInstance().getUserApi().UserCreate(user.getEmail(), user.getPassword(), user.getUsername(), user.getSchool());
                create_user_call.enqueue(new Callback<UserCreationResponse>() {
                    @Override
                    public void onResponse(Call<UserCreationResponse> call, Response<UserCreationResponse> response) {
                            UserCreationResponse userCreationResponse=response.body();
                            if (response.code()==201)
                            {
                                progressDialog.dismiss();

                                assert userCreationResponse != null;
                                Toast.makeText(getApplicationContext(),userCreationResponse.getMessage(), Toast.LENGTH_LONG).show();
                            }else if (response.code()==422)
                            {
                                progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(),"User Already Exist", Toast.LENGTH_LONG).show();
                            }

                    }


                    @Override
                    public void onFailure(Call<UserCreationResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
//                        all data are valid now you can register
            } else {
                if (!FormValidation.getInstance().checkEmail(user.getEmail())) {
                    email.setError("not valid email");
                    email.requestFocus();

                } else {

                }


                if (!FormValidation.getInstance().checkuserName(user.getUsername())) {
                    user_name.setError("not valid user name ");
                    user_name.requestFocus();
                } else {

                }

                if (!FormValidation.getInstance().checkuserName(user.getSchool())) {
                    school.setError("not valid input");
                    school.requestFocus();
                } else {

                }

                if (!FormValidation.getInstance().checkPassword(user.getPassword())) {
                    password.setError("not valid password");
                    password.requestFocus();
                } else {

                }
            }
//                    log the user
        } else {
            if (user.getUsername().isEmpty()) {
                user_name.setError("user name is required");
                user_name.requestFocus();
            }
            if (user.getEmail().isEmpty()) {
                email.setError("email is required");
                email.requestFocus();
            }
            if (user.getSchool().isEmpty()) {
                school.setError("school is required");
                school.requestFocus();
            }
            if (user.getPassword().isEmpty()) {
                password.setError("password is required");
                password.requestFocus();
            }
//                    validate the user
        }
    }
}
