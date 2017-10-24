package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eleartech.speed.adapters.Status;
import com.eleartech.speed.data.Regional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegionalProcurementActivity extends AppCompatActivity {

    TextView provinceTextView;
    TextView totalHectaresTextView;
    EditText sacksToAllocateEditText;
    EditText sacksInspectedEditText;
    Button confirmAllocationButton;
    Button confirmInspectionButton;

    float sacksToAllocate;
    float sacksInspected;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regional_procurement);

        provinceTextView = (TextView) findViewById(R.id.provinceTextView);
        totalHectaresTextView = (TextView) findViewById(R.id.totalHectaresTextView);
        sacksToAllocateEditText = (EditText) findViewById(R.id.sacksToAllocate);
        sacksInspectedEditText = (EditText) findViewById(R.id.sacksInspected);

        confirmAllocationButton = (Button) findViewById(R.id.confirmAllocationButton);
        confirmInspectionButton = (Button) findViewById(R.id.confirmInspectionButton);

        provinceTextView.setText(RegionalActivity.province);

        db = FirebaseDatabase.getInstance().getReference("provinces");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Regional regional = snapshot.getValue(Regional.class);
                    if (regional == null)
                        return;

                    if (regional.province.equals(RegionalActivity.province)) {
                        totalHectaresTextView.setText("Total Hectares: " + regional.totalHectares);
                        sacksToAllocateEditText.setText("" + regional.sacksToAllocate);
                        sacksInspectedEditText.setText("" + regional.sacksInspected);

                        if (regional.allocationComplete) {
                            sacksToAllocateEditText.setFocusable(false);
                            confirmAllocationButton.setVisibility(View.INVISIBLE);
                        }

                        if (regional.inspectionComplete) {
                            sacksInspectedEditText.setFocusable(false);
                            confirmInspectionButton.setVisibility(View.INVISIBLE);
                        }

                        sacksToAllocate = Float.valueOf(sacksToAllocateEditText.getText().toString());
                        sacksInspected = Float.valueOf(sacksInspectedEditText.getText().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        sacksToAllocate = Float.valueOf(sacksToAllocateEditText.getText().toString());
        sacksInspected = Float.valueOf(sacksInspectedEditText.getText().toString());
    }

    public void confirmAllocationClicked(View view) {
        if (sacksToAllocateEditText.getText().toString().equals(""))
            return;

        float sacksToAllocate = Float.valueOf(sacksToAllocateEditText.getText().toString());

        db.child(RegionalActivity.province).child("sacksToAllocate").setValue(sacksToAllocate);
        db.child(RegionalActivity.province).child("allocationComplete").setValue(true);

        sacksToAllocateEditText.setFocusable(false);
        confirmAllocationButton.setVisibility(View.INVISIBLE);

        Toast.makeText(this, "Allocation Saved!", Toast.LENGTH_SHORT).show();
    }

    public void confirmInspectionClicked(View view) {
        if (sacksInspectedEditText.getText().toString().equals(""))
            return;

        float sacksInspected = Float.valueOf(sacksInspectedEditText.getText().toString());

        db.child(RegionalActivity.province).child("sacksInspected").setValue(sacksInspected);
        db.child(RegionalActivity.province).child("inspectionComplete").setValue(true);

        sacksInspectedEditText.setFocusable(false);
        confirmInspectionButton.setVisibility(View.INVISIBLE);

        Toast.makeText(this, "Inspection Saved!", Toast.LENGTH_SHORT).show();
    }

    public void proceedClicked(View view) {
        if (sacksToAllocate > 0 && sacksInspected > 0) {
            db.child(RegionalActivity.province).child("isComplete").setValue(true);
        }
        startActivity(new Intent(this, RegionalActivity.class));
    }
}
