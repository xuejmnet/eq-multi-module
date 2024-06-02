package com.eq.multi.api.controller;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.easy.query.core.api.pagination.EasyPageResult;
import com.easy.query.core.proxy.core.draft.Draft2;
import com.easy.query.core.proxy.sql.Select;
import com.eq.multi.domain.RoleMenu;
import com.eq.multi.domain.SysMenu;
import com.eq.multi.domain.SysRole;
import com.eq.multi.domain.SysUser;
import com.eq.multi.domain.SysUserAddress;
import com.eq.multi.domain.Topic;
import com.eq.multi.domain.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * create time 2024/6/2 09:28
 * 文件说明
 *
 * @author xuejiaming
 */
@RestController
@RequestMapping("/h2test")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class H2TestController {
    @Resource(name="h2eq")
    private final EasyEntityQuery easyEntityQuery;

    @GetMapping("/case1")
    public Object case1() {
        List<SysUser> users = easyEntityQuery.queryable(SysUser.class)
                .where(s -> {
                    //隐式子查询会自动join用户表和地址表
                    s.or(()->{
                        s.address().city().eq("City1");
                        s.address().city().eq("City2");
                    });
                }).toList();
        return users;
    }
    @GetMapping("/case2")
    public Object case2() {
        List<Draft2<String, String>> userNameAndAddr = easyEntityQuery.queryable(SysUser.class)
                .where(s -> {
                    s.name().eq("用户2");
                }).select(s -> Select.DRAFT.of(
                        s.name(),
                        s.address().addr()//隐式join因为用户返回了地址标的地址信息
                )).toList();
        return userNameAndAddr;
    }
    @GetMapping("/casePage1")
    public Object casePage1() {
        EasyPageResult<SysUser> pageResult = easyEntityQuery.queryable(SysUser.class)
                .includes(s -> s.roles(), then -> {
                    then.includes(r -> r.menus());
                })
                .where(s -> {
                    //隐式子查询会自动join用户表和地址表
                    s.or(() -> {
                        s.address().city().eq("City1");
                        s.address().city().eq("City2");
                    });
                }).toPageResult(1, 3);
        return pageResult;
    }
}
