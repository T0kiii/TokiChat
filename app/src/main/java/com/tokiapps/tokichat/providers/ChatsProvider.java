package com.tokiapps.tokichat.providers;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.tokiapps.tokichat.models.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsProvider {

    CollectionReference mCollection;

    public ChatsProvider() {
        mCollection = FirebaseFirestore.getInstance().collection("Chats");
    }

    public Task<Void> create(Chat chat) {
        return mCollection.document(chat.getId()).set(chat);
    }

    public Query getUserChats(String idUser) {
        return mCollection.whereArrayContains("ids", idUser).whereGreaterThanOrEqualTo("numberMessages", 1);
    }

    public Query getChatByUser1AndUser2(String idUser1, String idUser2) {
        ArrayList<String> ids = new ArrayList<>();
        ids.add(idUser1 + idUser2);
        ids.add(idUser2 + idUser1);
        return mCollection.whereIn("id", ids);
    }

    public void updateNumberMessages(final String idChat) {

        mCollection.document(idChat).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    if (documentSnapshot.contains("numberMessages")) {
                        long numberMessages = documentSnapshot.getLong("numberMessages") + 1;
                        Map<String, Object> map = new HashMap<>();
                        map.put("numberMessages", numberMessages);
                        mCollection.document(idChat).update(map);
                    }
                    else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("numberMessages", 1);
                        mCollection.document(idChat).update(map);
                    }
                }
            }
        });


    }

}
