package com.echipa1.mypetapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import classes.Pet;

public class AddPet extends AppCompatActivity {

    private TextInputEditText owner_name_editTxt;
    private TextInputEditText pet_name_editTxt;
    private Spinner species_spinner;
    private TextInputEditText breed_editTxt;
    private RadioGroup gender_radioGrp;
    private TextInputEditText age_editTxt;
    private TextInputEditText weight_editTxt;
    private RadioGroup chip_radioGrp;
    private RadioGroup vacc_radioGrp;
    private Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponent();
    }

    private void initComponent() {
        owner_name_editTxt = findViewById(R.id.timofte_serban_add_pet_tiet_owner_name);
        pet_name_editTxt = findViewById(R.id.timofte_serban_add_pet_tiet_owner_pet_name);
        species_spinner = findViewById(R.id.timofte_serban_add_pet_spinner_species);
        breed_editTxt = findViewById(R.id.timofte_serban_add_pet_tiet_breed);
        age_editTxt = findViewById(R.id.timofte_serban_add_pet_tiet_age);
        weight_editTxt = findViewById(R.id.timofte_serban_add_pet_tiet_weight);  // Inițializare weight
        gender_radioGrp = findViewById(R.id.timofte_serban_add_pet_radGroup_gender);
        chip_radioGrp = findViewById(R.id.timofte_serban_add_pet_radGroup_chip);
        vacc_radioGrp = findViewById(R.id.timofte_serban_add_pet_radGroup_vacc);
        save_btn = findViewById(R.id.timofte_serban_add_pet_btn_save);

        save_btn.setOnClickListener(view -> {
            if (isValid()) {
                String ipt_ownerName = owner_name_editTxt.getText().toString().trim();
                String petName = pet_name_editTxt.getText().toString().trim();
                String species = species_spinner.getSelectedItem().toString();
                String breed = breed_editTxt.getText().toString().trim();
                String gender = (gender_radioGrp.getCheckedRadioButtonId() == R.id.timofte_serban_add_pet_radBtn_male) ? "Male" : "Female";
                int age = Integer.parseInt(age_editTxt.getText().toString().trim());
                double weight = Double.parseDouble(weight_editTxt.getText().toString().trim());
                boolean microchip = chip_radioGrp.getCheckedRadioButtonId() == R.id.timofte_serban_add_pet_radBtn_chip_yes;
                boolean anual_vaccination = vacc_radioGrp.getCheckedRadioButtonId() == R.id.timofte_serban_add_pet_radBtn_vacc_yes;

                Pet newPet = new Pet(species, age, gender, weight, petName, breed, ipt_ownerName, microchip, anual_vaccination);
                Toast.makeText(getApplicationContext(), "Pet Created: " + newPet.getName(), Toast.LENGTH_SHORT).show();

                Log.i("AddPetActivity", newPet.toString());
            }
        });
    }

    private boolean isValid() {
        if (owner_name_editTxt.getText() == null ||
                owner_name_editTxt.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_owner_name, Toast.LENGTH_LONG).show();
            return false;
        }

        if (breed_editTxt.getText() == null ||
                breed_editTxt.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_breed_name, Toast.LENGTH_LONG).show();
            return false;
        }

        if (age_editTxt.getText() == null ||
                age_editTxt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_age, Toast.LENGTH_LONG).show();
            return false;
        }

        int age;
        try {
            age = Integer.parseInt(age_editTxt.getText().toString().trim());
            if (age <= 0) {
                Toast.makeText(getApplicationContext(), R.string.add_invalid_age, Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_age, Toast.LENGTH_LONG).show();
            return false;
        }

        if (weight_editTxt.getText() == null ||
                weight_editTxt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_weight, Toast.LENGTH_LONG).show();
            return false;
        }

        double weight;
        try {
            weight = Double.parseDouble(weight_editTxt.getText().toString().trim());
            if (weight <= 1) {
                Toast.makeText(getApplicationContext(), R.string.add_invalid_weight, Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_weight, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}