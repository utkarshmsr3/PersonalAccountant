package com.myproject.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateUtils {
	public static java.util.Date acceptDate() {
		java.util.Date dt = null;
		Scanner in = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String str = in.nextLine();
		try {
			dt = df.parse(str);
		} catch (ParseException e) {
			System.out.println("invalid format");
		}
		in.close();
		return dt;
	}

	public static java.util.Date convertDate(String date) {
		java.util.Date dt = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = df.parse(date);
		} catch (ParseException e) {
			System.out.println("invalid format");
		}
		return dt;
	}
	
	public static java.util.Date convertSqlToUtil(java.sql.Date date){
		return new java.util.Date(date.getTime());
	}

	public static java.sql.Date convertUtilToSql(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
}
