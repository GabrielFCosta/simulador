package maven.simulador;

public class Sensor {
	int co2, co;
	boolean fan;

	public Sensor(int co2, int co) {
		super();
		this.setCo2(co2);
		this.setCo2(co);
		this.fan = false;
	}

	public int getCo2() {
		return co2;
	}

	public int getCo() {
		return co;
	}

	public boolean isFan() {
		return fan;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public void setCo(int co) {
		this.co = co;
	}

	public void setFan(boolean fan) {
		this.fan = fan;
	}
}
