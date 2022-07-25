package controller.adv;

import controller.crud.CRUDOperation;
import controller.mainOperation;
import model.Service;
import model.Session;
import utils.ListStrConversion;

import java.util.Collections;
import java.util.LinkedList;

public class SessionOperation extends mainOperation {

    /**
     *
     * Add session in the list.
     *
     * @param session
     */
    public void addSession(Session session) {
        int index = CRUDOperation.find(new Service(session.getServiceCode()));
        LinkedList<Session> listOfSession = listService.get(index).getListSessionsAffiliated();
        listOfSession.add(session);       
    }

    /**
     *
     * Find session's index position in the list.
     *
     * @param sessionId
     * @param listOfSession
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     */
    public int findSession(String sessionId, LinkedList<Session> listOfSession) {
        Session sessionToFind = new Session("", "", sessionId, "", "", "", 0);
        Collections.sort(listOfSession);
        int index = Collections.binarySearch(listOfSession, sessionToFind);
        return index;
    }

    /**
     *
     * Update information about a session in the list of sessions of a service.
     *
     * @param sessionId
     * @param serviceCode
     * @param option
     * @param newValue
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     */
    public int updateSession(String sessionId, String serviceCode, int option, String newValue) {
        int indexService = CRUDOperation.find(new Service(serviceCode));
        if (indexService < 0) {
        	return -1;
        }
        LinkedList<Session> listOfSession = listService.get(indexService).getListSessionsAffiliated();

        int indexSession = findSession(sessionId, listOfSession);
        if(indexSession < 0) {
        	return -1;
        }
        
        Session session = listOfSession.get(indexSession);

        if (indexSession >= 0) {
            switch(option) {
                case 1:
                    session.setHour(newValue);
                    break;

                case 2:
                    session.setDayOfWeek(Integer.parseInt(newValue));
                    break;

                case 3:
                    session.setStartDate(newValue);
                    break;

                case 4:
                    session.setEndDate(newValue);
                    break;
            }
            return 0;
        }
        else
            return -1;
    }

    /**
     *
     * Delete a session from the list.
     *
     * @param sessionId
     * @param serviceCode
     * @return int
     *         0 -> Ok
     *        -1 -> Error
     */
    public int delSession(String sessionId, String serviceCode) {
        int indexService = CRUDOperation.find(new Service(serviceCode));
        if (indexService < 0) {
        	return -1; 
        }
        
        LinkedList<Session> listOfSession = listService.get(indexService).getListSessionsAffiliated();
        int indexSession = findSession(sessionId, listOfSession);

        if (indexSession >= 0) {
            listOfSession.remove(indexSession);
            return 0;
        }
        else
            return -1;
    }
}
