package com.it.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ShiroDAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据用户名查询密码
     */
    public String getPasswordByUserName(String username) {
        String sql = "select PASSWORD from SHIRO_USER where USER_NAME = ?";
        String password = jdbcTemplate.queryForObject(sql, String.class, username);
        return password;
    }

    /**
     * 查询当前用户对应的权限
     */
    public List<String> getPermissionByUserName(String username) {
        String sql = "select P.PERM_NAME from SHIRO_ROLE_PERMISSION P inner join SHIRO_USER_ROLE R on P.ROLE_NAME=R.ROLE_NAME where R.USER_NAME = ?";
        List<String> perms = jdbcTemplate.queryForList(sql, String.class, username);
        return perms;
    }
}