package com.eq.multi.api.controller;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.eq.multi.domain.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create time 2024/5/16 22:34
 * 文件说明
 *
 * @author xuejiaming
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TestController {
    private final EasyEntityQuery easyEntityQuery;
    @GetMapping("/query")
    public Object query() {
        List<Topic> list = easyEntityQuery.queryable(Topic.class)
                .where(t -> t.id().isNotBank())
                .toList();
        return list;
    }
}
