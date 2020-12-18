import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class BackEnd {
	// class staff
	private Staff[] staff;
	// these are counters that keep track of the array lengths
	private int currentRecord;
	private int totalRecords;

	// constructor
	public BackEnd() {
		// initial creation of all set to 1 ready for the first inputs
		this.totalRecords = 1;
		this.currentRecord = 0;
		// initialize the staff class to the set variables
		this.staff = new Staff[this.totalRecords];

	}

	// method that can be used for both console and gterm alike
	public void addRecord(String firstName, String lastName, double hourlyRate, double hours, String input) {
		if (input != null) {
			// checks the array sizing and increases it if need by the use of temp made
			// arrays to store the data
			if (this.currentRecord >= this.totalRecords) {
				// increase array size by 1 because noone likes nulls
				this.totalRecords += 1;
				// takes all the saved data inside the staff class and stores them in temp array
				Staff[] longerarray = new Staff[this.totalRecords];
				// counter initialize
				int j = 0;
				// while loop to make sure all data is recieved
				while (j < this.currentRecord) {
					longerarray[j] = this.staff[j];
					j++;
				}
				// conversion back from the temp data to the real data
				this.staff = longerarray;
			}
			// inserts the given variable into a staff class with a elemental record
			this.staff[this.currentRecord] = new Staff(firstName, lastName, hourlyRate, hours, input);
			// increase the counter of how many records there are
			this.currentRecord += 1;

		}

	}

	// method to edit can be used by gterm or console
	public void editRecord(int k, String firstName, String lastName, double hourlyRate, double hours, String input) {
		if (input != null) {
			// dont think this needs any explanation just takes the staff at element k and
			// gives it the new values overiding the old
			this.staff[k] = new Staff(firstName, lastName, hourlyRate, hours, input);

		}
	}

	// method to delete a record
	public void deleteRecord(int k) {
		// creates a temp array to store data
		Staff[] remove = new Staff[this.totalRecords - 1];
		// counter initializations
		int j = 0;
		int i = 0;
		// checks to see if selection made was not the last entry
		if (k < this.currentRecord) {
			// while loop to transfer data from main to temp
			while (i < this.currentRecord) {
				// the beating heart of remove if k at its element value equals the counter it
				// skips over it
				if (i == (k - 1)) {
					i++;
				}
				// the transfer part
				remove[j] = this.staff[i];
				// counter increasing
				j++;
				i++;
			}
			// checks to see it the selection is the last entry made
		} else if (k == this.currentRecord) {
			// performs transfer untill the record is 1 less
			while (i < (this.currentRecord - 1)) {
				// the transfer part
				remove[j] = this.staff[i];
				// counter increasing
				j++;
				i++;
			}
		}
		// transfer of the temp data back to the original
		this.staff = remove;
		// decrease of both counters because noone likes nulls
		this.totalRecords--;
		this.currentRecord--;

	}

	// boolean method of saving
	public boolean save(String save) {
		// initialize the buffered writer and outfile to null
		BufferedWriter outFile = null;
		// initialize the boolean default to false
		boolean complete = false;
		try {
			// tries to overwrite the null to new value including the variable given by
			// either front end strings
			outFile = new BufferedWriter(new FileWriter(save));
			// counter initialize
			int i = 0;
			// while loop to save lines until end is reached
			while (i < this.currentRecord) {
				// check to make sure there is data to save
				if (staff[i] != null) {
					// writes the raw data to file seperating with a new line for each
					outFile.write(staff[i].getRawData() + "\n");
					// if nothing to write will print the following to file
				} else {
					outFile.write("Patient " + i + "is null");
				}
				// counter increase
				i++;
			}
			// changes boolean to true if wrote correctly
			complete = true;
			// closes the file
			outFile.close();
			// if an exception of any value occured will print to console
		} catch (Exception e) {
			System.out.println("Error Message " + e.getMessage());
		}
		// returns the value
		return complete;
	}

	// method to load a file
	public void load(String load) {
		// sets current record back to 0 to overwrite any data still open and does not
		// add to already excisting data
		this.currentRecord = 0;
		// initialize the reader and file to null
		BufferedReader inFile = null;
		try {
			// tries to read file with the given string from either front ends
			inFile = new BufferedReader(new FileReader(load));
			// creates string from line in file
			String currLine = inFile.readLine();
			// while loop that occurs while there is a next line to read
			while (currLine != null) {
				// array sizing check
				if (this.currentRecord >= this.totalRecords) {
					// increases array by 1 because noone likes nulls
					this.totalRecords += 1;
					// creation of temp array
					Staff[] longerarray = new Staff[this.totalRecords];
					// counter initialization
					int j = 0;
					// while loop to transfer data from main to temp
					while (j < this.currentRecord) {
						longerarray[j] = this.staff[j];
						j++;
					}
					// transfer of data back to a newly sized array
					this.staff = longerarray;
				}
				// creates an array from the line of text from the file splitting it with a
				// comma
				String[] rawData = currLine.split(",");
				// creates the staff record at the given element spot with the elements of
				// rawdata also saving the line data with commas as an extra string
				this.staff[this.currentRecord] = new Staff(rawData[0], rawData[1], Double.parseDouble(rawData[2]),
						Double.parseDouble(rawData[3]), currLine);
				// increase the record counter
				this.currentRecord++;
				// looks to next line in file to read
				currLine = inFile.readLine();
			}
			// closes the file
			inFile.close();
			// if any exception pops it will print out to console
		} catch (Exception e) {
			System.out.println("Error with load" + e.getMessage());
		}
	}

	// method to calculate overtime with a variable of a staff selection
	public double overtime(int i) {
		// initialize the return value
		double overtime = 0;
		// grabs data from staff class using a get method i created
		double rate = getOutputs(i).getHourlyRate();
		double hours = getOutputs(i).getHours();
		// conditional check if hours are greater than the normal rate hours the
		// overtime is calculated
		if (hours > 38) {
			overtime = (rate * 1.5) * (hours - 38);
		}
		// returns overtime if condition was not met returns 0 however if condition met
		// returns the calculated overtime
		return overtime;
	}

	// method to calculate normal pay with a variable of a staff selection
	public double normalPay(int i) {
		// initialize normal pay to 0
		double normalPay = 0;
		// grabs data from staff class using a get method i created
		double rate = getOutputs(i).getHourlyRate();
		double hours = getOutputs(i).getHours();
		// condition if hours exceed 38 will calculate the hours at max value 38
		if (hours > 38) {
			normalPay = rate * 38;
			// if former condition not met the rate is calculated normally with a simple
			// equation can use else as no other conditions to contribute
		} else {
			normalPay = rate * hours;
		}
		// returns the pay figure from one of the above conditions
		return normalPay;
	}

	// method to get staff information from class
	public Staff[] getStaff(int i) {
		return this.staff;
	}

	// method to see the current record counter
	public int getRecord() {
		return this.currentRecord;
	}

	// method to get individual data from elements within a staff element
	public Staff getOutputs(int i) {
		return this.staff[i];
	}

}
