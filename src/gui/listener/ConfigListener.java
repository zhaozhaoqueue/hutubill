package gui.listener;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener{
	/*
	 * 注：这个listener只需要和一个按钮相连。
	 * 这是我自己写的方法。 应该是先考虑输入错误的情况。
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		if(GUIUtil.checkNumber(p.tfBudget, "预算"))
			if(isSqlPath(p.tfMysqlPath.getText())) {
				ConfigService cs = new ConfigService();
				cs.update(ConfigService.budget, p.tfBudget.getText());
				cs.update(ConfigService.mysqlPath, p.tfMysqlPath.getText());
				JOptionPane.showMessageDialog(p, "修改成功");
			}
		}
	
	private boolean isSqlPath(String sqlPath) {
		File folder = new File(sqlPath + "/bin");
		File[] files = folder.listFiles();
		for(File f: files) 
			if(f.getName() == "mysql.exe")
				return true;
		return false;
	}
	*/
	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		if(!GUIUtil.checkNumber(p.tfBudget, "本月预算"))
			return;
		String sqlPath = p.tfMysqlPath.getText();
		if(sqlPath.length() != 0) {
			File commandFile = new File(sqlPath, "bin/mysql.exe");
			if(!commandFile.exists()) {
				JOptionPane.showMessageDialog(p, "Mysql路径不正确");
				p.tfMysqlPath.grabFocus();
				return;
			}
		}
		
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath, sqlPath);
		
		JOptionPane.showMessageDialog(p, "修改成功");
	}
}
