package com.wcf;

import java.util.Map;

import com.wcf.common.CellPhoneUsagePrototype;
import com.wcf.factory.PrototypeFactory;
import com.wcf.helper.CellPhoneUsageType;
import com.wcf.service.CellPhoneUsageService;

public class Main {

	public static void main(String[] arg) {
		
		CellPhoneUsageService cellPhoneUsageService = null;
		
		try {
        	//Retrieve copy of cellPhoneUsageService from the factory.
			cellPhoneUsageService = (CellPhoneUsageService)PrototypeFactory.getInstance(CellPhoneUsageType.ISTATICSERVICE);
        }
        catch(CloneNotSupportedException e) {
        	 e.printStackTrace();
        }
		
		if(cellPhoneUsageService != null) {
			//Retrieve data from the static csv file.
			Map<String, Object> cellPhoneUsageMap = cellPhoneUsageService.getCellPhoneUsagesByMonth();
			
			//write processed record to file.
			cellPhoneUsageService.writeToFile(cellPhoneUsageMap);	
		}
		else {
			System.out.println("Can not instantiate cellPhoneUsageService.");
		}
	}
}
