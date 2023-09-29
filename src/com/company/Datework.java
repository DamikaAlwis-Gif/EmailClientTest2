package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datework {
    // get a calander obj from a birth day
    public static Calendar parseDate(String bithDay) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = formatter.parse(bithDay);
        cal.setTime(date);
        return cal;
    }
    // get today
    public static Calendar getToday() {
        Calendar cal = Calendar.getInstance();
        return cal;
    }
    // get the day from a calender obj
    public static String getDayStr(Calendar cal1 ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String str = dateFormat.format(cal1.getTime());
        return str;
    }
}