
### 项目整体的clone和pull
如果你想要获取项目的全部代码并将其复制到你的本地环境，可以使用以下命令克隆项目：

```bash
git clone https://github.com/gaorch85/Project.git
```

如果你已经克隆了项目，并且想要获取远程仓库的最新更改，可以在项目目录中执行以下命令：
```bash
git pull
```

### 运行前端项目
前端项目是front_end文件夹

开发、执行该项目依赖于node.js ，在官网安装node.js即可  

验证node.js安装成功，在命令行执行：
```bash
npm --version
```
如果出现版本信息，即安装成功

项目基于Vue CLI开发，命令行执行以下命令，安装Vue CLI
```bash
npm install -g @vue/cli
```
验证Vue CLI安装成功，在命令行执行
```bash
vue --version
```
如果出现版本信息，即安装成功

打开/front_end文件夹目录，打开命令行，首先执行以下命令安装模组
```bash
npm install
```
安装成功之后，命令行执行以下命令，即可成功运行  

不启动后端程序，全部使用Mock模拟数据：
```bash
npm run serve:mock
```
先启动后端程序，进行数据库-后端-前端真实运行：
```bash
npm run serve:production
```  

### 运行后端项目
后端项目是 back_end 文件夹  

后端项目技术栈为：springboot + mybatis plus + mysql  

本人IDE使用 IntelliJ IDEA 

项目构建工具使用 maven， 官网安装即可(本人版本为3.9.6)，注意配置环境变量和maven仓库（网络有教程或直接GPT）  

安装完成后，命令行执行
```bash
mvn --version
```
如果出现版本信息，即安装成功，且环境变量同样配置完成  

在IDEA中使用配置自己安装的maven，网络有教程，或者GPT  

使用IDEA打开/back_end项目，修改数据库信息，即可执行了，修改教程如下：  

创建\back_end\src\main\resources\application.properties文件，配置相关信息即可（springboot有该文件，但该文件内有敏感信息，本人已将该文件放在.gitignore中）
```bash
spring.application.name = demo02
spring.devtools.restart.enabled = true
spring.devtools.restart.additional-paths = src/main/java
spring.datasource.exitype = com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://[host]:[port]/[database]?useSSL=true
spring.datasource.username = [username]
spring.datasource.password = [password]
mybatis-plus.configuration.log-impl = org.apache.ibatis.logging.stdout.StdOutImpl
server.port = 8090
```
将上面的信息中，所有方括号内容，改为自己的即可，例如[host]改为localhost  



现在，后端连接到数据库也没有问题了  

在你的数据库中，创建三个表，即可成功跑通整个项目，需要创建的用户表为为：  
```bash
CREATE TABLE `user` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) UNIQUE NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `salt` VARCHAR(255) NOT NULL,
  `phone_name` VARCHAR(20),
  `email` VARCHAR(50),
  `is_vip` BOOLEAN DEFAULT FALSE
);
```
其余两个表类似上面，必须定义id，其他字段按照表的内容自行添加即可





