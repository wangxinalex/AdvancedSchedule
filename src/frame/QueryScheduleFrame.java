package frame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import data.CalendarManager;
import data.Schedule;
/**
 * 本类用于以窗口形式查询确定日期的日程并以表格形式显示，表格中允许以右键菜单形式增删改查，本类可以快速定位至确定日期，缺少本类并不直接影响程序的使用。
 * @author Administrator
 *
 */
public class QueryScheduleFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel functionPanel = null;
	private JPopupMenu popmenu;
	private JMenuItem deleteItem;
	private JMenuItem editItem;
	private JLabel titleLabel = null;
	private JLabel yearLabel = null;
	private JLabel monthLabel = null;
	private JLabel dayLabel = null;
	private JButton queryButton = null;
	private JButton resetButton = null;
	private JScrollPane queryScrollPane = null;
	private JTable queryTable = null;
	private JTextField yearTextField = null;	
	private JTextField monthTextField = null;	
	private JTextField dayTextField = null;
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Icon queryIcon = new ImageIcon("./icon/queryButton.png");
	private Icon clearIcon = new ImageIcon("./icon/clear.png");
	private Icon deleteIcon = new ImageIcon("./icon/delete.png"); 
	private Icon editIcon = new ImageIcon("./icon/editCalendar.png"); 
		
	private int row;
	@SuppressWarnings("unused")
	private int column;
	public Date selectedDate;
	private String dateStr;
	private String[] tableTitleStr  = { "标题", "地点", "开始时间", "结束时间", "优先级",
			"全天事件", "生日提醒" };
	/**
	 * This is the default constructor
	 */
	public QueryScheduleFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 285);
		this.setFont(f3);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setTitle("查询日程");
		Toolkit toolkit = this.getToolkit();
		Image topicon = toolkit.getImage("./icon/query.png");
		this.setIconImage(topicon);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new ImagePanel();
			jContentPane.setLayout(null);
			jContentPane.add(getFunctionPanel(), null);
			jContentPane.add(getQueryScrollPane(), null);
			jContentPane.add(getQueryButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes functionPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getFunctionPanel() {
		if (functionPanel == null) {
			dayLabel = new JLabel();
			dayLabel.setBounds(new Rectangle(375, 5, 20, 30));
			dayLabel.setText("日");
			dayLabel.setFont(f3);
			monthLabel = new JLabel();
			monthLabel.setBounds(new Rectangle(280, 5, 20, 30));
			monthLabel.setText("月");
			monthLabel.setFont(f3);
			yearLabel = new JLabel();
			yearLabel.setBounds(new Rectangle(180, 5, 20, 30));
			yearLabel.setText("年");
			yearLabel.setFont(f3);
			titleLabel = new JLabel();
			titleLabel.setBounds(new Rectangle(5, 5, 80, 30));
			titleLabel.setText("请输入日期:");
			titleLabel.setFont(f3);
			functionPanel = new JPanel();
			functionPanel.setLayout(null);
			functionPanel.setOpaque(false);
			functionPanel.setBounds(new Rectangle(0, 5, 416, 44));
			functionPanel.add(titleLabel, null);
			functionPanel.add(getYearTextField(), null);
			functionPanel.add(yearLabel, null);
			functionPanel.add(getMonthTextField(), null);
			functionPanel.add(monthLabel, null);
			functionPanel.add(getDayTextField(), null);
			functionPanel.add(dayLabel, null);
		}
		return functionPanel;
	}

	/**
	 * This method initializes yearTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getYearTextField() {
		if (yearTextField == null) {
			yearTextField = new JTextField();
			yearTextField.setBounds(new Rectangle(96, 5, 80, 30));
		}
		return yearTextField;
	}

	/**
	 * This method initializes monthTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getMonthTextField() {
		if (monthTextField == null) {
			monthTextField = new JTextField();
			monthTextField.setBounds(new Rectangle(200, 5, 80, 30));
		}
		return monthTextField;
	}

	/**
	 * This method initializes dayTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getDayTextField() {
		if (dayTextField == null) {
			dayTextField = new JTextField();
			dayTextField.setBounds(new Rectangle(300, 5, 70, 30));
		}
		return dayTextField;
	}

	/**
	 * This method initializes queryButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getQueryButton() {
		if (queryButton == null) {
			queryButton = new JButton();
			queryButton.setText("查询");
			queryButton.setBounds(new Rectangle(436, 8, 97, 39));
			queryButton.setFont(f3);
			queryButton.setIcon(queryIcon);
			queryButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// queryTable.setModel(null);
					String yearStr = yearTextField.getText();
					String monthStr = monthTextField.getText();
					String dayStr = dayTextField.getText();
					String dateStr = yearStr
							+ "/"
							+ (monthStr.length() == 1 ? "0" + monthStr
									: monthStr) + "/"
							+ (dayStr.length() == 1 ? "0" + dayStr : dayStr);
					String[][] tableDataStr = null;
					if (CalendarManager.isValid(dateStr)) {
						selectedDate = CalendarManager.getDate(dateStr);
						

						try {
//							System.out.println(dateStr);

							tableDataStr = CalendarManager
									.querySchedule("list " + dateStr);
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
					}else{
						JOptionPane.showMessageDialog(null, "参数不合法");
						return;
						
					}
					DefaultTableModel dtm = new DefaultTableModel(tableDataStr,
							tableTitleStr);
					queryTable.setModel(dtm);
				}

			});
		}
		return queryButton;
	}

	/**
	 * This method initializes resetButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setText("重置");
			resetButton.setBounds(new Rectangle(544, 8, 97, 39));
			resetButton.setFont(f3);
			resetButton.setIcon(clearIcon);
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					yearTextField.setText(null);
					monthTextField.setText(null);
					dayTextField.setText(null);
					// queryTable=new JTable();
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes queryScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getQueryScrollPane() {
		if (queryScrollPane == null) {
			queryScrollPane = new JScrollPane();
			queryScrollPane.setBounds(new Rectangle(1, 63, 690, 161));
			queryScrollPane.setViewportView(getQueryTable());
			queryScrollPane.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() == 2) {
						
						
						AddSchedule newAddSchedule = new AddSchedule();
						newAddSchedule.setVisible(true);
						newAddSchedule.setLocationRelativeTo(null);
						String yearStr = yearTextField.getText();
						String monthStr = monthTextField.getText();
						String dayStr = dayTextField.getText();
						dateStr = yearStr
								+ "/"
								+ (monthStr.length() == 1 ? "0" + monthStr
										: monthStr) + "/"
								+ (dayStr.length() == 1 ? "0" + dayStr : dayStr);
						if(dateStr!=null){
						String[] dateArr = dateStr.split("/");
						if(!CalendarManager.isValid(dateStr)){
							JOptionPane.showMessageDialog(null, "参数不合法");
							return;
						}
						newAddSchedule.setYearText(dateArr[0]);
						newAddSchedule.setMonthText(dateArr[1]);
						newAddSchedule.setDayText(dateArr[2]);}
						newAddSchedule.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent e) {
								String[][] tableDataStr = null;
								
								
								if(dateStr==null){
									JOptionPane.showMessageDialog(null, "请先选中一个日期");
									return;
								}
								if (CalendarManager.isValid(dateStr)) {
									try {
//										System.out.println(dateStr);

										tableDataStr = CalendarManager
												.querySchedule("list "
														+ dateStr);
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
								DefaultTableModel dtm = new DefaultTableModel(
										tableDataStr, tableTitleStr);
								queryTable.setModel(dtm);

							}
						});
					}

				}
			});
		}

		return queryScrollPane;
	}

	/**
	 * This method initializes queryTable
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getQueryTable() {
		if (queryTable == null) {
			queryTable = new JTable();
			queryTable.setOpaque(false);
			queryTable.setFont(f3);
			queryTable.addMouseListener(new java.awt.event.MouseAdapter() {

				public void mouseReleased(java.awt.event.MouseEvent evt) {

					try {
						if (evt.isPopupTrigger()
								&& (queryTable.getValueAt(queryTable
										.getSelectedRow(), 0) != null)) {

							(popmenu = getPopmenu()).show(evt.getComponent(),
									evt.getX(), evt.getY());
							row = queryTable.rowAtPoint(evt.getPoint());
							column=queryTable.columnAtPoint(evt.getPoint());
//							System.out.println(row + "\t" + column);

						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"You should select a row first");
					}

				}

			});
		}
		return queryTable;
	}

	public JPopupMenu getPopmenu() {
		if (popmenu == null) {
			popmenu = new JPopupMenu();
			popmenu.add(editItem = new JMenuItem("编辑该项记录"));
			editItem.setFont(f3);

			editItem.setIcon(editIcon);
			popmenu.add(deleteItem = new JMenuItem("删除该项记录"));
			deleteItem.setFont(f3);
			deleteItem.setIcon(deleteIcon);
			

			deleteItem.addMouseListener(getTableMouseAdapter());
			editItem.addMouseListener(getTableMouseAdapter());
		}
		return popmenu;
	}
/**
 * 为表格添加监听器，使表格每一行可以删除和编辑
 * @return
 */
	public MouseAdapter getTableMouseAdapter() {

		MouseAdapter tableMouseAdapter = new MouseAdapter() {

			public void mousePressed(MouseEvent evt) {
				// System.out.println("A");
				if (evt.getSource() == (deleteItem)) {
					// int row = jTable.rowAtPoint(evt.getPoint());
					// String nameStr = (String) queryTable.getValueAt(row, 0);
					try {
						Object[] options = { "确定", "取消" };
						int i = JOptionPane.showOptionDialog(null,
								"确认要删除所选日程吗？", "确认请求",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						if (i == 0) {
							CalendarManager.deleteScheduleByDateAndIndex(
									selectedDate, row);
							CalendarManager.saveRecordsToFile();
							((DefaultTableModel) queryTable.getModel())
									.removeRow(row);
							// ((DefaultTableModel)
							// jTable.getModel()).removeRow(row);
						} else {
							return;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (evt.getSource() == editItem) {
					// int row = jTable.rowAtPoint(evt.getPoint());
//					System.out.println(row);
					@SuppressWarnings("unused")
					String titleStr = (String) queryTable.getValueAt(row, 0);
					@SuppressWarnings("unused")
					String locationStr = (String) queryTable.getValueAt(row, 1);
					String startTimeStr = (String) queryTable
							.getValueAt(row, 2);
					String endTimeStr = (String) queryTable.getValueAt(row, 3);
					@SuppressWarnings("unused")
					String priorityStr = (String) queryTable.getValueAt(row, 4);
					String allDayStr = (String) queryTable.getValueAt(row, 5);
					Boolean allDayBol = allDayStr.equals("true");
					String birthdayAlarmStr = (String) queryTable.getValueAt(
							row, 6);
					Boolean birthdayAlarmBol = birthdayAlarmStr.equals("true");
					AddSchedule editSchedule = new AddSchedule();
					String[] dateArr = startTimeStr.split(" ");
					String[] dateArr1 = endTimeStr.split(" ");
					dateStr = dateArr[0];
					String[] dateArr2 = dateStr.split("/");

					String startTimeStr1 = dateArr[1];
					String endTimeStr1 = dateArr1[1];
					ArrayList<Schedule> ScheduleInList = new ArrayList<Schedule>();
					try {
						CalendarManager.loadRecordsFromFile();
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
					ScheduleInList = CalendarManager.schedulePool.get(dateStr);
					Schedule selectedItem = ScheduleInList.get(row);
					editSchedule.setYearText(dateArr2[0]);
					editSchedule.setMonthText(dateArr2[1]);
					editSchedule.setDayText(dateArr2[2]);
					editSchedule.setTitleText(selectedItem.getTitle());
					editSchedule.setAddressText(selectedItem.getLocation());
					editSchedule.setPriorityItem(selectedItem.getPriority());
					editSchedule.setStartTime(startTimeStr1);
					editSchedule.setEndTime(endTimeStr1);
					editSchedule.setAllDayAndBirthdayAlarm(allDayBol,
							birthdayAlarmBol);
					editSchedule.setVisible(true);
					editSchedule.addWindowListener(new WindowAdapter() {										
						public void windowClosing(WindowEvent e) {
							String[][] tableDataStr=null;
							try {
//								System.out.println(dateStr);

								tableDataStr = CalendarManager
										.querySchedule("list "
												+ dateStr);
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
						
						DefaultTableModel dtm = new DefaultTableModel(
								tableDataStr, tableTitleStr);
						queryTable.setModel(dtm);
							
						}
						
					});
				}
			}

		};
		return tableMouseAdapter;
	}


} 
