package com.example.scrapbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class form extends AppCompatActivity implements View.OnClickListener {
    EditText t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button b1;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        connect();
        getid();
        db = openOrCreateDatabase("SlamBookDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS SlamBook(myname VARCHAR,Nickname VARCHAR,D_O_B VARCHAR,Address VARCHAR,MobileNo VARCHAR, instagram_Id VARCHAR, Zodiac_Sign VARCHAR, Favourite_Quote VARCHAR ) ");

    }

    private void getid() {
        b1.setOnClickListener(this);

    }

    private void connect() {
        b1=findViewById(R.id.btnsave);
        t1 = findViewById(R.id.myname);
        t2 = findViewById(R.id.nickname);
        // t3=findViewById(R.id.dob);
        t4 = findViewById(R.id.address);
        t5 = findViewById(R.id.mobileno);
        t6 = findViewById(R.id.instagramid);

        t8 = findViewById(R.id.favquote);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsave:
                if (t3.getText().toString().trim().length() == 0 ||
                        t1.getText().toString().trim().length() == 0 ||
                        t2.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showMessege("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('" + t3.getText() + "','" + t1.getText() +
                        "','" + t2.getText() + "');");
                showMessege("Success", "Record added");


                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void showMessege(String title, String message) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

