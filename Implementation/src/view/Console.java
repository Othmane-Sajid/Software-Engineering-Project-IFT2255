package view;

import controller.*;
import controller.adv.*;
import controller.crud.CRUDOperation;
import model.*;
import model.dto.BillsDTO;
import model.dto.PaymentDTO;
import model.dto.SubSessionDTO;
import model.dto.SynthesisDTO;
import model.enums.AccountState;
import model.enums.PromptType;
import utils.DateTimeManipulation;
import utils.Message;

import java.util.LinkedList;
import java.util.Scanner;

public class Console extends mainOperation {
    private MemberOperation memberOperation;
    private SessionOperation sessionOperation;
    private AccountingOperation accountingOperation;
    private SubSessionOperation subSessionOperation;
    private ConfirmPresenceOperation confirmPresenceOperation;
    private ProOperation proOperation;
    
    private static final Scanner input = new Scanner(System.in);

    public Console(MemberOperation memberOperation,
                   AccountingOperation accountingOperation,
                   SessionOperation sessionOperation,
                   SubSessionOperation subSessionOperation,
                   ConfirmPresenceOperation confirmPresenceOperation,
                   ProOperation proOperation) {
        this.memberOperation = memberOperation;
        this.sessionOperation = sessionOperation;
        this.accountingOperation = accountingOperation;
        this.subSessionOperation = subSessionOperation;
        this.confirmPresenceOperation = confirmPresenceOperation;
        this.proOperation = proOperation;
    }

    // ============================================================================
    // Global Use
    // ============================================================================
    public int promptGlobalChoice() {
        try {
            return input.nextInt();
        } catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    public int promptCaseChoose(PromptType type) {
        int option = -1;

        switch (type) {
            case MEMBER:
                System.out.println(Message.MODIFY_ASK_MEMBER);
                option = input.nextInt();
                break;
            case PROFESSIONAL:
                System.out.println();
                option = input.nextInt();
                break;
            case AGENT:
                System.out.println();
                option = input.nextInt();
                break;
            case SERVICE:
                System.out.println(Message.MODIFY_ASK_SERVICE);
                option = input.nextInt();
                break;
            case SESSION_CRUD:
                System.out.println(Message.CHOOSE_CRUD_SESSION);
                option = input.nextInt();
                break;
            case SESSION_UPD:
                System.out.println(Message.MODIFY_ASK_SESSION);
                option = input.nextInt();
                break;
        }
        return option;
    }


    // ============================================================================
    // Commons
    // ============================================================================
    private void promptShowAllMembers() {
        System.out.println(Message.SHOW_MEMBER_LIST);
        System.out.println(listMember);
    }
    private void promptShowAllPros() {
        System.out.println(Message.SHOW_PRO_LIST);
        System.out.println(listPro);
    }
    private void promptShowAllServices() {
        System.out.println(Message.SHOW_SERVICE_LIST);
        System.out.println(listService);
    }
    private void promptShowAllSubSessions() {
        System.out.println(Message.SHOW_SUB_LIST);
        System.out.println(listSubSession);
        System.out.println(Message.SEPERATOR);
    }

    private void promptAddMemberClosure(Member member) {
        System.out.println(Message.SHOW_ID);
        System.out.println(member.getId());
        System.out.println();
        promptShowAllMembers();
    }
    private void promptAddProClosure(Professional pro) {
        System.out.println(Message.SHOW_ID);
        System.out.println(pro.getId());
        System.out.println();
        promptShowAllPros();
    }
    private void promptAddServiceClosure(Service service) {
        System.out.println(Message.SHOW_CODE);
        System.out.println(service.getServiceCode());
        promptShowAllServices();
    }




    // ============================================================================
    // Members
    // ============================================================================
    public void promptAddMember() {
        Member member = promptMemberData();
        int pay 	  = promptAskMemberPay();
        member.setState((pay == 1) ? AccountState.valid : AccountState.suspended);
        CRUDOperation.add(member);
        promptAddMemberClosure(member);
    }
    private Member promptMemberData() {
        input.nextLine();
        System.out.println(Message.ADD_CLIENT_NOM);
        String newMemberName = input.nextLine();

        System.out.println(Message.ADD_CLIENT_EMAIL);
        String newMemberEmail = input.nextLine();

        System.out.println(Message.ADD_CLIENT_PROVINCE);
        String newMemberProvince = input.nextLine();

        System.out.println(Message.ADD_CLIENT_CITY);
        String newMemberCity = input.nextLine();

        System.out.println(Message.ADD_CLIENT_ZIPCODE);
        String newZipCode = input.nextLine();

        System.out.println(Message.ADD_CLIENT_ADDRESS);
        String newAddress = input.nextLine();

        return new Member(
                newMemberName,
                newMemberEmail,
                newMemberProvince,
                newMemberCity,
                newZipCode,
                newAddress,
                AccountState.suspended
        );
    }
    
    private int promptAskMemberPay() {
        System.out.println(Message.ADD_MEMBER_ASK_PAY);
        int pay = input.nextInt();
        return pay;
    }
    
    public void promptMemberAccess(String email) {
        AccountState state = memberOperation.memberAccess(email);

        if (state != null) {
            switch(state) {
                case invalid:
                    System.out.println(Message.MEMBER_INVALID);
                    break;
                case valid:
                    System.out.println(Message.MEMBER_VALID);
                    break;
                case suspended:
                    System.out.println(Message.MEMBER_SUSPENDED);
                    break;
            }
        } else {
            System.out.println(Message.MEMBER_INVALID);
            System.out.println(Message.NOT_EXISTE);
        }
    }
    
    public void promptUpdateMember(String email, int option, String newValue) throws IllegalAccessException, ClassNotFoundException {
        int res = CRUDOperation.update(new Member(email), option, newValue);
        if (res == 0) {
            System.out.println(Message.SUCCESS_MODIFY);
            promptShowAllMembers();
            System.out.println();
        }
        else
            System.out.println(Message.NOT_EXISTE);
    }
    
    public void promptDeleteMember(String email) {
        int res = CRUDOperation.delete(new Member(email));

        if (res == 0) {
            System.out.println(Message.SUCCESS_DELETE);
            promptShowAllMembers();
        }
        else {
            System.out.println(Message.NOT_EXISTE);
        }
    }


    // ============================================================================
    // Professionals
    // ============================================================================
    public void promptAddPro() {
        Professional pro = promptProData();
        CRUDOperation.add(pro);
        promptAddProClosure(pro);
    }
    
    private Professional promptProData() {
        input.nextLine();
        System.out.println(Message.ADD_CLIENT_NOM);
        String newMemberName = input.nextLine();

        System.out.println(Message.ADD_CLIENT_EMAIL);
        String newMemberEmail = input.nextLine();

        System.out.println(Message.ADD_CLIENT_PROVINCE);
        String newMemberProvince = input.nextLine();

        System.out.println(Message.ADD_CLIENT_CITY);
        String newMemberCity = input.nextLine();

        System.out.println(Message.ADD_CLIENT_ZIPCODE);
        String newZipCode = input.nextLine();

        System.out.println(Message.ADD_CLIENT_ADDRESS);
        String newAddress = input.nextLine();

        return new Professional(
                newMemberName,
                newMemberEmail,
                newMemberProvince,
                newMemberCity,
                newZipCode,
                newAddress
        );
    }
    
    public void promptUpdatePro(String email, int option, String newValue) throws IllegalAccessException, ClassNotFoundException {
        int res = CRUDOperation.update(new Professional(email), option, newValue); 
        if (res == 0) {
            System.out.println(Message.SUCCESS_MODIFY);
            promptShowAllPros();
            System.out.println();
        }
        else
            System.out.println(Message.NOT_EXISTE);
    }
    
    public void promptDeletePro(String email) {
    	int index = CRUDOperation.find(new Professional(email));
		String proId = listPro.get(index).getId();
        int res = CRUDOperation.delete(new Professional(email)); 
        if (res == 0) {
            System.out.println(Message.SUCCESS_DELETE);
            proOperation.deleteServicesByProId(proId);          
            promptShowAllPros();
            System.out.println(Message.DELETE_SERVICE_ASSOC);
            promptShowAllServices();
            System.out.println();
        }
        else {
            System.out.println(Message.NOT_EXISTE);
        }
    }


    // ============================================================================
    // Service
    // ============================================================================
    public void promptAddService() {
        Service service = promptServiceData();
        CRUDOperation.add(service);
        promptAddServiceClosure(service);
    }
    
    private Service promptServiceData() {
        input.nextLine();
        System.out.println(Message.ADD_SERVICE_NAME);
        String serviceName = input.nextLine();

        System.out.println(Message.ADD_SERVICE_START_DATE);
        String startDate = input.nextLine();

        System.out.println(Message.ADD_SERVICE_END_DATE);
        String endDate = input.nextLine();

        System.out.println(Message.ADD_SERVICE_HOUR);
        String hour = input.nextLine();

        System.out.println(Message.ADD_SERVICE_Recurrence);
        String recurr = input.nextLine();

        System.out.println(Message.ADD_MAX_CAP);
        int maxCap = input.nextInt();
        input.nextLine();

        System.out.println(Message.ADD_PROID);
        String proId = input.nextLine();

        System.out.println(Message.ADD_COST);
        double cost = input.nextDouble();
        input.nextLine();

        System.out.println(Message.ADD_COMMENT);
        String comment = input.nextLine();

        return new Service(serviceName, startDate, endDate, hour, recurr,
                maxCap, proId, cost, comment);
    }
    
    public void promptUpdateService(String code, int option, String newValue) throws IllegalAccessException, ClassNotFoundException {
        int res = CRUDOperation.update(new Service(code), option, newValue);

        if (res == 0) {
            System.out.println(Message.SUCCESS_MODIFY);
            promptShowAllServices();
            System.out.println();
        }
        else
            System.out.println(Message.SERVICE_NOT_EXISTE);

    }
    
    public void promptDeleteService(String code) {
        int res = CRUDOperation.delete(new Service(code));

        if (res == 0) {
            System.out.println(Message.DELETE_SERVCE_OK);
            promptShowAllServices();
            System.out.println();
        }
        else
            System.out.println(Message.SERVICE_NOT_EXISTE);
    }

    // ============================================================================
    // Sessions
    // ============================================================================
    public void promptAddSession(String serviceCode) {
        input.nextLine();

        System.out.println(Message.ADD_PROID);
        String newProId = input.nextLine();
        System.out.println(Message.ADD_START_DATE);
        String newStartDate = input.nextLine();
        System.out.println(Message.ADD_END_DATE);
        String newEndDate = input.nextLine();
        System.out.println(Message.ADD_DAY);
        String newDayOfWeek = input.nextLine();
        System.out.println(Message.ADD_HOUR);
        String newHour = input.nextLine();

        Session session = new Session(
                serviceCode,
                newProId,
                Integer.parseInt(newDayOfWeek),
                newHour,
                newStartDate,
                newEndDate
        );

        sessionOperation.addSession(session);
        promptShowAllServices();
    }
    
    public void promptUpdateSession(String serviceCode, String sessionCode, int option, String newValue) {
        int res = sessionOperation.updateSession(sessionCode, serviceCode, option, newValue);

        if (res == 0) {
            System.out.println(Message.SUCCESS_MODIFY);
            System.out.println();
            promptShowAllServices();
        }
        else
            System.out.println(Message.SESSION_NOT_EXISTE);
    }
    
    public void promptDeleteSession(String serviceCode, String sessionCode) {
        int res = sessionOperation.delSession(sessionCode, serviceCode);

        if (res == 0) {
            System.out.println(Message.DELETE_SESSION);
            System.out.println();
            promptShowAllServices();
        }
        else
            System.out.println(Message.SESSION_NOT_EXISTE);
    }


    // ============================================================================
    // Subscribe Session
    // ============================================================================
    public void promptShowSubSessionByProId(String proId) {
        LinkedList<SubSession> proSession = subSessionOperation.getSubSessionListByProId(proId);
        System.out.println(proSession);
        System.out.println(Message.SEPERATOR);
    }
    
    public void promptSubSession(String email) {   
    	System.out.println(Message.PRESS_ENTER); 
    	input.nextLine();   	
        System.out.println(Message.CHOOSE_SERVICE);
        String serviceChosen = input.nextLine();
        System.out.println(Message.CHOOSE_SESSION);
        String sessionChosen = input.nextLine();
        System.out.println(Message.ENTER_COMMENT);
        String comment = input.nextLine();

        SubSessionDTO subSessionDTO = subSessionOperation.subSession(
                email,
                serviceChosen,
                sessionChosen,
                comment,
                DateTimeManipulation.generateCurrentTime(false)
        );

        int res = subSessionDTO.getRes();
        int nbJoinedMembersFromSession = subSessionDTO.getNbJoinedMembersFromSession();
        SubSession subSession = subSessionDTO.getSubSession();
        double totalCostForSession = subSessionDTO.getTotalCostForSession();

        if (res == 1) {
            System.out.println("Le nombre des participants de session choisie:   " + nbJoinedMembersFromSession);
            System.out.println();

            System.out.printf("Le montant à payer pour la session %s du servcie %s: %f",
                    subSession.getServiceCode(),
                    subSession.getSessionId(),
                    totalCostForSession
            );
            System.out.println();
            promptShowAllSubSessions();
            System.out.println(Message.SEPERATOR);
        } else {
            switch(res) {
                case -1:
                    System.out.println(Message.MEMBER_INVALID);
                    break;
                case 0:
                    System.out.println(Message.MEMBER_SUSPENDED);
                    break;
                case -2:
                    System.out.println(Message.NOT_EXISTE);
                    break;
                case -3:
                    System.out.println(Message.SERVICE_NOT_EXISTE);
                    break;
                case -4:
                    System.out.println(Message.SESSION_NOT_EXISTE);
                    break;
                case -5:
                    System.out.println(Message.MAX_CAP);
                    break;
            }
        }
    }

    // ============================================================================
    // Presence Confirmation
    // ============================================================================
    public void promptConfirmPresence(String sessionIdForOp2, String memberEmailForOp2, String proIdForOp2) {
        System.out.println(Message.ADD_COMMENT);
        String comment = input.nextLine();

        int res = confirmPresenceOperation.confirmPresence(sessionIdForOp2, memberEmailForOp2, proIdForOp2, comment);

        switch (res) {
            case 0:
                System.out.println(Message.SEPERATOR);
                System.out.println(Message.CONFIRMATION_PRESENCE);
                break;
            case -1:
                System.out.println(Message.REFUSE_CONFIRMATION);
                break;
            case -2:
                System.out.println(Message.NOT_EXISTE);
                break;
            case -3:
                System.out.println(Message.SESSION_CODE_INVALID);
                break;
            case -4:
                System.out.println(Message.SESSION_CODE_INVALID);
                break;
        }
    }


    // ============================================================================
    // Accounting
    // ============================================================================
    public void promptShowTEF() {
        LinkedList<TEF> listTEF = accountingOperation.generateTEF();
        System.out.println(Message.SHOW_TEF);
        for(TEF tef : listTEF) {
            System.out.println(tef);
            System.out.println(Message.SEPERATOR);
        }
    }
    
    public void promptShowSynthesis() {
        SynthesisDTO synthesisDTO = accountingOperation.generateSynthesis();

        System.out.println(Message.SHOW_SYNTHESIS);
        for(Report report : synthesisDTO.getListReport()) {
            System.out.println(report);
            System.out.println(Message.SEPERATOR);
        }
        System.out.printf("Le nombre total de professionnels qui ont fourni des services: %d \r\n", synthesisDTO.getTotalNbReports());
        System.out.printf("Le nombre total de services: %d \r\n", synthesisDTO.getTotalNbServices());
        System.out.printf("Le total des frais: %f \r\n", synthesisDTO.getTotalCost());
        System.out.println(Message.SEPERATOR);
    }
    
    public void promptShowBills(String memberEmail) {
        BillsDTO billsDTO = accountingOperation.showBills(memberEmail);

        Member member = billsDTO.getMember();
        String memberId = member.getId();
        String memberName = member.getName();
        String memberAddress = member.getAddress();
        String memberCity = member.getCity();
        String memberProvince = member.getProvince();
        String memberZipCode = member.getZipCode();

        System.out.println(Message.SEPERATOR);
        System.out.printf("Nom du membre: %s \r\n", memberName);
        System.out.printf("Numéro du member: %s \r\n", memberId);
        System.out.printf("Adresse du member: %s \r\n", memberAddress);
        System.out.printf("Ville du membre: %s \r\n", memberCity);
        System.out.printf("Province du member: %s \r\n", memberProvince);
        System.out.printf("Code postal du member: %s \r\n", memberZipCode);
        System.out.println();
        System.out.println("La liste des services qui ont été fournis: ");

        for (ConfirmPresence confirmPresence : billsDTO.getListForMember()) {
            System.out.printf("Date du service: %s \r\n",
                    DateTimeManipulation.convertFormatDate(confirmPresence.getCurrentTime()));
            String proName = billsDTO.getProId2proName().get(confirmPresence.getProId());
            System.out.printf("Nom du professionnel: %s \r\n", proName);
            System.out.printf("Nom du service: %s \r\n", confirmPresence.getServiceName());
            System.out.printf("Montant à payer chaque séance: %f \r\n", confirmPresence.getCostPerSession());
            System.out.println();
        }
        System.out.println(Message.SEPERATOR);
    }
    
    public void promptShowPayment(String proEmail) {
        PaymentDTO paymentDTO = accountingOperation.showPayment(proEmail);

        Professional pro = paymentDTO.getPro();
        String proId = pro.getId();
        String proName = pro.getName();
        String proAddress = pro.getAddress();
        String proCity = pro.getCity();
        String proProvince = pro.getProvince();
        String proZipCode = pro.getZipCode();

        System.out.println(Message.SEPERATOR);
        System.out.printf("Nom du professionnel: %s \r\n", proName);
        System.out.printf("Numéro du professionnel: %s \r\n", proId);
        System.out.printf("Adresse du professionnel: %s \r\n", proAddress);
        System.out.printf("Ville du professionnel: %s \r\n", proCity);
        System.out.printf("Province du professionnel: %s \r\n", proProvince);
        System.out.printf("Code postal du professionnel: %s \r\n", proZipCode);
        System.out.println();
        System.out.println("La liste des services que le professional a fournis: ");

        for (ConfirmPresence confirmPresence : paymentDTO.getListForPro()) {
            System.out.printf("Date du service: %s \r\n",
                    DateTimeManipulation.convertFormatDate(confirmPresence.getCurrentTime()));

            System.out.printf("Date et heure à laquelle les données étaient reçues par l'ordinateur: %s \r\n",
                    confirmPresence.getCurrentTime());

            String memberId = confirmPresence.getMemberId();
            String memberName = paymentDTO.getMemberId2memberName().get(memberId);
            System.out.printf("Nom du membre: %s \r\n", memberName);

            System.out.printf("Numéro du membre: %s \r\n", memberId);

            System.out.printf("Code de la séance: %s  \r\n", confirmPresence.getSessionId());

            System.out.printf("Montant à payer chaque séance: %f \r\n",
                    confirmPresence.getCostPerSession());

            System.out.println();
        }
        System.out.println(Message.SEPERATOR);
    }
}
