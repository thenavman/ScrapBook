package com.example.scrapbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText name, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        login = findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });


    }
    private  void validate(String userName, String userPassword){
        if((userName.equals("admin") ) && (userPassword.equals("1234")))
        {
            Intent intent= new Intent(Login.this,welcome.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Enter Correct Details", Toast.LENGTH_SHORT).show();
        }
    }



}
