package com.wcf.service;

import java.util.Map;

public interface CellPhoneUsageService extends CellPhoneUsageStrategy{
	public CellPhoneUsageServiceImpl clone() throws CloneNotSupportedException;
	public Map<String, Object> getCellPhoneUsagesByMonth();
}
