package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//һ��ĺ�����
	static long millisecondsOfOneDay = 1000*60*60*24;
	
	//��java.util.Dateת��Ϊjava.sql.Date
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	/*
	 * ��ȡ���죬��ʱ���֡���ͺ��붼����Ϊ0.��Ϊͨ�����ڿؼ���õ����ھ�û��ʱ������롣
	 * @return
	 */
	public static Date today() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND,  0);
		return c.getTime();
	}
	
	/*��ȡ�³�
	 * @return
	 */
	public static Date monthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 0);
		
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND,  0);
		return c.getTime();
	}
	
	/*
	 * ��ȡ��ĩ
	 * @return
	 */
	public static Date monthEnd() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND,  0);
		
		c.set(Calendar.DATE,  1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}
	
	/*��ȡ�����ж�����
	 * @return
	 */
	public static int thisMonthTotalDay() {
		/*
		 * ���ӷ���
		long lastdayMilliSeconds = monthEnd().getTime();
		long firstdayMilliSeconds = monthBegin().getTime();
		return (int)((lastdayMilliSeconds - firstdayMilliSeconds)/millisecondsOfOneDay) ;
		*/
		/*
		 *�򵥷�����ֱ����Calendar����ľ�̬���� 
		 */
		Calendar c = Calendar.getInstance();
		int maximum = c.getMaximum(Calendar.DATE);
		return maximum;
	}
	
	/*��ȡ����ʣ�������
	 * @return
	 */
	public static int thisMonthLeftDay() {
		Date today = today();
		Date end = monthEnd();
		return (int)((end.getTime() - today.getTime())/millisecondsOfOneDay);
	}
	
	/*��ȡ�����ѹ�������
	 *@return 
	 */
	public static int thisMonthPassDay() {
		Date today = today();
		Date begin = monthBegin();
		return (int)((today.getTime() - begin.getTime())/millisecondsOfOneDay);
	}
	public static void main(String[] args) {
		System.out.println(thisMonthPassDay());
	}
}
