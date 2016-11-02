package module;

import javax.inject.Singleton;

import bean.People;
import bean.Student;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
public class PeopleModule {

    @Singleton
    @Provides
    public People ProvideStudent() {
        return new Student();
    }
}
