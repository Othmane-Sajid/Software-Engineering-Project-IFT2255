package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import utils.Message;
import utils.ListStrConversion;

public class Service implements Comparable<Service>, Serializable {
	private static final long serialVersionUID = -5207696892320204805L;
	private static LinkedList<Service> serviceList = new LinkedList<>();
	private String name; 
	private String serviceCode;
    private String currentTime;
    private String startDate;
    private String endDate;
    private String givenHour;  
    private LinkedList<Integer> weeklyReccurence;
    private int maxCap;
    private String proId;
    private double cost;
    private String comment;
    private LinkedList<Session> listSessionsAffiliated;


    public Service(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    public Service(String name, String startDate, String endDate, String givenHour,
    		String recurr, int maxCap, String proId, double cost, String comment) {
    	this.name = name; 
        this.serviceCode = utils.UID.generateUID(3);
        this.currentTime = generateCurrentTime();
        this.startDate = startDate;
        this.endDate = endDate;
        this.givenHour = givenHour;
        this.weeklyReccurence = generateRecurList(recurr); 
        this.maxCap = maxCap;
        this.proId = proId;
        this.cost = cost;
        this.comment = comment;  
        this.listSessionsAffiliated = generateSessions(serviceCode, proId, 
        		startDate, endDate, weeklyReccurence, givenHour);
    }
    public Service(String name, String code, String startDate, String endDate, String givenHour,
    		String recurr, int maxCap, String proId, double cost, String comment) {
    	this.name = name; 
    	this.serviceCode = code;   	
        this.currentTime = generateCurrentTime();
        this.startDate = startDate;
        this.endDate = endDate;
        this.givenHour = givenHour;
        this.weeklyReccurence = generateRecurList(recurr); 
        this.maxCap = maxCap;
        this.proId = proId;
        this.cost = cost;
        this.comment = comment;  
        this.listSessionsAffiliated = generateSessions(serviceCode, proId, 
        		startDate, endDate, weeklyReccurence, givenHour);
    }


    private String generateCurrentTime() {
    	SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf.format(date); 
    }
    private LinkedList<Integer> generateRecurList(String str) {
    	return ListStrConversion.listOfInt(str); 
    }


    public LinkedList<Session> generateSessions(String serviceCode, String proId,
                                                String startDate, String endDate, LinkedList<Integer> weeklyReccurence, String givenHour) {
        LinkedList<Session> listSessions = new LinkedList<Session>();
        for(int i = 0; i < weeklyReccurence.size(); i++) {
            Session session = new Session(serviceCode, proId, weeklyReccurence.get(i), givenHour, startDate, endDate);
            listSessions.add(session);
        }
        return listSessions;
    }
    public String getName() {
    	return name;
    }
    public String getServiceCode() {
    	return serviceCode;
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
    public String getGivenHour() {
        return givenHour;
    }
    public void setGivenHour(String givenHour) {
        this.givenHour = givenHour;
    }
    public LinkedList<Integer> getWeeklyReccurence() {
        return weeklyReccurence;
    }
    public int getMaxCap() {
        return maxCap;
    }
    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }
    public String getProId() {
        return proId;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
	public LinkedList<Session> getListSessionsAffiliated() {
		return listSessionsAffiliated;
	}
	public static LinkedList<Service> getServiceList() {
		return serviceList;
	}
	public static void setServiceList(LinkedList<Service> newList) {
		serviceList = newList;
	}
    @Override
	public String toString() {
		String sep = Message.SEPERATOR + "\r\n";
		String str1 = String.format("Nom du service: %s  Heure actuelle: %s  Code de Service: %s    Heure initiale: %s    Date de début initiale: %s   Date de fin initiale: %s \r\n", 
        		name, currentTime, serviceCode, givenHour, startDate, endDate);
		String str2 = String.format("Capacité: %d   Numéro de professionnel: %s   Frais:  %f   Comment: %s \r\nSessions:",  
        		maxCap, proId, cost, comment);
        String str3 = ListStrConversion.listSession2str(listSessionsAffiliated);
        return sep + str1 + str2 + str3 + sep;
    }
	@Override
	public int compareTo(Service o) {	
		return serviceCode.compareTo(o.getServiceCode());
	}

}
