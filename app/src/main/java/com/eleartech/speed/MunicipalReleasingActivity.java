package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.eleartech.speed.data.Farmer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MunicipalReleasingActivity extends AppCompatActivity {

    ArrayList<String> fullNames = new ArrayList<>();
    static ArrayList<String> contactNumbers = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;

    public static int listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_releasing);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fullNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listId = position;
                startActivity(new Intent(MunicipalReleasingActivity.this, MunicipalityReleasingEditActivity.class));
            }
        });

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("farmer");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullNames.clear();
                contactNumbers.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Farmer farmer = snapshot.getValue(Farmer.class);
                    if (farmer == null)
                        return;

                    fullNames.add(farmer.profile.firstName + " " + farmer.profile.lastName);
                    contactNumbers.add(farmer.profile.contactNumber);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }


}
