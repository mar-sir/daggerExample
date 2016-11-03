package component;

import com.example.daggerthird.MainActivity;

import base.BaseComponent;
import dagger.Component;
import module.StudentModule;
import module.TeacherModule;

/**
 * Created by huangcl on 2016/11/3.
 */
@Component(dependencies = BaseComponent.class, modules = {TeacherModule.class, StudentModule.class})
public interface StudentComponent {
    void inject(MainActivity mainActivity);
}
