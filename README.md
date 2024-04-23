
### 项目整体的clone和pull
如果你想要获取项目的全部代码并将其复制到你的本地环境，可以使用以下命令克隆项目：

```bash
git clone https://github.com/your_username/your_project.git
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
```bash
npm run serve
```
