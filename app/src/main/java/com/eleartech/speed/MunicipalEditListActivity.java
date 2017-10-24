package com.eleartech.speed;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eleartech.speed.data.Farmer;
import com.eleartech.speed.data.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MunicipalEditListActivity extends AppCompatActivity {

    TextView firstNameTextView;
    TextView middleNameTextView;
    TextView lastNameTextView;
    TextView suffixNameTextView;

    TextView genderTextView;
    TextView birthdayTextView;
    TextView contactNumberTextView;

    TextView streetTextView;
    TextView barangayTextView;
    TextView cityTextView;
    TextView provinceTextView;

    TextView seasonTextView;
    TextView irrigatedAreaTextView;
    TextView rainfedAreaTextView;
    TextView totalAreaTextView;

    TextView ownershipTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_edit_list);

        firstNameTextView = (TextView) findViewById(R.id.firstNameTextView);
        middleNameTextView = (TextView) findViewById(R.id.middleNameTextView);
        lastNameTextView = (TextView) findViewById(R.id.lastNameTextView);
        suffixNameTextView = (TextView) findViewById(R.id.suffixNameTextView);

        genderTextView = (TextView) findViewById(R.id.genderTextView);
        birthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
        contactNumberTextView = (TextView) findViewById(R.id.contactNumberTextView);

        streetTextView = (TextView) findViewById(R.id.streetTextView);
        barangayTextView = (TextView) findViewById(R.id.barangayTextView);
        cityTextView = (TextView) findViewById(R.id.cityTextView);
        provinceTextView = (TextView) findViewById(R.id.provinceTextView);

        seasonTextView = (TextView) findViewById(R.id.seasonTextView);
        irrigatedAreaTextView = (TextView) findViewById(R.id.irrigatedAreaTextView);
        rainfedAreaTextView = (TextView) findViewById(R.id.rainfedAreaTextView);
        totalAreaTextView = (TextView) findViewById(R.id.totalAreaTextView);

        ownershipTextView = (TextView) findViewById(R.id.ownershipTextView);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Farmer farmer = snapshot.getValue(Farmer.class);
                    if (farmer == null)
                        return;

                    if (farmer.profile.contactNumber.equals(MunicipalListActivity.contactNumbers.get(MunicipalListActivity.listId))) {
                        firstNameTextView.setText("First Name: " + farmer.profile.firstName);
                        middleNameTextView.setText("Middle Name: " + farmer.profile.middleName);
                        lastNameTextView.setText("Last Name: " + farmer.profile.lastName);
                        suffixNameTextView.setText("Suffix Name: " + farmer.profile.suffixName);

                        genderTextView.setText("Gender: " + farmer.profile.gender);
                        birthdayTextView.setText("Birthday: " + farmer.profile.birthday);
                        contactNumberTextView.setText("Contact Number: " + farmer.profile.contactNumber);

                        streetTextView.setText("Purok / Street: " + farmer.address.street);
                        barangayTextView.setText("Barangay: " + farmer.address.barangay);
                        cityTextView.setText("Town / Municipality / City: " + farmer.address.city);
                        provinceTextView.setText("Province: " + farmer.address.province);

                        seasonTextView.setText("Season: " + farmer.land.season);
                        irrigatedAreaTextView.setText("Irrigated (HA): " + farmer.land.irrigatedArea);
                        rainfedAreaTextView.setText("Rainfed (HA): " + farmer.land.rainfedArea);
                        totalAreaTextView.setText("Total Area (HA): " + farmer.land.totalArea);

                        ownershipTextView.setText("Ownership: " + farmer.land.ownership);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public void viewDocumentClicked(View view) {

    }

    public void editClicked(View view) {

    }

    public void archiveClicked(View view) {

    }
}
