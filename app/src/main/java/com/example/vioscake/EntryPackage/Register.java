package com.example.vioscake.EntryPackage;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vioscake.R;

public class Register extends AppCompatActivity {

    private EditText showPassword1;
    private EditText showPassword2;
    private ImageView toggleButton1;
    private ImageView toggleButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        showPassword1 = findViewById(R.id.password2);
        showPassword2 = findViewById(R.id.passwordconfirm);
        toggleButton1 = findViewById(R.id.toggleeyeregister1);
        toggleButton2 = findViewById(R.id.toggleeyeregister2);

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });
    }
    private void togglePasswordVisibility() {
        if (showPassword1.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            showPassword1.setTransformationMethod(null);
            toggleButton1.setImageResource(R.drawable.eye);
        }else {
            showPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
        if (showPassword2.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
            showPassword2.setTransformationMethod(null);
            toggleButton2.setImageResource(R.drawable.eye);
        }else {
            showPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            toggleButton2.setImageResource(R.drawable.eye_closed);
        }
    }
}