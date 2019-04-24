package fr.insee.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Stopwatch;

@Component
public class TimerInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		var stopwatch = Stopwatch.createStarted();
		request.setAttribute("stopwatch", stopwatch);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		var stopwatch = (Stopwatch) request.getAttribute("stopwatch");
		logger.info(request.getRequestURI() + " - " + stopwatch);
	}

	
}
