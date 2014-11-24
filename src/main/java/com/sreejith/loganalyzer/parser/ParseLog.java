package com.sreejith.loganalyzer.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseLog {
	
	@SuppressWarnings("deprecation")
	public static int getHour(String timestamp) throws ParseException {

    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");
    	
    	Date mydate = simpleDateFormat.parse(timestamp);

    	return mydate.getHours();
 
      }

}
