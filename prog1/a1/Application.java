
public class Application {
	private RainGauge rainGauge;
	private StageB stageB;

	public Application() {
		this.rainGauge = new RainGauge();
		this.stageB = new StageB(this.rainGauge);
		
	}
	public static void main(String[] args) {
		Application app = new Application();

	}

}
