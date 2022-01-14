package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	public static String SecondsToDateTimeString(long seconds) {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		
		return String.format("%d hours %d minutes", hours, minutes);
	}
	
	public static String SecondsToHHMMString(long seconds) {
		long m = (seconds / 60);
		long s = (seconds % 60);
		
		return String.format("%d m %d s", m, s);
	}
	
	public static String FormatDateTime(LocalDateTime datetime) {
		if (datetime == null) return "";
		return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
