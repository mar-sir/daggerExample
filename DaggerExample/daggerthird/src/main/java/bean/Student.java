package bean;

import javax.inject.Inject;

/**
 * Created by huangcl on 2016/11/3.
 */
public class Student extends Person {

    private PersonInfo personInfo;

    public Student(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    @Override
    public String toString() {
        return personInfo.toString();
    }

}
