import java.util.Scanner;

public class FrontEndConsole {
	// creation of backend of scanner
	BackEnd be;
	private Scanner recordScan;

	// constructor
	public FrontEndConsole(BackEnd backEnd) {
		// initialize backend
		be = backEnd;
		System.out.println("Welcome to Staff record keeper 2.0");
		// calls console method
		console();
	}

	public void conAddRecord() {
		// adds record for the console version calls 2 methods from the backend 1 using
		// the input from console
		System.out.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		be.setInputs(this.recordScan.next());
		be.addRecord();

	}

	// the mainish method could of put in constuctor but changed coding to suit
	// Gayans otherwise would of used mine from iie07 to recall the method in the
	// method which is cool
	public void console() {
		String input;
		this.recordScan = new Scanner(System.in);
		String menu = "Staff Rates Console Version 1.0\n";
		menu += "[A]dd Record\n";
		menu += "[E]dit Record\n";
		menu += "[D]elete Record\n";
		menu += "E[x]it\n";
		System.out.println(menu);
		System.out.print("Enter Choice");
		input = this.recordScan.next();
		// must have a input other than x
		while (!input.equalsIgnoreCase("x")) {
			// calls method to add
			if (input.equalsIgnoreCase("a"))
				conAddRecord();
			// calls method to edit
			else if (input.equalsIgnoreCase("e"))
				be.edit();
			// calls method to remove
			else if (input.equalsIgnoreCase("d"))
				be.remove();
			// picks up on any other inputs other than a,e,d,x
			else if (!input.equalsIgnoreCase("x"))
				System.out.println("invalid choice");
			// calls method to print out data given
			conRefresh();
			// restarts from the start
			System.out.println(menu);
			System.out.print("Enter Choice");
			input = this.recordScan.next();

		}
	}

	public void conRefresh() {
		// refreshes the console version same principal as refreshtable also uses the
		// backend methods to give the data from the arrays to print
		int i = 0;
		while (i < be.getStaff()) {
			System.out.println("Staff Member " + (i + 1) + ": " + be.getname()[i] + " " + be.getgender()[i] + " "
					+ be.getbirth()[i] + " " + be.getrate()[i] + " " + be.getterm()[i]);
			i += 1;
		}
	}

}
