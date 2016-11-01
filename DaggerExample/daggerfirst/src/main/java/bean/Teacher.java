package bean;

/**
 * Created by huangcl on 2016/11/1.
 */
public class Teacher extends People {
    private PersonInfo personInfo;

    public Teacher(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    @Override
    public String toString() {
        return personInfo.toString();
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }


}
