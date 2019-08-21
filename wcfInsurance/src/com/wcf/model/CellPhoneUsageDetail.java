package com.wcf.model;

import java.time.LocalDate;

public class CellPhoneUsageDetail {
	long employeeId;
	String employeeName;
	String Model;
	LocalDate purchaseDate;
	double minutesUsage;
	double dataUsage;
	LocalDate usageDate;
	String monthYear;
	private double totalMinutes;
	private double totalData;
	
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public double getMinutesUsage() {
		return minutesUsage;
	}
	public void setMinutesUsage(double minutesUsage) {
		this.minutesUsage = minutesUsage;
	}
	public double getDataUsage() {
		return dataUsage;
	}
	public void setDataUsage(double dataUsage) {
		this.dataUsage = dataUsage;
	}
	public LocalDate getUsageDate() {
		return usageDate;
	}
	public void setUsageDate(LocalDate usageDate) {
		this.usageDate = usageDate;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
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
	
}
