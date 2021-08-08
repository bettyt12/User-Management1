package com.example.ums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    SharedPreferenceConfig sharedPreferenceConfig;
    DatabaseHelper helper;
    EditText user_nameFilled, passwordFilled;
    ImageView imageView,imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView=findViewById(R.id.imageView2);
        imageView1=findViewById(R.id.imageView3);
        user_nameFilled = findViewById(R.id.user_name);
        passwordFilled = findViewById(R.id.password);
        helper = new DatabaseHelper(this);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        imageView1.setVisibility(View.INVISIBLE);
        if (sharedPreferenceConfig.readFromPreference()) {
            startActivity(new Intent(this, UserList.class));
            finish();
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                imageView1.setVisibility(View.VISIBLE);

                passwordFilled.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                passwordFilled.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    public void login(View view) {

        String userName = user_nameFilled.getText().toString();
        String password = passwordFilled.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty()) {
            if (helper.is_vallide(userName, password)) {
                Toast.makeText(this, "Succesfully Login", Toast.LENGTH_LONG).show();
                sharedPreferenceConfig.writeToPreference(true,userName);//saving true if Login successfully

                startActivity(new Intent(this, UserList.class));
                Log.e("logg",sharedPreferenceConfig.readUserFromPreference());
                finish();


            } else {


                Toast.makeText(this, "Invallid U Name or password..try..again", Toast.LENGTH_LONG).show();
                user_nameFilled.setText("");
                passwordFilled.setText("");
            }
        }else{
            Toast.makeText(this, "Field Required", Toast.LENGTH_LONG).show();

        }
    }
        public void create (View view){

            startActivity(new Intent(this, Register.class));
        }


}
