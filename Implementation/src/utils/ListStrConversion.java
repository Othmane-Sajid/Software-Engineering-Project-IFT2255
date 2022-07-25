package utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Session;

public class ListStrConversion {

	/**
	 *
	 * Used to generate list of recurrences.
	 *
	 * @param str
	 * @return LinkedList<Integer>
	 */
	public static LinkedList<Integer> listOfInt(String str) {
		List<String> strList = Arrays.asList(str.split(","));
		LinkedList<Integer> intList = new LinkedList<>();
		for(int i = 0; i<strList.size(); i++) {
			intList.add(Integer.valueOf(strList.get(i)));
		}
		return intList;
	}

	/**
	 *
	 * Appends all sessions into a single chained String.
	 *
	 * @param allSessions
	 * @return String
	 */
	public static String listSession2str(LinkedList<Session> allSessions) {
		StringBuilder str = new StringBuilder();
		for(Session session : allSessions) {
			str.append(session.toString());
		}
		return str.toString();
	}
}
