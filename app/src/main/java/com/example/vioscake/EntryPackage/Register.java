package com.example.vioscake.EntryPackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vioscake.Koneksi.DB_connection;
import com.example.vioscake.Koneksi.VolleyConnection;
import com.example.vioscake.R;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword1, confPassword2;
    private ImageView toggleButton1, toggleButton2;
    private TextView loginButton;
    private CardView signUpButton;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        etEmail = findViewById(R.id.emailregistrasi1);
        etUsername = findViewById(R.id.usernameregistrasi1);
        etPassword1 = findViewById(R.id.password2);
        confPassword2 = findViewById(R.id.passwordconfirm);
        toggleButton1 = findViewById(R.id.toggleeyeregister1);
        toggleButton2 = findViewById(R.id.toggleeyeregister2);
        loginButton = findViewById(R.id.logintext1);
        signUpButton = findViewById(R.id.buttonregister1);
        progressDialog = new ProgressDialog(Register.this);
        progressBar = findViewById(R.id.loadingRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(view -> {
            if (InputValidated()) {
                performRegister();
            }
        });

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }
    private void togglePasswordVisibility() {
        if (etPassword1.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            etPassword1.setTransformationMethod(null);
            toggleButton1.setImageResource(R.drawable.eye);
        } else {
            etPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            toggleButton1.setImageResource(R.drawable.eye_closed);
        }

        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibilitySecond();
            }
        });
    }
    private void togglePasswordVisibilitySecond() {
        if (confPassword2.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            confPassword2.setTransformationMethod(null);
            toggleButton2.setImageResource(R.drawable.eye);
        } else {
            confPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            toggleButton2.setImageResource(R.drawable.eye_closed);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
    public void performRegister() {
        progressBar.setVisibility(View.VISIBLE);
        String email = etEmail.getText().toString();
        String password = etPassword1.getText().toString();
        String confirm = confPassword2.getText().toString();
        String nama = etUsername.getText().toString();

        if (!(email.isEmpty() || password.isEmpty() || confirm.isEmpty() || nama.isEmpty())) {

            //check pass = confirm
            if (password.equals(confirm)) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, DB_connection.urlRegister, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user_email", email);
                        params.put("user_password", confirm);
                        params.put("user_fullname", nama);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            } else {
                // Password dan konfirmasi password tidak sesuai
                Toast.makeText(getApplicationContext(), "Password mismatch", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    public boolean InputValidated() {
        String user_fullname = etUsername.getText().toString().trim();
        String user_email = etEmail.getText().toString().trim();
        String user_password = etPassword1.getText().toString().trim();
        String confirmPass = confPassword2.getText().toString().trim();

        boolean isValidusername = false;
        boolean isValidemail = false;
        boolean isValidpassword = false;
        boolean isValidConfPass = false;

        if (user_fullname.isEmpty() || !Character.isUpperCase(user_fullname.charAt(0))) {
            etUsername.setError("Username salah!");
        } else {
            isValidusername = true;
        }

        if (user_email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            etEmail.setError("Isi Email dengan format yang benar");
        } else {
            isValidemail = true;
        }

        if (user_password.isEmpty() || user_password.length() < 4) {
            etPassword1.setError("Password kurang!");
        } else {
            isValidpassword = true;
        }

        if (confirmPass.isEmpty() || !confirmPass.equals(user_password)) {
            confPassword2.setError("Konfirmasi password tidak cocok!");
        } else {
            isValidConfPass = true;
        }

        return isValidusername && isValidemail && isValidpassword && isValidConfPass;
    }
}