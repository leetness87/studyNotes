import java.util.Scanner;

public class FrontEndConsole {
	// creation of backend of scanner
	BackEnd be;
	private Scanner recordScan;

	// constructor
	public FrontEndConsole(BackEnd backEnd) {
		// initialize backend
		be = backEnd;
		System.out.println("Welcome to Staff record keeper 3.0");
		// calls console method
		console();
	}

	// the heart and soul of console
	public void console() {
		// initialize string could do later but decided to have here
		String input;
		// initialize of scanner
		this.recordScan = new Scanner(System.in);
		// creation of string that contains the main menu saved as string for easy
		// access and repeat use
		String menu = "Staff Rates Console Version 3.0\n";
		menu += "[A]dd Record\n";
		menu += "[E]dit Record\n";
		menu += "[D]elete Record\n";
		menu += "[L]oad File\n";
		menu += "[S]ave File\n";
		menu += "[R]efresh\n";
		menu += "E[x]it\n";
		// prints the menu string
		System.out.println(menu);
		// adds a line to show user to enter something
		System.out.print("Enter Choice");
		// takes input from user to be used
		input = this.recordScan.next();
		// must have a input other than x
		while (!input.equalsIgnoreCase("x")) {
			// calls method to add
			if (input.equalsIgnoreCase("a")) {
				add();
			}
			// calls method to edit
			else if (input.equalsIgnoreCase("e")) {
				edit();
			}
			// calls method to remove/delete
			else if (input.equalsIgnoreCase("d")) {
				delete();
			}
			// load input
			else if (input.equalsIgnoreCase("l")) {
				// prints line as to show user the default file name
				System.out.println("enter file name to open default is data.txt");
				// creates string from input gives the user a choice of file to load if known
				String load = this.recordScan.next();
				// calls backend load with the string load
				be.load(load);
				// save input
			} else if (input.equalsIgnoreCase("s")) {
				// prints line to shows user the default file name
				System.out.println("enter file name to save default is data.txt");
				// creates string from input the user can create any file the wish aslong as
				// file type is relavent
				String save = this.recordScan.next();
				// calls the save method which is a boolean that is why the if else uses the
				// string created from user to create or override a file
				if (this.be.save(save)) {
					System.out.println("Save Successful");
				} else {
					System.out.println("ERROR 100001000001");
				}
				// refreshs the console if inputting into gterm and want to see it in console
				// this is required
			} else if (input.equalsIgnoreCase("r")) {
				System.out.println("You have chosen to refresh");
				// picks up on any other inputs other than a,e,d,x
			} else if (!input.equalsIgnoreCase("x")) {
				System.out.println("invalid choice");
			}
			// calls method to print out data given after every selection made
			conRefresh();
			// restarts from the start
			System.out.println(menu);
			System.out.print("Enter Choice");
			input = this.recordScan.next();

		}
	}

	// method to edit
	public void edit() {
		System.out.println("Which staff member do you wish to edit?");
		// creates an int from a input by the user
		int k = this.recordScan.nextInt();
		// validates the input to be in range if not prints out the error and program
		// starts over
		if (k > be.getRecord() || k < 0) {
			System.out.println("The selection you made was invalid please try again");
		} else {
			// makes the user selection an elemental value
			k = (k - 1);
			System.out.println("Enter new first name, last name, hourly rate, hours worked\nseperated by commas");
			// creates a string input from the users entered data
			String input = this.recordScan.next();
			// creates a array from the input string splitting the inputs with the use of a
			// comma
			String rawData[] = input.split(",");
			// creates a string from the first element of input
			String firstName = rawData[0];
			// conditional check on first name while loop continues until condition is met
			while (firstName.isBlank()) {
				System.out.println("Names can not be blank\nEnter value for first name");
				// overrides the first name with new input that is within the scope
				firstName = this.recordScan.next();
			}
			// creates string from second element of input
			String lastName = rawData[1];
			// conditional check on last name while loop continues until condition is met
			while (lastName.isBlank()) {
				System.out.println("Names can not be blank\nEnter value for last name");
				// overrides the last name with new input that is within the scope
				lastName = this.recordScan.next();
			}
			// creates a double value from the 3rd element of input
			double hourlyRate = Double.parseDouble(rawData[2]);
			// conditional check on hourlyrate while loop continues until condition is met
			while (hourlyRate < 16 || hourlyRate > 199) {
				System.out.println("Hourly Rate must be between 16 and 199");
				// overrides the hourly rate with new input that is within the scope
				hourlyRate = this.recordScan.nextDouble();
			}
			// creates a double value from the 4th element of input
			double hours = Double.parseDouble(rawData[3]);
			// conditional check on hours while loop continues until condition is met
			while (hours < 0 || hours > 112) {
				System.out.println("Hours must be between 0 and 112");
				// overrides the hours with new input that is within the scope
				hours = this.recordScan.nextDouble();
			}
			// overrides the initial input once all conditions are met so can be added as a
			// string for easy print outs
			input = firstName + "," + lastName + "," + hourlyRate + "," + hours;
			// calls backend method to edit record with the created variables
			be.editRecord(k, firstName, lastName, hourlyRate, hours, input);
		}

	}

	// method to add
	public void add() {
		System.out.println("Enter first name, last name, hourly rate, hours worked\nseperated by commas");
		// creates a string input from the users entered data
		String input = this.recordScan.next();
		// creates a array from the input string splitting the inputs with the use of a
		// comma
		String rawData[] = input.split(",");
		// creates a string from the first element of input
		String firstName = rawData[0];
		// conditional check on first name while loop continues until condition is met
		while (firstName.isBlank()) {
			System.out.println("Names can not be blank\nEnter value for first name");
			// overrides the first name with new input that is within the scope
			firstName = this.recordScan.next();
		}
		// creates string from second element of input
		String lastName = rawData[1];
		// conditional check on last name while loop continues until condition is met
		while (lastName.isBlank()) {
			System.out.println("Names can not be blank\nEnter value for last name");
			// overrides the last name with new input that is within the scope
			lastName = this.recordScan.next();
		}
		// creates a double value from the 3rd element of input
		double hourlyRate = Double.parseDouble(rawData[2]);
		// conditional check on hourlyrate while loop continues until condition is met
		while (hourlyRate < 16 || hourlyRate > 199) {
			System.out.println("Hourly Rate must be between 16 and 199");
			// overrides the hourly rate with new one that is within the scope
			hourlyRate = this.recordScan.nextDouble();
		}
		// creates a double value from the 4th element of input
		double hours = Double.parseDouble(rawData[3]);
		// conditional check on hours while loop continues until condition is met
		while (hours < 0 || hours > 112) {
			System.out.println("Hours must be between 0 and 112");
			// overrides hours with new one that meets the scope of condition
			hours = this.recordScan.nextDouble();
		}
		// overrides the initial input once all conditions are met so can be added as a
		// string for easy print outs
		input = firstName + "," + lastName + "," + hourlyRate + "," + hours;
		// calls backend method to add record with the created variables
		be.addRecord(firstName, lastName, hourlyRate, hours, input);

	}

	// method to delete
	public void delete() {
		System.out.println("Which staff member do you wish to delete?");
		// creates an int from a input by the user
		int k = this.recordScan.nextInt();
		// validates the input to be in range if not prints out the error and program
		// starts over
		if (k > be.getRecord() || k < 0) {
			System.out.println("You selection was invalid please try again");
		}
		// calls delete method with the variable created
		be.deleteRecord(k);
	}

	// method to refresh
	public void conRefresh() {
		// prints top line seperated with tab spacings
		System.out.println("Index\tFirst\t\tLast\t\tRate\t\tHours\t\tnorm\tOT\ttotal");
		// counter initialization
		int i = 0;
		// allows access to staff class through the backend to access the data needed
		Staff[] staff = be.getStaff(i);
		// while loop to print untill counter has reached total records in the backend
		// data
		while (i < be.getRecord()) {
			// use of backend method to give a value
			double normalPay = be.normalPay(i);
			// use of backend method to give a value
			double overtime = be.normalPay(i);
			// creates a double value that adds both former doubles to be set as a total
			double total = overtime + normalPay;
			// prints line to console using the saved string rawData to make it less
			// problematic has a index value for user to see as well as the newly created
			// doubles for the totals
			System.out.println((i + 1) + "\t" + staff[i].getRawData().replace(",", "\t\t") + "\t\t" + normalPay + "\t"
					+ overtime + "\t" + total);
			//counter increase
			i++;

		}
	}

}
