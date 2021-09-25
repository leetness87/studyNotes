
import java.util.Scanner;

public class StageB {
	static int currentIndex;
	private static Scanner scan;
	private static boolean setup;
	static int maxPrintJobs;

	public static void setup() {
		int userInput = 0;
		System.out.println("----Setup Needed----");
		while (userInput == 0) {
			System.out.println("Maximum Queue Needed:");
			userInput = Integer.parseInt(scan.nextLine());
			System.out.println();
			if (userInput <= 0) {
				System.out.println("Error: must be possitive number");
				System.out.println();
			}
		}
		maxPrintJobs = userInput;
		setup = true;
	}

	public static void menu() {
		int menuSelection = 0;
		String menu = "**Main Menu**\n";
		menu += "1: Create Print Job\n";
		menu += "2: Display Queued Jobs\n";
		menu += "3: Search By Print ID\n";
		menu += "4: Remove job from front of Queue\n";
		menu += "5: Exit Program\n";
		System.out.println(menu);
		System.out.print("Enter Choice\n");
		menuSelection = Integer.parseInt(scan.nextLine());
		while (menuSelection < 1 || menuSelection > 5) {
			System.out.println("Error in choice\nPlease choose again");
			menuSelection = Integer.parseInt(scan.nextLine());
		}
		if (menuSelection == 1) {
			addPrintJob();
		} else if (menuSelection == 2) {
			listQueue();
		} else if (menuSelection == 3) {
			searchPrintJob();
		} else if (menuSelection == 4) {
			removePrintJob();
		} else if (menuSelection == 5) {
			System.out.print("Have a nice day :)");
			System.exit(0);

		}
	}

	private static void removePrintJob() {
		PrintQueue.removeJob();
		menu();
	}

	private static void searchPrintJob() {
		int i = 0;
		int input = 0;
		boolean exists = false;
		while (!exists) {
			System.out.println("Please enter the Print ID number\nor press 1 to display all");
			input = Integer.parseInt(scan.nextLine());
			if (input == 1) {
				listQueue();
			}
			if (input != 1) {
				while (input < (PrintJob.printID2 - currentIndex) || input > PrintJob.printID2 - 1) {
					System.out.println("Number is out of range of Jobs, Please try again");
					input = Integer.parseInt(scan.nextLine());
				}
				i = PrintQueue.search(input, exists, currentIndex);

			}
			System.out.println("We have found a match for " + input);
			System.out.println("");
			System.out.format(
					"+-----------+----------------+----------------+----------------+-----------+-----------+-------------+-------------+-------------+%n");
			System.out.format(
					"| Print ID  |  Customer ID   |   Description  |   Material     | Quantity  |   Volume  |  Raw Price  | Total Price |  Processing |%n");
			System.out.format(
					"+-----------+----------------+----------------+----------------+-----------+-----------+-------------+-------------+-------------+%n");
			PrintQueue.displayAll(i);
			System.out.format(
					"+-----------+----------------+----------------+----------------+-----------+-----------+-------------+-------------+-------------+%n");
			System.out.println("");
			menu();
		}
	}

	private static void listQueue() {

		PrintQueue.displayBasic();
		menu();
	}

	private static void addPrintJob() {
		if (currentIndex < maxPrintJobs) {
			int tempInput = 0;
			int matSelect = 0;
			int printID;
			printID = PrintJob.printID2;
			String custID;
			String description;
			String material = null;
			int printQuantity;
			int volume;
			boolean processing1 = false;
			boolean processing2 = false;
			boolean processing3 = false;
			System.out.println("You are now adding a job please follow the prompts:");
			System.out.println("Please enter customer ID:");
			custID = scan.nextLine().toUpperCase();
			System.out.println("Enter brief description:");
			description = scan.nextLine().toUpperCase();
			System.out.println("Which material will be used\n1: PLA\n2: ABS\n3: PLASTIC NYLON\n4: ACRYLIC");
			matSelect = Integer.parseInt(scan.nextLine());
			while (matSelect < 0 || matSelect > 4) {
				System.out.println("error please try again");
				System.out.println("Which material will be used\n1: PLA\n2: ABS\n3: PLASTIC NYLON\n4: ACRYLIC");
				matSelect = Integer.parseInt(scan.nextLine());
			}
			if (matSelect == 1) {
				material = "PLA";
			} else if (matSelect == 2) {
				material = "ABS";
			} else if (matSelect == 3) {
				material = "Plastic Nylon";
			} else if (matSelect == 4) {
				material = "Acrylic";
			}
			System.out.println("Please enter Volume in Cubic Millimeter:");
			tempInput = Integer.parseInt(scan.nextLine());
			while (tempInput <= 0) {
				System.out.println("error please try again must be positive number");
				System.out.println("Please enter Volume in Cubic Millimeter:");
				tempInput = Integer.parseInt(scan.nextLine());
			}
			printQuantity = tempInput;
			System.out.println("Please enter Quantity:");
			tempInput = Integer.parseInt(scan.nextLine());
			while (tempInput <= 0) {
				System.out.println("error please try again must be positive number");
				System.out.println("Please enter Quantity:");
				tempInput = Integer.parseInt(scan.nextLine());
			}
			volume = tempInput;
			if (matSelect != 4) {
				System.out.println("Would you require processing 1: YES 2: NO:");
				tempInput = Integer.parseInt(scan.nextLine());
			}
			if (tempInput == 1 || matSelect == 4) {
				processing(matSelect);
			}

			System.out.println();
			PrintQueue.addJob(printID, custID, description, material, printQuantity, volume);
			PrintJob.printID2++;
			currentIndex++;
			menu();
		} else {
			System.out.println("You have reached the maximum queue");
			menu();
		}
	}

	public static void processing(int matSelect) {
		int tempInput = 0;
		boolean processing1 = false;
		boolean processing2 = false;
		boolean processing3 = false;
		System.out.println("UV curing 1: YES 2: NO:");
		tempInput = Integer.parseInt(scan.nextLine());
		if (tempInput == 1) {
			processing1 = true;
		}
		System.out.println("UV protection  1: YES 2: NO:");
		tempInput = Integer.parseInt(scan.nextLine());
		if (tempInput == 1) {
			processing2 = true;
		}
		System.out.println("Polishing 1: YES 2: NO:");
		tempInput = Integer.parseInt(scan.nextLine());
		if (tempInput == 1) {
			processing3 = true;
		}
		
	}

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		maxPrintJobs = 0;
		currentIndex = 0;
		do {
			setup();
		} while (!setup);
		PrintQueue.initialize(maxPrintJobs);
		menu();

	}
}
