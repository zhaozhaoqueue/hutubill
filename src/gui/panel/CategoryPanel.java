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
	public JButton bAdd = new JButton("����");
	public JButton bEdit = new JButton("�༭");
	public JButton bDelete = new JButton("ɾ��");
	//public JButton bDetail = new JButton("����");
	
	//û��̫���Ϊʲô��public������private
	//����ģʽ
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
		//����Ĭ��ѡ�е�һ��
		t.getSelectionModel().setSelectionInterval(0, 0);
		
		//�ж��������ݣ�û����ʱedit��delete��ť������
		//��׼���õ���if(ctm.cs.size() == 0)
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
