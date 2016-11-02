package component;

import com.example.daggerfirst.MainActivity;
import com.example.daggerfirst.MainActivity1;

import dagger.Component;
import module.PeopleModule1;

/**
 * Created by huangcl on 2016/11/1.
 */
@Component(modules = PeopleModule1.class)
public interface PeopleComponent1 {
    //表示PeopleModule里提供的实例会注入到MainActivity里面
    void inject(MainActivity1 activity);
}
