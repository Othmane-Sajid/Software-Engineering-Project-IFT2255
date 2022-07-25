package controller.adv;

import java.util.Collections;
import java.util.LinkedList;

import controller.crud.CRUDOperation;
import controller.mainOperation;
import model.ConfirmPresence;
import model.Member;
import model.Service;
import model.Session;

public class ConfirmPresenceOperation extends mainOperation {
	private SessionOperation sessionOperation = new SessionOperation();

	/**
	 *
	 * Confirm the presence of a member to a session with a professional.
	 *
	 * @param sessionId
	 * @param email
	 * @param proId
	 * @param comment
	 * @return int
	 * 		   0 -> Ok
	 * 		   -1 to -4 -> Error codes
	 */
	public int confirmPresence(String sessionId, String email, String proId, String comment) {
		String serviceCode = sessionId.substring(0, 3);
		int indexService = CRUDOperation.find(new Service(serviceCode));
		
		if(indexService >= 0) {
			Service service = listService.get(indexService);
			String serviceName = service.getName();
			double cost = service.getCost();
			
			int indexSession = sessionOperation.findSession(sessionId, service.getListSessionsAffiliated());
			if(indexSession >= 0) {			
				int indexMemberInMemberList = CRUDOperation.find(new Member(email));
				if (indexMemberInMemberList >= 0) {
					String memberId = listMember.get(indexMemberInMemberList).getId();
					
					Session session = service.getListSessionsAffiliated().get(indexSession);
					LinkedList<String> participantsId = session.getParticipantsId();
					Collections.sort(participantsId);
					int indexMember = Collections.binarySearch(participantsId, memberId);
							
					if(indexMember >= 0) {
						ConfirmPresence confirmPresence = new ConfirmPresence(proId, memberId, sessionId, comment, serviceName, cost);
						listConfirmPresence.add(confirmPresence);
						return 0;
					} else {
						return -1;
					}
				} else {
					return -2;
				}				
			} else {
				return -3;
			}
		} else {
			return -4;
		}
	}	
}
