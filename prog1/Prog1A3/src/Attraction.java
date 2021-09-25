import java.util.Scanner;

public class Attraction {
	static int attID;
	private int attractionID;
	private String attDescription;
	private double ticketCost;
	private int ticketsSold;

	// Constructor
	public Attraction(int attractionID, String attDescription, Double ticketCost, int ticketsSold) {
		this.attractionID = attractionID;
		this.attDescription = attDescription;
		this.ticketCost = ticketCost;
		this.ticketsSold = ticketsSold;

	}

	// Scanner function to load file and reconstruct program
	public Attraction(Scanner fileScanner) {
		this.attractionID = Integer.parseInt(fileScanner.nextLine());
		this.attDescription = fileScanner.nextLine();
		this.ticketCost = Double.parseDouble(fileScanner.nextLine());
		this.ticketsSold = Integer.parseInt(fileScanner.nextLine());
		;
	}

	// format of price to 2 decimal place
	public static String printPrice(double input) {
		String price;
		price = new java.text.DecimalFormat("0.00").format(input);
		return price;
	}

	// print for display all attractions
	public void print() {
		System.out.println("Attraction ID :" + this.attractionID);
		System.out.println("Attraction Description :" + this.attDescription);

	}

	// all detail print for specific attraction
	public void printFull() {
		System.out.println("Attraction ID :" + this.attractionID);
		System.out.println("Attraction Description :" + this.attDescription);
		System.out.println("Ticket costs : $" + printPrice(this.ticketCost));
		System.out.println("Tickets sold : " + ticketsSold);
		System.out.println("Total Money : $" + printPrice(this.ticketCost * ticketsSold));
	}

	// add to ticket count
	public void sellTickets(int numTickets) throws TicketException {
		this.ticketsSold = this.ticketsSold + numTickets;

	}

	// remove from ticket count no future overrides as taken care of by sale of
	// tickets
	public void refundTickets(int numTickets) throws TicketException {

		if (numTickets <= ticketsSold) {
			this.ticketsSold = this.ticketsSold - numTickets;
		} else {
			throw new TicketException("Error: There hasnt been enough tickets sold to refund this amount");
		}

	}

	// print formating for ticket receipt sale or refund
	public void receipt(int tickets) {
		String leftAlignFormat = "| %-12s | %-14s | %-14s | %-14s |%n";
		System.out.format(leftAlignFormat, attractionID, "$" + printPrice(ticketCost), tickets,
				"$" + printPrice(ticketCost * tickets));

	}

	public int getTicketsSold() {
		return this.ticketsSold;
	}

	public String getAttDescription() {
		return attDescription;
	}

	public double getTicketCost() {
		return ticketCost;
	}

	public int getAttractionID() {
		return this.attractionID;
	}

}
