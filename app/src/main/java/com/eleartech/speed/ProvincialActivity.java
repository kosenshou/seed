package com.eleartech.speed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProvincialActivity extends AppCompatActivity {

    EditText[] editTexts = new EditText[8];

    TextView availableTextView;
    TextView totalTextView;

    float maxAvailable = 100f;
    float available = 100f;
    float total = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincial);

        editTexts[0] = (EditText) findViewById(R.id.editText0);
        editTexts[1] = (EditText) findViewById(R.id.editText1);
        editTexts[2] = (EditText) findViewById(R.id.editText2);
        editTexts[3] = (EditText) findViewById(R.id.editText3);
        editTexts[4] = (EditText) findViewById(R.id.editText4);
        editTexts[5] = (EditText) findViewById(R.id.editText5);
        editTexts[6] = (EditText) findViewById(R.id.editText6);
        editTexts[7] = (EditText) findViewById(R.id.editText7);

        availableTextView = (TextView) findViewById(R.id.availableTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        for (int i = 0; i < editTexts.length; i++) {
            editTexts[i].addTextChangedListener(textWatcher);
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            for (int i = 0; i < editTexts.length; i++) {
                if (editTexts[i].getText().toString().equals("")) {
                    return;
                }
            }

            total = 0;
            available = maxAvailable;
            for (int i = 0; i < editTexts.length; i++) {
                total += Float.valueOf(editTexts[i].getText().toString());
            }

            available -= total;

            availableTextView.setText("Available: " + available);
            totalTextView.setText("Total: " + total);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void approveClicked(View view) {
        if (available > 0) {
            Toast.makeText(this, "You still have remaining sacks!", Toast.LENGTH_LONG).show();
            return;
        }

        if (total > maxAvailable) {
            Toast.makeText(this, "You exceed the available sacks!", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Approved!", Toast.LENGTH_LONG).show();
    }
}
