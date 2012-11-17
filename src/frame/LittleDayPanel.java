package frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Schedule;
import demo.PrintCalendar;
/**
 * 本类用于封装每一月的月历信息并以6*7的方格阵显示，本类的构造方法DayPanel(int n)中参数n用于表示与当前月的差值，如-1表示前一月，1表示后一月
 */
public class LittleDayPanel extends ImagePanel {
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JPanel eachDayPanel = null;
	private JPanel dayPanel = null;
	@SuppressWarnings("unused")
	private Font f1 = new Font("YouYuan", Font.PLAIN, 12);
	@SuppressWarnings("unused")
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	
	private GregorianCalendar d = new GregorianCalendar();
	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
			"yyyy/MM");
	ArrayList<Schedule> scheduleToday = new ArrayList<Schedule>();  //  @jve:decl-index=0:
	private PrintCalendar cal = new PrintCalendar();
	@SuppressWarnings("unused")
	private String titleStr = null;
	private String[] dateString = new String[42];
	private int yearInTitle;
	private int monthInTitle;
	private int titleBlank = 0;
	private int endingBlank = 0;
	private int today = 0;

	public LittleDayPanel(int index1) throws IOException, ClassNotFoundException,
			InterruptedException {
		initialize(index1);
	}
/**
 *本方法用于根据传入的参数n（与当前月的差值）判断具体的年月日 
 *
 */
	public void initialize(int index1) throws IOException,
			ClassNotFoundException, InterruptedException {
		
		this.setLayout(new BorderLayout());
		cal.DisplayMonth(index1);
		dateString = cal.getDateString();
		titleBlank = cal.getTitleBlank();
		endingBlank = cal.getEndingBlank();
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7));
		int month = d.get(Calendar.MONTH);
		d.set(Calendar.DAY_OF_MONTH, 1);
		int a = 0;
		if (index1 >= 0) {
			if ((month + index1) < 12) {
				a = index1;
				index1 = index1 / 12;
			} else {
				if (index1 % 12 >= (12 - month)) {
					a = index1 % 12 - 12;
					index1 = index1 / 12 + 1;
				} else {
					a = index1 % 12;
					index1 = index1 / 12;
				}
			}
		} else {
			if (-index1 <= month) {
				a = (index1 % 12);
				index1 = index1 / 12;
			} else if ((-index1 % 12) <= (month)) {
				a = ((index1 % 12));
				index1 = index1 / 12;
			} else {
				a = 12 + (index1 % 12);
				index1 = index1 / 12 - 1;
			}
		}
		d.add(Calendar.MONTH, a);
		month = (month += a) % 12;
		d.add(Calendar.YEAR, (int) (index1));
		//d.set(Calendar.YEAR, yearInTitle);
		yearInTitle = d.get(Calendar.YEAR);


		monthInTitle = month + 1;
		today=cal.getToday();
		
		addEachDay(yearInTitle, monthInTitle);
		
//		System.out.println("year " + yearInTitle + "month" + monthInTitle);
		this.add(dayPanel, BorderLayout.CENTER);
		
		
	}

	private void addEachDay(int year, int month) throws IOException,
			ClassNotFoundException, InterruptedException {
		for (int i = 0; i < 42; i++) {
			
			String[] s =handleDateString();
			LittleEachDayLabel thisDayLabel = new LittleEachDayLabel(year,month,i, s,yearInTitle,monthInTitle,titleBlank,endingBlank,today);
			
			dayPanel.add(thisDayLabel);
			
			


		}

	}
/**
 * 本方法用于进一步处理字符串，为当前月方格、前一月方格、后一月方格。跨年日方格等
 * 添加上不同的表示方式，处理后的字符串即为最终显示形式
 */
	private String[] handleDateString() {
	

		for (int i = 0; i < 42; i++) {
			
			if (dateString[i].equals("1")) {
				StringBuffer buf  =new StringBuffer(dateString[i]);
				
				buf.append("日");
				buf.insert(0, "月");
				buf.insert(0,monthInTitle);
				dateString[i]=buf.toString();
			} else if (dateString[i].matches("[\\d]*{2}")) {
				StringBuffer buf  =new StringBuffer(dateString[i]);
				buf.append("日") ;
				dateString[i]=buf.toString();
			} else if (dateString[i].equals("T")) {
				int numberOfLast;
				if (monthInTitle == 1) {
					numberOfLast = getDayOfMonth(yearInTitle - 1, 12);
				} else {
					numberOfLast = getDayOfMonth(yearInTitle, monthInTitle - 1);
				}
				int beginDay = numberOfLast - titleBlank + 1 + i;
				if (i == 0) {
					if (monthInTitle == 1) {
						StringBuffer buf  =new StringBuffer(String.valueOf(yearInTitle - 1));
						buf.append("年");
						buf.append("12月");
						buf.append(beginDay);
						buf.append("日");
						dateString[i] = buf.toString();
					} else {
						StringBuffer buf  =new StringBuffer(String.valueOf(monthInTitle - 1));
						buf.append("月");
						buf.append(beginDay);
						buf.append("日");
						
						dateString[i] = buf.toString();
					
					}
				} else {
					dateString[i] = beginDay + "日";
				}
			} else if (dateString[i].equals("E")) {
				int beginningIndex = 42 - endingBlank;
				//System.out.println("endingBlank" + endingBlank);
				int nextMonthDay = i - beginningIndex + 1;
				if (nextMonthDay == 1) {
					if (monthInTitle == 12) {
						dateString[i] = (yearInTitle + 1) + "年" + "1月"
								+ nextMonthDay + "日";
					} else {
						dateString[i] = (monthInTitle + 1) + "月" + nextMonthDay
								+ "日";
					}
				} else {
					dateString[i] = nextMonthDay + "日";
				}
			}
		}
		
		return dateString;
	}
	
	

	public JPanel getPanel() {
		if (dayPanel == null) {
			dayPanel = new JPanel();
			dayPanel.setLayout(new GridLayout(6, 7));
		}
		return dayPanel;
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {
		LittleDayPanel test = new LittleDayPanel(0);
		JFrame testFrame = new JFrame();
		testFrame.add(test.dayPanel);
		testFrame.setSize(800, 600);
		testFrame.setVisible(true);
		
	}

	public String getTimeStr() {
		return yearInTitle + "年" + monthInTitle + "月";
	}
/**
 * 获得每一月的天数
 * @param year
 * @param month
 * @return
 */
	private int getDayOfMonth(int year, int month) {
		int dayOfMonth = 0;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dayOfMonth = 31;
				break;
			case 2:
				dayOfMonth = 29;
				break;
			default:
				dayOfMonth = 30;
				break;
			}
		} else {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dayOfMonth = 31;
				break;
			case 2:
				dayOfMonth = 28;
				break;
			default:
				dayOfMonth = 30;
				break;
			}
		}
		return dayOfMonth;
	}
	
}
