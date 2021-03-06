package com.rocketmq;

import java.util.Date;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 同步发送
 * 
 * @author hui.zhao.cfs
 *
 */
public class SyncProducer {

	public static void main(String[] args) throws Exception {

		System.setProperty("rocketmq.namesrv.domain", "localhost");
		// 构造Producer
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName2");
		// producer.setNamesrvAddr("192.168.237.128:9876");
		// 初始化Producer，整个应用生命周期内，只需要初始化1次
		producer.start();

		for (int i = 0; i < 5; i++) {
			Message msg = new Message("TopicTest6", "TagA",
					("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//			msg.setDelayTimeLevel(3);
			SendResult sendResult = producer.send(msg);
			System.out.println("Time [" + new Date().toString() + "]," + sendResult);
		}
		producer.shutdown();
	}
}
