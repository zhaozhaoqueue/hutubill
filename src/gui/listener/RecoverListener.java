package gui.listener;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import util.MysqlUtil;

import java.awt.event.ActionEvent;

public class RecoverListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		RecoverPanel p = RecoverPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		if(mysqlPath.length() == 0) {
			JOptionPane.showMessageDialog(p, "ª÷∏¥«∞«Îœ»≈‰÷√mysql¬∑æ∂");
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
		
		int returnVal = fc.showOpenDialog(p);
		File file = fc.getSelectedFile();
		System.out.println(file);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
				JOptionPane.showMessageDialog(p, "ª÷∏¥≥…π¶");
			}
			catch(Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(p, "ª÷∏¥ ß∞‹\r\n,¥ÌŒÛ£∫\r\n"+e1.getMessage());
			}
		}
	}
}
