import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

public class PayRateProgram {
	// the 3 gterm windows i will be using initially had 2 but created 3rd as a
	// console to print out errors so i didnt use showdiag etc
	private GTerm gtMain;
	private GTerm gtTable;
	private GTerm gtConsole;
	// the arrays i will be using have used short and float as because they are
	// packed in an array are worth using for the purpose of memory the hours i used
	// short i could use float again so the user could input hours in decimal point
	// but for the purpose of this creation have kept it to whole numbers only
	private String[] lastNames;
	private char[] firstNames;
	private short[] hours;
	private float[] hourlyRates;
	// counters as to how big arrays are aswell as how many entry have been made
	// into array currently
	private int currentStaff;
	private int arraySize;

	public PayRateProgram() {
		// a private gterm window that will close upon an entry made by the user and
		// create the 3 object windows if needed
		GTerm welcome = new GTerm(300, 400);
		welcome.setXY(1, 1);
		welcome.println(
				"Welcome to my Payrate Program\nby using this you agree that\na high mark will be achieved\nor $100 cash :P");
		// a true false statement if entered true the program will comence if false the
		// program shuts down used getinput string as only way to do what i want
		boolean terms = Boolean.parseBoolean(welcome.getInputString("Do you agree to my terms?\ntrue or false"));
		if (terms) {
			// closes the welcome gterm and creates the main gterms to be used within the
			// program
			welcome.close();
			// initialize current entries to 0 as no entries would be made on initial
			// startup
			this.currentStaff = 0;
			// initialize the window for the console or printouts
			this.gtConsole = new GTerm(600, 300);
			// moves window to top left so user does not need to move it
			this.gtConsole.setBounds(1, 1, 600, 300);
			// initializes the gterm window for the table
			this.gtTable = new GTerm(900, 800);
			// moves the window to the rhs instead of being centred so use does not need to
			// move windows on startup
			this.gtTable.setBounds(601, 1, 900, 800);
			// initialize the second gterm which is the main for entries and menu bar
			this.gtMain = new GTerm(600, 500);
			// moves the window to the left bottom so that the user does not need to move
			// window on startup
			this.gtMain.setBounds(1, 301, 600, 500);
			// setting the xy and the colours of the windows
			this.gtMain.setXY(50, 10);
			this.gtMain.setFontSize(16);
			this.gtMain.setFontColor(Color.BLACK);
			this.gtMain.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setFontColor(Color.BLACK);
			this.gtTable.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setBackground(Color.cyan);
			this.gtConsole.setBackgroundColor(Color.BLACK);
			this.gtConsole.setFont(null, Font.ITALIC, 16);
			this.gtConsole.setFontColor(Color.GREEN);
			// adds text to the window
			this.gtMain.println("Welcome to Wage Calculator 2.0");
			this.gtMain.println("Please make your selection\nfrom the available buttons");
			// reduction in font size to differ the header to the text
			this.gtMain.setFontSize(12);
			// the following adds the buttons to the main gterm with each of them having
			// there own seperate methods that are called for in between is println's for
			// spacing purposes
			this.gtMain.addButton("Add Record From text", this, "addRecord");
			this.gtMain.println("");
			this.gtMain.addButton("Edit First Name", this, "editFirstName");
			this.gtMain.println("");
			this.gtMain.addButton("Edit Last Name", this, "editLastName");
			this.gtMain.println("");
			this.gtMain.addButton("Edit hourly Rate", this, "editHourlyRate");
			this.gtMain.println("");
			this.gtMain.addButton("Edit Hours Worked", this, "editHours");
			this.gtMain.println("");
			this.gtMain.addButton("Info", this, "info");
			this.gtMain.println("");
			this.gtMain.addButton("Exit", this, "exit");
			this.gtMain.println("");
			// increase in font size again for the textfield to be larger then menu select
			// making it stick out more
			this.gtMain.setFontSize(16);
			this.gtMain.println("Enter First Name, Last name, Hourly rate , Hours\nSeperate by a comma");
			// adds a text field to the bottom of the window
			this.gtMain.addTextField("Enter Text Here", 400);
			this.gtMain.println("");
			this.gtMain.println("Enter in values here to edit from selection\n");
			this.gtMain.addTextField("", 200);
			// the following adds the table to the gterm table window aswell as buttons to
			// remove and edit which calls the relevant methods also setting font size and
			// xy to make most of sizing
			this.gtTable.setXY(1, 1);
			this.gtTable.setFontSize(14);
			this.gtTable.addTable(900, 700,
					"Index\tFirst name\tLastname\tHourly Rate\tHours\tNormal Pay\tOvertime\tTotal");
			this.gtTable.println("");
			this.gtTable.addButton("Remove entry", this, "remove");
			// initialize the array sizings from the initial startup so the user may set how
			// long the arrays are at the start of the program passed as an int as is a
			// counter and if the program was to increase in exponential size would not run
			// out of room used getinputstring as no better way in gterm
			this.arraySize = Integer.parseInt(this.gtMain.getInputString("How many records are you entering today?"));
			// adds condition from user input as arraysize should not be less than zero
			// again using getinputstring as gterm has no other real options
			while (this.arraySize < 0) {
				this.arraySize = Integer
						.parseInt(this.gtMain.getInputString("How many records are you entering today?"));
			}
			// initialize of all arrays to the set length the user has input
			this.lastNames = new String[this.arraySize];
			this.firstNames = new char[this.arraySize];
			this.hours = new short[this.arraySize];
			this.hourlyRates = new float[this.arraySize];
			// if user entered false this will exit the program
		} else {
			welcome.showErrorDialog("You miss out");
			welcome.close();

		}
	}

	// method to input the inputs into seperate array elements of the suitable value
	public void addRecord() {
		String inputData = this.gtMain.getTextFromEntry(0);
		// conditional check to make sure the user inputs are not null and have actually
		// been entered
		if (inputData != null) {
			// creation of a string array that then splits the inputs into the elemntal
			// spots required with the use of a comma
			String[] userInput = inputData.split(",");
			// clears the console gterm
			this.gtConsole.clear();
			// takes the first element of input array and converts it to a char as first
			// name wanted will become an initial has a conditional check to make sure the
			// char at 0 or first spot is alphabetical however can still remain a white
			// space
			char firstName = userInput[0].toUpperCase().charAt(0);
			while (!Character.isAlphabetic(firstName)) {
				// prints the error recieved in the console gterm
				this.gtConsole.println("The entered value in firstname is not correct must be alphabetical");
				// had to use get input string as no other viable choice in gterm
				firstName = this.gtMain.getInputString("Enter Firstname").charAt(0);
			}
			// takes the second element of input array and creates new string from entry has
			// a conditional check to make sure no white spaces are in last name eg if the
			// user entered 2 last names must be hyphonated not a space between other checks
			// that can be put in however for the sake of this program this will be
			// sufffiecient
			String lastName = userInput[1].toUpperCase();
			while (lastName.isEmpty() && lastName.contains(" ")) {
				// prints the error to the gterm console window
				this.gtConsole.println("The entered value for last name must not contain spaces");
				// had to use get input string as no other viable choice in gterm
				lastName = this.gtMain.getInputString("Enter Last Name");
			}
			// takes the third element of input array and converts to a float value used a
			// float as hourly rate shouldnt exceed the value to become a double has a
			// conditional check also to make sure it is within a range 16 being minimum
			// wage it cant go under and 99 as if anyting entered is above this assuming it
			// was a button mispress and 3 were entered rather than 2 as anything 100 or
			// above would be salary rather than rate
			float hourlyRate = Float.parseFloat(userInput[2]);
			while (hourlyRate < 16 || hourlyRate > 199) {
				// prints the error to the gterm console
				this.gtConsole.println(
						"The number you entered for hourly rate seems to be invalid\nneeds to be between 16 and 199");
				// had to use get input string as no other viable choice in gterm
				hourlyRate = Float.parseFloat(this.gtMain.getInputString("enter hourly rate"));
			}
			// takes the fourth element of input and converts it to short as hours worked in
			// a week wouldnt exceed the limit of a short has a conditional check too so a
			// minus cannot be entered aswell as anything above 112 would not be legal
			short hours = Short.parseShort(userInput[3]);
			while (hours < 0 || hours > 112) {
				// prints the error to the gterm console
				this.gtConsole.println("The hours you entered do not seem to be valid\nmust be between 0 and 112");
				// had to use get input string as no other viable choice in gterm
				hours = Short.parseShort(this.gtMain.getInputString("Enter hours"));
			}

			// increasing size of the arrays if there is not enough room left within
			if (this.currentStaff >= this.arraySize) {
				// increments the arrays by 1 to give additional spot for input as each input is
				// a single array add +1 seemed relevant as to not increase the arrays beyond
				// the use
				this.arraySize += 1;
				// creation of temp used arrays with new lengths which will be 1 larger to allow
				// for the inputs to be added
				char[] longerFirstNames = new char[this.arraySize];
				String[] longerLastNames = new String[this.arraySize];
				float[] longerHourlyRate = new float[this.arraySize];
				short[] longerHours = new short[this.arraySize];
				// new counter initialization
				int j = 0;
				// transfers the elemental data from the existing arrays to the longer temp ones
				// used in a while loop with the condition it is less than current staff counter
				// as the current staff array starts at 1 and counter is 0 can use less than
				while (j < this.currentStaff) {
					longerFirstNames[j] = this.firstNames[j];
					longerLastNames[j] = this.lastNames[j];
					longerHourlyRate[j] = this.hourlyRates[j];
					longerHours[j] = this.hours[j];
					j++;
				}
				// creates the original arrays back from the temp ones so the originals now have
				// a longer size with all datum still stored from previous inputs however the
				// arrays are now of 1 size greater to the input can be added
				this.firstNames = longerFirstNames;
				this.lastNames = longerLastNames;
				this.hourlyRates = longerHourlyRate;
				this.hours = longerHours;

			}
			// sends the datum of the inputs to the relevant element in the arrays that are
			// set by the current staff counter
			this.firstNames[this.currentStaff] = firstName;
			this.lastNames[this.currentStaff] = lastName;
			this.hourlyRates[this.currentStaff] = hourlyRate;
			this.hours[this.currentStaff] = hours;
			// increases the counter of current staff by 1 as an entry was made into the
			// arrays
			this.currentStaff += 1;
			// calls the method refresh table to print out the array elements
			refreshTable();
		}
	}

	// method to get index from a selection on the table
	public int tableSelect() {
		// creates an int value to return and is chosen by get index of table so if user
		// selects on the table and clicks a button will return the index used an int as
		// because it is not in array the use of short etc would still store in a 32 bit
		// segment same for all other ints i create in theory reduces code duplication
		// it is only 1 line but 1 line i dont need to write 4 times and dont need to
		// write justification each time
		int i = this.gtTable.getIndexOfSelectedRowFromTable(0);
		// returns the value for the method
		return i;
	}

	// method for button to edit first names
	public void editFirstName() {
		// gets the index from the table select method
		int i = tableSelect();
		// clears console
		this.gtConsole.clear();
		// prints to console the selection the user had made so they can see former
		// value in case they selected wrong they can change back
		this.gtConsole.println("You just edited " + lastNames[i] + " first name initial:" + firstNames[i]);
		// creates a temp value incase the user enters a value outside the parameters
		char temp = this.firstNames[i];
		// gathers the input from the second text field in the main gterm window and
		// allocates it as seen fit by the code
		this.firstNames[i] = this.gtMain.getTextFromEntry(1).toUpperCase().charAt(0);
		// if the user entered an invalid choice instead of changing to the new value
		// from the text field it will reset it to the temp variable created prior
		// therefore not changing it at all
		if (!Character.isAlphabetic(firstNames[i])) {
			this.gtConsole.println("The first name you entered is not valid\nand has been reset to initial value");
			this.firstNames[i] = temp;
		}
		// prints an output in the console gterm to show the new value that has been
		// entered
		this.gtConsole.println("The new value is :" + firstNames[i]);
		// calling refresh table method
		refreshTable();
	}

	public void editLastName() {
		// gets the index from the table select method
		int i = tableSelect();
		// clears console
		this.gtConsole.clear();
		// prints to console the selection the user had made so they can see former
		// value in case they selected wrong they can change back
		this.gtConsole.println("You just edited " + lastNames[i] + " Last Name:" + lastNames[i]);
		// creates a temp value incase the user enters a value outside the parameters
		String temp = this.lastNames[i];
		// gathers the input from the second text field in the main gterm window and
		// allocates it as seen fit by the code
		this.lastNames[i] = this.gtMain.getTextFromEntry(1).toUpperCase();
		// if the user entered an invalid choice instead of changing to the new value
		// from the text field it will reset it to the temp variable created prior
		// therefore not changing it at all
		if (this.lastNames[i].isBlank() && this.lastNames[i].contains(" ")) {
			this.gtConsole.println("The last name you entered is not valid\nand has been reset to initial value");
			this.lastNames[i] = temp;
		}
		// prints an output in the console gterm to show the new value that has been
		// entered
		this.gtConsole.println("The new value is :" + lastNames[i]);
		// calling refresh table method
		refreshTable();

	}

	public void editHourlyRate() {
		// gets the index from the table select method
		int i = tableSelect();
		// clears console
		this.gtConsole.clear();
		// prints to console the selection the user had made so they can see former
		// value in case they selected wrong they can change back
		this.gtConsole.println("You just edited " + lastNames[i] + " hourly rate :" + hourlyRates[i]);
		// creates a temp value incase the user enters a value outside the parameters
		float temp = this.hourlyRates[i];
		// gathers the input from the second text field in the main gterm window and
		// allocates it as seen fit by the code
		this.hourlyRates[i] = Float.parseFloat(this.gtMain.getTextFromEntry(1));
		// if the user entered an invalid choice instead of changing to the new value
		// from the text field it will reset it to the temp variable created prior
		// therefore not changing it at all
		if (this.hourlyRates[i] < 16 || this.hourlyRates[i] > 199) {
			this.gtConsole.println("The Hourly Rate you entered is not valid\nand has been reset to initial value");
			this.hourlyRates[i] = temp;
		}
		// prints an output in the console gterm to show the new value that has been
		// entered
		this.gtConsole.println("The new value is :" + hourlyRates[i]);
		// calling refresh table method
		refreshTable();
	}

	public void editHours() {
		// gets the index from the table select method
		int i = tableSelect();
		// clears console
		this.gtConsole.clear();
		// prints to console the selection the user had made so they can see former
		// value in case they selected wrong they can change back
		this.gtConsole.println("You just edited " + lastNames[i] + " hours :" + hours[i]);
		// creates a temp value incase the user enters a value outside the parameters
		short temp = this.hours[i];
		// gathers the input from the second text field in the main gterm window and
		// allocates it as seen fit by the code
		this.hours[i] = Short.parseShort(this.gtMain.getTextFromEntry(1));
		// if the user entered an invalid choice instead of changing to the new value
		// from the text field it will reset it to the temp variable created prior
		// therefore not changing it at all
		if (this.hours[i] < 0 && this.hours[i] > 112) {
			this.gtConsole.println("The Hours you entered is not valid\nand has been reset to initial value");
			this.hours[i] = temp;
		}
		// prints an output in the console gterm to show the new value that has been
		// entered
		this.gtConsole.println("The new value is :" + hours[i]);
		// calling refresh table method
		refreshTable();
	}

	public void remove() {
		// clears the gterm console
		this.gtConsole.clear();
		// gets the user element to remove from the user selection method
		int i = tableSelect();
		int j = 0;
		int k = 0;
		this.gtConsole.println("You have chosen to remove " + firstNames[i] + "." + lastNames[i]);
		// the following condtion is if the selection element +1 is same as current
		// staff counter which would occur if the user selected the last entry made to
		// be removed it decreases the counter and doesnt have to move the arrays around
		if (i + 1 == this.currentStaff) {
			this.currentStaff--;
			// this condition would occur when the selection is somewhere other then the end
		} else {
			// will transfer the arrays in term of element spot to element spot making a
			// copy of array unless the following if statement is met
			while (j < this.currentStaff) {
				// if the user selection matches the counter it will increase the counter on the
				// rhs in the following copy overs which in turn skips over the element that is
				// wished to be deleted
				if (j == i) {
					j++;
				}
				this.firstNames[k] = this.firstNames[j];
				this.lastNames[k] = this.lastNames[j];
				this.hourlyRates[k] = this.hourlyRates[j];
				this.hours[k] = this.hours[j];
				j++;
				k++;
			}
			// decreases counter of staff as 1 would have been removed in the above
			// statements
			this.currentStaff--;
		}
		// creation and initializing of new temp arrays that match up to how many
		// entries are left over
		this.arraySize = this.currentStaff;
		char[] shorterFirstNames = new char[this.arraySize];
		String[] shorterLastNames = new String[this.arraySize];
		float[] shorterHourlyRate = new float[this.arraySize];
		short[] shorterHours = new short[this.arraySize];
		// counter reset to be used over again
		j = 0;
		// transfers the elemental data from the existing arrays to the shorter temp
		// ones
		while (j < this.currentStaff) {
			shorterFirstNames[j] = this.firstNames[j];
			shorterLastNames[j] = this.lastNames[j];
			shorterHourlyRate[j] = this.hourlyRates[j];
			shorterHours[j] = this.hours[j];
			j++;
		}
		// creates the original arrays back from the temp ones so the originals now have
		// shorter size so that in retrospect will never have null values or data still
		// stored in an element awaiting a rewrite arrays will only be of size that is
		// needed
		this.firstNames = shorterFirstNames;
		this.lastNames = shorterLastNames;
		this.hourlyRates = shorterHourlyRate;
		this.hours = shorterHours;
		// calls refresh table method
		refreshTable();
	}

	// method to return a double which takes parameters in the case of this program
	// the parameters are coded in from a loop in refreshtable that incriments
	// depending on what element needs to be worked out
	public double overtime(double rate, short hours) {
		// initialize the return value
		double overtime = 0;
		// conditional check if hours are greater than the normal rate hours the
		// overtime is calculated
		if (hours > 38) {
			overtime = (rate * 1.5) * (hours - 38);
		}
		// returns overtime if condition was not met returns 0 however if condition met
		// returns the calculated overtime
		return overtime;
	}

	// method to return a double for the normal rate of pay parameters are from
	// refreshtable incrementation to align with the staff member to be calculated
	public double normalPay(double rate, short hours) {
		// initialize normal pay to 0
		double normalPay = 0;
		// condition if hours exceed 38 will calculate the hours at max value 38
		if (hours > 38) {
			normalPay = rate * 38;
			// if former condition not met the rate is calculated normally with a simple
			// equation can use else as no other conditions to contribute
		} else {
			normalPay = rate * hours;
		}
		// returns the pay figure from one of the above conditions
		return normalPay;
	}

	public void refreshTable() {
		// clears all data that has been input into table so can start again
		this.gtTable.clearRowsOfTable(0);
		// initialize counter
		int i = 0;
		// initialize a double to add totals together
		double runningTotal = 0;
		// while loop to add a row for each matching elements untill the amount of staff
		// has been reached
		while (i < this.currentStaff) {
			// calls on method normal pay with the parameters given to give value of
			// normalpay from the returned value
			double normalPay = normalPay(this.hourlyRates[i], this.hours[i]);
			// calls method overtime with the parameters given to give valus of overtime
			// from the returned value
			double overtime = overtime(this.hourlyRates[i], this.hours[i]);
			// creates a double value that adds both former doubles to be set as a total for
			// use in table
			double total = overtime + normalPay;
			// increases the funning total by the total given in former double so a complete
			// total of values can e shown
			runningTotal = runningTotal + total;
			// adds a line for every element that is available split into columns also adds
			// the above values from return values and totals to the table to be shown will
			// recalculate after edits removes and additions
			this.gtTable.addRowToTable(0, i + 1 + "\t" + this.firstNames[i] + "\t" + this.lastNames[i] + "\t"
					+ this.hourlyRates[i] + "\t" + this.hours[i] + "\t" + normalPay + "\t" + overtime + "\t" + total);
			// counter to move to next row of table untill the condition of how many staff
			// is reached
			i += 1;
		}
		// adds the running total to the table once the while loop has been completed
		// showing a total of all totals
		this.gtTable.addRowToTable(0, "\t\t\t\t\t\tTotal\t" + runningTotal);
	}

	// a little jazz added to the program to show developer information and version
	// of gterm
	public void info() {
		// creates new gterm with set colors and fonts given
		GTerm info = new GTerm(375, 500);
		info.setXY(30, 2);
		info.setBackgroundColor(Color.BLACK);
		info.setFont(null, Font.ITALIC, 16);
		info.setFontColor(Color.GREEN);
		// adds text to the window i know i could use the set tab to increase the spaces
		// or use set xy but found it more intrumental to keep as is and line up this
		// way
		info.println("This was brought to you by Channon Harper");
		info.println("\t\t\t\t\t\t\ts3871491");
		info.println("\t\t\t\tI hope you enjoyed it :D");
		info.setXY(60, 100);
		info.addImageIcon("bblogo.png");
		// set to bottom of window and adds the version of gterm used
		info.setXY(2, 350);
		info.showHelp();
	}

	// method to close all windows save yourself 3 clicks :D or moving the window to
	// press the red stop button
	public void exit() {
		this.gtConsole.close();
		this.gtTable.close();
		this.gtMain.close();
	}

	// the main main which runs the program
	public static void main(String[] args) {
		PayRateProgram a2obj = new PayRateProgram();

	}

}
// chan,harps,28,38