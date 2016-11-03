package base;

import android.content.Context;

import javax.inject.Singleton;

import bean.Man;
import bean.Person;
import bean.PersonInfo;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/3.
 */
@Module
public class BaseApplicationModule {

    private MyApplication myApplication;

    public BaseApplicationModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Singleton
    @Provides
    public Context getContext() {
        return myApplication;
    }

    @Provides
    public Man providesPersoninfo() {
        return new Man("huangsss");
    }


}
