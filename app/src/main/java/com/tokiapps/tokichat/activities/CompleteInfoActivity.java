package com.tokiapps.tokichat.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.models.User;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.UsersProvider;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteInfoActivity extends AppCompatActivity {

    TextInputEditText mTextInputUsername;
    Button mButtonConfirm;
    CircleImageView mCircleImagePhoto;

    UsersProvider mUsersProvider;
    AuthProvider mAuthProvider;

    Options mOptions;

    ArrayList<String> mReturnValues = new ArrayList<>();

    File mImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_info);

        mTextInputUsername = findViewById(R.id.textInputUsername);
        mButtonConfirm = findViewById(R.id.btnConfirm);
        mCircleImagePhoto = findViewById(R.id.circleImagePhoto);

        mUsersProvider = new UsersProvider();
        mAuthProvider = new AuthProvider();


        mOptions = Options.init()
                .setRequestCode(100)                                           //Request code for activity results
                .setCount(1)                                                   //Number of images to restict selection count
                .setFrontfacing(false)                                         //Front Facing camera on start
                .setPreSelectedUrls(mReturnValues)                               //Pre selected Image Urls
                .setExcludeVideos(true)                                       //Option to exclude videos
                .setVideoDurationLimitinSeconds(0)                            //Duration for video recording
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
                .setPath("/pix/images");                                       //Custom Path For media Storage


        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserInfo();
            }
        });

        mCircleImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPix();
            }
        });

    }

    private void startPix() {
        Pix.start(CompleteInfoActivity.this, mOptions);
    }

    private void updateUserInfo() {
        String username = mTextInputUsername.getText().toString();
        if (!username.equals("")) {
            User user = new User();
            user.setUsername(username);
            user.setId(mAuthProvider.getId());
            mUsersProvider.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(CompleteInfoActivity.this, "La informacion se actualizo correctamente", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            mReturnValues = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            mImageFile = new File(mReturnValues.get(0));
            mCircleImagePhoto.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(CompleteInfoActivity.this, mOptions);
                } else {
                    Toast.makeText(CompleteInfoActivity.this, "Por favor concede los permisos para acceder a la camara", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}