public class ChildrenCalcWithIfStatements {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);

		gt.setXY(2, 2);
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		gt.setBackgroundColor(168, 84, 50);
		// initialization of strings to be use in window later on
		// all relate to inputs given by user creating said string
		// saves extra typing further on with if statements
		String wantChildrenOutput = "";
		String shoppingBillOutput = "";
		String childrenOutput = "";
		String mumDad = "";
		// keeping it simple and direct i used name as i have no further inputs of
		// anything else if i did would you name1 etc
		String name = gt.getInputString("What is your name:");
		if (name.isEmpty()) {
			name = "You selected nothing guess privacy is an issue";
		}
		// used the char here to get input
		// found it has a better fail safe for incorrect inputs
		// less coding to enter into if statement in future
		char gender = gt.getInputString("Are you Male or Female").charAt(0);
		// adds a incorrect input loop
		while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			gender = gt.getInputString("Sorry that was invalid\nWhat is your gender Male Female").charAt(0);
		}
		// converts the input char into a string for the coding required
		// gives multiple paths of entries
		// added a nest of if statements only place i deemed fit
		if (gender == 'm' || gender == 'M') {
			char beard = (gt.getInputString("do you have a beard").charAt(0));
			if (beard == 'y' || beard == 'Y') {
				mumDad = "Awesome Dad ";
			} else {
				mumDad = "Dad that's a Mum ";
			}
		} else if (gender == 'f' || gender == 'F') {
			mumDad = "Mother ";
		}
		// used children as better then using noOfChildren it is set as integer as
		// values will always be whole and not excessive
		// if statement to change string outcome
		// use of integer used later also in equation
		int children = Integer.parseInt(gt.getInputString("How many children do you have"));
		if (children <= 0) {
			childrenOutput = " children, Not ready for that commintment?";
		} else if (children == 1 || children < 4) {
			childrenOutput = children + " children, nice size family you have there";
		} else {
			childrenOutput = children + " children, Damn don't know how you do it";
		}
		// used shoppingBill because of question asked
		// a float should be sufficient as inputs may be rounded down
		// if statement to depict different ranges and outcomes
		// -100 is for typical items and subtracted from extra costs
		float shoppingBill = Float.parseFloat(gt.getInputString("How much do you spend weekly on shopping?"));
		if (shoppingBill <= 149.0) {
			shoppingBillOutput = "That's great " + ((shoppingBill - 100) / children) + " per child";
		} else if (shoppingBill >= 150.0 && shoppingBill <= 400.0) {
			shoppingBillOutput = "Thats normal " + ((shoppingBill - 100) / children) + " per child";
		} else {
			shoppingBillOutput = "Where do you get your money " + ((shoppingBill - 100) / children) + " per child";
		}
		// used wantChildren as is understandable
		// the if statement changes string value
		// used less than and greater in the statement for the outcomes
		// also added else clause
		boolean wantChildren = Boolean.parseBoolean(gt.getInputString("do you want more children true of false"));
		if (wantChildren == true && children < 4) {
			wantChildrenOutput = "You are in the midst creating i nice family";
		} else if (wantChildren == true && children > 4) {
			wantChildrenOutput = "Are you insane?!?";
		} else {
			wantChildrenOutput = "guess you are done";
		}
		// Prints all outcomes to the Gterm Window
		gt.println("Your name: " + name);
		gt.println("You are the " + mumDad);
		gt.println("You have " + childrenOutput);
		gt.println("Your shopping bill is $" + shoppingBill + "\n" + shoppingBillOutput);
		gt.println("You selected " + wantChildren + " to having more children\n" + wantChildrenOutput);
		// shows version down bottom of window
		gt.setXY(100, 500);
		gt.showHelp();

	}
}
