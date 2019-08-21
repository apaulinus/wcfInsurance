package com.wcf.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.PrintException;

import com.wcf.dao.CellPhoneUsageDao;
import com.wcf.dao.CellPhoneUsageDaoImpl;
import com.wcf.model.CellPhoneUsageByMonth;
import com.wcf.model.CellPhoneUsageDetail;
import com.wcf.model.CellPhoneUsageReportDetail;
import com.wcf.model.EmployeeCellPhone;
import com.wcf.util.PrintingServiceDemo;

public class CellPhoneUsageServiceImpl implements CellPhoneUsageService {
	
	CellPhoneUsageDao cellPhoneUsageDao = new CellPhoneUsageDaoImpl();
	final DecimalFormat decimalFormat = new DecimalFormat("#.##");
	final String file = "c:/temp/samplefile.txt";
	File theDir = new File("c:/temp/");
	
	@Override
	public CellPhoneUsageServiceImpl clone() throws CloneNotSupportedException {
		//Deep Copy
		CellPhoneUsageServiceImpl css = new CellPhoneUsageServiceImpl();
		return css;
	}
	
	public void writeToFile(Map<String, Object> cellPhoneUsageMap) {

		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		        se.printStackTrace();
		    } 
		}
		try {
			BufferedWriter writer = new BufferedWriter(
	                new FileWriter(file, true));
	                
			@SuppressWarnings("unchecked")
			Map<String, Object> header = (Map<String, Object>)cellPhoneUsageMap.get("header");
			
			@SuppressWarnings("unchecked")
			Set<String> monthYear = (Set<String>) cellPhoneUsageMap.get("monthYear");
			
			@SuppressWarnings("unchecked")
			Map<String, List<CellPhoneUsageDetail>> cellPhoneUsageByModel = (Map<String, List<CellPhoneUsageDetail>>)cellPhoneUsageMap.get("cellPhoneUsageByModel");
			
			writeHeaderToFile(header, writer, decimalFormat);
			writeCellPhoneUsageDetailToFile(cellPhoneUsageByModel, monthYear, writer, decimalFormat);
			
			writer.close();
			PrintingServiceDemo printingServiceDemo = new PrintingServiceDemo();
			printingServiceDemo.print(file);
			
		} catch (PrintException e) {
			e.printStackTrace();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	private static void writeHeaderToFile(Map<String, Object> header, BufferedWriter writer, 
			DecimalFormat decimalFormat) throws IOException{
		writer.write("Report Run Date: "+ LocalDate.now());
		writer.newLine();
		writer.write("NumberOfPhones: "+ 	decimalFormat.format(header.get("numberOfPhones")));
		writer.newLine();
		writer.write("TotalMinutes: "+ 	decimalFormat.format(header.get("totalMinutes")));
		writer.newLine();
		writer.write("TotalData: "+ 		decimalFormat.format(header.get("totalData")));
		writer.newLine();
		writer.write("AverageMinutes: "+ 	decimalFormat.format(header.get("averageMinutes")));
		writer.newLine();
		writer.write("AverageData: "+ 	decimalFormat.format(header.get("averageData")));
		writer.newLine();
		
		writer.newLine();
		writer.write("Cell Phone Usage grouped by model");
		
	}
	
	private static void writeCellPhoneUsageDetailToFile(Map<String, List<CellPhoneUsageDetail>> cellPhoneUsageByModel,
			Set<String> monthYear, BufferedWriter writer, DecimalFormat decimalFormat) throws IOException{
		for(String k : cellPhoneUsageByModel.keySet()) {
			List<CellPhoneUsageReportDetail> cellPhoneUsageReportDetails= new ArrayList<>();
	    	
			writer.newLine();
			writer.write("\n***************************"+k+"*********************************");
			writer.newLine();
			
			writer.write("EmployeeId \t Employee Name \t Model \t \t \t Purchase Date \t MinutesUsage \t\t Data Usage");
			writer.newLine();
			
			writer.write("\t\t\t\t\t\t\t\t\t");
	    	for(String mthYr:monthYear) {
	    		writer.write(mthYr + " " );
	    	}
	    	writer.newLine();
			
		    @SuppressWarnings("unchecked")
			Map<String,List<CellPhoneUsageDetail>> cellPhoneUsageByMonth = (Map<String, List<CellPhoneUsageDetail>>) cellPhoneUsageByModel.get(k);
		    
		    getCellPhoneUsageReportDetail(cellPhoneUsageByMonth, cellPhoneUsageReportDetails);
		    
		    for (CellPhoneUsageReportDetail cellPhoneUsageReportDetail: cellPhoneUsageReportDetails) {
		    	writer.write(cellPhoneUsageReportDetail.getEmployeeId() + "\t \t" +
        			cellPhoneUsageReportDetail.getEmployeeName() + "\t"+
        			cellPhoneUsageReportDetail.getModel() + "\t " +
        			cellPhoneUsageReportDetail.getPurchaseDate() + "\t "
        			);
	        	for(Double minutesUsage : cellPhoneUsageReportDetail.getMinutesUsages()) {
	        		writer.write(decimalFormat.format(minutesUsage) + " ");
	        	}
	        	writer.write("\t");
	        	for(Double dataUsage : cellPhoneUsageReportDetail.getDataUsages()) {
	        		writer.write(decimalFormat.format(dataUsage) + " ");
	        	}
	        	writer.newLine();
	        }
		}
	}
	
	private static void getCellPhoneUsageReportDetail(Map<String,List<CellPhoneUsageDetail>> cellPhoneUsageByMonth,
			List<CellPhoneUsageReportDetail> cellPhoneUsageReportDetails) {

	    for(String k1 : cellPhoneUsageByMonth.keySet()) {
	    	List<CellPhoneUsageDetail> cellPhoneUsageList = cellPhoneUsageByMonth.get(k1);
	    	
	        for (CellPhoneUsageDetail cellPhoneUsage : cellPhoneUsageList){
	        	boolean newRecord = false;
	    		
        		for (CellPhoneUsageReportDetail cellPhoneUsageReportDetail: cellPhoneUsageReportDetails) {
        			if(cellPhoneUsageReportDetail.getEmployeeId() == cellPhoneUsage.getEmployeeId()) {
        				cellPhoneUsageReportDetail.getMinutesUsages().add(cellPhoneUsage.getTotalMinutes());
        				cellPhoneUsageReportDetail.getDataUsages().add(cellPhoneUsage.getTotalData());
        				newRecord = true;
	        			break;
        			}
        		}
        		if(!newRecord) {
        			CellPhoneUsageReportDetail newCellPhoneUsageReportDetail = new CellPhoneUsageReportDetail();
		        	newCellPhoneUsageReportDetail.setEmployeeId(cellPhoneUsage.getEmployeeId());
        			newCellPhoneUsageReportDetail.setEmployeeName(cellPhoneUsage.getEmployeeName());
        			newCellPhoneUsageReportDetail.setModel(cellPhoneUsage.getModel());
        			newCellPhoneUsageReportDetail.setPurchaseDate(cellPhoneUsage.getPurchaseDate());
    				List<Double> minutesUsages = new ArrayList<Double>();
        			minutesUsages.add(cellPhoneUsage.getMinutesUsage());
        			newCellPhoneUsageReportDetail.setMinutesUsages(minutesUsages);
        			List<Double> dataUsages = new ArrayList<Double>();
        			dataUsages.add(cellPhoneUsage.getDataUsage());
        			newCellPhoneUsageReportDetail.setDataUsages(dataUsages);
        			newCellPhoneUsageReportDetail.getMinutesUsages().add(cellPhoneUsage.getTotalMinutes());
        			newCellPhoneUsageReportDetail.getMinutesUsages().add(cellPhoneUsage.getTotalData());
        			cellPhoneUsageReportDetails.add(newCellPhoneUsageReportDetail);
        			newRecord = true;
        		}					
	        }
	    }
	}
	
	public Map<String, Object> getCellPhoneUsagesByMonth(){
		List<EmployeeCellPhone> employeeCellPhones = cellPhoneUsageDao.retrieveCellPhone();
		List<CellPhoneUsageByMonth> cellPhoneUsageByMonths = cellPhoneUsageDao.retrieveCellPhoneUsageByMonth();
		List<CellPhoneUsageDetail> cellPhoneUsageDetails = new ArrayList<>();
		Map<String, Object> header = new HashMap<>();
		Map<String, Object> retval = new HashMap<>();
		Map<String, CellPhoneUsageDetail> CellPhoneUsagesByMonthsMap = new HashMap<>();
		Set<String> monthYear = new HashSet<>();
		
		int numberOfPhones = 0;
		double totalMinutes = 0;
		double totalData = 0;
		double averageMinutes = 0;
		double averageData = 0;
		double totalMinutesPerMonth = 0;
		double totalDataPerMonth = 0;
		
		for(CellPhoneUsageByMonth cellPhoneUsageByMonth : cellPhoneUsageByMonths) {
			for(EmployeeCellPhone employeeCellPhone : employeeCellPhones) {
				if(cellPhoneUsageByMonth.getEmployeeId() == employeeCellPhone.getEmployeeId()) {
					
					if(CellPhoneUsagesByMonthsMap.containsKey(cellPhoneUsageByMonth.getMonthYear())) {
						totalMinutesPerMonth = CellPhoneUsagesByMonthsMap.get(cellPhoneUsageByMonth.getMonthYear()).getTotalMinutes() + cellPhoneUsageByMonth.getTotalMinutes();
						totalDataPerMonth = CellPhoneUsagesByMonthsMap.get(cellPhoneUsageByMonth.getMonthYear()).getTotalData() + cellPhoneUsageByMonth.getTotalData();					
					}
					else {
						totalMinutesPerMonth = cellPhoneUsageByMonth.getTotalMinutes();
						totalDataPerMonth = cellPhoneUsageByMonth.getTotalData();
					}
					
					numberOfPhones = numberOfPhones+1;	
					totalMinutes = totalMinutes + cellPhoneUsageByMonth.getTotalMinutes();
					totalData = totalData + cellPhoneUsageByMonth.getTotalData();
					
					CellPhoneUsageDetail cellPhoneUsageDetail = new CellPhoneUsageDetail();
					cellPhoneUsageDetail.setEmployeeId(employeeCellPhone.getEmployeeId());
					cellPhoneUsageDetail.setEmployeeName(employeeCellPhone.getEmployeeName());
					cellPhoneUsageDetail.setModel(employeeCellPhone.getModel());
					cellPhoneUsageDetail.setPurchaseDate(employeeCellPhone.getPurchaseDate());
					cellPhoneUsageDetail.setMinutesUsage(cellPhoneUsageByMonth.getTotalMinutes());
					cellPhoneUsageDetail.setDataUsage(cellPhoneUsageByMonth.getTotalData());
					cellPhoneUsageDetail.setUsageDate(cellPhoneUsageByMonth.getDate());
					cellPhoneUsageDetail.setMonthYear(cellPhoneUsageByMonth.getMonthYear());
					cellPhoneUsageDetail.setTotalMinutes(totalMinutesPerMonth);
					cellPhoneUsageDetail.setTotalData(totalDataPerMonth);
					
					cellPhoneUsageDetails.add(cellPhoneUsageDetail);
					CellPhoneUsagesByMonthsMap.put(cellPhoneUsageDetail.getMonthYear(), cellPhoneUsageDetail);
				}
			}
		}
		averageMinutes = totalMinutes/numberOfPhones;
		averageData = totalData/numberOfPhones;
		
		header.put("numberOfPhones", numberOfPhones);
		header.put("totalMinutes", totalMinutes);
		header.put("totalData", totalData);
		header.put("averageMinutes", averageMinutes);
		header.put("averageData", averageData);
		monthYear = CellPhoneUsagesByMonthsMap.keySet();
		
		Map<String, Map<String, List<CellPhoneUsageDetail>>> cellPhoneUsageByModel =
				CellPhoneUsagesByMonthsMap.values().stream()
					.collect(Collectors.toList()).stream()
		                .collect(Collectors.groupingBy(cellPhoneUsageDetail -> cellPhoneUsageDetail.getModel(),
		                		Collectors.groupingBy(cellPhoneUsageDetail -> cellPhoneUsageDetail.getMonthYear())));
			
		retval.put("header", header);
		retval.put("cellPhoneUsageByModel", cellPhoneUsageByModel);
		retval.put("monthYear", monthYear);
		return retval;
	}
}
