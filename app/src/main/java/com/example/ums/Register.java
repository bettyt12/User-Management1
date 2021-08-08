package com.example.ums;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
   Button save_btn;
   EditText userName,fullName,email,mobile,password;
   RadioGroup radioGroup;
   RadioButton female,male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper=new DatabaseHelper(this);
        save_btn=findViewById(R.id.save);
        userName=findViewById(R.id.user_name);
        fullName=findViewById(R.id.full_name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.phone);
        password=findViewById(R.id.password);

        radioGroup=findViewById(R.id.group);
       female=findViewById(R.id.female);
       male=findViewById(R.id.male);

    }

    public void saveUserData(View view) {

        String sex="";
        int id=radioGroup.getCheckedRadioButtonId();
        if(id==R.id.male){
            sex="Male";
        }else if (id==R.id.female){
            sex="Female";
        }
        if(!userName.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

            boolean result = databaseHelper.insertUser(fullName.getText().toString(), userName.getText().toString()
                    , email.getText().toString(), password.getText().
                            toString(), mobile.getText().toString(), sex);
            if (result) {
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error!! while saved", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "User Name or password required", Toast.LENGTH_LONG).show();

        }
    }
}
