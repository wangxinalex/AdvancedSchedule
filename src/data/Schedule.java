/**
 * Schedule 类，用于存放和联系人有关的各项属性和方法
 */
package data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日程类，保存日程的各种基本信息
 * @author Administrator
 *
 */
public class Schedule implements Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1L;

	private String title;
	private String location;
	private Date startTime;
	private Date endTime;
	private String priority;
	private boolean allDay;
	private boolean birthdayAlarm;
	private boolean isDone;
	private String introduction;
	private String[] scheduleInfo = new String[7];

	static SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	static SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat dateFormatter3 = new SimpleDateFormat("HH:mm");

	/**
	 * @param orderStr
	 * @param title
	 * @param startTime
	 * @param location
	 * @param priority
	 * @param allDay
	 */
	public Schedule(String title, Date startTime, Date endTime,
			String location, String priority, boolean allDay,
			boolean birthdayAlarm,boolean isDone) {
		this.title = title;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.priority = priority;
		this.allDay = allDay;
		this.birthdayAlarm = birthdayAlarm;
		this.isDone=isDone;
	}

	/**
	 * @return title
	 */
	public boolean getBirthdayAlarm() {
		return birthdayAlarm;
	}

	public void setBirthdayAlarm(boolean birthdayAlarm) {
		this.birthdayAlarm = birthdayAlarm;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * set title to schedule
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * set location to Schedule
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * set time to Schedule
	 * 
	 * @param time
	 */
	public void setStartTime(Date time) {
		this.startTime = time;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * set priority to Schedule using index
	 * 
	 * @param priorityInt
	 */
	public void setPriority(int priorityInt) {
		switch (priorityInt) {
		case 1:
			this.priority = "high";
			break;
		case 2:
			this.priority = "medium";
			break;
		case 3:
			this.priority = "low";
			break;
		default:
			this.priority = "uncertain";
			break;
		}
	}

	/**
	 * set priority to Schedule using String
	 * 
	 * @param priorityStr
	 */
	public void setPriority(String priorityStr) {
		this.priority = priorityStr;
	}

	/**
	 * set allDay to Schedule
	 * 
	 * @param allDay
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * @return allDay
	 */
	public boolean getAllDay() {
		return allDay;
	}

	/**
	 * overwrite the compareTo method
	 */
	public int compareTo(Object o) {
		int i = 0;
		do {
			char theOrder = (dateFormatter1.format(((Schedule) o)
					.getStartTime()).charAt(i));

			if (dateFormatter1.format(startTime).charAt(i) < theOrder)
				return -1;
			else if (dateFormatter1.format(startTime).charAt(i) > theOrder)
				return 1;
			else
				i++;
		} while (i < Math
				.min(dateFormatter1.format(startTime).length(),
						((dateFormatter1.format(((Schedule) o).getStartTime())
								.length()))));
		return 0;

	}

	/**
	 * 按allDay取值不同，分别重写toString方法，在时间一栏显示不同内容
	 */
	public String toString() {

		if (!allDay && !birthdayAlarm) {
			return this.title + "\t" + dateFormatter1.format(this.startTime)
					+ "\t" + this.location + "\t" + this.getPriority();
		}

		else if (allDay && !birthdayAlarm) {
			return this.title + "\t" + dateFormatter2.format(this.startTime)
					+ " All Day" + "\t" + this.location + "\t"
					+ this.getPriority();
		} else {
			return this.title + "\t" + dateFormatter2.format(this.startTime)
					+ " Birthday" + "\t" + this.location + "\t"
					+ this.getPriority();
		}
	}

	public String getIntroduction() {
		if (birthdayAlarm) {
			introduction = "生日提醒 " + this.title;
		} else if (allDay) {
			introduction = "全天事件 " + this.title;
		} else {
			introduction = dateFormatter3.format(startTime) + "-"
					+ dateFormatter3.format(endTime) + " " + this.title;
		}
		return introduction;
	}

	public String[] printindetailTable() {
		scheduleInfo[0] = this.getTitle();
		scheduleInfo[1] = this.getLocation();
		scheduleInfo[2] = dateFormatter1.format(this.getStartTime());
		scheduleInfo[3] = dateFormatter1.format(this.getEndTime());
		scheduleInfo[4] = this.getPriority();
		scheduleInfo[5] = this.getAllDay() ? "  √" : " ";
		scheduleInfo[6] = this.getBirthdayAlarm() ? "  √" : " ";

		return scheduleInfo;

	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}
}
