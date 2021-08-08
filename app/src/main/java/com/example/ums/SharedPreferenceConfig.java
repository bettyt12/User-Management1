package com.example.ums;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SharedPreferenceConfig {

    private Context context;
    SharedPreferences sharedPreferences;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("SP_NAME",Context.MODE_PRIVATE);
    }


    public void writeToPreference(boolean status,String user){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("LOGIN_STATUS",status);
        editor.putString("LOGED_IN_USER",user);
        editor.commit();
    }

    public boolean readFromPreference(){

        boolean status;
                status=sharedPreferences.getBoolean("LOGIN_STATUS",false);
                return status;
    }

    public String readUserFromPreference(){
        return sharedPreferences.getString("LOGED_IN_USER","");
    }
}
