package gui.page;

public class SpendPage {
	//��������
	public String monthSpend;
	//��������
	public String todaySpend;
	//�վ�����
	public String avgSpendPerDay;
	//����ʣ��
	public String monthLeft;
	//�վ�����
	public String dayAvailable;
	//������ĩ
	public String monthLeftDay;
	//ʹ�ñ���
	public int usagePercentage;
	//�Ƿ�֧
	public boolean isOverSpend = false;
	
	public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthLeft, 
			int dayAvailable, int monthLeftDay, int usagePercentage) {
		this.monthSpend = "��" + monthSpend;
		this.todaySpend = "��" + todaySpend;
		this.avgSpendPerDay = "��" + avgSpendPerDay;
		if(monthLeft<0)
			this.isOverSpend = true;
		
		if(isOverSpend) {
			this.monthLeft = "��֧��" + (0 - monthLeft);
			this.dayAvailable = "��0";
		}
		else {
			this.monthLeft = "��" + monthLeft;
			this.dayAvailable = "��" + dayAvailable;
		}
		this.monthLeftDay = monthLeftDay + "��";
		this.usagePercentage = usagePercentage;
	}
}
