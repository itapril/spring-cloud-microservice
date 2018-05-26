package com.itapril.cloud.config;


/**
 * Created by itapril on 2018/5/22 23:02.
 */
import com.itapril.cloud.security.JwtAuthenticationEntryPoint;
import com.itapril.cloud.security.JwtAuthenticationProviter;
import com.itapril.cloud.security.JwtAuthenticationTokenFilter;
import com.itapril.cloud.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/*
@link: https://www.cnblogs.com/duanxz/p/7493276.html
@Configuration注解的配置类有如下要求：

@Configuration不可以是final类型；
@Configuration不可以是匿名类；
嵌套的configuration必须是静态类。

@Configuration加载spring
1.1、@Configuration配置spring并启动spring容器
1.2、@Configuration启动容器+@Bean注册Bean
1.3、@Configuration启动容器+@Component注册Bean

*/

@EnableGlobalMethodSecurity(prePostEnabled = true) // prePostEnabled: 基于表达式的注解
@EnableWebSecurity  //  启用web安全
@Configuration
//@ComponentScan({"com.itapril.cloud.security"}) // 指定 @Autowired 具体类的所在包，要不然在idea中会显示红色下划线，虽然不影响程序的运行
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtAuthenticationProviter authenticationProviter;

    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(this.userDetailsService);
//    }

    @Bean
    public AuthenticationManager authenticationManager(){
        //用来生成只读 的单一元素的List 生成的list不可进行add remove
        return new ProviderManager(Collections.singletonList(authenticationProviter));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter(){
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("**//*rest*//**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
}
