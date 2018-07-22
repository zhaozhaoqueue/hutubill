package gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import entity.Category;
import service.CategoryService;

public class CategoryTableModel implements TableModel{
	String[] columnNames = {"��������", "���Ѵ���"};
	//List<String> cs = new ArrayList<String>();
	public List<Category> cs;
	
	public CategoryTableModel() {
		//cs.add("����");
		//cs.add("��ͨ");
		//cs.add("ס��");
		//cs.add("����");
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
		//δ���д
	}
	
	public void addTableModelListener(TableModelListener l) {
		//δ���д
	}
	
	public void removeTableModelListener(TableModelListener l) {
		//δ���д
	}
}
