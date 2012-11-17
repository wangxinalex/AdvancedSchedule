package data;

import java.io.Serializable;
/**
 * 本类用于保存用户账号数据
 * @author Administrator
 *
 */
public class GuestInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;

	public GuestInfo(String name, String password) {
		this.name = name;
		this.password = password;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "GuestInfo [name=" + name + ", password=" + password + "]";
	}
}
