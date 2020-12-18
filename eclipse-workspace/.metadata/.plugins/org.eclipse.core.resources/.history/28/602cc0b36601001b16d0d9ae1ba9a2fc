public class HourlyRatesWhileLoops {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);
		// added table to window with headings to suit the following variables
		gt.addTable(700, 600,
				"Name" + '\t' + "Gender" + '\t' + "Birth Year" + '\t' + "Hourly Rate" + '\t' + "Terms Accepted");
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		// Initialization of a double for averaging sum
		double hourlyRateAvg = 0;
		// to comply with 5b a counter initialization and the input required to have a
		// counter end
		int counterStart = 0;
		int numberStaff = Integer.parseInt(gt.getInputString("How many staff members do you have?"));
		// while statement that is nested with others the the program inside will run
		// until the counter has met its requirements
		while (counterStart < numberStaff) {
			// inputs to an array that will be worked off for the following
			String rawInput = gt.getInputString(
					"Please enter the following accompanied by comma to split\nEmployee name\nGender male or female\nWhat year were "
							+ "they born XXXX?\nWhat is there hourly rate?\nDo you accept my terms? True or False");
			String[] userInputs = rawInput.split(",");
			// conversions of inputs to the variables that are required
			String name = userInputs[0];
			char gender = userInputs[1].charAt(0);
			int birthYear = Integer.parseInt(userInputs[2]);
			double hourlyRate = Double.parseDouble(userInputs[3]);
			Boolean terms = Boolean.parseBoolean(userInputs[4]);
			// the following are conditional checks on the inputs previously in the form of
			// a while loop that will continue until the conditional requirements are met
			// has internal input strings to re-take inputs and convert them as needed
			while (name.isBlank()) {
				name = gt.getInputString("You have not entered name correctly please try again");
			}
			while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
				gender = gt.getInputString("You entered an incorrect value for gender\nplease input M or F").charAt(0);
			}
			while (birthYear < 1900 || birthYear > 2005) {
				birthYear = Integer
						.parseInt(gt.getInputString("The entered year Value may not be valid\nplease try again"));
			}
			while (hourlyRate < 15.00 || hourlyRate > 99) {
				hourlyRate = Double.parseDouble(
						gt.getInputString("Please check value of Hourly Rate\nthe entered value was not valid"));
			}
			while (terms != true) {
				terms = Boolean.parseBoolean(gt.getInputString("Too bad you must enter True"));
			}
			// adds row to the table once the criteria above has been met
			gt.addRowToTable(0, name + '\t' + gender + '\t' + birthYear + '\t' + hourlyRate + '\t' + terms);
			// created a sum for the inputs of hourly rate to find an average will only
			// count valid inputs in this location this is followed by a counter increase
			// that only adds once conditions are met
			hourlyRateAvg = (hourlyRate + hourlyRateAvg);
			counterStart++;
		}
		// an output of the average in the table under the hourly rates
		if (numberStaff == counterStart) {
			gt.addRowToTable(0, "" + '\t' + "" + '\t' + "" + '\t' + (hourlyRateAvg / numberStaff));

		}
		gt.setXY(100, 500);
		gt.showHelp();
		// bob,m,2000,50,True
	}
}
