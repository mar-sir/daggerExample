package module;

import javax.inject.Named;

import bean.People;
import bean.PersonInfo;
import bean.Student;
import bean.Teacher;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
public class PeopleModule3 {
    @Named("Student")
    @Provides
    public People provideStudent(PersonInfo personInfo){
        personInfo.setAge(24);
        personInfo.setSex("男同学");
        return new Student(personInfo);
    }


    @Named("Teacher")
    @Provides
    public People provideTeacher(PersonInfo personInfo){
        personInfo.setAge(24);
        personInfo.setSex("女老师");
        return new Teacher(personInfo);
    }
}
