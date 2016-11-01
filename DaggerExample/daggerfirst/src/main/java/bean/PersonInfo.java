package bean;

import javax.inject.Inject;

/**
 * Created by huangcl on 2016/11/1.
 */
public class PersonInfo {
    private int age;

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonInfo(int age, String sex) {
        this.age = age;
        this.sex = sex;
    }

    //Commponent会找到Inject注入实例
    @Inject
    public PersonInfo(){
        this.age=20;
        this.sex="人妖";
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
