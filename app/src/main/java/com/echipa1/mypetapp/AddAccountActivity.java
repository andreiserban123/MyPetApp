package com.echipa1.mypetapp;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddAccountActivity extends AppCompatActivity {

    public static final String FIRST_NAME_KEY = "FIRST_NAME_KEY";
    private TextInputEditText tietFirstName;
    private TextInputEditText tietLastName;
    private TextInputEditText tietPhoneNumber;
    private Spinner spnCity;
    private Button btnSave;
    private Intent intent;
    private ImageView imageView;

    private List<String> citiesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intent = getIntent();

        initComp();


        btnSave.setOnClickListener(v -> {
            if (validation()) {
                intent.putExtra(FIRST_NAME_KEY, tietFirstName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initComp() {
        imageView = findViewById(R.id.serban_mariana_luciana_ImageView);
        tietFirstName = findViewById(R.id.serban_mariana_luciana_tiet_firstname);
        tietLastName = findViewById(R.id.serban_mariana_luciana_tiet_lastname);
        tietPhoneNumber = findViewById(R.id.serban_mariana_luciana_tiet_phoneNumber);
        spnCity = findViewById(R.id.serban_mariana_luciana_spn_city);
        btnSave = findViewById(R.id.serban_mariana_luciana_btnSave);
        citiesList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.add_city)));
    }

    private boolean validation() {
        String firstName = tietFirstName.getText() != null ? tietFirstName.getText().toString().trim() : "";
        if (firstName.isEmpty()) {
            tietFirstName.setError(getString(R.string.error_emptyFirstName));
            return false;
        }
        if (firstName.length() < 2) {
            tietFirstName.setError(getString(R.string.error_firstNameLength));
            return false;
        }
        if (!firstName.matches("[a-zA-Z ]+")) {
            tietFirstName.setError(getString(R.string.error_firstNameChar));
            return false;
        }

        String lastName = tietLastName.getText() != null ? tietLastName.getText().toString().trim() : "";
        if (lastName.isEmpty()) {
            tietLastName.setError(getString(R.string.error_emptyLastName));
            return false;
        }
        if (!lastName.matches("[a-zA-Z ]+")) {
            tietLastName.setError(getString(R.string.error_lastNameChar));
            return false;
        }
        if (lastName.length() < 2) {
            tietLastName.setError(getString(R.string.error_lastNameLength));
            return false;
        }

        String phoneNumber = tietPhoneNumber.getText() != null ? tietPhoneNumber.getText().toString().trim() : "";
        if (phoneNumber.isEmpty()) {
            tietPhoneNumber.setError(getString(R.string.error_emptyPhone));
            return false;
        }
        if (!phoneNumber.matches("^07[0-9]{8}$")) {
            tietPhoneNumber.setError(getString(R.string.error_invalidPhone));
            return false;
        }

        if (spnCity.getSelectedItem() == null) {
            showError(getString(R.string.error_city));
            return false;
        }
        return true;
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}