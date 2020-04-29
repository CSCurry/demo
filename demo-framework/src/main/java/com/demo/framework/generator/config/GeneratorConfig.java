package com.demo.framework.generator.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Component
public class GeneratorConfig {

    //DB配置
    private final String driverClass = "com.mysql.cj.jdbc.Driver";
    private final String connectionURL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private final String username = "root";
    private final String password = "123456";

    //maven module名称
    private final String moduleName = "demo-framework";
    //存放位置
    private final String targetProject = moduleName + "/src/main/java";

    //entity存放包名
    private String modelTargetPackage = "com.demo.framework.generator.code.domain";
    //dao存放包名
    private String daoTargetPackage = "com.demo.framework.generator.code.dao";
    //mapper存放包名
    private String sqlTargetPackage = "com.demo.framework.generator.code.mapper";

    //自定义注释模板
    private final String commentGenerator = "com.demo.framework.generator.config.MySQLCommentGenerator";

    public static void main(String[] args) {
        //1.上面参数全改为自己的
        //2.选择批量或全部生成，批量生成需自己指定表
        //3.Run main()
        //4.项目右键，选择reload from disk，刷新项目，对应包下就有了

        //generatorAll();
        generatorBatch();
    }

    /**
     * 批量生成
     */
    private static void generatorBatch() {
        List<TableEntity> list = new ArrayList<>();

        TableEntity table1 = new TableEntity("demo_user");
        list.add(table1);

        TableEntity table2 = new TableEntity("chartered_evaluate");
        list.add(table2);

        //......

        new GeneratorConfig().generator(list);
    }

    /**
     * 全部生成
     */
    private static void generatorAll() {
        new GeneratorConfig().generator(new ArrayList<>());
    }

    private void generator(List<TableEntity> tableList) {
        Context context = new Context(ModelType.CONDITIONAL);
        //MyBatis3或者MyBatis3Simple，建议用MyBatis3，因为MyBatis3Simple生成的SQL太简易
        context.setTargetRuntime("MyBatis3");
        context.setId("mysql");
        //自动识别数据库关键字，用``包起来
        context.addProperty("autoDelimitKeywords", "true");
        context.addProperty("beginningDelimiter", "`");
        context.addProperty("endingDelimiter", "`");
        //所有entity实现Serializable接口
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(pluginConfiguration);

        //使用自定义注释模板
        CommentGeneratorConfiguration comment = new CommentGeneratorConfiguration();
        comment.setConfigurationType(commentGenerator);

        //*************** DB ***************//
        JDBCConnectionConfiguration jdbc = new JDBCConnectionConfiguration();
        jdbc.setDriverClass(driverClass);
        jdbc.setConnectionURL(connectionURL);
        jdbc.setUserId(username);
        jdbc.setPassword(password);
        //避免生成其他数据库的表
        jdbc.addProperty("nullCatalogMeansCurrent", "true");
        //解决表注释获取不到的问题
        jdbc.addProperty("useInformationSchema", "true");

        //*************** entity ***************//
        JavaModelGeneratorConfiguration javaModel = new JavaModelGeneratorConfiguration();
        javaModel.setTargetPackage(modelTargetPackage);
        javaModel.setTargetProject(targetProject);
        javaModel.addProperty("enableSubPackages", "true");
        javaModel.addProperty("trimStrings", "true");

        //*************** dao ***************//
        JavaClientGeneratorConfiguration javaClient = new JavaClientGeneratorConfiguration();
        javaClient.setConfigurationType("XMLMAPPER");
        javaClient.setTargetPackage(daoTargetPackage);
        javaClient.setTargetProject(targetProject);
        javaClient.addProperty("enableSubPackages", "true");

        //*************** mapper ***************//
        SqlMapGeneratorConfiguration sqlMap = new SqlMapGeneratorConfiguration();
        sqlMap.setTargetPackage(sqlTargetPackage);
        sqlMap.setTargetProject(targetProject);
        sqlMap.addProperty("enableSubPackages", "true");

        JavaTypeResolverConfiguration javaType = new JavaTypeResolverConfiguration();
        javaType.addProperty("forceBigDecimals", "false");

        context.setCommentGeneratorConfiguration(comment);
        context.setJdbcConnectionConfiguration(jdbc);
        context.setJavaTypeResolverConfiguration(javaType);
        context.setJavaModelGeneratorConfiguration(javaModel);
        context.setSqlMapGeneratorConfiguration(sqlMap);
        context.setJavaClientGeneratorConfiguration(javaClient);

        if (tableList != null && !tableList.isEmpty()) {
            //生成指定表
            tableList.forEach(tableEntity -> {
                TableConfiguration table = new TableConfiguration(context);
                table.setTableName(tableEntity.getTableName());
                table.setDomainObjectName(tableEntity.getDomainObjectName());

                //避免生成一堆example的东西
                table.setCountByExampleStatementEnabled(false);
                table.setUpdateByExampleStatementEnabled(false);
                table.setDeleteByExampleStatementEnabled(false);
                table.setSelectByExampleStatementEnabled(false);

                context.addTableConfiguration(table);
            });
        } else {
            //生成所有表
            TableConfiguration table = new TableConfiguration(context);
            table.setTableName("%");

            //避免生成一堆example的东西
            table.setCountByExampleStatementEnabled(false);
            table.setUpdateByExampleStatementEnabled(false);
            table.setDeleteByExampleStatementEnabled(false);
            table.setSelectByExampleStatementEnabled(false);

            context.addTableConfiguration(table);
        }

        Configuration config = new Configuration();
        config.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, new ArrayList<>());
            myBatisGenerator.generate(null);
        } catch (InvalidConfigurationException | InterruptedException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}