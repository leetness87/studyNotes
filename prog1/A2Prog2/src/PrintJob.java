
public class PrintJob {
	static int printID2 = 100;
	protected int printID;
	protected String custID; // Customer ID
	protected String description;
	protected String material;
	protected int printQuantity;
	protected int volume;


	public PrintJob(int printID, String custID, String description, String material, int printQuantity, int volume) {
		this.printID = printID;
		this.custID = custID;
		this.description = description;
		this.material = material;
		this.printQuantity = printQuantity;
		this.volume = volume;

		
	}

	public void print() {
		System.out.println("Print ID :" + this.printID);
		System.out.println("Customer ID :" + this.custID);
		System.out.println("Description :" + this.description);
	}

	public static void searchDisplay(int i) {
		
	}
	public void displayAllDetails() {
		String processed = "";
		double raw = rawPrice(this.material, this.volume);
		double total = calcTotalPrice(this.printQuantity, raw);
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
		String leftAlignFormat = "| %-9s | %-14s | %-14s | %-14s | %-9s | %-9s | %-11s | %-11s | %-11s |%n";
		System.out.format(leftAlignFormat, printID, lowerName, lowerDescription, material, printQuantity, volume,
				"$" + printPrice(raw), "$" + printPrice(total), processed);

	}

	public static String printPrice(double input) {
		String price;
		price = new java.text.DecimalFormat("0.00").format(input);
		return price;
	}

	public double calcTotalPrice(int quantity, double raw) {
		double total = quantity * raw;
		return total;
	}

	public double rawPrice(String material, int volume) {
		double raw = 0;
		if (material.equalsIgnoreCase("PLA")) {
			raw = (0.05 * volume);
		} else if (material.equalsIgnoreCase("ABS")) {
			raw = (0.06 * volume);
		} else if (material.equalsIgnoreCase("Plastic Nylon")) {
			raw = (0.07 * volume);
		} else if (material.equalsIgnoreCase("Acrylic")) {
			raw = (0.09 * volume);
		}
		return raw;
	}

	public int getPrintID() {
		return printID;
	}

	public void setPrintID(int printID) {
		this.printID = printID;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getPrintQuantity() {
		return printQuantity;
	}

	public void setPrintQuantity(int printQuantity) {
		this.printQuantity = printQuantity;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}



}
