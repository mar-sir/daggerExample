package module;

import annotation.IntName;
import bean.People;
import bean.PersonInfo;
import bean.Student;
import bean.Teacher;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/1.
 * 如果Dagger2 找不到带@Provides 提供的对应参数的对象,自动调用@Inject参数的构造方法生成的对象对象
 */
@Module//表示本类属于Module
public class PeopleModule {
    @IntName(1)
    @Provides//表示该方法用来向外提供依赖对象
    public People provideStudent(PersonInfo personInfo){
        return new Student(personInfo);
    }

    @IntName(2)
    @Provides//表示该方法用来向外提供依赖对象
    public People provideTeacher(PersonInfo personInfo){
        return new Teacher(personInfo);
    }

}
