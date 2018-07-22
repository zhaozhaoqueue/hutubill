package gui.model;

import java.sql.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.RecordDAO;
import entity.Record;

public class DetailTableModel implements TableModel{
	public String[] columnNames = {"花费 ", "分类名称", "评论", "日期"};
	public List<Record> rs;
	public Record r;
	
	//有参构造
	public DetailTableModel(int cid) {
		rs = new RecordDAO().list(cid);
		if(!rs.isEmpty()) {
			this.r = rs.get(0);
		}
	}
	//无参构造
	public DetailTableModel() {
		rs = new RecordDAO().list();
		if(!rs.isEmpty()) {
			this.r = rs.get(0);
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==0)
			return Integer.class;
		else if(columnIndex==1)
			return String.class;
		else if(columnIndex==2)
			return String.class;
		else if(columnIndex==3)
			return Date.class;
		else
			return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Record h = rs.get(rowIndex);
		if(columnIndex==0)
			return h.getSpend();
		if(columnIndex==1)
			return h.getCategory();
		if(columnIndex==2)
			return h.getComment();
		if(columnIndex==3)
			return h.getDate();
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
}
