package gui.listener;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;
import gui.panel.DetailPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import service.RecordService;

import java.awt.event.ActionEvent;

public class DetailListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		DetailPanel p = DetailPanel.instance;
		JButton b = (JButton) e.getSource();
		if(b == p.bSearch) {
			Category category = p.getSelectedCategory();
			p.cid = category.getId();
		}
		
		if(b == p.bAdd) {
			MainPanel.instance.workingPanel.show(RecordPanel.instance);
			return;
		}
		
		if(b == p.bEdit) {
			Record r = p.getSelectedRecord();
			String money = JOptionPane.showInputDialog("修改金额", r.getSpend());
			if(money == null)
				return;
			if(money.length() == 0) {
				JOptionPane.showMessageDialog(p,"金额不能为空");
				return;
			}
			try {
				int spend = Integer.parseInt(money);
				r.setSpend(spend);
				new RecordService().update(r);
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(p, "金额必须为整数");
			}
		}
		
		if(b == p.bDelete) {
			Record r = p.getSelectedRecord();
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
				return;
			new RecordService().delete(r);
		}
		
		p.updateData();
	}
}
