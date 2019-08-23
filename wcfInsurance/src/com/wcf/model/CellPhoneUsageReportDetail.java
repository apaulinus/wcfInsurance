package com.wcf.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CellPhoneUsageReportDetail {
	long employeeId;
	String employeeName;
	String Model;
	LocalDate purchaseDate;
	double minutesUsage;
	double dataUsage;
	LocalDate usageDate;
	String monthYear;
	double totalMinutesUsagePerMonth;
	double totalDataUsagePerMonth;
	List<Double> minutesUsages = new ArrayList<>();
	List<Double> dataUsages = new ArrayList<>();
	
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
	public double getTotalinutesUsagePerMonth() {
		return totalMinutesUsagePerMonth;
	}
	public void setTotalMinutesUsagePerMonth(double totalMinutesUsagePerMonth) {
		this.totalMinutesUsagePerMonth = totalMinutesUsagePerMonth;
	}
	public double getTotalDataUsagePerMonth() {
		return totalDataUsagePerMonth;
	}
	public void setTotalDataUsagePerMonth(double totalDataUsagePerMonth) {
		this.totalDataUsagePerMonth = totalDataUsagePerMonth;
	}
	public List<Double> getMinutesUsages() {
		return minutesUsages;
	}
	public void setMinutesUsages(List<Double> minutesUsages) {
		this.minutesUsages = minutesUsages;
	}
	public List<Double> getDataUsages() {
		return dataUsages;
	}
	public void setDataUsages(List<Double> dataUsages) {
		this.dataUsages = dataUsages;
	}
}
