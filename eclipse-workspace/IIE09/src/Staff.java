
public class Staff {
	private String name;
	private char gender;
	private int birthYear;
	private double hourlyRate;
	private Boolean term;
	// constructor of class 
	public Staff(String name, char gender, int birthYear, double hourlyRate, boolean term) {
		this.setName(name);
		this.setGender(gender);
		this.setBirthYear(birthYear);
		this.setHourlyRate(hourlyRate);
		this.setTerm(term);
	}
	// the getters and setters for all the above elements
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public Boolean getTerm() {
		return term;
	}

	public void setTerm(Boolean term) {
		this.term = term;
	}

}
