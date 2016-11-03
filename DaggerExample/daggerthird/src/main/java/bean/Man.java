package bean;

/**
 * Created by huangcl on 2016/11/3.
 */
public class Man {
    private String job;
    public Man(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Man{" +
                "job='" + job + '\'' +
                '}';
    }
}
