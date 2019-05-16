package com.example.scrapbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Form2 extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper mdbhelper;
    //Declaring ToolBar
    Toolbar toolbar;
    ActionMode actionMode;
    //Main Variables Declaration
    Button add, modify, delete, show, showall;
    SQLiteDatabase db;
    TextView t3;
    EditText t1, t2, t4, t5, t6, t7, t8, t9;

    //Spinner spinner;
    RadioGroup mf;
    RadioButton m, f;
    DatePickerDialog datePickerDialog;
    String g;


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }
//Menu Option


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                startActivity(new Intent(Form2.this, welcome.class));
                Toast.makeText(Form2.this, "Home", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_exit:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                  startActivity(new Intent(getApplicationContext(), HomeSp.class));
                }
                 Toast.makeText(Form2.this, "Exit", Toast.LENGTH_LONG).show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Datatbase");

        //connecting Variables with id
        mf = findViewById(R.id.radiogrp1);
        m = findViewById(R.id.maleradio);
        f = findViewById(R.id.femaleradio);

        db = openOrCreateDatabase("slambookdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS slambook(myname VARCHAR,Nickname VARCHAR,Dob VARCHAR,Address VARCHAR,MobileNo VARCHAR, instagram_Id VARCHAR, Zodiac_Sign VARCHAR, Favourite_Quote VARCHAR,Gender VARCHAR ); ");


        t3 = findViewById(R.id.dob);
        t1 = findViewById(R.id.myname);
        t2 = findViewById(R.id.nickname);
        t4 = findViewById(R.id.address);
        t5 = findViewById(R.id.mobileno);
        t6 = findViewById(R.id.instagramid);
        t8 = findViewById(R.id.favquote);
        t9 = findViewById(R.id.gen);
        t7 = findViewById(R.id.btnz);
        add = findViewById(R.id.btnsave);
        show = findViewById(R.id.btnshow);
        modify = findViewById(R.id.btnmodify);
        delete = findViewById(R.id.btndelete);
        showall = findViewById(R.id.btnviewall);

        add.setOnClickListener(this);
        show.setOnClickListener(this);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
        showall.setOnClickListener(this);

       /* spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zodiac_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                String item = adapter.getItemAtPosition(position).toString();


                Toast.makeText(getApplicationContext(),
                        " " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
*/
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Form2.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                t3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsave:
                if (t3.getText().toString().trim().length() == 0 ||
                        t1.getText().toString().trim().length() == 0 ||
                        t2.getText().toString().trim().length() == 0 ||
                        t4.getText().toString().trim().length() == 0 || t7.getText().toString().trim().length() == 0 ||
                        t5.getText().toString().trim().length() == 0 || t6.getText().toString().trim().length() == 0 || t8.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    showMessege("Error", "Please enter all values");
                    return;
                }
            //    if (t7.getText().toString() != "aries" || t7.getText().toString() != "taurus" || t7.getText().toString() != "gemini" || t7.getText().toString() != "cancer" || t7.getText().toString() != "leo" || t7.getText().toString() != "vigro" || t7.getText().toString() != "libra" || t7.getText().toString() != "scorpion" || t7.getText().toString() != "sagittarius" || t7.getText().toString() != "capricon" || t7.getText().toString() != "aquarius" || t7.getText().toString() != "pisces") {
              //      showMessege("Invalid Zodiac Name", "Please Enter In small letters");
                //}
                if (t1.getText().toString().trim().length() >= 20 || t2.getText().toString().trim().length() >= 20 || t3.getText().toString().trim().length() >= 20 || t4.getText().toString().trim().length() >= 20 || t5.getText().toString().trim().length() >= 20 || t6.getText().toString().trim().length() >= 20 || t8.getText().toString().trim().length() >= 20) {
                    showMessege("Invalid Entry", "input shold be less than 20 characters");
                }
                db.execSQL("INSERT INTO slambook VALUES('" + t1.getText().toString() + "','" + t2.getText().toString() +
                        "','" + t3.getText().toString() + "','" + t4.getText().toString() + "','" + t5.getText().toString() + "','" + t6.getText().toString() + "','" + t7.getText().toString() + "','" + t8.getText().toString() + "','" + g + "');");

                showMessege("Success", "Record added");
                clearText();

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnshow:
                if (t1.getText().toString().trim().length() == 0) {
                    showMessege("Error", "Please enter Name ");
                    return;
                } else {
                    Cursor c3 = db.rawQuery("SELECT * FROM slambook WHERE myname='" + t1.getText() + "'", null);
                    if (c3.moveToFirst()) {
                        //t1.setText(c3.getString(0));
                        t2.setText(c3.getString(1));
                        t3.setText(c3.getString(2));
                        t4.setText(c3.getString(3));
                        t5.setText(c3.getString(4));
                        t6.setText(c3.getString(5));
                        t8.setText(c3.getString(7));
                        t7.setText(c3.getString(6));
                        t9.setText(c3.getString(8));


                    } else {
                        showMessage("Error", "Invalid Name");
                        clearText();
                    }
                }
                break;
            case R.id.btndelete:
                Toast.makeText(this, "Delete", Toast.LENGTH_LONG).show();
                if (t1.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Name");
                    return;
                }
                Cursor c1 = db.rawQuery("SELECT * FROM SlamBook WHERE myname='" + t1.getText().toString() + "'", null);
                if (c1.moveToFirst()) {
                    db.execSQL("DELETE FROM Slambook WHERE myname='" + t1.getText() + "'");
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid Name");
                }
                clearText();
                break;


            case R.id.btnviewall:
                Toast.makeText(this, "ViewAll", Toast.LENGTH_SHORT).show();
                Cursor c12 = db.rawQuery("SELECT * FROM slambook", null);
                if (c12.getCount() == 0) {
                    Toast.makeText(this, "no file", Toast.LENGTH_SHORT).show();
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c12.moveToNext()) {
                    buffer.append("Name: " + c12.getString(0) + "\n");
                    buffer.append("Nickname: " + c12.getString(1) + "\n");
                    buffer.append("DOB: " + c12.getString(2) + "\n");
                    buffer.append("Address: " + c12.getString(3) + "\n");
                    buffer.append("Mobile No: " + c12.getString(4) + "\n");
                    buffer.append("Instagram-ID " + c12.getString(5) + "\n");
                    buffer.append("Zodiac Sign: " + c12.getString(6) + "\n");
                    buffer.append("Favourite Quoto: " + c12.getString(7) + "\n");
                    buffer.append("Gender: " + c12.getString(8) + "\n\n");

                }
                Toast.makeText(this, "student", Toast.LENGTH_SHORT).show();
                showMessage("Student Details", buffer.toString());
                break;


           /*case R.id.btnmodify:
                Toast.makeText(this, "Modify", Toast.LENGTH_LONG).show();
                if (t1.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Name");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM SlamBook WHERE myname='" + t1.getText() + "'", null);

                if (c.moveToFirst()) {
                    if (t2.getText().toString().trim().length() == 0 && t3.getText().toString().trim().length() == 0) {

                        showMessage("Error", "Invalid nickname And D.O.B");
                    } else if (t2.getText().toString().trim().length() == 0) {
                        db.execSQL("UPDATE SlamBook SET D.O.B='" + t3.getText() +
                                "' WHERE myname='" + t1.getText() + "'");
                    } else if (t3.getText().toString().trim().length() == 0) {
                        db.execSQL("UPDATE SlamBook SET Nickname='" + t2.getText() +
                                "' WHERE myname='" + t1.getText() + "'");

                    } else {
                        db.execSQL("UPDATE SlamBook SET Nickname='" + t2.getText() + "',D.O.B='" + t3.getText() +
                                "' WHERE myname='" + t1.getText() + "'");
                        showMessage("Success", "Record Modified");
                    }
                } else {
                    showMessage("Error", "Invalid Name");
                }
                clearText();
                break;
*/
        }


    }

    private void showMessage(String error, String invalid_name) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);

        builder.setMessage(invalid_name);
        builder.show();

    }

    private void clearText() {
        t3.setText("");
        t1.setText("");
        t2.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");

    }


    private void showMessege(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onCheckedChanged(View view) {
        switch (view.getId()) {
            case R.id.maleradio:
                g = "male";
                Toast.makeText(getApplicationContext(), "male", Toast.LENGTH_SHORT).show();
                break;

            case R.id.femaleradio:
                g = "female";
                Toast.makeText(getApplicationContext(), "female", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
