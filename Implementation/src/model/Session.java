package model;

import java.io.Serializable;
import java.util.LinkedList;
import utils.Message;

public class Session implements Comparable<Session>, Serializable {
	private static final long serialVersionUID = -6420757850402118909L;
	private static int lastAssignedId = 1000;
	private String proId;
    private String sessionId;
    private String hour;
    private int dayOfWeek;
    private String startDate;
    private String endDate;
    private LinkedList<String> participantsId;
    private String serviceCode;


    public Session(String sessionId) {
    	this.sessionId = sessionId; 
    }
    public Session(String serviceCode, String proId, int dayOfWeek, String hour, 
    		String startDate, String endDate) {    
    	this.serviceCode = serviceCode;
    	this.proId = proId; 
        this.sessionId = serviceCode + utils.UID.generateUID(4);
        this.hour = hour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfWeek = dayOfWeek;
        this.participantsId = new LinkedList<String>();
    }
    public Session(String serviceCode, String proId, String code,  String hour, 
    		String startDate, String endDate, int dayOfWeek) {
    	this.serviceCode = serviceCode;
    	this.proId = proId; 
    	this.sessionId = code;
        this.hour = hour;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfWeek = dayOfWeek;
        this.participantsId = new LinkedList<String>();
    }


    public static int getLastAssignedId() {
        return lastAssignedId;
    }
    public String getProId() {
        return proId;
    }
    public String getServiceCode() {
        return serviceCode;
    }
    public String getSessionId() {
        return sessionId;
    }
    public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public LinkedList<String> getParticipantsId() {
		return participantsId;
	}
    public String toString() {
    	String sep = Message.SEPERATOR + "\r\n";   	
        return sep + String.format("Session ID: %s    Numéro de Pro: %s   Jour de la Semaine: %d   Heure: %s    Date de début: %s   Date de fin: %s \r\n", 
        		sessionId, proId, dayOfWeek, hour, startDate, endDate) + sep;
    }
	@Override
	public int compareTo(Session o) {
		return sessionId.compareTo(o.getSessionId());
	}
}
