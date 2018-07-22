package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

public class RecordListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		RecordPanel p = RecordPanel.instance;
		if(p.cbModel.cs.size() == 0) {
			JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先增加消费分类");
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
			return;
		}
		if(!GUIUtil.checkZero(p.tfSpend, "金额"))
			return;
		int spend = Integer.parseInt(p.tfSpend.getText());
		String comment = p.tfComment.getText();
		Date date = p.datepick.getDate();
		//Category c = (Category)(p.cbCategory.getSelectedItem());
		//RecordPanel中有一个getSelectedCategory的方法，专门为了用在这
		Category c = p.getSelectedCategory();
		new RecordService().add(spend, c, comment, date);
		
		JOptionPane.showMessageDialog(p, "添加成功");
		
		MainPanel.instance.workingPanel.show(SpendPanel.instance);
	}
	
}
