package org.dubboConsumer;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dubboApi.DemoService;
import com.dubboApi.bean.TestBean;

public class Consumer {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
		context.start();
		RpcContext.getContext().setAttachment("tag", "gray");
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理

		// System.out.println(demoService.syncSayHello("world"));
		System.out.println(demoService.asyncSayHello("world"));
		Future<String> futrue = RpcContext.getContext().getFuture();
		System.out.println(futrue.get());

		// System.out.println(demoService.sayHello(new TestBean("zhaohui", 99,
		// "nanjing")));

		// Map<String, String> map = (Map<String, String>)
		// context.getBean("redis");
		// map.put("haha", "vvv1");
		//
		// System.out.println(map.get("haha"));
	}
}
