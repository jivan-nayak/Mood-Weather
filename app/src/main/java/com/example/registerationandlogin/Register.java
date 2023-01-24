package com.example.registerationandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText userName, password, password1;
    private Button register, login;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String repass = password1.getText().toString();
                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(Register.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        boolean checkUser = db.checkUser(user);
                        if(!checkUser){
                            boolean insert = db.insertData(user, pass);
                            if (insert){
                                Toast.makeText(Register.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "User Already Exists Please Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

    }
}