package com.wcf.service;

import java.util.Map;

public interface CellPhoneUsageService extends CellPhoneUsageStrategy{
	public Map<String, Object> getCellPhoneUsagesByMonth();
}
