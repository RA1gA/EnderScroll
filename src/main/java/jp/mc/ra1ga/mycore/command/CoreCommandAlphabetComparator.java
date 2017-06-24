package jp.mc.ra1ga.mycore.command;

import java.util.Comparator;

public class CoreCommandAlphabetComparator implements Comparator<SubCommandable> {

	@Override
	public int compare(SubCommandable o1, SubCommandable o2) {
		String c1 = o1.getCommand();
		String c2 = o2.getCommand();

		return c1.compareTo(c2);
	}

}
