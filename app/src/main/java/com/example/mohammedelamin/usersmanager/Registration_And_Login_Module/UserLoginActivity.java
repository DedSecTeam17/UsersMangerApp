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

import com.example.mohammedelamin.usersmanager.Home.HomepageActivity;
import com.example.mohammedelamin.usersmanager.R;
import com.example.mohammedelamin.usersmanager.Validator.FormValidation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    private EditText email;
    private  EditText password;
    private TextView toSignUp;
    private Button singIn;
    private User user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        email=(EditText)findViewById(R.id.login_email);
        password=(EditText)findViewById(R.id.login_password);
        singIn=(Button)findViewById(R.id.login_btn);
        toSignUp=(TextView)findViewById(R.id.Sign_up_text);
        progressDialog=new ProgressDialog(this);

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(intent);
            }
        });



        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



    }

    private void signIn() {
        user = User.newUser()
                .email(email.getText().toString())
                .password(password.getText().toString())
                .build();

        if ( !user.getEmail().isEmpty() &&  !user.getPassword().isEmpty()) {

//                    validate data ------->

            if (FormValidation.getInstance().checkEmail(user.getEmail())  && FormValidation.getInstance().checkPassword(user.getPassword()) ) {
//                Toast.makeText(getApplicationContext(), "ok lets go", Toast.LENGTH_LONG).show();
                progressDialog.setMessage("wait...");
                progressDialog.setTitle("Sign In");
                progressDialog.show();

                Call<UserLoginResponse> call=UsersClient.getInstance().getUserApi().UserLogin(user.getEmail(),user.getPassword());
                call.enqueue(new Callback<UserLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                        UserLoginResponse user=response.body();
                        if (response.code()==201)
                        {
                            progressDialog.dismiss();
                            Intent intent=new Intent(getApplicationContext(), HomepageActivity.class);
                            intent.putExtra("id",user.getUser().getId());
                            intent.putExtra("name",user.getUser().getUsername());
                            intent.putExtra("email",user.getUser().getEmail());
                            intent.putExtra("school",user.getUser().getSchool());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"welcome \t"+user.getUser().getUsername(),Toast.LENGTH_LONG).show();
                        }else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"email or password wrong",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<UserLoginResponse> call, Throwable t) {

                    }
                });




//                        all data are valid now you can register
            } else {
                if (!FormValidation.getInstance().checkEmail(user.getEmail())) {
                    email.setError("not valid email");
                    email.requestFocus();

                }
                if (!FormValidation.getInstance().checkPassword(user.getPassword())) {
                    password.setError("not valid password");
                    password.requestFocus();
                }
            }
//                    log the user
        } else {

            if (user.getEmail().isEmpty()) {
                email.setError("email is required");
                email.requestFocus();
            }
            if (user.getPassword().isEmpty()) {
                password.setError("password is required");
                password.requestFocus();
            }
//                    validate the user
        }
    }
}
