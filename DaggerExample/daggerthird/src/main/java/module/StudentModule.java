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
public class StudentModule {


    @Provides
    public Student provideStudent(PersonInfo personInfo) {
        return new Student(personInfo);
    }


    @Provides
    public PersonInfo providePersonInfo() {
        return new PersonInfo(100, "呵呵哈");
    }


}
