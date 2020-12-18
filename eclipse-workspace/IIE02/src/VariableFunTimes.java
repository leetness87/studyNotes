public class VariableFunTimes {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);

		gt.setXY(100, 50);
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		gt.setBackgroundColor(168, 84, 50);
		// keeping it simple and direct i used name as i have no further inputs of
		// anything else if i did would you name1 etc
		String name = gt.getInputString("What is your name:");
		// used the colourInitial as name because input would retrieve first character
		// only with code
		// i can see how it is used however apart from using it as unicode i find it to
		// be very niche.
		char colourInitial = gt.getInputString("enter your favourite colour").charAt(0);
		// used children as better then using noOfChildren it is set as integer as
		// values will always be whole and not excessive
		int children = Integer.parseInt(gt.getInputString("How many children do you have"));
		// used income because only 1 input otherwise would use income1 etc the value
		// can get big and have cent values so needed to be a double
		double income = Double.parseDouble(gt.getInputString("What is your income"));
		// used noChildren as refers back to another variable used it as boolean as will
		// have a non numeric value to be used in a if statement
		boolean noChildren = Boolean.parseBoolean(gt.getInputString("do you have children true of false"));
		gt.println("Your name: " + name);
		gt.println("Your favourite colour starts with: " + colourInitial);
		gt.println("You have " + children + " children");
		gt.println("Your income: $" + income);
		gt.println("You have Children " + noChildren);
		// shows version down bottom of window
		gt.setXY(100, 500);
		gt.showHelp();

		// message box
		gt.showMessageDialog("After new values\n Name is " + (name = ("Bob\n")) + (colourInitial + 1) + "\nChildren "
				+ (children + 1) + "\nyour income $" + (income + 1) + "\nYour Have Children " + (noChildren ^= true));

	}
}
