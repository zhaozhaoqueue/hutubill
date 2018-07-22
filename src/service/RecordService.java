package service;

import java.util.Date;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordService {
	RecordDAO dao = new RecordDAO();
	public void add(int spend, Category c, String comment, Date date) {
		Record record = new Record();
		record.setSpend(spend);
		record.setCid(c.getId());
		record.setComment(comment);
		record.setDate(date);
		dao.add(record);
	}
	
	public void update(Record r) {
		dao.update(r);
	}
	
	public void delete(Record r) {
		dao.delete(r.getId());
	}
}
