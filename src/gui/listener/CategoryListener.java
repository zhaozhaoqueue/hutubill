package gui.listener;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.panel.CategoryPanel;
import gui.panel.DetailPanel;
import gui.panel.MainPanel;
import service.CategoryService;

import java.awt.event.ActionEvent;

public class CategoryListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		CategoryPanel p = CategoryPanel.instance;
		JButton b = (JButton) e.getSource();
		if(b == p.bAdd) {
			String name = JOptionPane.showInputDialog(null);
			if(name == null)
				return;
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(p,"�������Ʋ���Ϊ��");
				return;
			}
			new CategoryService().add(name);
		}
		
		if(b == p.bEdit) {
			Category c = p.getSelectedCategory();
			String name = JOptionPane.showInputDialog("�޸ķ�������", c.name);
			if(name == null)
				return;
			if(name.length() == 0) {
				JOptionPane.showMessageDialog(p,"�������Ʋ���Ϊ��");
				return;
			}
			
			new CategoryService().update(c.id, name);
		}
		
		if(b == p.bDelete) {
			Category c = p.getSelectedCategory();
			/*
			List<Record> rs = new RecordDAO().list(c.id);
			if(rs == null) {
				new CategoryService().delete(c.id);
			}
			else {
				JOptionPane.showMessageDialog(p, "�������������Ѽ�¼���ڣ�����ɾ��");
				return;
			}
			*/
			if(c.recordNumber != 0) {
				JOptionPane.showMessageDialog(p,"�������������Ѽ�¼���ڣ�����ɾ��");
				return;
			}
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "ȷ��Ҫɾ����"))
				return;
			
			new CategoryService().delete(c.id);
		}
		/*
		if(b == p.bDetail) {
			Category c = p.getSelectedCategory();
			DetailPanel dp = DetailPanel.instance;
			dp.cid = c.getId();
			MainPanel.instance.workingPanel.show(dp);
		}
		*/
		p.updateData();
	}
}
