package component;


import com.example.daggerthird.MainActivity1;

import dagger.Component;
import module.StudentModule;
import module.TeacherModule;

/**
 * Created by huangcl on 2016/11/3.
 */
@Component(modules = {TeacherModule.class})
public interface TeacherComponent {
    void inject(MainActivity1 mainActivity1);
}
