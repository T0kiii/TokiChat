package com.tokiapps.tokichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CodeVerificationActivity extends AppCompatActivity {

    Button mButtonCodeVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);

        mButtonCodeVerification = findViewById(R.id.btnCodeVerification);
        mButtonCodeVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CodeVerificationActivity.this, "El usuario clickeo el boton", Toast.LENGTH_SHORT).show();
            }
        });
    }
}