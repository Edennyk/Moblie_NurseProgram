package com.example.NurseProgram.Models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Nurse.class,Patient.class,Test.class}, version=1, exportSchema = false)

public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;
    private static final String DATABASE_NAME="NursingApp";

    //abstract methods
    public abstract NurseDao nurseDao();
    public abstract PatientDao patientDao();
    public abstract TestDao testDao();


    public static synchronized AppDB getInstance(Context context){
        if(instance==null){
            //create database object
            instance = Room.databaseBuilder(context,
                    AppDB.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
