#daggerfirst
##配置Dagger2的依赖
首先，在项目最外层.gradle文件中添加 //添加apt插件 （classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'）,如图:

![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step1.png?raw=true)

接着,在daggerfirst的.gradle文件里面添加Dagger2的依赖;
    
    //Dagger2
    compile 'com.google.dagger:dagger:2.4'     //公共API
    apt 'com.google.dagger:dagger-compiler:2.4' //注解处理器
    compile 'org.glassfish:javax.annotation:10.0-b28' //javax注解
还有别忘在.gradle文件最上面添加代码（apply plugin: 'com.neenbedankt.android-apt'），不然依赖包找不到,贴图如下:

![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step2.png?raw=true)

到此依赖添加完成！
##Dagger2的简单使用
就像这么一个场景   
    
    A a=new (new B(),new C());
像这样的我们通过Dagger2就可以直接

    @Inject
    A a;
这样写，是不是看起来少new了很多实例,这就是dagger2强解耦的特性,项目越大越能提会其优越性,而生成实体类的功劳归功于Component,Inject,Module这三大功臣。
下面让我们尝试和这三货打打交到；首先介绍一下我用的实体类;
######People类
     public class People {
         private String name;
     }
######Student类
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

######Teacher类
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
######PersonInfo类
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
         //在Component里面的module没有提供PersonInfo实例时，Dagger2会注入此实例
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
Student,Teacher继承People类，它们持有PersonInfo作为成员对象，这里构造了一个 Student s=new Student(new PersonInfo)这样一个场景类似;
A a=new A(new B());

####实体类写好之后我们看案例1。我们先写了一个PeopleModule,PeopleComponent,MainActivity.
###案例1
######PeopleModule,其中我自己写了一个注解,这和MainActivity里面的Inject不同的IntName对应起来(提供对应的实例).如果MainActivity里面不写对应的IntName,则编译不通过.
      /**
       * 如果Dagger2 找不到带@Provides 提供的对应参数的对象,自动调用@Inject参数的构造方法生成的对象对象
       */
      @Module//表示本类属于Module
      public class PeopleModule {
          @IntName(1)
          @Provides//表示该方法用来向外提供依赖对象
          public People provideStudent(PersonInfo personInfo){
              return new Student(personInfo);
          }
          @IntName(2)
          @Provides//表示该方法用来向外提供依赖对象
          public People provideTeacher(PersonInfo personInfo){
              return new Teacher(personInfo);
          }
      }
      
######PeopleComponent,注入器，有着链接的作用，充当桥梁的角色
     /**
      *
      * 表示Dagger2将会在PeopleModule里面寻找提供的实例
      */
     @Component(modules = PeopleModule.class)
     public interface PeopleComponent {
         //表示PeopleModule里提供的实例会注入到MainActivity里面
         void inject(MainActivity activity);
     }
######MainActivity，这里面没啥说的,就是Inject得到实例
     public class MainActivity extends AppCompatActivity {
         @IntName(1)
         @Inject
         People student;
         @IntName(2)
         @Inject
         People teacher;
         
         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
             //两种注入方式都行，在编译后，会自动生一些文档，在注入的时候Dagger会找到PersonInfo类里面@Inject提供出来的构造实例。所以不会导致PeopleModule
             //类里面@Provides注解的方法找不到PersonInfo的实例；
             //Dagger注入某个实例，则需要@Inject或者@Provides注解.
             //找不到带@Provides 提供的对应参数的对象,自动调用@Inject参数的构造方法生成的对象对象
             //DaggerPeopleComponent.create().inject(this);
             DaggerPeopleComponent.builder().peopleModule(new PeopleModule()).build().inject(this);
         }
     
     
         public void tstStudent(View view) {
             Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
         }
     
         public void tstTeacher(View view) {
             Toast.makeText(this, teacher.toString(), Toast.LENGTH_SHORT).show();
         }
     
         public void jumpActivity(View view) {
             startActivity(new Intent(this,MainActivity1.class));
         }
     }
在写完这些后点击运行，系统会自动生成一些文件如下图:
![Dagger2生成文件](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step3.png?raw=true)
在DaggerPeopleComponent.builder().peopleModule(new PeopleModule()).build().inject(this)被调用后，就像ButterKnife的ButterKnife.bind(this);它会帮
们自动绑定控件,这里的案例1,Component会在Module里寻找@Provides提供的实例,找到了两个Provides，但Provides的方法需要personInfo实例, 而PeopleModule
没有Provides出PersonInfo的实例，dagger则再去寻找对应的@Inject，正好在PersonInfo类里面提供了这么一个实例(personInfo),所以不会导致PeopleModule
里面@Provides注解的方法找不到PersonInfo的实例而报错.

###案例2
######PeopleModule1代码。
      @Module
      //一个完整的的Module必须包含@Module和@Provides
      public class PeopleModule1 {
          @Provides //注明该方法用来提供依赖对象
          public People provideStudent(){
              PersonInfo personInfo = new PersonInfo(20,"楼主1");
              return new Student(personInfo);
          }
          @Named("teacher")
          @Provides
          public People provideTeacher(){
              PersonInfo personInfo = new PersonInfo(22,"楼主11");
              return new Student(personInfo);
          }
      }
其他类代码大同小异。就是PeopleComponent1依赖的Module换成PeopleMoudle1.这个Module体现了，Dagger会通过@Name指定他要提取的是哪个实例。
###案例3
######PeopleModule2代码。
     @Module
     public class PeopleModule2 {
         @Provides
         public People providePeople(PersonInfo personInfo){
             return new Teacher(personInfo);
         }
     
         @Provides
         public PersonInfo providePersonInfo(){
             return new PersonInfo(22,"楼主2");
         }
     
     }
这里Module帮我们提供了一个PersonInfo实例，那么Dagger则不会在去找Inject的PersonInfo实例.
###案例4
######PeopleModule3代码。
    @Module
    public class PeopleModule3 {
        @Named("Student")
        @Provides
        public People provideStudent(PersonInfo personInfo){
            personInfo.setAge(24);
            personInfo.setSex("男同学");
            return new Student(personInfo);
        }
    
    
        @Named("Teacher")
        @Provides
        public People provideTeacher(PersonInfo personInfo){
            personInfo.setAge(24);
            personInfo.setSex("女老师");
            return new Teacher(personInfo);
        }
    }
这体现的和案例2一样。就不再说了，至此DaggerFirst已经介绍完毕.你明白了吗？