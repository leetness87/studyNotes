
public class AKnightsTaleIntro {

	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);
		gt.setXY(1, 1);
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		gt.setBackgroundColor(168, 84, 50);
		// Strings needed in future initialized
		String gender1 = "";
		// printing in the gterm window as an introduction
		gt.print("Hello adventurer i see you wish to become a Knight \nFirst i must ask you a few questions");
		// first input string which is name to suite the question
		// used gt diag box to obtain input
		String name = gt.getInputString("Please enter name");
		//condition check on the entered name loops if nothing entered
		while (name.isBlank()) {
			name = (gt.getInputString("Please enter name"));
		}
		// second input which is a char value taken by the gt diag box
		// have used char as i find it easier to obtain a true value
		// has fewer error that can occur
		char gender = gt.getInputString(
				"What is your gender \nPlease answer with one of the following (a,b,c):\n a. Male\n b.Female \n c.Other")
				.charAt(0);
		// while loop or condition check simply it sees if the input is valid
		// will continue until one of the conditions are met
		while (gender != 'a' && gender != 'b' && gender != 'c' && gender != 'A' && gender != 'B' && gender != 'C') {
			// error code which resets the diag box to obtain input
			gender = gt.getInputString(
					"Sorry that was invalid\nWhat is your gender \nPlease answer with one of the following (a,b,c):\n a. Male\n b.Female \n c.Other")
					.charAt(0);
		}
		// if else statements to input a string in the coding from the given char
		// depicts the title of a gender and inputs it accordingly
		// is nested given the age inputed here using an Integer
		// the condition checks with the conditions are of a numerical input
		// a selection of a suffix is made has 3 conditions with 2 conditions having
		// further conditions to make a good choice for the user
		if (gender == 'a' || gender == 'A') {
			int classSelection = Integer.parseInt(gt.getInputString("How old are you??"));
			if (classSelection > 0 && classSelection <= 10) {
				gender1 = "Squire ";
			}

			else if (classSelection > 10 && classSelection <= 13) {
				gender1 = "Padawan ";
			} else if (classSelection > 13 && classSelection <= 100) {
				gender1 = "Sir ";
			} else {
				gender1 = ("Unknown");
			}

		} else if (gender == 'b' || gender == 'B') {
			int classSelection = Integer.parseInt(gt.getInputString("How old are you??"));
			if (classSelection > 0 && classSelection <= 10) {
				gender1 = "Squire ";
			}

			else if (classSelection > 10 && classSelection <= 13) {
				gender1 = "Dame ";
			} else if (classSelection > 13 && classSelection <= 100) {
				gender1 = "Lady ";
			} else {
				gender1 = ("Unknown ");
			}

		} else if (gender == 'c' || gender == 'C') {
			gender1 = ("Unknown ");
		}
		// a clear screen for the window to take you onto stage 2 of the program
		gt.clear();
		// introduction to stage 2 in the window inserts the variables selected in stage
		// 1 also resets text to where it should be
		gt.println("Welcome " + gender1 + name + " to the world of Mutingard");
		gt.println("Today we will test your knowledge to see if \nyou are ready to become one at the table");
		gt.println("Please answer the following questions");
		// sets image back to corner
		// is put in after code so it runs behind the text and
		// makes it more appeasing to look at
		gt.setXY(1, 1);
		// castle image to be used for question and to provide theme
		gt.addImageIcon("Mutingard.png");
		// creates the castle string which will be need for a condition check in the
		// last of the program
		String castle = gt.getInputString("Please spell what is picture behind\nHint a King would live here");
		// a simple question and used a double however could use a float
		// doubles being more precise give correct feedback whether the input was
		// correct
		double answer1 = Double.parseDouble(gt.getInputString("If you divide 11 in half what would the answer be"));
		// a true or false statement Boolean was the right choice
		// could be achieved by adding an if statement to another condition
		// however having a answer to the question being true or false
		// easy to do it this way
		boolean brave = Boolean.parseBoolean(
				gt.getInputString("It is your duty to be Brave and Courageous\n Please type True or False"));
		// a clear screen to conclude the end of stage 2
		gt.clear();
		// printed lines in the window to show the answers that were given
		// user has visual input of there selections to answer the next question
		gt.println("You have selected " + castle + ", " + answer1 + ", " + brave);
		gt.println("it is now time to see if you have passed the test are you ready??");
		// another char value to depict a selection
		// is more viable to not error out with fewer lines of code
		char yesNo = gt.getInputString("Are you??? y or n").charAt(0);
		// a condition check to see if value entered is valid otherwise
		// will loop back until a valid input is entered
		while (yesNo != 'y' && yesNo != 'n' && yesNo != 'Y' && yesNo != 'N') {
			yesNo = gt.getInputString("Please enter valid command \nAre you??? y or n").charAt(0);
		}
		// a clear screen to conclude stage 3
		gt.clear();
		// if else statement to input the selection given breaks into 2 stages
		gt.setXY(20, 100);
		if (yesNo == 'y' || yesNo == 'Y') {
			gt.println("Good Choice");
		} else {
			gt.close();
		}
		// condition check to see if answers are correct

		if (answer1 == 5.5 && brave == true && castle.equalsIgnoreCase("castle")) {
			gt.println("YOU ARE READY TO CONTINUE \nA new tale chapter comming shortly");
		} else {
			gt.println("Sorry you are not at this time worthy \nPlease try again");
		}
		// end of program
		gt.setXY(10, 500);
		// give rights to creator of gterm and shows version
		gt.showHelp();

	}

}
