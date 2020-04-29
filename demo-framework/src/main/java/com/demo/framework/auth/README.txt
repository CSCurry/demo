======================包access
客户端授权访问用户，新开通之后，把clientId、clientSecret、publicKey给客户端

======================包crypt
通过注解方式，动态控制接口请求参数和返回参数是否需要加密解密处理

======================包client
给客户端的工具类，以及demo


注意：token验证是数据加密解密的前置条件


======================客户端主要需要以下依赖：
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.56</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpcore</artifactId>
</dependency>
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
</dependency>