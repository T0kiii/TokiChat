package com.tokiapps.tokichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;


public class MainActivity extends AppCompatActivity {

    Button mButtonSendCode;
    EditText mEditTextPhone;
    CountryCodePicker mCountryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSendCode = findViewById(R.id.btnSendCode);
        mEditTextPhone = findViewById(R.id.editTextPhone);
        mCountryCode = findViewById(R.id.ccp);

        mButtonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToCodeVerificationActivity();
                goToCodeVerificationActivity();
            }
        });
    }

    private void getData(){

        String code = mCountryCode.getSelectedCountryCodeWithPlus();
        String phone = mEditTextPhone.getText().toString();
        Toast.makeText(MainActivity.this, "teléfono: " + code + " " + phone, Toast.LENGTH_LONG).show();
    }

    private void goToCodeVerificationActivity(){
        Intent intent = new Intent(MainActivity.this, CodeVerificationActivity.class);
        startActivity(intent);
    }
}