package protocol;

import java.util.List;
import java.util.ArrayList;

public class PackHandler implements Handler{
	public List<StringBuilder> execute(String[] p){
		List<StringBuilder> list = new ArrayList<>();
		for(int i = 1; i < p.length; i++) {
			StringBuilder msg = new StringBuilder(p[i]);
			list.add(msg);
		}
		
		return list;
	}
}
