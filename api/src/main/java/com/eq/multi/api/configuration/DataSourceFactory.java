package com.eq.multi.api.configuration;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * create time 2024/6/1 22:38
 * 文件说明
 *
 * @author xuejiaming
 */
public class DataSourceFactory {
    public static DataSource getDataSource(String dbName, String sqlPath) {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setName(dbName)
                .setType(EmbeddedDatabaseType.H2)
                .addScript(sqlPath)
                .build();
        return dataSource;
    }
}
