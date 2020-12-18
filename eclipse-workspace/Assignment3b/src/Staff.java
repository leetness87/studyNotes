
public class Staff {
	// creationg of the elements to each staff member
	private String firstName;
	private String lastName;
	private double hourlyRate;
	private double hours;
	private String rawData;

	// main method of staff
	public Staff(String firstName, String lastName, double hourlyRate, double hours, String rawData) {
		// sets first name in staff member
		this.setFirstName(firstName);
		// sets last name in staff member
		this.setLastName(lastName);
		// sets hourly rate for staff member
		this.setHourlyRate(hourlyRate);
		// sets hours of staff member
		this.setHours(hours);
		// sets the raw data given by the user in a string that isnt split for further
		// use
		this.setRawData(rawData);
	}

	// the following are all just the usual get and set methods for each individual
	// elements that are contained within the staff class
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
