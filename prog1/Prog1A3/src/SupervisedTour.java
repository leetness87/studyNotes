import java.util.Scanner;

public class SupervisedTour extends Attraction {

	private int groupSize;
	private String activities;

	public SupervisedTour(int attractionID, String attDescription, Double ticketCost, int ticketsSold, int groupSize,
			String activities) {
		super(attractionID, attDescription, ticketCost, ticketsSold);
		this.groupSize = groupSize;
		this.activities = activities;
	}

	// Scanner function to load file and reconstruct program
	public SupervisedTour(Scanner fileScanner) {
		super(fileScanner);
		String input = "";
		this.activities = "";
		this.groupSize = Integer.parseInt(fileScanner.nextLine());
		while (fileScanner.hasNextLine()) {
			input = fileScanner.nextLine() + "\n";
			if (input.isBlank()) {
				break;
			}
			this.activities += input;
		}
	}

	// adds the spefics of this class to print out details
	@Override
	public void printFull() {
		super.printFull();
		System.out.println("Group size : " + groupSize);
		System.out.println("Activities include :\n" + activities);
	}

	// extra guidelines for sale of tickets for this class must be less than given
	// group size
	@Override
	public void sellTickets(int numTickets) throws TicketException {

		if (numTickets <= groupSize) {
			super.sellTickets(numTickets);

		} else {
			throw new TicketException("Error!! the number of tickets exceeds the group size!!");
		}
	}

	public int getGroupSize() {
		return groupSize;
	}

	public String getActivities() {
		return activities;
	}

}
