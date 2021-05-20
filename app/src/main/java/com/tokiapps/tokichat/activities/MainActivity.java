package com.tokiapps.tokichat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.tokiapps.tokichat.R;

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
            public void onClick(View view) {
                //goToCodeVerificationActivity();
                getData();
            }
        });


    }

    private void getData() {
        String code = mCountryCode.getSelectedCountryCodeWithPlus();
        String phone = mEditTextPhone.getText().toString();

        if (phone.equals("")) {
            Toast.makeText(this, "debe insertar el telefono", Toast.LENGTH_SHORT).show();
        }
        else {
            goToCodeVerificationActivity(code + phone);
        }
    }

    private void goToCodeVerificationActivity(String phone) {

        Intent intent = new Intent(MainActivity.this, CodeVerificationActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);

    }
}