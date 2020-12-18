import java.util.Arrays;

public class RatesMultipleArrays {
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
		String[] name;
		char[] gender;
		int[] birthYear;
		double[] hourlyRate;
		Boolean[] terms;
		double hourlyRateAvg = 0;
		// creation of the arrays based on the input that was received prior
		name = new String[numberStaff];
		gender = new char[numberStaff];
		birthYear = new int[numberStaff];
		hourlyRate = new double[numberStaff];
		terms = new Boolean[numberStaff];
		// first time using the counter so have initialized it here
		int i = 0;
		// a loop that increments the input received into the array chosen in this case
		// the name has a incrementation in the string so the user can see what value is
		// to be expected also has a validation loop embedded inside it
		while (i < numberStaff) {
			name[i] = gt.getInputString("enter name for staff member " + (i + 1));
			while (name[i].isBlank()) {
				name[i] = gt.getInputString("You have not entered name correctly please try again");
			}
			// 2nd input for the next inputs this is the gender used in a char format again
			// has embedded validation loop
			gender[i] = gt.getInputString("enter gender for staff member " + (i + 1)).charAt(0);
			while (gender[i] != 'm' && gender[i] != 'M' && gender[i] != 'f' && gender[i] != 'F') {
				gender[i] = gt.getInputString("You entered an incorrect value for gender\nplease input M or F")
						.charAt(0);
			}
			// 3rd input for the next lot of inputs this is birth year input by int as will
			// only have maximum 4 digits has validation loop have not set a maximum so the
			// program will not date
			birthYear[i] = Integer.parseInt(gt.getInputString("enter birth year of staff member " + (i + 1)));
			while (birthYear[i] < 1900) {
				birthYear[i] = Integer
						.parseInt(gt.getInputString("The entered year Value may not be valid\nplease try again"));
			}
			// 4th input this one asks for hourly rates has a validation loop embedded with
			// a max figure as anything in 3 figures would most likely be a extra button
			// press is a double as one would assume a cent value would be entered so need
			// decimal point
			hourlyRate[i] = Double.parseDouble(gt.getInputString("enter hourly rate of staff member " + (i + 1)));
			while (hourlyRate[i] < 15.00 || hourlyRate[i] > 99) {
				hourlyRate[i] = Double.parseDouble(
						gt.getInputString("Please check value of Hourly Rate\nthe entered value was not valid"));
			}
			// 5th input this one asks for a boolean value it is in for the sake of training
			// purposes has a validation as well that it will not accept anything other then
			// a true value can also be written as terms != true and terms == false
			terms[i] = Boolean
					.parseBoolean(gt.getInputString("does you agree to the terms for staff member " + (i + 1)));
			while (!terms[i]) {
				terms[i] = Boolean.parseBoolean(gt.getInputString("Too bad you must enter True"));
			}
			// have added calculation for hourly rate average total also a print to table of
			// all inputed datum moved into initial loop to print as you go followed by
			// counter increase
			gt.addRowToTable(0,
					name[i] + '\t' + gender[i] + '\t' + birthYear[i] + '\t' + hourlyRate[i] + '\t' + terms[i]);
			hourlyRateAvg = (hourlyRate[i] + hourlyRateAvg);
			i++;
		}
		gt.addRowToTable(0, "\t\tAverage hourly rate\t" + (hourlyRateAvg / numberStaff));
		// 2 new counters added for use in finding a minimum value
		int minIndex = 0;
		int arrayCounter = 1;
		// new array for inputing the index of the minimum values also set the min to
		// the first element of the array i am working on
		int[] minlist = new int[numberStaff];
		int min = birthYear[0];
		// for loop used to check all elements in an array first if will add minimum
		// element and perform checks on the rest and add them if they are equal the
		// else if resets the array position back to 0 and reset the counter of how long
		// the next array has to be array counter stays at 1 as cannot be empty
		for (i = 0; i < birthYear.length; i++)
			if (birthYear[i] == min) {
				minlist[minIndex] = i;
				minIndex++;
				arrayCounter++;
			} else if (min > birthYear[i]) {
				min = birthYear[i];
				minIndex = 0;
				arrayCounter = 1;
				minlist[minIndex] = i;
				minIndex++;
			}
		// a new array copies the indexs given by the former for loop and sets it to a
		// length that the counter had reached as to how many minimum values were equal
		int[] reduce = Arrays.copyOf(minlist, arrayCounter);
		gt.addRowToTable(0, "The oldest member is");
		minIndex = 0;
		//add rows to table in correlation to how many minimum values were present
		while (minIndex < reduce.length) {
			gt.addRowToTable(0,
					name[reduce[minIndex]] + '\t' + gender[reduce[minIndex]] + '\t' + birthYear[reduce[minIndex]] + '\t'
							+ hourlyRate[reduce[minIndex]] + '\t' + terms[reduce[minIndex]]);
			minIndex++;
		}
		gt.setXY(100, 500);
		gt.showHelp();
	}
}
