package gui.listener;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JOptionPane;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

import java.awt.event.ActionEvent;

public class BackupListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		BackupPanel p = BackupPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		if(mysqlPath.length() == 0) {
			JOptionPane.showMessageDialog(p, "����ǰ��������mysql·��");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
		}
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("hutubill.sql"));
		fc.setFileFilter(new FileFilter() {
			public String getDescription() {
				return ".sql";
			}
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		int returnVal = fc.showSaveDialog(p);
		File file = fc.getSelectedFile();
		System.out.println(file);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//���������ļ���û����.sql��β���Զ�����.sql
			System.out.println(file);
			if(!file.getName().toLowerCase().endsWith(".sql"))
				file = new File(file.getParentFile(), file.getName()+".sq;");
			System.out.println(file);
			
			try {
				MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(p, "���ݳɹ��������ļ�λ�ڣ�\r\n" + file.getAbsolutePath());
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(p, "���ݴ��󣬴���\r\n"+e1.getMessage());
			}
		}
	}
}
