/*
 *@(#)EmployeeNumber.java 2017.02.03
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.amazon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author kim.minjoo
 */
public class EmployeeNumber {
	private SimpleDateFormat employeeDateFormat = new SimpleDateFormat("hh:mm:ss");

	private Date convertStringToDate (String strDate) throws ParseException {
		return employeeDateFormat.parse(strDate);
	}

	public int getEmployeeNumber(String logFilePath, String Time) {
		try {
			Date standardTime = convertStringToDate(Time);

			int employeeNumber = 0;
			BufferedReader in = new BufferedReader(new FileReader(logFilePath));
			String line;

			while ((line = in.readLine()) != null) {
				String[] employeeStrTime = line.split(" ");
				Date inTime = convertStringToDate(employeeStrTime[0]);
				Date outTime = convertStringToDate(employeeStrTime[1]);

				if (inTime.compareTo(standardTime) != outTime.compareTo(standardTime)) {
					employeeNumber++;
				}
			}
			in.close();

			return employeeNumber;
		} catch (ParseException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
		}

		return 0;
	}
}