package com.zhouyu.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class UserService implements InitializingBean{

	@Autowired
	private OrderService orderService;

	/**
	 * 需求：在userService中有一个管理员对象，如何在获取userService的时候就可以从数据库中查询到管理员账号并将我们的账号密码
	 * 赋值给我们的admin对象，那么在获取userService对象的时候就可以获取到带有属性值的admin对象，而不是注入单纯的一个admin空对象。
	 * @Autowired
	 * private User admin;  //new User();
	 */

	private User admin;

	//方案一: 自己手动实现
	 public void a(){
	 	 //程序员自己写的方法来执行并从数据库中查询数据然后赋值给我们的管理员账户
		 //mysql ---> 管理员的信息---> user对象---> admin
	 }

	 //方案二: spring生命周期可以在初始化之前来解决
	//在创建userService对象的过程中自动调用方法
	//思考问题:spring怎么知道在众多方法中该调用哪个方法呢？
    //通过添加标识注解：@PostConstruct,那么spring就可以知道在初始化之前可以调用这个方法
	@PostConstruct
	public void b(){
	 	//超级管理员用户账号赋能success！
	}

	// 方案三:加注解的作用就是让我们的方法变得更加特殊，其实也可以使用实现接口的方式来实现接口规定的方法。
	// 实现InitializingBean接口并重写afterPropertiesSet方法，很明显这个方法很特殊。也就是spring会
	// 怎么判断UserService类中是否有此方法并执行呢？
	// 同样是使用反射来找到具体的方法。
	// ctrl n 查找 doCreateBean方法的实现逻辑

	@Override
	public void afterPropertiesSet() throws Exception {
		//mysql ---> 管理员的信息---> user对象---> admin
	}

	public void test(){
		System.out.println(orderService);
	}



}
