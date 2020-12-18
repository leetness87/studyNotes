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
		menu += "[L]oad File\n";
		menu += "[S]ave File\n";
		menu += "E[x]it\n";
		System.out.println(menu);
		System.out.print("Enter Choice");
		input = this.recordScan.next();
		// must have a input other than x
		while (!input.equalsIgnoreCase("x")) {
			// calls method to add
			if (input.equalsIgnoreCase("a"))
				add();
			// calls method to edit
			else if (input.equalsIgnoreCase("e"))
				edit();
			// calls method to remove
			else if (input.equalsIgnoreCase("d"))
				delete();
			// picks up on any other inputs other than a,e,d,x
			else if (input.equalsIgnoreCase("l"))
				be.load();
			else if (input.equalsIgnoreCase("s"))
				be.save();
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
	public void edit() {
		System.out.println("Which staff member do you wish to edit?");
		int k = this.recordScan.nextInt();
		System.out.println("Enter new first name, last name, hourly rate, hours worked\nseperated by commas");
		String input = this.recordScan.next();
		be.editRecord(k, input);
	}
	public void add() {
		System.out.println("Enter first name, last name, hourly rate, hours worked\nseperated by commas");
		String input = this.recordScan.next();
		be.addRecord(input);
	}
	public void delete() {
		System.out.println("Which staff member do you wish to delete?");
		int k = this.recordScan.nextInt();
		be.deleteRecord(k);
	}

	public void conRefresh() {
		// refreshes the console version same principal as refreshtable also uses the
		// backend methods to give the data from the arrays to print
		int i = 0;

	}

}
