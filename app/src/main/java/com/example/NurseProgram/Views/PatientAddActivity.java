package com.example.NurseProgram.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Patient;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.PatientViewModel;

public class PatientAddActivity extends AppCompatActivity {

    private EditText etPatientId, etfirstName, etlastName, etdepartment, etroom;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        //etPatientId = findViewById(R.id.etpatientId);
        etfirstName = findViewById(R.id.etpatientFirstName);
        etlastName = findViewById(R.id.etpatientLastName);
        etdepartment = findViewById(R.id.etpatientDepartment);
        etroom = findViewById(R.id.etpatientRoom);
        //etnurseId = findViewById(R.id.etn_NurseID);

    }

    public void addNewPatient(View view)
    {
        Patient patient = new Patient();
        //patient.setPatientId(Integer.valueOf(etPatientId.getText().toString()));
        patient.setFirstName(etfirstName.getText().toString());
        patient.setLastname(etlastName.getText().toString());
        patient.setDepartment(etdepartment.getText().toString());
        patient.setRoom(Integer.valueOf(etroom.getText().toString()));

        SharedPreferences sharedPreferences = getSharedPreferences("NurseID",MODE_PRIVATE);
        patient.setNurseId(Integer.valueOf(sharedPreferences.getString("NurseID", "")));

        patientViewModel.insert(patient);

        startActivity(new Intent(this, PatientActivity.class));
    }
}
