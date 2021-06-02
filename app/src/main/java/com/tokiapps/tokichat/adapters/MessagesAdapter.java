package com.tokiapps.tokichat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.ListenerRegistration;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.models.Message;
import com.tokiapps.tokichat.models.User;
import com.tokiapps.tokichat.providers.AuthProvider;
import com.tokiapps.tokichat.providers.UsersProvider;
import com.tokiapps.tokichat.utils.RelativeTime;

public class MessagesAdapter extends FirestoreRecyclerAdapter<Message, MessagesAdapter.ViewHolder> {

    Context context;
    AuthProvider authProvider;
    UsersProvider usersProvider;
    User user;
    ListenerRegistration listener;

    public MessagesAdapter(FirestoreRecyclerOptions options, Context context) {
        super(options);
        this.context = context;
        authProvider = new AuthProvider();
        usersProvider = new UsersProvider();
        user = new User();
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull final Message message) {

        holder.textViewMessage.setText(message.getMessage());
        holder.textViewDate.setText(RelativeTime.timeFormatAMPM(message.getTimestamp(), context));

    }

    public ListenerRegistration getListener() {
        return listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_message, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMessage;
        TextView textViewDate;
        ImageView imageViewCheck;

        View myView;

       public ViewHolder(View view) {
           super(view);
           myView = view;
           textViewMessage = view.findViewById(R.id.textViewMessage);
           textViewDate = view.findViewById(R.id.textViewDate);
           imageViewCheck = view.findViewById(R.id.imageViewCheck);
       }

   }
}
