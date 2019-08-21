package com.wcf.model;

import java.time.LocalDate;

public class CellPhoneUsageByMonth {
	private long employeeId;
	private LocalDate date;
	private double totalMinutes;
	private double totalData;
	private String monthYear;
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotalMinutes() {
		return totalMinutes;
	}
	public void setTotalMinutes(double totalMinutes) {
		this.totalMinutes = totalMinutes;
	}
	public double getTotalData() {
		return totalData;
	}
	public void setTotalData(double totalData) {
		this.totalData = totalData;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	
}
