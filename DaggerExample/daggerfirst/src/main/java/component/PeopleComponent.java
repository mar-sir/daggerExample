package component;

import com.example.daggerfirst.MainActivity;

import dagger.Component;
import module.PeopleModule;

/**
 * Created by huangcl on 2016/11/1.
 *
 * 表示Dagger2将会在PeopleModule里面寻找提供的实例
 */
@Component(modules = PeopleModule.class)
public interface PeopleComponent {
    //表示PeopleModule里提供的实例会注入到MainActivity里面
    void inject(MainActivity activity);
}
