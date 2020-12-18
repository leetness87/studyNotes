import javax.swing.JOptionPane;

public class BackEnd {
	// the only array
	private Staff[] staff;
	// counters to keep track
	private int currentStaff;
	private int arraysize;

	public BackEnd() {
		// initialization
		this.currentStaff = 0;
		this.arraysize = 1;
		this.staff = new Staff[this.arraysize];
	}

	// method to add record has 5 parameters from either front ends and puts them
	// into the array as needed also has a check to make sure the arraylength is
	// adequate and will increase if needed
	public void addRecord(String name, char gender, int birthYear, double hourlyRate, boolean term) {
		if (this.currentStaff >= this.arraysize) {
			this.arraysize += 1;
			Staff[] longerarray = new Staff[this.arraysize];
			int j = 0;
			while (j < this.currentStaff) {
				longerarray[j] = this.staff[j];
				j++;
			}
			this.staff = longerarray;
		}
		this.staff[this.currentStaff] = new Staff(name, gender, birthYear, hourlyRate, term);
		this.currentStaff += 1;
	}

	// edit method with 6 parameters same as add record but calls for an int for the
	// selection by the user
	public void edit(int i, String name, char gender, int birthYear, double hourlyRate, boolean term) {
		//checks to make sure in range
		if (i <= this.currentStaff) {
			// makes i elemental number
			i = i - 1;
			// writes over the previous entry
			this.staff[i] = new Staff(name, gender, birthYear, hourlyRate, term);
			// prints error if not in range
		} else {
			JOptionPane.showMessageDialog(null, "Not a valid input");

		}
	}

	// remover method with 1 parameter
	public void remove(int k) {
		// checks to see if within range
		if (k <= this.currentStaff) {
			// new temp array
			Staff[] remove = new Staff[this.arraysize - 1];
			int j = 0;
			int i = 0;
			// transfers data from main to temp if the selection is the same it skips over
			// on 1 side and continues
			if (k < this.currentStaff) {
				while (i < this.currentStaff) {
					if (i == (k - 1)) {
						i++;
					}
					remove[j] = this.staff[i];
					j++;
					i++;
				}
				// if equal will insist it is last entry so array does plain copy over to new
				// one that is shorter in length could make this easier but this works
			} else if (k == this.currentStaff) {
				while (i < (this.currentStaff - 1)) {
					remove[j] = this.staff[i];
					j++;
					i++;
				}
			}
			// transfers back data from temp to main and decrease the counters
			this.staff = remove;
			this.arraysize--;
			this.currentStaff--;
		} // if the int input is out of range will show error message used joptionpane
			// just so wasnt reliant on gterm or console
		else {
			JOptionPane.showMessageDialog(null, "Not a valid input");
		}
	}

	// method so frontend can see how many staff there are in counter
	public int getStaff() {
		return this.currentStaff;
	}

	// method so front end can print out data stored in arrays with the use of staff
	// class
	public Staff getOutputs(int i) {
		return this.staff[i];

	}

}

//bob,m,1980,33,true