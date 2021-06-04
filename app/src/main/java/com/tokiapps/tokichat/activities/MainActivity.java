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
import com.tokiapps.tokichat.providers.AuthProvider;


/*
* Primera actividad de la app si no hay sesión iniciada.
* Recibe el número de teléfono del usuario, le adjunta el código del país seleccionado y manda la info a la actividad CodeVerificationActivity
* */

public class MainActivity extends AppCompatActivity {

    Button mButtonSendCode;
    EditText mEditTextPhone;
    CountryCodePicker mCountryCode;

    AuthProvider mAuthProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSendCode = findViewById(R.id.btnSendCode);
        mEditTextPhone = findViewById(R.id.editTextPhone);
        mCountryCode = findViewById(R.id.ccp);

        mAuthProvider = new AuthProvider();

        mButtonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuthProvider.getSessionUser() != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void getData() {
        String code = mCountryCode.getSelectedCountryCodeWithPlus();
        String phone = mEditTextPhone.getText().toString();

        if (phone.equals("")) {
            Toast.makeText(this, "Debe insertar un telefono", Toast.LENGTH_SHORT).show();
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