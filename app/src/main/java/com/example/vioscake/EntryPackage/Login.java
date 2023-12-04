package com.example.vioscake.EntryPackage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vioscake.Koneksi.DB_connection;
import com.example.vioscake.NavbarFragment;
import com.example.vioscake.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText emailLine, passwordData, passwordShow;
    private CardView loginButton;
    private TextView signupButton, forgetpassButton;
    private ImageView togglePassword;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        emailLine = findViewById(R.id.emaillogin1);
        passwordData = findViewById(R.id.password1);
        loginButton = findViewById(R.id.buttonlogin1);
        signupButton = findViewById(R.id.signuptext1);
        forgetpassButton = findViewById(R.id.forgotpassword1);
        passwordShow = findViewById(R.id.password1);
        togglePassword = findViewById(R.id.toggleeyelogin1);
        progressDialog = new ProgressDialog(Login.this);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = emailLine.getText().toString();
                String user_password = passwordData.getText().toString();

                if (user_email.isEmpty() || user_password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    Toast.makeText(getApplicationContext(), "Email tidak valid", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(user_email, user_password);
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forgetpassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }
    private void togglePasswordVisibility() {
        if (passwordShow.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            // Jika password tersembunyi, tampilkan
            passwordShow.setTransformationMethod(null);
            togglePassword.setImageResource(R.drawable.eye); // Ganti dengan gambar mata terbuka
        } else {
            // Jika password terlihat, sembunyikan
            passwordShow.setTransformationMethod(PasswordTransformationMethod.getInstance());
            togglePassword.setImageResource(R.drawable.eye_closed); // Ganti dengan gambar mata tertutup
        }
    }
    private void loginUser(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DB_connection.urlLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int code = jsonResponse.getInt("code");

                            if (code == 200) {
                                JSONArray dataArray = jsonResponse.getJSONArray("data");
                                JSONObject userData = dataArray.getJSONObject(0);

                                String userId = userData.getString("id_user");
                                String username = userData.getString("user_email");
                                String password = userData.getString("user_password");
                                String alamat = userData.getString("user_fullname");

                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), NavbarFragment.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", email);
                params.put("user_password", password);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}