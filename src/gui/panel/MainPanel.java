package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

public class MainPanel extends JPanel{
	static {
		GUIUtil.useLNF();
	}
	//����ģʽʵ����MainPanel
	public static MainPanel instance = new MainPanel();
	
	//ToolBar
	public JToolBar tb = new JToolBar();
	//��ť
	public JButton bSpend= new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
	public JButton bDetail = new JButton();
	
	public CenterPanel workingPanel;
	
	private MainPanel(){
		//���ð���ͼ�ꡢ�ı����ı���ʾ
		GUIUtil.setImageIcon(bSpend, "home.png", "����һ��");
		GUIUtil.setImageIcon(bRecord, "record.png", "��һ��");
		GUIUtil.setImageIcon(bCategory, "category2.png", "���ѷ���");
		GUIUtil.setImageIcon(bReport, "report.png", "�����ѱ���");
		GUIUtil.setImageIcon(bConfig, "config.png", "����");
		GUIUtil.setImageIcon(bBackup, "backup.png", "����");
		GUIUtil.setImageIcon(bRecover, "restore.png", "�ָ�");
		GUIUtil.setImageIcon(bDetail, "category1.png", "���Ѽ�¼");
		
		tb.add(bSpend);
		tb.add(bRecord);
		tb.add(bCategory);
		tb.add(bDetail);
		tb.add(bReport);
		tb.add(bConfig);
		tb.add(bBackup);
		tb.add(bRecover);
		tb.setFloatable(false);
		
		workingPanel = new CenterPanel(0.8);
		
		setLayout(new BorderLayout());
		add(tb, BorderLayout.NORTH);
		add(workingPanel, BorderLayout.CENTER);
		
		addListener();
	}
	
	//ʵ����һ��ToolBarListener��Ϊÿ����ť����һ����������
	private void addListener() {
		ToolBarListener listener = new ToolBarListener();
		
		bDetail.addActionListener(listener);
		bSpend.addActionListener(listener);
		bReport.addActionListener(listener);
		bRecover.addActionListener(listener);
		bBackup.addActionListener(listener);
		bConfig.addActionListener(listener);
		bCategory.addActionListener(listener);
		bRecord.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(MainPanel.instance, 1);
	}
}