package com.wcf.factory;

import java.util.HashMap;
import java.util.Map;

import com.wcf.common.CellPhoneUsagePrototype;
import com.wcf.helper.CellPhoneUsageType;
import com.wcf.service.CellPhoneUsageServiceImpl;

public class PrototypeFactory {

	private static Map<CellPhoneUsageType, CellPhoneUsagePrototype> prototypes = new HashMap<CellPhoneUsageType, CellPhoneUsagePrototype>();
	
	static {
		prototypes.put(CellPhoneUsageType.ISTATICSERVICE, new CellPhoneUsageServiceImpl());
	}
	
	public static CellPhoneUsagePrototype getInstance(final CellPhoneUsageType type) throws CloneNotSupportedException{
		
		return ((CellPhoneUsagePrototype) prototypes.get(type)).clone();
	}
}
