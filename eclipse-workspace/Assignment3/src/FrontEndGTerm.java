import java.awt.Color;
import java.awt.Font;

public class FrontEndGTerm {
	// usual stuff creating backend and a gterm
	BackEnd be;
	private GTerm gtMain;
	private GTerm gtTable;
	private GTerm gtConsole;

	public FrontEndGTerm(BackEnd backEnd) {
		// initialize the backend class may be being lazy but if gterm can be gt you
		// know where im going
		be = backEnd;
		// the usual nothing new here
		// initialize the window for the console or printouts
		this.gtConsole = new GTerm(600, 300);
		// moves window to top left so user does not need to move it
		this.gtConsole.setBounds(1, 1, 600, 300);
		// initializes the gterm window for the table
		this.gtTable = new GTerm(900, 800);
		// moves the window to the rhs instead of being centred so use does not need to
		// move windows on startup
		this.gtTable.setBounds(601, 1, 900, 800);
		// initialize the second gterm which is the main for entries and menu bar
		this.gtMain = new GTerm(600, 500);
		// moves the window to the left bottom so that the user does not need to move
		// window on startup
		this.gtMain.setBounds(1, 301, 600, 500);
		// setting the xy and the colours of the windows
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
		// adds text to the window
		this.gtMain.println("Welcome to Wage Calculator 2.0");
		this.gtMain.println("Please make your selection\nfrom the available buttons");
		// reduction in font size to differ the header to the text
		this.gtMain.setFontSize(12);
		// the following adds the buttons to the main gterm with each of them having
		// there own seperate methods that are called for in between is println's for
		// spacing purposes
		this.gtMain.addButton("Add Record", this, "addRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Edit Record", this, "editRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Delete Record", this, "deleteRecord");
		this.gtMain.println("");
		this.gtMain.addButton("Load File", this, "loadFile");
		this.gtMain.println("");
		this.gtMain.addButton("Save File", this, "saveFile");
		this.gtMain.println("");
		this.gtMain.addButton("Exit", this, "exit");
		this.gtMain.println("");
		// increase in font size again for the textfield to be larger then menu select
		// making it stick out more
		this.gtMain.setFontSize(16);
		this.gtMain.println("Enter First Name, Last name, Hourly rate , Hours\nSeperate by a commas");
		// adds a text field to the bottom of the window
		this.gtMain.addTextField("Enter Text Here", 400);
		this.gtMain.println("");
		this.gtMain.println("Enter in values here to edit selection seperated by commas\n");
		this.gtMain.addTextField("", 200);
		// the following adds the table to the gterm table window aswell as buttons to
		// remove and edit which calls the relevant methods also setting font size and
		// xy to make most of sizing
		this.gtTable.setXY(1, 1);
		this.gtTable.setFontSize(14);
		this.gtTable.addTable(900, 700, "Index\tFirst name\tLastname\tHourly Rate\tHours\tNormal Pay\tOvertime\tTotal");
		this.gtTable.println("");
	}

	// method to add a record
	public void addRecord() {
		String input = this.gtMain.getTextFromEntry(0);
		be.addRecord(input);
		refreshTable();

	}

	// method to edit
	public void editRecord() {
		int k = this.gtTable.getIndexOfSelectedRowFromTable(0);
		String input = this.gtMain.getTextFromEntry(1);
		be.editRecord(k, input);
		refreshTable();
	}

	// method to remove
	public void deleteRecord() {
		int k = this.gtTable.getIndexOfSelectedRowFromTable(0);
		k = (k + 1);
		be.deleteRecord(k);
		refreshTable();
	}

	public void load() {
		be.load();
	}

	public void save() {
		be.save();
	}

	// the usual refresh table with a slight variance that i am using the backend
	// get methods to give me the array data i need to print
	public void refreshTable() {
		this.gtTable.clearRowsOfTable(0);
		int i = 0;
		Staff[] staff = be.getStaff(i);
		while (i < be.getRecord()) {
			this.gtTable.addRowToTable(0, (i + 1) + "\t" + staff[i].getRawData().replace(",", "\t"));
			i++;

		}

	}
}
