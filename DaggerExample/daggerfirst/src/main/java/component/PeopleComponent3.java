package component;

import com.example.daggerfirst.MainActivity3;

import dagger.Component;
import module.PeopleModule3;

/**
 * Created by huangcl on 2016/11/1.
 */
@Component(modules = PeopleModule3.class)
public interface PeopleComponent3 {
    //表示PeopleModule里提供的实例会注入到MainActivity里面
    void inject(MainActivity3 activity);
}
