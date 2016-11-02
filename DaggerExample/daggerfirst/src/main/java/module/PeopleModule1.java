package module;

import javax.inject.Named;

import bean.People;
import bean.PersonInfo;
import bean.Student;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
//一个完整的的Module必须包含@Module和@Provides
public class PeopleModule1 {

    @Provides //注明该方法用来提供依赖对象
    public People provideStudent(){
        PersonInfo personInfo = new PersonInfo(20,"楼主1");
        return new Student(personInfo);
    }
    @Named("teacher")
    @Provides
    public People provideTeacher(){
        PersonInfo personInfo = new PersonInfo(22,"楼主11");
        return new Student(personInfo);
    }
}
