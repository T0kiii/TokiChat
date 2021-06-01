package com.tokiapps.tokichat.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.activities.ProfileActivity;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.ImageProvider;
import com.tokiapps.tokichat.providers.UsersProvider;

public class BottomSheetSelectImage extends BottomSheetDialogFragment {

    LinearLayout mLinearLayoutDeleteImage;
    LinearLayout mLinearLayoutSelectImage;
    ImageProvider mImageProvider;
    AuthProvider mAuthProvider;
    UsersProvider mUsersProvider;

    String image;

    public static BottomSheetSelectImage newInstance(String url) {
        BottomSheetSelectImage bottomSheetSelectImage = new BottomSheetSelectImage();
        Bundle args = new Bundle();
        args.putString("image", url);
        bottomSheetSelectImage.setArguments(args);
        return bottomSheetSelectImage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getString("image");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_select_image, container, false);
        mLinearLayoutDeleteImage = view.findViewById(R.id.linearLayoutDeleteImage);
        mLinearLayoutSelectImage = view.findViewById(R.id.linearLayoutSelectImage);

        mImageProvider = new ImageProvider();
        mUsersProvider = new UsersProvider();
        mAuthProvider = new AuthProvider();

        mLinearLayoutDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImage();
            }
        });

        mLinearLayoutSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateImage();
            }
        });

        return view;
    }

    private void updateImage() {
        ((ProfileActivity)getActivity()).startPix();
    }

    private void deleteImage() {
        mImageProvider.delete(image).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mUsersProvider.updateImage(mAuthProvider.getId(), null).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                setImageDefault();
                                Toast.makeText(getContext(), "La imagen se elimino correctamente", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), "No se pudo eliminar el dato de la imagen", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "No se pudo eliminar la imagen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setImageDefault() {
        ((ProfileActivity)getActivity()).setImageDefault();
    }

}
