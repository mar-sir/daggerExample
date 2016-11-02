package com.example.daggerfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import bean.People;
import component.DaggerPeopleComponent2;

/**
 * 当DaggerPeopleComponent2里面提供了PersonInfo的实例之后，Dagger就不会在去寻找PersonInfo里面@Inject的实例
 */

public class MainActivity2 extends AppCompatActivity {
    @Inject
    People student;
    @Inject
    People teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DaggerPeopleComponent2.create().inject(this);
    }

    public void tstStudent(View view) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }

    public void tstTeacher(View view) {
        Toast.makeText(this, teacher.toString(), Toast.LENGTH_SHORT).show();
    }

    public void jumpActivity(View view) {
       startActivity(new Intent(this,MainActivity3.class));
    }
}
