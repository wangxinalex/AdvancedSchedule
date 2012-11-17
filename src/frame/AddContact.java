package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import data.CalendarManager;
import data.Contact;
import data.ContactManager;
import data.Schedule;
import java.awt.SystemColor;
/**
 * 本窗口用于添加和修改联系人记录和发送邮件
 * @author Administrator
 *
 */
public class AddContact extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contactPanel = null;
	private JPanel jContentPane = null;
	private JButton saveButton = null;
	private JButton mailButton = null;
	private JLabel nameLabel = null;
	private JLabel yearLabel1 = null;
	private JLabel monthLabel1 = null;
	private JLabel dayLabel1 = null;
	private JLabel addressLabel1 = null;
	private JLabel birthdayLabel1 = null;
	private JLabel phoneLabel1 = null;
	private JLabel emailLabel1 = null;
	private JLabel websiteLabel1 = null;	
	private JComboBox genderComboBox = null;	
	private JComboBox yearComboBox = null;
	private JComboBox monthComboBox = null;
	private JComboBox dayComboBox = null;
	private JTextField nameTextField = null;
	@SuppressWarnings("unused")
	private JTextField lastnameTextField = null;
	private JTextField addressTextField = null;	
	private JTextField phoneTextField = null;	
	private JTextField emailTextField = null;	
	private JTextField websiteTextField = null;	
	private Boolean genderBol = true;
	private String nameStr = null;
	private String yearStr = null, monthStr=null,dayStr = null,dateStr = null;
	private String addressStr = null;
	private String phoneStr = null;
	private String emailStr = null;
	private String websiteStr = null;
	private String[] daysOfMonthIniStr=null;
	private String[] daysOfMonthStr = null;
	private String[] monthOfYearStr=null;
	private String[] digitOfYear=null;
	private String[] gender = { "先生", "女士" };
	private Font f3 = new Font("微软雅黑", Font.PLAIN, 12);
	private Font f4 = new Font("微软雅黑", Font.PLAIN+Font.BOLD, 12);
	private Color color1=new Color(213,228,242);
	private Color color2 =new Color(245,245,245);
	private Color color3=SystemColor.controlHighlight;  //  @jve:decl-index=0:
	private Icon saveIcon  =new ImageIcon("./icon/save.png");
	private Icon mailIcon  =new ImageIcon("./icon/mail.png");
	private Icon clearIcon = new ImageIcon("./icon/clear.png");
	/**
	 * This is the default constructor
	 */
	public AddContact() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 372);
		this.setContentPane(getJContentPane());
		this.setTitle("联系人管理");
		this.setFont(f3);
		this.setResizable(false);
		Toolkit toolkit = this.getToolkit();
		Image topicon =toolkit.getImage("./icon/addContact.png");
		this.setIconImage(topicon);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			websiteLabel1 = new JLabel();
			websiteLabel1.setText("网址");
			websiteLabel1.setForeground(color3);
			websiteLabel1.setFont(f4);
			websiteLabel1.setBounds(new Rectangle(5, 240, 90, 30));
			emailLabel1 = new JLabel();
			emailLabel1.setText("邮箱");
			emailLabel1.setForeground(color3);
			emailLabel1.setFont(f4);
			emailLabel1.setBounds(new Rectangle(5, 200, 90, 30));
			phoneLabel1 = new JLabel();
			phoneLabel1.setText("电话");
			phoneLabel1.setForeground(color3);
			phoneLabel1.setFont(f4);
			phoneLabel1.setBounds(new Rectangle(5, 160, 90, 30));
			addressLabel1 = new JLabel();
			addressLabel1.setText("地址");
			addressLabel1.setFont(f4);
			addressLabel1.setBounds(new Rectangle(5, 120, 90, 30));
			dayLabel1 = new JLabel();
			dayLabel1.setText("日");
			dayLabel1.setFont(f4);
			dayLabel1.setBounds(new Rectangle(358, 78, 18, 30));
			monthLabel1 = new JLabel();
			monthLabel1.setText("月");
			monthLabel1.setFont(f4);
			monthLabel1.setBounds(new Rectangle(267, 79, 30, 30));
			yearLabel1 = new JLabel();
			yearLabel1.setText("年");
			yearLabel1.setFont(f4);
			yearLabel1.setBounds(new Rectangle(179, 78, 26, 30));
			birthdayLabel1 = new JLabel();
			birthdayLabel1.setText("出生日期");
			birthdayLabel1.setFont(f4);
			birthdayLabel1.setBounds(new Rectangle(5, 80, 90, 30));
			nameLabel = new JLabel();
			nameLabel.setText("姓/名");
			nameLabel.setFont(f4);
			nameLabel.setBounds(new Rectangle(5, 35, 90, 30));
			jContentPane = new ImagePanel();
			jContentPane.setLayout(null);
			//jContentPane.setBorder(new TitledBorder("联系人管理"));
			jContentPane.add(getContactPanel(), null);
			jContentPane.setBackground(color1);
			jContentPane.add(getMailButton(), null);
			jContentPane.add(getSaveButton(), null);
			jContentPane.add(getResetButton(), null);
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
			saveButton.setText("保存联系人");
			saveButton.setBounds(new Rectangle(5, 0, 130, 40));
			saveButton.setFont(f3);
			saveButton.setIcon(saveIcon);
			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()"); // TODO
					// Auto-generated Event stub actionPerformed()
					try {
						saveContact();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return saveButton;
	}

	private void saveContact() throws Exception {
		// TODO Auto-generated method stub
		nameStr = nameTextField.getText();
		//lastnameStr = lastnameTextField.getText();
		//nameStr = surnameStr;
		genderBol = genderComboBox.getSelectedItem().equals("先生") ? true
				: false;
		yearStr = (String) yearComboBox.getSelectedItem();
		monthStr = ((String) monthComboBox.getSelectedItem()).length() == 1 ? "0"
				+ (String) monthComboBox.getSelectedItem()
				: (String) monthComboBox.getSelectedItem();
		dayStr = ((String) dayComboBox.getSelectedItem()).length() == 1 ? "0"
				+ (String) dayComboBox.getSelectedItem() : (String) dayComboBox
				.getSelectedItem();
		dateStr = yearStr + "/" + monthStr + "/" + dayStr;
		addressStr = addressTextField.getText().equals("")?"null":addressTextField.getText();
		phoneStr = phoneTextField.getText().equals("")?"null":phoneTextField.getText();
		emailStr = emailTextField.getText().equals("")?"null":emailTextField.getText();
		websiteStr = websiteTextField.getText().equals("")?"null":websiteTextField.getText();
		boolean isFull = ((!nameStr.equals("")));
		boolean valid = CalendarManager.isValid(dateStr);
		boolean isRepeat = false;//用于决定是否覆盖原有记录
		if (valid&&isFull){
			for(int i=0;i<ContactManager.contactList.size();i++){
				if(nameStr.equals(ContactManager.contactList.get(i).getName())){
					isRepeat=true;
					break;
				}
			}
			if(isRepeat){
				ContactManager.deleteContact("dalate contact "+nameStr);
				ContactManager.writeObj("contact.data");
			}
			Date birthdayDate = CalendarManager.getDate(dateStr);
			Contact contact = new Contact(nameStr, genderBol, emailStr,
					phoneStr, birthdayDate, addressStr, websiteStr);
			
			ContactManager.contactList.add(contact);
			ContactManager.contactPool.put(contact.getName(), contact);
			ContactManager.writeObj("contact.data");
			@SuppressWarnings("unused")
			Date startTime = CalendarManager.getDate(dateStr+" 00:00:00");
			//System.out.println(dateStr.substring(5));

			@SuppressWarnings("unused")
			Date endTime = CalendarManager.getDate(dateStr+" 23:59:59");
			GregorianCalendar d = new GregorianCalendar();
			String dateTodayStr = CalendarManager.dateFormatter2.format(d.getTime());
			if(dynamicRadioButton.isSelected()){
				for(int i = 0;i<30;i++){
					int  newDateInt = Integer.parseInt(dateTodayStr.substring(0, 4))+i;
					String newDateStr = newDateInt +"/"+dateStr.substring(5);
					Date newStartTime = CalendarManager.getDate(newDateStr+" 00:00:00");
					System.out.println(newDateStr);
					Date newEndTime = CalendarManager.getDate(newDateStr+" 23:59:59");
					Schedule newSchedule = new Schedule(nameStr+"\t生日提醒",newStartTime,newEndTime,"dining hall","high",true,true,false);
//					System.out.println(newSchedule);
					CalendarManager.addNewSchedule(newSchedule);
					CalendarManager.saveRecordsToFile();
				}
				
			}
			JOptionPane.showMessageDialog(null,"联系人添加成功");
		} else {
			JOptionPane.showMessageDialog(null, "不合法的参数", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	/**
	 * This method initializes mailButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getMailButton() {
		if (mailButton == null) {
			mailButton = new JButton();
			mailButton.setText("给该联系人发送邮件");
			mailButton.setBounds(new Rectangle(255, 0, 180, 40));
			mailButton.setFont(f3);
			mailButton.setIcon(mailIcon);
			mailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					MailJFrame javaMail=new MailJFrame();
	                javaMail.setVisible(true);
	                javaMail.setRecipient(emailTextField.getText());
	                javaMail.setLocationRelativeTo(null);
				}
			});
		}
		return mailButton;
	}

	/**
	 * This method initializes surnameTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSurnameTextField() {
		if (nameTextField == null) {
			nameTextField = new JTextField();
			nameTextField.setBounds(new Rectangle(100, 35, 223, 30));
		}
		return nameTextField;
	}
	public void setNameText(String name){
		this.nameTextField.setText(name);
	}

	/**
	 * This method initializes genderComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getGenderComboBox() {

		if (genderComboBox == null) {
			
			genderComboBox = new JComboBox(gender);
			genderComboBox.setBounds(new Rectangle(340, 35, 123, 30));

			// genderComboBox.addItem(gender);
		}
		return genderComboBox;
	}
	public void setGenderComboBox(String genderStr){
		if(genderStr.equals("先生")){
			genderComboBox.setSelectedItem(gender[0]);
		}else{
			genderComboBox.setSelectedItem(gender[1]);
		}
	}

	/**
	 * This method initializes yearComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getYearComboBox() {
		digitOfYear = new String[81];

		for (int year = 1930, i = 0; year <= 2010; year++, i++) {
			digitOfYear[i] = String.valueOf(year);
		}
		
		if (yearComboBox == null) {
			yearComboBox = new JComboBox(digitOfYear);
			yearComboBox.setBounds(new Rectangle(100, 80, 70, 30));
			yearComboBox.setSelectedItem(digitOfYear[62]);
			monthOfYearStr = new String[12];
			for (int month = 1, i = 0; month < 13; month++, i++) {
				monthOfYearStr[i] = String.valueOf(month);
			}
			daysOfMonthIniStr = new String[31];
			for (int day = 1, i = 0; day <= 31; day++, i++) {
				daysOfMonthIniStr[i] = String.valueOf(day);
			}
			if (monthComboBox == null) {
				monthComboBox = new JComboBox(monthOfYearStr);
				monthComboBox.setBounds(new Rectangle(200, 80, 60, 30));
				contactPanel.add(getMonthComboBox(), null);
			}
			if (dayComboBox == null) {
			
				dayComboBox = new JComboBox(daysOfMonthIniStr);
				dayComboBox.setBounds(new Rectangle(295, 80, 60, 30));
				contactPanel.add(dayComboBox, null);
			}
		monthComboBox.addActionListener(dayOfMonthListener) ;
		yearComboBox.addActionListener(dayOfMonthListener);

		}

		return yearComboBox;
	}
	/**
	 * 为显示年月的ComboBox添加监听器，使dayComboBox能根据不同的年月显示不同的天数，避免日期失效
	 */
	ActionListener dayOfMonthListener=new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			int dayOfMonth=getDayOfMonth();  //  @jve:decl-index=0:
			daysOfMonthStr = new String[dayOfMonth];
			for (int day = 1, i = 0; day <= dayOfMonth; day++, i++) {

				daysOfMonthStr[i] = String.valueOf(day);
			}
			contactPanel.remove(dayComboBox);
			dayComboBox = new JComboBox(daysOfMonthStr);
			dayComboBox.setBounds(new Rectangle(295, 80, 60, 30));
			contactPanel.add(dayComboBox, null);
			contactPanel.validate();
		}
	};
	private JButton resetButton = null;
	private JRadioButton dynamicRadioButton = null;
	/**
	 * This method initializes monthComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getMonthComboBox() {
		String[] monthStr = new String[12];
		for (int month = 1, i = 0; month < 13; month++, i++) {
			monthStr[i] = String.valueOf(month);
		}
		if (monthComboBox == null) {
			monthComboBox = new JComboBox(monthStr);
			monthComboBox.setBounds(new Rectangle(230, 80, 90, 30));
		}
		return monthComboBox;
	}

	public void setDateComboBox(String dateStr){
		String[] dateStrArr = dateStr.split("/");
		String yearStr = dateStrArr[0];
		String monthStr = dateStrArr[1];
		String dayStr = dateStrArr[2];
		int yearInt = Integer.parseInt(yearStr);
		int monthInt = Integer.parseInt(monthStr);
		int dayInt = Integer.parseInt(dayStr);
		yearComboBox.setSelectedItem(digitOfYear[yearInt-1930]);
		monthComboBox.setSelectedItem(monthOfYearStr[monthInt-1]);
		dayComboBox.setSelectedItem(daysOfMonthStr[dayInt-1]);
	}

	/**
	 * This method initializes addressTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getAddressTextField() {
		if (addressTextField == null) {
			addressTextField = new JTextField();
			addressTextField.setBounds(new Rectangle(100, 120, 365, 30));
		}
		return addressTextField;
	}
	public void setAddressText(String address){
		addressTextField.setText(address);
	}
	/**
	 * This method initializes phoneTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getPhoneTextField() {
		if (phoneTextField == null) {
			phoneTextField = new JTextField();
			phoneTextField.setBounds(new Rectangle(100, 160, 365, 30));
		}
		return phoneTextField;
	}
	public void setPhoneText(String phoneStr){
		phoneTextField.setText(phoneStr);
	}
	/**
	 * This method initializes emailTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getEmailTextField() {
		if (emailTextField == null) {
			emailTextField = new JTextField();
			emailTextField.setBounds(new Rectangle(100, 200, 365, 30));
		}
		return emailTextField;
	}
	public void setEmailText(String emailStr){
		emailTextField.setText(emailStr);
	}
	/**
	 * This method initializes websiteTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getWebsiteTextField() {
		if (websiteTextField == null) {
			websiteTextField = new JTextField();
			websiteTextField.setBounds(new Rectangle(100, 240, 365, 30));
		}
		return websiteTextField;
	}
	public void setWebsiteText(String websiteStr){
		websiteTextField.setText(websiteStr);
	}
	/**
	 * This method initializes contactPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getContactPanel() {
		if (contactPanel == null) {
			contactPanel = new JPanel();
			contactPanel.setOpaque(false);
			contactPanel.setLayout(null);
			contactPanel.setBorder(new TitledBorder("联系人管理"));
			contactPanel.setBounds(new Rectangle(0, 49, 485, 293));
			contactPanel.add(nameLabel, null);
			contactPanel.add(getSurnameTextField(), null);
			//contactPanel.add(getLastnameTextField(), null);
			contactPanel.add(birthdayLabel1, null);
			contactPanel.add(getYearComboBox(), null);
			contactPanel.add(getGenderComboBox(), null);
			// contactPanel.add(getMonthComboBox(), null);

			contactPanel.add(getAddressTextField(), null);
			contactPanel.add(addressLabel1, null);
			contactPanel.add(phoneLabel1, null);
			contactPanel.add(getPhoneTextField(), null);
			contactPanel.add(yearLabel1, null);
			contactPanel.add(monthLabel1, null);
			contactPanel.add(dayLabel1, null);
			contactPanel.add(emailLabel1, null);
			contactPanel.add(getWebsiteTextField(), null);
			contactPanel.add(getEmailTextField(), null);
			contactPanel.add(websiteLabel1, null);
			contactPanel.setBackground(color1);
			contactPanel.add(getDynamicRadioButton(), null);
		}
		return contactPanel;
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
			resetButton.setBounds(new Rectangle(135, 0, 120, 40));
			resetButton.setFont(f3);
			resetButton.setIcon(clearIcon);
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					nameTextField.setText(null);
					//lastnameTextField.setText(null);
					genderComboBox.setSelectedItem(gender[0]);
					yearComboBox.setSelectedItem(digitOfYear[62]);
					monthComboBox.setSelectedItem(monthOfYearStr[0]);
					dayComboBox.repaint();
					addressTextField.setText(null);
					phoneTextField.setText(null);
					emailTextField.setText(null);
					websiteTextField.setText(null);
				}
			});
		}
		return resetButton;
	}

	/**
	 * 本按钮用于决定联系人生日是否联动显示于日历上
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getDynamicRadioButton() {
		if (dynamicRadioButton == null) {
			dynamicRadioButton = new JRadioButton();
			dynamicRadioButton.setOpaque(false);
			dynamicRadioButton.setBounds(new Rectangle(377, 77, 88, 32));
			dynamicRadioButton.setBackground(color1);
			dynamicRadioButton.setText("联动显示");
			dynamicRadioButton.setFont(f4);
		}
		return dynamicRadioButton;
	}

	public static void main(String[] args) throws IOException {
		ContactManager.readObj();
		try {
			CalendarManager.loadRecordsFromFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new AddContact().setVisible(true);
	}
	/**
	 * 辅助方法，用于确定每月天数
	 * @return
	 */
	private int getDayOfMonth() {
		int yearOfDay = Integer.parseInt((String) yearComboBox
				.getSelectedItem());
		int monthOfDay = Integer.parseInt((String) monthComboBox
				.getSelectedItem());
		int dayOfMonth;
		if((yearOfDay%4==0&&yearOfDay%100!=0)||yearOfDay%400==0){
			switch (monthOfDay) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:dayOfMonth=31;break;
			case 2:dayOfMonth=29;break;
			default:dayOfMonth=30;break;
			}
		}else{
			switch (monthOfDay) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:dayOfMonth=31;break;
			case 2:dayOfMonth=28;break;
			default:dayOfMonth=30;break;
			}
		}
		return dayOfMonth;
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10" 
