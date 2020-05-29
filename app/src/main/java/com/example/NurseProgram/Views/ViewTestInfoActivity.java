package com.example.NurseProgram.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Test;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.TestViewModel;

public class ViewTestInfoActivity extends AppCompatActivity {
    private TestViewModel testViewModel;
    private EditText et_BPL, et_BML, et_Temprature,et_BAuscutation,et_BInspection;

    private int nurseId, patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences= getSharedPreferences("NurseID",MODE_PRIVATE);
        nurseId = Integer.valueOf(sharedPreferences.getString("NurseID", ""));
        patientId = getIntent().getIntExtra("patientId", 0);

        setContentView(R.layout.activity_view_test_info);
        et_BPL=findViewById(R.id.et_BPL);
        et_BML= findViewById(R.id.et_BPM);
        et_Temprature= findViewById(R.id.et_Temperature);
        et_BAuscutation= findViewById(R.id.et_Auscultation);
        et_BInspection= findViewById(R.id.et_BInspection);
        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);

    }
    public void addNewTest (View view){

        Test test= new Test();
        test.setBPL(Integer.valueOf(et_BPL.getText().toString()));
        test.setBPM(Integer.valueOf(et_BML.getText().toString()));
        test.setTemperature(Integer.valueOf(et_Temprature.getText().toString()));
        test.setAuscultation(et_BAuscutation.getText().toString());
        test.setBodyInspection(et_BInspection.getText().toString());
        test.setPatientId(patientId);
        test.setNurseId(nurseId);

        try {
            testViewModel.insert(test);
            Intent intent = new Intent(this,PatientHistoryActivity.class);
            intent.putExtra("patientId", patientId);
            startActivity(intent);
            //Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex)
        {
            Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
