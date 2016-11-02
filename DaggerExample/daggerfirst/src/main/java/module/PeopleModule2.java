package module;
import bean.People;
import bean.PersonInfo;
import bean.Teacher;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
public class PeopleModule2 {
    @Provides
    public People providePeople(PersonInfo personInfo){
        return new Teacher(personInfo);
    }

    @Provides
    public PersonInfo providePersonInfo(){
        return new PersonInfo(22,"楼主2");
    }

}
