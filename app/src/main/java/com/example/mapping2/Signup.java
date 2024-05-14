package com.example.mapping2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText ed1 = findViewById(R.id.emailtxt1);
        EditText pas1 = findViewById(R.id.passtxt2);
        EditText conpas1 = findViewById(R.id.conpasstxt2);
        EditText name = findViewById(R.id.nametxt);
        Button signupbtn = findViewById(R.id.signupbtn);
        TextView signintxt = findViewById(R.id.signuptxt);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ed1.getText().toString();
                String password = pas1.getText().toString();
                String conpassword = conpas1.getText().toString();
                String username = name.getText().toString();
                if (email.isEmpty() || password.isEmpty() || conpassword.isEmpty() || username.isEmpty()) {
                    Toast.makeText(Signup.this, "Details Are Empty", Toast.LENGTH_SHORT).show();

                }


                if (password.equals(conpassword)) {

                    Map<String, Object> adduser = new HashMap<>();

                    adduser.put("Name", username);
                    adduser.put("Email", email);

                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email, password);


                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    firestore.collection("User Information").add(adduser)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Signup.this, "Details Added", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signup.this, "Unvalied Deatils", Toast.LENGTH_SHORT).show();
                                }
                            });


                }

                signintxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Signup.this, Login.class);
                        startActivity(i);
                    }
                });

            }
        });
    }
}
