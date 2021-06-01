package com.tokiapps.tokichat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.fragments.BottomSheetSelectImage;
import com.tokiapps.tokichat.models.User;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.UsersProvider;
import com.tokiapps.tokichat.utils.MyToolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton mFabSelectImage;
    BottomSheetSelectImage mBottomSheetSelectImage;

    UsersProvider mUsersProvider;
    AuthProvider mAuthProvider;

    TextView mTextViewUsername; // ctrl + D duplicar líneas
    TextView mTextViewPhone;
    CircleImageView mCircleImageProfile;

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
                if (documentSnapshot.exists()) { // validación si documento existe en base de datos
                    User user = documentSnapshot.toObject(User.class); // recupero directamente la clase
                    mTextViewUsername.setText(user.getUsername());
                    mTextViewPhone.setText(user.getPhone());
                    if (user.getImage() != null) {
                        if (!user.getImage().equals("")) {
                            Picasso.get().load(user.getImage()).into(mCircleImageProfile); // para mostrar imagen de url de internet
                        }
                    }
                }
            }
        });
    }

    private void openBottomSheetSelectImage() {
        mBottomSheetSelectImage = BottomSheetSelectImage.newInstance();
        mBottomSheetSelectImage.show(getSupportFragmentManager(), mBottomSheetSelectImage.getTag());
    }
}