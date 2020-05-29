package com.example.NurseProgram.Views;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Patient;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.PatientViewModel;

public class PatientEditActivity extends AppCompatActivity {
    private EditText etPatientId, etfirstName, etlastName, etdepartment, etroom, etnurseId;
    private Patient patient;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit);

        //etPatientId=findViewById(R.id.etpatientId);
        etfirstName=findViewById(R.id.etpatientFirstName);
        etlastName=findViewById(R.id.etpatientLastName);
        etdepartment=findViewById(R.id.etpatientDepartment);
        etroom=findViewById(R.id.etpatientRoom);
        //etnurseId=findViewById(R.id.etNurseId);

        int patientId = getIntent().getIntExtra("patientId", 0);
        Log.i("Patient", "Entered in PatientEditActivity class and patient is "+ String.valueOf(patientId));

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patient = patientViewModel.getPatientById(patientId);
        Log.i("Patient", "Entered in PatientEditActivity class and patientId is "+ patient.toString());

        //etPatientId.setText(String.valueOf(patient.getPatientId()));
        etfirstName.setText(patient.getFirstName());
        etlastName.setText(patient.getLastname());
        etdepartment.setText(patient.getDepartment());
        etroom.setText(String.valueOf(patient.getRoom()));
       // etnurseId.setText(patient.getNurseId());
    }

    public void savePatient(View view) {
        patient.setFirstName(etfirstName.getText().toString());
        patient.setLastname(etlastName.getText().toString());
        patient.setDepartment(etdepartment.getText().toString());
        patient.setRoom(Integer.valueOf(etroom.getText().toString()));

        //patient.setNurseId(Integer.valueOf(etnurseId.getText().toString()));

        patientViewModel.update(patient);
        Toast.makeText(this, "Patient Updated Successfully.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,PatientHistoryActivity.class);
        intent.putExtra("patientId", patient.getPatientId());
        startActivity(intent);
    }
}
