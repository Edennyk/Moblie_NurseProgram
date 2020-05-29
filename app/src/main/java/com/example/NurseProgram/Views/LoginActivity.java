package com.example.NurseProgram.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.NurseProgram.Models.Nurse;
import com.example.NurseProgram.R;
import com.example.NurseProgram.ViewModels.NurseViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText nurseId;
    private EditText nursePassword;
    private NurseViewModel nurseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nurseId=findViewById(R.id.etNurseId);
        nursePassword=findViewById(R.id.etNursePassword);

        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);
        nurseViewModel.allNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
            }
        });
    }

    public void signIn(View view){
        String nId = nurseId.getText().toString();
        String nPw = nursePassword.getText().toString();

        //login null check
        if(nId.matches("") || nPw.matches("")){
            Toast.makeText(this,"Enter your ID and Password",Toast.LENGTH_LONG).show();
        }
        else{
            if(nurseViewModel.checkLogin(Integer.valueOf(nurseId.getText().toString()), nursePassword.getText().toString())){
                try {
                    // for editing nurse id
                    SharedPreferences sharedPreferences = getSharedPreferences("NurseID", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("NurseID", nurseId.getText().toString());
                    editor.commit();

                    Intent intent = new Intent(this, PatientActivity.class);
                    startActivity(intent);
                }
                catch (Exception ex){
                    Toast.makeText(this,"EXCEPTION ERROR: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this,"Your ID or Password is wrong",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void registerNurse(View view){
        Intent intent=new Intent(this,NurseRegistrationActivity.class);
        startActivity(intent);

    }
}
