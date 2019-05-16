package com.example.scrapbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DispActivity extends AppCompatActivity implements View.OnClickListener {
    //Declaring ToolBar
    Toolbar toolbar;
    ActionMode actionMode;
    //Main Variables Declaration
    SQLiteDatabase db;
    EditText t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button b1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                startActivity(new Intent(DispActivity.this, welcome.class));
                Toast.makeText(DispActivity.this, "View", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_exit:

                //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //  startActivity(new Intent(getApplicationContext(), HomeSp.class));
                //}
                // Toast.makeText(Form2.this, "Exit", Toast.LENGTH_LONG).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add");
        initiate();
        db = openOrCreateDatabase("SlamBookDB", Context.MODE_PRIVATE, null);
     //   db.execSQL("CREATE TABLE IF NOT EXISTS SlamBook(myname VARCHAR,Nickname VARCHAR,D_O_B VARCHAR,Address VARCHAR,MobileNo VARCHAR, instagram_Id VARCHAR, Zodiac_Sign VARCHAR, Favourite_Quote VARCHAR, Gender VARCHAR ) ");
        getid();
    }

    private void getid() {
        b1.setOnClickListener(this);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void clearText() {
        t3.setText("");
        t1.setText("");
        t2.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");

    }


    private void initiate() {
        //connecting Variables with id;
        t3 = findViewById(R.id.dob);
        b1 = findViewById(R.id.btnshow);
        t1 = findViewById(R.id.mynm);
        t2 = findViewById(R.id.nkname);
        t4 = findViewById(R.id.add);
        t5 = findViewById(R.id.mobno);
        t6 = findViewById(R.id.instaid);
        t8 = findViewById(R.id.favqt);
        t9 = findViewById(R.id.gen);
        t7 = findViewById(R.id.zod);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnshow:
                if (t1.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Name ");
                    return;
                }
                Cursor c2 = db.rawQuery("SELECT * FROM SlamBook WHERE myname='" + t1.getText() + "'", null);
                if (c2.moveToFirst()) {
                     t1.setText(c2.getString(0));
                    t2.setText(c2.getString(1));
                    t3.setText(c2.getString(2));
                    t4.setText(c2.getString(3));
                    t5.setText(c2.getString(4));
                    t6.setText(c2.getString(5));
                    t8.setText(c2.getString(7));
                    t7.setText(c2.getString(6));
                    t9.setText(c2.getString(8));


                } else {
                    showMessage("Error", "Invalid Name");
                    clearText();
                }
                break;

        }
    }
}