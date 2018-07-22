package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

public class ReportService {
	
	public List<Record> listThisMonthRecords(){
		RecordDAO dao = new RecordDAO();
		List<Record> thisMonthRawData = dao.listThisMonth();
		List<Record> result = new ArrayList<Record>();
		
		Date monthBegin = DateUtil.monthBegin();
		int totalDay = DateUtil.thisMonthTotalDay();
		
		Calendar c = Calendar.getInstance();
		for(int i=0;i<totalDay;i++) {
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			int daySpend = getDaySpend(c.getTime(), thisMonthRawData);
			Record record = new Record();
			record.setSpend(daySpend);
			result.add(record);
		}
		return result;
	}
	
	public int getDaySpend(Date d, List<Record> monthRawData) {
		int daySpend = 0;
		for(Record r: monthRawData)
			if(r.getDate().equals(d))
				daySpend += r.getSpend();
		return daySpend;
	}
}
