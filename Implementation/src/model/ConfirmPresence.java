package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.io.*;

import utils.Message;
import utils.DateTimeManipulation;

public class ConfirmPresence implements Comparable<ConfirmPresence>, Serializable {
	private static final long serialVersionUID = -7158780627112753126L;
	private static LinkedList<ConfirmPresence> confirmPresenceList = new LinkedList<ConfirmPresence>();
	private String currentTime;
	private String proId;
	private String memberId;
	private String sessionId;
	private String comment;
	private String serviceName;
	private double costPerSession;


	public ConfirmPresence(String proId, String memberId, String sessionId, String comment, 
			String serviceName, double costPerSession) {
		this.currentTime = generateCurrentTime();
		this.proId = proId;
		this.memberId = memberId;		
		this.sessionId = sessionId;
		this.comment = comment;		
		this.serviceName = serviceName;
		this.costPerSession = costPerSession; 
	}
	public ConfirmPresence(String currentTime, String proId, String memberId, String sessionId, 
			String comment, String serviceName, double costPerSession) {
		this.currentTime = currentTime;
		this.proId = proId;
		this.memberId = memberId;
		this.sessionId = sessionId;
		this.comment = comment;
		this.serviceName = serviceName;
		this.costPerSession = costPerSession; 
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
	public String getProId() {
		return proId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public double getCostPerSession() {
		return costPerSession;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String toString() {
		String sep = Message.SEPERATOR + "\r\n";
		String str1 = String.format("Heure actuelle: %s  Numéro de Professionel: %s  Numéro de membre: %s \r\n", 
        		currentTime, proId, memberId);
		String str2 = String.format("Code de session: %s  Commentaire: %s \r\n", sessionId, comment);
		return sep + str1 + str2 + sep;
	}
	@Override
	public int compareTo(ConfirmPresence o) {
		String thisTimeConverted = DateTimeManipulation.convertFormat(currentTime);
		String otherTimeConverted = DateTimeManipulation.convertFormat(o.getCurrentTime());
		return thisTimeConverted.compareTo(otherTimeConverted);
	}
	public static LinkedList<ConfirmPresence> getConfirmPresenceList() {
		return confirmPresenceList;
	}
	public static void setConfirmPresenceList(LinkedList<ConfirmPresence> newList) {
		confirmPresenceList = newList;
	}	
}
