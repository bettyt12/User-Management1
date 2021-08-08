package com.example.ums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MyDetail extends AppCompatActivity {

     SharedPreferenceConfig sharedPreferenceConfig;
    DatabaseHelper databaseHelper;

    TextView fullName,userName,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        sharedPreferenceConfig=new SharedPreferenceConfig(this);
        databaseHelper=new DatabaseHelper(this);








        fullName=findViewById(R.id.full_name);
        userName=findViewById(R.id.user_name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
         setData(sharedPreferenceConfig.readUserFromPreference());

}

    public void setData(String loged ){
        Cursor cursor=databaseHelper.getLogedUser(loged);
        cursor.moveToFirst();
        fullName.setText(cursor.getString(0));
        userName.setText(cursor.getString(1));
        email.setText(cursor.getString(2));
        phone.setText(cursor.getString(4));
    }





}
