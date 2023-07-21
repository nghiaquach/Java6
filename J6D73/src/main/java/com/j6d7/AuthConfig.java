package com.j6d7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.j6d7.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;
	
	/*--Quản lý người dữ liệu người sử dụng--*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	/*--Phân quyền sử dụng và hình thức đăng nhập--*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF, CORS
		http.csrf().disable().cors().disable();
		
		// Phân quyền sử dụng
		http.authorizeRequests()
			.antMatchers("/home/admins").hasRole("ADMIN")
			.antMatchers("/home/users").hasAnyRole("ADMIN", "USER")
			.antMatchers("/home/authenticated").authenticated()
			.anyRequest().permitAll(); // anonymous
		
		// Điều khiển lỗi truy cập không đúng vai trò
		http.exceptionHandling()
			.accessDeniedPage("/auth/access/denied"); // [/error]
		
		// Giao diện đăng nhập
		http.formLogin()
			.loginPage("/auth/login/form")
			.loginProcessingUrl("/auth/login") // [/login]
			.defaultSuccessUrl("/auth/login/success", false)
			.failureUrl("/auth/login/error")
			.usernameParameter("username") // [username]
			.passwordParameter("password"); // [password]
		http.rememberMe()
			.rememberMeParameter("remember"); // [remember-me]
		
		// Đăng xuất
		http.logout()
			.logoutUrl("/auth/logoff") // [/logout]
			.logoutSuccessUrl("/auth/logoff/success");	

		// OAuth2 - Đăng nhập từ mạng xã hội
		http.oauth2Login()
			.loginPage("/auth/login/form")
			.defaultSuccessUrl("/oauth2/login/success", true)
			.failureUrl("/auth/login/error")
			.authorizationEndpoint()
				.baseUri("/oauth2/authorization");
	}
	
	/*--Mã hóa mật khẩu--*/
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*--Cho phép request đến REST API từ browser--*/
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}