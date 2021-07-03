// backend rain gauge store all data
public class RainGauge {
	
	private int[] rainfall;
	private String location;
	private Float latitude;
	private Float longtitude;
	private int index; // array length by user
	private int i;// to check against array length
	private int rainfallMax;
	private int dayIndex;


	// initialisation of all above
	public RainGauge() {
		this.rainfall = new int[0];
		this.location = "Blank";
		this.latitude = 0f;
		this.longtitude = 0f;
		this.index = 0;
		this.i = 0;
		this.rainfallMax = 0;
		this.dayIndex = 0;

	}

	// calculate day with maximum rainfall
	public void getMaximum() {
		this.rainfallMax = 0;
		this.dayIndex = 0;
		for (int y = 0; y < this.index; y++) {
			if (this.rainfall[y] > this.rainfallMax) {
				this.setRainfallMax(this.rainfall[y]);
				this.setDayIndex(y);
			}
		}
	}

	// calculate threshold below 
	public boolean threshold(int startDay, int endDay, int threshold) {
		int x = startDay;
		int y = endDay;
		int z = threshold;
		boolean below = true;
		for (; x <= y; x++) {
			if (this.rainfall[x] >= z) {
				below = false;
			}
		}
		return below;
	}

	//****all the setters and getters for frontend***
	
	public void setRainfallIndex(int index) {
		this.rainfall = new int[index];
		this.setIndex(index);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Float longtitude) {
		this.longtitude = longtitude;
	}

	public int[] getRainfall() {
		return this.rainfall;
	}

	// set rainfall overwrite if array is full
	public void setRainfall(int rainfall) {
		if (this.i >= (this.index)) {
			for (int y = 0; y < (this.index - 1); y++) {
				this.rainfall[y] = this.rainfall[y + 1];
			}
			this.rainfall[this.i - 1] = rainfall;
		} else if (this.i < this.index) {
			this.rainfall[this.i] = rainfall;
			this.i++;
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getI() {
		return this.i;
	}

	public int getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}

	public int getRainfallMax() {
		return rainfallMax;
	}

	public void setRainfallMax(int rainfallMax) {
		this.rainfallMax = rainfallMax;
	}



	
}
