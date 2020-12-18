import java.awt.Color;

public class myTakeOnXero {
	private GTerm gtMain;
	private GTerm gtTable;
	private GTerm gtExtra;
	private String[] lastNames;
	private char[] firstNames;
	private short[] hours;
	private float[] hourlyRates;
	private int currentStaff;
	private int maxNumberStaff;
	private int i;
	private int j;
	private int arraysize;
	private int storedinput;
	private char[] tempFirstNames;
	private float[] tempHourlyRates;
	private short[] tempHours;
	private String[] tempLastNames;
	private char firstName;
	private String lastName;
	private float hourlyRate;

	public myTakeOnXero() {
		this.gtMain = new GTerm(300, 400);
		this.gtMain.setXY(1, 1);
		this.gtMain.println(
				"Welcome to my Payrate Program\nby using this you agree that\na high mark will be achieved\nor $100 cash :P");
		boolean terms = Boolean.parseBoolean(this.gtMain.getInputString("Do you agree to my terms?\ntrue or false"));
		if (terms) {
			this.gtMain.close();
			this.currentStaff = 0;
			this.maxNumberStaff = 1;
			this.lastNames = new String[this.maxNumberStaff];
			this.firstNames = new char[this.maxNumberStaff];
			this.hours = new short[this.maxNumberStaff];
			this.hourlyRates = new float[this.maxNumberStaff];
			this.gtTable = new GTerm(800, 800);
			this.gtTable.setBounds(800, 200, 800, 800);
			this.gtMain = new GTerm(600, 500);
			this.gtMain.setBounds(200, 400, 600, 500);
			this.gtMain.setXY(70, 50);
			this.gtMain.setFontSize(16);
			this.gtMain.setFontColor(Color.BLACK);
			this.gtMain.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setFontColor(Color.BLACK);
			this.gtTable.setBackgroundColor(Color.LIGHT_GRAY);
			this.gtTable.setBackground(Color.cyan);
			this.gtMain.println("Welcome to willbebetterthenxero");
			this.gtMain.println("Please make you selection\nfrom the available buttons");
			this.gtMain.setFontSize(12);
			this.gtMain.addButton("Add Record From text", this, "textfield");
			this.gtMain.println("");
			this.gtMain.println("This button adds from the text field below");
			this.gtMain.addButton("Add Record Individualy", this, "addRecordInd");
			this.gtMain.println("");
			this.gtMain.println("This button allows to enter information 1 by 1");
			this.gtMain.addButton("Refresh", this, "refreshTable");
			this.gtMain.println("");
			this.gtMain.println("This refreshs Table");
			this.gtMain.addButton("Totals", this, "totals");
			this.gtMain.println("");
			this.gtMain.println("This will display totals of all");
			this.gtMain.addButton("Info", this, "info");
			this.gtMain.println("");
			this.gtMain.println("See Production information");
			this.gtMain.setFontSize(16);
			this.gtMain.println("Enter First Name, Last name, Hourly rate\nSeperate by a comma");
			this.gtMain.addTextField("", 400);
			this.gtTable.setXY(1, 1);
			this.gtTable.setFontSize(14);
			this.gtTable.addTable(800, 700, "Index\tFirst name\tLastname\tPay rate\tHours\tWage\tOvertime\tTotal");
			this.gtTable.println("");
			this.gtTable.addButton("Edit entry", this, "edit");
			this.gtTable.addButton("Remove entry", this, "remove");
			this.gtTable.addButton("Enter Hours", this, "hours");
		} else {
			this.gtMain.showErrorDialog("You miss out");
			this.gtMain.close();

		}
	}

	public void addRecordInd() {
		this.arraysize = this.maxNumberStaff;
		this.storedinput = this.currentStaff;
		try {
			this.firstName = this.gtMain.getInputString("Enter Name").charAt(0);
		} catch (Exception e) {
			this.firstName = this.gtMain.getInputString("Invalid name").charAt(0);
		}
		try {
			this.lastName = this.gtMain.getInputString("Enter last name");
		} catch (Exception e) {
			this.lastName = this.gtMain.getInputString("Invalid last name enter again");
		}
		try {
			this.hourlyRate = Float.parseFloat(this.gtMain.getInputString("Enter hourly rate"));
		} catch (Exception e) {
			this.hourlyRate = Float.parseFloat(this.gtMain.getInputString("Invalid entry Enter hourly rate"));
		}
		arrayManipulation();
		firstnames();
		lastnames();
		hourlyRates();
		this.hours[this.storedinput] = 0;
		this.currentStaff++;
		refreshTable();
	}

	public double wagehours(double i) {
		if (this.hours[this.i] >= 38) {
			i = 38 * this.hourlyRates[this.i];
			return i;
		} else {
			i = this.hours[this.i] * this.hourlyRates[this.i];
			return i;
		}
	}

	public double overtime(double i) {
		if (this.hours[this.i] > 38) {
			double overtime = (hours[this.i] - 38);
			overtime = overtime * (this.hourlyRates[this.i] * 1.5);
			i = overtime;
			return i;
		} else {
			i = 0;
			return i;
		}
	}

	public void error() {
		this.gtMain.showErrorDialog("ERROR with input");
	}

	public void firstnames() {
		this.firstNames[this.storedinput] = this.firstName;
	}

	public void lastnames() {

		this.lastNames[this.storedinput] = this.lastName;
	}

	public void hourlyRates() {
		while (this.hourlyRate < 16) {
			error();
			this.hourlyRate = Float.parseFloat(this.gtMain.getInputString("Invalid entry Enter hourly rate"));
			;
		}
		this.hourlyRates[this.storedinput] = this.hourlyRate;
	}

	public void userInput() {
		this.storedinput = Integer.parseInt(this.gtTable.getInputString("enter which staff member:"));
		this.storedinput = this.storedinput - 1;
	}

	public void hoursInput() {
		this.hours[this.storedinput] = Short.parseShort(gtTable.getInputString("Enter how many hours they worked: "));
		while (this.hours[this.storedinput] < 0 || this.hours[this.storedinput] > 60) {
			error();
			this.hours[this.storedinput] = Short
					.parseShort(gtTable.getInputString("Enter how many hours they worked:"));
		}
	}

	public void textfield() {
		this.arraysize = this.maxNumberStaff;
		this.storedinput = this.currentStaff;
		String inputs = this.gtMain.getTextFromEntry(0);
		if (inputs != null) {
			String[] inputData = inputs.split(",");
			this.firstName = inputData[0].charAt(0);
			this.lastName = inputData[1];
			this.hourlyRate = Float.parseFloat(inputData[2]);
			arrayManipulation();
			firstnames();
			lastnames();
			hourlyRates();
			this.hours[this.storedinput] = 0;
			this.currentStaff += 1;
			refreshTable();
		}
	}

	public void edit() {
		userInput();
		int section = Integer.parseInt(this.gtTable.getInputString(
				"What section would you like to edit\n1. First Name\n2. Last Name\n3.Hourly rate\n4.Hours"));
		if (section == 1) {
			firstnames();

		} else if (section == 2) {
			lastnames();

		} else if (section == 3) {
			hourlyRates();

		} else if (section == 4) {
			hoursInput();

		}
		refreshTable();

	}

	public void transferArray() {
		tempFirstNames[j] = this.firstNames[i];
		tempLastNames[j] = this.lastNames[i];
		tempHourlyRates[j] = this.hourlyRates[i];
		tempHours[j] = this.hours[i];
		this.j++;
		this.i++;
	}

	public void arrayRemove() {
		if ((this.storedinput + 1) < this.currentStaff) {
			while (i < this.currentStaff) {
				if (i == (this.storedinput)) {
					this.i++;
				}
				transferArray();

			}
		} else if ((this.storedinput + 1) == this.currentStaff) {
			while (i < (this.currentStaff - 1)) {
				transferArray();
			}
		}
	}

	public void arrayManipulation() {
		this.j = 0;
		this.i = 0;
		if (this.currentStaff >= this.maxNumberStaff) {
			this.arraysize++;
		}
		this.tempFirstNames = new char[this.arraysize];
		this.tempLastNames = new String[this.arraysize];
		this.tempHourlyRates = new float[this.arraysize];
		this.tempHours = new short[this.arraysize];
		if (this.currentStaff >= this.maxNumberStaff) {
			if (this.currentStaff != this.storedinput) {
				arrayRemove();
			} else if (this.currentStaff >= this.maxNumberStaff) {
				this.maxNumberStaff++;
				while (j < this.currentStaff) {
					transferArray();
				}
			}
			this.firstNames = tempFirstNames;
			this.lastNames = tempLastNames;
			this.hourlyRates = tempHourlyRates;
			this.hours = tempHours;
		}
	}

	public void remove() {
		userInput();
		while ((this.storedinput + 1) > this.currentStaff) {
			error();
			userInput();
		}
		this.arraysize = this.maxNumberStaff - 1;
		arrayManipulation();
		this.maxNumberStaff--;
		this.currentStaff--;
		refreshTable();

	}

	public void refreshTable() {
		this.gtTable.clearRowsOfTable(0);
		int i = 0;
		while (i < this.currentStaff) {
			double wage = wagehours(0);
			double overtime = overtime(0);
			double total = wage + overtime;
			this.gtTable.addRowToTable(0, (i + 1) + "\t" + firstNames[i] + "\t" + this.lastNames[i] + "\t"
					+ this.hourlyRates[i] + "\t" + hours[i] + "\t" + wage + "\t" + overtime + "\t" + total);

			i += 1;
		}
	}

	public void hours() {
		userInput();
		hoursInput();
		refreshTable();

	}

	public void info() {

	}

	public void totals() {

	}

	public static void main(String[] args) {
		myTakeOnXero a2obj = new myTakeOnXero();

	}

}