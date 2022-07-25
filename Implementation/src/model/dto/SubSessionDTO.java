package model.dto;

import model.Session;
import model.SubSession;

public class SubSessionDTO {
    private int res;
    private int nbJoinedMembersFromSession;
    private SubSession subSession;
    private double totalCostForSession;


    public SubSessionDTO(int res) {
        this.res = res;
    }
    public SubSessionDTO(int res, int nbJoinedMembersFromSession, SubSession subSession, double totalCostForSession) {
        this.res = res;
        this.nbJoinedMembersFromSession = nbJoinedMembersFromSession;
        this.subSession = subSession;
        this.totalCostForSession = totalCostForSession;
    }


    public int getRes() {
        return res;
    }
    public int getNbJoinedMembersFromSession() {
        return nbJoinedMembersFromSession;
    }
    public SubSession getSubSession() {
        return subSession;
    }
    public double getTotalCostForSession() {
        return totalCostForSession;
    }
}
