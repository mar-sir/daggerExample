package bean;

/**
 * Created by huangcl on 2016/11/1.
 */
public class Student extends People {
    private PersonInfo personInfo;

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo people) {
        this.personInfo = people;
    }

    public Student(PersonInfo personInfo) {
      this.personInfo=personInfo;
    }

    @Override
    public String toString() {
        return personInfo.toString();
    }
}
