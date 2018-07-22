package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {
	CategoryDAO cDao = new CategoryDAO();
	RecordDAO rDao = new RecordDAO();
	
	public List<Category> list(){
		List<Category> cs = cDao.list();
		for(Category c: cs) {
			List<Record> ls = rDao.list(c.getId());
			c.setRecordNumber(ls.size());
		}
		
		Collections.sort(cs, (c1, c2)->c2.recordNumber - c1.recordNumber);
		
		return cs;
	}
	
	public void add(String name) {
		Category category = new Category();
		category.setName(name);
		cDao.add(category);
	}
	
	public void update(int id, String name) {
		//Category category = cDao.get(id);
		//category.setName(name);
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		cDao.update(category);
	}
	
	public void delete(int id) {
		cDao.delete(id);
	}
}

