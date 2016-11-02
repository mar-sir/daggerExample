package component;

import com.example.daggersecond.MainActivity;
import com.example.daggersecond.MainActivity1;

import javax.inject.Singleton;

import dagger.Component;
import module.PeopleModule;

/**
 * Created by huangcl on 2016/11/2.
 */
@Singleton
@Component(modules = PeopleModule.class)
public interface PeopleComponent {
    void inject(MainActivity mainActivity);
    void inject(MainActivity1 mainActivity);
}
