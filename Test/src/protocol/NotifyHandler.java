package protocol;

import java.util.ArrayList;
import java.util.List;

public class NotifyHandler implements Handler{
	public List<StringBuilder> execute(String[] p) {
		List<StringBuilder> list = new ArrayList<>();
		StringBuilder msg = new StringBuilder(p[1]);
		list.add(msg);
		return list;
	}
}
