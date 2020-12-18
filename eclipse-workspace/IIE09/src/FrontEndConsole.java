import java.util.Scanner;

public class FrontEndConsole {
	BackEnd be;
	private Scanner recordScan;

	public FrontEndConsole(BackEnd backEnd) {
		// initialize the backend
		be = backEnd;
		this.recordScan = new Scanner(System.in);
		System.out.println("Welcome to Staff record keeper 2.0");
		console();
	}

	public void edit() {
		// asks for selection of staff member
		System.out.println("Which Staff member");
		int selection = this.recordScan.nextInt();
		System.out.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		// creating a array from the inputs in scanner
		String[] inputData = this.recordScan.next().split(",");
		// boolean for validation without needing to use while loops
		boolean success = true;
		String name = inputData[0];
		// validation will change boolean to false if does not meet requirements is same
		// for all data inputs
		if (name.isBlank()) {
			System.out.println("Error with name input cannot be blank");
			success = false;
		}
		char gender = inputData[1].charAt(0);
		if (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			System.out.println("Error with gender input must be M or F");
			success = false;
		}
		int birthYear = Integer.parseInt(inputData[2]);
		if (birthYear < 1900) {
			System.out.println("Error with birth year input must be greater than 1900");
			success = false;
		}
		double hourlyRate = Double.parseDouble(inputData[3]);
		if (hourlyRate < 15.00 || hourlyRate > 99) {
			System.out.println("Error with hourly rate input must be between 15 and 99");
			success = false;
		}
		Boolean term = Boolean.parseBoolean(inputData[4]);
		if (!term) {
			System.out.println("Error with term input must be True");
			success = false;
		} // if all the inputs are validated to be true then will call the method from
			// backend to edit
		if (success) {
			be.edit(selection, name, gender, birthYear, hourlyRate, term);
		} // otherwise it prints the line which will be under any println that didnt meet
			// requirements
		else {
			System.out.println("Please check above error messages");
		}
	}

	public void conAddRecord() {
		// all the same as the edit method untill end
		System.out.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		String[] inputData = this.recordScan.next().split(",");
		boolean success = true;
		String name = inputData[0];
		if (name.isBlank()) {
			System.out.println("Error with name input cannot be blank");
			success = false;
		}
		char gender = inputData[1].charAt(0);
		if (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			System.out.println("Error with gender input must be M or F");
			success = false;
		}
		int birthYear = Integer.parseInt(inputData[2]);
		if (birthYear < 1900) {
			System.out.println("Error with birth year input must be greater than 1900");
			success = false;
		}
		double hourlyRate = Double.parseDouble(inputData[3]);
		if (hourlyRate < 15.00 || hourlyRate > 99) {
			System.out.println("Error with hourly rate input must be between 15 and 99");
			success = false;
		}
		Boolean term = Boolean.parseBoolean(inputData[4]);
		if (!term) {
			System.out.println("Error with term input must be True");
			success = false;
		} // calls add record method in backend if all is validated and success is still
			// true
		if (success) {
			be.addRecord(name, gender, birthYear, hourlyRate, term);
		} else {
			System.out.println("Please check above error messages");
		}
	}

	public void console() {
		// creation of the console as you can see has all the selections and calls
		// methods depending on what selection is made by user
		String input;
		String menu = "Staff Rates Console Version 1.0\n";
		menu += "[A]dd Record\n";
		menu += "[E]dit Record\n";
		menu += "[D]elete Record\n";
		menu += "E[x]it\n";
		System.out.println(menu);
		System.out.print("Enter Choice");
		input = this.recordScan.next();
		while (!input.equalsIgnoreCase("x")) {
			if (input.equalsIgnoreCase("a")) {
				conAddRecord();
			} else if (input.equalsIgnoreCase("e")) {
				System.out.println("enter which staff member");
				edit();
			} else if (input.equalsIgnoreCase("d")) {
				System.out.println("Which staff member");
				be.remove(this.recordScan.nextInt());
			} else if (!input.equalsIgnoreCase("x")) {
				System.out.println("invalid choice");
			}
			conRefresh();
			System.out.println(menu);
			System.out.print("Enter Choice");
			input = this.recordScan.next();

		}
	}
	// prints the data of the array in backend by the use of class staff to seperate
	public void conRefresh() {
		int i = 0;
		while (i < be.getStaff()) {
			System.out.println("Staff Member " + (i + 1) + ": " + be.getOutputs(i).getName() + "\t"
					+ be.getOutputs(i).getGender() + "\t" + be.getOutputs(i).getBirthYear() + "\t"
					+ be.getOutputs(i).getHourlyRate() + "\t" + be.getOutputs(i).getTerm());
			i += 1;
		}
	}

}
