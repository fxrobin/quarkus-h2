package fr.fxjavadevblog.qjg.utils;

import java.time.Duration;

public class DateTimeUtils {
	
	public static String format(Duration d) {
	    long days = d.toDays();
	    d = d.minusDays(days);
	    long hours = d.toHours();
	    d = d.minusHours(hours);
	    long minutes = d.toMinutes();
	    d = d.minusMinutes(minutes);
	    long seconds = d.getSeconds() ;
	    return 
	            (days ==  0 ? "" : days  +"D ")+ 
	            (hours == 0?"":hours+" h ")+ 
	            (minutes ==  0?"":minutes+" m ")+ 
	            (seconds == 0?"":seconds+" s");
	}

}
