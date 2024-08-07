package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random rNum = new Random();
		int randomNum = rNum.nextInt(10000);
		return randomNum;
	}

	public int getRandomNumber(int randomNumber) {
		Random r = new Random();
		int i = r.nextInt(randomNumber);
		return i;
	}

	public String getSystemDateYYYYMMDD() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dt);
		return date;
	}

	public String generateDate() {
		Date da = new Date();
		String x[] = da.toString().split(" ");
		String date = x[2] + "-" + x[1] + "-" + x[5];
		return date;
	}

	public String generateTime() {
		Date dt = new Date();
		String[] x = dt.toString().split(" ");
		String time = x[3];
		return time.replace(":", "-");

	}

	public String getRequredDate(int days) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dt);

		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requredDate = sdf.format(cal.getTime());
		return requredDate;
	}

	public String generateTimeWithAll() {
		Date dt = new Date();
		String time = dt.toString().replaceAll(" ", "_").replace(":", "_");
		return time;
	}

}
