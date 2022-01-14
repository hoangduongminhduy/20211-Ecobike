package entity;

public enum BikeType {
	NONE(0),
	ELECTRIC(1),
	COUPLE(2);

	private int value;
	
	BikeType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
