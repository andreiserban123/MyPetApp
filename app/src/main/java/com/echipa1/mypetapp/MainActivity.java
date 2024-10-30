package com.echipa1.mypetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import classes.Pet;

public class MainActivity extends AppCompatActivity {
    private static final String REQUEST_CODE = "request_code";
    private static final int ADD_PET_REQUEST = 1;
    private static final int PROFILE_REQUEST = 2;
    private static final int SCHEDULE_REQUEST = 3;
    private static final int ABOUT_REQUEST = 4;
    private List<Pet> pets;
    private MaterialButton aboutBtn;
    private MaterialButton profileBtn;
    private MaterialButton addPetBtn;
    private MaterialButton scheduleBtn;

    private ListView petsListView;
    private ArrayAdapter<Pet> petsAdapter;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();
        setupListView();
        initLauncher();
        setOnClickListeners();
    }

    private void setupListView() {
        pets = new ArrayList<>();  // Initialize the list here
        pets.add(new Pet("Dog", 12, "Male", 23.3, "Dogo", "Pug", "florin", true, true));
        petsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pets
        );
        petsListView.setAdapter(petsAdapter);
    }

    private void initComponents() {
        aboutBtn = findViewById(R.id.serban_andrei_main_btn_about);
        profileBtn = findViewById(R.id.serban_andrei_main_btn_profile);
        addPetBtn = findViewById(R.id.serban_andrei_main_btn_add_pet);
        scheduleBtn = findViewById(R.id.serban_andrei_main_btn_schedule_vet);
        petsListView = findViewById(R.id.serban_andrei_main_lv_pets);
    }

    private void initLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            handleActivityResult(data);
                        }
                    }
                });
    }

    private void handleActivityResult(Intent data) {
        int requestCode = data.getIntExtra(REQUEST_CODE, 0);

        switch (requestCode) {
            case ADD_PET_REQUEST:
                handleAddPetResult(data);
                break;
            case PROFILE_REQUEST:
                handleProfileResult(data);
                break;
            case SCHEDULE_REQUEST:
                handleScheduleResult(data);
                break;
            case ABOUT_REQUEST:
                // Usually no handling needed for about
                break;
        }
    }

    private void handleAddPetResult(Intent data) {
        Pet newPet = data.getParcelableExtra("new_pet");
        if (newPet != null) {
            pets.add(newPet);
            petsAdapter.notifyDataSetChanged();
            Log.i("AddPet", pets.toString());
            Toast.makeText(this, "New pet added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleProfileResult(Intent data) {
        // Handle any profile updates
        boolean profileUpdated = data.getBooleanExtra("profile_updated", false);
        if (profileUpdated) {
            // Refresh profile data
            // refreshProfileData();
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleScheduleResult(Intent data) {
        // Handle new appointment data
        boolean appointmentCreated = data.getBooleanExtra("appointment_created", false);
        if (appointmentCreated) {
            // Refresh appointments list
            // refreshAppointments();
            Toast.makeText(this, "Appointment scheduled successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setOnClickListeners() {
        addPetBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPetActivity.class);
            intent.putExtra(REQUEST_CODE, ADD_PET_REQUEST);
            launcher.launch(intent);
        });

//        profileBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//            intent.putExtra(REQUEST_CODE, PROFILE_REQUEST);
//            launcher.launch(intent);
//        });
//
//        scheduleBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
//            intent.putExtra(REQUEST_CODE, SCHEDULE_REQUEST);
//            intent.putParcelableArrayListExtra("pets", new ArrayList<>(pets));
//            launcher.launch(intent);
//        });
//
//        aboutBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//            intent.putExtra(REQUEST_CODE, ABOUT_REQUEST);
//            launcher.launch(intent);
//        });
    }
}