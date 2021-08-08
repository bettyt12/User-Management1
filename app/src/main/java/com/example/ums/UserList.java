package com.example.ums;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;
    UserAdapter adapter;
    public SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        databaseHelper=new DatabaseHelper(this);
        sharedPreferenceConfig=new SharedPreferenceConfig(this);
       // getAllUser();
         adapter= new UserAdapter(this,getAllUser());


         recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private List<User> getAllUser() {
        Cursor cursor=databaseHelper.getAll();
        List<User> users=new ArrayList<>();
        while (cursor.moveToNext()){
            User user=new User(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5));
            users.add(user);
        }
       // adapter.notifyDataSetChanged();
        return users;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(R.id.my_account==item.getItemId()) {
            myAccount();
        }else if(R.id.logout==item.getItemId()){
            logout();
        }
        return true;


    }

    public void logout(){
        sharedPreferenceConfig.writeToPreference(false,"");
        startActivity(new Intent(this,Login.class));
        finishFromChild(this);
        finish();

    }
    private void myAccount() {

        Intent intent= new Intent(this,MyDetail.class);
        startActivity(intent);
    }

}
