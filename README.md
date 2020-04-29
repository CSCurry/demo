###基本框架
* **SpringBoot**
* **Mybatis**

###功能模块
**mybatis generator**
```
根据数据库表生成代码：entity、dao、mapper
```
* **Redis**
```
缓存：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。
```
* **Redis Lock**
```
Redis分布式锁
用于接口防重复提交
```
* **logback**
```
日志
```
* **validation**
```
接口参数校验
```
* **Swagger2**
```
在线接口文档
```
* **MD5**
```
授权用户每次请求接口前获取token进行身份验证
```
* **AES & RSA**
```
接口请求数据加密解密、接口响应数据加密解密
加密方式：
    1.客户端每次请求接口时随机生成aesKey，用aesKey加密data，用RSA公钥加密aesKey
    2.将加密后的data和加密后aesKey一起传到服务端
    3.服务端先用RSA私钥解密aesKey，再用aesKey解密data，即可处理业务
    4.服务端返回数据前，用aesKey加密返回数据
    5.客户端用aesKey解密数据，得到响应数据
使用方式：
    1.自定义注解@Decrypt和@Encrypt加在controller方法上即可
```
* **SpringBoot Mail**
```
发送邮件（普通文本、HTML、附件）
```
* **SpringBoot Scheduling**
```
定时任务
```
* **金蝶**
```
企业税号关联查询
```
* **@SystemLog**
```
SystemLog注解的接口，会自动打印请求日志，通过AOP实现的
```
* pagehelper
```
分页
```