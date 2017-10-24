package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.eleartech.speed.data.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {

    EditText firstNameEditText;
    EditText middleNameEditText;
    EditText lastNameEditText;
    EditText suffixEditText;
    Spinner genderSpinner;
    EditText birthdayEditText;
    EditText contactNumberEditText;

    public static String contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        middleNameEditText = (EditText) findViewById(R.id.middleNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        suffixEditText = (EditText) findViewById(R.id.suffixNameEditText);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        contactNumberEditText = (EditText) findViewById(R.id.contactNumberEditText);
    }

    public void continueButtonClicked(View view) {
        Profile profile = new Profile(
                firstNameEditText.getText().toString(),
                middleNameEditText.getText().toString(),
                lastNameEditText.getText().toString(),
                suffixEditText.getText().toString(),
                genderSpinner.getSelectedItem().toString(),
                birthdayEditText.getText().toString(),
                contactNumberEditText.getText().toString()
        );

        contactNumber = profile.contactNumber;

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");
        db.child(profile.contactNumber).child("profile").setValue(profile);

        startActivity(new Intent(this, AddressActivity.class));
    }
}