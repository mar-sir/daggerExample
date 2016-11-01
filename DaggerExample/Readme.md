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