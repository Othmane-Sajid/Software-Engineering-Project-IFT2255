package controller;

import model.*;

import java.util.LinkedList;

public abstract class mainOperation {
    protected static LinkedList<SubSession>         listSubSession      = SubSession.getListSubSession();
    protected static LinkedList<ConfirmPresence>    listConfirmPresence = ConfirmPresence.getConfirmPresenceList();
    protected static LinkedList<Professional>       listPro             = Professional.getProList();
    protected static LinkedList<Member>             listMember          = Member.getMemberList();
    protected static LinkedList<TEF>                listTEF             = TEF.getTefList();
    protected static LinkedList<Report>             listReport          = Report.getReportList();
    protected static LinkedList<Service>            listService         = Service.getServiceList();
}
