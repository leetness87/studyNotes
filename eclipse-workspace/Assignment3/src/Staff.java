
public class Staff {
	private String firstName;
	private String lastName;
	private double hourlyRate;
	private double hours;
	private String rawData;
	
	public Staff(String firstName, String lastName, double hourlyRate, double hours, String rawData) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setHourlyRate(hourlyRate);
		this.setHours(hours);
		this.setRawData(rawData);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
}
