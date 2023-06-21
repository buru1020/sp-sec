package com.sp.fc.web.config;

import com.sp.fc.web.student.StudentManager;
import com.sp.fc.web.teacher.TeacherManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Order(1)
@Configuration
public class MobileSecurityConfig {

    CsrfFilter filter;
    private StudentManager studentManager;
    private TeacherManager teacherManager;

    public MobileSecurityConfig(StudentManager studentManager, TeacherManager teacherManager) {
        this.studentManager = studentManager;
        this.teacherManager = teacherManager;
    }

    //FIXME 필터에서 403을 떨군다 이유룰 못참음 고칠 필요가 있다.
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests(reqs -> reqs.anyRequest().authenticated() )
                .httpBasic()
                .and()
                .build();
    }



}
