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
			JOptionPane.showMessageDialog(p, "�������ѷ��࣬�޷���ӣ������������ѷ���");
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
			return;
		}
		if(!GUIUtil.checkZero(p.tfSpend, "���"))
			return;
		int spend = Integer.parseInt(p.tfSpend.getText());
		String comment = p.tfComment.getText();
		Date date = p.datepick.getDate();
		//Category c = (Category)(p.cbCategory.getSelectedItem());
		//RecordPanel����һ��getSelectedCategory�ķ�����ר��Ϊ��������
		Category c = p.getSelectedCategory();
		new RecordService().add(spend, c, comment, date);
		
		JOptionPane.showMessageDialog(p, "��ӳɹ�");
		
		MainPanel.instance.workingPanel.show(SpendPanel.instance);
	}
	
}
