package module;

import bean.People;
import bean.Student;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
public class LazyLoadModule {

    @Provides
    public People provideStudent(){
        return new Student();
    }
}
