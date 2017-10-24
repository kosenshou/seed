package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eleartech.speed.adapters.Status;
import com.eleartech.speed.adapters.StatusAdapter;
import com.eleartech.speed.data.Regional;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegionalActivity extends AppCompatActivity {

    Status[] statuses = new Status[2];
    StatusAdapter statusAdapter;

    public static String province;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regional);

        statusAdapter = new StatusAdapter(this, statuses);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(statusAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                province = statuses[position].name;
                startActivity(new Intent(RegionalActivity.this, RegionalProcurementActivity.class));
            }
        });

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("provinces");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Regional regional = snapshot.getValue(Regional.class);
                    if (regional == null)
                        return;

                    statuses[i] = new Status(regional.province, regional.isComplete);
                    System.out.println(regional.isComplete);
                    i++;
                }
                statusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public void approveClicked(View view) {
        // Set tracking to approve in regional activity or pass approval to next step
        // change allocation and inspection status can't be edited
        Toast.makeText(this, "Provinces Approved!", Toast.LENGTH_LONG).show();
    }
}
