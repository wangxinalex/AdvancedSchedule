package demo;

import java.io.IOException;
import java.util.*;

import data.CalendarManager;
import data.Schedule;
import util.Utils;
/**
 * ����ԭ��������PJ1�д�ӡ�ַ���������PJ2�и���Ϊ���ݲ�ͬ�·����ɲ�ͬ�ַ������飬������ʾ��ͬ�µ�����
 * @author Administrator
 *
 */
public class PrintCalendar {
	public String[] dateString = new String[42];
	private int dateStringIndex = 0;
	public   GregorianCalendar d = new GregorianCalendar();
	public int yearInTitle;
	public int monthInTitle;
	int titleBlank=0;
	int endingBlank=0;
	int today ;
	

	String titleString=null;
	public void DisplayMonth(int n) throws IOException, ClassNotFoundException,
			InterruptedException {
		
		for (int i = 0; i < 42; i++) {
			dateString[i] = "E";
		}
//		System.out.println("Welcome to Alex's Calendar!");
		@SuppressWarnings("unused")
		String myName = Utils.getWindowsCurrentUser();
//		System.out.println("Current user:" + myName);

		today = d.get(Calendar.DAY_OF_MONTH);
		int month = d.get(Calendar.MONTH);
		d.set(Calendar.DAY_OF_MONTH, 1);
		int a = 0;
		if (n >= 0) {
			if ((month + n) < 12) {
				a = n;
				n = n / 12;
			} else {
				if (n % 12 >= (12 - month)) {
					a = n % 12 - 12;
					n = n / 12 + 1;
				} else {
					a = n % 12;
					n = n / 12;
				}
			}
		} else {
			if (-n <= month) {
				a = -(-n % 12);
				n = n / 12;
			} else if ((-n % 12) <= (month)) {
				a = ((n % 12));
				n = n / 12;
			} else {
				a = 12 + (n % 12);
				n = n / 12 - 1;
			}
		}
		
		/**
		 * �������㣬aΪӦ���·ݣ�nΪӦ�����
		 */
		d.add(Calendar.MONTH, a);
		month = (month += a) % 12;
		d.add(Calendar.YEAR, (int) (n));
		yearInTitle = d.get(Calendar.YEAR);
		monthInTitle = month + 1;
		//System.out.println(titleString="Current Calendar:" + yearInTitle + "��"
		//		+ monthInTitle + "��");

		int weekday = d.get(Calendar.DAY_OF_WEEK);
//		System.out.println("Sun  Mon  Tue  Wed  Thu  Fri  Sat");
		for (int i = Calendar.SUNDAY; i < weekday; i++) {
			//System.out.print("     ");
			dateString[dateStringIndex++] = "T";//������ǰ�Ŀո���Ϊ��T�������º�Ŀո���Ϊ��E��
			titleBlank++;
		}
		while (d.get(Calendar.MONTH) == month) {
			int day = d.get(Calendar.DAY_OF_MONTH);
//			System.out.print((day < 10) ? " " + day : day);
			dateString[dateStringIndex++] = String.valueOf(day);
//			if (day == today)
//				System.out.print("*  ");
//			else
//				System.out.print("   ");
//			if (weekday == Calendar.SATURDAY)
//				System.out.println();
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		endingBlank=42-dateStringIndex;
		dateStringIndex = 0;
		//System.out.println();
		CalendarManager.loadRecordsFromFile();
		int occurrence = 0;
		Calendar c = Calendar.getInstance();
		//System.out.println("����ʱ�䣺"
			//	+ CalendarManager.dateFormatter1.format(c.getTime()));
		ArrayList<Schedule> scheduleToday = new ArrayList<Schedule>();
		scheduleToday = CalendarManager.schedulePool
				.get(CalendarManager.dateFormatter2.format(c.getTime()));
		//System.out.println(CalendarManager.titleString);
		if (scheduleToday == null) {
//			System.out.println("��ǰ����û�м�¼");
		} else {
			for (int i = 0; i < scheduleToday.size(); i++) {

				//System.out.println(i + "\t" + scheduleToday.get(i));
				occurrence++;

			}

//			System.out.println("[Message]�����칲��" + occurrence + "���ճ�");
		}

	}

	public String[] getDateString() {
		return dateString;
	}

	public String getTime() {
//		System.out.println(yearInTitle + "��"
//				+ monthInTitle + "��");
		return yearInTitle + "��"+ monthInTitle + "��";
	}
	public int getTitleBlank() {
		return titleBlank;
	}

	
	public int getEndingBlank() {
		return endingBlank;
	}
	public int getToday() {
		return today;
	}
	
}
