package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class SpendPanel extends WorkingPanel{
	static {
		GUIUtil.useLNF();
	}
	public static SpendPanel instance = new SpendPanel();
	
	//标签
	public JLabel lMonthSpend = new JLabel("本月消费");
	public JLabel lTodaySpend = new JLabel("本日消费");
	public JLabel lAvgSpendPerDay = new JLabel("日均消费");
	public JLabel lMonthLeft = new JLabel("本月剩余");
	public JLabel lDayAvgAvailable = new JLabel("日均可用");
	public JLabel lMonthLeftDay = new JLabel("距离月末");
	
	public JLabel vMonthSpend = new JLabel("￥2300");
	public JLabel vTodaySpend = new JLabel("￥25");
	public JLabel vAvgSpendPerDay = new JLabel("￥120");
	public JLabel vMonthLeft = new JLabel("￥2084");
	public JLabel vDayAvgAvailable = new JLabel("￥389");
	public JLabel vMonthLeftDay = new JLabel("15天");
	
	CircleProgressBar bar;
	
	public SpendPanel() {
		this.setLayout(new BorderLayout());
		bar = new CircleProgressBar();
		bar.setBackgroundColor(ColorUtil.blueColor);
		
		//设置颜色
		GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
				lMonthLeftDay, vAvgSpendPerDay, vMonthLeft, vDayAvgAvailable, vMonthLeftDay);
		GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
		
		//设置字体
		vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
		
		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);
	}
	
	private JPanel south() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 4));
		p.add(lAvgSpendPerDay);
		p.add(lMonthLeft);
		p.add(lDayAvgAvailable);
		p.add(lMonthLeftDay);
		
		p.add(vAvgSpendPerDay);
		p.add(vMonthLeft);
		p.add(vDayAvgAvailable);
		p.add(vMonthLeftDay);
		
		return p;
	}
	
	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(), BorderLayout.CENTER);
		return p;
	}
	
	private Component center2() {
		return bar;
	}
	
	private JPanel west() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(lMonthSpend);
		p.add(vMonthSpend);
		p.add(lTodaySpend);
		p.add(vTodaySpend);
		return p;
	}
	
	public void addListener() {}
	
	public void updateData() {
		SpendPage spend = new SpendService().getSpendPage();
		vMonthSpend.setText(spend.monthSpend);
		vTodaySpend.setText(spend.todaySpend);
		vDayAvgAvailable.setText(spend.dayAvailable);
		vMonthLeft.setText(spend.monthLeft);
		vAvgSpendPerDay.setText(spend.avgSpendPerDay);
		vMonthLeftDay.setText(spend.monthLeftDay);
		
		bar.setProgress(spend.usagePercentage);
		bar.setBackgroundColor(ColorUtil.blueColor);
		bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
		
		if(spend.isOverSpend) {
			GUIUtil.setColor(ColorUtil.warningColor, vMonthSpend, vTodaySpend, vMonthLeft);
		}
		else {
			GUIUtil.setColor(ColorUtil.grayColor, vMonthSpend, vTodaySpend, vMonthLeft);
		}
		addListener();
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(SpendPanel.instance);
	}
}
