package component;

import com.example.daggersecond.LazyLoadActivity;

import dagger.Component;
import module.LazyLoadModule;

/**
 * Created by huangcl on 2016/11/2.
 */
@Component(modules = LazyLoadModule.class)
public interface LazyLoadComponent {
    void inject(LazyLoadActivity lazyLoadActivity);
}
