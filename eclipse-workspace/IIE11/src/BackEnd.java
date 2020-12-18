import java.io.*;

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

	public void addData() {
		BufferedReader inFile = null;
		int lineNum = 0;
		try {
			inFile = new BufferedReader(new FileReader("diagnosis.data"));
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
				this.patient[this.currentRecord]= new Patient(Double.parseDouble(rawData[0]),rawData[1],rawData[2],rawData[3],rawData[4],rawData[5],rawData[6],rawData[7],currLine);
				this.currentRecord++;
				lineNum++;
				currLine = inFile.readLine();
			}
			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Patient[] getPatient(int i) {
		return this.patient;
		
	}
	public int getRecord() {
		return this.currentRecord;
	}


}

