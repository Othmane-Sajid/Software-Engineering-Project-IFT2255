package controller.adv;

import controller.crud.CRUDOperation;
import controller.mainOperation;
import model.ConfirmPresence;
import model.Member;
import model.Professional;
import model.Report;
import model.SubSession;
import model.TEF;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import model.dto.BillsDTO;
import model.dto.PaymentDTO;
import model.dto.SynthesisDTO;
import utils.DateTimeManipulation;

public class AccountingOperation extends mainOperation {

	/**
	 *
	 * Links pro's id with name.
	 *
	 * @return HashMap<String, String>
	 */
    private static HashMap<String, String> proIdConvertToName() {
    	HashMap<String, String> proId2proName = new HashMap<>();    	
    	for(Professional pro : listPro) {
    		proId2proName.put(pro.getId(), pro.getName());
    	}
    	return proId2proName;
    }

	/**
	 *
	 * Links member's id with name.
	 *
	 * @return HashMap<String, String>
	 */
	private static HashMap<String, String> memberIdConvertToName() {
    	HashMap<String, String> memberId2proName = new HashMap<>();    	
    	for(Member member : listMember) {
    		memberId2proName.put(member.getId(), member.getName());
    	}
    	return memberId2proName;
    }

	/**
	 *
	 * Creates a list of Confirm Presence objects for the current week, trough the fixed date friday.
	 *
	 * @return LinkedList<ConfirmPresence>
	 */
    private LinkedList<ConfirmPresence> getConfirmPresenceForThisWeek() {
        String thisMonday = DateTimeManipulation.getFormattedCurrentMonday();          
        String currentTime = DateTimeManipulation.getFormattedCurrentFriday();
        // we use getCurrentWeekFriday because current time must be a Friday 
        // to generate automatically TEF 
        
        LinkedList<ConfirmPresence> confirmThisWeek = new LinkedList<>();

		for (ConfirmPresence confirmPresence : listConfirmPresence) {
			// time when the member confirm his presence of the session subscribed
			String time = DateTimeManipulation.convertFormat(confirmPresence.getCurrentTime());

			if (time.compareTo(thisMonday) >= 0 && time.compareTo(currentTime) <= 0) {
				confirmThisWeek.add(confirmPresence);
			}
		}
    	return confirmThisWeek;
    }

	/**
	 *
	 * Creates a list of Subscribe Session objects for the current week, trough the fixed date friday.
	 *
	 * @return LinkedList<SubSession>
	 */
	private LinkedList<SubSession> getSubSessionForThisWeek() {
        //String thisMonday = DateTimeManipulation.getFormattedCurrentMonday();        
        String thisFriday = DateTimeManipulation.getFormattedCurrentFriday();
        		       		
        LinkedList<SubSession> subscriptionThisWeek = new LinkedList<>();
        
        for (int i = 0; i<listSubSession.size(); i++) {
        	String timeSubService = listSubSession.get(i).getCurrentTime(); 
        	String timeEndSerivce = listSubSession.get(i).getEndDateSession() + " 23:59:59";
        	
        	String timeSubServiceConverted = DateTimeManipulation.convertFormat(timeSubService);
        	String timeEndServiceConverted = DateTimeManipulation.convertFormat(timeEndSerivce);
        	
        	if(timeSubServiceConverted.compareTo(thisFriday) <= 0 && 
        			timeEndServiceConverted.compareTo(thisFriday) >= 0) {
        		subscriptionThisWeek.add(listSubSession.get(i));
        	}     	
        }       
    	return subscriptionThisWeek;
    }

	/**
	 *
	 * Generate a map of HashMap<String, LinkedList<ConfirmPresence>>.
	 *
	 * @return HashMap<String, LinkedList<ConfirmPresence>>
	 */
    private HashMap<String, LinkedList<ConfirmPresence>> generateMapTEF() {
    	LinkedList<ConfirmPresence> listThisWeek = getConfirmPresenceForThisWeek();  
    	    	
    	HashMap<String, LinkedList<ConfirmPresence>> mapTEF = new HashMap<>();
    	LinkedList<ConfirmPresence> sessionForEachPro = new LinkedList<>();
    	
    	for(ConfirmPresence confirmPresence : listThisWeek) {
    		if(mapTEF.containsKey(confirmPresence.getProId())) {
    			mapTEF.get(confirmPresence.getProId()).add(confirmPresence);
    		} else {    			
    			mapTEF.put(confirmPresence.getProId(), sessionForEachPro);
    			sessionForEachPro.add(confirmPresence);
    		}
    	}   	
    	return mapTEF;
    }

	/**
	 *
	 * Generate a map of HashMap<String, LinkedList<SubSession>>.
	 *
	 * @return HashMap<String, LinkedList<SubSession>>
	 */
	private HashMap<String, LinkedList<SubSession>> generateMapReport() {
		LinkedList<SubSession> listThisWeek = getSubSessionForThisWeek();

		HashMap<String, LinkedList<SubSession>> mapReport = new HashMap<>();
		LinkedList<SubSession> sessionForEachPro = new LinkedList<>();

		for(SubSession sub : listThisWeek) {
			if(mapReport.containsKey(sub.getProId())) {
				mapReport.get(sub.getProId()).add(sub);
			} else {
				mapReport.put(sub.getProId(), sessionForEachPro);
				sessionForEachPro.add(sub);
			}
		}
		return mapReport;
	}


	/**
	 *
	 * Creates a list of TEF reports.
	 *
	 * The generation is made with the help of the following methods:
	 * 			proIdConvertToName();
	 * 			generateMapReport();
	 *
	 * @return LinkedList<TEF>
	 */
    public LinkedList<TEF> generateTEF() {
    	HashMap<String, String> proId2proName = proIdConvertToName();
        HashMap<String, LinkedList<ConfirmPresence>> map = generateMapTEF();
       
        for(String proId : map.keySet()) {
        	String proName = proId2proName.get(proId);
        	double pay = 0.0;
        	for(ConfirmPresence confirmPresence : map.get(proId)) {
        		// Supposed that the professional's pays = 
        		// the sum of cost payed by all members who has confirmed their presence 
        		pay += confirmPresence.getCostPerSession();
        	}
        	
        	TEF tef = new TEF(proName, proId, pay);
        	listTEF.add(tef);
        }
        return listTEF;
    }

	/**
	 *
	 * Generate a synthesis from listReport and listSubSession.
	 *
	 * Calculations are made within this method (ints and double in the DTO)
	 *
	 * The generation is restarted at each execution, with the help of the following methods:
	 * 			proIdConvertToName();
	 * 			generateMapReport();
	 *
	 * @return synthesisDTO -> Data Transfer Object for:
	 *     		private LinkedList<Report> listReport;
	 *     		private int totalNbReports;
	 *     		private int totalNbServices;
	 *     		private int totalNbSubServices;
	 *     		private double totalCost;
	 */
	public SynthesisDTO generateSynthesis() {
		double pay = 0.0;
		int totalNbServices = 0;
		int totalNbSubServices = 0;
		double totalCost = 0.0;
		HashMap<String, String> proId2proName = proIdConvertToName();
        HashMap<String, LinkedList<SubSession>> map = generateMapReport();
        listReport.clear();
        listSubSession.clear();
        
        for(String proId : map.keySet()) {
        	String proName = proId2proName.get(proId);
        	HashSet<String> serviceSet = new HashSet<>();
        	
        	for(SubSession subSession : map.get(proId)) {
        		pay += subSession.getCostPerSession();
				totalNbSubServices++;
        		serviceSet.add(subSession.getServiceCode());
        	}
        	
        	Report report = new Report(proName, proId, serviceSet.size(), pay);
        	listReport.add(report);
        }

		for(Report report : listReport) {
			totalNbServices += report.getNbServices();
			totalCost += report.getPay();
		}

		SynthesisDTO synthesisDTO = new SynthesisDTO(listReport, listReport.size(), totalNbServices, totalNbSubServices, totalCost);

		return synthesisDTO;
    }

	/**
	 *
	 * Generate the payment report for the member.
	 *
	 * @param memberEmail
	 * @return BillsDTO -> Data Transfer Object for:
	 *     private Member member;
	 *     private LinkedList<ConfirmPresence> listForMember;
	 *     private HashMap<String, String> proId2proName;
	 */
    public BillsDTO showBills(String memberEmail) {
    	LinkedList<ConfirmPresence> listThisWeek = getConfirmPresenceForThisWeek();
    	LinkedList<ConfirmPresence> listForMember = new LinkedList<>();
    	HashMap<String, String> proId2proName = proIdConvertToName();
    	
    	int indexMember = CRUDOperation.find(new Member(memberEmail));

    	Member member = listMember.get(indexMember);

    	for(ConfirmPresence confirmPresence : listThisWeek) {
    		if(confirmPresence.getMemberId().equals(member.getId())) {
    			listForMember.add(confirmPresence);
    		}
    	}
    	
    	Collections.sort(listForMember);

		BillsDTO billsDTO = new BillsDTO(
				member,
				listForMember,
				proId2proName
		);

		return billsDTO;
    }

	/**
	 *
	 * Generate the payment report for the professional.
	 *
	 * @param proEmail
	 * @return PaymentDTO -> Data Transfer Object for:
	 *     		private Professional pro;
	 *     		private LinkedList<ConfirmPresence> listForPro;
	 *     		private HashMap<String, String> memberId2memberName;
	 */
	public PaymentDTO showPayment(String proEmail) {
    	LinkedList<ConfirmPresence> listThisWeek = getConfirmPresenceForThisWeek();
    	LinkedList<ConfirmPresence> listForPro = new LinkedList<>();
    	HashMap<String, String> memberId2memberName = memberIdConvertToName();
    	   	
    	int indexPro = CRUDOperation.find(new Professional(proEmail));
    	Professional pro = listPro.get(indexPro);

    	for(ConfirmPresence confirmPresence : listThisWeek) {
    		if(confirmPresence.getProId().equals(pro.getId())) {
    			listForPro.add(confirmPresence);
    		}
    	}
    	
    	Collections.sort(listForPro);

		PaymentDTO paymentDTO = new PaymentDTO(
				pro,
				listForPro,
				memberId2memberName
		);

		return paymentDTO;
    }
}
