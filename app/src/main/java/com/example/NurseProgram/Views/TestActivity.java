package com.example.NurseProgram.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Test;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.TestViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private TestViewModel testViewModel;
    private List<Test> testList;
    private ListView testListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testList = new ArrayList<Test>();

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
//        patientViewModel.allPatients().observe(this, new Observer<List<Patient>>() {
//            @Override
//            public void onChanged(List<Patient> patients) {
//                Log.i("PatientActivity", " Observe.onChange");
//                patientList = patients;
//            }
//        });

//        private int testId;
//
//        private int patientId,nurseId,BPL, BPM,temperature;
//        private String auscultation,bodyInspection;



        testList = testViewModel.allTests();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, testList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView patientName = view.findViewById(android.R.id.text1);
                TextView patientInfo = view.findViewById(android.R.id.text2);

                patientName.setTextSize(16);
                patientName.setText(testList.get(position).getTestId() + " - " + testList.get(position).getBPM() + " " + testList.get(position).getBPL());

                patientInfo.setText("temperature: " + testList.get(position).getTemperature() + " - auscultation: " + testList.get(position).getAuscultation() + " - bodyInspection: " + testList.get(position).getBodyInspection());

                return view;

            }

        };


        testListView = findViewById(R.id.testListView);
        testListView.setAdapter(arrayAdapter);
        startActivity(new Intent(this, PatientHistoryActivity.class));


    }

    public void addnewTest(View view) {
        // Intent intent = new Intent(this, PatientAddActivity.class);
        //  startActivity(intent);

    }
}