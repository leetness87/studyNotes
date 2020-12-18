import java.io.*;

import javax.swing.JOptionPane;

public class BackEnd {
	// the only array
	private Patient[] patient;
	// counters to keep track
	private int currentRecord;
	private int totalRecords;

	public BackEnd() {
		// initialization
		this.currentRecord = 0;
		this.totalRecords = 1;
		this.patient = new Patient[this.totalRecords];
	}

	public void readFile(String filePath) {
		BufferedReader inFile = null;
		int lineNum = 0;
		try {
			inFile = new BufferedReader(new FileReader(filePath));
			lineNum = 0;
			String currLine = inFile.readLine();
			while (currLine != null) {
				if (this.currentRecord >= this.totalRecords) {
					this.totalRecords += 1;
					Patient[] longerarray = new Patient[this.totalRecords];
					int j = 0;
					while (j < this.currentRecord) {
						longerarray[j] = this.patient[j];
						j++;
					}
					this.patient = longerarray;
				}
				String[] rawData = currLine.split(",");
				this.patient[this.currentRecord] = new Patient(Double.parseDouble(rawData[0]), rawData[1], rawData[2],
						rawData[3], rawData[4], rawData[5], rawData[6], rawData[7], currLine);
				this.currentRecord++;
				lineNum++;
				currLine = inFile.readLine();
			}
			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean writeFile() {
		BufferedWriter outFile = null;
		boolean complete = false;
		try {
			outFile = new BufferedWriter(new FileWriter("new.data"));
			int i = 0;
			while (i < currentRecord) {
				if (patient[i] != null) {
					outFile.write(patient[i].getRawData() + "\n");

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
	public void remove(int k) {
		if (k <= this.currentRecord) {
			// new temp array
			Patient[] remove = new Patient[this.totalRecords - 1];
			int j = 0;
			int i = 0;
			// transfers data from main to temp if the selection is the same it skips over
			// on 1 side and continues
			if (k < this.currentRecord) {
				while (i < this.currentRecord) {
					if (i == (k - 1)) {
						i++;
					}
					remove[j] = this.patient[i];
					j++;
					i++;
				}
				// if equal will insist it is last entry so array does plain copy over to new
				// one that is shorter in length could make this easier but this works
			} else if (k == this.currentRecord) {
				while (i < (this.currentRecord - 1)) {
					remove[j] = this.patient[i];
					j++;
					i++;
				}
			}
			// transfers back data from temp to main and decrease the counters
			this.patient = remove;
			this.totalRecords--;
			this.currentRecord--;
		} // if the int input is out of range will show error message used joptionpane
			// just so wasnt reliant on gterm or console
		else {
			JOptionPane.showMessageDialog(null, "Not a valid input");
		}
	}

	
	public Patient[] getPatient(int i) {
		return this.patient;

	}

	public int getRecord() {
		return this.currentRecord;
	}

}
