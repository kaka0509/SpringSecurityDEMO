package com.imooc.security.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


/**
 * RBAC权限控制实现
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    // 路径匹配器
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetailsService) { //如果是由匿名过滤器处理的是String
            String username = ((UserDetails) principal).getUsername();
            // todo:根据用户名username查询数据库读取用户拥有的权限URL
            Set<String> urls = new HashSet<>();

            for (String url : urls) {
                if (antPathMatcher.match(url,request.getRequestURI()));
                hasPermission = true;
                break;
            }
        }
        // 匿名用户直接返回默认值: FALSE
        return hasPermission;
    }
}
