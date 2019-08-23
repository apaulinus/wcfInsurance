package com.wcf.service;

import java.util.Map;

import com.wcf.common.CellPhoneUsagePrototype;

public interface CellPhoneUsageStrategy extends CellPhoneUsagePrototype {

	public void writeToFile(Map<String, Object> cellPhoneUsageMap);
}
