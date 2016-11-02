package component;

import com.example.daggerfirst.MainActivity1;
import com.example.daggerfirst.MainActivity2;

import dagger.Component;
import module.PeopleModule2;

/**
 * Created by huangcl on 2016/11/1.
 */
@Component(modules = PeopleModule2.class)
public interface PeopleComponent2 {
    //表示PeopleModule里提供的实例会注入到MainActivity里面
    void inject(MainActivity2 activity);
}
