package fr.musee.adr.adrmusee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminOrNot extends AppCompatActivity {
    final static  String DB_URL= "https://adrmusee.firebaseio.com/";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String user_id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminornot);
        mAuth=FirebaseAuth.getInstance();
        user_id=mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        mDatabase.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                String isadmin= dataSnapshot.child("isadmin").getValue().toString();
                Toast.makeText(AdminOrNot.this, isadmin, Toast.LENGTH_SHORT).show();
                if(isadmin =="1"){
                    startActivity(new Intent(AdminOrNot.this, AdminActivity.class));

                }else{
                    startActivity(new Intent(AdminOrNot.this, CompteActivity.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}