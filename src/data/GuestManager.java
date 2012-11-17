package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
/**
 * 本类用于从文件中读写用户账号信息
 * @author Administrator
 *
 */
public class GuestManager {
	static ArrayList<GuestInfo> guestList = new ArrayList<GuestInfo>();
	public static HashMap<String, GuestInfo> guestPool = new HashMap<String, GuestInfo>();
	public static ArrayList<String> guestNames;

	public static void loadRecordsFromFile() throws IOException,
			ClassNotFoundException, InterruptedException {
		File recorder = new File("Guest.data");
		if (!recorder.exists()) {
			boolean exists = recorder.createNewFile();
			if (!exists) {
				System.out
						.println("The file Schedule.data has already existed before created\n"
								+ "or the step of cteating Schedule.data has failed.\n"
								+ "Enter any key and MyCalendar will exit.");
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
				System.exit(0);
			}
		}
		FileInputStream input = new FileInputStream(recorder);
		deSerialize(input);
	}

	public static void saveRecordsToFile() throws IOException {
		File recorder = new File("Guest.data");
		FileOutputStream output = new FileOutputStream(recorder);
		Serialize(output);
	}

	@SuppressWarnings("unchecked")
	public static void deSerialize(FileInputStream fReader) throws IOException,
			ClassNotFoundException {
		try {
			ObjectInputStream in = new ObjectInputStream(fReader);
			guestPool = (HashMap<String, GuestInfo>) in.readObject();
			System.out.println(guestPool);
		} catch (EOFException e) {
		}
	}

	public static void Serialize(FileOutputStream fWriter) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(fWriter);
		out.writeObject(guestPool);
	}

	public static void addNewGuest(GuestInfo s) {
		String key = s.getName();
		if (guestPool == null) {
			System.out
					.println("[Error]schedulePool is null, please make sure your loadRecordsFromFile is implemented and if it is the first time "
							+ "you run the programe you should new a HashMap");
			return;
		}
		GuestInfo target = guestPool.get(key);
		if (target == null) {

			guestPool.put(key, s);
		}
	}

	public static ArrayList<String> getGuestNames() {
		guestNames = new ArrayList<String>();
		Set<String> keys = guestPool.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			guestNames.add(iter.next());
		}

		if (guestNames == null) {
			JOptionPane.showMessageDialog(null, "目前无用户记录");
		}
		return guestNames;

	}

	public static void delete(String name) {
		if (guestPool.containsKey(name)) {
			guestPool.remove(name);
			JOptionPane.showMessageDialog(null, "账号已删除");
		} else {
			JOptionPane.showMessageDialog(null, "该账号不存在");
		}
	}

}
