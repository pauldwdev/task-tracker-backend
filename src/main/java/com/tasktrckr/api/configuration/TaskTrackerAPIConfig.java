package com.tasktrckr.api.configuration;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.tasktrckr.api.reqresp.logging.ReqRespLoggingDispatcherServlet;

@Configuration
public class TaskTrackerAPIConfig {

	@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration() {
		return new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet());
	}

	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		return new ReqRespLoggingDispatcherServlet();
	}
}
