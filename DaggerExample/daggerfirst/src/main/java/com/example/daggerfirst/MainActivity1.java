package com.example.daggerfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import bean.People;
import component.DaggerPeopleComponent1;

/**
 * 注入对应的实例
 */
public class MainActivity1 extends AppCompatActivity {

    @Named("teacher")
    @Inject
    People teacher;
    @Inject
    People student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        DaggerPeopleComponent1.create().inject(this);
    }

    public void tstStudent(View view) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }

    public void tstTeacher(View view) {
        Toast.makeText(this, teacher.toString(), Toast.LENGTH_SHORT).show();
    }

    public void jumpActivity(View view) {
      startActivity(new Intent(this,MainActivity2.class));
    }

}
