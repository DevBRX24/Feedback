package com.android.developer.kalikasan;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.abdularis.civ.AvatarImageView;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText firstName, middleName, lastName,user, pass,password1;
    private Button registerButton;
    private ProgressBar loading;
    private Spinner position,service_month,service_year,office,division;
    RadioGroup userGender;
    RadioButton male,female;
    Boolean radioClick = true;


    private static final String URL_REGISTER = "http://192.168.254.130/Feedback/create.php";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firstName = findViewById(R.id.firstName);
        middleName = findViewById(R.id.middleName);
        lastName = findViewById(R.id.lastName);
        user = findViewById(R.id.usernameText);
        pass = findViewById(R.id.passwordText);
        password1 = findViewById(R.id.passwordText1);
        loading = findViewById(R.id.loading);
        registerButton = findViewById(R.id.registerButton);

        male = findViewById(R.id.maleRadio);
        userGender = findViewById(R.id.radioGender);
        female = findViewById(R.id.femaleRadio);


        position = findViewById(R.id.positionSpinner);
        service_month = findViewById(R.id.lengthSpinner1);
        service_year = findViewById(R.id.lengthSpinner2);
        office = findViewById(R.id.officeSpinner);
        division = findViewById(R.id.divisionSpinner);


            // Button for Register Account
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    if(userGender.getCheckedRadioButtonId() == -1){
                     Toast.makeText(RegisterActivity.this,"Gender is required",Toast.LENGTH_LONG).show();

                    }else{
                        Register();
                        sweetDialog();
                    }

                }
            });


        //*************************************
        //Code for Dropdown the content is in String.xml
        //*************************************
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Position, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position.setAdapter(arrayAdapter);
        position.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.Months, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service_month.setAdapter(arrayAdapter2);
        service_month.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.Year, android.R.layout.simple_spinner_item);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service_year.setAdapter(arrayAdapter3);
        service_year.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter4 = ArrayAdapter.createFromResource(this, R.array.office, android.R.layout.simple_spinner_item);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        office.setAdapter(arrayAdapter4);
        office.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> arrayAdapter5 = ArrayAdapter.createFromResource(this, R.array.division, android.R.layout.simple_spinner_item);
        arrayAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division.setAdapter(arrayAdapter5);
        division.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void RadioButtonClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.maleRadio:
                if (checked)
                break;

            case R.id.femaleRadio:
                if (checked)
                break;
        }
    }
    private void Register(){

        final String name = this.firstName.getText().toString().trim();
        final String middle = this.middleName.getText().toString().trim();
        final String last = this.lastName.getText().toString().trim();
        final String position = this.position.getSelectedItem().toString().trim();
        final String month = this.service_month.getSelectedItem().toString().trim();
        final String year = this.service_year.getSelectedItem().toString().trim();
        final String office = this.office.getSelectedItem().toString().trim();
        final String division = this.division.getSelectedItem().toString().trim();
        final String gender = ((RadioButton) findViewById(userGender.getCheckedRadioButtonId())).getText().toString().trim();
        final String username = this.user.getText().toString().trim();
        final String password = this.pass.getText().toString().trim();
        final  String confirm = this.password1.getText().toString().trim();


        if(TextUtils.isEmpty(name)){

            firstName.setError("FirstName is required");
            firstName.requestFocus();
            return;

        }else if(TextUtils.isEmpty(last)){

            lastName.setError("LastName is required");
            lastName.requestFocus();
            return;

        }else if(TextUtils.isEmpty(username)){
            user.setError("Username is Required");
            user.requestFocus();
            return;
        }else if(!password.equals(confirm)) {
            password1.setError("Password not matched");
            password1.requestFocus();
            return;
        }else if(TextUtils.isEmpty(password) ) {
            pass.setError("Password is Required");
            pass.requestFocus();
            return;
        }

        loading.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                loading.setVisibility(View.GONE);
                                registerButton.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            loading.setVisibility(View.GONE);
                           registerButton.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorDialog();
                        Toast.makeText(RegisterActivity.this, "Failed" + error.toString() , Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        registerButton.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("first_name",name);
                params.put("middle_name",middle);
                params.put("last_name",last);
                params.put("position",position);
                params.put("month",month);
                params.put("year",year);
                params.put("office",office);
                params.put("division",division);
                params.put("gender",gender);
                params.put("username",username);
                params.put("password",password);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void sweetDialog(){

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Register Successfully")
                .setContentText("Data has been saved")
                .show();
                clearForm();


    }
    private void errorDialog(){
        new SweetAlertDialog(this,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Network Error")
                .setContentText("Please connect to server")
                .setCustomImage(R.drawable.vector_error1)
                .show();

    }
    private void errorDialog1(){
        new SweetAlertDialog(this,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Network Error")
                .setContentText("Server has an issue ")
                .setContentText("Please contact your network administrator")
                .setCustomImage(R.drawable.vector_error1)
                .show();

    }
    private void clearForm(){

        //This will use for version 1.0

        firstName.getText().clear();
        middleName.getText().clear();
        lastName.getText().clear();
        user.getText().clear();
        pass.getText().clear();
        password1.getText().clear();

        position.setSelection(0);
        service_month.setSelection(0);
        service_year.setSelection(0);
        office.setSelection(0);
        division.setSelection(0);

        userGender.clearCheck();

    }
}
