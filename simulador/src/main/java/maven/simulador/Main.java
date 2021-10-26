package maven.simulador;

//import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main(String[] args) {
		Sensor sensor = new Sensor(0,0);
		String content = "CO2 = "+sensor.getCo2()+" ; CO = "+sensor.getCo()
		+" ; Fan = "+sensor.isFan();
		System.out.println(content);
		MqttPublish publicar = new MqttPublish(content);
		publicar.publish();
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(5);
				//LocalTime myObj = LocalTime.now(); 
			    //System.out.println(myObj);
				if(sensor.isFan()) {
					if(sensor.getCo2() > 0) {
						sensor.setCo2(sensor.getCo2()-1);
					}
					if(sensor.getCo() > 0) {
						sensor.setCo(sensor.getCo()-1);
					}
					if(sensor.getCo2() == 0 && sensor.getCo() == 0) {
						sensor.setFan(false);
					}
				}
				else {
					sensor.setCo2(sensor.getCo2()+returnRandom());
					sensor.setCo(sensor.getCo()+returnRandom());
					if(sensor.getCo2()> 15 || sensor.getCo() > 10) {
						sensor.setFan(true);
					}
				}				
				content = "CO2 = "+sensor.getCo2()+" ; CO = "+sensor.getCo()
				+" ; Fan = "+sensor.isFan();
				System.out.println(content);
				publicar.setContent(content);
				publicar.publish();
			} catch (InterruptedException e) {
				System.out.println("exception "+e);
			}	
		}
	}
	public static int returnRandom() {	
		Random rand = new Random();
		return rand.nextInt(5); 
	}
}
