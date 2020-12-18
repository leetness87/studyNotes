import java.util.Scanner;

public class FrontEndConsole {
	BackEnd be;
	private Scanner recordScan;

	public FrontEndConsole(BackEnd backEnd) {
		// initialize the backend
		be = backEnd;
		this.recordScan = new Scanner(System.in);
		console();

	}

	public void console() {
		// creation of the console as you can see has all the selections and calls
		// methods depending on what selection is made by user
		String input;
		String menu = "Patient listings\n";
		menu += "[A]dd Records\n";
		menu += "[L]ist Records\n";
		menu += "E[x]it\n";
		System.out.println(menu);
		System.out.print("Enter Choice");
		input = this.recordScan.next();
		while (!input.equalsIgnoreCase("x")) {
			if (input.equalsIgnoreCase("a")) {
				be.readFile("diagnosis.data");
				System.out.println("Adding done");
			} else if (input.equalsIgnoreCase("l")) {
				refresh();

			} else if (!input.equalsIgnoreCase("x")) {
				System.out.println("invalid choice");
			}
			System.out.println(menu);
			System.out.print("Enter Choice");
			input = this.recordScan.next();

		}
	}

	public void refresh() {
		int i = 0;
		Patient[] patient = be.getPatient(i);
		while (i < be.getRecord()) {
			System.out.println((i + 1) + "\t" + patient[i].getRawData().replace(",", "\t"));
			i++;
		}
	}
}
