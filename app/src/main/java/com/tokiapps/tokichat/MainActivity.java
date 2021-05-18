package com.tokiapps.tokichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button mButtonSendCode;
    EditText mEditTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSendCode = findViewById(R.id.btnSendCode);
        mEditTextPhone = findViewById(R.id.editTextPhone);
        mButtonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToCodeVerificationActivity();
                getPhone();
            }
        });
    }

    private void getPhone(){
        String phone = mEditTextPhone.getText().toString();
        Toast.makeText(MainActivity.this, "teléfono: " + phone, Toast.LENGTH_LONG).show();
    }

    private void goToCodeVerificationActivity(){
        Intent intent = new Intent(MainActivity.this, CodeVerificationActivity.class);
        startActivity(intent);
    }
}