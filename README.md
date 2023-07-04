# 快速搭建单体服务

## 环境搭建

### Maven

下载一个maven安装包，解压：

![1682175358849](E:\JAVA_Files\quick-monomer\assets\1682175358849.png)



找到setting.xml

![1682175372983](E:\JAVA_Files\quick-monomer\assets\1682175372983.png)

修改仓库路径

![1682175391233](E:\JAVA_Files\quick-monomer\assets\1682175391233.png)

添加阿里云的镜像仓库

![1682175450188](E:\JAVA_Files\quick-monomer\assets\1682175450188.png)

添加如下

```xml
<mirror>
   <id>alimaven</id>
   <mirrorOf>central</mirrorOf>
   <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
</mirror>

```



### 安装Mybatisx

在idea上安装MybatisX插件

![1682175597033](E:\JAVA_Files\quick-monomer\assets\1682175597033.png)

### 克隆项目

根据我给的模板，创建一个自己的项目。

## 快速开发

### 1、使用mybatisx生成代码

使用idea连接mysql数据库：

![1682171495759](E:\JAVA_Files\quick-monomer\assets\1682171495759.png)

![1682171599948](E:\JAVA_Files\quick-monomer\assets\1682171599948.png)

可以按住Shift多选想要生成的表：

![1682171633718](E:\JAVA_Files\quick-monomer\assets\1682171633718.png)

右键点击，选择generator：

![1682171659007](E:\JAVA_Files\quick-monomer\assets\1682171659007.png)

填写生成路径：

![1682171787457](E:\JAVA_Files\quick-monomer\assets\1682171787457.png)

选择实体类的生成规则：

![1682171863883](E:\JAVA_Files\quick-monomer\assets\1682171863883.png)

到这里会自动生成entity、mapper、service、mapperxml等类信息：

随后找到entity类，这里建议手动给每一个类都加上如下的几个注解：

![1682171960209](E:\JAVA_Files\quick-monomer\assets\1682171960209.png)

还有添加逻辑删除字段：

![1682171996444](E:\JAVA_Files\quick-monomer\assets\1682171996444.png)



### 2、Controller层



#### 1)、Controller层代码参考

![1682172533832](E:\JAVA_Files\quick-monomer\assets\1682172533832.png)

Controller层的代码，可以先了解一下restful Api，实际上就是GET、POST、PUT、DELETE请求方法。

一般基本的查询使用GET或者参数多的时候使用POST

添加的时候使用POST

更新的使用PUT

删除的时候使用DELETE

#### 2)、只用dto

java开发中关于实体类有很多种，什么VO，POJO等等，我的这里全部使用Dto

查询接口参数一般是XXXQueryDto，添加就是XXXAddDto，更新就是UpdateDto。

返回结果统一都是ResultDto。

![1682172168233](E:\JAVA_Files\quick-monomer\assets\1682172168233.png)



#### 3)、参数校验

基本上都是使用javax.validation中提供的api

下面是开启校验的写法，具体的规则比如@NotNull，@Length等等，自己去了解一下。

![1682172508494](E:\JAVA_Files\quick-monomer\assets\1682172508494.png)

除了javax.validation提供的的注解意外，还可以自定义注解，我提供了一个自定义参数校验的注解。

![1688472452056](E:\JAVA_Files\quick-monomer\assets\1688472452056.png)

用法：

1. 首先在一个类(如Class1)中定义一个静态的校验方法（如 method1）
2. 然后，如下图：![1688472631137](E:\JAVA_Files\quick-monomer\assets\1688472631137.png)
3. 搭配hutool工具包中的Validator使用更好。

#### 4)、结果类包装

正常编写接口时，接口的返回值需要经过这个类进行包装再返回比较得当：

![1682172902525](E:\JAVA_Files\quick-monomer\assets\1682172902525.png)



有了一下这个类，在Controller层中不需要声明以Result<>包裹的类型，在接口返回的时候，会自行包装Result。

![1682172835929](E:\JAVA_Files\quick-monomer\assets\1682172835929.png)

如果希望接口返回值不包装Result，可以在方法上添加如下注解。

![1682172924474](E:\JAVA_Files\quick-monomer\assets\1682172924474.png)







### 3、Service层



#### 1)、基本的CRUD





#### 2)、分页





#### 3)、错误的情况处理







### 4、Mapper层







### 5、日志



![1682173307593](E:\JAVA_Files\quick-monomer\assets\1682173307593.png)



### 6、 缓存



![1682173391428](E:\JAVA_Files\quick-monomer\assets\1682173391428.png)







## 快速部署

### 搭建Docker仓库





### 使用docker-maven仓库





## 接口文档