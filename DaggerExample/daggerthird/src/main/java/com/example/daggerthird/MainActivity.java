package com.example.daggerthird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import base.MyApplication;
import bean.Man;
import bean.Student;
import bean.Teacher;
import component.DaggerStudentComponent;


public class MainActivity extends AppCompatActivity {
    @Inject
    Teacher teacher;
    @Inject
    Student student;

    @Inject
    Man man;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerStudentComponent.builder()
                .baseComponent(((MyApplication) getApplication()).getBaseComponent())
                .build()
                .inject(this);
    }

    public void tstDagger(View view) {
        Toast.makeText(this, student.toString() + "\t" + teacher.toString() + "\t" + man.toString(), Toast.LENGTH_LONG).show();
    }

    public void jump(View view) {
        startActivity(new Intent(this, MainActivity1.class));
    }
}
