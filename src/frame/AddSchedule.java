package frame;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import data.CalendarManager;
import data.Schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import java.awt.event.KeyEvent;
/**
 * 本类用于添加和修改日程记录
 * @author Administrator
 *
 */
public class AddSchedule extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel schedulePanel = null;
	private JButton saveButton = null;
	private JButton resetButton = null;
	private ButtonGroup group = new ButtonGroup(); 
	private JRadioButton normalRadioButton;
	private JRadioButton birthdayRadioButton = null;
	private JRadioButton alldayRadioButton = null;
	private JComboBox priorityComboBox = null;
	private JLabel dateLabel = null;
	private JLabel startTimeLabel = null;
	private JLabel colonLabel1 = null;
	private JLabel endTimeLabel = null;
	private JLabel colonLabel2 = null;
	private JLabel addressLabel = null;
	private JLabel titleLabel = null;
	private JLabel scheduleLabel = null;
	private JLabel priorityLabel = null;
	private JTextField startTimeTextField1 = null;	
	private JTextField startTimeTextField2 = null;	
	private JTextField endTimeTextField1 = null;	
	private JTextField endTimeTextField2 = null;	
	private JTextField addressTextField = null;	
	private JTextField titleTextField = null;
	private JTextField yearTextField = null;
	private JTextField monthTextField = null;
	private JTextField dayTextField = null;
	private Color color1 = new Color(213, 228, 242);
	private Color color2 = new Color(245, 245, 245);
	private Color color3=SystemColor.controlHighlight;  //  @jve:decl-index=0:
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Font f4 = new Font("微软雅黑", Font.PLAIN+Font.BOLD, 12);
	private Icon saveIcon = new ImageIcon("./icon/save.png");
	@SuppressWarnings("unused")
	private Icon mailIcon = new ImageIcon("./icon/mail.png"); 
	private Icon clearIcon = new ImageIcon("./icon/clear.png");
	
	protected ArrayList<Schedule> scheduleToday;	
	private boolean allDay = false;
	private boolean birthdayAlarm = false;
	private boolean isRepeat = false;
	private Date startTime = null;
	private Date endTime = null;
	private String title = null;
	private String location = null;	
	private String priority = null;
	private String[] priorityStr = { "高", "中", "低", "不确定" };
	
	/**
	 * This is the default constructor
	 */
	public AddSchedule() {
		super();
		initialize();
	}

	public AddSchedule(String yearStr, String monthStr, String dayStr) {
		super();
		initialize();
		this.yearTextField.setText(yearStr);
		this.monthTextField.setText(monthStr);
		this.dayTextField.setText(dayStr);

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(445, 332);
		this.setContentPane(getJContentPane());
		this.setTitle("日程维护");
		this.setFont(f3);
		this.setResizable(false);
		Toolkit toolkit = this.getToolkit();
		Image topicon = toolkit.getImage("./icon/calendarManager.png");
		this.setIconImage(topicon);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				// TODO Auto-generated Event stub windowClosing()
			}
		});
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			titleLabel = new JLabel();
			titleLabel.setText("内容");
			titleLabel.setForeground(color3);
			titleLabel.setFont(f4);
			titleLabel.setBounds(new Rectangle(5, 175, 65, 30));
			addressLabel = new JLabel();
			addressLabel.setText("地址");
			addressLabel.setForeground(color3);
			addressLabel.setFont(f4);
			addressLabel.setBounds(new Rectangle(5, 135, 65, 30));
			colonLabel2 = new JLabel();
			colonLabel2.setText("：");
			colonLabel2.setBounds(new Rectangle(335, 95, 15, 30));
			endTimeLabel = new JLabel();
			endTimeLabel.setText("结束时间");
			endTimeLabel.setFont(f4);
			endTimeLabel.setBounds(new Rectangle(210, 95, 65, 30));
			colonLabel1 = new JLabel();
			colonLabel1.setText("：");
			colonLabel1.setBounds(new Rectangle(140, 95, 15, 30));
			startTimeLabel = new JLabel();
			startTimeLabel.setText("起始时间");
			startTimeLabel.setFont(f4);
			startTimeLabel.setBounds(new Rectangle(5, 95, 65, 30));
			dateLabel = new JLabel();
			dateLabel.setText("日期");
			dateLabel.setFont(f4);
			dateLabel.setBounds(new Rectangle(5, 60, 65, 30));
			jContentPane = new ImagePanel();
			jContentPane.setLayout(null);
			jContentPane.add(getSchedulePanel(), null);
			jContentPane.setBackground(color1);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getSaveButton(), null);
			group.add(getNormalRadioButton());
			group.add(getBirthdayRadioButton());
			group.add(getAlldayRadioButton());
		}
		return jContentPane;
	}

	/**
	 * This method initializes saveButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("保存日程");
			saveButton.setBounds(new Rectangle(10, 0, 130, 35));
			saveButton.setFont(f3);
			saveButton.setIcon(saveIcon);
			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()"); // TODO
					// Auto-generated Event stub actionPerformed()
					title = titleTextField.getText();
					location = addressTextField.getText();
					isRepeat = false;
					String yearStr = yearTextField.getText();
					String monthStr = monthTextField.getText();
					String dayStr = dayTextField.getText();
					String dateStr = yearStr
							+ "/"
							+ (monthStr.length() == 1 ? "0" + monthStr
									: monthStr) + "/"
							+ (dayStr.length() == 1 ? "0" + dayStr : dayStr);

					String startTimeStr1 = startTimeTextField1.getText();
					String startTimeStr2 = startTimeTextField2.getText();
					String startTimeStr = dateStr + " " + startTimeStr1 + ":"
							+ startTimeStr2 + ":00";

					String endTimeStr1 = endTimeTextField1.getText();
					String endTimeStr2 = endTimeTextField2.getText();
					String endTimeStr = dateStr + " " + endTimeStr1 + ":"
							+ endTimeStr2 + ":00";
					if (!title.equals("")) {
						if (CalendarManager.isValid(dateStr)
								&& CalendarManager.isValid(endTimeStr)
								&& CalendarManager.isValid(startTimeStr)) {
							Date dateDat = CalendarManager.getDate(dateStr);
							String priorityStr = (String) priorityComboBox
									.getSelectedItem();
							if (priorityStr.equals("高")) {
								priority = "high";
							} else if (priorityStr.equals("中")) {
								priority = "medium";
							} else if (priorityStr.equals("低")) {
								priority = "low";
							} else {
								priority = "uncertain";
							}
							allDay = alldayRadioButton.isSelected();
							birthdayAlarm = birthdayRadioButton.isSelected();
							int order = 0;
							startTime = CalendarManager.getDate(startTimeStr);
							endTime = CalendarManager.getDate(endTimeStr);
							
							Schedule newSchedule = new Schedule(title,
									startTime, endTime, location, priority,
									allDay, birthdayAlarm,false);
							try {
								CalendarManager.loadRecordsFromFile();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (ClassNotFoundException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							scheduleToday = CalendarManager.schedulePool
									.get(dateStr);
							if (scheduleToday != null) {
								for (int i = 0; i < scheduleToday.size(); i++) {
									if (title.equals(scheduleToday.get(i)
											.getTitle())) {
										isRepeat = true;
										order = i;
										break;
									}
								}
								if (isRepeat) {
									CalendarManager
											.deleteScheduleByDateAndIndex(
													dateDat, order);
									isRepeat = false;
									order = 0;
								}
							}
							CalendarManager.addNewSchedule(newSchedule);
							try {

								CalendarManager.saveRecordsToFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "日程添加成功");
						} else {
							JOptionPane.showMessageDialog(null, "日期格式错误",
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "标题不能为空", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		}
		return saveButton;
	}

	/**
	 * This method initializes startTimeTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getStartTimeTextField1() {
		if (startTimeTextField1 == null) {
			startTimeTextField1 = new JTextField();
			startTimeTextField1.setBounds(new Rectangle(100, 95, 40, 30));
			startTimeTextField1.setText("00");
			startTimeTextField1.setForeground(Color.GRAY);
		}
		return startTimeTextField1;
	}

	/**
	 * This method initializes startTimeTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getStartTimeTextField2() {
		if (startTimeTextField2 == null) {
			startTimeTextField2 = new JTextField();
			startTimeTextField2.setBounds(new Rectangle(160, 95, 40, 30));
			startTimeTextField2.setText("00");
			startTimeTextField2.setForeground(Color.GRAY);
		}
		return startTimeTextField2;
	}
	public void setStartTime(String startTime){
		String[] startTimeArr = startTime.split(":");
		startTimeTextField1.setText(startTimeArr[0]);
		startTimeTextField2.setText(startTimeArr[1]);
	}
	/**
	 * This method initializes endTimeTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEndTimeTextField1() {
		if (endTimeTextField1 == null) {
			endTimeTextField1 = new JTextField();
			endTimeTextField1.setBounds(new Rectangle(290, 95, 40, 30));
			endTimeTextField1.setText("00");
			endTimeTextField1.setForeground(Color.gray);
		}
		return endTimeTextField1;
	}

	/**
	 * This method initializes endTimeTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEndTimeTextField2() {
		if (endTimeTextField2 == null) {
			endTimeTextField2 = new JTextField();
			endTimeTextField2.setBounds(new Rectangle(354, 95, 40, 30));
			endTimeTextField2.setText("00");
			endTimeTextField2.setForeground(Color.gray);
		}
		return endTimeTextField2;
	}
	public void setEndTime(String endTime){
		String[] endTimeArr = endTime.split(":");
		endTimeTextField1.setText(endTimeArr[0]);
		endTimeTextField2.setText(endTimeArr[1]);
	}
	/**
	 * This method initializes addressTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getAddressTextField() {
		if (addressTextField == null) {
			addressTextField = new JTextField();
			addressTextField.setBounds(new Rectangle(100, 135, 300, 30));
		}
		return addressTextField;
	}
	public void setAddressText(String addressStr){
		addressTextField.setText(addressStr);
	}
	/**
	 * This method initializes titleTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTitleTextField() {
		if (titleTextField == null) {
			titleTextField = new JTextField();
			titleTextField.setBounds(new Rectangle(100, 175, 300, 30));
		}
		return titleTextField;
	}

	public void setTitleText(String title) {
		this.titleTextField.setText(title);
	}

	/**
	 * This method initializes schedulePanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getSchedulePanel() {
		if (schedulePanel == null) {
			priorityLabel = new JLabel();
			priorityLabel.setBounds(new Rectangle(5, 214, 65, 30));
			priorityLabel.setText("重要性");
			priorityLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			priorityLabel.setForeground(color3);
			priorityLabel.setFont(f4);
			scheduleLabel = new JLabel();
			scheduleLabel.setBounds(new Rectangle(5, 25, 65, 30));
			scheduleLabel.setText("事件类型");
			scheduleLabel.setFont(f4);
			schedulePanel = new JPanel();
			schedulePanel.setOpaque(false);
			schedulePanel.setBackground(color1);
			schedulePanel.setBorder(new TitledBorder("日程维护"));
			schedulePanel.setFont(f4);
			schedulePanel.setLayout(null);
			schedulePanel.setBounds(new Rectangle(5, 40, 430, 260));
			schedulePanel.add(dateLabel, null);
			schedulePanel.add(getStartTimeTextField1(), null);
			schedulePanel.add(getStartTimeTextField2(), null);
			schedulePanel.add(getEndTimeTextField1(), null);
			schedulePanel.add(getEndTimeTextField2(), null);
			schedulePanel.add(getAddressTextField(), null);
			schedulePanel.add(getTitleTextField(), null);
			schedulePanel.add(startTimeLabel, null);
			schedulePanel.add(colonLabel1, null);
			schedulePanel.add(endTimeLabel, null);
			schedulePanel.add(colonLabel2, null);
			schedulePanel.add(addressLabel, null);
			schedulePanel.add(titleLabel, null);
			schedulePanel.add(scheduleLabel, null);
			schedulePanel.add(getNormalRadioButton(), null);
			schedulePanel.add(getBirthdayRadioButton(), null);
			schedulePanel.add(getAlldayRadioButton(), null);
			schedulePanel.add(getYearTextField(), null);
			schedulePanel.add(getMonthTextField(), null);
			schedulePanel.add(getDayTextField(), null);
			schedulePanel.add(priorityLabel, null);
			schedulePanel.add(getPriorityComboBox(), null);
		}
		return schedulePanel;
	}

	/**
	 * This method initializes normalRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getNormalRadioButton() {
		if (normalRadioButton == null) {
			normalRadioButton = new JRadioButton();
			normalRadioButton.setOpaque(false);
			normalRadioButton.setBounds(new Rectangle(100, 25, 85, 30));
			normalRadioButton.setText("普通事件");
			normalRadioButton.setFont(f4);
			normalRadioButton.setBackground(color1);
		}
		return normalRadioButton;
	}

	/**
	 * This method initializes birthdayRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getBirthdayRadioButton() {
		if (birthdayRadioButton == null) {
			birthdayRadioButton = new JRadioButton();
			birthdayRadioButton.setOpaque(false);
			birthdayRadioButton.setBounds(new Rectangle(191, 25, 85, 30));
			birthdayRadioButton.setText("生日提醒");
			birthdayRadioButton.setFont(f4);
			birthdayRadioButton.setBackground(color1);
		}
		return birthdayRadioButton;
	}

	/**
	 * This method initializes alldayRadioButton
	 * 
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getAlldayRadioButton() {
		if (alldayRadioButton == null) {
			alldayRadioButton = new JRadioButton();
			alldayRadioButton.setOpaque(false);
			alldayRadioButton.setBounds(new Rectangle(290, 25, 85, 30));
			alldayRadioButton.setText("全天事件");
			alldayRadioButton.setFont(f4);
			alldayRadioButton.setBackground(color1);
		}
		return alldayRadioButton;
	}
	public void setAllDayAndBirthdayAlarm(boolean allDay2,
			boolean birthdayAlarm2) {
		if(birthdayAlarm2){
			birthdayRadioButton.setSelected(true);
		}else if(allDay2){
			alldayRadioButton.setSelected(true);
		}else{
			normalRadioButton.setSelected(true);
		}
		
	}
	/**
	 * This method initializes yearTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getYearTextField() {
		if (yearTextField == null) {
			yearTextField = new JTextField();
			yearTextField.setBounds(new Rectangle(100, 60, 85, 30));
		}
		return yearTextField;
	}

	public void setYearText(String year) {
		this.yearTextField.setText(year);
	}

	/**
	 * This method initializes monthTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getMonthTextField() {
		if (monthTextField == null) {
			monthTextField = new JTextField();
			monthTextField.setBounds(new Rectangle(209, 60, 85, 30));
		}
		return monthTextField;
	}

	public void setMonthText(String month) {
		this.monthTextField.setText(month);
	}

	/**
	 * This method initializes dayTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getDayTextField() {
		if (dayTextField == null) {
			dayTextField = new JTextField();
			dayTextField.setBounds(new Rectangle(315, 60, 85, 30));
		}
		return dayTextField;
	}

	public void setDayText(String day) {
		this.dayTextField.setText(day);
	}

	/**
	 * This method initializes priorityComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getPriorityComboBox() {
		if (priorityComboBox == null) {

			priorityComboBox = new JComboBox(priorityStr);
			priorityComboBox.setBounds(new Rectangle(100, 215, 300, 30));
		}
		return priorityComboBox;
	}
	public void setPriorityItem(String priority){
		if(priority.equals("high")){
			priorityComboBox.setSelectedItem(priorityStr[0]);
		}else if(priority.equals("medium")){
			priorityComboBox.setSelectedItem(priorityStr[1]);
		}else if(priority.equals("low")){
			priorityComboBox.setSelectedItem(priorityStr[2]);
		}else{
			priorityComboBox.setSelectedItem(priorityStr[3]);
		}
	}
	/**
	 * This method initializes resetButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setText("重置数据");
			resetButton.setBounds(new Rectangle(140, 0, 130, 35));
			resetButton.setFont(f3);
			resetButton.setIcon(clearIcon);
			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// System.out.println("actionPerformed()"); // TODO
					// Auto-generated Event stub actionPerformed()
					normalRadioButton.setSelected(true);
					yearTextField.setText(null);
					monthTextField.setText(null);
					dayTextField.setText(null);
					startTimeTextField1.setText(null);
					startTimeTextField2.setText(null);
					endTimeTextField1.setText(null);
					endTimeTextField2.setText(null);
					addressTextField.setText(null);
					titleTextField.setText(null);
					priorityComboBox.setSelectedItem(priorityStr[0]);

				}
			});
		}
		return resetButton;
	}

	

} // @jve:decl-index=0:visual-constraint="10,10"
