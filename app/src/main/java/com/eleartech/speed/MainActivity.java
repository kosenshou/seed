package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner userSpinner;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userSpinner = (Spinner) findViewById(R.id.userSpinner);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void signInClicked(View view) {
        String user = userSpinner.getSelectedItem().toString();
        String password = passwordEditText.getText().toString();

        if (!password.equals("admin")) {
            showToast("Wrong Password!");
            return;
        }

        if (user.equals("Department of Agriculture")) {
            startActivity(new Intent(this, RegionalActivity.class));
        }
        else if (user.equals("Municipal Agriculturist")) {
            startActivity(new Intent(this, MunicipalActivity.class));
        }
        else if (user.equals("Provincial Agriculturist")) {
            startActivity(new Intent(this, ProvincialActivity.class));
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
