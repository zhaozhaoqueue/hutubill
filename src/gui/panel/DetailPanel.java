package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.listener.DetailListener;
import gui.model.CategoryComboBoxModel;
import gui.model.DetailTableModel;
import util.ColorUtil;
import util.GUIUtil;

public class DetailPanel extends WorkingPanel{
	static {
		GUIUtil.useLNF();
	}
	//单例
	public static DetailPanel instance = new DetailPanel();
	
	public JButton bSearch = new JButton("查询");
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	public Integer cid ;
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
	
	public DetailTableModel dtm = new DetailTableModel();
	public JTable t = new JTable(dtm);
	
	public DetailPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete, cbCategory, bSearch);
		JPanel pSearch = new JPanel();
		JScrollPane sp = new JScrollPane(t);
		JPanel pButton = new JPanel();
		
		pSearch.add(cbCategory);
		pSearch.add(bSearch);
		
		pButton.add(bAdd);
		pButton.add(bEdit);
		pButton.add(bDelete);
		
		this.setLayout(new BorderLayout());
		this.add(pSearch, BorderLayout.NORTH);
		this.add(sp, BorderLayout.CENTER);
		this.add(pButton, BorderLayout.SOUTH);
		
		addListener();
		updateData();
	}
	
	public void addListener() {
		DetailListener listener = new DetailListener();
		bSearch.addActionListener(listener);
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		
	}
	public void updateData() {
		if(cid!=null)
			dtm.rs = new RecordDAO().list(cid.intValue());
		else
			dtm.rs = new RecordDAO().list();
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0, 0);
		if(dtm.rs.isEmpty()) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		}
		else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}
	}
	
	public Category getSelectedCategory() {
		return (Category) cbCategory.getSelectedItem();
	}
	
	public Record getSelectedRecord() {
		int index = t.getSelectedRow();
		return dtm.rs.get(index);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(DetailPanel.instance);
	}
}
