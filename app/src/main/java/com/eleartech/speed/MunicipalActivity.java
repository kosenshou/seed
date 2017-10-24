package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MunicipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal);
    }

    public void addFarmerClicked(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void masterlistClicked(View view) {
        startActivity(new Intent(this, MunicipalListActivity.class));
    }

    public void allocationClicked(View view) {
        startActivity(new Intent(this, MunicipalAllocationActivity.class));
    }

    public void releasingClicked(View view) {
        startActivity(new Intent(this, MunicipalReleasingActivity.class));
    }
}
