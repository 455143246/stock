package util;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import cstt.Constant;


/**
 * 日期工具
 * @author wengxf
 *
 */
public class DateUtil {
	/**校验当前日期是否是节假日,双休
	 * @return
	 */
	public static boolean validHoliday(Date date){
		String retCode=DateUtil.getHoliday(date);
		if(Constant.IS_WORKDAY.equals(retCode)){
			return false;
		}
		return true;
	}
	/**访问网址,0是工作日,1是节假日,2是休息日
	 * @return
	 */
	public static String getHoliday(Date date){
		String httpUrl=Constant.HOLIDAY_VALID_URL;
		String currentDate=DateUtil.date2String(date,"yyyyMMdd" );
		Map map=new HashMap();
		map.put("d",currentDate );
		String url = HttpUtil.addParam(httpUrl, map);
		InputStream is = HttpUtil.sendHttpGet(url);
		return InputStreamUtil.jsonInputStreamToMap(is).get(currentDate).toString();
	}
	/**
	 * 得到当前日期
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * 转换字符串为日期
	 * @param dateString
	 * @param pattern		如"yyyy-MM-dd"
	 * @return
	 */
	public static Date string2Date(String dateString, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date dt = null;
		try {
			dt = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	/**
	 * 转换日期为字符串
	 * @param dt
	 * @param pattern	如"yyyy-MM-dd"
	 * @return
	 */
	public static String date2String(Date dt, String pattern) {
		return new SimpleDateFormat(pattern).format(dt);
	}
	
	/**
	 * 转换日期为年月日时分秒字符串
	 * @param dt
	 * @return
	 */
	public static String date2TimeString(Date dt) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt);
	}
	
	/**
	 * 转换日期为年月日字符串
	 * @param dt
	 * @return
	 */
	public static String date2DateString(Date dt) {
		return new SimpleDateFormat("yyyy-MM-dd").format(dt);
	}
	
	/**
	 * 得到当前日期，String型
	 * @return
	 */
	public static String getSysDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return f.format(date);
	}
	
	/**
	 * 得到当前日期，java.sql.Date型
	 * @return
	 */
	public static java.sql.Date getSysSqlDate(){
		return new java.sql.Date(System.currentTimeMillis());
    }
	
	/**
	 * 得到当前日期，java.sql.Timestamp型
	 * @return
	 */
	public static Timestamp getSysSqlTimestamp(){
		return  new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 将String转为java.sql.Date, pattern为“yyyy-MM-dd”
	 * @param strDate
	 * @return
	 */
	public static java.sql.Date toSqlDate(String strDate){
		return toSqlDate(strDate, "yyyy-MM-dd");
	}
	
	/**
	 * 将String转为java.sql.Date
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static java.sql.Date toSqlDate(String strDate, String pattern){
		Date date = toDate(strDate,pattern);
		if( date != null )
		{
			return new java.sql.Date(date.getTime());
		}
		return null;
    }
	
	/**
	 * 
	 * @param strDate 需要转换的日期字符串 
	 * @param pattern 转换的样式
	 * @return
	 */
	public static Date toDate(String strDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date date = null;
		sdf.applyPattern(pattern);
		try {
			date = sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
     * JXL中通过DateCell.getDate()获取单元格中的时间为（实际填写日期+8小时），原因是JXL是按照GMT时区来解析XML。本方法用于获取单元格中实际填写的日期！
     * 例如单元格中日期为“2009-9-10”，getDate得到的日期便是“Thu Sep 10 08:00:00 CST 2009”；
     * 单元格中日期为“2009-9-10 16:00:00”，getDate得到的日期便是“Fri Sep 11 00:00:00 CST 2009”.
     * @param jxlDate 通过DateCell.getDate()获取的时间
     * @return
     * @throws ParseException 
     */
    public static java.util.Date convertDate4JXL(java.util.Date jxlDate)
			throws ParseException {
		if (jxlDate == null)
			return null;

		TimeZone gmt = TimeZone.getTimeZone("GMT");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		dateFormat.setTimeZone(gmt);
		String str = dateFormat.format(jxlDate);
		TimeZone local = TimeZone.getDefault();
		dateFormat.setTimeZone(local);
		return dateFormat.parse(str);

	}
    
    
	/**
	 * 得到当前年份，String型
	 * @return
	 */
	public static String getYear() {
		String date=DateUtil.getSysDateStr();
		return date.substring(0,4);
	}
	
	
	
	/**
	 * 当前日期往前取n个月
	 * @return
	 */
	public static String getLastNmonthStr(int n) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();//当前日期
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, -n);//月份减n
		return f.format(calendar.getTime());
	}
	
	
	/**
	 * 取上个月的第一天
	 * @return
	 */
	public static String getLastMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);//月份减1
	    cal.set(Calendar.DAY_OF_MONTH, 1);//1号
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    return f.format(cal.getTime());
	}
	
	/**
	 * 取上个月的最后一天
	 * @return
	 */
	public static String getLastMonthLastDay() {
		
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);//当前月的1号
	    cal.add(Calendar.DAY_OF_MONTH, -1);//1号的前一天
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    return f.format(cal.getTime());
	    
	}
		  
	/**
	 * 取给定日期的前一天
	 */	  
	
	public static String getDayBefore(String day){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
	    cal.setTime(toDate(day,"yyyy-MM-dd"));
	    cal.add(Calendar.DATE, -1);//前一天
	    
	    return f.format(cal.getTime());
	}
	


}
