package com.web.services;

import com.web.dao.ShiroDAO;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;

import java.util.List;

public class ShiroService {
    private ShiroDAO shiroDAO;

    public void setShiroDAO(ShiroDAO shiroDAO) {
        this.shiroDAO = shiroDAO;
    }

    /**
     * 登录
     */
    public void doLogin(String username, String password) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token =
                    new UsernamePasswordToken(username, password);
            token.setRememberMe(true);//是否记住用户
            try {
                currentUser.login(token);//执行登录
            } catch (UnknownAccountException uae) {
                throw new Exception("账户不存在");
            } catch (IncorrectCredentialsException ice) {
                throw new Exception("密码不正确");
            } catch (LockedAccountException lae) {
                throw new Exception("用户被锁定了 ");
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
                throw new Exception("未知错误");
            }
        }
    }

    /**
     * 根据用户名查询密码
     */
    public String getPasswordByUserName(String username) {
        return shiroDAO.getPasswordByUserName(username);
    }

    /**
     * 查询用户所有权限
     */
    public List<String> getPermissionByUserName(String username) {
        return shiroDAO.getPermissionByUserName(username);
    }
}
