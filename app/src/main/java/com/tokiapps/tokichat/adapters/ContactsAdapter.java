package com.tokiapps.tokichat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.activities.ChatActivity;
import com.tokiapps.tokichat.models.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends FirestoreRecyclerAdapter<User, ContactsAdapter.ViewHolder> {

    Context context;

    public ContactsAdapter(FirestoreRecyclerOptions options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull User user) {
        holder.textViewInfo.setText(user.getInfo());
        holder.textViewUsername.setText(user.getUsername());
        if (user.getImage() != null) {
            if (!user.getImage().equals("")) {
                Picasso.get().load(user.getImage()).into(holder.circleImageUser);
            }
            else {
                holder.circleImageUser.setImageResource(R.drawable.ic_person);
            }
        }
        else {
            holder.circleImageUser.setImageResource(R.drawable.ic_person);
        }

        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChatActivity();
            }
        });
    }

    private void goToChatActivity() {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacts, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUsername;
        TextView textViewInfo;
        CircleImageView circleImageUser;
        View myView;

        public ViewHolder(View view) {
            super(view);
            myView = view;
            textViewUsername = view.findViewById(R.id.textViewUsername);
            textViewInfo = view.findViewById(R.id.textViewInfo);
            circleImageUser = view.findViewById(R.id.circleImageUser);

        }

    }
}
