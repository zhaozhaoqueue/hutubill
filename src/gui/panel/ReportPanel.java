package gui.panel;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{
	static {
		GUIUtil.useLNF();
	}
	public static ReportPanel instance = new ReportPanel();
	
	public JLabel l = new JLabel();
	
	public ReportPanel() {
		this.setLayout(new BorderLayout());
		Image img = ChartUtil.getImage(400, 400);
		Icon i = new ImageIcon(img);
		l.setIcon(i);
		this.add(l);
		
		addListener();
	}
	
	public void addListener() {
		
	}
	
	public void updateData() {
		List<Record> rs = new ReportService().listThisMonthRecords();
		Image img = ChartUtil.getImage(rs, 400, 300);
		Icon i = new ImageIcon(img);
		l.setIcon(i);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(ReportPanel.instance);
	}
}
