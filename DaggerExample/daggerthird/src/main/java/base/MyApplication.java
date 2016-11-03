package base;

import android.app.Application;

import javax.inject.Inject;

import bean.Teacher;


/**
 * Created by huangcl on 2016/11/3.
 */
public class MyApplication extends Application {

    BaseComponent baseComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        baseComponent = DaggerBaseComponent.builder()
                .baseApplicationModule(new BaseApplicationModule(this))
                .build();
        baseComponent.inject(this);
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }



}
