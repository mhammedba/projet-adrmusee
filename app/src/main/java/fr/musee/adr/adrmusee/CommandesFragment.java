package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CommandesFragment extends Fragment {
    @Nullable

    private DatabaseReference mDatabase;
    private ArrayList commands;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.activity_commandes, null);
         mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Commands");
         mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 commands = new ArrayList();

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
         return view;
    }
}
