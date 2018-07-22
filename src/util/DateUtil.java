package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//一天的毫秒数
	static long millisecondsOfOneDay = 1000*60*60*24;
	
	//将java.util.Date转换为java.sql.Date
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	/*
	 * 获取今天，把时、分、秒和毫秒都设置为0.因为通过日期控件获得的日期就没有时分秒毫秒。
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
	
	/*获取月初
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
	 * 获取月末
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
	
	/*获取本月有多少天
	 * @return
	 */
	public static int thisMonthTotalDay() {
		/*
		 * 复杂方法
		long lastdayMilliSeconds = monthEnd().getTime();
		long firstdayMilliSeconds = monthBegin().getTime();
		return (int)((lastdayMilliSeconds - firstdayMilliSeconds)/millisecondsOfOneDay) ;
		*/
		/*
		 *简单方法，直接用Calendar里面的静态方法 
		 */
		Calendar c = Calendar.getInstance();
		int maximum = c.getMaximum(Calendar.DATE);
		return maximum;
	}
	
	/*获取本月剩余多少天
	 * @return
	 */
	public static int thisMonthLeftDay() {
		Date today = today();
		Date end = monthEnd();
		return (int)((end.getTime() - today.getTime())/millisecondsOfOneDay);
	}
	
	/*获取本月已过多少天
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
