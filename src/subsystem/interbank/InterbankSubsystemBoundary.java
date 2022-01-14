package subsystem.interbank;

import common.exception.UnrecognizedException;
import util.API;

public class InterbankSubsystemBoundary {
	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
