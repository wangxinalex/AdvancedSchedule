/**
 * @author 10302010023 ����
 * ����Ϊ���������
 */

package demo;

/**
 * Pj1�ĳ������
 * @author Administrator
 *
 */
public class Start {
	public static void main(String[] args) throws Exception {
		//Media.autorun();
		PrintCalendar d = new PrintCalendar();
		d.DisplayMonth(0);
		InfoController a = new InfoController();
//		String s="2011��1��1��";
//		System.out.println(s.substring(0,s.indexOf('��')));
		//InfoJFrame myMenu=new InfoJFrame();
		a.Command();
	}
}
