package com.example.daggerfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import annotation.IntName;
import bean.People;
import component.DaggerPeopleComponent;
import module.PeopleModule;

public class MainActivity extends AppCompatActivity {
    @IntName(1)
    @Inject
    People student;
    @IntName(2)
    @Inject
    People teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //两种注入方式都行，在编译后，会自动生一些文档，在注入的时候Dagger会找到PersonInfo类里面@Inject提供出来的构造实例。所以不会导致PeopleModule
        //类里面@Provides注解的方法找不到PersonInfo的实例；
        //Dagger注入某个实例，则需要@Inject或者@Provides注解.
        //找不到带@Provides 提供的对应参数的对象,自动调用@Inject参数的构造方法生成的对象对象
        //DaggerPeopleComponent.create().inject(this);
        DaggerPeopleComponent.builder().peopleModule(new PeopleModule()).build().inject(this);
    }


    public void tstStudent(View view) {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }

    public void tstTeacher(View view) {
        Toast.makeText(this, teacher.toString(), Toast.LENGTH_SHORT).show();
    }

    public void jumpActivity(View view) {
        startActivity(new Intent(this,MainActivity1.class));
    }
}
