package com.example.daggerfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import annotation.IntName;
import bean.People;

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

    }

    public void tstDagger(View view) {

    }
}
