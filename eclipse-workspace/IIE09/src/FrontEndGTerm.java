import java.awt.Color;

public class FrontEndGTerm {
	// usual stuff creating backend and a gterm
	BackEnd be;
	private GTerm gt;

	// constructor make the gterm window and adds all the elements
	public FrontEndGTerm(BackEnd backEnd) {
		//initialize the backend
		be = backEnd;
		this.gt = new GTerm(800, 800);
		this.gt.setXY(70, 50);
		this.gt.addTable(700, 600, "Index\tName\tGender\tBirth Year\tHourly Rate\tTerms Accepted");
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.RED);
		this.gt.setBackgroundColor(Color.GRAY);
		this.gt.println("");
		this.gt.addButton("Add Record", this, "addRecord");
		this.gt.addButton("Edit", this, "butEdit");
		this.gt.addButton("Remove", this, "butRemove");
		this.gt.println("");
		this.gt.addButton("Refresh", this, "refreshTable");
		this.gt.println("");
		this.gt.setFontColor(Color.BLACK);
		this.gt.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		// textfield for entries
		this.gt.addTextField("", 500);
	}

	public void addRecord() {
		// creates array from the textfield
		String[] inputData = this.gt.getTextFromEntry(0).split(",");
		// creates string from first input with validation
		String name = inputData[0];
		while (name.isBlank()) {
			name = this.gt.getInputString("Error with name input cannot be blank");

		} // creates char from second input with validation
		char gender = inputData[1].charAt(0);
		while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			gender = this.gt.getInputString("Error with gender input must be M or F").charAt(0);

		} // creates int from third input with validation
		int birthYear = Integer.parseInt(inputData[2]);
		while (birthYear < 1900) {
			birthYear = Integer
					.parseInt(this.gt.getInputString("Error with birth year input must be greater than 1900"));
		} // creates double from fourth input with validation
		double hourlyRate = Double.parseDouble(inputData[3]);
		while (hourlyRate < 15.00 || hourlyRate > 99) {
			hourlyRate = Double
					.parseDouble(this.gt.getInputString("Error with hourly rate input must be between 15 and 99"));

		}// creates boolean from fifth input with validation
		Boolean term = Boolean.parseBoolean(inputData[4]);
		while (!term) {
			term = Boolean.parseBoolean(this.gt.getInputString("Error with term input must be True"));
		}
		// once all are validated calls method in backend to add them to array there
		be.addRecord(name, gender, birthYear, hourlyRate, term);
		refreshTable();

	}

	public void butEdit() {
		// asks user for selection of what user they would like to edit
		int selection = (Integer.parseInt(this.gt.getInputString("Which staff member?")));
		// creates string from textfield rest is the same as addrecord with validations
		String[] inputData = this.gt.getTextFromEntry(0).split(",");
		String name = inputData[0];
		while (name.isBlank()) {
			name = this.gt.getInputString("Error with name input cannot be blank");

		}
		char gender = inputData[1].charAt(0);
		while (gender != 'm' && gender != 'M' && gender != 'f' && gender != 'F') {
			gender = this.gt.getInputString("Error with gender input must be M or F").charAt(0);

		}
		int birthYear = Integer.parseInt(inputData[2]);
		while (birthYear < 1900) {
			birthYear = Integer
					.parseInt(this.gt.getInputString("Error with birth year input must be greater than 1900"));

		}
		double hourlyRate = Double.parseDouble(inputData[3]);
		while (hourlyRate < 15.00 || hourlyRate > 99) {
			hourlyRate = Double
					.parseDouble(this.gt.getInputString("Error with hourly rate input must be between 15 and 99"));

		}
		Boolean term = Boolean.parseBoolean(inputData[4]);
		while (!term) {
			term = Boolean.parseBoolean(this.gt.getInputString("Error with term input must be True"));
		}// calls edit method in backend once all validated
		be.edit(selection, name, gender, birthYear, hourlyRate, term);
		refreshTable();
	}

	public void butRemove() {
		// calls method in backend to remove
		be.remove(Integer.parseInt(this.gt.getInputString("Which staff member?")));
		refreshTable();
	}
	// refreshs table with the use of backend that links to staff class to get the outputs for table
	public void refreshTable() {
		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < be.getStaff()) {
			this.gt.addRowToTable(0,
					(i + 1) + "\t" + be.getOutputs(i).getName() + "\t" + be.getOutputs(i).getGender() + "\t"
							+ be.getOutputs(i).getBirthYear() + "\t" + be.getOutputs(i).getHourlyRate() + "\t"
							+ be.getOutputs(i).getTerm());
			i++;
		}
	}

}
