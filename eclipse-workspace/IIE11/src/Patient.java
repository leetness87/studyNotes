
public class Patient {
	private double temperature;
	private String nausia;
	private String lumbarPain;
	private String urinePushing;
	private String micturationPain;
	private String urethraProblems;
	private String decideInflamation;
	private String decideNephritus;
	private String rawData;

	public Patient(double temperature, String nausia, String lumbarPain, String urinePushing, String micturationPain,
			String urethraProblems, String decideInflamation, String decideNephritus, String rawData) {
		this.setTemperature(temperature);
		this.setNausia(nausia);
		this.setLumbarPain(lumbarPain);
		this.setUrinePushing(urinePushing);
		this.setMicturationPain(micturationPain);
		this.setUrethraProblems(urethraProblems);
		this.setDecideInflamation(decideInflamation);
		this.setDecideNephritus(decideNephritus);
		this.setRawData(rawData);
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getNausia() {
		return nausia;
	}

	public void setNausia(String nausia) {
		this.nausia = nausia;
	}

	public String getLumbarPain() {
		return lumbarPain;
	}

	public void setLumbarPain(String lumbarPain) {
		this.lumbarPain = lumbarPain;
	}

	public String getUrinePushing() {
		return urinePushing;
	}

	public void setUrinePushing(String urinePushing) {
		this.urinePushing = urinePushing;
	}

	public String getMicturationPain() {
		return micturationPain;
	}

	public void setMicturationPain(String micturationPain) {
		this.micturationPain = micturationPain;
	}

	public String getUrethraProblems() {
		return urethraProblems;
	}

	public void setUrethraProblems(String urethraProblems) {
		this.urethraProblems = urethraProblems;
	}

	public String getDecideInflamation() {
		return decideInflamation;
	}

	public void setDecideInflamation(String decideInflamation) {
		this.decideInflamation = decideInflamation;
	}

	public String getDecideNephritus() {
		return decideNephritus;
	}

	public void setDecideNephritus(String decideNephritus) {
		this.decideNephritus = decideNephritus;
	}


	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

}
