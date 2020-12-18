import java.awt.Color;
import java.awt.Font;

public class FrontEndGTerm {
	// creating backend and gterm windows
	BackEnd be;
	private GTerm gtMain;
	private GTerm gtTable;
	private GTerm gtConsole;

	public FrontEndGTerm(BackEnd backEnd) {
		// initialize the backend
		be = backEnd;
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
		this.gtMain.println("Welcome to Wage Calculator 3.0");
		this.gtMain.println("Please make your selection\nfrom the available buttons");
		// reduction in font size to differ the header to the text
		this.gtMain.setFontSize(12);
		// the following adds the buttons to the main gterm with each of them having
		// there own seperate methods that are called for in between is println's for
		// spacing purposes
		this.gtMain.addButton("Add Record", this, "addRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Edit Record", this, "editRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Delete Record", this, "deleteRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Load File", this, "load");
		this.gtMain.println("");
		this.gtMain.addButton("Save File", this, "save");
		this.gtMain.println("");
		this.gtMain.addButton("Refresh Table", this, "refreshTable");
		this.gtMain.println("");
		this.gtMain.addButton("Exit", this, "exit");
		this.gtMain.println("");
		// increase in font size again for the textfield to be larger then menu select
		// making it stick out more
		this.gtMain.setFontSize(16);
		this.gtMain.println("Enter First Name, Last name, Hourly rate , Hours\nSeperate by a commas");
		// adds a text field to the bottom of the window
		this.gtMain.addTextField("Enter Text Here", 400);
		this.gtMain.println("");
		this.gtMain.println("Enter in values here to edit selection seperated by commas\n");
		this.gtMain.addTextField("", 200);
		// the following adds the table to the gterm table also setting font size and
		// xy to make most of sizing
		this.gtTable.setXY(1, 1);
		this.gtTable.setFontSize(14);
		this.gtTable.addTable(870, 700, "Index\tFirst name\tLastname\tHourly Rate\tHours\tNormal Pay\tOvertime\tTotal");
		this.gtTable.println("");
		//display version of GTerm in use
		this.gtTable.showHelp();
	}

	// method to add a record
	public void addRecord() {
		// creates a string input from the first created text field
		String input = this.gtMain.getTextFromEntry(0);
		// creates a array from the input string splitting the inputs with the use of a
		// comma
		String rawData[] = input.split(",");
		// creates a string from the first element of input
		String firstName = rawData[0];
		// clears console to show any error messages that may be invoked
		this.gtConsole.clear();
		// conditional check on first name while loop continues until condition is met
		while (firstName.isBlank()) {
			// prints to console window a message
			this.gtConsole.println("Names can not be blank");
			// overrides the first name with new input that is within the scope
			firstName = this.gtMain.getInputString("Enter first name");
		}
		// creates string from second element of input
		String lastName = rawData[1];
		// conditional check on last name while loop continues until condition is met
		while (lastName.isBlank()) {
			// prints to console window a message
			this.gtConsole.println("Names can not be blank");
			// overrides the last name with new input that is within the scope
			lastName = this.gtMain.getInputString("Enter last name");
		}
		// creates a double value from the 3rd element of input
		double hourlyRate = Double.parseDouble(rawData[2]);
		// conditional check on hourlyrate while loop continues until condition is met
		while (hourlyRate < 16 || hourlyRate > 199) {
			// prints to console window a message
			this.gtConsole.println("hourly rate must be between 16 and 199");
			// overrides the hourly rate with new one that is within the scope
			hourlyRate = Double.parseDouble(this.gtMain.getInputString("enter hourly rate"));
		}
		// creates a double value from the 4th element of input
		double hours = Double.parseDouble(rawData[3]);
		// conditional check on hours while loop continues until condition is met
		while (hours < 0 || hours > 112) {
			// prints to console window a message
			this.gtConsole.println("hours must be between 0 and 112");
			// overrides hours with new one that meets the scope of condition
			hours = Double.parseDouble(this.gtMain.getInputString("enter hours"));
		}
		// overrides the initial input once all conditions are met so can be added as a
		// string for easy print outs
		input = firstName + "," + lastName + "," + hourlyRate + "," + hours;
		// calls backend method to add record with the created variables
		be.addRecord(firstName, lastName, hourlyRate, hours, input);
		// refreshes the table
		refreshTable();

	}

	// method to edit
	public void editRecord() {
		// creates an int from a selection of the table
		int k = this.gtTable.getIndexOfSelectedRowFromTable(0);
		// creates a string input from the second created text field
		String input = this.gtMain.getTextFromEntry(1);
		// creates a array from the input string splitting the inputs with the use of a
		// comma
		String rawData[] = input.split(",");
		// creates a string from the first element of input
		String firstName = rawData[0];
		// clears console to show any error messages that may be invoked
		this.gtConsole.clear();
		// conditional check on first name while loop continues until condition is met
		while (firstName.isBlank()) {
			// prints to console window a message
			this.gtConsole.println("Names can not be blank");
			// overrides the first name with new input that is within the scope
			this.gtMain.getInputString("Enter first name");
		}
		// creates string from second element of input
		String lastName = rawData[1];
		// conditional check on last name while loop continues until condition is met
		while (lastName.isBlank()) {
			// prints to console window a message
			this.gtConsole.println("Names can not be blank");
			// overrides the last name with new input that is within the scope
			lastName = this.gtMain.getInputString("Enter last name");
		}
		// creates a double value from the 3rd element of input
		double hourlyRate = Double.parseDouble(rawData[2]);
		// conditional check on hourlyrate while loop continues until condition is met
		while (hourlyRate < 16 || hourlyRate > 199) {
			// prints to console window a message
			this.gtConsole.println("hourly rate must be between 16 and 199");
			// overrides the hourly rate with new input that is within the scope
			hourlyRate = Double.parseDouble(this.gtMain.getInputString("enter hourly rate"));
		}
		// creates a double value from the 4th element of input
		double hours = Double.parseDouble(rawData[3]);
		// conditional check on hours while loop continues until condition is met
		while (hours < 0 || hours > 112) {
			// prints to console window a message
			this.gtConsole.println("hours must be between 0 and 112");
			// overrides the hours with new input that is within the scope
			hours = Double.parseDouble(this.gtMain.getInputString("enter hours"));
		}
		// overrides the initial input once all conditions are met so can be added as a
		// string for easy print outs
		input = firstName + "," + lastName + "," + hourlyRate + "," + hours;
		// calls backend method to edit record with the created variables
		be.editRecord(k, firstName, lastName, hourlyRate, hours, input);
		// refreshes the table
		refreshTable();
	}

	// method to remove
	public void deleteRecord() {
		// creates an int from a selection of the table
		int k = this.gtTable.getIndexOfSelectedRowFromTable(0);
		// transfers the elemental value to one that would be index value
		k = (k + 1);
		// calls backend method to delete the record with the variable set above
		be.deleteRecord(k);
		// calls refresh table
		refreshTable();
	}

	// method to load data file
	public void load() {
		// creates a string from user input of get file path
		String path = this.gtMain.getFilePath();
		// calls backend method with the above created string
		be.load(path);
		// refresh table cause you know always need to
		refreshTable();
	}

	// method to save a file
	public void save() {
		// creates a string from user input of set file path
		String path = this.gtMain.setFilePath();
		// if statement as be save is boolean will show save successful using the above
		// string as a variable for the method
		if (this.be.save(path)) {
			this.gtConsole.clear();
			this.gtConsole.println("Save Successful");
			// if something wnet wrong the following error will show
		} else {
			this.gtConsole.clear();
			this.gtConsole.println("ERROR 100001000001");
		}
	}

	// the usual refresh table with a slight variance that i am using the backend
	// methods to give me the array data i need to print
	public void refreshTable() {
		this.gtConsole.clear();
		this.gtConsole.println("Table refreshed");
		// clearing the table for fresh print
		this.gtTable.clearRowsOfTable(0);
		// counter initialized to 0
		int i = 0;
		// allows access to staff class through the backend to access the data needed
		Staff[] staff = be.getStaff(i);
		// while loop to print untill counter has reached total records in the backend
		// data
		while (i < be.getRecord()) {
			// use of backend method to give a value
			double normalPay = be.normalPay(i);
			// use of backend method to give a value
			double overtime = be.overtime(i);
			// creates a double value that adds both former doubles to be set as a total
			double total = overtime + normalPay;
			// adds row to table instead of grabbing every element i used the saved raw data
			// string to print plus the extra created above to give the last of the
			// requirements
			this.gtTable.addRowToTable(0, (i + 1) + "\t" + staff[i].getRawData().replace(",", "\t") + "\t" + normalPay
					+ "\t" + overtime + "\t" + total);
			//counter increase
			i++;

		}

	}
	// method to exit if you want easy way to just use console without the gterms up
	public void exit() {
		this.gtConsole.close();
		this.gtTable.close();
		this.gtMain.close();
	}
}
