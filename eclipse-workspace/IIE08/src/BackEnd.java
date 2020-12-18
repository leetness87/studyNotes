import javax.swing.JOptionPane;

public class BackEnd {
	// initialization of arrays and variable to the object member variable level
	// these are the main arrays that store data
	private String[] names;
	private char[] genders;
	private int[] birthYears;
	private double[] hourlyRates;
	private Boolean[] terms;
	// these are the temp arrays used here to use over multiple methods to reduce
	// duplicate coding
	private String[] tempNames;
	private char[] tempgenders;
	private int[] tempBirthYears;
	private double[] tempHourlyRates;
	private Boolean[] tempTerms;
	// these are counters that keep track of the array lengths
	private int currentStaff;
	private int maxNumberStaff;
	// used as a data entry from the frontend programs
	private String inputs;
	// used this as a way to manipulate arrays up and down with same coding
	private int arraysize;
	// counter variables to be used in multiple methods
	private int i;
	private int j;
	// a stored user input from the selection method to be used throughout the
	// coding
	private int storedInput;

	// constructor
	public BackEnd() {
		// initial creation of all set to 1 ready for the first inputs
		this.maxNumberStaff = 1;
		this.names = new String[this.maxNumberStaff];
		this.genders = new char[this.maxNumberStaff];
		this.birthYears = new int[this.maxNumberStaff];
		this.hourlyRates = new double[this.maxNumberStaff];
		this.terms = new Boolean[this.maxNumberStaff];

	}

	// method that can be used for both console and gterm alike
	public void addRecord() {
		// setting the sizes of the coding further down had to put these in to
		// manipulate same code for different things
		this.arraysize = this.maxNumberStaff;
		this.storedInput = this.currentStaff;
		// calls a check to see if the array has room if not increases arrays
		arrayCheck();
		// check to see if the inputs have an entry inputs are from a set method used by
		// the frontend
		if (this.inputs != null) {
			// splits the user inputs separated by a comma to store in element zones in
			// inputData array each has the usual conditional checks on them
			String[] inputData = this.inputs.split(",");
			String name = inputData[0];
			while (name.isBlank()) {
				name = JOptionPane.showInputDialog("You have not entered name correctly please try again");
			}
			char gender = inputData[1].charAt(0);
			while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
				gender = JOptionPane.showInputDialog("You entered an incorrect value for gender\nplease input M or F")
						.charAt(0);
			}
			int birthYear = Integer.parseInt(inputData[2]);
			while (birthYear < 1900) {
				birthYear = Integer.parseInt(
						JOptionPane.showInputDialog("The entered year Value may not be valid\nplease try again"));
			}
			double hourlyRate = Double.parseDouble(inputData[3]);
			while (hourlyRate < 15.00 || hourlyRate > 99) {
				hourlyRate = Double.parseDouble(JOptionPane
						.showInputDialog("Please check value of Hourly Rate\nthe entered value was not valid"));
			}
			Boolean term = Boolean.parseBoolean(inputData[4]);
			while (!term) {
				term = Boolean.parseBoolean(JOptionPane.showInputDialog("Too bad you must enter True"));
			}
			// inputs the data above into there arrays
			this.names[this.currentStaff] = name;
			this.genders[this.currentStaff] = gender;
			this.birthYears[this.currentStaff] = birthYear;
			this.hourlyRates[this.currentStaff] = hourlyRate;
			this.terms[this.currentStaff] = term;
			// increases counter on staff by 1 for every new entry
			this.currentStaff += 1;
		}

	}

	// method to edit can be used by gterm or console
	public void edit() {
		// calls selection method for a user input as to who they wish to edit
		selection();
		// calls method to set input from within the backend
		setInputs(JOptionPane.showInputDialog("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct"));
		// creates string for the input from diag and splits them and from there is
		// added to individual element zone with values usual stuff
		String[] inputData = this.inputs.split(",");
		String name = inputData[0];
		while (name.isBlank()) {
			name = JOptionPane.showInputDialog("You have not entered name correctly please try again");
		}
		char gender = inputData[1].charAt(0);
		while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			gender = JOptionPane.showInputDialog("You entered an incorrect value for gender\nplease input M or F")
					.charAt(0);
		}
		int birthYear = Integer.parseInt(inputData[2]);
		while (birthYear < 1900) {
			birthYear = Integer
					.parseInt(JOptionPane.showInputDialog("The entered year Value may not be valid\nplease try again"));
		}
		double hourlyRate = Double.parseDouble(inputData[3]);
		while (hourlyRate < 15.00 || hourlyRate > 99) {
			hourlyRate = Double.parseDouble(
					JOptionPane.showInputDialog("Please check value of Hourly Rate\nthe entered value was not valid"));
		}
		Boolean term = Boolean.parseBoolean(inputData[4]);
		while (!term) {
			term = Boolean.parseBoolean(JOptionPane.showInputDialog("Too bad you must enter True"));
		} // changes the given selection(element) and updates it with the new data given
		this.names[this.storedInput] = name;
		this.genders[this.storedInput] = gender;
		this.birthYears[this.storedInput] = birthYear;
		this.hourlyRates[this.storedInput] = hourlyRate;
		this.terms[this.storedInput] = term;
	}

	// method to transfer all data from temp arrays to mains once array checks and
	// manipulation have been done see down further
	public void transferArray() {
		this.tempNames[j] = this.names[i];
		this.tempgenders[j] = this.genders[i];
		this.tempBirthYears[j] = this.birthYears[i];
		this.tempHourlyRates[j] = this.hourlyRates[i];
		this.tempTerms[j] = this.terms[i];
		this.j++;
		this.i++;
	}

	// a method called from within array check will only run if critera is met from
	// if statement that user wishes to remove a staff member
	public void arrayRemove() {
		if ((this.storedInput + 1) < this.currentStaff) {
			while (i < this.currentStaff) {
				// this skips out on the value selected staff member
				if (i == (this.storedInput)) {
					this.i++;
				} // see above method
				transferArray();

			} // further statement incase the element to be removed is the last entry in stops
				// any out of bounds errors
		} else if ((this.storedInput + 1) == this.currentStaff) {
			while (i < (this.currentStaff - 1)) {
				transferArray();
			}
		}
	}

	// main method for checking the state of the array
	public void arrayCheck() {
		// zeroing main counters
		this.j = 0;
		this.i = 0;
		// this calls an increase on array size if needed in this will always be true as
		// i increment or decrease by 1 because i dont want null values
		if (this.currentStaff >= this.maxNumberStaff) {
			// arraysize is from max staff and is changed weather the user wishs to add or
			// remove
			this.arraysize++;
		}
		// creation of temp used arrays with new length set
		this.tempNames = new String[this.arraysize];
		this.tempgenders = new char[this.arraysize];
		this.tempBirthYears = new int[this.arraysize];
		this.tempHourlyRates = new double[this.arraysize];
		this.tempTerms = new Boolean[this.arraysize];
		// if statement but with coding i put in will always be valid left in incase i
		// change some values on arrays later to how much they increase decrease etc
		if (this.currentStaff >= this.maxNumberStaff) {
			// calls on a remove function as stored inputs would be equal unless using the
			// remove code
			if (this.currentStaff != this.storedInput) {
				arrayRemove();
				// the usual to increase the array size and the max staff value the use here of
				// j is why i had to make it a private
			} else if (this.currentStaff >= this.maxNumberStaff) {
				this.maxNumberStaff++;
				while (j < this.currentStaff) {
					transferArray();
				}
			}
			// creates the original arrays back from the temp ones so the originals now have
			// a longer or shorter size with all datum still stored from previous
			this.names = this.tempNames;
			this.genders = this.tempgenders;
			this.birthYears = this.tempBirthYears;
			this.hourlyRates = this.tempHourlyRates;
			this.terms = this.tempTerms;
		}
	}

	// method for user selection used for edit delete just returns a elemental value
	// for use
	public int selection() {
		this.storedInput = Integer
				.parseInt(JOptionPane.showInputDialog("enter which staff member you want to remove:"));
		while (this.storedInput > this.currentStaff) {
			this.storedInput = Integer.parseInt(
					JOptionPane.showInputDialog("out of bounds\nenter which staff member you want to remove:"));
		}
		this.storedInput = this.storedInput - 1;
		return this.storedInput;
	}

	// the remove method
	public void remove() {
		selection();
		// this is how i manage to decrease arrays in the arraycheck by taking away 2
		// prior to it adding 1 so the temp arrays created will be 1 less than max size
		this.arraysize = this.maxNumberStaff - 2;
		arrayCheck();
		// decrease in main counters
		this.maxNumberStaff--;
		this.currentStaff--;
	}

	// the following are for the front end so it can access the arrays saved here to
	// print out in either system or on table
	public int getStaff() {
		return this.currentStaff;
	}

	public String[] getname() {
		return this.names;
	}

	public int[] getbirth() {
		return this.birthYears;
	}

	public double[] getrate() {
		return this.hourlyRates;
	}

	public char[] getgender() {
		return this.genders;
	}

	public Boolean[] getterm() {
		return this.terms;
	}
	// used by front end to transfer the data back here to be used in the methods
	public void setInputs(String inputs) {
		this.inputs = inputs;
	}
}

//bob,m,1980,33,true