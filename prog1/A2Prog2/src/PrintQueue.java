
public class PrintQueue {

	private static PrintJob[] printJob;

	public static void initialize(int i) {
		printJob = new PrintJob[i];

	}

	public static void addJob(int printID, String custID, String description, String material, int printQuantity,
			int volume) {
		printJob[StageB.currentIndex] = new PrintJob(printID, custID, description, material, printQuantity, volume);

	}

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

	public static void displayAll(int i) {
		printJob[i].displayAllDetails();
	}

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