package com.maoding.core.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 类描述：时间操作定义类
 * 
 * @author:  张代浩
 * @date： 日期：2012-12-8 时间：下午12:15:03
 * @version 1.0
 */
public class DateUtils extends PropertyEditorSupport {
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
			"yyyy-MM-dd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf2 = new SimpleDateFormat(
			"yyyy/MM/dd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_ym = new SimpleDateFormat(
				"yyyy-MM");
	public static final SimpleDateFormat date_sdf3 = new SimpleDateFormat(
			"yyyy.MM.dd");
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat time_sdf_slash = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");
	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
	"yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
			"HH:mm");
	public static final  SimpleDateFormat datetimeFormat = new SimpleDateFormat(
	"yyyy-MM-dd HH:mm:ss");

	public static final  SimpleDateFormat leaveOffFormat = new SimpleDateFormat(
	"yyyy/MM/dd HH:mm");

	public static final  SimpleDateFormat workOutFormat = new SimpleDateFormat(
	"yyyy/MM/dd a",Locale.CHINESE);

	public static final String date_sdf2_string="yyyy/MM/dd";
	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;
	// 指定模式的时间格式
	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	/** 获取起始时间到终止时间的字符串 */
	public static String getTimeText(Date startTime, Date endTime, SimpleDateFormat fmt){
		String s = "";
		if (startTime != null) {
			s = DateUtils.date2Str(startTime, fmt);
		}
		if ((startTime != null) || (endTime != null)) {
			s += " — ";
		}
		if (endTime != null) {
			s += DateUtils.date2Str(endTime, fmt);
		}
		return s;
	}
	/**
	 * 当前日历，这里用中国时间表示
	 * 
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 指定毫秒数表示的日历
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(millis));
		return cal;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getDate
	// 各种方式获取的Date
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {
		return new Date(millis);
	}

	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String timestamptoStr(Timestamp time) {
		if (null != time) {
		}
		return date2Str(date_sdf);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, date_sdf);
		return new Timestamp(date.getTime());
	}
	/**
	 * 字符串转换成日期
	 * @param str
	 * @param sdf
	 * @return
	 */
	public static Date str2Date(String str, SimpleDateFormat sdf) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 */
	public static Date str2Date(String str) {
		if (StringUtil.isNullOrEmpty(str)) {
			return null;
		}
		Date date = null;
		SimpleDateFormat df = null;
		if(str.contains(".")){
			df = DateUtils.date_sdf3;
		}
		else if(str.contains("-")){
			if (str.contains(":")){
				df = DateUtils.time_sdf;
			} else {
				df = DateUtils.date_sdf;
			}
		}
		else if(str.contains("/")){
			df = DateUtils.date_sdf2;
		}else {
			df = DateUtils.yyyyMMdd;
		}
		try {
			date = df.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDateString(String str){
		if (StringUtil.isNullOrEmpty(str)){
			return null;
		}
		str = str.replaceAll("/","-");
		String[] date = str.split("-");
		Integer y = Integer.parseInt(date[0]);
		Integer m = 0 ;
		Integer d = 0;
		str = y+"";
		if(date.length>1){
			m = Integer.parseInt(date[1]);
			str = str+"-" +((m < 10) ? "0" + m : m);
		}
		if(date.length>2){
			d = Integer.parseInt(date[2]);
			str = str+"-" +((d < 10) ? "0" + d : d);
		}
		return str;
	}

	/**
	 * 日期转换为字符串
	 * 
	 *            日期
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(SimpleDateFormat date_sdf) {
		Date date=getDate();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 *            日期格式
	 * @return 字符串
	 */
	public static String date2Str(Date date, SimpleDateFormat date_sdf) {
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}
	/**
	 * 日期转换为字符串
	 * 
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String getDate(String format) {
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}

	/**
	 * 以字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
		return buydate;
	}

	// ////////////////////////////////////////////////////////////////////////////
	// getMillis
	// 各种方式获取的Millis
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 系统时间的毫秒数
	 * 
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis() {
		return new Date().getTime();
	}

	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}

	/**
	 * 指定日期的毫秒数
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的毫秒数
	 */
	public static long getMillis(Date date) {
		return date.getTime();
	}

	/**
	 * 系统时间的毫秒数
	 *
	 * @return 系统时间的毫秒数
	 */
	public static long getMillis(String date) {
		return str2Date(date,datetimeFormat).getTime();
	}

	/**
	 * 指定时间戳的毫秒数
	 * 
	 * @param ts
	 *            指定时间戳
	 * @return 指定时间戳的毫秒数
	 */
	public static long getMillis(Timestamp ts) {
		return ts.getTime();
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatDate
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日
	 * 
	 * @return 默认日期按“年-月-日“格式显示
	 */
	public static String formatDate() {
		return date_sdf.format(getCalendar().getTime());
	}
	/**
	 * 获取时间字符串
	 */
	public static String getDataString(SimpleDateFormat formatstr) {
		return formatstr.format(getCalendar().getTime());
	}
	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Calendar cal) {
		return date_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日“格式显示
	 */
	public static String formatDate(Date date) {
		return date_sdf.format(date);
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日“格式显示
	 */
	public static String formatDate(long millis) {
		return date_sdf.format(new Date(millis));
	}

	/**
	 * 默认日期按指定格式显示
	 * 
	 * @param pattern
	 *            指定的格式
	 * @return 默认日期按指定格式显示
	 */
	public static String formatDate(String pattern) {
		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param cal
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Calendar cal, String pattern) {
		return getSDFormat(pattern).format(cal.getTime());
	}

	/**
	 * 指定日期按指定格式显示
	 * 
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 指定日期按指定格式显示
	 */
	public static String formatDate(Date date, String pattern) {
		return getSDFormat(pattern).format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：年-月-日 时：分
	 * 
	 * @return 默认日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime() {
		return time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(long millis) {
		return time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Calendar cal) {
		return time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年-月-日 时：分“格式显示
	 */
	public static String formatTime(Date date) {
		return time_sdf.format(date);
	}
	/**
	 * 指定日期的默认显示，具体格式：年-月-日 时：分
	 *
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“年/月/日 时：分“格式显示
	 */
	public static String formatTimeSlash(Date date) {
		return time_sdf_slash.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// formatShortTime
	// 将日期按照一定的格式转化为字符串
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 默认方式表示的系统当前日期，具体格式：时：分
	 * 
	 * @return 默认日期按“时：分“格式显示
	 */
	public static String formatShortTime() {
		return short_time_sdf.format(getCalendar().getTime());
	}

	/**
	 * 指定毫秒数表示日期的默认显示，具体格式：时：分
	 * 
	 * @param millis
	 *            指定的毫秒数
	 * @return 指定毫秒数表示日期按“时：分“格式显示
	 */
	public static String formatShortTime(long millis) {
		return short_time_sdf.format(new Date(millis));
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param cal
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Calendar cal) {
		return short_time_sdf.format(cal.getTime());
	}

	/**
	 * 指定日期的默认显示，具体格式：时：分
	 * 
	 * @param date
	 *            指定的日期
	 * @return 指定日期按“时：分“格式显示
	 */
	public static String formatShortTime(Date date) {
		return short_time_sdf.format(date);
	}

	// ////////////////////////////////////////////////////////////////////////////
	// parseDate
	// parseCalendar
	// parseTimestamp
	// 将字符串按照一定的格式转化为日期或时间
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 */
	public static Date parseDate(String src, String pattern)
			throws ParseException {
		return getSDFormat(pattern).parse(src);

	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的日期
	 * @throws ParseException
	 */
	public static Calendar parseCalendar(String src, String pattern)
			throws ParseException {

		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatAddDate(String src, String pattern, int amount)
			throws ParseException {
		Calendar cal;
		cal = parseCalendar(src, pattern);
		cal.add(Calendar.DATE, amount);
		return formatDate(cal);
	}

	/**
	 * 根据指定的格式将字符串转换成Date 如输入：2003-11-19 11:20:20将按照这个转成时间
	 * 
	 * @param src
	 *            将要转换的原始字符窜
	 * @param pattern
	 *            转换的匹配格式
	 * @return 如果转换成功则返回转换后的时间戳
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(String src, String pattern)
			throws ParseException {
		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	// ////////////////////////////////////////////////////////////////////////////
	// dateDiff
	// 计算两个日期之间的差值
	// ////////////////////////////////////////////////////////////////////////////

	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}

	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar calSrc = Calendar.getInstance();
		calSrc.setTime(fDate);

		Calendar calDes = Calendar.getInstance();
		calSrc.setTime(oDate);

		long millisDiff = fDate.getTime() - oDate.getTime();

		return (int) (millisDiff / DAY_IN_MILLIS);
	}

	public static int daysOfTwo(String fDate, String oDate) {
		if(StringUtil.isNullOrEmpty(fDate) || StringUtil.isNullOrEmpty(oDate)){
			return 0;
		}
		Date fDate1 =  DateUtils.str2Date(fDate, DateUtils.date_sdf);
		Calendar calSrc = Calendar.getInstance();
		calSrc.setTime(fDate1);

		Date oDate2 =  DateUtils.str2Date(oDate, DateUtils.date_sdf);
		Calendar calDes = Calendar.getInstance();
		calSrc.setTime(oDate2);

		long millisDiff = fDate1.getTime() - oDate2.getTime();

		return (int) (millisDiff / DAY_IN_MILLIS);
	}

    /**
     * String类型 转换为Date,
     * 如果参数长度为10 转换格式”yyyy-MM-dd“
     *如果参数长度为19 转换格式”yyyy-MM-dd HH:mm:ss“
     * * @param text
	 *             String类型的时间值
     */
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(DateUtils.date_sdf.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(DateUtils.datetimeFormat.parse(text));
				} else {
					throw new IllegalArgumentException(
							"Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage(), ex);
                throw iae;
			}
		} else {
			setValue(null);
		}
	}
	public static int getYear(){
	    GregorianCalendar calendar=new GregorianCalendar();
	    calendar.setTime(getDate());
	    return calendar.get(Calendar.YEAR);
	  }
	
	public static String[] getNMonths(String month, int N)
	{
		String[] months = new String[N];
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try
		{
			date = dt.parse(month);

			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			for (int i = 0; i < N; i++)
			{
				months[i] = dt.format(cl.getTime());
				cl.add(Calendar.MONTH, -1);

			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return months;
	}
	
	public static String[] getNMonthByYear(String year)
	{
		String month = null;
		int N = 0;
		if(year == null || year.equals("") || Integer.parseInt(year)==getYear()){
			GregorianCalendar calendar=new GregorianCalendar();
		    calendar.setTime(getDate());
			N=calendar.get(Calendar.MONTH)+1;
			month=date2Str(date_sdf);
		}else{
			month = year+"-12-01";
			N = 12;
		}
		String[] months = new String[N];
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try
		{
			date = dt.parse(month);

			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			for (int i = 0; i < N; i++)
			{
				months[i] = dt.format(cl.getTime());
				cl.add(Calendar.MONTH, -1);

			}

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return months;
	}

	/**
	 * 方法描述：当前天(d)的后面几天(ay)
	 * 作        者：MaoSF
	 * 日        期：2015年12月9日-上午11:18:55
	 * @param d
	 * @param day
	 * @return
	 */
	 public static String getDateAfter(Date d, int day) {  
		 	GregorianCalendar calendar=new GregorianCalendar();
		    calendar.setTime(d);
		    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);  
		    String dataStr=date_sdf.format(calendar.getTime());
	        return dataStr;  
	    }
	 
		/**
		 * 方法描述：当前天(d)的后面几天(ay)
		 * 作        者：MaoSF
		 * 日        期：2015年12月9日-上午11:18:55
		 * @param d
		 * @param day
		 * @return
		 */
	 public static String getDateAfter(String d, int day) {  
		 	GregorianCalendar calendar=new GregorianCalendar();
		 	Date date;
			try {
				date = date_sdf.parse(d);
				 calendar.setTime(date);
			    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);  
			    String dataStr=date_sdf.format(calendar.getTime());
		        return dataStr;  
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		   
	    }

	/**
	 * 方法描述：获取两个日期之间的日期数
	 * 作        者：MaoSF
	 * 日        期：2015年12月9日-上午11:18:55
	 * @return
	 */
	public static List<String> getBetweenDates(Date d1,Date d2) {
		List<String> dateList = new ArrayList<>();
		try {
			String startDate = null;
			String endDate = null;
			if(!StringUtil.isNullOrEmpty(d1)){
				 startDate = DateUtils.formatDate(d1);
				 dateList.add(startDate);
			}
			if(!StringUtil.isNullOrEmpty(d2)){
				endDate = DateUtils.formatDate(d2);
				if(!dateList.contains(endDate)){
					dateList.add(endDate);
				}
			}
			//如果存在d2>d1的情形
			if(!StringUtil.isNullOrEmpty(startDate) && !StringUtil.isNullOrEmpty(endDate) && DateUtils.datecompareDate(startDate,endDate)<0){
				String currentDate = startDate;
				while (!getDateAfter(currentDate,1).equals(endDate)){
					currentDate = getDateAfter(currentDate,1);
					dateList.add(currentDate);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateList;
	}

	/**
	 * 方法描述：当前天的后面几天(ay)
	 * 作        者：MaoSF
	 * 日        期：2015年12月9日-上午11:18:55
	 * @param day
	 * @return
	 */
	public static Date getDateAfter2(Date d, int day) {
		if(d==null){//如果d为null，则获取当前天的日期
			d = getDate();
		}
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(d);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		return calendar.getTime();
	}

	 /**
	  * 方法描述：判断两个日期的大小，date1==date2:return :0 date1<date2:return <0  date1>date2:return>0
	  * 作        者：MaoSF
	  * 日        期：2015年11月4日-上午9:58:20
	  * @param date1
	  * @param date2
	  * @return
	  */
	 public static int datecompareDate(String date1,String date2){
		 if(date1==null || "".equals(date1)){
			 return -1;
		 }
		 if(date2==null || "".equals(date2)){
			 return 1;
		 }
	 	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		try
		{
		c1.setTime(df.parse(date1));
		c2.setTime(df.parse(date2));
		}catch(java.text.ParseException e){
		System.err.println("格式不正确");
		}
		int  result=c1.compareTo(c2);
		return result;
	 }

	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
		// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess=false;
		}
		return convertSuccess;
	}

	public static Date isValidDate2(Object str) {
		try{
			return (Date)str;
		}catch (Exception e){
			// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				format.setLenient(false);
				return format.parse((String)str);
			}catch (Exception e2) {
				try{
					format2.setLenient(false);
					return format2.parse((String)str);
				}catch (Exception e3){
					return null;
				}

			}
		}
	}
	/**
	 * 格式化时间 0 :昨天，1:今天,2,某天，3错误参数
	 * @param time
	 * @return
	 */
	public static int formatDateTime(String time) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(time==null ||"".equals(time)){
			return 3;
		}
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar current = Calendar.getInstance();

		Calendar today = Calendar.getInstance();    //今天

		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH));
		//  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set( Calendar.HOUR_OF_DAY, 0);
		today.set( Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		Calendar yesterday = Calendar.getInstance();    //昨天

		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
		yesterday.set( Calendar.HOUR_OF_DAY, 0);
		yesterday.set( Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);

		current.setTime(date);

		if(current.after(today)){
			//return "今天 "+time.split(" ")[1];
			return 1;
		}else if(current.before(today) && current.after(yesterday)){

			//return "昨天 "+time.split(" ")[1];
			return 0;
		}else{
			//int index = time.indexOf("-")+1;
			//return time.substring(index, time.length());
			return 2;
		}
	}

	public static boolean isDate(String date)
	{
		/**
		 * 判断日期格式和范围
		 */
		String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

		Pattern pat = Pattern.compile(rexp);

		Matcher mat = pat.matcher(date);

		boolean dateType = mat.matches();

		return dateType;
	}


	/**
	 * 方法描述：获取两天直接的工作日
	 * 作者：MaoSF
	 * 日期：2017/1/9
	 * @param:
	 * @return:
	 */
	public static int getDutyDays(String strStartDate,String strEndDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date endDate = null;

		try {
			startDate=df.parse(strStartDate);
			endDate = df.parse(strEndDate);
		} catch (ParseException e) {
			System.out.println("非法的日期格式,无法进行转换");
			e.printStackTrace();
		}
		int result = 0;
		while (startDate.compareTo(endDate) <= 0) {
			if (startDate.getDay() != 6 && startDate.getDay() != 0)
				result++;
			startDate.setDate(startDate.getDate() + 1);
		}

		return result;
	}


	/**
	 * 只使用与yyyy-MM格式
	 */
	public static String getLastMonth(String month) {
		if (StringUtil.isNullOrEmpty(month)) {
			return date_sdf_ym.format(Calendar.getInstance().getTime());
		} else {
			if (month.contains("-")) {
				int year = Integer.parseInt(month.substring(0, 4));
				int mon = Integer.parseInt(month.substring(5));
				if (mon == 1) {
					return (year - 1) + "-12";
				}
				return year + "-" + String.format("%2d", (mon - 1)).replace(" ", "0");
			}
		}
		return null;
	}

	public static String getLastMonth(){
		return getLastMonth(null);
	}


	//判断选择的日期是否是本月
	public static boolean isThisMonth(Date date)
	{
		return isThisTime(date,"yyyy-MM");
	}
	private static boolean isThisTime(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);//参数时间
		String now = sdf.format(new Date());//当前时间
		if(param.equals(now)){
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception{
		List<String> afterDate = DateUtils.getBetweenDates(DateUtils.str2Date("2018-01-05"),DateUtils.str2Date("2018-01-07"));

		System.out.println(afterDate);
//
		System.out.println(DateUtils.workOutFormat.format(DateUtils.getDate()));
//		System.out.println(DateUtils.str2Date("2017-03-01"));
//		System.out.println(DateUtils.str2Date("2017.03.01"));
//		System.out.println(DateUtils.str2Date("2017/03/01"));
//		System.out.println(DateUtils.str2Date("2017/4/1"));
//		System.out.println(DateUtils.str2Date("2017-3-1"));
	}

	public static String addDate(String day, int x)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
		Date date = null;
		try
		{
			date = format.parse(day);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (date == null) return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);//24小时制
		//cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}


	public static String addDate(Date date, int x)
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		if (date == null) return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, x);//24小时制
		//cal.add(Calendar.HOUR, x);12小时制
		date = cal.getTime();
		System.out.println("front:" + date);
		cal = null;
		return format.format(date);
	}

}