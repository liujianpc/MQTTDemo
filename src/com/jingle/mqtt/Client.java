package com.jingle.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Client {

	public static final String HOST = "tcp://127.0.0.1:1883";
	public static final String CLIENT = "client_one";
	public static final String USR = "jingle";
	public static final String PASSWORD = "jingle";
	private MqttClient client = null;
	private MqttConnectOptions conncetOpt;
	public Client() throws MqttException {

		startConnect();

	}

	public void startConnect() throws MqttException {
		client = new MqttClient(HOST, CLIENT, new MemoryPersistence());
		client.setCallback(new ClientCallback());
		conncetOpt = new MqttConnectOptions();
		conncetOpt.setAutomaticReconnect(true);
		conncetOpt.setCleanSession(false);
		conncetOpt.setConnectionTimeout(20);
		conncetOpt.setKeepAliveInterval(60);
		conncetOpt.setPassword(PASSWORD.toCharArray());
		conncetOpt.setUserName(USR);
		client.connect();
		client.subscribe("sport/football", 1);//subscribeµƒQos…Ë÷√Œ™1
	}

	public static void main(String[] args) throws MqttException {
		// TODO Auto-generated method stub
		Client client = new Client();

	}

}
