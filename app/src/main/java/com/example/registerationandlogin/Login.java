package com.example.registerationandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText loginUsername, loginPassword;
    private Button loginButton;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        db = new DBHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = loginUsername.getText().toString();
                String pass = loginPassword.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean check = db.checkUsernamePassword(user, pass);
                    if (check){
                        Toast.makeText(Login.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, TempDashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}