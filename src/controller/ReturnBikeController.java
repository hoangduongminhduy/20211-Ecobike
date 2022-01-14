package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import entity.Bike;
import entity.BikeType;
import entity.Renting;

/**
 * @author SnowPhantom
 *
 */
public class ReturnBikeController extends BaseController {
	
	private Renting currentRenting;
	
	/**
	 * Constructor
	 * @param renting
	 */
	public ReturnBikeController(Renting renting) {
		this.currentRenting = renting;
	}
	
	public ReturnBikeController() {	}
	
	/**
	 * Get current renting information
	 * @return renting
	 */
	public Renting getRentingInfo() {
		return currentRenting;
	}
	
	/**
	 * Conduct the return bike work
	 */
	public void returnBike(Renting renting) {
		renting.setRentingStatus(2);
		renting.setEndtime(LocalDateTime.now());
		renting.setCost(calculateFee(renting));
		
		renting.getBike().setBikeStatus(0);
	}
	
	/**
	 * Conduct calculating fee of the rent
	 * @param renting
	 * @return fee
	 */
	public long calculateFee(Renting renting) {
		long price = 0;
		long useTimeInMinutes = renting.getUseTime() / 60;
		
		if(useTimeInMinutes < 10)
    		price = 0;
    	else if(useTimeInMinutes < 30)
    		price = 10000;
    	else 
    		price = 10000 + (useTimeInMinutes - 30) /15 * 3000;
		
		Bike bike = renting.getBike();
    	
    	if(bike.getBikeType() == BikeType.ELECTRIC.getValue() || bike.getBikeType() == BikeType.COUPLE.getValue()) {
    		return (long) (price * 1.5);
    	}
    		
    	return price;
	}
}
