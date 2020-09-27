package com.nt.basics;

import java.text.SimpleDateFormat;

public class DateConversions {

	public static void main(String[] args) throws Exception {
		//converting simple date to util date
		String d1="2020-30-08"; //DD-MM-YYYY
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-dd-MM"); //it holds the pattern of simple date
		java.util.Date ud1=sdf.parse(d1); //simple date converted to util date format
		System.out.println("util Date:"+ud1);
		
		
		//converting util date to sql date
		long ms=ud1.getTime(); //here getTime() will return the time in milliseconds time (by calculating from jan 1st 1970 00:00hrs to given date and time)
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println("SQL Date1::"+sd1);
		
		//if string date is in sql date format then,we don't need to convert it to util format.we can directly convert the string date format to sql date format
		
		String d2="2020-08-30"; //yyyy-MM-dd
		java.sql.Date sd2=java.sql.Date.valueOf(d2);
		System.out.println("SQL Date2:"+sd2);
		
		SimpleDateFormat sdf2=new SimpleDateFormat("MMM-yyyy-dd");
		String d3=sdf2.format(sd2);
		System.out.println("String Date:"+d3);
	}

}
