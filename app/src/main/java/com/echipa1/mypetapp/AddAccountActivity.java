package com.echipa1.mypetapp;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

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

    private ImageView imageView;
    private TextInputEditText tietFirstName;
    private TextInputEditText tietLastname;
    private TextInputEditText tietPhoneNumber;
    private Spinner spnCity;
    private Button btnSave;
    private Intent intent;

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
    }

    private void initComp() {
        imageView=findViewById(R.id.serban_mariana_luciana_ImageView);
        tietFirstName=findViewById(R.id.serban_mariana_luciana_tiet_firstname);
        tietLastname=findViewById(R.id.serban_mariana_luciana_tiet_lastname);
        tietPhoneNumber=findViewById(R.id.serban_mariana_luciana_tiet_phoneNumber);
        spnCity=findViewById(R.id.serban_mariana_luciana_sp_city);
        btnSave=findViewById(R.id.serban_mariana_luciana_btnSave);
        citiesList=new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.add_city)));
    }
}