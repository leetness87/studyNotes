import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class BackEnd {
	// initialization of arrays and variable to the object member variable level
	// these are the main arrays that store data
	private Staff[] staff;
	// these are counters that keep track of the array lengths
	private int currentRecord;
	private int totalRecords;

	// constructor
	public BackEnd() {
		// initial creation of all set to 1 ready for the first inputs
		this.totalRecords = 1;
		this.currentRecord = 0;
		this.staff = new Staff[this.totalRecords];

	}

	// method that can be used for both console and gterm alike
	public void addRecord(String input) {
		if (input != null) {
			String[] rawData = input.split(",");
			try {
				String firstName = rawData[0];
				String lastName = rawData[1];
				double hourlyRate = Double.parseDouble(rawData[2]);
				double hours = Double.parseDouble(rawData[3]);
				if (this.currentRecord >= this.totalRecords) {
					this.totalRecords += 1;
					Staff[] longerarray = new Staff[this.totalRecords];
					int j = 0;
					while (j < this.currentRecord) {
						longerarray[j] = this.staff[j];
						j++;
					}
					this.staff = longerarray;
				}
				this.staff[this.currentRecord] = new Staff(firstName, lastName, hourlyRate, hours, input);
				this.currentRecord += 1;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "There was an error with inputs");

			}
		}

	}

	// method to edit can be used by gterm or console
	public void editRecord(int k, String input) {
		if (k <= this.currentRecord) {
			if (input != null) {
				String[] rawData = input.split(",");
				try {
					String firstName = rawData[0];
					String lastName = rawData[1];
					double hourlyRate = Double.parseDouble(rawData[2]);
					double hours = Double.parseDouble(rawData[3]);
					this.staff[k] = new Staff(firstName, lastName, hourlyRate, hours, input);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There was an error with inputs");

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error with selection number");
		}
	}

	public void deleteRecord(int k) {
		if (k <= this.currentRecord) {
			// new temp array
			Staff[] remove = new Staff[this.totalRecords - 1];
			int j = 0;
			int i = 0;
			// transfers data from main to temp if the selection is the same it skips over
			// on 1 side and continues
			if (k < this.currentRecord) {
				while (i < this.currentRecord) {
					if (i == (k - 1)) {
						i++;
					}
					remove[j] = this.staff[i];
					j++;
					i++;
				}
				// if equal will insist it is last entry so array does plain copy over to new
				// one that is shorter in length could make this easier but this works
			} else if (k == this.currentRecord) {
				while (i < (this.currentRecord - 1)) {
					remove[j] = this.staff[i];
					j++;
					i++;
				}
			}
			// transfers back data from temp to main and decrease the counters
			this.staff = remove;
			this.totalRecords--;
			this.currentRecord--;
		} // if the int input is out of range will show error message used joptionpane
			// just so wasnt reliant on gterm or console
		else {
			JOptionPane.showMessageDialog(null, "Not a valid input");
		}
	}

	public boolean save() {
		BufferedWriter outFile = null;
		boolean complete = false;
		try {
			outFile = new BufferedWriter(new FileWriter("data.txt"));
			int i = 0;
			while (i < currentRecord) {
				if (staff[i] != null) {
					outFile.write(staff[i].getRawData() + "\n");

				} else {
					outFile.write("Patient " + i + "is null");
				}
				i++;
			}
			complete = true;
			outFile.close();
		} catch (Exception e) {
			System.out.println("Error Message " + e.getMessage());
		}
		return complete;
	}

	public void load() {
		BufferedReader inFile = null;
		int lineNum = 0;
		try {
			inFile = new BufferedReader(new FileReader("data.txt"));
			lineNum = 0;
			String currLine = inFile.readLine();
			while (currLine != null) {
				if (this.currentRecord >= this.totalRecords) {
					this.totalRecords += 1;
					Staff[] longerarray = new Staff[this.totalRecords];
					int j = 0;
					while (j < this.currentRecord) {
						longerarray[j] = this.staff[j];
						j++;
					}
					this.staff = longerarray;
				}
				String[] rawData = currLine.split(",");
				this.staff[this.currentRecord] = new Staff(rawData[0], rawData[1],
						Double.parseDouble(rawData[2]), Double.parseDouble(rawData[3]), currLine);
				this.currentRecord++;
				lineNum++;
				currLine = inFile.readLine();
			}
			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Staff[] getStaff(int i) {
		return this.staff;
	}

	public int getRecord() {
		return this.currentRecord;
	}

}
