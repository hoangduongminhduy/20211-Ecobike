package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Renting {
	// CRUD
	private int rentingStatus;
	private LocalDateTime startime;
	private LocalDateTime endtime;
	private Bike bike;
	private long cost;
	
	public int getRentingStatus() {
		return rentingStatus;
	}

	public void setRentingStatus(int rentingStatus) {
		this.rentingStatus = rentingStatus;
	}

	public LocalDateTime getStartime() {
		return startime;
	}

	public void setStartime(LocalDateTime startime) {
		this.startime = startime;
	}

	public LocalDateTime getEndtime() {
		return endtime;
	}

	public void setEndtime(LocalDateTime endtime) {
		this.endtime = endtime;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}
	
	public long getUseTime() {
		return ChronoUnit.SECONDS.between(this.startime, this.endtime);
	}
}
