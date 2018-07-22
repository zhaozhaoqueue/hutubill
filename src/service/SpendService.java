package service;

import java.util.List;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

public class SpendService {
	private ConfigService configService = new ConfigService();
	private RecordDAO recordDao = new RecordDAO();
	private int monthSpend = 0;
	private int todaySpend = 0;
	private int avgSpendPerDay = 0; 
	private int monthLeft = 0;
	private int dayAvgAvailable = 0; 
	private int monthLeftDay = 0;
	private int usagePercentage = 0;
	public SpendPage getSpendPage() {
		List<Record> mRecords = recordDao.listThisMonth();
		List<Record> dRecords = recordDao.listToday();
		int budget = configService.getIntBudget();
		
		for(Record r: mRecords) {
			monthSpend += r.getSpend();
		}
		avgSpendPerDay = (int) (monthSpend/DateUtil.thisMonthPassDay());
		
		for(Record r: dRecords)
			todaySpend += r.getSpend();
		
		monthLeft = budget - monthSpend;
		
		monthLeftDay = DateUtil.thisMonthLeftDay();
		dayAvgAvailable = (int)(monthLeft/monthLeftDay);
		
		usagePercentage = (int)(monthSpend*100/budget);
		
		return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, 
				monthLeft, dayAvgAvailable, monthLeftDay, usagePercentage);
	}
}
