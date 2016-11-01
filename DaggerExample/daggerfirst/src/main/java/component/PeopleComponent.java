package component;

import com.example.daggerfirst.MainActivity;

import dagger.Component;
import module.PeopleModule;

/**
 * Created by huangcl on 2016/11/1.
 */
@Component(modules = PeopleModule.class)
public interface PeopleComponent {
    void inject(MainActivity activity);
}
