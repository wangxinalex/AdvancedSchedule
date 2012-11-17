package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import data.ContactManager;
/**
 * 本类是联系人管理界面，用于以表格形式显示、查询、排序联系人记录，并支持以右键菜单形式修改、添加、删除联系人
 * @author Administrator
 *
 */
public class ContactJPanel extends ImagePanel {

	private static final long serialVersionUID = 1L;
	private JPanel functionPanel = null;
	private JPopupMenu popmenu = null;
	private JMenuItem editItem = null;
	private JMenuItem deleteItem = null;
	private JMenuItem emailItem = null;
	private JLabel titleLabel1 = null;
	private JLabel titleLabel2 = null;
	private JLabel sortingLabel1 = null;
	private JLabel searchingLabel = null;
	private JButton sortingButton = null;
	private JButton searchingButton = null;
	private JComboBox sortingComboBox1 = null;
	private JComboBox sortingComboBox2 = null;
	private JComboBox searchingComboBox = null;
	private JScrollPane tableScrollPane = null;
	static JTable jTable = null;
	private JTextField searchingTextField = null;
	private Font f1 = new Font("YouYuan", Font.BOLD, 25);
	private Font f2 = new Font("YouYuan", Font.BOLD , 30);
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Icon deleteIcon = new ImageIcon("./icon/erase.png");
	private Icon editIcon = new ImageIcon("./icon/editContact.png");
	private Icon emailIcon = new ImageIcon("./icon/mail.png");
	private Color titleColor =SystemColor.activeCaption;
	
	private String[] tableTitleStr = { "姓名", "性别", "邮箱", "电话", "生日", "地址", "网址" };	
	private String[] conditionStr = { "姓名", "生日", "邮箱", "地址", "网址" };
	private String[] conditionStr2 = { "姓名", "邮箱", "地址", "网址", "电话" };
	private String[] orderStr = { "顺序", "倒序" };
	private String[][] tableDataStr;	
	private int row;
	@SuppressWarnings("unused")
	private int column;	
	public static String tempNameStr =null;
	
	/**
	 * This is the default constructor
	 * 
	 * @throws IOException
	 */
	public ContactJPanel() throws IOException {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @throws IOException
	 */
	void initialize() throws IOException {
		titleLabel2 = new JLabel();
		titleLabel2.setBounds(new Rectangle(201, 63, 153, 49));
		titleLabel2.setForeground(titleColor);
		titleLabel2.setFont(f2);
		titleLabel2.setText("Contacts");
		titleLabel1 = new JLabel();
		titleLabel1.setBounds(new Rectangle(1, 5, 149, 44));

		titleLabel1.setFont(f1);
		titleLabel1.setText("联系人管理");
		titleLabel1.setForeground(titleColor);
		this.setSize(1002, 700);
		this.setLayout(null);
		this.add(titleLabel1, null);
		this.add(titleLabel2, null);
		this.add(getFunctionPanel(), null);
		this.add(getTableScrollPane(), null);
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
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

	/**
	 * This method initializes functionPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getFunctionPanel() {
		if (functionPanel == null) {
			searchingLabel = new JLabel();
			searchingLabel.setBounds(new Rectangle(0, 50, 30, 30));
			searchingLabel.setText("按");
			searchingLabel.setFont(f3);
			sortingLabel1 = new JLabel();
			sortingLabel1.setText("按");
			sortingLabel1.setFont(f3);
			sortingLabel1.setBounds(new Rectangle(0, 0, 27, 33));
			functionPanel = new JPanel();
			functionPanel.setLayout(null);
			functionPanel.setOpaque(false);
			functionPanel.setBounds(new Rectangle(470, 60, 503, 100));
			functionPanel.add(sortingLabel1, null);
			functionPanel.add(getSortingComboBox1(), null);
			functionPanel.add(getSortingComboBox2(), null);
			functionPanel.add(getSortingButton(), null);
			functionPanel.add(getSearchingButton(), null);
			functionPanel.add(getSearchingComboBox(), null);
			functionPanel.add(getSearchingTextField(), null);
			functionPanel.add(searchingLabel, null);
		}
		return functionPanel;
	}

	/**
	 * This method initializes sortingComboBox1
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSortingComboBox1() {
		if (sortingComboBox1 == null) {
			sortingComboBox1 = new JComboBox(conditionStr);
			sortingComboBox1.setBounds(new Rectangle(40, 0, 100, 30));
		}
		return sortingComboBox1;
	}

	/**
	 * This method initializes sortingComboBox2
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSortingComboBox2() {
		if (sortingComboBox2 == null) {
			sortingComboBox2 = new JComboBox(orderStr);
			sortingComboBox2.setBounds(new Rectangle(155, 0, 133, 30));
		}
		return sortingComboBox2;
	}

	/**
	 * This method initializes sortingButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSortingButton() {
		if (sortingButton == null) {
			sortingButton = new JButton();
			sortingButton.setText("排序");
			sortingButton.setFont(f3);
			sortingButton.setToolTipText("按要求排序");
			sortingButton.setFont(f3);
			sortingButton.setBounds(new Rectangle(300, 0, 90, 30));
			sortingButton.addActionListener(getSortingListener());
		}
		return sortingButton;
	}
	/**
	 * This method initializes searchingComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSearchingComboBox() {
		if (searchingComboBox == null) {
			searchingComboBox = new JComboBox(conditionStr2);
			searchingComboBox.setBounds(new Rectangle(40, 50, 100, 30));
		}
		return searchingComboBox;
	}

	/**
	 * This method initializes searchingTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSearchingTextField() {
		if (searchingTextField == null) {
			searchingTextField = new JTextField();
			searchingTextField.setBounds(new Rectangle(155, 50, 133, 30));
		}
		return searchingTextField;
	}

/**
 * 本监听器用于按照不同条件不同次序排列联系人，并以表格形式显示结果
 * 
 */
	private ActionListener getSortingListener() {
		ActionListener sortingAdapter = new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == sortingButton) {
					// JOptionPane.showMessageDialog(null, "Sorting");
					boolean order = sortingComboBox2.getSelectedItem().equals(
							"顺序");
					if (sortingComboBox1.getSelectedItem().equals("生日"))

						try {
							String[][] tableData = ContactManager
									.sortContactAllInDetailTableByBirthDay(order);
							DefaultTableModel dtm = new DefaultTableModel(
									tableData, tableTitleStr);
							jTable.setModel(dtm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (sortingComboBox1.getSelectedItem().equals("姓名")) {

						try {
							String[][] tableData = ContactManager
									.sortContactAllInDetailTableByName(order);
							DefaultTableModel dtm = new DefaultTableModel(
									tableData, tableTitleStr);
							jTable.setModel(dtm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (sortingComboBox1.getSelectedItem().equals("邮箱")) {
						try {
							String[][] tableData = ContactManager
									.sortContactAllInDetailTableByEmail(order);
							DefaultTableModel dtm = new DefaultTableModel(
									tableData, tableTitleStr);
							jTable.setModel(dtm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (sortingComboBox1.getSelectedItem().equals("地址")) {
						try {
							String[][] tableData = ContactManager
									.sortContactAllInDetailTableByAddress(order);
							DefaultTableModel dtm = new DefaultTableModel(
									tableData, tableTitleStr);
							jTable.setModel(dtm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (sortingComboBox1.getSelectedItem().equals("网址")) {
						try {
							String[][] tableData = ContactManager
									.sortContactAllInDetailTableByWebsite(order);
							DefaultTableModel dtm = new DefaultTableModel(
									tableData, tableTitleStr);
							jTable.setModel(dtm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		};
		return sortingAdapter;
	}

	/**
	 * 本方法用于根据不同条件查询联系人，并以表格形式显示结果
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSearchingButton() {
		if (searchingButton == null) {
			searchingButton = new JButton();
			searchingButton.setText("查询/复原");
			searchingButton.setFont(f3);
			searchingButton.setToolTipText("按要求查询联系人，将搜索条件清空点击为复原");
			searchingButton.setBounds(new Rectangle(300, 50, 90, 30));
			searchingButton.addActionListener(getQueryingListener());
		}
		return searchingButton;
	}

	private ActionListener getQueryingListener() {
		ActionListener queryingAdapter = new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (evt.getSource() == searchingButton) {
					String conditionStr = searchingTextField.getText();
					if (searchingComboBox.getSelectedItem().equals("姓名")) {
						String[][] tableData = null;
						try {
							tableData = ContactManager
									.queryContactAllInDetailTableByName(conditionStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tableData == null) {
							JOptionPane.showMessageDialog(null, "无符合要求联系人");
							return;
						}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
					}else if (searchingComboBox.getSelectedItem().equals("邮箱")) {
						String[][] tableData = null;
						try {
							tableData = ContactManager
									.queryContactAllInDetailTableByEmail(conditionStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tableData == null) {
							JOptionPane.showMessageDialog(null, "无符合要求联系人");
							return;
						}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
					}else if (searchingComboBox.getSelectedItem().equals("地址")) {
						String[][] tableData = null;
						try {
							tableData = ContactManager
									.queryContactAllInDetailTableByAddress(conditionStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tableData == null) {
							JOptionPane.showMessageDialog(null, "无符合要求联系人");
							return;
						}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
					}else if (searchingComboBox.getSelectedItem().equals("网址")) {
						String[][] tableData = null;
						try {
							tableData = ContactManager
									.queryContactAllInDetailTableByWebsite(conditionStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tableData == null) {
							JOptionPane.showMessageDialog(null, "无符合要求联系人");
							return;
						}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
					}else if (searchingComboBox.getSelectedItem().equals("电话")) {
						String[][] tableData = null;
						try {
							tableData = ContactManager
									.queryContactAllInDetailTableByPhone(conditionStr);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (tableData == null) {
							JOptionPane.showMessageDialog(null, "无符合要求联系人");
							return;
						}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
					}
				}
			}
		};
		return queryingAdapter;
	}

	/**
	 * This method initializes tableScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 * @throws IOException
	 */
	private JScrollPane getTableScrollPane() throws IOException {
		if (tableScrollPane == null) {
			tableScrollPane = new JScrollPane();
			tableScrollPane.setBounds(new Rectangle(40, 164, 918, 450));
			tableScrollPane.setOpaque(false);
			tableScrollPane.setViewportView(getJTable());
			tableScrollPane.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent evt) {//双击面板显示添加联系人窗口，添加结果可以在表格中同步显示
					if(evt.getClickCount()==2){
						
						AddContact newAddContact=new AddContact();
						newAddContact.setVisible(true);
						newAddContact.setLocationRelativeTo(null);
						newAddContact.addWindowListener(new WindowAdapter() {										
							public void windowClosing(WindowEvent e) {
								String[][] tableData=null;
								try {
									tableData = ContactManager
									.sortContactAllInDetailTableByName(true);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						DefaultTableModel dtm = new DefaultTableModel(
								tableData, tableTitleStr);
						jTable.setModel(dtm);
								
							}
							
						});
					}
					
				}
			});
		}
		return tableScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 * @throws IOException
	 */
	private JTable getJTable() throws IOException {
		if (jTable == null) {
			tableDataStr = ContactManager
					.sortContactAllInDetailTableByName(true);
			DefaultTableModel dtm = new DefaultTableModel(tableDataStr,
					tableTitleStr);

			jTable = new JTable(dtm);
			jTable.setOpaque(false);
			jTable.setFont(f3);
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable.getColumnModel().getColumn(1).setPreferredWidth(5);
			jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
			//jTable.setEnabled(false);
			jTable.addMouseListener(new MouseAdapter() {//右击表格某一行弹出右键菜单
				public void mouseReleased(MouseEvent evt) {
					try {
						if (evt.isPopupTrigger()
								&& (jTable.getValueAt(jTable.getSelectedRow(),
										0) != null)) {

							(popmenu = getPopmenu()).show(evt.getComponent(),
									evt.getX(), evt.getY());
							row = jTable.rowAtPoint(evt.getPoint());
							column = jTable.columnAtPoint(evt.getPoint());
//							System.out.println(row + "\t" + column);

						}else if(evt.getClickCount()==1
								&& (jTable.getValueAt(jTable.getSelectedRow(),
										0) != null)){
							row = jTable.rowAtPoint(evt.getPoint());
							column = jTable.columnAtPoint(evt.getPoint());
							tempNameStr = (String) jTable.getValueAt(row, 0);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"You should select a row first");
					}

				}

			});
		}
		return jTable;
	}

	public JPanel getContactPanel() {
		return this;
	}

	public JPopupMenu getPopmenu() {
		if (popmenu == null) {
			popmenu = new JPopupMenu();
			popmenu.add(deleteItem = new JMenuItem("删除该项记录"));
			deleteItem.setFont(f3);
			deleteItem.setIcon(deleteIcon);
			popmenu.add(editItem = new JMenuItem("编辑该项记录"));
			editItem.setFont(f3);
			editItem.setIcon(editIcon);
			popmenu.add(emailItem = new JMenuItem("发送邮件"));
			emailItem.setFont(f3);
			emailItem.setIcon(emailIcon);
			deleteItem.addMouseListener(getTableMouseAdapter());
			editItem.addMouseListener(getTableMouseAdapter());
			emailItem.addMouseListener(getTableMouseAdapter());
		}
		return popmenu;
	}
/**
 * 为右键菜单中菜单项添加监听器
 * @return
 */
	public MouseAdapter getTableMouseAdapter() {

		MouseAdapter tableMouseAdapter = new MouseAdapter() {

			public void mousePressed(MouseEvent evt) {
				//System.out.println("A");
				if (evt.getSource() == (deleteItem)) {
					// int row = jTable.rowAtPoint(evt.getPoint());
					String nameStr = (String) jTable.getValueAt(row, 0);
					try {
						Object[] options = { "确定", "取消" };
						int i = JOptionPane.showOptionDialog(null,
								"确认要删除所选联系人吗？", "确认请求",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						
						
						if (i == 0) {
							ContactManager.deleteContact("delete contact "
									+ nameStr);
							((DefaultTableModel) jTable.getModel())
									.removeRow(row);
							//((DefaultTableModel) jTable.getModel()).removeRow(row);
						} else {
							return;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (evt.getSource() == editItem) {
					// int row = jTable.rowAtPoint(evt.getPoint());
					String nameStr = (String) jTable.getValueAt(row, 0);
					String genderStr = (String) jTable.getValueAt(row, 1);
					String emailStr = (String)jTable.getValueAt(row, 2);
					String phoneStr = (String)jTable.getValueAt(row, 3);
					String birthdayStr = (String) jTable.getValueAt(row, 4);
					String addressStr = (String)jTable.getValueAt(row, 5);
					String websiteStr = (String)jTable.getValueAt(row, 6);
					AddContact editContact = new AddContact();
					editContact.setLocationRelativeTo(null);
					editContact.setVisible(true);
					editContact.setNameText(nameStr);
					editContact.setGenderComboBox(genderStr);
					editContact.setDateComboBox(birthdayStr);
					editContact.setAddressText(addressStr);
					editContact.setPhoneText(phoneStr);
					editContact.setEmailText(emailStr);
					editContact.setWebsiteText(websiteStr);
					editContact.addWindowListener(new WindowAdapter() {										
						public void windowClosing(WindowEvent e) {
							String[][] tableData=null;
							try {
								tableData = ContactManager
								.sortContactAllInDetailTableByName(true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					DefaultTableModel dtm = new DefaultTableModel(
							tableData, tableTitleStr);
					jTable.setModel(dtm);
							
						}
						
					});
				} else if (evt.getSource() == emailItem) {
					String emailStr = (String) jTable.getValueAt(row, 2);
					MailJFrame mailJFrame = new MailJFrame();
					mailJFrame.setRecipientText(emailStr);
					mailJFrame.setLocationRelativeTo(null);
					mailJFrame.setVisible(true);
				}
			}

		};
		return tableMouseAdapter;
	}

	
} 
