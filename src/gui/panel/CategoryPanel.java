package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.CategoryDAO;
import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import util.ColorUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel{
	static {
		GUIUtil.useLNF();
	}
	
	public static CategoryPanel instance = new CategoryPanel();
	
	public CategoryTableModel ctm = new CategoryTableModel();
	public JTable t = new JTable(ctm);
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	//public JButton bDetail = new JButton("详情");
	
	//没有太理解为什么用public而不用private
	//单例模式
	public CategoryPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
		
		JScrollPane sp = new JScrollPane(t);
		JPanel pButton = new JPanel();
		
		pButton.add(bAdd);
		pButton.add(bEdit);
		pButton.add(bDelete);
		//pButton.add(bDetail);
		
		this.setLayout(new BorderLayout());
		this.add(sp, BorderLayout.CENTER);
		this.add(pButton, BorderLayout.SOUTH);
		
		addListener();
		updateData();
	}
	
	public Category getSelectedCategory() {
		int index = t.getSelectedRow();
		return ctm.cs.get(index);
	}
	
	public void updateData() {
		ctm.cs = new CategoryDAO().list();
		t.updateUI();
		//保持默认选中第一行
		t.getSelectionModel().setSelectionInterval(0, 0);
		
		//判断有无数据，没数据时edit和delete按钮不可用
		//标准答案用的是if(ctm.cs.size() == 0)
		if(ctm.cs.isEmpty()) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
			//bDetail.setEnabled(false);
		}
		else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
			//bDetail.setEnabled(true);
		}
	}
	
	public void addListener() {
		CategoryListener listener = new CategoryListener();
		bAdd.addActionListener(listener);
		bDelete.addActionListener(listener);
		bEdit.addActionListener(listener);
		//bDetail.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(CategoryPanel.instance);
	}
}
