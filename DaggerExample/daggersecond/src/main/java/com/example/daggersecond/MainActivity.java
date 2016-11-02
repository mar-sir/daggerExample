package com.example.daggersecond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import bean.People;
import component.DaggerPeopleComponent;

public class MainActivity extends AppCompatActivity {

    @Inject
    People student;

    @Inject
    People student1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerPeopleComponent.create().inject(this);
    }

    public void test(View view) {
      if (student.hashCode()==student1.hashCode()){
          Toast.makeText(this,"student.hashCode()==student1.hashCode()",Toast.LENGTH_SHORT).show();
          startActivity(new Intent(this,MainActivity1.class));
          Toast.makeText(this,student.hashCode()+"",Toast.LENGTH_LONG).show();
      }
    }

    public void lazyLoad(View view) {
        
    }
}
