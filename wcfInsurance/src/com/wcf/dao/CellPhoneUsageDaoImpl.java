package com.wcf.dao;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.wcf.model.CellPhoneUsageByMonth;
import com.wcf.model.EmployeeCellPhone;

public class CellPhoneUsageDaoImpl implements CellPhoneUsageDao, Cloneable{
	
	@Override
	public CellPhoneUsageDaoImpl clone() throws CloneNotSupportedException {
		return (CellPhoneUsageDaoImpl) super.clone();
	}
	
	/**
	 * 
	 * A method to retrieve list of cell phones from csv file
	 * 
	 * @return employeeCellPhones list of cell phone records retrieved from the csv
	 */
	public List<EmployeeCellPhone> retrieveCellPhone() {
		
		String csvFilename = "/backend/resources/data/CellPhone.csv";
		List<EmployeeCellPhone> employeeCellPhones = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            
            Reader reader = Files.newBufferedReader(Paths.get(csvFilename));

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            
            List<String[]> cellPhones = csvReader.readAll();
            
            for(String[] cellPhone : cellPhones) {
            	EmployeeCellPhone employeeCellPhone = new EmployeeCellPhone();
                if(StringUtils.isNotEmpty(cellPhone[0]) && StringUtils.isNumeric(cellPhone[0])) {
            		employeeCellPhone.setEmployeeId(Long.parseLong(cellPhone[0]));
            	}
            	employeeCellPhone.setEmployeeName(cellPhone[1]);
            	if(StringUtils.isNotEmpty(cellPhone[2])) {
            		String date = cellPhone[2].substring(0,4).concat("/").concat(cellPhone[2].substring(4,6)).concat("/").concat(cellPhone[2].substring(6,8));
            		employeeCellPhone.setPurchaseDate(LocalDate.parse(date, formatter));
            	}
            	employeeCellPhone.setModel(cellPhone[3]);
            	
            	employeeCellPhones.add(employeeCellPhone);
            }
            
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }

		return employeeCellPhones;
	}
	
	/**
	 * 
	 * A method to retrieve list of cell phone usages per month from csv file
	 * 
	 * @return cellPhoneUsageByMonths list of cell phone usages per month retrieved from the csv
	 */
	public List<CellPhoneUsageByMonth> retrieveCellPhoneUsageByMonth() {
		
		String csvFilename = "/backend/resources/data/CellPhoneUsageByMonth.csv";
		List<CellPhoneUsageByMonth> cellPhoneUsageByMonths = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            
            Reader reader = Files.newBufferedReader(Paths.get(csvFilename));

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            
            List<String[]> cellPhoneUsages = csvReader.readAll();
            
            for(String[] cellPhoneUsage : cellPhoneUsages) {
            	CellPhoneUsageByMonth cellPhoneUsageByMonth = new CellPhoneUsageByMonth();
                if(StringUtils.isNotEmpty(cellPhoneUsage[0]) && StringUtils.isNumeric(cellPhoneUsage[0])) {
                	cellPhoneUsageByMonth.setEmployeeId(Long.parseLong(cellPhoneUsage[0]));
            	}
                if(StringUtils.isNotEmpty(cellPhoneUsage[1])) {
                	String[] dates = cellPhoneUsage[1].split("/");
                	if(dates[0].length()<2) {
                		dates[0] = "0".concat(dates[0]);
                	}
                	if(dates[1].length() < 2) {
                		dates[1] = "0".concat(dates[1]);
                	}
                	String date = dates[0].concat("/").concat(dates[1]).concat("/").concat(dates[2]);
            		cellPhoneUsageByMonth.setDate(LocalDate.parse(date, formatter));
            	}
                if(StringUtils.isNotEmpty(cellPhoneUsage[2]) && StringUtils.isNumeric(cellPhoneUsage[2])) {
                	cellPhoneUsageByMonth.setTotalMinutes(Double.parseDouble(cellPhoneUsage[2]));
                }
                if(StringUtils.isNotEmpty(cellPhoneUsage[3]) ) {
                	cellPhoneUsageByMonth.setTotalData(Double.parseDouble(cellPhoneUsage[3]));
                }
                cellPhoneUsageByMonth.setMonthYear(cellPhoneUsageByMonth.getDate().getMonthValue() + "/" + cellPhoneUsageByMonth.getDate().getYear());
                cellPhoneUsageByMonths.add(cellPhoneUsageByMonth);
            }
            
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }

		return cellPhoneUsageByMonths;
	}
}
