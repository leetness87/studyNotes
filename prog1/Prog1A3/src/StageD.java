
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StageD {

	private static Scanner scan;
	private static ArrayList<Attraction> attractions;
	private static int counter;
	private static final String savefile = "data.txt";

	// Re-usable menu to be reloaded at end of each completed method until exited
	public static void menu() {
		int menuSelection = 0;
		String menu = "**Main Menu**\n";
		menu += "1: Add attraction\n";
		menu += "2: Remove attraction\n";
		menu += "3: View all attractions\n";
		menu += "4: View attraction details\n";
		menu += "5: Sell tickets\n";
		menu += "6: Refund tickets\n";
		menu += "7: Exit Program\n";
		System.out.println(menu);
		System.out.print("Enter Choice\n");
		menuSelection = Integer.parseInt(scan.nextLine());
		while (menuSelection < 1 || menuSelection > 7) {
			System.out.println("Error in choice\nPlease choose again");
			menuSelection = Integer.parseInt(scan.nextLine());
		}
		if (menuSelection == 1) {
			addAttraction();
		} else if (menuSelection == 2) {
			removeAttraction();
		} else if (menuSelection == 3) {
			details();
		} else if (menuSelection == 4) {
			fullDetails();
		} else if (menuSelection == 5) {
			sellTickets();
		} else if (menuSelection == 6) {
			refundTickets();
		} else if (menuSelection == 7) {
			save(savefile);
			System.out.print("Have a nice day :)");
			System.exit(0);

		}
	}

	// attempt to refund tickets if criteria is met
	private static void refundTickets() {
		System.out.println("");
		System.out.println("Please enter Attraction ID for refund:");
		int input = Integer.parseInt(scan.nextLine());
		int k = search(input);
		System.out.println("How many Tickets:");
		int tickets = Integer.parseInt(scan.nextLine());
		try {
			attractions.get(k).refundTickets(tickets);
			System.out.format("+--------------+----------------+----------------+----------------+%n");
			System.out.format("| AttractionID |  Ticket Cost   | Tickets Refund |  Total Refund  |%n");
			System.out.format("+--------------+----------------+----------------+----------------+%n");
			attractions.get(k).receipt(tickets);
			System.out.format("+--------------+----------------+----------------+----------------+%n");
			System.out.println("");
		} catch (TicketException error) {
			System.out.println(error.getMessage());
		}
		menu();
	}

	// attempt to sell tickets if criteria is met
	private static void sellTickets() {
		System.out.println("");
		System.out.println("Please enter Attraction ID:");
		int input = Integer.parseInt(scan.nextLine());
		int k = search(input);
		System.out.println("How many Tickets:");
		int tickets = Integer.parseInt(scan.nextLine());
		try {
			attractions.get(k).sellTickets(tickets);
		} catch (TicketException error) {
			System.out.println(error.getMessage());
			menu();
		}
		System.out.println("");
		System.out.println("");
		System.out.format("+--------------+----------------+----------------+----------------+%n");
		System.out.format("| AttractionID |  Ticket Cost   |  Tickets Sold  |   Total Cost   |%n");
		System.out.format("+--------------+----------------+----------------+----------------+%n");
		attractions.get(k).receipt(tickets);
		System.out.format("+--------------+----------------+----------------+----------------+%n");
		System.out.println("");
		menu();
	}

	// display all details of a selected attraction by the Unique ID
	private static void fullDetails() {
		System.out.println("");
		System.out.println("Please enter Attraction ID:");
		int input = Integer.parseInt(scan.nextLine());
		int k = search(input);
		System.out.println("");
		System.out.format("+---------------------------------------------+%n");
		System.out.format("|              Attraction Details             |%n");
		System.out.format("+---------------------------------------------+%n");
		attractions.get(k).printFull();
		System.out.format("+---------------------------------------------+%n");
		System.out.println("");
		menu();
	}

	// displays all attractions in a list with there ID and description
	private static void details() {
		int i = 0;
		System.out.println("");
		System.out.format("+---------------------------------------------+%n");
		System.out.format("|              Current Attractions            |%n");
		System.out.format("+---------------------------------------------+%n");
		while (i < counter) {
			attractions.get(i).print();
			System.out.format("+---------------------------------------------+%n");
			i++;
		}
		menu();
	}

	// determine if searched attraction exists return to main if not returns the
	// index number of the list
	public static int search(int k) {
		int i = 0;
		int l = 0;
		boolean exists = false;
		while (i < counter) {
			if (k == attractions.get(i).getAttractionID()) {
				exists = true;
				l = i;
			}
			i++;
		}
		if (!exists) {
			System.out.println("Not found");
			menu();
		}
		return l;
	}

	// removal of a selected attraction from input of Uniqie ID
	private static void removeAttraction() {
		System.out.println("");
		System.out.println("Please enter Attraction ID to remove:");
		int input = Integer.parseInt(scan.nextLine());
		int k = search(input);
		attractions.remove(k);
		counter--;
		menu();
	}

	// user input and creates attraction given the inputs
	private static void addAttraction() {
		String description;
		double cost;
		int selection;
		String activities = "";
		String instructions = "";
		String input = "";
		System.out.println("You are now adding a Attraction please follow the prompts:");
		System.out.println("Will this be 1) Supervised or 2) Unsupervised:");
		selection = Integer.parseInt(scan.nextLine());
		while (selection < 1 || selection > 2) {
			System.out.println("Will this be 1) Supervised or 2)Unsupervised:");
			selection = Integer.parseInt(scan.nextLine());
		}
		System.out.println("Please enter desciption:");
		description = scan.nextLine().toUpperCase();
		System.out.println("Enter ticket cost:");
		cost = Double.parseDouble(scan.nextLine());
		if (selection == 1) {
			System.out.println("Please enter max group size:");
			int grpSize = Integer.parseInt(scan.nextLine());
			System.out.println("Please enter list of activities:\nLeave blank and enter when finished");
			while (scan.hasNextLine()) {
				input = scan.nextLine();
				if (input.isBlank()) {
					break;
				} else {
					input += "\n";
				}
				activities += input;
			}
			Attraction temp = new SupervisedTour(Attraction.attID, description, cost, 0, grpSize, activities);
			attractions.add(temp);
		} else if (selection == 2) {
			System.out.println("Please enter difficulty rating 1-10:");
			int diffRating = Integer.parseInt(scan.nextLine());
			System.out.println("Please enter Instruction guide:\nLeave blank and enter when finished");
			while (scan.hasNextLine()) {
				input = scan.nextLine() + "\n";
				if (input.isBlank()) {
					break;
				}
				instructions += input;
			}
			Attraction temp = new UnsupervisedTour(Attraction.attID, description, cost, 0, diffRating, instructions);
			attractions.add(temp);
		}
		counter++;
		Attraction.attID++;
		menu();

	}

	// Save functionality Stage D prints to txt file with a line seperating each
	// attraction
	public static void save(String filename) {
		PrintWriter pw = null;
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			pw = new PrintWriter(fw);
			int count = 0;
			while (attractions.size() > count) {
				if (UnsupervisedTour.class.isInstance(attractions.get(count))) {
					pw.println("UnsupervisedTour");
					pw.println(((UnsupervisedTour) attractions.get(count)).getAttractionID());
					pw.println(((UnsupervisedTour) attractions.get(count)).getAttDescription());
					pw.println(((UnsupervisedTour) attractions.get(count)).getTicketCost());
					pw.println(((UnsupervisedTour) attractions.get(count)).getTicketsSold());
					pw.println(((UnsupervisedTour) attractions.get(count)).getDifficultyRating());
					pw.println(((UnsupervisedTour) attractions.get(count)).getInstructionGuide());

				} else if (SupervisedTour.class.isInstance(attractions.get(count))) {
					pw.println("SupervisedTour");
					pw.println(((SupervisedTour) attractions.get(count)).getAttractionID());
					pw.println(((SupervisedTour) attractions.get(count)).getAttDescription());
					pw.println(((SupervisedTour) attractions.get(count)).getTicketCost());
					pw.println(((SupervisedTour) attractions.get(count)).getTicketsSold());
					pw.println(((SupervisedTour) attractions.get(count)).getGroupSize());
					pw.println(((SupervisedTour) attractions.get(count)).getActivities());

				}
				count++;
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("could not create file!!!");
		}
	}

	// load functionality Stage D returns the txt file to a working program
	public static void restore(String filepath) {
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(new FileReader("data.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		while (fileScanner.hasNextLine()) {
			String tag = fileScanner.nextLine();
			Attraction tempFile = null;
			if (tag.equals("UnsupervisedTour")) {
				tempFile = new UnsupervisedTour(fileScanner);
			} else if (tag.equals("SupervisedTour")) {
				tempFile = new SupervisedTour(fileScanner);

			}
			attractions.add(tempFile);
			counter++;
			Attraction.attID++;

		}
		fileScanner.close();

	}

	// constructor initializing program
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		attractions = new ArrayList<Attraction>();
		counter = 0;
		Attraction.attID = 1;
		restore(savefile);
		menu();

	}
}
