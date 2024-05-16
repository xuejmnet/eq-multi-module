package com.eq.multi.domain;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.eq.multi.domain.proxy.TopicProxy;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * create time 2024/5/16 22:36
 * 文件说明
 *
 * @author xuejiaming
 */
@Data
@Table("t_topic")
@EntityProxy
public class Topic implements ProxyEntityAvailable<Topic , TopicProxy> {
    @Column(primaryKey = true)
    private String id;
    private Integer stars;
    private String title;
    private LocalDateTime createTime;

    @Override
    public Class<TopicProxy> proxyTableClass() {
        return TopicProxy.class;
    }
}
