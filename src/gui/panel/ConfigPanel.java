package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel{
	static {
		GUIUtil.useLNF();
	}
	
	public static ConfigPanel instance = new ConfigPanel();
	
	public JLabel lBudget = new JLabel("����Ԥ�㣨����");
	public JTextField tfBudget = new JTextField("0");
	public JLabel lMysql = new JLabel("Mysql��װĿ¼");
	public JTextField tfMysqlPath = new JTextField();
	public JButton bSubmit = new JButton("����");
	
	public ConfigPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
		GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		
		int gap = 40;
		pInput.setLayout(new GridLayout(4,1, gap, gap));
		pInput.add(lBudget);
		pInput.add(tfBudget);
		pInput.add(lMysql);
		pInput.add(tfMysqlPath);
		
		pSubmit.add(bSubmit);
		
		this.setLayout(new BorderLayout());
		this.add(pInput, BorderLayout.NORTH);
		this.add(pSubmit, BorderLayout.CENTER);
		
		addListener();
		//bSubmit.addActionListener(new ConfigListener());
		
		//updateData();
	}
	
	public void addListener() {
		ConfigListener l = new ConfigListener();
		bSubmit.addActionListener(l);
	}
	
	public void updateData() {
		ConfigService cs = new ConfigService();
		String budget = cs.get(ConfigService.budget);
		String mysqlPath = cs.get(ConfigService.mysqlPath);
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		tfBudget.grabFocus();
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(ConfigPanel.instance);
	}
}
