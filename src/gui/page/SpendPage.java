package gui.page;

public class SpendPage {
	//本月消费
	public String monthSpend;
	//本日消费
	public String todaySpend;
	//日均消费
	public String avgSpendPerDay;
	//本月剩余
	public String monthLeft;
	//日均可用
	public String dayAvailable;
	//距离月末
	public String monthLeftDay;
	//使用比例
	public int usagePercentage;
	//是否超支
	public boolean isOverSpend = false;
	
	public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthLeft, 
			int dayAvailable, int monthLeftDay, int usagePercentage) {
		this.monthSpend = "￥" + monthSpend;
		this.todaySpend = "￥" + todaySpend;
		this.avgSpendPerDay = "￥" + avgSpendPerDay;
		if(monthLeft<0)
			this.isOverSpend = true;
		
		if(isOverSpend) {
			this.monthLeft = "超支￥" + (0 - monthLeft);
			this.dayAvailable = "￥0";
		}
		else {
			this.monthLeft = "￥" + monthLeft;
			this.dayAvailable = "￥" + dayAvailable;
		}
		this.monthLeftDay = monthLeftDay + "天";
		this.usagePercentage = usagePercentage;
	}
}
