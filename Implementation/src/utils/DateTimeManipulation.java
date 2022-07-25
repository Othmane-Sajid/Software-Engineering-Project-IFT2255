package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeManipulation {

	public static String generateCurrentTime(boolean extended) {
		DateTimeFormatter dtf;
		if (extended)
			dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		else
			dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
	}
	
	public static int[] calculateWeeks(String start, String end) {	
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        int[] res = new int[2];
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start);
            d2 = format.parse(end);

            //ms
            long diff = d2.getTime() - d1.getTime();
            
            long diffDays = diff / (24 * 60 * 60 * 1000);
            int diffWeeks = (int) (diffDays / 7);
            int modDays = (int) (diffDays % 7);
            
            res[0] = diffWeeks;
            res[1] = modDays;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        return res;
    }

	public static String getFormattedDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");  
		return sdf.format(date);
	}
	
	// https://stackoverflow.com/questions/43387634/current-week-monday-date
	public static Date getCurrentMonday() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c.getTime();
	}
	
	public static Date getCurrentFriday() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return c.getTime();
	}
	
	public static String getFormattedCurrentMonday() {
		return getFormattedDate(getCurrentMonday()) + " 00:00:00";
	}
	
	public static String getFormattedCurrentFriday() {
		return getFormattedDate(getCurrentFriday()) + " 23:59:59";
	}
	
	// https://stackoverflow.com/questions/57269997/how-to-convert-date-from-ddmmyyyy-hhmmss-to-yyyy-mm-dd-hhmmss-sss
	public static String convertFormat(String date) {
		DateTimeFormatter inSDF = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		DateTimeFormatter outSDF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		
		return outSDF.format(inSDF.parse(date));		
	}
	
	public static String convertFormatDate(String date) {
		DateTimeFormatter inSDF = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		DateTimeFormatter outSDF = DateTimeFormatter.ofPattern("dd-MM-yyyy");		
		return outSDF.format(inSDF.parse(date));	
	}
}





