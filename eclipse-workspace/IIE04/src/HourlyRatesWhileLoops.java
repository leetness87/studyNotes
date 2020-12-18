public class HourlyRatesWhileLoops {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);
		gt.addTable(700, 600,
				"Name" + '\t' + "Gender" + '\t' + "Birth Year" + '\t' + "Hourly Rate" + '\t' + "Full Time");
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		// this is the initialization of the counter to comply with 5b
		// counterStart is basic for name but does purpose
		double hourlyRateAvg = 0;
		int numberStaff = Integer.parseInt(gt.getInputString("How many staff members do you have?"));
		int counterStart = 0;
		boolean fullTimeInput = false;
		// the following array collects all the inputs in 1 diag box
		// each input is used further down with the correct conversions
		// the while loop will continue for the previous input of how many staff members
		// has a counter addition after each input
		while (counterStart < numberStaff) {
			String rawInput = gt.getInputString(
					"Please enter the following accompanied by comma to split\nEmployee name\nGender male or female\nWhat year were "
							+ "they born XXXX?\nWhat is there hourly rate?\nAre they fulltime? true or false?");
			String[] userInputs = rawInput.split(",");
			
			String name = userInputs[0];
			// validates that an entry was made
			while (name.isBlank()) {
				name = gt.getInputString("You have not entered name correctly please try again");
			}
			// used the char here to get input for a selection
			// found it has a better fail safe for incorrect inputs
			// since the question has 2 outputs error are easier to be voided
			char gender = userInputs[1].charAt(0);
			while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
				gender = gt.getInputString("You entered an incorrect value for gender\nplease input M or F").charAt(0);
			}
			// used int for birthYear as will be a whole number
			// is the third input in the split
			int birthYear = Integer.parseInt(userInputs[2]);
			// a loop created to validate the birth year meets conditions
			while (birthYear < 1900 || birthYear > 2005) {
				birthYear = Integer
						.parseInt(gt.getInputString("The entered year Value may not be valid\nplease try again"));
			}
			// used double for hourly rate as will have dollar and cent value
			// need the 2 decimal places that float will not offer
			double hourlyRate = Double.parseDouble(userInputs[3]);
			// checks to see if hourly rate of employee is within conditions i thought these
			// values where adequate
			// as shouldn't drop below that of a minimum wage and anything over 99 you would
			// imagine is a double key press
			while (hourlyRate < 15.00 || hourlyRate > 99) {
				hourlyRate = Double.parseDouble(
						gt.getInputString("Please check value of Hourly Rate\nthe entered value was not valid"));

			}
			// a true or false statement i tinkered with
			// i found that the conditional checks for boolean to be lacking
			// so i instead did the loop on the string and created a boolean out of it
			// as .valueof only recognizes true and parse recognizes both however in these
			// cases you could enter anything other then those and would be recognized as
			// false could use a while loop on boolean if you are asking for true but in
			// this instance my selection is both ways
			String fullTime = (userInputs[4]);
			while (!fullTime.equalsIgnoreCase("true") && !fullTime.equalsIgnoreCase("false")) {
				fullTime = gt.getInputString("Seems i didnt get that last input\nplease enter true or false");
			}
			if (fullTime.equalsIgnoreCase("true")) {
				fullTimeInput = true;

			} else {
				fullTimeInput = false;
			}
			
			gt.addRowToTable(0, name + '\t' + gender + '\t' + birthYear + '\t' + hourlyRate + '\t' + fullTimeInput);
			// the average i added in to comply with 5e added if statement to only print
			// once all inputs are entered adds to table is spaced to sit in the correct
			// column
			// added this line to collect data to add up the inputs of wages had to add in

			hourlyRateAvg = (hourlyRate + hourlyRateAvg);
			if (numberStaff == counterStart) {
				gt.addRowToTable(0, "" + '\t' + "" + '\t' + "" + '\t' + (hourlyRateAvg / numberStaff));
			}
			counterStart++;
		}
		gt.setXY(100, 500);
		gt.showHelp();

	}
}