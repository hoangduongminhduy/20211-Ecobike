package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Parking;

public class BaseController {
	
	
	public ObservableList<Parking> getParkingData(){
		ObservableList<Parking> listPark = FXCollections.observableArrayList();
		Parking parking;
		
		parking = new Parking();
		parking.setName("BX01");
		parking.setAddress("Khu Nhà  D3-5");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX02");
		parking.setAddress("Khu Nhà  D9");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		
		parking = new Parking();
		parking.setName("BX03");
		parking.setAddress("Khu Nhà  D8");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX04");
		parking.setAddress("Khu Nhà  C2");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX05");
		parking.setAddress("Tòa Nhà  B1");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(90);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX06");
		parking.setAddress("Khu KTX B8");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(80);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX07");
		parking.setAddress("Khu KTX B6");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX08");
		parking.setAddress("Khu KTX B10");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(90);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX09");
		parking.setAddress("Khu Nhà  TC");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(90);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX10");
		parking.setAddress("Thư viện Tạ Quang Bửu");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(70);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX11");
		parking.setAddress("Sân vận động Bách Khoa");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(70);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX12");
		parking.setAddress("Khu Nhà  D6");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX13");
		parking.setAddress("Khu Nhà  D8");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX14");
		parking.setAddress("Bể bơi bách khoa");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		parking = new Parking();
		parking.setName("BX14");
		parking.setAddress("KTX B7");
		parking.setImgSrc("/image/logo.png");
		parking.setNumBikes(100);
		parking.setNumBikeTypes(10);
		listPark.add(parking);
		
		
		return listPark;
		
	}
	
}
