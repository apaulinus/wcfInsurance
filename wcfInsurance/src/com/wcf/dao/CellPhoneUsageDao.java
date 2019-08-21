package com.wcf.dao;

import java.util.List;

import com.wcf.model.CellPhoneUsageByMonth;
import com.wcf.model.EmployeeCellPhone;

public interface CellPhoneUsageDao {
	public List<EmployeeCellPhone> retrieveCellPhone();
	public List<CellPhoneUsageByMonth> retrieveCellPhoneUsageByMonth();
}