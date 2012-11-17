package data;
/**
 * 本类用于播放音乐
 * @author Administrator
 *
 */
public class Media {
	 java.applet.AudioClip clip = java.applet.Applet.newAudioClip(this
			.getClass().getResource("music1.mid"));
	public void play() {	
		clip.loop();
	}
	public void stop() {	
		clip.stop();
	}
	static Media audio = new Media();
	public static void autoRun() {
		//Media audio = new Media();
		audio.play();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

	}
	public static void autostop() {
		//Media audio = new Media();
		audio.stop();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

	}
}
