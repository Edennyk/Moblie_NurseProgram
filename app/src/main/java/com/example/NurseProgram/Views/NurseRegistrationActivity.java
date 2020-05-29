package com.example.NurseProgram.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Nurse;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.NurseViewModel;

public class NurseRegistrationActivity extends AppCompatActivity {
    private EditText nurseIdEt,firstNameEt,lastNameEt,departmentNameEt,passwordEt;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_registration);
        nurseIdEt=findViewById(R.id.nurseIDEt);
        firstNameEt=findViewById(R.id.firstNameEt);
        lastNameEt=findViewById(R.id.lastNameEt);
        departmentNameEt=findViewById(R.id.departmentEt);
        passwordEt=findViewById(R.id.passwordEt);
        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);
    }


    public void registerNurse(View view){
        try {
            Nurse nurse = new Nurse();

            nurse.setNurseId(Integer.valueOf(nurseIdEt.getText().toString()));
            nurse.setFirstName(firstNameEt.getText().toString());
            nurse.setLastname(lastNameEt.getText().toString());
            nurse.setDepartment(departmentNameEt.getText().toString());
            nurse.setPassword(passwordEt.getText().toString());
            nurseViewModel.insert(nurse);
            Toast.makeText(this,"The new nurse was added to the system successfully!", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        catch(Exception ex){
            Toast.makeText(this,ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
