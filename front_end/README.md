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
    npm run serve:dev
    ```  
    - 连接作者服务器上运行的后端程序：  
    ```bash
    npm run serve:pro
    ```  