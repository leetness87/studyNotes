import java.awt.Color;

public class RatesMethods {
	// initialization of arrays and variable to the object member variable level
	private GTerm gt;
	private String[] names;
	private char[] genders;
	private int[] birthYears;
	private double[] hourlyRates;
	private Boolean[] terms;
	private int currentStaff;
	private int maxNumberStaff;
	private String inputs;

	public RatesMethods() {
		this.gt = new GTerm(800, 800);
		this.gt.setXY(70, 50);
		this.gt.addTable(700, 600, "Name\tGender\tBirth Year\tHourly Rate\tTerms Accepted");
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.RED);
		this.gt.setBackgroundColor(Color.GRAY);
		// makes button go down bottom
		this.gt.println("");
		// adds a button that calls on addRecord
		this.gt.addButton("Add Record", this, "addRecord");
		// initializing array lengths to 1
		this.maxNumberStaff = 1;
		// initializing stings to set length issued
		this.names = new String[this.maxNumberStaff];
		this.genders = new char[this.maxNumberStaff];
		this.birthYears = new int[this.maxNumberStaff];
		this.hourlyRates = new double[this.maxNumberStaff];
		this.terms = new Boolean[this.maxNumberStaff];
		this.gt.println("");
		this.gt.setFontColor(Color.BLACK);
		//enters line of text on gterm window and a text field under it for inputs
		this.gt.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		this.gt.addTextField("", 500);
	}

	public void addRecord() {
		// gets user input to be added to table from the text field
		this.inputs = this.gt.getTextFromEntry(0);
		// inserts the data into the available string elements on the press of add record button
		if (this.inputs != null) {
			// splits the user inputs separated by a comma to store in element zones in
			// inputData array
			String[] inputData = this.inputs.split(",");
			// takes first string input from inputData array element 0 and creates string
			// for name
			String name = inputData[0];
			while (name.isBlank()) {
				name = this.gt.getInputString("You have not entered name correctly please try again");
			} // takes second string input from inputData array element 1 and creates char for
				// gender
			char gender = inputData[1].charAt(0);
			while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
				gender = this.gt.getInputString("You entered an incorrect value for gender\nplease input M or F")
						.charAt(0);
			} // takes third string input from inputData array element 2 and creates int for
				// birthYear
			int birthYear = Integer.parseInt(inputData[2]);
			while (birthYear < 1900) {
				birthYear = Integer
						.parseInt(this.gt.getInputString("The entered year Value may not be valid\nplease try again"));
			} // takes fourth string input from inputData array element 3 and creates double
				// for hourlyRate
			double hourlyRate = Double.parseDouble(inputData[3]);
			while (hourlyRate < 15.00 || hourlyRate > 99) {
				hourlyRate = Double.parseDouble(
						this.gt.getInputString("Please check value of Hourly Rate\nthe entered value was not valid"));
			} // takes fifth string input from inputData array element 4 and creates Boolean
				// for term
			Boolean term = Boolean.parseBoolean(inputData[4]);
			while (!term) {
				term = Boolean.parseBoolean(this.gt.getInputString("Too bad you must enter True"));
			}
			// increasing size of the arrays if there is not enough room left
			if (this.currentStaff >= this.maxNumberStaff) {
				// increments the strings by 1 to give additional spot for input
				this.maxNumberStaff += 1;
				// creation of temp used arrays with new length set
				String[] longerNames = new String[this.maxNumberStaff];
				char[] longerGender = new char[this.maxNumberStaff];
				int[] longerBirth = new int[this.maxNumberStaff];
				double[] longerWage = new double[this.maxNumberStaff];
				Boolean[] longerTerm = new Boolean[this.maxNumberStaff];
				// new counter initialization
				int j = 0;
				// transfers the elemental data from the existing arrays to the longer temp ones
				while (j < this.currentStaff) {
					longerNames[j] = this.names[j];
					longerGender[j] = this.genders[j];
					longerBirth[j] = this.birthYears[j];
					longerWage[j] = this.hourlyRates[j];
					longerTerm[j] = this.terms[j];
					j++;
				}
				// creates the original arrays back from the temp ones so the originals now have
				// a longer size with all datum still stored from previous
				this.names = longerNames;
				this.genders = longerGender;
				this.birthYears = longerBirth;
				this.hourlyRates = longerWage;
				this.terms = longerTerm;
			}
			// sends all variables above to the element chosen by counter currentStaff to
			// there set arrays
			this.names[this.currentStaff] = name;
			this.genders[this.currentStaff] = gender;
			this.birthYears[this.currentStaff] = birthYear;
			this.hourlyRates[this.currentStaff] = hourlyRate;
			this.terms[this.currentStaff] = term;
			this.currentStaff += 1;
			// calls refresh table 
			refreshTable();
		}
	}
	public void refreshTable() {
		// add rows to the table using a counter will clear table and add all
		// inputs in the arrays 
		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < this.currentStaff) {
			this.gt.addRowToTable(0, this.names[i] + "\t" + this.genders[i] + "\t" + this.birthYears[i] + "\t"
					+ this.hourlyRates[i] + "\t" + this.terms[i]);
			i += 1;
		}
	}
	public static void main(String[] args) {
		RatesMethods prmObj = new RatesMethods();
	}

}
//bob,m,1980,33,true