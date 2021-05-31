package com.tokiapps.tokichat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.utils.MyToolbar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        MyToolbar.show(this, "Perfil de usuario", true);
    }
}