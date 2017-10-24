package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eleartech.speed.data.Address;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddressActivity extends AppCompatActivity {

    EditText streetEditText;
    EditText barangayEditText;
    EditText cityEditText;
    EditText provinceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        streetEditText = (EditText) findViewById(R.id.streetEditText);
        barangayEditText = (EditText) findViewById(R.id.barangayEditText);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        provinceEditText = (EditText) findViewById(R.id.provinceEditText);
    }

    public void continueButtonClicked(View view) {
        Address address = new Address(
                streetEditText.getText().toString(),
                barangayEditText.getText().toString(),
                cityEditText.getText().toString(),
                provinceEditText.getText().toString()
        );

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");
        db.child(ProfileActivity.contactNumber).child("address").setValue(address);

        startActivity(new Intent(this, LandActivity.class));
    }

}
