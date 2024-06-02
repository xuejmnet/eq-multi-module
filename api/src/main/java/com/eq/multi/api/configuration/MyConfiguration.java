package com.eq.multi.api.configuration;

import com.easy.query.api.proxy.client.DefaultEasyEntityQuery;
import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.core.api.client.EasyQueryClient;
import com.easy.query.core.basic.jdbc.conn.ConnectionManager;
import com.easy.query.core.bootstrapper.EasyQueryBootstrapper;
import com.easy.query.core.configuration.nameconversion.NameConversion;
import com.easy.query.core.configuration.nameconversion.impl.UnderlinedNameConversion;
import com.easy.query.core.datasource.DataSourceUnitFactory;
import com.easy.query.h2.config.H2DatabaseConfiguration;
import com.easy.query.oracle.config.OracleDatabaseConfiguration;
import com.easy.query.sql.starter.conn.SpringConnectionManager;
import com.easy.query.sql.starter.conn.SpringDataSourceUnitFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * create time 2024/6/1 22:37
 * 文件说明
 *
 * @author xuejiaming
 */
@Configuration
public class MyConfiguration {
    @Bean("h2DataSource")
    public DataSource h2DataSource(){
        return DataSourceFactory.getDataSource("ds", "h2init.sql");
    }
    @Bean("h2eqc")//使用的时候通过注入指定名称即可
    public EasyQueryClient easyQueryClient(@Qualifier("h2DataSource") DataSource dataSource){
        EasyQueryClient easyQueryClient = EasyQueryBootstrapper.defaultBuilderConfiguration()
                .setDefaultDataSource(dataSource)
                .replaceService(DataSourceUnitFactory.class, SpringDataSourceUnitFactory.class)
                .replaceService(NameConversion.class, UnderlinedNameConversion.class)
                .replaceService(ConnectionManager.class, SpringConnectionManager.class)
                .optionConfigure(builder -> {
                    builder.setPrintSql(true);
//                    builder.setDeleteThrowError(easyQueryProperties.getDeleteThrow());
//                    builder.setInsertStrategy(easyQueryProperties.getInsertStrategy());
//                    builder.setUpdateStrategy(easyQueryProperties.getUpdateStrategy());
//                    builder.setMaxShardingQueryLimit(easyQueryProperties.getMaxShardingQueryLimit());
//                    builder.setExecutorMaximumPoolSize(easyQueryProperties.getExecutorMaximumPoolSize());
//                    builder.setExecutorCorePoolSize(easyQueryProperties.getExecutorCorePoolSize());
//                    builder.setThrowIfRouteNotMatch(easyQueryProperties.isThrowIfRouteNotMatch());
//                    builder.setShardingExecuteTimeoutMillis(easyQueryProperties.getShardingExecuteTimeoutMillis());
//                    builder.setQueryLargeColumn(easyQueryProperties.isQueryLargeColumn());
//                    builder.setMaxShardingRouteCount(easyQueryProperties.getMaxShardingRouteCount());
//                    builder.setExecutorQueueSize(easyQueryProperties.getExecutorQueueSize());
//                    builder.setDefaultDataSourceName(easyQueryProperties.getDefaultDataSourceName());
//                    builder.setDefaultDataSourceMergePoolSize(easyQueryProperties.getDefaultDataSourceMergePoolSize());
//                    builder.setMultiConnWaitTimeoutMillis(easyQueryProperties.getMultiConnWaitTimeoutMillis());
//                    builder.setWarningBusy(easyQueryProperties.isWarningBusy());
//                    builder.setInsertBatchThreshold(easyQueryProperties.getInsertBatchThreshold());
//                    builder.setUpdateBatchThreshold(easyQueryProperties.getUpdateBatchThreshold());
//                    builder.setPrintSql(easyQueryProperties.isPrintSql());
//                    builder.setStartTimeJob(easyQueryProperties.isStartTimeJob());
//                    builder.setDefaultTrack(easyQueryProperties.isDefaultTrack());
//                    builder.setRelationGroupSize(easyQueryProperties.getRelationGroupSize());
//                    builder.setKeepNativeStyle(easyQueryProperties.isKeepNativeStyle());
//                    builder.setNoVersionError(easyQueryProperties.isNoVersionError());
//                    builder.setReverseOffsetThreshold(easyQueryProperties.getReverseOffsetThreshold());
                })
                .useDatabaseConfigure(new H2DatabaseConfiguration())
                .build();

        return easyQueryClient;
    }

    @Bean("h2eq")
    public EasyEntityQuery easyEntityQuery(@Qualifier("h2eqc") EasyQueryClient easyQueryClient){
        return new DefaultEasyEntityQuery(easyQueryClient);
    }
}
