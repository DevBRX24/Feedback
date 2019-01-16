package com.android.developer.kalikasan;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button signUp, loginButton;
    private EditText username, password,firstName,lastName,position,year,month,office,division;
    private static final String URL_LOGIN = "http://192.168.254.130/Feedback/login.php";
    private ProgressBar loading;
    //SessionManager sessionManager;


    RelativeLayout relativeLayout1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            relativeLayout1.setVisibility(View.VISIBLE);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // sessionManager  = new SessionManager(this);

        position = findViewById(R.id.profilePosition);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        loading = findViewById(R.id.loadingLogin);

        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signupButton);

        //ANIMATE LOGIN
        relativeLayout1 = findViewById(R.id.relative1);
        handler.postDelayed(runnable, 2000);

        signUp.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        //LOGIN USER
      loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myUser = username.getText().toString().trim();
                String myPassword = password.getText().toString().trim();

                if(!myUser.isEmpty() || !myPassword.isEmpty()){

                    Login(myUser,myPassword);

                  //  Intent  intent = new Intent(LoginActivity.this,MenuActivity.class);
                  //   startActivity(intent);


                }else{
                    loading.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);
                    username.setError("Enter UserName");
                    password.setError("Enter Password");
                }
            }
        });


    } //End of onCreate Method


    //MAIN CODE FOR LOGIN
    private void Login( final String username,final String password) {

        loading.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){

                                for(int i = 0  ;i < jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String username = object.getString("username").trim();
                                    Toast.makeText(LoginActivity.this,"Success Login " + username,Toast.LENGTH_LONG).show();

                                    loading.setVisibility(View.GONE);

                                   /* String firstName  = object.getString("firstName").trim();
                                    String lastName  = object.getString("lastName").trim();
                                    String position  = object.getString("position").trim();
                                    String year  = object.getString("year").trim();
                                    String month  = object.getString("month").trim();
                                    String office  = object.getString("office").trim();
                                    String division  = object.getString("division").trim(); */

                                   // sessionManager.createSession(firstName,lastName,position,year,month,office,division)
                                }

                            }

                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this,"Error" + e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            loginButton.setVisibility(View.VISIBLE);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Failed" + error.toString() , Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        errorMessage();
                        loading.setVisibility(View.GONE);
                        loginButton.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    //DISPLAY ERROR IF USERNAME AND PASSWORD ARE INCORRECT
    private void errorMessage(){

        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Login Failed")
                .setContentText("Check your connection")
                .show();
    }

    //DISPLAY MESSAGE IF SUCCESS
    private void sweetDialog(){

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Register Successfully")
                .setContentText("Data has been saved")
                .show();

   }


   //SAMPLE INTENT FOR TEST ONLY
    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.signupButton:
                i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;

          /*  case R.id.loginButton:
                i  = new Intent(this,MenuActivity.class);
                startActivity(i);
          */
        }

    }
}
