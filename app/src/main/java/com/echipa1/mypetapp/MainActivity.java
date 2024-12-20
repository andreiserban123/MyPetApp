package com.echipa1.mypetapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import classes.Appointment;
import classes.Pet;

public class MainActivity extends AppCompatActivity {
    public static final String APPOINTMENT = "appointment";
    public static final String PETS = "pets";
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
    private TextView tvName;

    private List<Appointment> appointments;
    private ArrayAdapter<String> appointmentsAdapter;

    private ListView petsListView;
    private ArrayAdapter<Pet> petsAdapter;
    private ActivityResultLauncher<Intent> launcher;

    private ListView appointmentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();
        setupPetsListView();
        setupAppointmentsListView();
        initLauncher();
        setOnClickListeners();
    }

    private void setupPetsListView() {
        pets = new ArrayList<>();  // Initialize the list here
        pets.add(new Pet("Dog", 12, "Male", 23.3, "Azorel", "Pug", true, true));
        pets.add(new Pet("Cat", 5, "Female", 2, "Maricica", "British Short Hair", true, false));
        petsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                pets
        );


        petsListView.setAdapter(petsAdapter);
    }

    private void setupAppointmentsListView() {
        appointments = new ArrayList<>();
        appointmentsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>()
        );
        appointmentsListView.setAdapter(appointmentsAdapter);
    }

    private void initComponents() {
        aboutBtn = findViewById(R.id.serban_andrei_main_btn_about);
        profileBtn = findViewById(R.id.serban_andrei_main_btn_profile);
        addPetBtn = findViewById(R.id.serban_andrei_main_btn_add_pet);
        scheduleBtn = findViewById(R.id.serban_andrei_main_btn_schedule_vet);
        petsListView = findViewById(R.id.serban_andrei_main_lv_pets);
        appointmentsListView = findViewById(R.id.serban_andrei_main_lv_appointments);
        tvName = findViewById(R.id.serban_andrei_main_account_name);
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
        }
    }

    private void handleAddPetResult(Intent data) {
        Pet newPet = (Pet) data.getSerializableExtra(AddPetActivity.PET_KEY);
        String name = data.getStringExtra(AddPetActivity.PET_NAME);
        Log.i("MainActivity", "Pet name: " + name);
        if (newPet != null) {
            pets.add(newPet);
            petsAdapter.notifyDataSetChanged();
            Toast.makeText(this, R.string.new_pet_added + "with name: " + name, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleProfileResult(Intent data) {
        String profileName = data.getStringExtra(AddAccountActivity.FIRST_NAME_KEY);

        if (profileName != null) {
            tvName.setText(getString(R.string.welcome_back) + profileName);
        }
    }

    private void handleScheduleResult(Intent data) {
        if (data != null) {
            Appointment newAppointment = (Appointment) data.getSerializableExtra(APPOINTMENT);
            if (newAppointment != null) {
                appointments.add(newAppointment);
                updateAppointmentsList();
                Toast.makeText(this, R.string.appointment_scheduled, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateAppointmentsList() {
        List<String> appointmentStrings = new ArrayList<>();
        for (Appointment appointment : appointments) {
            String formattedDate = android.text.format.DateFormat.format("MM/dd/yyyy HH:mm",
                    appointment.getAppointmentDate()).toString();

            String displayText = String.format("%s - %s with %s",
                    formattedDate,
                    appointment.getPet(),
                    appointment.getVeterinarianName());

            appointmentStrings.add(displayText);
        }

        appointmentsAdapter.clear();
        appointmentsAdapter.addAll(appointmentStrings);
        appointmentsAdapter.notifyDataSetChanged();
    }

    private void setOnClickListeners() {
        addPetBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPetActivity.class);
            intent.putExtra(REQUEST_CODE, ADD_PET_REQUEST);
            launcher.launch(intent);
        });

        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
            intent.putExtra(REQUEST_CODE, PROFILE_REQUEST);
            launcher.launch(intent);
        });
        scheduleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAppointmentActivity.class);
            intent.putExtra(REQUEST_CODE, SCHEDULE_REQUEST);

            ArrayList<String> petNames = new ArrayList<>();
            for (Pet pet : pets) {
                petNames.add(pet.getName());
            }
            intent.putStringArrayListExtra(PETS, petNames);
            launcher.launch(intent);
        });
        aboutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        });
    }
}