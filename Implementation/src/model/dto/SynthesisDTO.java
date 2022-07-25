package model.dto;

import model.Report;

import java.util.LinkedList;

public class SynthesisDTO {
    private LinkedList<Report> listReport;
    private int totalNbReports;
    private int totalNbServices;
    private int totalNbSubServices;
    private double totalCost;


    public SynthesisDTO(LinkedList<Report> listReport, int totalNbReports, int totalNbServices, int totalNbSubServices, double totalCost) {
        this.listReport = listReport;
        this.totalNbReports = totalNbReports;
        this.totalNbServices = totalNbServices;
        this.totalNbSubServices = totalNbSubServices;
        this.totalCost = totalCost;
    }


    public LinkedList<Report> getListReport() {
        return listReport;
    }
    public void setListReport(LinkedList<Report> listReport) {
        this.listReport = listReport;
    }
    public int getTotalNbReports() {
        return totalNbReports;
    }
    public void setTotalNbReports(int totalNbReports) {
        this.totalNbReports = totalNbReports;
    }
    public int getTotalNbServices() {
        return totalNbServices;
    }
    public void setTotalNbServices(int totalNbServices) {
        this.totalNbServices = totalNbServices;
    }
    public int getTotalNbSubServices() {
        return totalNbSubServices;
    }
    public void setTotalNbSubServices(int totalNbSubServices) {
        this.totalNbSubServices = totalNbSubServices;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
