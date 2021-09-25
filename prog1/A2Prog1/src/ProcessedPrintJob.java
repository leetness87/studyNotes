
public class ProcessedPrintJob extends PrintJob {

	private boolean curing;
	private boolean coating;
	private boolean pollishing;

	// constructor using super class
	public ProcessedPrintJob(int printID, String custID, String description, String material, int printQuantity,
			int volume, boolean curing, boolean coating, boolean pollishing) {
		super(printID, custID, description, material, printQuantity, volume);
		this.curing = curing;
		this.coating = coating;
		this.pollishing = pollishing;

	}

	// override of printjob if returned as this class is overused code could have
	// made this easier but ran out of time
	@Override
	public void displayAllDetails() {

		double raw = rawPrice(this.material, this.volume);
		double total = newTotal(this.printQuantity, this.volume, raw);
		// addition of limits to not mess up table saves alot more coding for tables
		String lowerDescription = this.description;
		if (lowerDescription.length() > 14) {
			lowerDescription = lowerDescription.substring(0, 14);
		}
		String lowerName = this.custID;
		if (lowerName.length() > 14) {
			lowerName = lowerName.substring(0, 14);
		}
		// table align and spacing to suite
		String leftAlignFormat = "| %-9s | %-14s | %-14s | %-14s | %-9s | %-9s | %-11s | %-11s |%n";
		System.out.format(leftAlignFormat, printID, lowerName, lowerDescription, material, printQuantity, volume,
				"$" + printPrice(raw), "$" + printPrice(total));

	}

	// calculate all processing and discounts
	public double newTotal(double quantity, double volume, double raw) {
		double total = quantity * raw;
		double extra = 0;
		if (curing) {
			extra = extra + (volume * quantity * 0.01);
		}
		if (coating) {
			extra = extra + (volume * quantity * 0.005);
		}
		if (pollishing) {
			extra = extra + (quantity * 0.5);
		}
		total = total + extra;
		if (quantity > 500) {
			total = total - (total / 10);
		}
		if (total < 60.50) {
			total = 60.50;
		}
		return total;

	}

}
