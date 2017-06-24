package jp.mc.ra1ga.mycore.command;

import java.util.Comparator;

public class CoreCommandLengthComparator implements Comparator<SubCommandable> {

	@Override
	public int compare(SubCommandable o1, SubCommandable o2) {
		int l1 = o1.getLength();
		int l2 = o2.getLength();

		if(l1 > l2) {
			return 1;
		}else if(l1 == l2) {
			return 0;
		}else {
			return -1;
		}
	}

}
