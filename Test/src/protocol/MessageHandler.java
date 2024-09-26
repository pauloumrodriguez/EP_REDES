package protocol;

import java.util.List;
import java.util.ArrayList;

public class MessageHandler implements Handler {
	public List<StringBuilder> execute(String[] p) {
		List<StringBuilder> list = new ArrayList<>();
		StringBuilder msg = new StringBuilder(p[1]);
		list.add(msg);
		return list;
	}
}
