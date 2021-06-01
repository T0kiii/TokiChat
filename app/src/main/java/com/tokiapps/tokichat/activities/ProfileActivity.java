package com.tokiapps.tokichat.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.PermUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.fragments.BottomSheetSelectImage;
import com.tokiapps.tokichat.models.User;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.ImageProvider;
import com.tokiapps.tokichat.providers.UsersProvider;
import com.tokiapps.tokichat.utils.MyToolbar;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

// mImageProvider instanciado en saveImage() para que no me cree una carpeta en la nube

public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton mFabSelectImage;
    BottomSheetSelectImage mBottomSheetSelectImage;

    UsersProvider mUsersProvider;
    AuthProvider mAuthProvider;
    ImageProvider mImageProvider;

    TextView mTextViewUsername;
    TextView mTextViewPhone;
    CircleImageView mCircleImageProfile;

    User mUser;

    Options mOptions;
    ArrayList<String> mReturnValues = new ArrayList<>();
    File mImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        MyToolbar.show(this, "Perfil de usuario", true);

        mUsersProvider = new UsersProvider();
        mAuthProvider = new AuthProvider();

        mTextViewUsername = findViewById(R.id.textViewUsername);
        mTextViewPhone = findViewById(R.id.textViewPhone);
        mCircleImageProfile = findViewById(R.id.circleImageProfile);

        mOptions = Options.init()
                .setRequestCode(100)                                           //Request code for activity results
                .setCount(1)                                                   //Number of images to restict selection count
                .setFrontfacing(false)                                         //Front Facing camera on start
                .setPreSelectedUrls(mReturnValues)                               //Pre selected Image Urls
                .setExcludeVideos(true)                                       //Option to exclude videos
                .setVideoDurationLimitinSeconds(0)                            //Duration for video recording
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
                .setPath("/pix/images");                                       //Custom Path For media Storage


        mFabSelectImage = findViewById(R.id.fabSelectImage);
        mFabSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheetSelectImage();
            }
        });

        getUserInfo();

    }

    private void getUserInfo() {
        mUsersProvider.getUserInfo(mAuthProvider.getId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    mUser = documentSnapshot.toObject(User.class);
                    mTextViewUsername.setText(mUser.getUsername());
                    mTextViewPhone.setText(mUser.getPhone());
                    if (mUser.getImage() != null) {
                        if (!mUser.getImage().equals("")) {
                            Picasso.get().load(mUser.getImage()).into(mCircleImageProfile);
                        }
                    }
                }
            }
        });
    }

    private void openBottomSheetSelectImage() {
        if (mUser != null) {
            mBottomSheetSelectImage = BottomSheetSelectImage.newInstance(mUser.getImage());
            mBottomSheetSelectImage.show(getSupportFragmentManager(), mBottomSheetSelectImage.getTag());
        }
        else {
            Toast.makeText(this, "La informacion no se pudo cargar", Toast.LENGTH_SHORT).show();
        }
    }

    public void setImageDefault() {
        mCircleImageProfile.setImageResource(R.drawable.ic_person_white);
    }



    public void startPix() {
        Pix.start(ProfileActivity.this, mOptions);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            mReturnValues = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            mImageFile = new File(mReturnValues.get(0));
            mCircleImageProfile.setImageBitmap(BitmapFactory.decodeFile(mImageFile.getAbsolutePath()));
            saveImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(ProfileActivity.this, mOptions);
                } else {
                    Toast.makeText(ProfileActivity.this, "Por favor concede los permisos para acceder a la camara", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void saveImage() {
        mImageProvider = new ImageProvider();
        mImageProvider.save(ProfileActivity.this, mImageFile).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    mImageProvider.getDownloadUri().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            mUsersProvider.updateImage(mAuthProvider.getId(), url).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ProfileActivity.this, "La imagen se actualizo correctamente", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                else {
                    Toast.makeText(ProfileActivity.this, "No se pudo almacenar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}