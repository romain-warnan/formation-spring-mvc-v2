package fr.insee.springmvc;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private Collection<HandlerInterceptor> interceptors;

	@Autowired
	private Collection<HandlerMethodArgumentResolver> resolvers;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		this.interceptors.forEach(it -> registry.addInterceptor(it).addPathPatterns("/**").excludePathPatterns("/static/**"));
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		this.resolvers.forEach(it -> resolvers.add(it));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}
