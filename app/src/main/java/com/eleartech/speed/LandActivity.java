package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.eleartech.speed.data.Land;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandActivity extends AppCompatActivity {

    Spinner seasonSpinner;
    EditText irrigatedAreaEditText;
    EditText rainfedAreaEditText;
    EditText totalAreaEditText;
    Spinner ownershipSpinner;

    float irrigatedArea;
    float rainfedArea;
    float totalArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        seasonSpinner = (Spinner) findViewById(R.id.seasonSpinner);
        irrigatedAreaEditText = (EditText) findViewById(R.id.irrigatedAreaEditText);
        rainfedAreaEditText = (EditText) findViewById(R.id.rainfedAreaEditText);
        totalAreaEditText = (EditText) findViewById(R.id.totalAreaEditText);
        ownershipSpinner = (Spinner) findViewById(R.id.ownershipSpinner);

        irrigatedAreaEditText.addTextChangedListener(textWatcher);
        rainfedAreaEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (irrigatedAreaEditText.getText().toString().equals(""))
                return;
            if (rainfedAreaEditText.getText().toString().equals(""))
                return;

            if (Float.valueOf(irrigatedAreaEditText.getText().toString()) == null)
                return;
            if (Float.valueOf(rainfedAreaEditText.getText().toString()) == null)
                return;

            irrigatedArea = Float.valueOf(irrigatedAreaEditText.getText().toString());
            rainfedArea = Float.valueOf(rainfedAreaEditText.getText().toString());
            totalArea = irrigatedArea + rainfedArea;

            String totalAreaText = totalArea + "";
            totalAreaEditText.setText(totalAreaText);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void continueButtonClicked(View view) {
        if (irrigatedAreaEditText.getText().toString().equals("")) {
            showWarning();
            return;
        }
        if (rainfedAreaEditText.getText().toString().equals("")) {
            showWarning();
            return;
        }

        if (Float.valueOf(irrigatedAreaEditText.getText().toString()) == null) {
            showWarning();
            return;
        }
        if (Float.valueOf(rainfedAreaEditText.getText().toString()) == null) {
            showWarning();
            return;
        }

        Land land = new Land(
                seasonSpinner.getSelectedItem().toString(),
                irrigatedArea,
                rainfedArea,
                totalArea,
                ownershipSpinner.getSelectedItem().toString()
        );

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");
        db.child(ProfileActivity.contactNumber).child("land").setValue(land);

        startActivity(new Intent(this, MunicipalActivity.class));
        Toast.makeText(this, "Farmer Details Added!", Toast.LENGTH_LONG).show();
    }

    public void showWarning() {
        Toast.makeText(this, "Invalid AREA inputs!", Toast.LENGTH_SHORT).show();
    }
}
