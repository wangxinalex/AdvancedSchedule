/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaMail;

/**
 *
 * @author kejun
 */
public class JavaMail {
        //处理邮件发送类
	private String toAddress;
	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@SuppressWarnings("static-access")
	public void sendMail(String id , String passwd ,String title ,String content) {
                String sender=id+"@fudan.edu.cn";
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("mail.fudan.edu.cn");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(id);//用户名
		mailInfo.setPassword(passwd);// 您的邮箱密码
		mailInfo.setFromAddress(sender);//发送地址
		mailInfo.setToAddress(this.toAddress);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendHtmlMail(mailInfo);// 发送html格式
		//System.out.println("Done!");
	}
}
