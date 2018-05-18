package com.nullthinker.metaldummy.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAsync
public class AppConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(7);
		executor.setMaxPoolSize(42);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("MyExecutor-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new MyAsyncUncaughtExceptionHandler();
	}



	private class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler{

		@Override
		public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
			System.out.println("Exception message - " + throwable.getMessage());
			System.out.println("Method name - " + method.getName());
			for (Object param : obj) {
				System.out.println("Parameter value - " + param);
			}
		}


	}


}
