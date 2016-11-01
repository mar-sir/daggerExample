package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by huangcl on 2016/11/1.
 */
@Documented
@Qualifier//表示IntName用来区分用途
@Retention(RetentionPolicy.RUNTIME)//运行时有效
public @interface IntName {
    int value();
}
