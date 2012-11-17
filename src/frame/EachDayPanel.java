package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import data.CalendarManager;
import data.Schedule;
import demo.PrintCalendar;

/**
 * ����������ʾÿ���������ڵ�С���񣬼��������ڲ���ȷ����ǩ����ʾ�����ں����������ʾ���ճ̱�ǩ
 * 
 */
public class EachDayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel bodyPanel = null;
	private JPopupMenu popmenu = null;
	private JMenuItem addItem = null;
	private JMenuItem editItem = null;
	private JMenuItem deleteItem = null;
	private JMenuItem clearItem = null;
	private JMenuItem copyItem = null;
	private JMenuItem pasteItem = null;
	private JMenuItem cutItem = null;
	private JMenuItem doneItem=null;
	private JLabel titleLabel = null;
	private JScrollPane bodyScrollPane = null;
	private ItemLabel selectedItem = null;
	private ItemLabel[] itemLabel = new ItemLabel[10];
	private Color color1 = new Color(87, 248, 182);
	private Color color2 = new Color(167, 192, 194);
	private Color color3 = new Color(155, 181, 225);
	private Color color4 = new Color(255, 170, 32);
	private Color color5 = new Color(212, 225, 241);
	private Color color6 = new Color(250, 100, 105);
	private Color color7 = new Color(199, 237, 204);
	@SuppressWarnings("unused")
	private Color color8 = new Color(30, 235, 54);
	private Color color9 = new Color(254, 33, 8);
	private Font f3 = new Font("΢���ź�", Font.PLAIN, 12);
	private Font f4 = new Font("΢���ź�", Font.BOLD, 14);
	private Icon addIcon = new ImageIcon("./icon/addSchedule.png");
	private Icon deleteIcon = new ImageIcon("./icon/erase.png");
	private Icon editIcon = new ImageIcon("./icon/editCalendar.png");
	private Icon clearIcon = new ImageIcon("./icon/clearAll.png");
	private Icon copyIcon = new ImageIcon("./icon/copy.png");
	private Icon cutIcon = new ImageIcon("./icon/cut.png");
	private Icon pasteIcon = new ImageIcon("./icon/paste.png");
	private Icon doneIcon = new ImageIcon("./icon/done.png");
	private String dayStr = null;
	private String dateStr = null;
	private String[] dateString = new String[42];
	private boolean isToday = false;
	private boolean isThisMonth = false;
	GregorianCalendar d = new GregorianCalendar();
	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat(
			"yyyy/MM");
	public static SimpleDateFormat dateFormatter3 = new SimpleDateFormat(
			"yyyy/MM/dd");
	private ArrayList<Schedule> scheduleToday = new ArrayList<Schedule>();
	private ArrayList<Schedule> scheduleListCertainDay = new ArrayList<Schedule>();
	PrintCalendar cal = new PrintCalendar();
	private AddSchedule newAddSchedule = null;
	private ItemLabel selectedLabel = null;
	int yearInTitle;
	int monthInTitle;
	int TitleBlank = 0;
	int endingBlank = 0;
	int today = 0;

	public EachDayPanel( int day, String[] s,
			int yearInTitle, int monthInTitle, int titleBlank, int endingBlank,
			int today) throws IOException, ClassNotFoundException,
			InterruptedException {

		this.setLayout(new BorderLayout());
		this.setBorder(new SoftBevelBorder(1, color1, color2));
		this.dateString = s;
		this.yearInTitle = yearInTitle;
		this.monthInTitle = monthInTitle;
		this.TitleBlank = titleBlank;
		this.endingBlank = endingBlank;
		this.today = today;
		this.addMouseListener(getPanelAdapter());
		this.titleLabel = new JLabel();
		this.titleLabel.setBackground(color5);
		bodyPanel = new DayImagePanel();
		bodyScrollPane = new JScrollPane();
		this.add(titleLabel, BorderLayout.NORTH);
		this.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setBorder(new LineBorder(Color.white, 1, true));
		bodyPanel.setLayout(new GridLayout(4, 1));

		// CalendarManager.loadRecordsFromFile();

		// ���²������ڸ��ݷ�������ʾ�����ڻ��ͳһ��ʽ(yyyy/MM/dd)���ַ���ֵ����������ÿһ����ճ�

		if (dateString[day].contains("��")) {
			bodyPanel.setBackground(color3);
			// System.out.println(dateString[day].length());
			String yearStr = dateString[day].substring(0, dateString[day]
					.indexOf('��'));
			String monthStr = dateString[day].substring(dateString[day]
					.indexOf('��') + 1, dateString[day].indexOf('��'));
			dayStr = dateString[day].substring(
					dateString[day].indexOf('��') + 1,
					dateString[day].length() - 1);

			dateStr = yearStr
					+ "/"
					+ ((monthStr.length() == 1) ? ("0" + (monthStr))
							: (monthStr)) + "/"
					+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			if (Integer.parseInt(yearStr) != yearInTitle) {
				this.setBackground(color3);
			}
		} else if (day < TitleBlank) {
			this.setBackground(color3);

			if (dateString[day].contains("��")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('��') + 1, dateString[day]
								.indexOf('��'));
			} else {
				dayStr = dateString[day].replaceAll("��", "");
			}
			if (monthInTitle == 1) {
				dateStr = yearInTitle - 1 + "/" + "12" + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			} else {
				dateStr = yearInTitle
						+ "/"
						+ ((monthInTitle < 11) ? ("0" + (monthInTitle - 1))
								: (monthInTitle - 1)) + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			}
			// System.out.println(dateStr);
		} else if (day >= TitleBlank && day < 42 - endingBlank) {
			bodyPanel.setBackground(Color.WHITE);
			this.isThisMonth = true;
			if (dateString[day].contains("��")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('��') + 1, dateString[day]
								.indexOf('��'));
			} else {
				dayStr = dateString[day].replaceAll("��", "");
			}
			dateStr = yearInTitle
					+ "/"
					+ ((monthInTitle < 10) ? ("0" + (monthInTitle))
							: (monthInTitle)) + "/"
					+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			// System.out.println(dateStr);
		} else {

			if (dateString[day].contains("��")) {
				dayStr = dateString[day].substring(
						dateString[day].indexOf('��') + 1, dateString[day]
								.indexOf('��'));
			} else {
				dayStr = dateString[day].replaceAll("��", "");
			}
			if (monthInTitle == 12) {
				dateStr = yearInTitle + 1 + "/" + "01" + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			} else {
				dateStr = yearInTitle
						+ "/"
						+ ((monthInTitle < 9) ? ("0" + (monthInTitle + 1))
								: (monthInTitle + 1)) + "/"
						+ ((dayStr.length() == 1) ? "0" + dayStr : dayStr);
			}
			this.setBackground(color3);
			// System.out.println(dateStr);
		}

		if ((day == TitleBlank + today - 1)) {
			this.isToday = true;
			this.setBackground(color4);
			this.setBorder(new LineBorder(color4));
			//this.setFont(f4);
			//dateString[day] += "          today";
		}
		addItemLabel();
		titleLabel.setText(dateString[day]);
		titleLabel.setFont(f3);
		if ((day == TitleBlank + today - 1)) {
			titleLabel.setFont(f4);
			titleLabel.setForeground(Color.CYAN);
			//this.setFont(f4);
			//dateString[day] += "          today";
		}

	}

	/**
	 * �÷�������Ϊÿһ������Ӷ�Ӧ���ճ̱�ǩ
	 */
	public void addItemLabel() {
		scheduleToday = CalendarManager.schedulePool.get(dateStr);
		if (scheduleToday != null) {
			if (scheduleToday.size() > 4) {
				this.remove(bodyPanel);
				this.add(bodyScrollPane);// ��������ճ̶���4�������Զ���ΪScrollPane��ʽ������ʾ������ճ�
				bodyScrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				bodyScrollPane
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				bodyScrollPane.setViewportView(bodyPanel);
				bodyPanel.setLayout(new GridLayout(10, 1));

			}

			for (int i = 0; i <= scheduleToday.size() - 1; i++) {
				itemLabel[i] = new ItemLabel();
				itemLabel[i].setBorder(new LineBorder(Color.CYAN, 2, true));
				itemLabel[i].setBackground(color7);
				itemLabel[i].setText(scheduleToday.get(i).getIntroduction());
				itemLabel[i].setFont(f3);
				itemLabel[i].setTitle(scheduleToday.get(i).getTitle());
				itemLabel[i].setLocation(scheduleToday.get(i).getLocation());
				itemLabel[i].setPriority(scheduleToday.get(i).getPriority());
				itemLabel[i].setStartTime(scheduleToday.get(i).getStartTime());
				itemLabel[i].setEndTime(scheduleToday.get(i).getEndTime());
				itemLabel[i].setAllDay(scheduleToday.get(i).getAllDay());
				itemLabel[i].setBirthdayAlarm(scheduleToday.get(i)
						.getBirthdayAlarm());
				itemLabel[i].setDone(scheduleToday.get(i).isDone());
				if (itemLabel[i].getPriority().equals("high")) {
					itemLabel[i].setForeground(color9);
					itemLabel[i].setFont(f3);
					//System.out.println("This is a high priority.");
				}
				if (itemLabel[i].isDone()) {
					itemLabel[i].setForeground(Color.GRAY);
					itemLabel[i].setFont(f3);
					//System.out.println("This is a done item.");

				}
				itemLabel[i].setOrder(i);
				itemLabel[i].setFont(f3);
				itemLabel[i].setOpaque(false);
				itemLabel[i].setToolTipText(scheduleToday.get(i)
						.getIntroduction()
						+ "  " + scheduleToday.get(i).getLocation());
				itemLabel[i].addMouseListener(getLabelAdapter());
				bodyPanel.add(itemLabel[i]);
			}

		}
	}

	public MouseAdapter getLabelAdapter() {
		MouseAdapter labelMouse = new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				try {
					if (evt.isPopupTrigger()
							&& evt.getSource() instanceof JLabel) {
						selectedLabel = (ItemLabel) evt.getSource();
						(popmenu = getPopmenu()).show(evt.getComponent(), evt
								.getX(), evt.getY());
						addItem.setEnabled(false);
						deleteItem.setEnabled(true);
						editItem.setEnabled(true);
						copyItem.setEnabled(true);
						cutItem.setEnabled(true);
						clearItem.setEnabled(true);
						pasteItem.setEnabled(false);
						doneItem.setEnabled(true);

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"You should select a label first");
				}

			}

			public void mouseEntered(MouseEvent evt) {
				selectedItem = (ItemLabel) evt.getSource();
				selectedItem.setBorder(new LineBorder(color6, 2));

			}

			public void mouseExited(MouseEvent evt) {
				selectedItem = (ItemLabel) evt.getSource();
				selectedItem.setBorder(new LineBorder(Color.CYAN, 2));
			}

			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					selectedItem = (ItemLabel) evt.getSource();
					AddSchedule editSchedule = new AddSchedule();
					String[] dateArr = dateStr.split("/");
					editSchedule.setYearText(dateArr[0]);
					editSchedule.setMonthText(dateArr[1]);
					editSchedule.setDayText(dateArr[2]);
					editSchedule.setTitleText(selectedItem.getTitle());
					editSchedule.setAddressText(selectedItem.getLocationStr());
					editSchedule.setPriorityItem(selectedItem.getPriority());
					editSchedule.setStartTime(selectedItem.getStartTimeStr());
					editSchedule.setEndTime(selectedItem.getEndTimeStr());
					editSchedule.setAllDayAndBirthdayAlarm(selectedItem
							.isAllDay(), selectedItem.isBirthdayAlarm());
					editSchedule.setVisible(true);
					editSchedule.addWindowListener(new WindowAdapter() {// Ϊ����ճ̴���ע�ᴰ�ڼ�������ʹ�������޸���������Ч

								@Override
								public void windowClosing(WindowEvent e) {
									bodyPanel.removeAll();
									addItemLabel();
									bodyPanel.validate();
								}
							});

				}
			}
		};
		return labelMouse;
	}

	// Ϊÿһ���ճ̱�ǩ����Ҽ������˵�
	public JPopupMenu getPopmenu() {
		if (popmenu == null) {
			popmenu = new JPopupMenu();
			popmenu.add(addItem = new JMenuItem("��Ӽ�¼"));
			addItem.setFont(f3);
			addItem.setIcon(addIcon);
			popmenu.add(deleteItem = new JMenuItem("ɾ�������¼"));
			deleteItem.setFont(f3);
			deleteItem.setIcon(deleteIcon);
			popmenu.add(editItem = new JMenuItem("�༭�����¼"));
			editItem.setFont(f3);
			editItem.setIcon(editIcon);
			popmenu.add(clearItem = new JMenuItem("ɾ������ȫ����¼"));
			clearItem.setFont(f3);
			clearItem.setIcon(clearIcon);
			popmenu.add(copyItem = new JMenuItem("���Ƹü�¼"));
			copyItem.setFont(f3);
			copyItem.setIcon(copyIcon);
			popmenu.add(cutItem = new JMenuItem("���иü�¼"));
			cutItem.setFont(f3);
			cutItem.setIcon(cutIcon);
			popmenu.add(pasteItem = new JMenuItem("ճ����¼"));
			pasteItem.setFont(f3);
			pasteItem.setIcon(pasteIcon);
			popmenu.add(doneItem = new JMenuItem("�����"));
			doneItem.setFont(f3);
			doneItem.setIcon(doneIcon);
			addItem.addActionListener(getPopmenuListener());
			deleteItem.addActionListener(getPopmenuListener());
			editItem.addActionListener(getPopmenuListener());
			clearItem.addActionListener(getPopmenuListener());
			copyItem.addActionListener(getPopmenuListener());
			pasteItem.addActionListener(getPopmenuListener());
			cutItem.addActionListener(getPopmenuListener());
			doneItem.addActionListener(getPopmenuListener());
		}
		return popmenu;
	}

	private ActionListener getPopmenuListener() {
		ActionListener panelAdapter = new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
				if (evt.getSource() == deleteItem) {
					if (selectedLabel.isBirthdayAlarm()) {
						Object[] options2 = { "��", "��", "ȡ��" };
						int j = JOptionPane.showOptionDialog(null,
								"����ѡ���ճ�Ϊ�������ѣ���Ҫ����ɾ����", "ȷ������",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null,
								options2, options2[0]);
						if (j == 0) {

							String dateStr = dateFormatter3
									.format(selectedLabel.getStartTime());
							String[] posStrArr = dateStr.split("/");

							int year = Integer.parseInt(posStrArr[0]);
							for (int k = year - 30; k <= year + 30; k++) {
								String newDateString = dateStr
										.replaceFirst("[\\d]{4}", String
												.valueOf(k));
								// System.out.println(newDateString);
								Date newDate = CalendarManager
										.getDate(newDateString);
								scheduleListCertainDay = CalendarManager.schedulePool
										.get(newDateString);
								if (scheduleListCertainDay != null) {
									for (int m = 0; m < scheduleListCertainDay
											.size(); m++) {
										if (scheduleListCertainDay.get(m).getTitle().equals(selectedLabel.getTitle())) {
											CalendarManager.deleteScheduleByDateAndIndex(newDate, m);
										}
									}
								}
							}
						} else if (j == 1) {
							Date selectDate = CalendarManager
									.getDate(dateStr);
							CalendarManager.deleteScheduleByDateAndIndex(
									selectDate, selectedItem.getOrder());
							
						} else{
							return;
						}try {
							CalendarManager.saveRecordsToFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bodyPanel.removeAll();
						addItemLabel();
						bodyPanel.validate();
						JOptionPane.showMessageDialog(null, "��ѡ������ɾ��");
					
					}else{

					Object[] options = { "ȷ��", "ȡ��" };
					int i = JOptionPane.showOptionDialog(null, "ȷ��Ҫɾ����ѡ�ճ���",
							"ȷ������", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if (i == 0) {
							Date selectDate = CalendarManager.getDate(dateStr);
							CalendarManager.deleteScheduleByDateAndIndex(
									selectDate, selectedItem.getOrder());
						
						try {
							CalendarManager.saveRecordsToFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bodyPanel.removeAll();
						addItemLabel();
						bodyPanel.validate();
						JOptionPane.showMessageDialog(null, "��ѡ������ɾ��");}else{
							return;
						}
					}

				} else if (evt.getSource() == editItem) {
					// System.out.println("edit schedule");
					AddSchedule editSchedule = new AddSchedule();
					String[] dateArr = dateStr.split("/");
					editSchedule.setYearText(dateArr[0]);// ����ÿһ����¼�����Ը���AddSchedule���ڵĳ�ʼֵ
					editSchedule.setMonthText(dateArr[1]);
					editSchedule.setDayText(dateArr[2]);
					editSchedule.setTitleText(selectedItem.getTitle());
					editSchedule.setAddressText(selectedItem.getLocationStr());
					editSchedule.setPriorityItem(selectedItem.getPriority());
					editSchedule.setStartTime(selectedItem.getStartTimeStr());
					editSchedule.setEndTime(selectedItem.getEndTimeStr());
					editSchedule.setAllDayAndBirthdayAlarm(selectedItem
							.isAllDay(), selectedItem.isBirthdayAlarm());
					editSchedule.setVisible(true);
					editSchedule.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							bodyPanel.removeAll();
							addItemLabel();
							bodyPanel.validate();
						}
					});
				} else if (evt.getSource() == clearItem) {

					Object[] options = { "ȷ��", "ȡ��" };
					int i = JOptionPane.showOptionDialog(null, "ȷ��Ҫɾ�������ճ���",
							"ȷ������", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if (i == 0) {
						@SuppressWarnings("unused")
						Date selectDate = CalendarManager.getDate(dateStr);
						// System.out.println(dateStr);
						ArrayList<Schedule> scheduleToday = CalendarManager.schedulePool
								.get(dateStr);
						// for(int j = 0;j<scheduleToday.size();j++){
						// CalendarManager.deleteScheduleByDateAndIndex(
						// selectDate,j);
						// System.out.println("AAAAAAAAAAA");
						scheduleToday.clear();
						// }
						try {
							CalendarManager.saveRecordsToFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bodyPanel.removeAll();
						addItemLabel();
						bodyPanel.validate();
						JOptionPane.showMessageDialog(null, "��ѡ������ɾ��");
					} else {
						return;
					}

				} else if (evt.getSource() == copyItem) {
					MainMenu.tempLabel = selectedItem;
				} else if (evt.getSource() == pasteItem) {
					ItemLabel tempItemLabel = MainMenu.tempLabel;
					String title = tempItemLabel.getTitle();
					Date startTime = CalendarManager.getDate(dateStr + " "
							+ tempItemLabel.getStartTimeStr() + ":00");
					Date endTime = CalendarManager.getDate(dateStr + " "
							+ tempItemLabel.getEndTimeStr() + ":00");
					String location = tempItemLabel.getLocationStr();
					String priority = tempItemLabel.getPriority();
					Boolean allDay = tempItemLabel.isAllDay();
					Boolean birthdayAlarm = tempItemLabel.isBirthdayAlarm();
					Boolean isDone = tempItemLabel.isDone();
					Schedule s = new Schedule(title, startTime, endTime,
							location, priority, allDay, birthdayAlarm,isDone);
					// System.out.println(s);
					CalendarManager.addNewSchedule(s);
					try {
						CalendarManager.saveRecordsToFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bodyPanel.removeAll();
					addItemLabel();
					bodyPanel.validate();
				} else if (evt.getSource() == cutItem) {
					MainMenu.tempLabel = selectedItem;
					Date date = CalendarManager.getDate(dateStr);
					int order = selectedItem.getOrder();
					CalendarManager.deleteScheduleByDateAndIndex(date, order);
					bodyPanel.removeAll();
					addItemLabel();
					bodyPanel.validate();
				} else if (evt.getSource() == addItem) {
					String dayStrArray[] = dateStr.split("/");
					// System.out.println("AAAA"+dateStr);
					newAddSchedule = new AddSchedule(dayStrArray[0],
							dayStrArray[1], dayStrArray[2]);
					newAddSchedule.setVisible(true);
					newAddSchedule.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							bodyPanel.removeAll();
							addItemLabel();
							bodyPanel.validate();
						}
					});
				}else if (evt.getSource() == doneItem) {
					Boolean newIsDone = selectedLabel.isDone();
					selectedLabel.setDone(!newIsDone);
					//selectedLabel.setForeground(Color.GRAY);
					String title = selectedLabel.getTitle();
					Date startTime=selectedLabel.getStartTime();
					Date endTime=selectedLabel.getEndTime();
					String location = selectedLabel.getLocationStr();
					String priority=selectedLabel.getPriority();
					Boolean allDay=selectedLabel.isAllDay();
					Boolean birthdayAlarm = selectedLabel.isBirthdayAlarm();
					Boolean isDone = selectedLabel.isDone();
					Schedule newSchedule = new Schedule(title,
							startTime, endTime, location, priority,
							allDay, birthdayAlarm,isDone);
					Date selectedDate = selectedLabel.getStartTime();
					System.out.println(selectedDate);
					int order = selectedLabel.getOrder();
					CalendarManager.deleteScheduleByDateAndIndex(selectedDate, order);
					CalendarManager.addNewSchedule(newSchedule);
					bodyPanel.removeAll();
					addItemLabel();
					bodyPanel.validate();
					System.out.println("1 "+selectedLabel.isDone());
					try {
						CalendarManager.saveRecordsToFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		return panelAdapter;
	}

	public MouseAdapter getPanelAdapter() {
		MouseAdapter panelAdapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {

				setBackground(color1);

			}

			public void mouseExited(MouseEvent evt) {

				if (isToday) {
					setBackground(color4);
				} else if (!isThisMonth) {
					setBackground(color3);
				} else {
					setBackground(color5);
				}
			}

			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					String dayStrArray[] = dateStr.split("/");
					// System.out.println("AAAA"+dateStr);
					newAddSchedule = new AddSchedule(dayStrArray[0],
							dayStrArray[1], dayStrArray[2]);
					newAddSchedule.setVisible(true);
					newAddSchedule.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							bodyPanel.removeAll();
							addItemLabel();
							bodyPanel.validate();
						}
					});

				}
			}

			public void mouseReleased(MouseEvent evt) {
				try {
					if (evt.isPopupTrigger()
							&& evt.getSource() instanceof JPanel) {

						(popmenu = getPopmenu()).show(evt.getComponent(), evt
								.getX(), evt.getY());
						if (MainMenu.tempLabel == null) {
							addItem.setEnabled(true);
							deleteItem.setEnabled(false);
							editItem.setEnabled(false);
							copyItem.setEnabled(false);
							cutItem.setEnabled(false);
							clearItem.setEnabled(true);
							pasteItem.setEnabled(false);
						} else {
							addItem.setEnabled(true);
							deleteItem.setEnabled(false);
							editItem.setEnabled(false);
							copyItem.setEnabled(false);
							cutItem.setEnabled(false);
							clearItem.setEnabled(true);
							pasteItem.setEnabled(true);
						}

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"You should select a label first");
				}

			}
		};
		return panelAdapter;
	}

}
