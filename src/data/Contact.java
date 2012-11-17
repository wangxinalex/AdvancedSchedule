/**
 * Contact �࣬���ڴ�ź���ϵ���йصĸ������Ժͷ���
 */
package data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ��ϵ���࣬������ϵ�˵ĸ�������
 * @author Administrator
 *
 */
public class Contact implements Serializable, Comparable<Object> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String phone;
	private Date birthday;
	private String address;
	private String website;
	private Boolean gender;
	private String pinyin;
	private String[] contactInfo = new String[7];
	

	static SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy/MM/dd");

	public Contact() {

	}
/**
 * @param name
 * @param email
 * @param phone
 * @param birthday
 * @param address
 * @param website
 */
	public Contact(String name,Boolean gender ,String email, String phone, Date birthday,
			String address, String website) {
		this.name = name;
		this.gender=gender;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.website = website;
		this.pinyin=GetFirstLetter.getFirstLetter(this.name);
	}
/**
 * @return name
 */
	public String getName() {
		return name;
	}
/**
 * set name to Contact
 * @param name
 */
	public void setName(String name) {
		this.name = name;
	}
/**
 * @return email
 */public String getGender() {
		return gender?"����":"Ůʿ";
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}
/**
 * set email to Contact
 * @param email
 */
	public void setEmail(String email) {
		this.email = email;
	}
/**
 * @return phone
 */
	public String getPhone() {
		return phone;
	}
/**
 * set phone to Contact
 * @param phone 
 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
/**
 * @return birthday
 */
	public Date getBirthday() {
		return birthday;
	}
/**
 * set birthday to Contact
 * @param birthday
 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
/**
 * @return address
 */
	public String getAddress() {
		return address;
	}
/**
 * set address to Contact
 * @param address
 */
	public void setAddress(String address) {
		this.address = address;
	}
/**
 * @return website
 */
	public String getWebsite() {
		return website;
	}
/**
 * set website to Contact
 * @param website
 */
	public void setWebsite(String website) {
		this.website = website;
	}
/**
 * over the toString method
 */
	public String toString() {
		return "����:" + this.name + "\t\t" + "�绰:" + this.phone;
	}
/**
 * overwrite the compareTo method 
 * ������ת��Ϊƴ����Ƚ���ĸ˳��
 */
	public int compareTo(Object o) {
		int i = 0;
		String compareName=GetFirstLetter.getFirstLetter(((Contact) o).getName());//���ݺ�������Ӧ��ƴ������
		System.out.println(compareName);
		do {
			//String compareName=GetFirstLetter.getFirstLetter(((Contact) o).getName());
		
			char theOrder = compareName.charAt(i);
			
			if (pinyin.charAt(i) < theOrder)
				return -1;
			else if (pinyin.charAt(i) > theOrder)
				return 1;
			else
				i++;
		} while (i < Math.min(pinyin.length(), (compareName.length())));
		return 0;

	}

/**
 * print contact in detail	
 */
	public void printindetail() {
		System.out.println("����:\t" + this.getName());
		System.out.println("�Ա�:\t" + this.getGender());
		System.out.println("����:\t" + this.getEmail());
		System.out.println("�绰:\t" + this.getPhone());
		System.out.println("����:\t" + dateFormatter2.format(this.getBirthday()));
		System.out.println("��ַ:\t" + this.getAddress());
		System.out.println("��ַ:\t" + this.getWebsite() + "\n");
	}
	/**
	 * ���ڷ��ض�ά�����е�һ��
	 * @return
	 */
	public String[] printindetailTable(){
		
		contactInfo[0] = this.getName();
		contactInfo[1] = this.getGender();
		contactInfo[2] = this.getEmail();
		contactInfo[3] = this.getPhone();
		contactInfo[4] = dateFormatter2.format(this.getBirthday());
		contactInfo[5] = this.getAddress();
		contactInfo[6] = this.getWebsite();
		
		return contactInfo;
		
	}
}
