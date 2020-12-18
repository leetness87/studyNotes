import java.awt.Color;

public class FrontEndGTerm {
	//usual stuff creating backend and a gterm
	BackEnd be;
	private GTerm gt;

	public FrontEndGTerm(BackEnd backEnd) {
		// initialize the backend class may be being lazy but if gterm can be gt you
		// know where im going
		be = backEnd;
		// the usual nothing new here
		this.gt = new GTerm(800, 800);
		this.gt.setXY(70, 50);
		this.gt.addTable(700, 600, "Index\tName\tGender\tBirth Year\tHourly Rate\tTerms Accepted");
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.RED);
		this.gt.setBackgroundColor(Color.GRAY);
		// makes button go down bottom
		this.gt.println("");
		// adds buttons that call on methods want to change colour of buttons but
		// couldnt figure it
		this.gt.addButton("Add Record", this, "addRecord");
		this.gt.addButton("Edit", this, "butEdit");
		this.gt.addButton("Remove", this, "butRemove");
		this.gt.println("");
		this.gt.addButton("Refresh", this, "refreshTable");
		this.gt.println("");
		// makes it differentiate from buttons
		this.gt.setFontColor(Color.BLACK);
		// enters line of text on gterm window and a text field under it for inputs
		this.gt.println("Enter Name, Gender, Year of Birth, Hourly rate, True if Correct");
		this.gt.addTextField("", 500);
	}

	// method to add a record
	public void addRecord() {
		// calls method from backend with the string from the entry here
		be.setInputs(this.gt.getTextFromEntry(0));
		// calls method from backend
		be.addRecord();
		refreshTable();
	}

	// method to edit
	public void butEdit() {
		// calls backend method
		be.edit();
		refreshTable();
	}

	// method to remove
	public void butRemove() {
		// calls backend method
		be.remove();
		refreshTable();
	}

	// the usual refresh table with a slight variance that i am using the backend
	// get methods to give me the array data i need to print
	public void refreshTable() {
		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < be.getStaff()) {
			this.gt.addRowToTable(0, (i + 1) + "\t" + be.getname()[i] + "\t" + be.getgender()[i] + "\t"
					+ be.getbirth()[i] + "\t" + be.getrate()[i] + "\t" + be.getterm()[i]);
			i++;
		}
	}

}
