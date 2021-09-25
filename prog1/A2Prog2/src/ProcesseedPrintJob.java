
public class ProcesseedPrintJob extends PrintJob {
	
	private boolean processed;
	private boolean curing;
	private boolean coating;
	private boolean pollishing;
	
	
	public ProcesseedPrintJob(int printID, String custID, String description, String material, int printQuantity, int volume, boolean processed, boolean curing, boolean coating, boolean pollishing) {
		super(printID, custID, description, material, printQuantity, volume);
		this.processed = processed;
		this.curing = curing;
		this.coating = coating;
		this.pollishing = pollishing;
	
	}

}
