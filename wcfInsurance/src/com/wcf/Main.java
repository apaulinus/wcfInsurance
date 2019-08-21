package com.wcf;

import java.util.Map;

import com.wcf.common.CellPhoneUsagePrototype;
import com.wcf.factory.PrototypeFactory;
import com.wcf.helper.CellPhoneUsageType;
import com.wcf.service.CellPhoneUsageService;

public class Main {

	public static void main(String[] arg) {
		
		CellPhoneUsagePrototype cellPhoneUsageService = null;
		
		try {
        	//Retrieve copy of cellPhoneUsageService from the factory.
			cellPhoneUsageService = PrototypeFactory.getInstance(CellPhoneUsageType.ISTATICSERVICE);
        }
        catch(CloneNotSupportedException e) {
        	 e.printStackTrace();
        }
		
		//Retrieve data from the static csv file.
		Map<String, Object> cellPhoneUsageMap = ((CellPhoneUsageService)cellPhoneUsageService).getCellPhoneUsagesByMonth();
		
		//write processed record to file.
		((CellPhoneUsageService)cellPhoneUsageService).writeToFile(cellPhoneUsageMap);		
	}
}
