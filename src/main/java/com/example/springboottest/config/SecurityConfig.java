package com.example.springboottest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
// extends WebSecurityConfigurerAdapter 已经过时了
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存数据存储验证
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())//5.0后密码bcrypt加密处理
                .withUser("root")
                .password(new BCryptPasswordEncoder().encode("root"))
                .authorities("ROLE_USER")
                .and()
                .withUser("root1")
                .password(new BCryptPasswordEncoder().encode("root1"))
                .authorities("ROLE_USER");
//        super.configure(auth);
        //jdbc用户存储
        auth.jdbcAuthentication()
            .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users "+
                                "where username=?")
                .authoritiesByUsernameQuery(
                        "select username, password, enabled from UserAuthorities "+
                                "where username=?")
                .passwordEncoder(new SCryptPasswordEncoder());
        //LDAP用户存储
        //userSearchBase() 方法提供了查找用户的基本查询。同样，groupSearchBase() 方法指定查找组的基本查询。
        //这个示例不是从根目录进行搜索，而是指定要搜索用户所在的组织单元是 people，组应该搜索组织单元所在的 group。
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare()//此语句为希望通过密码比较进行身份验证;或者将用户通过LDAP服务器直接进行验证
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("passcode")
                .and()
                .contextSource()
                .root("dc=tacocloud,dc=com");//嵌入式服务器
//                .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");//LDAP服务器
        }

    }
}
