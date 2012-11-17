package frame;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import data.Schedule;


/**
 * 本类用于显示日程标签，与schedule具有相似的属性和方法
 */
@SuppressWarnings("serial")
public class ItemLabel extends JLabel {
	private int order = 0;
	private String title;
	private String location;
	private Date startTime;
	private Date endTime;
	private String priority;
	private boolean allDay;
	private boolean birthdayAlarm;
	private boolean isDone;
	private String introduction;
	
	
	static SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	 static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
	"yyyy/MM/dd");
	 static SimpleDateFormat dateFormatter3 = new SimpleDateFormat("HH:mm");



/**
 * @return title
 */
	public boolean isBirthdayAlarm() {
		return birthdayAlarm;
	}

	public void setBirthdayAlarm(boolean birthdayAlarm) {
		this.birthdayAlarm = birthdayAlarm;
	}
	
/**
 * set title to schedule
 * @param title
 */

/**
 * @return location
 */
	public String getLocationStr() {
		return location;
	}
/**
 * set location to Schedule
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
 * @param time
 */
	public void setStartTime(Date time) {
		this.startTime = time;
	}
	public String getStartTimeStr(){
		return dateFormatter3.format(this.startTime);
	}
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getEndTimeStr(){
		return dateFormatter3.format(this.endTime);
	}
/**
 * @return priority
 */
	public String getPriority() {
		return priority;
	}
/**
 * set priority to Schedule using index
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
 * @param priorityStr
 */
	public void setPriority(String priorityStr) {
		this.priority = priorityStr;
	}
/**
 * set allDay to Schedule
 * @param allDay
 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
/**
 * @return allDay
 */
	public boolean isAllDay() {
		return allDay;
	}
/**
 * overwrite the compareTo method
 */
	public int compareTo(Object o) {
		int i = 0;
		do {
			char theOrder = (dateFormatter1.format(((Schedule) o).getStartTime())
					.charAt(i));

			if (dateFormatter1.format(startTime).charAt(i) < theOrder)
				return -1;
			else if (dateFormatter1.format(startTime).charAt(i) > theOrder)
				return 1;
			else
				i++;
		} while (i < Math.min(dateFormatter1.format(startTime).length(),
				((dateFormatter1.format(((Schedule) o).getStartTime()).length()))));
		return 0;

	}
/**
 * overwrite the toString method
 */
	public String toString() {//按allDay取值不同，分别重写toString方法，在时间一栏显示不同内容

		if (!allDay&&!birthdayAlarm) {
			return this.title + "\t"
					+ dateFormatter1.format(this.startTime) + "\t" + this.location
					+ "\t" + this.getPriority();
		}

		else if(allDay&&!birthdayAlarm){
			return  this.title + "\t" + dateFormatter2.format(this.startTime)+" All Day" + "\t"
					+ this.location + "\t" + this.getPriority();
		}else {
			return  this.title + "\t" + dateFormatter2.format(this.startTime)+" Birthday" + "\t"
			+ this.location + "\t" + this.getPriority();
		}
	}
	public String getIntroduction() {
		if(birthdayAlarm){
			introduction="生日提醒 "+this.title;
		}else if(allDay){
			introduction="全天事件 "+this.title;
		}else{
			introduction=dateFormatter3.format(startTime)+"-"+dateFormatter3.format(endTime)+" "+this.title;
		}
		return introduction;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isDone() {
		return isDone;
	}
}
