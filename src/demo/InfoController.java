/**
 * 用于判断命令和控制流程
 */
package demo;

import data.CalendarManager;
import data.ContactManager;
import data.Media;
import util.Utils;
/**
 * PJ1中用于判断命令行，PJ2中已废弃
 * @author Administrator
 *
 */
public class InfoController {
	CalendarManager cal = new CalendarManager();
	ContactManager cont = new ContactManager();

	@SuppressWarnings("static-access")
	public void Command() throws Exception {
		while (true) {
			String myName = Utils.getWindowsCurrentUser();
			String inputStr = Utils.consolePrintAndReadLine(myName
					+ "@ MyCalendar:$");
			if (inputStr.equals("quit") || inputStr.equals("exit")) {
				System.out.println("[Message]Good Bye! CopyRight(c) Alex Ltd.2010-2012 All Rights Reserved");
				System.exit(0);
			}else if(inputStr.equals("play")){
				Media.autoRun();}
			else if(inputStr.equals("stop")){
					Media.autostop();
			} else if (inputStr.contains("previous")
					|| inputStr.contains("next")) {
				cal.month(inputStr);
			} else if (inputStr.contains("select")
					&& !(inputStr.contains("select contact"))) {

				cal.select(inputStr);
			} else if (inputStr.equals("list")) {

				cal.list(CalendarManager.selectDate);
			} else if (inputStr.contains("list") && inputStr.contains("/")) {

				cal.list(inputStr);
			} else if (inputStr.equals("new")) {
				cal.newSchedule();
			} else if (inputStr.contains("repeatbyweek")) {
				cal.repeatByWeek(inputStr);
			} else if (inputStr.contains("repeatbymonth")) {
				cal.repeatByMonth(inputStr);

			} else if (inputStr.equals("query prior schedule")) {
				cal.queryPriorSchedule();
			} else if (inputStr.contains("delete")
					&& (!inputStr.contains("delete contact"))) {
				cal.deleteSchedule(inputStr);
			} else if (inputStr.contains("edit ")
					&& (!inputStr.contains("edit contact"))) {
				cal.editSchedule(inputStr);
			} else if (inputStr.equals("add contact")) {
				cont.addContact();
			} else if (inputStr.equals("query contact all")) {
				cont.queryContactAll();
			} else if (inputStr.equals("query contact all in detail")) {
				cont.queryContactAllInDetail();
			} else if (inputStr.contains("delete contact")) {
				cont.deleteContact(inputStr);
			} else if (inputStr.contains("edit contact")) {
				cont.editContact(inputStr);

			} else if (inputStr.equals("query contact all desc")) {
				cont.queryContactAllDesc();
			} else if (inputStr.contains("select contact")) {
				cont.selectContact(inputStr);
			} else if (inputStr.contains("new item")) {
				cont.newitem();
			} else if (inputStr.contains("query contact name contains")) {
				cont.queryContactName(inputStr);
			} else if (inputStr.contains("query contact email contains")) {
				cont.queryContactEmail(inputStr);
			} else if (inputStr.contains("query contact phone contains")) {
				cont.queryContactPhone(inputStr);
			} else if (inputStr.contains("query contact website contains")) {
				cont.queryContactWebsite(inputStr);
			} else if (inputStr.contains("query contact address contains")) {
				cont.queryContactAddress(inputStr);
			} else if (inputStr.contains("query contact birthday contains")) {
				cont.queryContactBirthday(inputStr);
			} else {
				System.out.println("[Message]" + inputStr + "不是本程序内部指令");
			}
		}
	}
}