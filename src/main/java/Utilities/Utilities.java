package Utilities;

import java.util.Date;

public class Utilities {
	
	public static String timegenerator() {
		Date date=new Date();
		String timstampString=date.toString().replaceAll(" ", "_").replace(":", "_");
		return timstampString;

	}

}
