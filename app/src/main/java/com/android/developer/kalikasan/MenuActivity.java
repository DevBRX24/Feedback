package com.android.developer.kalikasan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.github.abdularis.civ.AvatarImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MenuActivity.class.getSimpleName();
    private CardView feedbackCard,profileButton;
    private EditText userPosition;
   // SessionManager sessionManager;
    private Button userLogout;
    private static final String URL_READ = "http://192.168.254.130/Feedback/model/read.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        feedbackCard  = findViewById(R.id.feedbackCard1);
        profileButton = findViewById(R.id.profileButton);
        userPosition = findViewById(R.id.profilePosition);
        userLogout = findViewById(R.id.userLogout);


       // sessionManager = new SessionManager(this);
       // sessionManager.checkLogin();
       // HashMap<String,String> user = sessionManager.getUserDetail();
      //  String position =  user.get(SessionManager.POSITION);

       // userPosition.setText(position);

        feedbackCard.setOnClickListener(this);

        //USER LOG OUT CONNECTED TO SESSION MANAGER
         /* userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // sessionManager.logOut();
            }
       });*/

       //BOTTOM SHEET THIS WILL SHOW WHEN THE USER
       //CLICK HIS/SHER PROFILE
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MenuActivity.this);
                View parentView = getLayoutInflater().inflate(R.layout.bottom_profile,null);
                bottomSheetDialog.setContentView(parentView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View)parentView.getParent());
                bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1000,getResources().getDisplayMetrics()));

                bottomSheetDialog.show();



            }
        });

    }


    //GET USER DATA
    private void getUserDetail(final String id){

        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG,response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1"))
                            {
                                for(int i = 0; i < jsonArray.length();i++){

                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                    String position = jsonObject1.getString("position").trim();

                                    userPosition.setText(position);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(MenuActivity.this,"Error Reading Detail" + e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MenuActivity.this,"Error Reading Detail" + error.toString(),Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params = new HashMap<>();


                return super.getParams();
            }
        };

    }

    public void onClick(View view){

        Intent intent;

        switch (view.getId()){

            case R.id.feedbackCard1:
                intent = new Intent(this,CardTab.class);
                startActivity(intent);
                break;
        }



    }

}
