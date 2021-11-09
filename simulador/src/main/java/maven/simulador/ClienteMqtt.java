package maven.simulador;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClienteMqtt implements MqttCallback {
	
	String topic;
	String broker; 
	String clientId;
	int qos;
	MqttClient client;
	MemoryPersistence persistence;
	
	public ClienteMqtt() {
		super();
		this.topic = "MATD02_GasMonitor1";
		this.broker = "tcp://broker.hivemq.com:1883";
		this.clientId = "GrupoTrabalho-Cliente";
		this.qos = 2;
		this.persistence = new MemoryPersistence();
		try {
			client = new MqttClient(broker, clientId, persistence);
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        client.setCallback(this);
	        client.connect(connOpts);
	        client.subscribe(topic,qos);
	        System.out.println("Cliente conectado e inscrito no tópico.");
	    } catch(MqttException e) {
	        System.out.println("Exception "+e);
	    }
	}

	public void desconectar() {
		try {
			client.disconnect();
			System.out.println("Cliente desconectado.");
			System.exit(0);
		} catch (MqttException e) {
			System.out.println("Exception "+e);
		}
	}

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("Conexão perdida.");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		System.out.println("-------------------------------------------------");
		System.out.println("| Tópico:" + arg0);
		System.out.println("| Mensagem: " + new String(arg1.getPayload()));
		System.out.println("-------------------------------------------------");
	}
	

}
