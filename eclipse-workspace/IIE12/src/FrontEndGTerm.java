import java.awt.Color;

public class FrontEndGTerm {
	// usual stuff creating backend and a gterm
	BackEnd be;
	private GTerm gt;

	// constructor make the gterm window and adds all the elements
	public FrontEndGTerm(BackEnd backEnd) {
		// initialize the backend
		be = backEnd;
		this.gt = new GTerm(800, 800);
		this.gt.setXY(70, 50);
		this.gt.addTable(700, 600,
				"Patient\tTemperature\tNausia\tLumbar Pain\tUrine Pushing\tMicturation Pain\tUrethra Problems\tInflamation Diagnosis\tNephritus Diagnosis");
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.RED);
		this.gt.setBackgroundColor(Color.GRAY);
		this.gt.println("");
		this.gt.addButton("Load file", this, "readFile");
		this.gt.println("");
		this.gt.addButton("Save file", this, "saveFile");
		this.gt.println("");
		this.gt.addButton("Remove staff", this, "remove");
		this.gt.addButton("Refresh", this, "refreshTable");
		this.gt.println("");
		this.gt.setFontColor(Color.BLACK);
	}

	public void readFile() {
		String path = this.gt.getFilePath();
		this.be.readFile(path);
	}
	public void saveFile() {
		if(this.be.writeFile()) {
			this.gt.showMessageDialog("Good Job");
		}else {
			this.gt.showMessageDialog("ERROR 100001000001");
		}
	}
	public void remove() {
		int i = this.gt.getIndexOfSelectedRowFromTable(0);
		i = (i+1);
		be.remove(i);
	}

	public void refreshTable() {
		this.gt.clearRowsOfTable(0);
		int i = 0;
		Patient[] patient = be.getPatient(i);
		while (i < be.getRecord()) {
			this.gt.addRowToTable(0,(i+1)+"\t"+patient[i].getRawData().replace(",", "\t"));
			i++;
		}
	}
}