package com.example.daggersecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import bean.People;
import component.DaggerPeopleComponent;

public class MainActivity1 extends AppCompatActivity {

    @Inject
    People student2;
    @Inject
    People student3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        DaggerPeopleComponent.builder().build().inject(this);
        ((TextView) findViewById(R.id.rootTxt)).setText(student2.hashCode() + "\t\t\t\t"+student3.hashCode());
    }
}
