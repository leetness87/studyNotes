import java.util.Scanner;
// main program frontend of rain gauge
public class StageB {
	RainGauge rg;
	private Scanner input;
	private int menuSelection;
	
	// initialisation 
	public StageB(RainGauge rainGauge) {
		// initialize the backend
		rg = rainGauge;
		this.input = new Scanner(System.in);
		System.out.println("Welcome to Rain Gauge 2.0");
		menu();
	}

	// menu for user selection with validation
	public void menu() {
		String menu = "**Main Menu**\n";
		menu += "1: Create Rain Gauge\n";
		menu += "2: Display Stored Rain Gauge\n";
		menu += "3: Add Rainfall to Gauge\n";
		menu += "4: Display Histogram\n";
		menu += "5: Show Max Rainfall\n";
		menu += "6: Check Threshold\n";
		menu += "7: Display Analysis\n";
		menu += "8: Exit\n";
		System.out.println(menu);
		System.out.print("Enter Choice\n");
		this.menuSelection = Integer.parseInt(this.input.nextLine());
		while (this.menuSelection < 1 || this.menuSelection > 8) {
			System.out.println("Error in choice\nPlease choose again");
			this.menuSelection = Integer.parseInt(this.input.nextLine());
		}
		if (this.menuSelection == 1) {
			addGauge();
		} else if (this.menuSelection == 2) {
			displayGauge();
		} else if (this.menuSelection == 3) {
			addRainfall();
		} else if (this.menuSelection == 4) {
			histogram();
		} else if (this.menuSelection == 5) {
			maxRainfall();
		} else if (this.menuSelection == 6) {
			threshhold();
		} else if (this.menuSelection == 7) {
			displayAnalysis();
		} else if (this.menuSelection == 8) {
			System.out.print("Have a nice day :)");
			System.exit(0);

		}
	}

	// calculate if days are below threshold
	private void threshhold() {
		System.out.println("Please enter the start day index:");
		int startDay = Integer.parseInt(this.input.nextLine());
		System.out.println("Please enter the end day index:");
		int endDay = Integer.parseInt(this.input.nextLine());
		System.out.println("Please enter the threshold:");
		int threshold = Integer.parseInt(this.input.nextLine());
		System.out.println(
				"From your selection day index " + startDay + " to " + endDay + " these were below the threshold of "
						+ threshold + " : " + rg.threshold(startDay, endDay, threshold));
		System.out.println();
		menu();

	}
	
	// calculate day with maximum rainfall
	private void maxRainfall() {
		rg.getMaximum();
		System.out.println("The maximimum rainfall is on index " + rg.getDayIndex() + " with a rainfall of "
				+ rg.getRainfallMax());
		System.out.println();
		menu();
	}
	
	// produce histogram on latest data
	private void histogram() {
		int x = rg.getI();
		int[] display = new int[x];
		display = rg.getRainfall();
		for (int y = 0; y < x; y++) {
			System.out.print("Rainfall for day " + y + "\t");
			for (int z = 10; z <= display[y]; z = z + 10) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println();
		menu();

	}

	// produce an analysis of all current data
	private void displayAnalysis() {
		int y;
		int below = 0;
		int above = 0;
		int equal = 0;
		int total = 0;
		int x = rg.getI();
		int[] analysis = new int[x];
		analysis = rg.getRainfall();
		for (y = 0; y < x; y++) {
			total = total + analysis[y];
		}
		double average = total / x;
		int round = (int) Math.round(average);
		for (y = 0; y < x; y++) {
			if (analysis[y] < average) {
				below++;
			} else if (analysis[y] > average) {
				above++;
			} else if (analysis[y] == average) {
				equal++;
			}
		}
		System.out.println("# of days recorded : " + x);
		System.out.println("Average (rounded) :" + round);
		System.out.println("# of days below average :" + below);
		System.out.println("# of days equal to average :" + equal);
		System.out.println("# of days above average :" + above);
		System.out.println();
		menu();
	}

	// add rainfall to gauge array
	private void addRainfall() {
		System.out.println("Enter Rainfall:");
		rg.setRainfall(Integer.parseInt(this.input.nextLine()));
		System.out.println();
		menu();
	}
	
	// display information of rain gauge
	private void displayGauge() {
		System.out.println(rg.getLocation());
		System.out.println(rg.getLatitude());
		System.out.println(rg.getLongtitude());
		System.out.println(rg.getIndex());
		System.out.println();
		menu();
	}

	// add or overwrite raingauge information
	public void addGauge() {
		System.out.println("Enter Location Name");
		rg.setLocation(this.input.nextLine());
		System.out.println("Enter Location Latitude");
		rg.setLatitude(Float.parseFloat(this.input.nextLine()));
		System.out.println("Enter Location Longtitude");
		rg.setLongtitude(Float.parseFloat(this.input.nextLine()));
		System.out.println("How many entries do you wish to make this Gauge?");
		rg.setRainfallIndex(Integer.parseInt(this.input.nextLine()));
		System.out.println();
		menu();

	}

}
