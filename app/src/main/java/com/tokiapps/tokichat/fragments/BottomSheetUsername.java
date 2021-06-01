package com.tokiapps.tokichat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.ImageProvider;
import com.tokiapps.tokichat.providers.UsersProvider;


public class BottomSheetUsername extends BottomSheetDialogFragment {

    Button mButtonSave;
    Button mButtonCancel;
    EditText mEditTextUsername;

    ImageProvider mImageProvider;
    AuthProvider mAuthProvider;
    UsersProvider mUsersProvider;

    String username;

    public static BottomSheetUsername newInstance(String username) {
        BottomSheetUsername bottomSheetSelectImage = new BottomSheetUsername();
        Bundle args = new Bundle();
        args.putString("username", username);
        bottomSheetSelectImage.setArguments(args);
        return bottomSheetSelectImage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getArguments().getString("username");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_username, container, false);
        mButtonSave = view.findViewById(R.id.btnSave);
        mButtonCancel = view.findViewById(R.id.btnCancel);
        mEditTextUsername = view.findViewById(R.id.editTextUsername);
        mEditTextUsername.setText(username);

        mImageProvider = new ImageProvider();
        mUsersProvider = new UsersProvider();
        mAuthProvider = new AuthProvider();

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUsername();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    private void updateUsername() {
        String username = mEditTextUsername.getText().toString();
        if (!username.equals("")) {
            mUsersProvider.updateUsername(mAuthProvider.getId(), username).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    dismiss();
                    Toast.makeText(getContext(), "El nombre de usuario se ha actualizo", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
