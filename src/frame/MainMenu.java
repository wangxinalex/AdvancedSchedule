package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import data.CalendarManager;
import data.ContactManager;
import java.awt.Rectangle;
import javax.swing.JButton;

import ch.randelshofer.quaqua.util.Methods;

import java.awt.Dimension;

/**
 * 程序主类，用于显示日程管理界面，支持图形化日历显示，在每个日历方格中双击添加日程、右键删除和编辑日程（结果同步显示），前后翻月、翻年等功能
 * 
 * @author 10302010023 王欣
 * @param tempLabel
 *            剪贴板，用于记录剪切或复制的日程
 */
class MainMenu implements ActionListener {

	private  JFrame mainFrame = null;
	private static JFrame showFrame=null;
	private JPanel contentPane = null;
	private JPanel changePanel, titlePanel = null;  //  @jve:decl-index=0:visual-constraint="10,902"
	private JPanel weekPanel = null;
	private JPanel addContactPanel = null;
	private JPanel calendarPanel = new JPanel();
	private JMenuBar myMenuBar = null;
	private JMenu contact_menu;
	private JMenu query_contact;
	private JMenu schedule_menu = null;
	private JMenu help_menu = null;
	private JMenu skin_menu = null;
	private JMenu exit_menu = null;
	private JMenuItem add_contact, delete_contact, manage_contact, mail_menu;
	private JMenuItem add_schedule, delete_schedule, edit_schedule,
			query_schedule;
	private JMenuItem query_schedule_today, query_schedule_day,
			query_schedule_prior;
	private JMenuItem query_contact_all, query_contact_condition;
	private JMenuItem help_about, help_content;
	private JMenuItem exit_schedule;
	private JRadioButtonMenuItem AluminiumLookAndFeel_skin,
			SmartLookAndFeel_menu, AeroLookAndFeel_menu, McWinLookAndFeel_menu,
			LunaLookAndFeel_menu;
	private JButton previousButton = null;
	private JButton nextButton = null;
	private JButton previousYearButton = null;
	private JButton nextYearButton = null;
	private JButton resetButton = null;
	private JLabel dateLabel = null;
	private JLabel weekLabel = null;
	public static ItemLabel tempLabel = null;
	private ContactJPanel contactPanel = null;
	private Font f1 = new Font("YouYuan", Font.BOLD, 25);
	private Font f2 = new Font("YouYuan", Font.BOLD, 30);
	private static Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private static Font f4 = new Font("微软雅黑", Font.BOLD+Font.ITALIC, 12);
	private Icon leftYearIcon = new ImageIcon("./icon/leftYear.png");
	private Icon leftYearIcon1 = new ImageIcon("./icon/leftYear1.png");
	private Icon rightYearIcon = new ImageIcon("./icon/rightYear.png");
	private Icon rightYearIcon1 = new ImageIcon("./icon/rightYear1.png");
	private Icon changeIcon = new ImageIcon("./icon/change.png");
	private Icon calendarManagerIcon = new ImageIcon(
			"./icon/calendarManager.png");
	private Icon addScheduleIcon = new ImageIcon("./icon/addSchedule.png");
	private Icon queryMenuIcon = new ImageIcon("./icon/queryMenu.png");
	private Icon contactManagerIcon = new ImageIcon("./icon/contactManager.png");
	private Icon addContactIcon = new ImageIcon("./icon/addContact.png");
	private Icon deleteContactIcon = new ImageIcon("./icon/erase.png");
	private Icon mailIcon = new ImageIcon("./icon/mail.png");
	private Icon helpIcon = new ImageIcon("./icon/gethelp.png");
	private Icon aboutIcon = new ImageIcon("./icon/helpIcon2.png");
	private Icon exitIcon = new ImageIcon("./icon/exit.png");
	private Icon resetIcon = new ImageIcon("./icon/resetCalendar.png");
	private Color titleColor = SystemColor.activeCaption ;

	private String[] weekString = { "SUN", "MON", "TUE", "WEN", "THU", "FRI",
			"SAT" };
	private String[] tableTitleStr = { "姓名", "性别", "邮箱", "电话", "生日", "地址", "网址" };
	private String[][] tableDataStr;
	//private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	public static SimpleDateFormat dateFormatter4 = new SimpleDateFormat(
			"yyyy年MM月");
	private static int occurrence = 0;
	private JButton jDesktopButton = null;

	public MainMenu(JFrame mainFrame) throws IOException,
			ClassNotFoundException, InterruptedException {
		this.mainFrame = mainFrame;
		init();
	}
	public MainMenu() throws IOException, ClassNotFoundException, InterruptedException{
		this.mainFrame=new JFrame();
		init();
	}

	private void init() throws IOException, ClassNotFoundException,
			InterruptedException {
		CalendarManager.loadRecordsFromFile();
		ContactManager.readObj();
		myMenuBar = new JMenuBar();
		mainFrame.setJMenuBar(addMenuBar());
		mainFrame.setContentPane(getContentPane());
		Toolkit toolkit = mainFrame.getToolkit();
		Image topicon = toolkit.getImage("./icon/title.png");
		mainFrame.setIconImage(topicon);
		mainFrame.setVisible(true);

	}

	public JPanel getContentPane() throws IOException, ClassNotFoundException,
			InterruptedException {
		if (contentPane == null) {
			contentPane = new ImagePanel();
			contentPane.setLayout(new BorderLayout(2, 2));
			contentPane.add(titlePanel = AddPanel(), BorderLayout.NORTH);
			contentPane
					.add(calendarPanel = AddCalendar(0), BorderLayout.CENTER);
			contentPane.add(weekPanel = getWeekPanel(), BorderLayout.SOUTH);
			addListener();
		}
		return contentPane;
	}

	public JPanel getWeekPanel() {
		if (weekPanel == null) {
			weekPanel = new JPanel();
			weekPanel.setOpaque(false);
			weekPanel.setLayout(new GridLayout(1, 7));
			JLabel[] weekLabels = new JLabel[7];
			for (int i = 0; i < 7; i++) {
				weekLabels[i] = new JLabel(weekString[i]);
				weekLabels[i].setFont(f4);
				weekLabels[i].setForeground(Color.WHITE);
				weekPanel.add(weekLabels[i]);
				weekLabels[i].setOpaque(false);
				//weekLabels[i].setFont(f3);
			}
		}
		return weekPanel;
	}

	private void addListener() {
		exit_schedule.addActionListener(this);
		help_about.addActionListener(this);
		help_content.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousYearButton.addActionListener(this);
		nextYearButton.addActionListener(this);
		resetButton.addActionListener(this);
		manage_contact.addActionListener(this);
		delete_contact.addActionListener(this);
		edit_schedule.addActionListener(this);
		add_schedule.addActionListener(this);
		query_schedule.addActionListener(this);
		add_contact.addActionListener(this);
		mail_menu.addActionListener(this);
	}

	/**
	 * 本方法用于在前后月刷新后为失效的按钮重新添加监听器
	 */
	private void addListenerExtra() {
		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		SmartLookAndFeel_menu.addActionListener(getActionListener());
		AluminiumLookAndFeel_skin.addActionListener(getActionListener());
		McWinLookAndFeel_menu.addActionListener(getActionListener());
		AeroLookAndFeel_menu.addActionListener(getActionListener());

		// previousYearButton.addActionListener(this);
		// nextYearButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (e.getSource() instanceof JMenuItem) {
			if (command.equals("退出系统")) {
				System.exit(0);
			}
			if (command.equals("关于Advanced Schedule")) {
				new HelpJFrame().setVisible(true);
			}
			if (command.equals("帮助内容")) {
				
				new GetHelpJFrame().setVisible(true);
			
			}
			if (command.equals("联系人管理界面")) {
				contentPane.removeAll();

				try {
					contactPanel = new ContactJPanel();
					contentPane.add(addContactPanel = contactPanel
							.getContactPanel());
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (command.equals("日程管理界面")) {
				occurrence = 0;
				contentPane.removeAll();
				contentPane.setLayout(new BorderLayout(0, 2));
				try {
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// int year = d.get(Calendar.YEAR);
				// int month = d.get(Calendar.MONTH)+1;
				try {
					contentPane.add(calendarPanel = AddCalendar(0),
							BorderLayout.CENTER);
					contentPane.add(weekPanel, BorderLayout.SOUTH);
					JLabel[] weekLabels = new JLabel[7];

					weekPanel.removeAll();
					for (int i = 0; i < 7; i++) {
						weekLabels[i] = new JLabel(weekString[i]);
						weekLabels[i].setFont(f4);
						weekLabels[i].setForeground(Color.WHITE);
						weekPanel.add(weekLabels[i]);
						weekLabels[i].setOpaque(false);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				contentPane.validate();
				addListenerExtra();
			}
			if (command.equals("增加日程")) {

				AddSchedule addSchedule = new AddSchedule();
				addSchedule.setVisible(true);
				addSchedule.setLocationRelativeTo(null);
			}
			if (command.equals("查询日程")) {

				QueryScheduleFrame querySchedule = new QueryScheduleFrame();
				querySchedule.setVisible(true);
				querySchedule.setLocationRelativeTo(null);
			}
			if (command.equals("增加联系人")) {

				AddContact addContact = new AddContact();
				addContact.setVisible(true);
				addContact.setLocationRelativeTo(null);
			}
			if (command.equals("删除联系人")) {
				if (ContactJPanel.tempNameStr != null) {
					try {
						Object[] options = { "确定", "取消" };
						int i = JOptionPane.showOptionDialog(null,
								"确认要删除所选联系人吗？", "确认请求",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

						if (i == 0) {

							ContactManager.deleteContact("delete edit "
									+ ContactJPanel.tempNameStr);
							tableDataStr = ContactManager
									.sortContactAllInDetailTableByName(true);
							DefaultTableModel dtm = new DefaultTableModel(
									tableDataStr, tableTitleStr);

							ContactJPanel.jTable.setModel(dtm);
							// ((DefaultTableModel)
							// jTable.getModel()).removeRow(row);
						} else {
							return;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if (command.equals("发送邮件")) {
				MailJFrame mailJFrame = new MailJFrame();
				mailJFrame.setVisible(true);
				mailJFrame.setLocationRelativeTo(null);
			}
		} else if (e.getSource() instanceof JButton) {
			if (e.getSource() == previousButton) {
				try {
					occurrence = occurrence - 1;// occurrence为全局变量，用于记录向前向后所翻月数，以构造不同的DayPanel
					contentPane.remove(titlePanel);
					contentPane.remove(calendarPanel);
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
					contentPane.add(calendarPanel = AddCalendar(occurrence),
							BorderLayout.CENTER);
					addListenerExtra();
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == nextButton) {
				try {
					occurrence = occurrence + 1;
					contentPane.remove(titlePanel);
					contentPane.remove(calendarPanel);
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
					contentPane.add(calendarPanel = AddCalendar(occurrence),
							BorderLayout.CENTER);
					addListenerExtra();
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == previousYearButton) {
				try {
					occurrence = occurrence -12;
					contentPane.remove(titlePanel);
					contentPane.remove(calendarPanel);
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
					contentPane.add(calendarPanel = AddCalendar(occurrence),
							BorderLayout.CENTER);
					addListenerExtra();
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == nextYearButton) {
				try {
					occurrence = occurrence + 12;
					contentPane.remove(titlePanel);
					contentPane.remove(calendarPanel);
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
					contentPane.add(calendarPanel = AddCalendar(occurrence),
							BorderLayout.CENTER);
					addListenerExtra();
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == resetButton) {
				try {
					occurrence = 0;
					contentPane.remove(titlePanel);
					contentPane.remove(calendarPanel);
					contentPane
							.add(titlePanel = AddPanel(), BorderLayout.NORTH);
					contentPane.add(calendarPanel = AddCalendar(occurrence),
							BorderLayout.CENTER);
					addListenerExtra();
					contentPane.validate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
		}

	}

	public void refresh() {
		try {
			// occurrence1=occurrence1-1;
			contentPane.removeAll();
			contentPane.setLayout(new BorderLayout(0, 2));
			contentPane.add(AddPanel(), BorderLayout.NORTH);
			contentPane.add(calendarPanel = AddCalendar(occurrence),
					BorderLayout.CENTER);
			addListenerExtra();
			contentPane.validate();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private JPanel AddCalendar(int n1) throws IOException,
			ClassNotFoundException, InterruptedException {
		JPanel dayPanel = new DayPanel(n1);
		dayPanel.setVisible(true);
		dateLabel.setText(((DayPanel) dayPanel).getTimeStr());
		//dateLabel.setFont(f3);
		return dayPanel;
	}

	/**
	 * 用于添加按钮、设置快捷键等
	 * 
	 * @return titlePanel
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private JPanel AddPanel() throws InterruptedException,
			ClassNotFoundException, IOException {

		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2, 2));
		titlePanel.setOpaque(false);
		JLabel titleLabel1 = new JLabel("日程管理");
		// titleLabel1.setOpaque(false);
		// Font f1 = new Font("Serif", Font.BOLD, 20);
		titleLabel1.setFont(f1);
		titleLabel1.setForeground(titleColor);
		JLabel titleLabel2 = new JLabel("            Schedule");
		titleLabel2.setForeground(titleColor);
		titleLabel2.setFont(f2);
		changePanel = new JPanel();
		changePanel.setOpaque(false);
		changePanel.setLayout(null);
		changePanel.setSize(new Dimension(447, 44));
		previousButton = new JButton("");
		previousButton.setMnemonic(KeyEvent.VK_LEFT);
		previousButton.setBounds(new Rectangle(59, 0, 50, 35));

		previousButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Icon leftIcon = new ImageIcon("./icon/left.png");
		previousButton.setIcon(leftIcon);
		Icon leftIcon1 = new ImageIcon("./icon/left1.png");
		previousButton.setRolloverIcon(leftIcon1);
		previousButton.setPressedIcon(leftIcon1);
		String dateString = ((new DayPanel(0).getTimeStr()));
		dateLabel = new JLabel(dateString);
		dateLabel.setBounds(new Rectangle(111, 10, 91, 18));
		changePanel.add(previousButton, null);
		nextButton = new JButton("");
		nextButton.setMnemonic(KeyEvent.VK_RIGHT);
		nextButton.setBounds(new Rectangle(207, 0, 50, 35));
		nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Icon rightIcon = new ImageIcon("./icon/right.png");
		nextButton.setIcon(rightIcon);
		Icon rightIcon1 = new ImageIcon("./icon/right1.png");
		nextButton.setPressedIcon(rightIcon1);
		nextButton.setRolloverIcon(rightIcon1);

		changePanel.add(dateLabel, null);
		changePanel.add(nextButton, null);
		changePanel.add(getPreviousYearButton(), null);
		changePanel.add(getNextYearButton(), null);
		changePanel.add(getResetButton(), null);
		changePanel.add(getJDesktopButton(), null);
		previousButton.setToolTipText("前一月");
		nextButton.setToolTipText("后一月");
		previousYearButton.setToolTipText("前一年");
		previousYearButton.setMnemonic(KeyEvent.VK_UP);
		previousYearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		nextYearButton.setToolTipText("后一年");
		nextYearButton.setMnemonic(KeyEvent.VK_DOWN);
		nextYearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resetButton.setToolTipText("返回当前月");
		resetButton.setMnemonic(KeyEvent.VK_HOME);
		resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		titlePanel.add(titleLabel1);
		titlePanel.add(new JLabel());
		titlePanel.add(titleLabel2);
		titlePanel.add(changePanel);

		return titlePanel;
	}

	/**
	 * 添加菜单栏、菜单项和快捷键
	 * 
	 * @return
	 */
	private JMenuBar addMenuBar() {

		schedule_menu = new JMenu("日程表管理(S)");
		schedule_menu.setFont(f3);
		schedule_menu.setMnemonic('S');

		
		schedule_menu.add(edit_schedule = new JMenuItem("日程管理界面"));
		edit_schedule.setFont(f3);
		edit_schedule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.ALT_MASK));
		edit_schedule.setIcon(calendarManagerIcon);
		schedule_menu.add(add_schedule = new JMenuItem("增加日程"));
		add_schedule.setFont(f3);
		add_schedule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.ALT_MASK));
		add_schedule.setIcon(addScheduleIcon);
		schedule_menu.add(query_schedule = new JMenuItem("查询日程"));
		query_schedule.setFont(f3);
		query_schedule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.ALT_MASK));
		query_schedule.setIcon(queryMenuIcon);
		myMenuBar.add(schedule_menu);
		contact_menu = new JMenu("联系人管理(C)");
		contact_menu.setFont(f3);
		contact_menu.setMnemonic('C');

		contact_menu.add(manage_contact = new JMenuItem("联系人管理界面"));
		manage_contact.setFont(f3);
		manage_contact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.ALT_MASK));
		manage_contact.setIcon(contactManagerIcon);
		contact_menu.add(add_contact = new JMenuItem("增加联系人"));
		add_contact.setFont(f3);
		add_contact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.ALT_MASK));
		add_contact.setIcon(addContactIcon);
		contact_menu.add(delete_contact = new JMenuItem("删除联系人"));
		delete_contact.setFont(f3);
		delete_contact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.ALT_MASK));
		delete_contact.setIcon(deleteContactIcon);
		contact_menu.add(mail_menu = new JMenuItem("发送邮件"));
		mail_menu.setFont(f3);
		mail_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.ALT_MASK));
		mail_menu.setIcon(mailIcon);
		myMenuBar.add(contact_menu);
		skin_menu = new JMenu("切换皮肤(K)");
		skin_menu.setFont(f3);
		skin_menu.setMnemonic('K');
		myMenuBar.add(skin_menu);
		skin_menu
				.add(McWinLookAndFeel_menu = new JRadioButtonMenuItem("Mac风格"));
		McWinLookAndFeel_menu.setFont(f3);
		skin_menu.add(AluminiumLookAndFeel_skin = new JRadioButtonMenuItem(
				"金属质感"));
		AluminiumLookAndFeel_skin.setFont(f3);
		skin_menu.add(SmartLookAndFeel_menu = new JRadioButtonMenuItem("木质感"));
		SmartLookAndFeel_menu.setFont(f3);
		skin_menu
				.add(AeroLookAndFeel_menu = new JRadioButtonMenuItem("XP清新风格"));
		AeroLookAndFeel_menu.setFont(f3);
		McWinLookAndFeel_menu.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, InputEvent.ALT_MASK));
		AluminiumLookAndFeel_skin.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, InputEvent.ALT_MASK));
		SmartLookAndFeel_menu.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_3, InputEvent.ALT_MASK));
		AeroLookAndFeel_menu.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_4, InputEvent.ALT_MASK));
		SmartLookAndFeel_menu.addActionListener(getActionListener());
		AluminiumLookAndFeel_skin.addActionListener(getActionListener());
		McWinLookAndFeel_menu.addActionListener(getActionListener());
		AeroLookAndFeel_menu.addActionListener(getActionListener());
		ButtonGroup skinGroup = new ButtonGroup();
		skinGroup.add(McWinLookAndFeel_menu);
		skinGroup.add(AluminiumLookAndFeel_skin);
		skinGroup.add(SmartLookAndFeel_menu);
		skinGroup.add(AeroLookAndFeel_menu);
		help_menu = new JMenu("帮助(H)");
		help_menu.setMnemonic('H');
		help_menu.setFont(f3);
		myMenuBar.add(help_menu);
		help_menu.add(help_content = new JMenuItem("帮助内容"));
		help_content.setFont(f3);
		help_content.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				InputEvent.ALT_MASK));
		help_content.setIcon(helpIcon);
		help_menu.addSeparator();
		help_menu.add(help_about = new JMenuItem("关于Advanced Schedule"));
		help_about.setFont(f3);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.ALT_MASK));
		help_about.setIcon(aboutIcon);
		exit_menu = new JMenu("退出(E)");
		exit_menu.setMnemonic('E');
		exit_menu.setFont(f3);
		myMenuBar.add(exit_menu);
		exit_menu.add(exit_schedule = new JMenuItem("退出系统"));
		exit_schedule.setFont(f3);
		exit_schedule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				InputEvent.ALT_MASK));
		exit_schedule.setIcon(exitIcon);
		return myMenuBar;

	}

	private ActionListener getActionListener() {
		// TODO Auto-generated method stub
		ActionListener skinAdapter = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == McWinLookAndFeel_menu) {
					try {
						UIManager
								.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
						contentPane.repaint();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (evt.getSource() == AluminiumLookAndFeel_skin) {
					try {
						UIManager
								.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
						contentPane.repaint();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (evt.getSource() == SmartLookAndFeel_menu) {
					try {

						UIManager
								.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
						contentPane.repaint();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (evt.getSource() == AeroLookAndFeel_menu) {
					try {

						UIManager
								.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
						contentPane.repaint();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		return skinAdapter;
	}

	/**
	 * This method initializes previousYearButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPreviousYearButton() {
		if (previousYearButton == null) {
			previousYearButton = new JButton();
			previousYearButton.setIcon(leftYearIcon);
			previousYearButton.setRolloverIcon(leftYearIcon1);
			previousYearButton.setPressedIcon(leftYearIcon1);
			previousYearButton.setBounds(new Rectangle(4, 0, 50, 35));
		}
		return previousYearButton;
	}

	/**
	 * This method initializes nextYearButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getNextYearButton() {
		if (nextYearButton == null) {
			nextYearButton = new JButton();
			nextYearButton.setIcon(rightYearIcon);
			nextYearButton.setRolloverIcon(rightYearIcon1);
			nextYearButton.setPressedIcon(rightYearIcon1);
			nextYearButton.setBounds(new Rectangle(261, 0, 50, 35));
		}
		return nextYearButton;
	}

	/**
	 * This method initializes resetButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setIcon(resetIcon);
			resetButton.setBounds(new Rectangle(315, 0, 34, 33));

		}
		return resetButton;
	}

	/**
	 * This method initializes jDesktopButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDesktopButton() {
		if (jDesktopButton == null) {
			jDesktopButton = new JButton();
			jDesktopButton.setIcon(changeIcon);
			jDesktopButton.setToolTipText("迷你界面");
			jDesktopButton.setBounds(new Rectangle(388, -2, 46, 41));
			jDesktopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						
						
						DesktopJFrame newFrame=new DesktopJFrame();
						newFrame.showMe();
						if(showFrame!=null)
							showFrame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
		}
		return jDesktopButton;
	}
	public static void showFrame() throws NoSuchMethodException{
		try {
			
			
			showFrame = new JFrame("日程表管理");
			System.out.println("Show");
			//showFrame.setFont(f3);
			showFrame.setSize(1000, 700);
			showFrame.setLocationRelativeTo(null);
			showFrame.setVisible(true);
			showFrame.setResizable(false);
			showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
//				UIManager
//				.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			@SuppressWarnings("unused")
			MainMenu myMenu = new MainMenu(showFrame);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws NoSuchMethodException {
		showFrame();

	}

}