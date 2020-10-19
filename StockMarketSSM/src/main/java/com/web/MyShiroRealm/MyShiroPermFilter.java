package com.web.MyShiroRealm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class MyShiroPermFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed
            (ServletRequest req, ServletResponse resp, Object mappedValue)

            throws Exception {
        Subject subject = getSubject(req, resp);
        String[] permsArray = (String[]) mappedValue;

        if (permsArray == null || permsArray.length == 0) { //没有权限限制
            return true;
        }
        for (int i = 0; i < permsArray.length; i++) {
            //如果是角色，就是subject.hasRole()
            //若当前用户是permsArray中的任何一个，则有权限访问
            if (subject.isPermitted(permsArray[i])) {
                return true;
            }
        }
        return false;
    }
}
