package com.example.vioscake.EntryPackage;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vioscake.Koneksi.DB_connection;
import com.example.vioscake.R;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends AppCompatActivity {
    EditText emailfieldOTP;
    ImageView returnBtn1;
    CardView btnKirimKode;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_page);

        returnBtn1 = findViewById(R.id.returntologin1);
        emailfieldOTP = findViewById(R.id.emaillupapassword1);
        btnKirimKode = findViewById(R.id.buttonkirimkode1);
        progressDialog = new ProgressDialog(ForgetPassword.this);

        returnBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });

        btnKirimKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Sedang Memuat...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, DB_connection.urlForgetPw,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                if (response.equals("success")){
                                    Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                                    intent.putExtra("user_email", emailfieldOTP.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("user_email", emailfieldOTP.getText().toString());
                        return paramV;
                    }
                };
                stringRequest.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 30000;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 30000;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });
                queue.add(stringRequest);
            }
        });

    }
}