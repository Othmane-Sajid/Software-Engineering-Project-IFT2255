package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import utils.Message;
import utils.DateTimeManipulation;

public class SubSession implements Comparable<SubSession>, Serializable {
	private static final long serialVersionUID = -4670793977235524226L;
	private static LinkedList<SubSession> listSubSession = new LinkedList<>();
	private String currentTime;
	private String startDate; 		// start date when member subscribes this session
	private String endDateSession;
	private double costPerSession;
	private String comment;
	private String serviceCode;
	private String sessionId;
	private String memberId;
	private String proId;


	public SubSession(String startDate, String endDateSession, double cost, String comment,
		 String serviceCode, String sessionId, String memberId, String proId) {
	 this.currentTime = generateCurrentTime();
	 this.startDate = startDate;
	 this.endDateSession = endDateSession;
	 this.costPerSession = cost;
	 this.comment = comment;
	 this.serviceCode = serviceCode;
	 this.sessionId = sessionId;
	 this.memberId = memberId;
	 this.proId = proId;
	}
	public SubSession(String currentTime, String startDate, String endDateSession,
		 double cost, String comment, String serviceCode, String sessionId,
		 String memberId, String proId) {
	 this.currentTime = currentTime;
	 this.startDate = startDate;
	 this.endDateSession = endDateSession;
	 this.costPerSession = cost;
	 this.comment = comment;
	 this.serviceCode = serviceCode;
	 this.sessionId = sessionId;
	 this.memberId = memberId;
	 this.proId = proId;
	}


	private String generateCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}


	public String getCurrentTime() {
	 return currentTime;
	}
	public String toString() {
	 String sep = Message.SEPERATOR + "\r\n";
	 String str1 = String.format("Heure actuelle: %s  Date de d'inscription: %s    "
			+ "Date de fin de session: %s  \r\n",
				currentTime, startDate, endDateSession);

	 String str2 = String.format("Frais par session: %f  Code de Service: %s   "
			+ "Code de session: %s  Commentaire: %s \r\n",
				costPerSession, serviceCode, sessionId, comment);

	 String str3 = String.format("Numéro de membre: %s     Numéro de professionnel: %s \r\n",
			 memberId, proId);

	 return sep + str1 + str2 + str3 + sep;
	}
	public String getProId() {
	 return proId;
	}
	public double getCostPerSession() {
	 return costPerSession;
	}
	public String getEndDateSession() {
	 return endDateSession;
	}
	public String getServiceCode() {
	 return serviceCode;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getStartDate() {
	 return startDate;
	}
	public static LinkedList<SubSession> getListSubSession() {
		return listSubSession;
	}
	public static void setListSubSession(LinkedList<SubSession> newList) {
		listSubSession = newList;
	}
	@Override
	public int compareTo(SubSession o) {
		String thisTimeConverted = DateTimeManipulation.convertFormat(currentTime);
		String otherTimeConverted = DateTimeManipulation.convertFormat(o.getCurrentTime());
		return thisTimeConverted.compareTo(otherTimeConverted);
	}
	public double calculateTotalCost(int dayOfWeekSession) {
		int[] diffWeeksDays = DateTimeManipulation.calculateWeeks(startDate, endDateSession);
		int nbWeeks = diffWeeksDays[0];
		double costWeeks = costPerSession * nbWeeks;		
		return costWeeks;
	}	 
}
