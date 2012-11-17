package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import util.Utils;
/**
 * Contact��ĸ��ֵײ����
 * @author Administrator
 *
 */
public class ContactManager {
		
	public static ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
	public static ArrayList<Contact> contactList = new ArrayList<Contact>();
	public static HashMap<String, ArrayList<Schedule>> schedulePool = new HashMap<String, ArrayList<Schedule>>();
	public static HashMap<String, Contact> contactPool = new HashMap<String, Contact>();
	public static SimpleDateFormat dateFormatter1 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
			"yyyy/MM/dd");
	public static SimpleDateFormat dateFormatter3 = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss EE");
	HashMap<String, String> listSchedule = new HashMap<String, String>();
	static boolean isListed;
	public static Date selectDate = null;
	public static String titleString = "���\t����\t\tʱ��\t\t�ص�\t���ȼ�";
	public static ArrayList<Schedule> selectDateList;
	String selectName = null;
	public String[] contactNameList=null;
	
	/**
	 * ���������ϵ��
	 * 
	 * @throws Exception
	 */
	public void addContact() throws Exception {
		System.out.println("add contact");
		readObj();
		String nameStr;
		int i = 0;
		do {
			nameStr = Utils.consolePrintAndReadLine(i == 0 ? "������������"
					: "[Error]��������Ϊ�գ�����������:");
			boolean isRepete = false;// �������ظ������������*��ʾ����
			do {
				int h = 0;
				for (int j = 0; j < contactList.size(); j++) {

					if (nameStr.equals(contactList.get(j).getName())) {
						isRepete = true;
						nameStr += "*";
						h++;
					} else {
						isRepete = (h == 0 ? false : isRepete);
					}
					h = 0;
				}
			} while (isRepete);

			i++;
		} while (nameStr.equals(""));
		String genderStr = Utils.consolePrintAndReadLine("�������Ա�(F-Ůʿ,M������)");
		Boolean genderBol;
		if (genderStr.equalsIgnoreCase("M")) {
			genderBol = true;
		} else {
			genderBol = false;
		}
		String emailStr = Utils.consolePrintAndReadLine("���������䣺");
		if (emailStr.equals("")) {
			emailStr = "null";
		}
		String phoneStr = Utils.consolePrintAndReadLine("������绰��");
		if (phoneStr.equals("")) {
			phoneStr = "null";
		}
		String birthdayStr = null;
		Date birthdayDate = null;
		int j = 0;
		do {

			birthdayStr = Utils
					.consolePrintAndReadLine(j == 0 ? "����������(��2010/01/01)��"
							: "[Error]���ڸ�ʽ��������������:");
			try {
				birthdayDate = CalendarManager.getDate(birthdayStr);
			} catch (NumberFormatException ee) {
			}
			j++;
		} while (!CalendarManager.isValid(birthdayStr));
		String addressStr = Utils.consolePrintAndReadLine("�������ַ��");
		if (addressStr.equals("")) {
			addressStr = "null";
		}
		String websiteStr = Utils.consolePrintAndReadLine("��������ַ��");
		if (websiteStr.equals("")) {
			websiteStr = "null";
		}
		Contact contact = new Contact(nameStr, genderBol, emailStr, phoneStr,
				birthdayDate, addressStr, websiteStr);
		System.out.println("[Success]��ϵ�˴����ɹ�");
		contactList.add(contact);
		contactPool.put(contact.getName(), contact);
		writeObj("contact.data");

	}

	/**
	 * �������ļ���д����ϵ������
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public static void writeObj(String filename) throws Exception {
		BufferedWriter writeStream = new BufferedWriter(
				new FileWriter(filename));
		for (int i = 0; i < contactList.size(); i++) {
			writeStream.write(contactList.get(i).getName());
			writeStream.write("|");
			writeStream.write(contactList.get(i).getGender());
			writeStream.write("|");
			writeStream.write(contactList.get(i).getEmail());
			writeStream.write("|");
			writeStream.write(contactList.get(i).getPhone());
			writeStream.write("|");
			writeStream.write(dateFormatter2.format(contactList.get(i)
					.getBirthday()));
			writeStream.write("|");
			writeStream.write(contactList.get(i).getAddress());
			writeStream.write("|");
			writeStream.write(contactList.get(i).getWebsite());
			writeStream.newLine();
		}
		writeStream.close();

	}

	/**
	 * ���ڶ�ȡ��ϵ������
	 * 
	 * @throws IOException
	 */
	public static void readObj() throws IOException {
		File recorder = new File("contact.data");

		if (!recorder.exists()) {
			boolean exists = recorder.createNewFile();
			if (!exists) {
				System.out
						.println("The file contact.data has already existed before created\n"
								+ "or the step of cteating Schedule.data has failed.\n"
								+ "Enter any key and MyCalendar will exit.");
				Scanner scanner = new Scanner(System.in);
				scanner.next();
				System.exit(0);
			}
		}
		contactList.clear();
		contactPool.clear();
		String in;
		BufferedReader readStream = new BufferedReader(new FileReader(
				"contact.data"));
		while ((in = readStream.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(in, "|");
			String name = st.nextToken();
			String gender = st.nextToken();
			String email = st.nextToken();
			String phone = st.nextToken();
			String birthday = st.nextToken();
			String address = st.nextToken();
			String website = st.nextToken();
			Date birthdayDate = CalendarManager.getDate(((birthday)));
			Boolean genderBol = gender.equals("����") ? true : false;
			Contact contact = new Contact(name, genderBol, email, phone,
					birthdayDate, address, website);
			contactList.add(contact);
			contactPool.put(contact.getName(), contact);
		}

	}

	/**
	 * ������ʾ������ϵ�ˣ�������˳������
	 * 
	 * @throws IOException
	 */
	public static void queryContactAll() throws IOException {
		contactList.clear();

		System.out.println("contact:");
		readObj();
		Object[] o = contactList.toArray();
		Arrays.sort(o);
		int occurrence = 0;
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
			occurrence++;
		}
		if (occurrence == 0) {
			System.out.println("[Message]û����ϵ�˼�¼");
		}
	}

	/**
	 * ����ɾ����ϵ��
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public static void deleteContact(String inputStr) throws Exception {

		String[] posStrArr = inputStr.split(" ");
		int occurrence = 0;
		for (int i = 0; i < contactList.size(); i++) {
			if (posStrArr[2].equals(contactList.get(i).getName())) {
				occurrence++;
			}
		}
		if (occurrence == 0) {
			System.err.println("[Error]��ѡ��ϵ�˲�����");
			return;
		}
		File f = new File("contact.data");
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("");
		contactPool.remove(posStrArr[2]);
		contactList.clear();
		for (Object obj : contactPool.keySet()) {
			contactList.add(contactPool.get(obj));
		}
		System.out.println("[Success]" + posStrArr[2] + "ɾ���ɹ�!");

		writeObj("contact.data");
		readObj();
		queryContactAll();

	}

	/**
	 * ���ڱ༭��ϵ��
	 * 
	 * @param inputStr
	 * @throws Exception
	 */
	public void editContact(String inputStr) throws Exception {
		String[] posStrArr = inputStr.split(" ");
		int occurrence = 0;
		for (int i = 0; i < contactList.size(); i++) {
			if (posStrArr[2].equals(contactList.get(i).getName())) {
				occurrence++;
			}
		}
		if (occurrence == 0) {
			System.err.println("[Error]��ѡ��ϵ�˲�����");
			return;
		}
		contactList.clear();
		Contact editContact = contactPool.get(posStrArr[2]);
		String nameStr = null;
		nameStr = Utils.consolePrintAndReadLine("���������������س�������:");
		if (nameStr.equals(""))
			nameStr = editContact.getName();
		String emailStr = Utils.consolePrintAndReadLine("�����������䣨�س�������:");
		if (emailStr.equals(""))
			emailStr = editContact.getEmail();
		String phoneStr = Utils.consolePrintAndReadLine("�������µ绰���س�������:");
		if (phoneStr.equals(""))
			phoneStr = editContact.getPhone();
		String birthdayStrBef = null;
		String birthdayStrAft = null;
		Date birthdayDate = null;
		int j = 0;
		do {
			birthdayStrBef = Utils
					.consolePrintAndReadLine(j == 0 ? "����������(��2010/01/01,�س�����)��"
							: "[Error]���ڸ�ʽ��������������:");
			try {
				if (birthdayStrBef.equals("")) {
					birthdayDate = editContact.getBirthday();
					birthdayStrAft = dateFormatter2.format(editContact
							.getBirthday());
				} else {
					if (CalendarManager.isValid(birthdayStrBef)) {
						birthdayDate = CalendarManager.getDate(birthdayStrBef);
					}
					birthdayStrAft = birthdayStrBef;
				}
			} catch (NumberFormatException ee) {
				System.err.println("[Error]���ڸ�ʽ����");
				return;
			}
			j++;
		} while (!CalendarManager.isValid(birthdayStrAft)
				&& !birthdayStrBef.equals("") || birthdayDate == null);

		String addressStr = Utils.consolePrintAndReadLine("�������µ�ַ���س�������:");
		if (addressStr.equals(""))
			addressStr = editContact.getAddress();
		String websiteStr = Utils.consolePrintAndReadLine("����������ַ���س�������:");
		if (websiteStr.equals(""))
			websiteStr = editContact.getWebsite();
		editContact.setName(nameStr);
		editContact.setEmail(emailStr);
		editContact.setPhone(phoneStr);
		editContact.setBirthday(birthdayDate);
		editContact.setAddress(addressStr);
		editContact.setWebsite(websiteStr);
		contactPool.remove(posStrArr[2]);
		contactPool.put(editContact.getName(), editContact);
		for (Object obj : contactPool.keySet()) {
			contactList.add(contactPool.get(obj));
		}
		System.out.println("[Success]" + nameStr + "�޸ĳɹ�");
		File f = new File("contact.data");
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("");
		writeObj("contact.data");
		queryContactAll();

	}

	/**
	 * ������ʾ������ϵ�ˣ���������������
	 * 
	 * @throws IOException
	 */
	public void queryContactAllDesc() throws IOException {
		contactList.clear();
		System.out.println("contact:");
		readObj();
		Object[] o = contactList.toArray();
		Arrays.sort(o);
		int occurrence = 0;
		for (int i = o.length - 1; i >= 0; i--) {
			System.out.println(o[i]);
			occurrence++;
		}
		if (occurrence == 0) {
			System.out.println("[Message]û����ϵ�˼�¼");
		}
	}

	/**
	 * ������ʾ������ϵ�˵���ϸ����
	 * 
	 * @throws IOException
	 */
	public void queryContactAllInDetail() throws IOException {
		contactList.clear();

		System.out.println("contact:");
		readObj();
		Object[] o = contactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			((Contact) (o[i])).printindetail();

		}
	}
	
	public String[] getContactAllNameList() throws IOException {
		contactList.clear();

		System.out.println("contact:");
		readObj();
		Object[] o = contactList.toArray();
		contactNameList=new String[o.length];
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			contactNameList[i]=((Contact) (o[i])).getName();
//System.out.println(contactNameList[i]);
		}
		return contactNameList;
	}
/**
 * �������������ɹ�����ϵ�˱������Ķ�ά���飬�����򡢲�ѯ�ж���Ҫ�õ����Ƶķ�����
 * @param order
 * @return
 * @throws IOException
 */
	public static String[][] sortContactAllInDetailTableByName(boolean order)
			throws IOException {

		// contactList.clear();

		System.out.println("contact:");
		readObj();
		Object[] o = contactList.toArray();
		String tableDataStr[][] = new String[o.length][7];
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			if (order) {
				tableDataStr[i] = ((Contact) (o[i])).printindetailTable();
			} else {
				tableDataStr[o.length - i - 1] = ((Contact) (o[i]))
						.printindetailTable();
			}

		}
		return tableDataStr;
	}

	/**
	 * ����ѡ����ϵ��
	 * 
	 * @param inputStr
	 * @throws IOException
	 */
	public void selectContact(String inputStr) throws IOException {
		readObj();
		String[] posStrArr = inputStr.split(" ");
		int occurrence = 0;
		for (int i = 0; i < contactList.size(); i++) {
			if (posStrArr[2].equals(contactList.get(i).getName())) {
				occurrence++;
			}
		}
		if (occurrence == 0) {
			System.err.println("[Error]��ѡ��ϵ�˲�����");
			return;
		}
		selectName = posStrArr[2];
		Contact selectContact = contactPool.get(selectName);
		selectContact.printindetail();
		System.out.println("[Success]select " + selectName + " successfully!");

	}

	/**
	 * ������ѡ����ϵ�˴������¼�
	 * 
	 * @throws Exception
	 */
	public void newitem() throws Exception {
		if (selectName == null) {
			System.err.println("[Error]����ѡ��һ����ϵ��");
			return;
		}
		System.out.println("[Success]Contact" + selectName + "selected!");
		System.out.println(">new item");
		System.out.println("����:With " + selectName);
		String titleStr = "With " + selectName;
		String startTimeStr;
		int occurrence1 = 0;
		do {
			startTimeStr = Utils
					.consolePrintAndReadLine((occurrence1 == 0) ? "��ʼʱ��:"
							: "[Error]��ʽ������������:");
			occurrence1++;
		} while (!CalendarManager.isValid(startTimeStr));
		Date startTimeDate = CalendarManager.getDate((startTimeStr));
		String endTimeStr;
		int occurrence2 = 0;
		do {
			endTimeStr = Utils
					.consolePrintAndReadLine((occurrence2 == 0) ? "����ʱ��:"
							: "[Error]��ʽ������������:");
			occurrence2++;
		} while (!CalendarManager.isValid(endTimeStr));
		Date endTimeDate = CalendarManager.getDate((endTimeStr));
		String locationStr = Utils.consolePrintAndReadLine("�ص�:");

		String priorityStr = Utils
				.consolePrintAndReadLine("���ȼ�(1 for high,2 for medium,3 for low,0 for uncertain)��");
		int n;
		if (priorityStr.matches("[\\d]")) {
			n = Integer.parseInt(priorityStr);
		} else {
			System.err.println("[Success]��ʽ��������������");
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
		String allDayStr = Utils.consolePrintAndReadLine("ȫ���¼���(Y/N)");
		boolean allDayBol;
		while (true) {
			if (allDayStr.equalsIgnoreCase("Y")) {
				allDayBol = true;
				break;
			} else if (allDayStr.equalsIgnoreCase("N")) {
				allDayBol = false;
				break;
			} else {
				System.err.println("[Error]����������");
			}
		}
		String birthdayAlarmStr = Utils.consolePrintAndReadLine("�������ѣ�(Y/N)");
		boolean birthdayAlarmBol;
		while (true) {
			if (birthdayAlarmStr.equalsIgnoreCase("Y")) {
				birthdayAlarmBol = true;
				break;
			} else if (birthdayAlarmStr.equalsIgnoreCase("N")) {
				birthdayAlarmBol = false;
				break;
			} else {
				System.err.println("[Error]����������");
			}
		}
		System.out.println("[Success]���񴴽��ɹ�!");
		boolean isDone  = false;
		Schedule s = new Schedule(titleStr, startTimeDate, endTimeDate,
				locationStr, priorityStr, allDayBol, birthdayAlarmBol,isDone);
		CalendarManager.addNewSchedule(s);
		CalendarManager.saveRecordsToFile();

	}

	/**
	 * ���ڰ�������ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactName(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String nameKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getName().contains(nameKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}
	public String queryContactNameToGetEmail(String nameKey) {
		
		//int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getName().equals(nameKey)) {
				queryContactList.add(contactList.get(i));
				break;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		String emailStr = ((Contact) o[0]).getEmail();
		return emailStr;
	}
/**
 * ���ڰ�������ѯ��ϵ�ˣ����ر������Ķ�ά����
 * @param nameKey
 * @return
 * @throws IOException
 */
	public static String[][] queryContactAllInDetailTableByName(
			String nameKey) throws IOException {

		readObj();
		String[][] tableDataStr;
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getName().contains(nameKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		if (o.length == 0) {
			return null;
		} else {
			Arrays.sort(o);
			tableDataStr = new String[o.length][7];
			for (int i = 0; i < o.length; i++) {
				tableDataStr[i] = ((Contact) o[i]).printindetailTable();
				System.out.println(tableDataStr[i][0]);
			}
			return tableDataStr;
		}
	}

	/**
	 * ���ڰ��绰��ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactPhone(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String phoneKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getPhone().contains(phoneKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}
	public static String[][] queryContactAllInDetailTableByPhone(
			String phoneKey) throws IOException {

		readObj();
		String[][] tableDataStr;
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getPhone().contains(phoneKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		if (o.length == 0) {
			return null;
		} else {
			Arrays.sort(o);
			tableDataStr = new String[o.length][7];
			for (int i = 0; i < o.length; i++) {
				tableDataStr[i] = ((Contact) o[i]).printindetailTable();
				System.out.println(tableDataStr[i][3]);
			}
			return tableDataStr;
		}
	}
	/**
	 * ���ڰ����ղ�ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactBirthday(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String birthdayKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (dateFormatter2.format(contactList.get(i).getBirthday())
					.contains(birthdayKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}

	public static String[][] sortContactAllInDetailTableByBirthDay(boolean order)
			throws IOException {

		// contactList.clear();

		System.out.println("contact:");
		readObj();
		// int occurrence = 0;
		Object[] contactListByBirthday = contactList.toArray();
		int length = contactListByBirthday.length;
		HashMap<String, Contact> arrange1 = new HashMap<String, Contact>();
		for (int k = 0; k < length; k++) {
			String birthdayStr = dateFormatter2
					.format(((Contact) contactListByBirthday[k]).getBirthday());
			arrange1.put(birthdayStr, (Contact) contactListByBirthday[k]);
			// arrange2[k] = date + " " + k;
		}

		String[] birthdayOfContact = new String[length];
		for (int i = 0; i < length; i++) {
			birthdayOfContact[i] = dateFormatter2
					.format(((Contact) contactListByBirthday[i]).getBirthday());
		}
		birthdayOfContact = sortByBirthDay(birthdayOfContact);
		contactList.clear();
		for (int i = 0; i < contactListByBirthday.length; i++) {
			contactList.add(arrange1.get(birthdayOfContact[i]));
		}
		String[][] tableDataStr = new String[length][7];

		for (int i = 0; i < length; i++) {
			if (order) {
				tableDataStr[length - i - 1] = ((Contact) (contactList
						.toArray())[i]).printindetailTable();
			} else {
				tableDataStr[i] = ((Contact) (contactList.toArray())[i])
						.printindetailTable();
			}
		}

		for (int i = 0; i < length; i++) {
			System.out.println(tableDataStr[i][4]);
		}
		return tableDataStr;
	}

	public static String[] sortByBirthDay(String[] birthday) {
		for (int i = birthday.length - 2; i >= 0; i--) {
			String currentBirthday = (birthday[i]);
			int k;
			for (k = i + 1; k <= birthday.length - 1
					&& compareBirthday(birthday[k], currentBirthday); k++) {
				birthday[k - 1] = birthday[k];
			}
			birthday[k - 1] = currentBirthday;
		}
		return birthday;
	}

	public static boolean compareBirthday(String birthday1, String birthday2) {
		String[] date1 = birthday1.split("/");
		String[] date2 = birthday2.split("/");
		if (Integer.parseInt(date1[0]) > Integer.parseInt(date2[0])) {
			return true;
		} else if (Integer.parseInt(date1[0]) < Integer.parseInt(date2[0])) {
			return false;
		} else {
			if (Integer.parseInt(date1[1]) > Integer.parseInt(date2[1])) {
				return true;
			} else if (Integer.parseInt(date1[1]) < Integer.parseInt(date2[1])) {
				return false;
			} else {

				if (Integer.parseInt(date1[2]) >= Integer.parseInt(date2[2])) {
					return true;
				} else {
					return false;
				}
			}

		}
	}

	/**
	 * ���ڰ������ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactEmail(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String emailKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getEmail().contains(emailKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}
	public static String[][] queryContactAllInDetailTableByEmail(
			String emailKey) throws IOException {

		readObj();
		String[][] tableDataStr;
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getEmail().contains(emailKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		if (o.length == 0) {
			return null;
		} else {
			Arrays.sort(o);
			tableDataStr = new String[o.length][7];
			for (int i = 0; i < o.length; i++) {
				tableDataStr[i] = ((Contact) o[i]).printindetailTable();
				System.out.println(tableDataStr[i][2]);
			}
			return tableDataStr;
		}
	}

	public static String[][] sortContactAllInDetailTableByEmail(boolean order)
			throws IOException {
		readObj();
		// int occurrence = 0;
		Object[] contactListByEmail = contactList.toArray();
		int length = contactListByEmail.length;
		IdentityHashMap<String, Contact> arrange1 = new IdentityHashMap<String, Contact>();
		for (int k = 0; k < length; k++) {
			String emailStr = ((Contact) contactListByEmail[k]).getEmail();
			arrange1.put(emailStr, (Contact) contactListByEmail[k]);
			// arrange2[k] = date + " " + k;
		}

		String[] emailOfContact = new String[length];
		for (int i = 0; i < length; i++) {
			emailOfContact[i] = ((Contact) contactListByEmail[i]).getEmail();
		}
		emailOfContact = sortByEmail(emailOfContact);
		contactList.clear();
		for (int i = 0; i < contactListByEmail.length; i++) {
			contactList.add(arrange1.get(emailOfContact[i]));
		}
		String[][] tableDataStr = new String[length][7];

		for (int i = 0; i < length; i++) {
			if (order) {
				tableDataStr[length - i - 1] = ((Contact) (contactList
						.toArray())[i]).printindetailTable();
			} else {
				tableDataStr[i] = ((Contact) (contactList.toArray())[i])
						.printindetailTable();
			}
		}

		for (int i = 0; i < length; i++) {
			System.out.println(tableDataStr[i][2]);
		}
		return tableDataStr;
	}

	public static String[] sortByEmail(String[] email) {
		for (int i = email.length - 2; i >= 0; i--) {
			String currentEmail = email[i];
			int k;
			for (k = i + 1; k <= email.length - 1
					&& compareString(email[k], currentEmail); k++) {
				email[k - 1] = email[k];
			}
			email[k - 1] = currentEmail;
		}
		return email;
	}

	public static boolean compareString(String string1, String string2) {
		int length1 = string1.length();
		int length2 = string2.length();
		for (int i = 0; i < length1 && i < length2; i++) {
			char a = string1.charAt(i);
			char b = string2.charAt(i);
			if (a > b) {
				return true;
			} else if (a < b) {
				return false;
			}
		}
		if (length1 > length2) {
			return true;
		} else if (length1 < length2) {
			return false;
		}
		return false;
	}

	/**
	 * ���ڰ���ַ��ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactWebsite(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String websiteKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getWebsite().contains(websiteKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}
	public static String[][] queryContactAllInDetailTableByWebsite(
			String websiteKey) throws IOException {

		readObj();
		String[][] tableDataStr;
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getWebsite().contains(websiteKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		if (o.length == 0) {
			return null;
		} else {
			Arrays.sort(o);
			tableDataStr = new String[o.length][7];
			for (int i = 0; i < o.length; i++) {
				tableDataStr[i] = ((Contact) o[i]).printindetailTable();
				System.out.println(tableDataStr[i][6]);
			}
			return tableDataStr;
		}
	}
	public static String[][] sortContactAllInDetailTableByWebsite(boolean order)
			throws IOException {
		readObj();
		// int occurrence = 0;
		Object[] contactListByWebsite = contactList.toArray();
		int length = contactListByWebsite.length;
		IdentityHashMap<String, Contact> arrange1 = new IdentityHashMap<String, Contact>();
		for (int k = 0; k < length; k++) {
			String addressStr = ((Contact) contactListByWebsite[k])
					.getWebsite();
			arrange1.put(addressStr, (Contact) contactListByWebsite[k]);
			// arrange2[k] = date + " " + k;
		}

		String[] websiteOfContact = new String[length];
		for (int i = 0; i < length; i++) {
			websiteOfContact[i] = ((Contact) contactListByWebsite[i])
					.getWebsite();
		}
		websiteOfContact = sortByWebsite(websiteOfContact);
		contactList.clear();
		for (int i = 0; i < contactListByWebsite.length; i++) {
			contactList.add(arrange1.get(websiteOfContact[i]));
		}
		String[][] tableDataStr = new String[length][7];

		for (int i = 0; i < length; i++) {
			if (order) {
				tableDataStr[length - i - 1] = ((Contact) (contactList
						.toArray())[i]).printindetailTable();
			} else {
				tableDataStr[i] = ((Contact) (contactList.toArray())[i])
						.printindetailTable();
			}
		}

		for (int i = 0; i < length; i++) {
			System.out.println(tableDataStr[i][5]);
		}
		return tableDataStr;
	}

	public static String[] sortByWebsite(String[] website) {
		for (int i = website.length - 2; i >= 0; i--) {
			String currentWebsite = website[i];
			int k;
			for (k = i + 1; k <= website.length - 1
					&& compareString(website[k], currentWebsite); k++) {
				website[k - 1] = website[k];
			}
			website[k - 1] = currentWebsite;
		}
		return website;
	}

	/**
	 * ���ڰ���ַ��ѯ��ϵ��
	 * 
	 * @param inputStr
	 */
	public void queryContactAddress(String inputStr) {
		String[] posStrArr = inputStr.split(" ");
		String addressKey = posStrArr[4];
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getAddress().contains(addressKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		Arrays.sort(o);
		for (int i = 0; i < o.length; i++) {
			System.out.println(o[i]);
		}
		if (occurrence == 0) {
			System.out.println("[Message]�����ڷ��ϲ�ѯҪ�����ϵ��");
		}

	}
	public static String[][] queryContactAllInDetailTableByAddress(
			String addressKey) throws IOException {

		readObj();
		String[][] tableDataStr;
		int occurrence = 0;
		ArrayList<Contact> queryContactList = new ArrayList<Contact>();
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getAddress().contains(addressKey)) {
				queryContactList.add(contactList.get(i));
				occurrence++;
			}
		}
		Object[] o = queryContactList.toArray();
		if (o.length == 0) {
			return null;
		} else {
			Arrays.sort(o);
			tableDataStr = new String[o.length][7];
			for (int i = 0; i < o.length; i++) {
				tableDataStr[i] = ((Contact) o[i]).printindetailTable();
				System.out.println(tableDataStr[i][5]);
			}
			return tableDataStr;
		}
	}
	public static String[][] sortContactAllInDetailTableByAddress(boolean order)
			throws IOException {
		readObj();
		// int occurrence = 0;
		Object[] contactListByAddress = contactList.toArray();
		int length = contactListByAddress.length;
		String[] addressOfContact = new String[length];
		IdentityHashMap<String, Contact> arrange1 = new IdentityHashMap<String, Contact>();
		for (int k = 0; k < length; k++) {
			addressOfContact[k] = GetFirstLetter.getFirstLetter(((Contact) contactListByAddress[k])
					.getAddress());
			arrange1.put(addressOfContact[k], (Contact) contactListByAddress[k]);
			
			// arrange2[k] = date + " " + k;
		}
		System.out.println(arrange1);
	
//		for (int i = 0; i < length; i++) {
//			addressOfContact[i] = GetFirstLetter.getFirstLetter(((Contact) contactListByAddress[i])
//					.getAddress());
//		}
		addressOfContact = sortByAddress(addressOfContact);
		contactList.clear();
		for (int i = 0; i < contactListByAddress.length; i++) {
			contactList.add(arrange1.get(addressOfContact[i]));
//			System.out.println(addressOfContact[i]+"0000");
//			System.out.println(arrange1.get(addressOfContact[i]));
		}
		
		String[][] tableDataStr = new String[length][7];

		for (int i = 0; i < length; i++) {
			if (order) {
				tableDataStr[length - i - 1] = ((Contact) (contactList
						.toArray())[i]).printindetailTable();
			} else {
				tableDataStr[i] = ((Contact) (contactList.toArray())[i])
						.printindetailTable();
			}
		}

		for (int i = 0; i < length; i++) {
			System.out.println(tableDataStr[i][5]);
		}
		return tableDataStr;
	}

	public static String[] sortByAddress(String[] address) {
		for (int i = address.length - 2; i >= 0; i--) {
			String currentAddress = address[i];
			int k;
			for (k = i + 1; k <= address.length - 1
					&& compareString(address[k], currentAddress); k++) {
				address[k - 1] = address[k];
			}
			address[k - 1] = currentAddress;
		}
		return address;
	}

}
