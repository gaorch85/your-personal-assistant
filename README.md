## 你的个人助手
这是一款前后端分离的web应用，在这里，您可以管理待办事项、阅读博客文章，并查看您的课表。我们的应用旨在帮助您更好地管理时间和任务，提高效率。通过集成多种功能，我们希望为您提供一个一站式的管理平台。

**主要功能：**
- `JWT`登录认证
- 待办事项
- 博客系统
- 课表管理

**技术支持：**
- 前端基于 `vue` 框架开发，部分使用 `element ui`
- 后端基于 `springboot` + `mybatis plus` + `mysql` 开发
- 前后端通信遵循 `RESTful` 风格

**在线预览：**
- [你的个人助手](http://1.92.148.127:80)

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
前端项目是 `/front_end` 文件夹，按照以下步骤，即可成功运行该项目

1. 安装 `node.js`  
    官网安装即可  
    验证是否安装成功，命令行执行：  
    ```bash
    npm --version
    ```
    如果出现版本信息，即安装成功
2. 安装 `Vue CLI`  
    命令行执行以下命令，全局安装 `Vue CLI`  
    ```bash
    npm install -g @vue/cli
    ```
    验证是否安装成功，命令行执行：  
    ```bash
    vue --version
    ```
    如果出现版本信息，即安装成功
3. 下载依赖 `module`  
   在 `/front_end` 目录下，命令行执行：  
   ```bash
   npm install
   ```
4. 运行项目，命令行执行（三者选其一即可）：  
    - 不启动后端程序，全部使用Mock模拟数据(大部分没有Mock，见 谅)：  
    ```bash
    npm run serve:mock
    ```
    - 先启动后端程序，进行数据库-后端-前端真实运行：  
    ```bash
    npm run serve:development
    ```  
    - 连接作者服务器上运行的后端程序：  
    ```bash
    npm run serve:production
    ```  

### 学习参考资料：
- [vue-admin-element](https://github.com/PanJiaChen/vue-admin-template)
- [vue-pure-admin](https://github.com/pure-admin/vue-pure-admin)