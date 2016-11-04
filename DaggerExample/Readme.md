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
#daggersecond
###第二个例子主要说明@Singleton的作用其实就像单例，只生成一个实例。还有懒加载模式:
###Singleton
![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step4.png?raw=true)
在MainActivity里面
    
    public class MainActivity extends AppCompatActivity {
    
        @Inject
        People student;
    
        @Inject
        People student1;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            DaggerPeopleComponent.create().inject(this);
        }
    
        public void test(View view) {
          if (student.hashCode()==student1.hashCode()){
              Toast.makeText(this,"student.hashCode()==student1.hashCode()",Toast.LENGTH_SHORT).show();
              startActivity(new Intent(this,MainActivity1.class));
              Toast.makeText(this,student.hashCode()+"",Toast.LENGTH_LONG).show();
          }
        }
    }
student和student2的hashCode是一样的，它们的内存地址也是一样的.其实@Singleton和我新写的@PerActivity作用是一样的;
######PerActivity
       
       /**
       * 
       * Created by huangcl on 2016/11/2.
       * 实体类作用域
       */
      @Scope
      @Retention(RetentionPolicy.RUNTIME)
      public @interface PerActivity {
          /**
           *   Java中提供了四种元注解，专门负责注解其他的注解，分别如下
           @Retention元注解，表示需要在什么级别保存该注释信息（生命周期）。可选的RetentionPoicy参数包括：
           RetentionPolicy.SOURCE: 停留在java源文件，编译器被丢掉
           RetentionPolicy.CLASS：停留在class文件中，但会被VM丢弃（默认）
           RetentionPolicy.RUNTIME：内存中的字节码，VM将在运行时也保留注解，因此可以通过反射机制读取注解的信息
      
           @Target元注解，默认值为任何元素，表示该注解用于什么地方。可用的ElementType参数包括
           ElementType.CONSTRUCTOR: 构造器声明
           ElementType.FIELD: 成员变量、对象、属性（包括enum实例）
           ElementType.LOCAL_VARIABLE: 局部变量声明
           ElementType.METHOD: 方法声明
           ElementType.PACKAGE: 包声明
           ElementType.PARAMETER: 参数声明
           ElementType.TYPE: 类、接口（包括注解类型)或enum声明
           @Documented将注解包含在JavaDoc中
           @Inheried允许子类继承父类中的注解
           */
      }
这里我们看到它是使用了@Scope的一个注释，这个注释的意思就是作用域，在作用域内保持单例，可以直接理解为单例即可。为什么要新增一个呢，
主要是因为各个组件需要独立出来，因此如果是依赖关系，则需要各自在不同的注释作用域里面,这里@Singleton 就是一个普通的作用域通道，使
用了作用域@Scope注释的代码，会变成单例模式。
###懒加载，比较简单，一张图让你明白:
![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step5.png?raw=true)

#Daggersecond
###主要简介dependencies,modules={Module1.class,Module2.class...},的作用和用途.
####modules={Module1.class,Module2.class...}
在一个容器（Activity）中,如果要用到的不止Module1里所提供的Provides，还要用到Module2等等这种情况,我们就可以这么写：
######StudentComponent
    @Component(dependencies = BaseComponent.class, modules = {TeacherModule.class, StudentModule.class})
    public interface StudentComponent {
        void inject(MainActivity mainActivity);
    }
######StudentModule
  
     @Provides
     public Student provideStudent(PersonInfo personInfo) {
        return new Student(personInfo);
     }
  
    
     @Provides
     public PersonInfo providePersonInfo() {
        return new PersonInfo(100, "呵呵哈")
     }
######TeacherModule
    @Provides
        public Teacher provideTeacher(PersonInfo personInfo) {
            return new Teacher(personInfo);
        }
######MainActivity
        
        @Inject
        Teacher teacher;
        @Inject
        Student student;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            DaggerStudentComponent.builder()
                    .build()
                    .inject(this);
        }
    
        public void tstDagger(View view) {
            Toast.makeText(this, student.toString() + "\t" + teacher.toString() + "\t" + man.toString(), Toast.LENGTH_LONG).show();
        }
这样我们就能得到其他Module提供的实例，功能类似于把所有Provides集中了起来。
####dependencies
为了演示dependencies的用法,我写了一个独立的实体类Man,还有base包里面的BaseApplicationModule，BaseComponent，MyApplication。
这稍微接近一般Dagger在项目里运用的样子了。
假如我要通过注入拿到Man的实例,而我又不直接拿，而是通过别的component注入，那该怎么办呢?,dagger提拱了一个组建依赖,dependencies.接着
贴代码.
######Man(这里可以换成其他，比如Retrofit的Api，常用的实体类等等)
     
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
######BaseApplicationModule提供了Man,和context。
     
     @Module
     public class BaseApplicationModule {
     
         private MyApplication myApplication;
     
         public BaseApplicationModule(MyApplication myApplication) {
             this.myApplication = myApplication;
         }
     
         @Singleton
         @Provides
         public Context getContext() {
             return myApplication;
         }
     
         @Provides
         public Man providesPersoninfo() {
             return new Man("huangsss");
         }
     
     
     }
######BaseComponent
    @Component(modules = BaseApplicationModule.class)
    public interface BaseComponent {
        void inject(MyApplication myApplication);
        //这里很关键,如果不提供，则后面编译不会被通过
        Man getMan();
    }
######MyApplication这没啥说的，就是干初始化的活
    
    BaseComponent baseComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        baseComponent = DaggerBaseComponent.builder()
                .baseApplicationModule(new BaseApplicationModule(this))
                .build();
        baseComponent.inject(this);
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }
写到这里，我们怎么得到Man的实例呢，其实很简单，只要你在写Component的时候加一个依赖就好了，如图
![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step6.png?raw=true)
然后我们在Component注入的容器（MainActivity）里这样写:
![](https://github.com/mar-sir/daggerExample/blob/master/DaggerExample/daggerfirst/imgs/step7.png?raw=true)
至此，Dagger2的简单使用已介绍完毕，希望对你有所帮助。
####代码
[代码地址传送](https://github.com/mar-sir/daggerExample)