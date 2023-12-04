package com.example.vioscake.EntryPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vioscake.Home;
import com.example.vioscake.NavbarFragment;
import com.example.vioscake.R;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CardView loginButton;
    private TextView signupButton;
    private TextView forgetpassButton;
    private EditText passwordShow;
    private ImageView togglePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

            username = findViewById(R.id.emaillogin1);
            password = findViewById(R.id.password1);
            loginButton = findViewById(R.id.buttonlogin1);
            signupButton = findViewById(R.id.signuptext1);
            forgetpassButton = findViewById(R.id.forgotpassword1);
            passwordShow = findViewById(R.id.password1);
            togglePassword = findViewById(R.id.toggleeyelogin1);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (username.getText().toString().equals("user") &&
                            password.getText().toString().equals("1234")) {
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, NavbarFragment.class);
                        startActivity(intent);
                        finish();
                    } else if (username.getText().toString().equals("admin") &&
                            password.getText().toString().equals("1231")){
                        Toast.makeText(getApplicationContext(), "Login sebagai Admin", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, NavbarFragment.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
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
                togglePassword.setImageResource(R.drawable.eye); // Ganti dengan gambar mata tertutup
            } else {
                // Jika password terlihat, sembunyikan
                passwordShow.setTransformationMethod(PasswordTransformationMethod.getInstance());
                togglePassword.setImageResource(R.drawable.eye_closed); // Ganti dengan gambar mata terbuka
            }
        }
    }

