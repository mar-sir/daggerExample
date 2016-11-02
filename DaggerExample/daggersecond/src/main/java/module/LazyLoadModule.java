package module;

import bean.Student;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huangcl on 2016/11/2.
 */
@Module
public class LazyLoadModule {

    @Provides
    public Student provideStudent(){
        return new Student();
    }
}
