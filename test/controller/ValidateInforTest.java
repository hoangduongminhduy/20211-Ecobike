package controller;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.HomeController; 

class ValidateInforTest {
	
	private HomeController homeController;

	@BeforeEach
	void setUp() throws Exception {
		homeController = new HomeController();
	}

	@ParameterizedTest
	@CsvSource({
		"ngo 4 phuong$5 mai, false",
		"ngo 4@ hai ba trung, false",
		"ngach 5 #truong @dinh, false",
		"san van#$ dong, false",
		"Park04, true",
	})

	public void test3(String infor, boolean excepted) {
		boolean isValid = homeController.validateInfor(infor);
		assertEquals(isValid, excepted);
	}


}
