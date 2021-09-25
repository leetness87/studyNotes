import java.util.Scanner;

public class UnsupervisedTour extends Attraction {

	private int difficultyRating;
	private String instructionGuide;

	public UnsupervisedTour(int attractionID, String attDescription, Double ticketCost, int ticketsSold,
			int difficultyRating, String instructionGuide) {
		super(attractionID, attDescription, ticketCost, ticketsSold);
		this.difficultyRating = difficultyRating;
		this.instructionGuide = instructionGuide;
	}

	// Scanner function to load file and reconstruct program
	public UnsupervisedTour(Scanner fileScanner) {
		super(fileScanner);
		this.instructionGuide = "";
		String input = "";
		this.difficultyRating = Integer.parseInt(fileScanner.nextLine());
		while (fileScanner.hasNextLine()) {
			input = fileScanner.nextLine() + "\n";
			if (input.isBlank()) {
				break;
			}
			this.instructionGuide += input;
		}

	}

	// adds the spefics of this class to print out details
	@Override
	public void printFull() {
		super.printFull();
		System.out.println("Difficulty Rating : " + difficultyRating);
		System.out.println("Instructions Guide :\n" + instructionGuide);
	}

	public int getDifficultyRating() {
		return difficultyRating;
	}

	public String getInstructionGuide() {
		return instructionGuide;
	}

}
