package com.echipa1.mypetapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddAppointment extends AppCompatActivity {

    private Spinner spnPet;
    private Spinner spnDoctor;
    private TextInputEditText tietReason;
    private CalendarView cvDate;
    private TimePicker tpHour;
    private MaterialButton mbImage;
    private Button bttnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_add_consultation), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComp();
    }

    private void initComp() {
        spnPet = findViewById(R.id.surugiu_george_alexandru_sp_animal);
        spnDoctor = findViewById(R.id.surugiu_george_alexandru_sp_doctor);
        tietReason = findViewById(R.id.surugiu_george_alexandru_tiet_consultationReason);
        cvDate = findViewById(R.id.surugiu_george_alexandru_calendarView);
        tpHour = findViewById(R.id.surugiu_george_alexandru_tp_appointmentHour);
        mbImage = findViewById(R.id.surugiu_george_alexandru_btn_camera);
        bttnSave = findViewById(R.id.surugiu_george_alexandru_bttnSave);
    }
}