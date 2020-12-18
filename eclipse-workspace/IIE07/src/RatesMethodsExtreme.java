import java.awt.Color;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class RatesMethodsExtreme {
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
	private Scanner recordScan;

	public RatesMethodsExtreme(int userInterfaceMode) {
		// initial creation of all strings as well as the input to control which
		// interface you want
		this.maxNumberStaff = 1;
		this.names = new String[this.maxNumberStaff];
		this.genders = new char[this.maxNumberStaff];
		this.birthYears = new int[this.maxNumberStaff];
		this.hourlyRates = new double[this.maxNumberStaff];
		this.terms = new Boolean[this.maxNumberStaff];
		if (userInterfaceMode == 0) {
			this.recordScan = new Scanner(System.in);
			console();
		} else if (userInterfaceMode == 1) {
			gterm();
		} else {
			JOptionPane.showMessageDialog(null, "You wood-duck");
		}

	}

	// gterm main that calls on the methods as needed by buttons and text field
	// entry
	public void gterm() {
		this.gt = new GTerm(800, 800);
		this.gt.setXY(70, 50);
		this.gt.addTable(700, 600, "Name\tGender\tBirth Year\tHourly Rate\tTerms Accepted");
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.RED);
		this.gt.setBackgroundColor(Color.GRAY);
		// makes button go down bottom
		this.gt.println("");
		// adds buttons with a space to move last 2 down added refresh because why not
		this.gt.addButton("Add Record", this, "addRecord");
		this.gt.addButton("Edit", this, "butEdit");
		this.gt.addButton("Remove", this, "butRemove");
		this.gt.println("");
		this.gt.addButton("Console", this, "toConsole");
		this.gt.addButton("Refresh", this, "refreshTable");
		// initializing array lengths to 1
		this.gt.println("");
		this.gt.setFontColor(Color.BLACK);
		// enters line of text on gterm window and a text field under it for inputs
		this.gt.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		this.gt.addTextField("", 500);
	}

	// method to swap from console to gterm
	public void toGterm() {
		gterm();
		refreshTable();
	}

	// extra method to add refresh table as couldn't call 2 from button???
	public void butRemove() {
		remove();
		refreshTable();
	}

	// same as above just for edit button
	public void butEdit() {
		edit();
		refreshTable();
	}

	// method to change from gterm to console
	public void toConsole() {
		this.gt.close();
		this.recordScan = new Scanner(System.in);
		conRefresh();
		console();
	}

	public void console() {
		// console version main page has 4 selections that call on methods depending on
		// user input
		System.out.println(
				"Welcome to Staff record keeper 2.0\nPlease enter option\n1. Add record\n2. Edit record \n3. Delete record \n4. Go to gterm ");
		int input = this.recordScan.nextInt();
		if (input == 1) {
			conAddRecord();
		} else if (input == 2) {
			edit();
			conRefresh();
			console();
		} else if (input == 3) {
			remove();
			conRefresh();
			console();
		} else if (input == 4) {
			toGterm();
		}
	}

	public void conAddRecord() {
		// adds record for the console version calling 3 methods
		System.out.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		this.inputs = this.recordScan.next();
		allData();
		conRefresh();
		console();
	}

	public void addRecord() {
		// gets user input to be added to table from the text field
		this.inputs = this.gt.getTextFromEntry(0);
		allData();
		System.out.println(maxNumberStaff);
		System.out.println(currentStaff);
		refreshTable();
	}

	public void allData() {
		if (this.inputs != null) {
			// splits the user inputs separated by a comma to store in element zones in
			// inputData array
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
		}
	}

	public void edit() {
		// selection of which staff member they wish to edit
		int selection = Integer.parseInt(JOptionPane.showInputDialog("enter which staff member you want to edit:"));
		// changes the selection given to the elemental number
		selection = (selection - 1);
		this.inputs = JOptionPane.showInputDialog("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		// creates string for the input from diag and splits them and from there is
		// added to individual element zone with values needed has loops for correct
		// inputs
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
		this.names[selection] = name;
		this.genders[selection] = gender;
		this.birthYears[selection] = birthYear;
		this.hourlyRates[selection] = hourlyRate;
		this.terms[selection] = term;
	}

	public void remove() {
		int selection = Integer.parseInt(JOptionPane.showInputDialog("enter which staff member you want to remove:"));
		// makes sure the input is within range so the array data doesnt wipe to null
		while (selection > this.currentStaff) {
			selection = Integer.parseInt(
					JOptionPane.showInputDialog("out of bounds error!!\nenter which staff member you want to remove:"));
		} // new temp strings minus 1 length of originals
		String[] removeNames = new String[this.maxNumberStaff - 1];
		char[] removeGender = new char[this.maxNumberStaff - 1];
		int[] removeBirth = new int[this.maxNumberStaff - 1];
		double[] removeWage = new double[this.maxNumberStaff - 1];
		Boolean[] removeTerm = new Boolean[this.maxNumberStaff - 1];
		// new counter initialization
		int j = 0;
		int i = 0;
		// transfers the elemental data from the existing arrays to the temp ones
		if (selection < this.currentStaff) {
			while (i < this.currentStaff) {
				// skips the selected element and continues counter past it
				if (i == (selection - 1)) {
					i++;
				}
				removeNames[j] = this.names[i];
				removeGender[j] = this.genders[i];
				removeBirth[j] = this.birthYears[i];
				removeWage[j] = this.hourlyRates[i];
				removeTerm[j] = this.terms[i];
				j++;
				i++;
			} // wrote in this section to remove the latest input removes out of bound errors
				// perhaps better way i havnt thought of yet
		} else if (selection == this.currentStaff) {
			while (i < (this.currentStaff - 1)) {
				removeNames[j] = this.names[i];
				removeGender[j] = this.genders[i];
				removeBirth[j] = this.birthYears[i];
				removeWage[j] = this.hourlyRates[i];
				removeTerm[j] = this.terms[i];
				j++;
				i++;
			}
		}
		// creates the original arrays back from the temp ones so the originals now have
		// a shorter size with all datum still stored from previous minus the removed
		// element still in order
		this.names = removeNames;
		this.genders = removeGender;
		this.birthYears = removeBirth;
		this.hourlyRates = removeWage;
		this.terms = removeTerm;
		this.maxNumberStaff--;
		this.currentStaff--;
	}

	public void conRefresh() {
		// refreshes the console version same principal as refreshtable
		int i = 0;
		while (i < this.currentStaff) {
			System.out.println("Staff Member " + (i + 1) + ": " + this.names[i] + " " + this.genders[i] + " "
					+ this.birthYears[i] + " " + this.hourlyRates[i] + " " + this.terms[i]);
			i += 1;
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
		RatesMethodsExtreme prmObj = new RatesMethodsExtreme(
				Integer.parseInt(JOptionPane.showInputDialog("enter 0 for console\nenter 1 for gTerm")));

	}

}
//bob,m,1980,33,true