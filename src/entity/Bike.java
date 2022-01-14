package entity;

public class Bike {
	private int bikeID;
	private String bikeName;
	private String bikeTypeName;
	private String bikeCode;
	private int bikeStatus; // string or int
	private String batteryStatus; 
	
	private String dockName;
	private int bikeType; // string or int
	private String licensePlate;
	private String color;
	
	
	
	public Bike(int bikeId, String bikeCode) {
		this.bikeID = bikeId;
		this.bikeCode = bikeCode;
	}
	
	public Bike(int bikeId, String dockName, int bikeType, String licensePlate, String battery, String color) {
		this.bikeID = bikeId;
		this.dockName = dockName;
		this.bikeType = bikeType;
		this.licensePlate = licensePlate;
		this.batteryStatus = battery;
		this.color = color;
	}
	
	public Bike() {
		
	}
	
	public int getBikeID() {
		return bikeID;
	}
	public void setBikeID(int bikeId) {
		this.bikeID = bikeId;
	}
	public String getBikeName() {
		return bikeName;
	}
	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}
	public String getBikeTypeName() {
		return bikeTypeName;
	}
	public void setBikeTypeName(String bikeTypeName) {
		this.bikeTypeName = bikeTypeName;
	}
	public String getBikeCode() {
		return bikeCode;
	}
	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}
	public int getBikeType() {
		return bikeType;
	}
	public void setBikeType(int bikeType) {
		this.bikeType = bikeType;
	}
	
	void checkAvailable() {}
	public int getBikeStatus() {
		return bikeStatus;
	}
	public void setBikeStatus(int bikeStatus) {
		this.bikeStatus = bikeStatus;
	}

	public String getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(String batteryStatus) {
		this.batteryStatus = batteryStatus;
	}
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDockName() {
		return dockName;
	}
	public void setDockName(String dockName) {
		this.dockName = dockName;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
}
