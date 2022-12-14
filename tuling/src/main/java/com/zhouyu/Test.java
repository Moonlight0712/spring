package com.zhouyu;

import com.zhouyu.service.User;
import com.zhouyu.service.UserService;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.test();


		/**
		 * 测试UserService中@PostCustruct修饰的初始化前的方法
		 */
		UserService userService1 = new UserService();
		for (Field field : userService.getClass().getDeclaredFields()) {
			//假设该类的属性被我们的Autowired修饰了那么就执行逻辑
			if (field.isAnnotationPresent(Autowired.class)) {
				field.set(userService1, ??);
			}
		}

		for (Method method : userService1.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(PostConstruct.class)) {
				try {
					//反射调用无参数的方法
					method.invoke(userService1, null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}


//		UserService userService1 = new UserService();
//
//		for (Field field : userService1.getClass().getDeclaredFields()) {
//			if (field.isAnnotationPresent(Autowired.class)) {
//				field.set(userService1, ??);
//			}
//		}
//
//
//		for (Method method : userService1.getClass().getDeclaredMethods()) {
//			if (method.isAnnotationPresent(PostConstruct.class)) {
//				method.invoke(userService1, null);
//			}
//		}
//
//		if (userService1 instanceof InitializingBean) {
//			try {
//				((InitializingBean)userService1).afterPropertiesSet();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		ProxyFactory proxyFactory = new ProxyFactory();
//		proxyFactory.setTarget(userService1);
//		proxyFactory.addAdvice(new MethodInterceptor() {
//			@Nullable
//			@Override
//			public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
//				System.out.println("切面逻辑 before...");
//				Object result = invocation.proceed();
////				Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
//				System.out.println("切面逻辑 after...");
//				return result;
//			}
//		});
//		UserService userService2  = (UserService) proxyFactory.getProxy();
//		userService2.test();

	}
}







