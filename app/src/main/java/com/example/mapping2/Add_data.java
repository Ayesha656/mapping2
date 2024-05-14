package com.example.mapping2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ObjectStreamException;
import java.util.HashMap;
import java.util.Map;

public class Add_data extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        EditText _ed1 = findViewById(R.id.loc_name);
        EditText _ed2 = findViewById(R.id.loc_review);
        EditText _ed3 = findViewById(R.id.loc_longitutude);
        EditText _ed4 = findViewById(R.id.loc_latiitude);
        Button btn = findViewById(R.id.addbtn);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _location_name = _ed1.getText().toString();
                String _location_review= _ed2.getText().toString();
                String _location_longitude = _ed3.getText().toString();
                String _location_latitide = _ed4.getText().toString();


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<Object, String> insertion_values = new HashMap<>();


                insertion_values.put("name",_location_name);
                insertion_values.put("review",_location_review);
                insertion_values.put("longitide",_location_longitude);
                insertion_values.put("latitide",_location_latitide);


                db.collection("mappdetails").add(insertion_values).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Snackbar.make(view, "Data Added!", Snackbar.LENGTH_SHORT).show();
                        Intent i = new Intent(Add_data.this,HomePage.class);
                        startActivity(i);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(view, " Process Failed!", Snackbar.LENGTH_SHORT).show();
                    }
                });


            }
        });




    }
}