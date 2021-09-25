
public class PrintQueue {

	private static PrintJob[] printJob;

	public static void initialize(int i) {
		printJob = new PrintJob[i];

	}

	// adds job as Print Job
	public static void addJob(int printID, String custID, String description, String material, int printQuantity,
			int volume) {
		printJob[StageB.currentIndex] = new PrintJob(printID, custID, description, material, printQuantity, volume);
	}
	
	// adds job as ecxtended processed
	public static void addProcessed(int printID, String custID, String description, String material, int printQuantity,
			int volume,boolean process1,boolean process2,boolean process3) {
		printJob[StageB.currentIndex] = new ProcessedPrintJob(printID, custID, description, material, printQuantity, volume, process1, process2, process3);
	}

	// search with print id and returns for display purposes
	public static int search(int input, boolean exists, int currentIndex) {
		int i;
		int k = 0;
		for (i = 0; i < currentIndex; i++) {
			if (input == printJob[i].getPrintID()) {
				exists = true;
				k = i;
			}
		}
		return k;

	}

	// simple array drop from front
	public static void removeJob() {
		PrintJob[] remove = new PrintJob[StageB.maxPrintJobs];
		int j = 0;
		int i = 1;
		while (i < StageB.currentIndex) {
			remove[j] = printJob[i];
			j++;
			i++;
		}
		printJob = remove;
		StageB.currentIndex--;

	}

	// display all details from either printjob or processed
	public static void displayAll(int i) {

			printJob[i].displayAllDetails();
		
		
	}

	// shows the queue
	public static void displayBasic() {
		int i = 0;
		System.out.println("");
		System.out.format("+---------------------------------------------+%n");
		System.out.format("|              Current Jobs in the Queue      |%n");
		System.out.format("+---------------------------------------------+%n");
		while (i < StageB.currentIndex) {
			printJob[i].print();
			System.out.format("+---------------------------------------------+%n");
			i++;
		}
		System.out.println("");

	}

}