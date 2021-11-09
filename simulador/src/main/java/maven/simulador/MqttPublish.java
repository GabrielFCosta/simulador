package maven.simulador;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublish {
	
	String topic;
	String content;
	int qos;
	String broker; 
	String clientId;
	MemoryPersistence persistence;
	
	public MqttPublish(String content) {
		super();
		this.topic = "MATD02_GasMonitor1";
		this.setContent(content);
		this.qos = 2;
		this.broker = "tcp://broker.hivemq.com:1883";
		this.clientId = "MATD02_Simulador";
		this.persistence = new MemoryPersistence();
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void publish(){
		try {
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        sampleClient.connect(connOpts);
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        sampleClient.publish(topic, message);
	        sampleClient.disconnect();
	    } catch(MqttException e) {
	        System.out.println("Exception "+e);
	    }
	}
	

}
