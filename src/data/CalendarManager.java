/**
 *  本类用于存放各种功能单元
 */

package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import util.Utils;
import data.Schedule;
import data.CalendarManager;
import demo.PrintCalendar;
/**
 * 实现日程类的各种底层操作
 * @author Administrator
 *
 */
public class CalendarManager {
	
	public static ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
	static ArrayList<Contact> contactList = new ArrayList<Contact>();
	public static HashMap<String, ArrayList<Schedule>> schedulePool = new HashMap<String, ArrayList<Schedule>>();
	static HashMap<String, Contact> contactPool = new HashMap<String, Contact>();
	public static SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
			"yyyy/MM/dd");
	public static SimpleDateFormat dateFormatter3 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss EE");
	HashMap<String, String> listSchedule = new HashMap<String, String>();
	static boolean isListed;
	public static Date selectDate = null;
	public static String titleString = "序号\t标题\t\t时间\t\t地点\t优先级";
	public static ArrayList<Schedule> selectDateList;

	/**
	 * This method is used to make sure the schedulePool not null. you should
	 * call this after you load your records from your file.
	 * 
	 */
	public static void checkSchedulePool() {
		if (schedulePool == null)
			schedulePool = new HashMap<String, ArrayList<Schedule>>();
	}

	/**
	 * You use this method to insert a new Schedule into your schedulePool
	 * 
	 * @param s
	 *            the schedule you want to insert
	 */
	public static void addNewSchedule(Schedule s) {
		String key = dateFormatter2.format(s.getStartTime());
		if (schedulePool == null) {
			System.out
					.println("[Error]schedulePool is null, please make sure your loadRecordsFromFile is implemented and if it is the first time "
							+ "you run the programe you should new a HashMap");
			return;
		}
		ArrayList<Schedule> pool = schedulePool.get(key);
		if (pool == null) {
			pool = new ArrayList<Schedule>();
			pool.add(s);
			schedulePool.put(key, pool);
		} else {
			pool.add(s);
		}
	}

	/**
	 * If you want to modify a schedule, you should find it first. You can use
	 * this method to find the schedule for the special date and index.
	 * 
	 * @param allTags
	 *            the date of the schedule you want to modify.
	 * @param index
	 *            the index of the schedule you want to modify
	 * @return null for error or schedule not found, you should check the return
	 *         value of this method.
	 */
	public static Schedule getScheduleByDateAndIndex(Date date, int index) {
		String key = dateFormatter2.format(date);

		if (schedulePool == null) {
			System.out
					.println("[Error]schedulePool is null, please make sure your loadRecordsFromFile is implemented and if it is the first time "
							+ "you run the programe you should new a HashMap");
			return null;
		}
		ArrayList<Schedule> pool = schedulePool.get(key);
		if (pool == null) {
			System.out.println("[Error] No Schedule found on the date " + key);
			return null;
		} else {
			return index >= pool.size() ? null : pool.get(index);
		}
	}

	/**
	 * You use this method to delete your schedules and you just need to pass
	 * the date, and the index of the schedule you want to delete to the method
	 * 
	 * @param selectedDate
	 *            the date of the schedule you want to delete
	 * @param index
	 *            the index of the schedule you want to delete.
	 */
	public static void deleteScheduleByDateAndIndex(Date selectedDate, int index) {
		String key = dateFormatter2.format(selectedDate);
		if (schedulePool == null) {
			System.out
					.println("[Error]schedulePool is null, please make sure your loadRecordsFromFile is implemented and if it is the first time "
							+ "you run the programe you should new a HashMap");
			return;
		}
		ArrayList<Schedule> pool = schedulePool.get(key);
		if (pool != null && index < pool.size()) {
			pool.remove(index);
			if (pool.size() == 0)
				schedulePool.remove(key);
			System.out.println("[Success] 事务删除成功！");
			//System.out.println(selectedDate);
		} else {
			System.out.println("[Error] 参数错误，找不到index指代的schedule");
		}
	}

	/**
	 * You should call this method at the beginning of your program. This Method
	 * is used to load your stored schedules from file. After you call this
	 * method, you should make sure the variable "schedulePool" is not null by
	 * calling the method checkSchedulePool(). This will only happen the first
	 * time you run your program, or the storage file is deleted. You should
	 * implement the method yourselves.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public static void loadRecordsFromFile() throws IOException,
			ClassNotFoundException, InterruptedException {
		File recorder = new File("Schedule.data");
		if (!recorder.exists()) {
			boolean exists = recorder.createNewFile();
			if (!exists) {
				System.out
						.println("The file Schedule.data has already existed before created\n"
								+ "or the step of cteating Schedule.data has failed.\n"
								+ "Enter any key and MyCalendar will exit.");
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
				System.exit(0);
			}
		}
		FileInputStream input = new FileInputStream(recorder);
		deSerialize(input);
	}

	/**
	 * This method is to save your records(schedules) into file. You should call
	 * this method every time you made change on your "schedulePool". You should
	 * implement the method yourselves.
	 * 
	 * @throws IOException
	 */
	public static void saveRecordsToFile() throws IOException {
		File recorder = new File("Schedule.data");
		FileOutputStream output = new FileOutputStream(recorder);
		Serialize(output);
	}

	/**
	 * This method is used to load records that stored in the file into memory.
	 * You just need to pass a file input stream as an arugments to it, and this
	 * method will load all the records into schedulePool.
	 * 
	 * @param fReader
	 *            the file input stream you need to pass as an argument.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void deSerialize(FileInputStream fReader) throws IOException,
			ClassNotFoundException {
		try {
			ObjectInputStream in = new ObjectInputStream(fReader);
			schedulePool = (HashMap<String, ArrayList<Schedule>>) in
					.readObject();
		} catch (EOFException e) {
		}
	}

	/**
	 * This method is fully implemented, and it is used to save objects into
	 * files. All you want to do is to pass a file output stream of your storage
	 * file to it.
	 * 
	 * @param fWriter
	 *            the file out put stream you need to pass as an argument.
	 * @throws IOException
	 */
	public static void Serialize(FileOutputStream fWriter) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(fWriter);
		out.writeObject(schedulePool);
	}

	public CalendarManager() {

	}

	/**
	 * 用于实现previous和next功能
	 * 
	 * @param inputStr
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException
	 */
	public void month(String inputStr) throws IOException,
			ClassNotFoundException, InterruptedException, NumberFormatException {
		PrintCalendar d = new PrintCalendar();
		if (!inputStr.contains(" ")) {
			if (inputStr.equals("previous")) {
				d.DisplayMonth(-1);
			}
			if (inputStr.equals("next")) {
				d.DisplayMonth(1);
			}
		} else {
			String[] posStrArr;

			posStrArr = inputStr.split(" ");

			if (posStrArr[0].equals("previous")) {

				if (posStrArr[1].equals(null)) {
					d.DisplayMonth(-1);
				} else {
					String s = posStrArr[1];
					int n = Integer.parseInt(s);
					d.DisplayMonth(-n);
				}

			}

			if (posStrArr[0].equals("next")) {
				if (posStrArr[1].equals(null)) {
					d.DisplayMonth(1);
				} else {
					String s = posStrArr[1];
					int n = Integer.parseInt(s);
					d.DisplayMonth(n);
				}
			}
		}
	}


	/**
	 * 将字符串转换为Date类型，包括带时间日期和不带时间日期
	 * 
	 * @param Date
	 * @return
	 */

	public static Date getDate(String Date) {
		int year, month, day, hour, minute, second;

		String[] posStrArr = Date.split(" ");
		StringTokenizer myDate = new StringTokenizer(posStrArr[0], "/");
		year = Integer.parseInt(myDate.nextToken());
		month = Integer.parseInt(myDate.nextToken());
		day = Integer.parseInt(myDate.nextToken());
		if (Date.contains(" ")) {
			StringTokenizer myTime = new StringTokenizer(posStrArr[1], ":");
			hour = Integer.parseInt(myTime.nextToken());
			minute = Integer.parseInt(myTime.nextToken());
			second = Integer.parseInt(myTime.nextToken());
			Calendar now = Calendar.getInstance();
			now.clear();
			now.set(year, month - 1, day, hour, minute, second);
			return now.getTime();
		} else {

			Calendar now = Calendar.getInstance();
			now.clear();
			now.set(year, month - 1, day);
			return now.getTime();
		}
	}

	/**
	 * 判断输入日期是否合法,用Calendar.set()方法将日期加载，如果出现Exception，则将valid改为false
	 * 
	 * @param string
	 * @return
	 */

	public static boolean isValid(String string) {
		boolean valid = true;
		if (string.contains(" ")) {// 如果字符串中包含空格，则为带时间的日期格式
			if (!string
					.matches("[\\d]{4}/[\\d]{2}/[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}")) {// 用正则表达式判断字符串格式
				valid = false;
			}
			String[] posStrArr = string.split(" ");
			try {
				int year = new Integer(posStrArr[0].substring(0, 4)).intValue();
				int month = new Integer(posStrArr[0].substring(5, 7))
						.intValue();
				int day = new Integer(posStrArr[0].substring(8)).intValue();
				int hour = new Integer(posStrArr[1].substring(0, 2)).intValue();
				int minute = new Integer(posStrArr[1].substring(3, 5))
						.intValue();
				int second = new Integer(posStrArr[1].substring(6)).intValue();
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);// 关闭日期宽容模式
				cal.set(year, month - 1, day, hour, minute, second);
				cal.getTime();

			} catch (Exception e) {
				valid = false;
			}
			return valid;

		} else {
			if (!string.matches("[\\d]{4}/[\\d]{2}/[\\d]{2}")) {
				valid = false;
			}
			try {
				int year = new Integer(string.substring(0, 4)).intValue();
				int month = new Integer(string.substring(5, 7)).intValue();
				int day = new Integer(string.substring(8)).intValue();
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				cal.set(year, month - 1, day);
				cal.getTime();

			} catch (Exception e) {
				valid = false;
			}
			return valid;
		}
	}

	/**
	 * 用于实现select功能
	 * 
	 * @param inputStr
	 * @return
	 * @throws IOException
	 */
	public void select(String inputStr) throws IOException {

		if (!inputStr.contains(" ")) {
			System.err.println("Please select a date");
			return;
		} else {
			String[] posStrArr = inputStr.split(" ");
			if (isValid(posStrArr[1])) {
				System.out.println("[Success]Select " + posStrArr[1]
						+ " successfully!");
				selectDate = getDate(posStrArr[1]);
				selectDateList = schedulePool.get(dateFormatter2
						.format(selectDate));
				return;
			} else {
				System.err.println("[Error]参数必须是日期，格式为yyyy/MM/dd");
				return;
			}
		}

	}

	/**
	 * 用于实现new功能
	 * 
	 * @throws Exception
	 */
	public void newSchedule() throws Exception {
		// readSchedule();
		loadRecordsFromFile();
		if (selectDate == null) {
			System.err.println("[Error]请先选中一个日期!");
			return;
		}
		String dateInFormatWithoutTime = dateFormatter2.format(selectDate);
//		System.out.println("Selected date: " + dateInFormatWithoutTime);
//		System.out.println("新建事务:(本行不需填写)");
		String titleStr = Utils.consolePrintAndReadLine("标题:");
		if ("".equals(titleStr)) {
			titleStr += "null";
		}
		String locationStr = Utils.consolePrintAndReadLine("地点:");
		if ("".equals(locationStr)) {
			locationStr += "null";
		}
		String startTimeStrTemp = null;
		String startTimeStr = null;
		Date startTimeDate = new Date();
		while (true) {
			startTimeStrTemp = Utils.consolePrintAndReadLine("起始时间:");
			String[] posStrArr = startTimeStrTemp.split(" ");
			if (!posStrArr[0].equals(dateInFormatWithoutTime)) {
				System.err.println("[Error]当前所选日期:" + startTimeStrTemp
						+ "与你输入的日期不一致，请输入正确的时间，或重新选择日期");
			} else if (!isValid(startTimeStrTemp)) {
				System.err.println("[Error]参数必须是日期，格式为yyyy/MM/dd HH:mm:ss");
			} else {
				startTimeStr = startTimeStrTemp;
				startTimeDate = getDate(startTimeStr);
				break;
			}

		}
		String endTimeStrTemp = null;
		String endTimeStr = null;
		Date endTimeDate = new Date();
		while (true) {
			endTimeStrTemp = Utils.consolePrintAndReadLine("结束时间:");
			String[] posStrArr = endTimeStrTemp.split(" ");
			if (!posStrArr[0].equals(dateInFormatWithoutTime)) {
				System.err.println("[Error]当前所选日期:" + endTimeStrTemp
						+ "与你输入的日期不一致，请输入正确的时间，或重新选择日期");
			} else if (!isValid(endTimeStrTemp)) {
				System.err.println("[Error]参数必须是日期，格式为yyyy/MM/dd HH:mm:ss");
			} else {
				endTimeStr = endTimeStrTemp;
				endTimeDate = getDate(endTimeStr);
				break;
			}

		}

		String priorityStr = Utils
				.consolePrintAndReadLine("优先级(1 for high,2 for medium,3 for low,0 for uncertain)：");
		int n;
		if (priorityStr.matches("[\\d]")) {
			n = Integer.parseInt(priorityStr);
		} else {
			System.err.println("[Error]格式错误，请重新输入");
			return;
		}
		switch (n) {
		case 1:
			priorityStr = "high";
			break;
		case 2:
			priorityStr = "medium";
			break;
		case 3:
			priorityStr = "low";
			break;
		default:
			priorityStr = "uncertain";
			break;
		}
		boolean allDayBol;

		while (true) {
			String allDayStr = Utils.consolePrintAndReadLine("全天事件？(Y/N)");

			if (allDayStr.equalsIgnoreCase("Y")) {
				allDayBol = true;
				break;
			} else if (allDayStr.equalsIgnoreCase("N")) {
				allDayBol = false;
				break;
			} else {
				System.err.println("[Error]请重新输入");

			}
		}
		String birthdayAlarmStr = Utils.consolePrintAndReadLine("生日提醒？(Y/N)");
		boolean birthdayAlarmBol;
		while (true) {
			if (birthdayAlarmStr.equalsIgnoreCase("Y")) {
				birthdayAlarmBol = true;
				break;
			} else if (birthdayAlarmStr.equalsIgnoreCase("N")) {
				birthdayAlarmBol = false;
				break;
			} else {
				System.err.println("[Error]请重新输入");
			}
		}
		boolean isDone=false;
		System.out.println("[Success]事务创建成功!");
		Schedule s = new Schedule(titleStr, startTimeDate, endTimeDate,
				locationStr, priorityStr, allDayBol, birthdayAlarmBol,isDone);
		// scheduleList.add(s);
		CalendarManager.addNewSchedule(s);
		CalendarManager.saveRecordsToFile();
	}

	/**
	 * 用于实现edit功能
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public void editSchedule(String inputStr) throws Exception {
		loadRecordsFromFile();
		if (selectDate == null) {
			System.err.println("[Error]请先选中一个日期!");
			return;
		}
		String[] posStrArr = inputStr.split(" ");
		if (!posStrArr[1].matches("[\\d]")) {
			System.err.println("[Error]参数不合法");
			return;
		}
		Schedule editSchedule = getScheduleByDateAndIndex(selectDate, Integer
				.parseInt(posStrArr[1]));

		String titleStr = Utils.consolePrintAndReadLine("请输入新标题（回车跳过）：");
		if (titleStr.equals(""))
			titleStr = editSchedule.getTitle();
		String locationStr = Utils.consolePrintAndReadLine("请输入新地点（回车跳过）：");
		if (locationStr.equals(""))
			locationStr = editSchedule.getLocation();

		String timeStrBef = null;
		Date timeDate = null;
		String timeStrAft = null;
		int j = 0;
		do {
			timeStrBef = Utils
					.consolePrintAndReadLine(j == 0 ? "请输入新时间(如2010/01/01 12:00:00,回车跳过)："
							: "[Error]日期格式错误或与原定日期不符，请重新输入:");
			try {
				if (timeStrBef.equals("")) {// 如果输入日期为空，则直接调用editSchedule的getTime方法
					timeDate = editSchedule.getStartTime();
					timeStrAft = dateFormatter1.format(timeDate);
				} else {
					if (isValid(timeStrBef)) {
						timeDate = getDate(timeStrBef);
					}
					timeStrAft = timeStrBef;
				}
			} catch (NumberFormatException ee) {
				System.err.println("[Error]日期格式错误");
				return;
			}
			j++;
		} while ((!isValid(timeStrBef) && !timeStrBef.equals(""))
				|| !timeStrAft.contains(dateFormatter2.format(editSchedule
						.getStartTime())) || timeDate == null);

		String priorityStr = Utils
				.consolePrintAndReadLine("请输入新优先级(1 for high,2 for medium,3 for low,0 for uncertain,回车跳过)：");
		int n = 0;
		if (priorityStr.equals(""))
			priorityStr = editSchedule.getPriority();
		else if (priorityStr.matches("[\\d]")) {
			n = Integer.parseInt(priorityStr);
			editSchedule.setPriority(n);
		} else {
			System.err.println("[Error]格式错误，请重新输入");
			return;
		}
		boolean allDayBol;
		while (true) {
			String allDayStr = Utils.consolePrintAndReadLine("全天事件？(Y/N,回车跳过)");

			if (allDayStr.equalsIgnoreCase("Y")) {
				allDayBol = true;
				break;
			} else if (allDayStr.equalsIgnoreCase("N")) {
				allDayBol = false;
				break;
			} else if (allDayStr.equalsIgnoreCase("")) {
				allDayBol = editSchedule.getAllDay();
				break;
			} else {
				System.err.println("[Error]请重新输入");
			}
		}
		String birthdayAlarmStr = Utils.consolePrintAndReadLine("生日提醒？(Y/N)");
		boolean birthdayAlarmBol = false;
		while (true) {
			if (birthdayAlarmStr.equalsIgnoreCase("Y")) {
				birthdayAlarmBol = true;
				break;
			} else if (birthdayAlarmStr.equalsIgnoreCase("N")) {
				birthdayAlarmBol = false;
				break;
			} else if (birthdayAlarmStr.equalsIgnoreCase("")) {
				allDayBol = editSchedule.getAllDay();
				break;
			} else {
				System.err.println("[Error]请重新输入");
			}
		}
		editSchedule.setTitle(titleStr);
		editSchedule.setLocation(locationStr);
		editSchedule.setStartTime(timeDate);
		editSchedule.setAllDay(allDayBol);
		editSchedule.setBirthdayAlarm(birthdayAlarmBol);
		// writeSchedule("schedule.data");
		saveRecordsToFile();
		System.out.println("[Success]修改事务成功!");
	}

	/**
	 * 用于实现list功能（带参数）
	 * 
	 * @param inputStr
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void list(String inputStr) throws IOException,
			ClassNotFoundException, InterruptedException {
		loadRecordsFromFile();
		ArrayList<Schedule> ScheduleInList = new ArrayList<Schedule>();
		isListed = true;
		String[] posStrArr = inputStr.split(" ");
		if (!isValid(posStrArr[1])) {
			System.err.println("[Error]日期格式错误");
			return;
		} else {
			try {
				// readSchedule();
				loadRecordsFromFile();
				ScheduleInList = schedulePool.get(dateFormatter2
						.format(getDate(posStrArr[1])));

				if (ScheduleInList == null) {
					System.err.println("[Message]当前日期无记录");
					return;
				}
				System.out.println(titleString);
				for (int i = 0; i < ScheduleInList.size(); i++) {
					System.out.println(i + "\t" + ScheduleInList.get(i));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
/**
 * 用于返回构造日程表格所需的二维数组。
 * @param inputStr
 * @return
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
	public static String[][] querySchedule(String inputStr) throws IOException,
			ClassNotFoundException, InterruptedException {
		loadRecordsFromFile();
		ArrayList<Schedule> ScheduleInList = new ArrayList<Schedule>();
		isListed = true;
		String[] posStrArr = inputStr.split(" ");
		if (!isValid(posStrArr[1])) {
			JOptionPane.showMessageDialog(null,"[Error]日期格式错误");
			return null;
		} else {
			loadRecordsFromFile();
			ScheduleInList = schedulePool.get(dateFormatter2
					.format(getDate(posStrArr[1])));
			if (ScheduleInList == null) {
				JOptionPane.showMessageDialog(null, "[Message]当前日期无记录");
				return null;
			}
			String tableDataStr[][] = new String[ScheduleInList.size()][7];

			 System.out.println(ScheduleInList.size());
			for (int i = 0; i < ScheduleInList.size(); i++) {
				tableDataStr[i] = ScheduleInList.get(i).printindetailTable();

			}
			return tableDataStr;

		}
	}

	/**
	 * 用于实现list功能（不带参数）
	 * 
	 * @param selectDate2
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void list(Date selectDate2) throws IOException,
			ClassNotFoundException, InterruptedException {
		loadRecordsFromFile();

		if (selectDate == null) {
			System.err.println("[Error]请先选中一个日期!");
			return;
		}
		selectDateList = schedulePool.get(dateFormatter2.format(selectDate));
		if (selectDateList == null) {
			System.out.println("[Message]当前日期没有记录");
			return;
		}
		System.out.println(titleString);
		for (int i = 0; i < selectDateList.size(); i++) {
			System.out.println(i + "\t" + selectDateList.get(i));
		}
	}

	/**
	 * 用于实现delete功能
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public void deleteSchedule(String inputStr) throws Exception {

		if (selectDate == null) {
			System.err.println("[Error]请先选中一个日期!");
			return;
		}

		String[] posStrArr = inputStr.split(" ");
		if (!posStrArr[1].matches("[\\d]")) {
			System.err.println("[Error]参数不合法");
			return;
		}
		deleteScheduleByDateAndIndex(selectDate, Integer.parseInt(posStrArr[1]));
		System.out.println("[Success]序号" + posStrArr[1] + "\t事务已删除成功!");
		saveRecordsToFile();
	}

	/**
	 * 用于查询重要事件
	 * 
	 * @throws IOException
	 */
	public void queryPriorSchedule() throws IOException {

		// readSchedule();
		try {
			loadRecordsFromFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Schedule> schedulePrior = new ArrayList<Schedule>();
		Map<String, ArrayList<Schedule>> mapSchedule = schedulePool;
		Collection<ArrayList<Schedule>> values = mapSchedule.values();
		Iterator<ArrayList<Schedule>> iter = values.iterator();
		while (iter.hasNext()) {
			scheduleList = iter.next();
			for (int i = 0; i < scheduleList.size(); i++) {
				if (scheduleList.get(i).getPriority().equals("high")) {
					schedulePrior.add(scheduleList.get(i));
				}
			}
		}
		System.out.println(titleString);
		Object[] o = schedulePrior.toArray();
		Arrays.sort(o);
		int occurrence = 0;
		for (int i = 0; i < o.length; i++) {
			if (((Schedule) o[i]).getPriority().equals("high")) {
				System.out.println(i + "\t" + (o[i]));
				occurrence++;
			}
		}
		if (occurrence == 0) {
			System.out.println("[Message]无符合查询要求的日程");
		}

	}

	/**
	 * 用于实现重复事件（按周重复）
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public void repeatByWeek(String inputStr) throws Exception {
		loadRecordsFromFile();
		if (selectDate == null && isListed) {
			System.err.println("[Error]请先选中一个日期!");
			return;
		}
		String[] posStrArr = inputStr.split(" ");
		if (!posStrArr[1].matches("[\\d]")) {
			System.err.println("[Error]参数不合法");
			return;
		}
		Schedule editSchedule = getScheduleByDateAndIndex(selectDate, Integer
				.parseInt(posStrArr[1]));

		if (editSchedule == null) {
			System.err.println("[Error]所选日期不存在");
			return;
		}
		System.out.println("Select date:" + dateFormatter2.format(selectDate));
		Calendar c = Calendar.getInstance();
		c.setTime(selectDate);
		String numberStr = Utils.consolePrintAndReadLine("请输入重复次数:");
		int numberOfRepeat;
		try {
			numberOfRepeat = Integer.parseInt(numberStr);
			if (numberOfRepeat <= 0) {
				System.err.println("[Error]参数不合法");
				return;
			}
		} catch (Exception e) {
			numberOfRepeat = 10;
		}
		System.out.println(titleString);
		for (int i = 0; i < numberOfRepeat; i++) {
			String titleStr = editSchedule.getTitle();

			String locationStr = editSchedule.getLocation();

			c.add(Calendar.DAY_OF_MONTH, 7);
			String[] posDatArr1 = dateFormatter1.format(
					editSchedule.getStartTime()).split(" ");
			String[] posDatArr2 = dateFormatter1.format(
					editSchedule.getEndTime()).split(" ");
			String DateStr1 = dateFormatter2.format(c.getTime()) + " "
					+ posDatArr1[1];
			String DateStr2 = dateFormatter2.format(c.getTime()) + " "
					+ posDatArr2[1];
			Date startTimeDate = getDate(DateStr1);
			Date endTimeDate = getDate(DateStr2);
			String priorityStr = editSchedule.getPriority();
			boolean allDayBol = editSchedule.getAllDay();
			boolean birthdayAlarmbol = editSchedule.getBirthdayAlarm();
			boolean isDone=editSchedule.isDone();
			Schedule s = new Schedule(titleStr, startTimeDate, endTimeDate,
					locationStr, priorityStr, allDayBol, birthdayAlarmbol,isDone);

			addNewSchedule(s);
			System.out.println(i + "\t" + s);

		}
		System.out.println("[Success]已被重复" + numberOfRepeat + "次");
		saveRecordsToFile();
		isListed = false;

	}

	/**
	 * 用于实现重复事件（按月重复）
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public void repeatByMonth(String inputStr) throws Exception {
		loadRecordsFromFile();
		if (selectDate == null && isListed) {
			System.err.println("[Success]请先选中一个日期!");
			return;
		}
		String[] posStrArr = inputStr.split(" ");
		Schedule editSchedule = getScheduleByDateAndIndex(selectDate, Integer
				.parseInt(posStrArr[1]));

		if (editSchedule == null) {
			System.err.println("[Error]所选事务不存在!");
			return;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(selectDate);
		System.out.println("Select date:" + dateFormatter3.format(selectDate));
		String numberStr = Utils.consolePrintAndReadLine("请输入重复次数:");
		int numberOfRepeat;
		try {

			numberOfRepeat = Integer.parseInt(numberStr);
		} catch (Exception e) {
			numberOfRepeat = 10;
		}
		System.out.println(titleString);
		for (int i = 0; i < numberOfRepeat; i++) {
			String titleStr = editSchedule.getTitle();
			String locationStr = editSchedule.getLocation();
			c.add(Calendar.MONTH, 1);
			String[] posDatArr1 = dateFormatter1.format(
					editSchedule.getStartTime()).split(" ");
			String[] posDatArr2 = dateFormatter1.format(
					editSchedule.getEndTime()).split(" ");
			String DateStr1 = dateFormatter2.format(c.getTime()) + " "
					+ posDatArr1[1];
			String DateStr2 = dateFormatter2.format(c.getTime()) + " "
					+ posDatArr2[1];
			Date startTimeDate = getDate(DateStr1);
			Date endTimeDate = getDate(DateStr2);

			String priorityStr = editSchedule.getPriority();
			boolean allDayBol = editSchedule.getAllDay();
			boolean birthdayAlarmbol = editSchedule.getBirthdayAlarm();
			boolean isDone = editSchedule.isDone();
			Schedule s = new Schedule(titleStr, startTimeDate, endTimeDate,
					locationStr, priorityStr, allDayBol, birthdayAlarmbol,isDone);

			addNewSchedule(s);
			System.out.println(i + "\t" + s);

		}
		System.out.println("[Success]已被重复" + numberOfRepeat + "次");
		saveRecordsToFile();
		isListed = false;

	}

}