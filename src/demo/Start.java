/**
 * @author 10302010023 王欣
 * 本类为程序总入口
 */

package demo;

/**
 * Pj1的程序入口
 * @author Administrator
 *
 */
public class Start {
	public static void main(String[] args) throws Exception {
		//Media.autorun();
		PrintCalendar d = new PrintCalendar();
		d.DisplayMonth(0);
		InfoController a = new InfoController();
//		String s="2011年1月1日";
//		System.out.println(s.substring(0,s.indexOf('年')));
		//InfoJFrame myMenu=new InfoJFrame();
		a.Command();
	}
}
