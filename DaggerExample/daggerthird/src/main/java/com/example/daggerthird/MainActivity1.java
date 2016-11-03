package com.example.daggerthird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import bean.Teacher;
import component.DaggerTeacherComponent;

public class MainActivity1 extends AppCompatActivity {

    @Inject
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DaggerTeacherComponent.builder().build().inject(this);
        ((TextView) findViewById(R.id.root)).setText(teacher.toString());
    }
}
