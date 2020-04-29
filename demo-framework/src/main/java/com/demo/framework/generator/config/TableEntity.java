package com.demo.framework.generator.config;

public class TableEntity {

    //表名
    private String tableName;
    //Java实体名，默认：表aaa_bbb生成AaaBbb，支持自定义
    private String domainObjectName;

    public TableEntity() {
    }

    public TableEntity(String tableName) {
        this.tableName = tableName;
    }

    public TableEntity(String tableName, String domainObjectName) {
        this.tableName = tableName;
        this.domainObjectName = domainObjectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }
}