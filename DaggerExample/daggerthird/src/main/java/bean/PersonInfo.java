package bean;

import javax.inject.Inject;

/**
 * Created by huangcl on 2016/11/3.
 */
public class PersonInfo {
    String name;
    int age;

    @Inject
    public PersonInfo() {
        this.name = "haha";
        this.age = 12;
    }

    public PersonInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age;
    }

}
