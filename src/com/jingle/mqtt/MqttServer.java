package com.jingle.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class MqttServer {
	public static final String HOST = "tcp://127.0.0.1:1883";
	public static final String CLIENT = "server_one";
	public static final String TOPIC = "sport/football";

	public static final String USR = "jingle";
	public static final String PASSWORD = "jingle";
	private MqttClient client = null;
	private MqttConnectOptions conncetOpt;
	private MqttMessage message;

	public MqttServer() {
		startConnect();
	}

	private void startConnect() {

		try {
			String string = "from server:messi is the best football player in the world!";
			message = new MqttMessage(string.getBytes());
			message.setQos(2);//publish设置为2，防止多次发送信息
			client = new MqttClient(HOST, CLIENT, new MqttDefaultFilePersistence());
			client.setCallback(new ClientCallback());
			MqttTopic topic = client.getTopic(TOPIC);
			conncetOpt = new MqttConnectOptions();
			conncetOpt.setAutomaticReconnect(true);
			conncetOpt.setCleanSession(false);
			conncetOpt.setConnectionTimeout(20);
			conncetOpt.setKeepAliveInterval(60);
			conncetOpt.setPassword(PASSWORD.toCharArray());
			conncetOpt.setUserName(USR);
			client.connect(conncetOpt);

			MqttDeliveryToken token = topic.publish(message);
			token.waitForCompletion();
			System.out.println(token.toString());

		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MqttServer server = new MqttServer();
	}

}
