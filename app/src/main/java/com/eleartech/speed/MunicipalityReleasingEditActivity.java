package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleartech.speed.data.Farmer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MunicipalityReleasingEditActivity extends AppCompatActivity {

    TextView fullNameTextView;
    TextView hectaresTextView;
    EditText numberOfSacksEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality_releasing_edit);

        fullNameTextView = (TextView) findViewById(R.id.fullNameTextView);
        hectaresTextView = (TextView) findViewById(R.id.hectaresTextView);
        numberOfSacksEditText = (EditText) findViewById(R.id.numberOfSacksTextView);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Farmer farmer = snapshot.getValue(Farmer.class);
                    if (farmer == null)
                        return;

                    if (farmer.profile.contactNumber.equals(MunicipalReleasingActivity.contactNumbers.get(MunicipalReleasingActivity.listId))) {
                        fullNameTextView.setText(farmer.profile.firstName + " " + farmer.profile.lastName);
                        hectaresTextView.setText(farmer.land.totalArea + "");
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }

    public void confirmClicked(View view) {
        if (numberOfSacksEditText.getText().toString().equals(""))
            return;

        Toast.makeText(this, "Released!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MunicipalReleasingActivity.class));
    }
}
