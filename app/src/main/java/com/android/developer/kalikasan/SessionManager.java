/*package com.android.developer.kalikasan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String POSITION = "POSITION";
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String OFFICE = "OFFICE";
    public static final String DIVISION = "DIVISION";


    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //CREATE SESSION IN MAIN ACTIVITY
    public void createSession(String firstName,String lastName, String position,
                             String year,String month,String office,String division){

                editor.putBoolean(LOGIN,true);
                editor.putString(NAME,firstName);
                editor.putString(LAST_NAME,lastName);
                editor.putString(POSITION,position);
                editor.putString(YEAR,year);
                editor.putString(MONTH,month);
                editor.putString(OFFICE,office);
                editor.putString(DIVISION,division);
                editor.apply();


    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);

    }

    public void checkLogin(){

        if(!this.isLogin()){
            Intent i = new Intent(context,LoginActivity.class);
            context.startActivity(i);
            ((MenuActivity)context).finish();

        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(LAST_NAME,sharedPreferences.getString(LAST_NAME,null));
        user.put(POSITION,sharedPreferences.getString(POSITION,null));
        user.put(YEAR,sharedPreferences.getString(YEAR,null));
        user.put(MONTH,sharedPreferences.getString(MONTH,null));
        user.put(OFFICE,sharedPreferences.getString(OFFICE,null));
        user.put(DIVISION,sharedPreferences.getString(DIVISION,null));

        return user;

    }

    public void logOut(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((MenuActivity)context).finish();
    }


}
*/