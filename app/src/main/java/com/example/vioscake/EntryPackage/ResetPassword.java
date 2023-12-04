package com.example.vioscake.EntryPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vioscake.Koneksi.DB_connection;
import com.example.vioscake.R;

import java.util.HashMap;
import java.util.Map;

public class ResetPassword extends AppCompatActivity {
    ImageView returnBtn2;
    EditText fieldOTP, fieldPass, fieldConf;
    CardView resetBtn;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_page);

        String email = getIntent().getExtras().getString("user_email");
        returnBtn2 = findViewById(R.id.returntologin2);
        fieldOTP = findViewById(R.id.konfirmasikode1);
        fieldPass = findViewById(R.id.konfirmasikode2);
        fieldConf = findViewById(R.id.konfirmasikode3);
        resetBtn = findViewById(R.id.buttonresetpassword1);
        progressDialog = new ProgressDialog(ResetPassword.this);

        returnBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, Login.class);
                startActivity(intent);
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Sedang Memuat...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                // Validasi OTP
                String otp = fieldOTP.getText().toString().trim();
                if (otp.isEmpty()) {
                    fieldOTP.setError("Masukkan kode OTP");
                    fieldOTP.requestFocus();
                    return;
                }

                // Validasi password
                String password = fieldPass.getText().toString().trim();
                String konfirmasiPassword = fieldConf.getText().toString().trim();
                if (password.isEmpty()) {
                    fieldPass.setError("Masukkan Password Baru");
                    fieldPass.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    fieldPass.setError("Password minimal 6 karakter");
                    fieldPass.requestFocus();
                    return;
                }

                if (!password.equals(konfirmasiPassword)) {
                    fieldConf.setError("Password tidak cocok");
                    fieldConf.requestFocus();
                    return;
                }

                progressDialog.dismiss();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, DB_connection.urlResetPw,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "New password Set", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
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
                        paramV.put("user_email", email);
                        paramV.put("otp", fieldOTP.getText().toString());
                        paramV.put("new-password", fieldPass.getText().toString());
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }
}