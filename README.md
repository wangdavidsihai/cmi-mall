# CMI mall


## 技术栈

> 1. Spring Boot
> 2. Vue
> 3. wechat micro program



## 功能

### 管理平台功能

* User 
* Mall
* Product
* Promotion
* System

## 快速启动
    
1. 数据库依次导入litemall-db/sql下的数据库文件
    * litemall_schema.sql
    * litemall_table.sql
    * litemall_data.sql
    * 

2. 启动后端服务

    打开命令行，输入以下命令
    
    ```bash
    cd cmi-mall
    mvn install
    mvn package
    cd ./cmi-all
    mvn spring-boot:run
    ```
    
3. 启动管理后台前端

    打开命令行，输入以下命令
    ```bash
    npm install -g cnpm --registry=https://registry.npm.taobao.org
    cd cmi-mall/cmi-admin
    cnpm install
    cnpm run dev
    ```
    浏览器打开，输入网址`http://localhost:9527`, 此时进入管理后台登录页面。
    