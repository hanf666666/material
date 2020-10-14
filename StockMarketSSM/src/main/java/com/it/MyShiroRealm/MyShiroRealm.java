package com.it.MyShiroRealm;

import com.it.services.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    private ShiroService shiroService;

    public void setShiroService(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据自己的需求编写获取授权信息，这里简化代码获取了用户对应的所有权限
        String username = (String) principalCollection.fromRealm(getName()).iterator().next();
        if (username != null) {
            List<String> perms = shiroService.getPermissionByUserName(username);
            if (perms != null && !perms.isEmpty()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for (String each : perms) {
                    //将权限资源添加到用户信息中
                    info.addStringPermission(each);
                }
                return info;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo

            (AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名，调用currentUser.login的时候执行
        String username = token.getUsername();
        if (username != null && !"".equals(username)) {
            //查询密码
            String password = shiroService.getPasswordByUserName(username);
            if (password != null) {
                return new SimpleAuthenticationInfo(username, password, getName());
            }
        }
        return null;
    }
}
