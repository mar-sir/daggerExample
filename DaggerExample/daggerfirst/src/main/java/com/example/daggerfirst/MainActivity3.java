package com.example.daggerfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import bean.People;
import component.DaggerPeopleComponent3;

/**
 * Dagger2会自动找对应的@Named("Teacher")，@Named("Student")实例，如果找不到，Dagger2编译不通过。。。
 */

public class MainActivity3 extends AppCompatActivity {
    @Inject
    @Named("Teacher")
    People teacher;

    @Inject
    @Named("Student")
    People student;

    /*@Inject
    People students;*///错误

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        DaggerPeopleComponent3.create().inject(this);
    }
    public void tstStudent(View view) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }

    public void tstTeacher(View view) {
        Toast.makeText(this, teacher.toString(), Toast.LENGTH_SHORT).show();
    }

    public void jumpActivity(View view) {
    }
}
