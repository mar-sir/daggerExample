package module;

import bean.PersonInfo;
import bean.Student;
import bean.Teacher;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/3.
 */
@Module
public class TeacherModule {


    @Provides
    public Teacher provideTeacher(PersonInfo personInfo) {
        return new Teacher(personInfo);
    }
}
