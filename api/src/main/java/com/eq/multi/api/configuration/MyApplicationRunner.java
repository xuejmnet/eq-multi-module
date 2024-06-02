package com.eq.multi.api.configuration;

import com.easy.query.api.proxy.client.EasyEntityQuery;
import com.eq.multi.domain.RoleMenu;
import com.eq.multi.domain.SysMenu;
import com.eq.multi.domain.SysRole;
import com.eq.multi.domain.SysUser;
import com.eq.multi.domain.SysUserAddress;
import com.eq.multi.domain.UserRole;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * create time 2024/6/2 09:50
 * 文件说明
 *
 * @author xuejiaming
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Resource(name="h2eq")
    private  EasyEntityQuery easyEntityQuery;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean any = easyEntityQuery.queryable(SysUser.class).any();
        if (any) {
            return;
        }
        ArrayList<SysUser> sysUsers = new ArrayList<>();
        ArrayList<SysUserAddress> sysUserAddresses = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setId(String.valueOf(i));
            sysUser.setName("用户"+i);
            sysUser.setCreateTime(LocalDateTime.now().plusDays(i));
            sysUsers.add(sysUser);
            SysUserAddress sysUserAddress = new SysUserAddress();
            sysUserAddress.setId("ua"+i);
            sysUserAddress.setUserId(sysUser.getId());
            sysUserAddress.setProvince("Province"+i);
            sysUserAddress.setCity("City"+i);
            sysUserAddress.setArea("Area"+i);
            sysUserAddress.setAddr("Addr"+i);
            sysUserAddresses.add(sysUserAddress);
        }
        ArrayList<SysRole> sysRoles = new ArrayList<>();
        for (int i = 1; i < 5; i++) {

            SysRole sysRole = new SysRole();
            sysRole.setId("role"+i);
            sysRole.setName("角色"+i);
            sysRole.setCreateTime(LocalDateTime.now().plusDays(i));
            sysRoles.add(sysRole);
        }
        ArrayList<UserRole> userRoles = new ArrayList<>();
        {
            UserRole userRole = new UserRole();
            userRole.setId("ur1");
            userRole.setUserId("1");
            userRole.setRoleId("role1");
            userRoles.add(userRole);
        }
        {
            UserRole userRole = new UserRole();
            userRole.setId("ur2");
            userRole.setUserId("1");
            userRole.setRoleId("role2");
            userRoles.add(userRole);
        }
        {
            UserRole userRole = new UserRole();
            userRole.setId("ur3");
            userRole.setUserId("2");
            userRole.setRoleId("role3");
            userRoles.add(userRole);
        }
        {
            UserRole userRole = new UserRole();
            userRole.setId("ur4");
            userRole.setUserId("3");
            userRole.setRoleId("role4");
            userRoles.add(userRole);
        }
        ArrayList<SysMenu> sysMenus = new ArrayList<>();
        for (int i = 1; i < 10; i++) {

            SysMenu sysMenu = new SysMenu();
            sysMenu.setId("m" + i);
            sysMenu.setName("菜单" + i);
            sysMenu.setRoute("/菜单" + i);
            sysMenu.setIcon("/icon_menu" + i);
            sysMenus.add(sysMenu);
        }
        ArrayList<RoleMenu> roleMenus = new ArrayList<>();
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm1");
            roleMenu.setRoleId("role1");
            roleMenu.setMenuId("m1");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm2");
            roleMenu.setRoleId("role1");
            roleMenu.setMenuId("m2");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm3");
            roleMenu.setRoleId("role1");
            roleMenu.setMenuId("m3");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm4");
            roleMenu.setRoleId("role2");
            roleMenu.setMenuId("m4");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm5");
            roleMenu.setRoleId("role3");
            roleMenu.setMenuId("m5");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm6");
            roleMenu.setRoleId("role4");
            roleMenu.setMenuId("m6");
            roleMenus.add(roleMenu);
        }
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId("rm7");
            roleMenu.setRoleId("role4");
            roleMenu.setMenuId("m7");
            roleMenus.add(roleMenu);
        }

        easyEntityQuery.insertable(sysUsers).executeRows();
        easyEntityQuery.insertable(sysRoles).executeRows();
        easyEntityQuery.insertable(userRoles).executeRows();
        easyEntityQuery.insertable(sysMenus).executeRows();
        easyEntityQuery.insertable(roleMenus).executeRows();
        easyEntityQuery.insertable(sysUserAddresses).executeRows();
    }
}
