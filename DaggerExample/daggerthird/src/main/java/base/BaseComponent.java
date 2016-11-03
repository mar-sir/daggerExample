package base;


import bean.Man;
import bean.Person;
import bean.PersonInfo;
import bean.Teacher;
import dagger.Component;

/**
 * Created by huangcl on 2016/11/3.
 */
@Component(modules = BaseApplicationModule.class)
public interface BaseComponent {
    void inject(MyApplication myApplication);

    Man getMan();
}
