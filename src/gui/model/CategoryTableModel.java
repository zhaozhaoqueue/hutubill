package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import entity.Category;
import service.CategoryService;

public class CategoryTableModel implements TableModel{
	String[] columnNames = {"分类名称", "消费次数"};
	//List<String> cs = new ArrayList<String>();
	public List<Category> cs;
	
	public CategoryTableModel() {
		//cs.add("餐饮");
		//cs.add("交通");
		//cs.add("住宿");
		//cs.add("话费");
		CategoryService c = new CategoryService();
		cs = c.list();
	}
	
	public int getRowCount() {
		return cs.size();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}
	
	public Class<?> getColumnClass(int columnIndex){
		if(columnIndex == 0)
			return String.class;
		else if(columnIndex == 1)
			return Integer.class;
		else
			return null;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Category h = cs.get(rowIndex);
		if(columnIndex==0)
			return h.name;
		if(columnIndex==1)
			return h.recordNumber;
		return null;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//未完待写
	}
	
	public void addTableModelListener(TableModelListener l) {
		//未完待写
	}
	
	public void removeTableModelListener(TableModelListener l) {
		//未完待写
	}
}
