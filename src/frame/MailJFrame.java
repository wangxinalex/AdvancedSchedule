package frame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import mail.JavaMail;
import data.ContactManager;
import data.GuestInfo;
import data.GuestManager;
import java.awt.Dimension;

/**
 * 本窗口利用javaMail发送电子邮件，实现了主题、多人抄送、记录用户账号和密码等功能
 * 
 * @author Administrator
 * 
 */
public class MailJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel headPanel = null;
	private JPanel textPanel = null;
	private JButton sendButton = null;
	private JButton resetButton = null;
	private JButton recordButton = null;
	private JButton deleteButton = null;
	private JButton clearGuestButton = null;
	private JComboBox selectjComboBox1 = null;
	private JComboBox selectjComboBox2 = null;
	private JComboBox recordComboBox = null;
	private JLabel idLabel = null;
	private JLabel passwdLabel = null;
	private JLabel recipientLabel = null;
	private JLabel copyLabel = null;
	private JLabel titleLabel = null;
	private JPasswordField passwdText = null;
	private JScrollPane contentScrollPane = null;
	private JTextField idText = null;
	private JTextField recipientText = null;
	private JTextField copyText = null;
	private JTextField titleText = null;	
	private JTextArea contentTextArea = null;
	
	private String id = "";
	private String passwd = "";
	private String recipient = "";
	private String recipientCopy = "";
	private String title = "";
	private String content = "";
	public String[] contactNameList;
	private String[] namesOfGuests = null;
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Icon sendIcon = new ImageIcon("./icon/send.png");
	private Icon clearIcon = new ImageIcon("./icon/clear.png");
	private Icon recordIcon = new ImageIcon("./icon/record.png");  //  @jve:decl-index=0:
	private Icon eraseIcon = new ImageIcon("./icon/erase.png");
	private Icon clearGuestIcon = new ImageIcon("./icon/clearAll.png");
	private ContactManager selectContact = new ContactManager(); 
	private ArrayList<String> guestNames = null; // @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public MailJFrame() {
		super();
		initialize();

	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		try {
			GuestManager.loadRecordsFromFile();
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
		guestNames = GuestManager.getGuestNames();
		if (guestNames != null) {
			Object[] namesObject = guestNames.toArray();
			namesOfGuests = new String[namesObject.length];
			for (int i = 0; i < namesObject.length; i++) {
				namesOfGuests[i] = (String) namesObject[i];
			}
		}
		this.setSize(625, 503);
		this.setContentPane(getJContentPane());
		this.setTitle("发送电子邮件");
		this.setFont(f3);
		this.setResizable(false);
		Toolkit toolkit = this.getToolkit();
		Image topicon = toolkit.getImage("./icon/mailTitle.png");
		this.setIconImage(topicon);
		selectjComboBox1.setSelectedItem(null);
		selectjComboBox2.setSelectedItem(null);
		recordComboBox.setSelectedItem(null);

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
			jContentPane.add(getHeadPanel(), null);
			jContentPane.add(getTextPanel(), null);
			jContentPane.add(getSendButton(), null);
			jContentPane.add(getClearGuestButton(), null);
			jContentPane.add(getDeleteButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getRecordButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes sendButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setText("发送邮件");
			sendButton.setBounds(new Rectangle(0, 0, 118, 40));
			sendButton.setFont(f3);
			sendButton.setIcon(sendIcon);
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {

					sendButtonMouseClicked(evt);

				}

			});
		}
		return sendButton;
	}

	/**
	 * This method initializes resetButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setText("清空内容");
			resetButton.setBounds(new Rectangle(118, 0, 118, 40));
			resetButton.setFont(f3);
			resetButton.setIcon(clearIcon);
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					cancelButtonMouseClicked(evt);
				}

			});
		}
		return resetButton;
	}

	/**
	 * This method initializes headPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getHeadPanel() {
		if (headPanel == null) {
			try {

				contactNameList = selectContact.getContactAllNameList();
				// System.out.println("length" + contactNameList.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < contactNameList.length; i++) {
				// System.out.println(contactNameList[i]);
			}
			titleLabel = new JLabel();
			titleLabel.setBounds(new Rectangle(20, 125, 60, 25));
			titleLabel.setText("邮件主题");
			titleLabel.setFont(f3);
			copyLabel = new JLabel();
			copyLabel.setBounds(new Rectangle(20, 90, 50, 25));
			copyLabel.setText("抄送");
			copyLabel.setFont(f3);
			recipientLabel = new JLabel();
			recipientLabel.setBounds(new Rectangle(20, 55, 50, 25));
			recipientLabel.setText("收件人");
			recipientLabel.setFont(f3);
			passwdLabel = new JLabel();
			passwdLabel.setBounds(new Rectangle(236, 19, 39, 25));
			passwdLabel.setText("密码");
			passwdLabel.setFont(f3);
			idLabel = new JLabel();
			idLabel.setBounds(new Rectangle(20, 20, 50, 25));
			idLabel.setText("学号");
			idLabel.setFont(f3);
			headPanel = new JPanel();
			headPanel.setOpaque(false);
			headPanel.setLayout(null);
			headPanel.setBounds(new Rectangle(9, 45, 600, 159));
			headPanel.setBorder(new TitledBorder("邮件头信息"));
			headPanel.add(idLabel, null);
			headPanel.add(getIdText(), null);
			headPanel.add(passwdLabel, null);
			headPanel.add(getPasswdText(), null);
			headPanel.add(recipientLabel, null);
			headPanel.add(getRecipientText(), null);
			headPanel.add(copyLabel, null);
			headPanel.add(getCopyText(), null);
			headPanel.add(titleLabel, null);
			headPanel.add(getTitleText(), null);
			headPanel.add(getSelectjComboBox1(), null);
			headPanel.add(getSelectjComboBox2(), null);
			headPanel.add(getRecordComboBox(), null);
		}
		return headPanel;
	}

	/**
	 * This method initializes idText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getIdText() {
		if (idText == null) {
			idText = new JTextField();
			idText.setBounds(new Rectangle(85, 20, 144, 25));
		}
		return idText;
	}

	/**
	 * This method initializes passwdText
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getPasswdText() {
		if (passwdText == null) {
			passwdText = new JPasswordField();
			passwdText.setBounds(new Rectangle(281, 20, 153, 25));
		}
		return passwdText;
	}

	/**
	 * This method initializes recipientText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getRecipientText() {
		if (recipientText == null) {
			recipientText = new JTextField();
			recipientText.setBounds(new Rectangle(85, 55, 350, 25));
		}
		return recipientText;
	}

	public void setRecipientText(String email) {
		this.recipientText.setText(email);
	}

	/**
	 * This method initializes copyText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getCopyText() {
		if (copyText == null) {
			copyText = new JTextField();
			copyText.setBounds(new Rectangle(85, 90, 351, 25));
		}
		return copyText;
	}

	/**
	 * This method initializes titleText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTitleText() {
		if (titleText == null) {
			titleText = new JTextField();
			titleText.setBounds(new Rectangle(85, 125, 485, 25));
		}
		return titleText;
	}

	/**
	 * This method initializes textPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTextPanel() {
		if (textPanel == null) {
			textPanel = new JPanel();
			textPanel.setLayout(null);
			textPanel.setOpaque(false);
			textPanel.setBounds(new Rectangle(10, 211, 600, 259));
			textPanel.setBorder(new TitledBorder("邮件正文"));
			textPanel.add(getContentScrollPane(), null);
		}
		return textPanel;
	}

	/**
	 * This method initializes contentScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getContentScrollPane() {
		if (contentScrollPane == null) {
			contentScrollPane = new JScrollPane();
			// contentScrollPane.setLayout(null);
			contentScrollPane.setBounds(new Rectangle(5, 20, 582, 226));
			contentScrollPane.setViewportView(getContentTextArea());
			//contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}
		return contentScrollPane;
	}

	/**
	 * This method initializes contentTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getContentTextArea() {
		if (contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setText("请输入邮件内容");
			contentTextArea.setFont(f3);
			contentTextArea.setOpaque(true);
			contentTextArea.setLineWrap(true);
			contentTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					String text = contentTextArea.getText();
					if(text.equals("请输入邮件内容")){
						contentTextArea.setText(null);
					}
				}
			});
		}
		return contentTextArea;
	}

	public void setRecipient(String email) {
		this.recipientText.setText(email);
	}

	@SuppressWarnings("deprecation")
	private void sendButtonMouseClicked(ActionEvent evt) {
		// TODO add your handling code here:
		// 点击发送按钮
		@SuppressWarnings("unused")
		String sendContent = "";
		id = idText.getText();
		passwd = passwdText.getText();
		checkGuests(id, passwd);
		JavaMail javaMail = new JavaMail();
		recipient = recipientText.getText();
		recipientCopy = copyText.getText();
		title = titleText.getText();
		content = contentTextArea.getText();
		String[] recipientArr = null;
		int numberOfRecipient = 0;
		if (!recipientCopy.equals("")) {
			recipientArr = recipientCopy.split(";");
			numberOfRecipient = recipientArr.length;
		}
		if (id.equals("") || passwd.equals("") || recipient.equals("")
				|| title.equals("") || content.equals("")) {
			JOptionPane.showMessageDialog(null, "参数不能为空", "错误",
					JOptionPane.ERROR_MESSAGE);
			return;
			// clear();
		} else if (!id.matches("[\\d]+")) {
			JOptionPane.showMessageDialog(null, "学号必须为数字", "错误",
					JOptionPane.ERROR_MESSAGE);
			return;
			// clear();
		}

		sendContent = "<b>" + content + "</b><br/>";
		if (recipientCopy.equals("")) {
			javaMail.setToAddress(recipient);
			javaMail.sendMail(id, passwd, title, content);
		} else {
			javaMail.setToAddress(recipient);
			javaMail.sendMail(id, passwd, title, content);
			for (int i = 0; i < numberOfRecipient; i++) {
				javaMail.setToAddress(recipientArr[i]);
				javaMail.sendMail(id, passwd, title, content);
			}
		}

		// System.out.println("done");
		JOptionPane.showMessageDialog(null, "发送成功！", "OK",
				JOptionPane.INFORMATION_MESSAGE);

	}
/**
 * 如果用户输入的账号为新账号，则自动提醒用户是否需要保存
 * @param id2
 * @param passwd2
 */
	private void checkGuests(String id2, String passwd2) {
		if (!id2.equals("") && !passwd2.equals("")) {
			if (!GuestManager.guestPool.containsKey(id2)) {
				Object[] options = { "确定", "取消" };
				int i = JOptionPane
						.showOptionDialog(null, "要添加账号记录吗？", "确认请求",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);

				if (i == 0) {
					GuestInfo newGuestInfo = new GuestInfo(id2, passwd2);
					GuestManager.addNewGuest(newGuestInfo);
					try {
						GuestManager.saveRecordsToFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "账号记录添加成功");
					// ((DefaultTableModel) jTable.getModel()).removeRow(row);
				} else {
					return;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "用户数据不完整", "提示",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void cancelButtonMouseClicked(ActionEvent evt) {
		this.idText.setText("");
		this.passwdText.setText("");
		this.recipientText.setText("");
		this.titleText.setText("");
		this.copyText.setText("");
		this.contentTextArea.setText("");

	}

	/**
	 * This method initializes selectjComboBox1
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSelectjComboBox1() {
		if (selectjComboBox1 == null) {
			// assert contactNameList!=null;
			selectjComboBox1 = new JComboBox(contactNameList);
			selectjComboBox1.setBounds(new Rectangle(440, 55, 130, 25));
			selectjComboBox1.addItemListener(getItemListener());
		}
		return selectjComboBox1;
	}

	private ItemListener getItemListener() {
		ItemListener itemlistener = new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				if (ie.getSource() == selectjComboBox1) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						String nameKey = ((String) selectjComboBox1
								.getSelectedItem());
						String emailStr = selectContact
								.queryContactNameToGetEmail(nameKey);
						recipientText.setText(emailStr);
					}
				} else if (ie.getSource() == selectjComboBox2) {
					if (ie.getStateChange() == ItemEvent.SELECTED) {
						String nameKey = ((String) selectjComboBox2
								.getSelectedItem());
						String emailStr = selectContact
								.queryContactNameToGetEmail(nameKey);
						String iniEmailStr = copyText.getText();
						if (!iniEmailStr.equals("")) {
							emailStr = iniEmailStr + ";" + emailStr;
						}
						copyText.setText(emailStr);
					}
				}
			}
		};
		return itemlistener;
	}

	/**
	 * This method initializes selectjComboBox2
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSelectjComboBox2() {
		if (selectjComboBox2 == null) {
			selectjComboBox2 = new JComboBox(contactNameList);
			selectjComboBox2.setBounds(new Rectangle(440, 90, 130, 25));
			selectjComboBox2.addItemListener(getItemListener());
		}
		return selectjComboBox2;
	}

	/**
	 * This method initializes recordComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getRecordComboBox() {

		if (namesOfGuests != null) {
			System.out.println("AAA");
			recordComboBox = new JComboBox(namesOfGuests);
		} else {
			recordComboBox = new JComboBox();
		}
		recordComboBox.setBounds(new Rectangle(440, 18, 130, 25));
		recordComboBox.addItemListener(getRecordListener());
		return recordComboBox;
	}

	private ItemListener getRecordListener() {
		ItemListener itemlistener = new ItemListener() {

			public void itemStateChanged(ItemEvent ie) {
				if (ie.getSource() == recordComboBox) {
					String keyName = (String) recordComboBox.getSelectedItem();
					GuestInfo guestSelected = GuestManager.guestPool
							.get(keyName);
					if (guestSelected != null) {
						String nameStr = guestSelected.getName();
						String passwdStr = guestSelected.getPassword();
						idText.setText(nameStr);
						passwdText.setText(passwdStr);
					}
				}
			}
		};
		return itemlistener;

	}

	/**
	 * 清空账号记录，保护用户隐私
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getClearGuestButton() {
		if (clearGuestButton == null) {
			clearGuestButton = new JButton();
			clearGuestButton.setText("清空使用记录");
			clearGuestButton.setBounds(new Rectangle(472, 0, 148, 40));
			clearGuestButton.setFont(f3);
			clearGuestButton.setIcon(clearGuestIcon);
			clearGuestButton.setToolTipText("清空您的使用记录");
			clearGuestButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Object[] options = { "确定", "取消" };
					int i = JOptionPane.showOptionDialog(null,
							"确实要清空所有用户账号吗？", "确认请求",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							options, options[0]);

					if (i == 0) {
						
						GuestManager.guestPool.clear();
					try {
						GuestManager.saveRecordsToFile();
						headPanel.remove(recordComboBox);
						headPanel.add(recordComboBox = new JComboBox());
								
					recordComboBox.setBounds(new Rectangle(420, 18, 110, 25));
					recordComboBox.addItemListener(getRecordListener());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}});
		}
		return clearGuestButton;
	}

	/**
	 * This method initializes recordButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRecordButton() {
		if (recordButton == null) {
			recordButton = new JButton();
			recordButton.setText("保存账号");
			recordButton.setBounds(new Rectangle(236, 0, 118, 40));
			recordButton.setFont(f3);
			recordButton.setIcon(recordIcon);
			recordButton.setToolTipText("保存您的学号和密码");
			recordButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String idStr = idText.getText();
					String passwdStr = passwdText.getText();
					if (!idStr .equals("")  && !passwdStr .equals("") ) {
						if (!GuestManager.guestPool.containsKey(idStr)) {
							Object[] options = { "确定", "取消" };
							int i = JOptionPane.showOptionDialog(null,
									"要添加账号记录吗？", "确认请求",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, options[0]);

							if (i == 0) {
								GuestInfo newGuestInfo = new GuestInfo(idStr,
										passwdStr);
								GuestManager.addNewGuest(newGuestInfo);
								try {
									GuestManager.saveRecordsToFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane
										.showMessageDialog(null, "账号记录添加成功");
								// ((DefaultTableModel)
								// jTable.getModel()).removeRow(row);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户数据不完整", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return recordButton;
	}

	/**
	 * 以shoeInputDialog形式删除指定账号
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("删除账号");
			deleteButton.setBounds(new Rectangle(354, 0, 118, 40));
			deleteButton.setFont(f3);
			deleteButton.setIcon(eraseIcon);
			deleteButton.setToolTipText("删除指定的账号");
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String nameKey = JOptionPane.showInputDialog(null, "请输入您要删除的用户账号");
					try{
						GuestManager.delete(nameKey);
						GuestManager.saveRecordsToFile();
						
					}catch(Exception ex){
						return;
					}
				}
			});
		}
		return deleteButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
