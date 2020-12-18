import java.util.Arrays;

public class RatesMethods {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);
		// added table to window with headings to suit the following variables
		gt.addTable(700, 600, "Name\tGender\tBirth Year\tHourly Rate\tTerms Accepted");
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		// initialization of input to be used throughout the arrays where numberStaff
		// will be used in all counters to correctly input data
		int numberStaff = Integer.parseInt(gt.getInputString("How many staff members do you have?"));
		// validation loop for input to ensure positive number is entered have entered
		// high value as well as i do not want my table to overload
		while (numberStaff < 0 || numberStaff > 30) {
			numberStaff = Integer
					.parseInt(gt.getInputString("Error enter postive number\nHow many staff members do you have?"));
		}
		// initialization of variables into arrays also has a simple initialization to
		// be used for average later on
		String[] names;
		char[] genders;
		int[] birthYears;
		double[] hourlyRates;
		Boolean[] terms;
		double hourlyRateAvg = 0;
		// creation of the arrays based on the input that was received prior
		names = new String[numberStaff];
		genders = new char[numberStaff];
		birthYears = new int[numberStaff];
		hourlyRates = new double[numberStaff];
		terms = new Boolean[numberStaff];
		// first time using the counter so have initialized it here
		int i = 0;
		int indexOfMin = 0;
		// a loop that increments the input received into the array chosen in this case
		// the name has a incrementation in the string so the user can see what value is
		// to be expected also has a validation loop embedded inside it
		while (i < numberStaff) {
			String name = gt.getInputString("enter name for staff member " + (i + 1));
			while (name.isBlank()) {
				name = gt.getInputString("You have not entered name correctly please try again");
			}
			// 2nd input for the next inputs this is the gender used in a char format again
			// has embedded validation loop
			char gender = gt.getInputString("enter gender for staff member " + (i + 1)).charAt(0);
			while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
				gender = gt.getInputString("You entered an incorrect value for gender\nplease input M or F").charAt(0);
			}
			// 3rd input for the next lot of inputs this is birth year input by int as will
			// only have maximum 4 digits has validation loop have not set a maximum so the
			// program will not date
			int birthYear = Integer.parseInt(gt.getInputString("enter birth year of staff member " + (i + 1)));
			while (birthYear < 1900) {
				birthYear = Integer
						.parseInt(gt.getInputString("The entered year Value may not be valid\nplease try again"));
			}
			// 4th input this one asks for hourly rates has a validation loop embedded with
			// a max figure as anything in 3 figures would most likely be a extra button
			// press is a double as one would assume a cent value would be entered so need
			// decimal point
			double hourlyRate = Double.parseDouble(gt.getInputString("enter hourly rate of staff member " + (i + 1)));
			while (hourlyRate < 15.00 || hourlyRate > 99) {
				hourlyRate = Double.parseDouble(
						gt.getInputString("Please check value of Hourly Rate\nthe entered value was not valid"));
			}
			// 5th input this one asks for a boolean value it is in for the sake of training
			// purposes has a validation as well that it will not accept anything other then
			// a true value can also be written as terms != true and terms == false
			Boolean term = Boolean
					.parseBoolean(gt.getInputString("does you agree to the terms for staff member " + (i + 1)));
			while (!term) {
				term = Boolean.parseBoolean(gt.getInputString("Too bad you must enter True"));
			}
			names[i] = name;
			genders[i] = gender;
			birthYears[i] = birthYear;
			hourlyRates[i] = hourlyRate;
			terms[i] = term;
			// have added calculation for hourly rate average total also a print to table of
			// all inputed datum moved into initial loop to print as you go followed by
			// counter increase
			String message = (names[i] + '\t' + genders[i] + '\t' + birthYears[i] + '\t' + hourlyRates[i] + '\t'
					+ terms[i]);
			gt.addRowToTable(0, message);
			hourlyRateAvg = (hourlyRate + hourlyRateAvg);

			if (birthYears[i] < birthYears[indexOfMin]) {
				indexOfMin = i;
			}
			i++;
		}
		int j=0;
		while(j<numberStaff) {
			indexOfMin = j;
			i = j;
			while (i < numberStaff) {
				if (birthYears[i] < birthYears[indexOfMin])
					indexOfMin = i;
				i++;
			}
			int tempBirthYear = birthYears[indexOfMin];
			birthYears[indexOfMin] = birthYears[j];
			birthYears[j] = tempBirthYear;

			String tempName = names[indexOfMin];
			names[indexOfMin] = names[j];
			names[j] = tempName;
			
			char tempGender = genders[indexOfMin];
			genders[indexOfMin] = genders[j];
			genders[j] = tempGender;
			
			double tempRate = hourlyRates[indexOfMin];
			hourlyRates[indexOfMin] = hourlyRates[j];
			hourlyRates[j] = tempRate;
			
			Boolean tempTerm = terms[indexOfMin];
			terms[indexOfMin] = terms[j];
			terms[j] = tempTerm;
			j++;
		}
		i = 0;
		indexOfMin = 0;
		while (i < numberStaff) {
			if (birthYears[i] < birthYears[indexOfMin])
				indexOfMin = i;
			i++;
		}
		int tempBirthYear = birthYears[indexOfMin];
		birthYears[indexOfMin] = birthYears[0];
		birthYears[0] = tempBirthYear;

		String tempName = names[indexOfMin];
		names[indexOfMin] = names[0];
		names[0] = tempName;

		char tempGender = genders[indexOfMin];
		genders[indexOfMin] = genders[0];
		genders[0] = tempGender;

		double tempRate = hourlyRates[indexOfMin];
		hourlyRates[indexOfMin] = hourlyRates[0];
		hourlyRates[0] = tempRate;

		Boolean tempTerm = terms[indexOfMin];
		terms[indexOfMin] = terms[0];
		terms[0] = tempTerm;

		gt.addRowToTable(0, "\t\tAverage hourly rate\t" + (hourlyRateAvg / numberStaff));
		gt.showMessageDialog("Press ok to show sorted table");
		gt.clearRowsOfTable(0);
		// String messageMin = (names[i] + '\t' + genders[i] + '\t' + birthYears[i] +
		// '\t'
		// + hourlyRates[i] + '\t' + terms[i]);
		i = 0;
		while (i < numberStaff) {
			gt.addRowToTable(0,
					(names[i] + '\t' + genders[i] + '\t' + birthYears[i] + '\t' + hourlyRates[i] + '\t' + terms[i]));

			i++;
		}

		gt.setXY(100, 500);
		gt.showHelp();
	}
}
