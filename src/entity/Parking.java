package entity;

/**
 * 
 * @author HoangLV
 *
 */

public class Parking {
	private String name;
    private String imgSrc;
    private String address;
    private int numBikes ;
    private int numBikeTypes;
    
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public int getNumBikes() {
		return numBikes;
	}
	
	public void setNumBikes(int numBikes) {
		this.numBikes = numBikes;
	}
	
	public int getNumBikeTypes() {
		return numBikeTypes;
	}
	
	public void setNumBikeTypes(int numBikeTypes) {
		this.numBikeTypes = numBikeTypes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
   
}
