package com.example.NurseProgram.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Patient;
import com.example.NurseProgram.Models.Test;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.PatientViewModel;
import com.example.NurseProgram.ViewModels.TestViewModel;

import java.util.List;

public class PatientHistoryActivity extends AppCompatActivity {

    private int patientId;
    private PatientViewModel patientViewModel;
    private Patient actualPatient;
    private TestViewModel testViewModel;

    private TextView  tvFirstName,tvLastName, tvDepartmentID,tvRoom;
    private EditText  et_FirstName,et_LastName, et_DepartmentID,et_Room;
    private List<Test> testList;
    private ListView testListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientId = getIntent().getIntExtra("patientId", 0);
        actualPatient = patientViewModel.getPatientById(patientId);

        et_FirstName = findViewById(R.id.et_FirstName);
        et_LastName = findViewById(R.id.et_LastName);
        et_DepartmentID = findViewById(R.id.et_Department);
        et_Room = findViewById(R.id.et_Room);

        tvFirstName = findViewById(R.id.tV_FirstName);
        tvFirstName.setText(actualPatient.getFirstName());

        tvLastName = findViewById(R.id.tV_LastName);
        tvLastName.setText(actualPatient.getLastname());

        tvDepartmentID = findViewById(R.id.tV_Department);
        tvDepartmentID.setText(actualPatient.getDepartment());
        tvRoom = findViewById(R.id.tV_Room);
        tvRoom.setText(String.valueOf(actualPatient.getRoom()));

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testList = testViewModel.getTestsByPatientId(patientId);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_2, android.R.id.text1, testList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView testLine1 = view.findViewById(android.R.id.text1);
                TextView testLine2 = view.findViewById(android.R.id.text2);

                testLine1.setTextSize(16);
                testLine1.setText("- Test ID: "+ testList.get(position).getTestId()+"\n- Nurse ID: "+testList.get(position).getNurseId()+
                        " \n- Patient BPL: "+testList.get(position).getBPL()+"\n- Temperature: "+testList.get(position).getTemperature()+" \n- Patient BPM: "+testList.get(position).getBPM());

                testLine2.setText("- Notes: "+testList.get(position).getAuscultation()+"\n- Alerts: "+testList.get(position).getBodyInspection());

                return view;

            }

        };

        testListView = findViewById(R.id.testListView);
        testListView.setAdapter(arrayAdapter);
        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textView = view.findViewById(android.R.id.text1);
                //String[] str = textView.getText().toString().split("-");
                int testId = Integer.valueOf(textView.getText().toString().substring(11,12));

                Intent intent = new Intent(getApplicationContext(),TestInfoActivity.class);
                intent.putExtra("testId", testId);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "Patient ID is: "+str[0], Toast.LENGTH_LONG).show();
            }
        });

    }
    public void addTest(View view){
        Intent intent = new Intent(this,ViewTestInfoActivity.class);
        intent.putExtra("patientId", patientId);
        startActivity(intent);
    }
    public void deletePatient(View view){
        patientViewModel.delete(actualPatient);
        Intent intent = new Intent(this,PatientActivity.class);
        startActivity(intent);
        finish();
    }

    public void editPatient(View view){
        Log.i("Patient", "Entered in edit Patient method and patinet Id is "+ String.valueOf(patientId));
        Intent intent = new Intent(this,PatientEditActivity.class);
        intent.putExtra("patientId", patientId);
        startActivity(intent);
    }
}
