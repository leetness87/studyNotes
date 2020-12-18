import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

public class WithoutMe {
	private GTerm gtMain;
	private GTerm gtTable;
	private GTerm gtConsole;
	private String[] lastNames;
	private char[] firstNames;
	private short[] hours;
	private float[] hourlyRates;
	private String[] userInput;
	private int currentStaff;
	private int arraySize;

	public WithoutMe() {

		GTerm welcome = new GTerm(300, 400);
		welcome.setXY(1, 1);
		welcome.println(
				"Welcome to my Payrate Program\nby using this you agree that\na high mark will be achieved\nor $100 cash :P");

		boolean terms = Boolean.parseBoolean(welcome.getInputString("Do you agree to my terms?\ntrue or false"));
		if (terms) {
			welcome.close();
			this.currentStaff = 0;
			this.gtConsole = new GTerm(600, 300);
			this.gtConsole.setBounds(1, 1, 600, 300);
			this.gtTable = new GTerm(900, 800);
			this.gtTable.setBounds(601, 1, 900, 800);
			this.gtMain = new GTerm(600, 500);
			this.gtMain.setBounds(1, 301, 600, 500);
			this.gtMain.setXY(50, 10);
			this.gtMain.setFontSize(16);
			this.gtMain.setFontColor(Color.BLACK);
			this.gtMain.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setFontColor(Color.BLACK);
			this.gtTable.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setBackground(Color.cyan);
			this.gtConsole.setBackgroundColor(Color.BLACK);
			this.gtConsole.setFont(null, Font.ITALIC, 16);
			this.gtConsole.setFontColor(Color.GREEN);
					this.gtMain.println("Welcome to Wage Calculator 2.0");
			this.gtMain.println("Please make your selection\nfrom the available buttons");
				this.gtMain.setFontSize(12);
					this.gtMain.addButton("Add Record From text", this, "addRecord");
			this.gtMain.println("");
			this.gtMain.addButton("Edit First Name", this, "editFirstName");
			this.gtMain.println("");
			this.gtMain.addButton("Edit Last Name", this, "editLastName");
			this.gtMain.println("");
			this.gtMain.addButton("Edit hourly Rate", this, "editHourlyRate");
			this.gtMain.println("");
			this.gtMain.addButton("Edit Hours Worked", this, "editHours");
			this.gtMain.println("");
			this.gtMain.addButton("Info", this, "info");
			this.gtMain.println("");
			this.gtMain.addButton("Exit", this, "exit");
			this.gtMain.println("");
			this.gtMain.setFontSize(16);
			this.gtMain.println("Enter First Name, Last name, Hourly rate , Hours\nSeperate by a comma");
			this.gtMain.addTextField("Enter Text Here", 400);
			this.gtMain.println("");
			this.gtMain.println("Enter in values here to edit from selection\n");
			this.gtMain.addTextField("", 200);
			this.gtTable.setXY(1, 1);
			this.gtTable.setFontSize(14);
			this.gtTable.addTable(900, 700,
					"Index\tFirst name\tLastname\tHourly Rate\tHours\tNormal Pay\tOvertime\tTotal");
			this.gtTable.println("");
			this.gtTable.addButton("Remove entry From text", this, "remove");
			this.arraySize = Integer.parseInt(this.gtMain.getInputString("How many records are you entering today?"));
				while (this.arraySize < 0) {
				this.arraySize = Integer
						.parseInt(this.gtMain.getInputString("How many records are you entering today?"));
			}
			this.lastNames = new String[this.arraySize];
			this.firstNames = new char[this.arraySize];
			this.hours = new short[this.arraySize];
			this.hourlyRates = new float[this.arraySize];
			this.userInput = new String[4];
		} else {
			welcome.showErrorDialog("You miss out");
			welcome.close();

		}
	}

	public void addRecord() {
		this.userInput = this.gtMain.getTextFromEntry(0).split(",");
		if (this.userInput != null) {
			this.gtConsole.clear();
			char firstName = this.userInput[0].toUpperCase().charAt(0);
			while (!Character.isAlphabetic(firstName)) {
				this.gtConsole.println("The entered value in firstname is not correct must be alphabetical");
				firstName = this.gtMain.getInputString("Enter Firstname").charAt(0);
			}
			String lastName = this.userInput[1].toUpperCase();
			while (lastName.contains(" ")) {
				this.gtConsole.println("The entered value for last name must not contain spaces");
				lastName = this.gtMain.getInputString("Enter Last Name");
			}
			float hourlyRate = Float.parseFloat(this.userInput[2]);
			while (hourlyRate < 16 || hourlyRate > 199) {
							this.gtConsole.println(
						"The number you entered for hourly rate seems to be invalid\nneeds to be between 16 and 199");
					hourlyRate = Integer.parseInt(this.gtMain.getInputString("enter hourly rate"));
			}
			short hours = Short.parseShort(this.userInput[3]);
			while (hours < 0 || hours > 112) {
						this.gtConsole.println("The hours you entered do not seem to be valid\nmust be between 0 and 112");
								hours = Short.parseShort(this.gtMain.getInputString("Enter hours"));
			}


			if (this.currentStaff >= this.arraySize) {
						this.arraySize += 1;
				char[] longerFirstNames = new char[this.arraySize];
				String[] longerLastNames = new String[this.arraySize];
				float[] longerHourlyRate = new float[this.arraySize];
				short[] longerHours = new short[this.arraySize];
						int j = 0;
				while (j < this.currentStaff) {
					longerFirstNames[j] = this.firstNames[j];
					longerLastNames[j] = this.lastNames[j];
					longerHourlyRate[j] = this.hourlyRates[j];
					longerHours[j] = this.hours[j];
					j++;
				}
				this.firstNames = longerFirstNames;
				this.lastNames = longerLastNames;
				this.hourlyRates = longerHourlyRate;
				this.hours = longerHours;
			}
			this.firstNames[this.currentStaff] = firstName;
			this.lastNames[this.currentStaff] = lastName;
			this.hourlyRates[this.currentStaff] = hourlyRate;
			this.hours[this.currentStaff] = hours;
			this.currentStaff += 1;
			refreshTable();
		}
	}

	public int tableSelect() {
		int i = this.gtTable.getIndexOfSelectedRowFromTable(0);
		return i;
	}

	public void editFirstName() {
			int i = tableSelect();
		this.gtConsole.clear();
		this.gtConsole.println("You just edited " + lastNames[i] + " first name initial:" + firstNames[i]);
		char temp = this.firstNames[i];
		this.firstNames[i] = this.gtMain.getTextFromEntry(1).toUpperCase().charAt(0);
		if (!Character.isAlphabetic(firstNames[i])) {
			this.gtConsole.println("The first name you entered is not valid\nand has been reset to initial value");
			this.firstNames[i] = temp;
		}
		this.gtConsole.println("The new value is :" + firstNames[i]);
		refreshTable();
	}

	public void editLastName() {
		int i = tableSelect();
		this.gtConsole.clear();
		this.gtConsole.println("You just edited " + lastNames[i] + " Last Name:" + lastNames[i]);
			String temp = this.lastNames[i];
		this.lastNames[i] = this.gtMain.getTextFromEntry(1).toUpperCase();
		if (this.lastNames[i].isBlank() && this.lastNames[i].contains(" ")) {
			this.gtConsole.println("The last name you entered is not valid\nand has been reset to initial value");
			this.lastNames[i] = temp;
		}
		this.gtConsole.println("The new value is :" + lastNames[i]);
		refreshTable();

	}

	public void editHourlyRate() {
		int i = tableSelect();
		this.gtConsole.clear();
		this.gtConsole.println("You just edited " + lastNames[i] + " hourly rate :" + hourlyRates[i]);
		float temp = this.hourlyRates[i];
		this.hourlyRates[i] = Float.parseFloat(this.gtMain.getTextFromEntry(1));
		if (this.hourlyRates[i] < 16 || this.hourlyRates[i] > 99) {
			this.gtConsole.println("The Hourly Rate you entered is not valid\nand has been reset to initial value");
			this.hourlyRates[i] = temp;
		}
		this.gtConsole.println("The new value is :" + hourlyRates[i]);
		refreshTable();
	}

	public void editHours() {
		int i = tableSelect();
		this.gtConsole.clear();
		this.gtConsole.println("You just edited " + lastNames[i] + " hours :" + hours[i]);
		short temp = this.hours[i];
		this.hours[i] = Short.parseShort(this.gtMain.getTextFromEntry(1));
		if (this.hours[i] < 0 && this.hours[i] > 80) {
			this.gtConsole.println("The Hours you entered is not valid\nand has been reset to initial value");
			this.hours[i] = temp;
		}
		this.gtConsole.println("The new value is :" + hours[i]);
		refreshTable();
	}

	public void remove() {
		int i = tableSelect();
		int j = 0;
		int k = 0;
		this.gtConsole.println("You have chosen to remove " + firstNames[i] + lastNames[i]);
		if (i + 1 == this.currentStaff) {
			this.currentStaff--;
		} else {
			while (j < this.currentStaff) {
				if (j == i) {
					j++;
				}
				this.firstNames[k] = this.firstNames[j];
				this.lastNames[k] = this.lastNames[j];
				this.hourlyRates[k] = this.hourlyRates[j];
				this.hours[k] = this.hours[j];
				j++;
				k++;
			}
			this.currentStaff--;
		}
		this.arraySize = this.currentStaff;
		char[] shorterFirstNames = new char[this.arraySize];
		String[] shorterLastNames = new String[this.arraySize];
		float[] shorterHourlyRate = new float[this.arraySize];
		short[] shorterHours = new short[this.arraySize];
		j = 0;
		while (j < this.currentStaff) {
			shorterFirstNames[j] = this.firstNames[j];
			shorterLastNames[j] = this.lastNames[j];
			shorterHourlyRate[j] = this.hourlyRates[j];
			shorterHours[j] = this.hours[j];
			j++;
		}
		this.firstNames = shorterFirstNames;
		this.lastNames = shorterLastNames;
		this.hourlyRates = shorterHourlyRate;
		this.hours = shorterHours;
		refreshTable();
	}

	public double overtime(double rate, short hours) {
		double overtime = 0;
		if (hours > 38) {
			overtime = (rate * 1.5) * (hours - 38);
		}
		return overtime;
	}
	public double normalPay(double rate, short hours) {
		double normalPay = 0;
		if (hours > 38) {
			normalPay = rate * 38;
		} else {
			normalPay = rate * hours;
		}
		return normalPay;
	}

	public void refreshTable() {
		this.gtTable.clearRowsOfTable(0);
		int i = 0;
		double runningTotal = 0;
		while (i < this.currentStaff) {
			double normalPay = normalPay(this.hourlyRates[i], this.hours[i]);
			double overtime = overtime(this.hourlyRates[i], this.hours[i]);
			double total = overtime + normalPay;
			runningTotal = runningTotal + total;
			this.gtTable.addRowToTable(0, i + 1 + "\t" + this.firstNames[i] + "\t" + this.lastNames[i] + "\t"
					+ this.hourlyRates[i] + "\t" + this.hours[i] + "\t" + normalPay + "\t" + overtime + "\t" + total);
			i += 1;
		}
		this.gtTable.addRowToTable(0, "\t\t\t\t\t\tTotal\t" + runningTotal);
	}
	public void info() {
		GTerm info = new GTerm(375, 500);
		info.setXY(30, 2);
		info.setBackgroundColor(Color.BLACK);
		info.setFont(null, Font.ITALIC, 16);
		info.setFontColor(Color.GREEN);
		info.println("This was brought to you by Channon Harper");
		info.println("\t\t\t\t\t\t\ts3871491");
		info.println("\t\t\t\tI hope you enjoyed it :D");
		info.setXY(60, 100);
		info.addImageIcon("bblogo.png");
		info.setXY(2, 350);
		info.showHelp();
	}

	public void exit() {
		this.gtConsole.close();
		this.gtTable.close();
		this.gtMain.close();
	}

	public static void main(String[] args) {
		WithoutMe a2obj = new WithoutMe();

	}

}
// chan,harps,28,38