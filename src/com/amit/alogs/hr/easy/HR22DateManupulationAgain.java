package com.amit.alogs.hr.easy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

public class HR22DateManupulationAgain {
	
	public static final String GREGORIAN_CHANGE_DATE = "01.01.1919";
	public static final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
//    static String dayOfProgrammer(int year) throws Exception{
//    	GregorianCalendar jCal = new GregorianCalendar();
//    	jCal.setGregorianChange(new Date(Long.MAX_VALUE));
//    	GregorianCalendar gCal = new GregorianCalendar();
//    	gCal.setGregorianChange(new Date(Long.MIN_VALUE));
//    	if(year < 1918){
//    		//Use jCal
//        	jCal.set(year,0,0);
//        	jCal.add(Calendar.DAY_OF_YEAR, 244);
//        	return df.format(jCal.getTime());
//    	}else if(year >= 1919){
//    		//use gCal
//        	gCal.set(year,0,0);
//        	gCal.add(Calendar.DAY_OF_YEAR, 256);
//        	return df.format(gCal.getTime());
//    	}else if(year == 1918){
//        	jCal.set(year,0,0);
//        	jCal.add(Calendar.DAY_OF_YEAR, 256);
//        	return df.format(jCal.getTime());
//    	}else{
//    		return null;
//    	}
//    }
    
	static String dayOfProgrammer(int year) throws Exception{ 
        if (year == 1918){
            return "26.09.1918";
        } else if ( ((year <= 1917) && (year%4 == 0)) || ( (year > 1918) && (year%400 == 0) || ((year%4 == 0) && (year%100 != 0)) )){
            return "12.09."+year;
        } else{
            return "13.09."+year;
        }
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(dayOfProgrammer(1700));
		System.out.println(dayOfProgrammer(1741));
		System.out.println(dayOfProgrammer(1800));
		System.out.println(dayOfProgrammer(1900));
		System.out.println(dayOfProgrammer(1917));
		System.out.println(dayOfProgrammer(1984));
	}
}
