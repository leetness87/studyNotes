public class Application {
	private BackEnd backEnd;
	private FrontEndGTerm uiGT;
	private FrontEndConsole uiConsole;

	public Application() {
		this.backEnd = new BackEnd();
		this.uiGT = new FrontEndGTerm(this.backEnd);
		this.uiConsole = new FrontEndConsole(this.backEnd);
	}

	public static void main(String[] args) {
		Application app = new Application();
	}
}
