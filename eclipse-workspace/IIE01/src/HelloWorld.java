public class HelloWorld {
	public static void main(String[] args) {
		GTerm gt = new GTerm(700, 600);
		gt.setXY(100, 50);
		gt.setFontSize(16);
		gt.setFontColor(16, 50, 64);
		gt.setBackgroundColor(168, 84, 50);
		gt.print("Hello ");
		String enteredString = gt.getInputString("Please enter name");
		gt.println(enteredString);
		gt.println("my name is Channon");
		gt.println("This will be the first program i have ever created");
		gt.println("I prefer to go by Harps, my family is my world");
		gt.setFontSize(14);
		gt.setFontColor(0, 0, 0);
		gt.println("This may be the first tasks but enjoyment is already high");
		gt.println("Just learning the basics i look forward to seeing what comes next");
		gt.setXY(1, 1);
		gt.addImageIcon("smiley.png");
		// gt.showHelp();

	}
}
