package com.example.daggersecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Provider;

import bean.People;
import dagger.Lazy;

public class LazyLoadActivity extends AppCompatActivity {

    @Inject
    Lazy<People> student;

    @Inject
    Provider<People> student1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_load);
        DaggerLazyLoadComponent.create().inject(this);
    }
}
