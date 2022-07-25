package model;

import java.util.LinkedList;

public class Report {
	private static LinkedList<Report> reportList = new LinkedList<>();
    private String name;
    private String id;
    private int nbServices; // number of services (not sessions) offered during this week 
    private double pay;


    public Report(String name, String id, int nbServices, double pay) {
    	this.name = name;
    	this.id = id;
    	this.nbServices = nbServices;
    	this.pay = pay;
    }


    public int getNbServices() {
    	return nbServices;
    }
    public double getPay() {
    	return pay;
    }
    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", number of services='" + nbServices + '\'' +
                ", pay=" + pay +
                '}';
    }
	public static LinkedList<Report> getReportList() {
		return reportList;
	}
}
