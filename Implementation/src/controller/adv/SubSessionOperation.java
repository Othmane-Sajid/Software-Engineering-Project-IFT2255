package controller.adv;

import java.util.LinkedList;

import controller.crud.CRUDOperation;
import controller.mainOperation;
import model.dto.SubSessionDTO;
import utils.DateTimeManipulation;
import model.Member;
import model.Service;
import model.Session;
import model.SubSession;

public class SubSessionOperation extends mainOperation {
	protected static SessionOperation sessionOperation = new SessionOperation();
	protected static MemberOperation memberOperation = new MemberOperation();

	/**
	 *
	 * Get the list of all subscribed sessions entries from members for a
	 * professional which has all the related services.
	 *
	 * @param proId
	 * @return LinkedList<SubSession>
	 */
	public LinkedList<SubSession> getSubSessionListByProId(String proId) {
        LinkedList<SubSession> proSession = new LinkedList<>();
        for (SubSession subSession : listSubSession) {
            if (subSession.getProId().equals(proId)) {
                proSession.add(subSession);
            }
        }
        return proSession;
    }

	/**
	 *
	 * Subscribe to a session by creating a SubSession object
	 *
	 * @param email
	 * @param serviceChosen
	 * @param sessionChosen
	 * @param comment
	 * @param time
	 * @return subSessionDTO -> Data Transfer Object for:
	 * 		    private int res;
	 * 		    	0 -> Ok
	 * 		    	-1 to -5 -> Error Codes
	 *     		private int nbJoinedMembersFromSession;
	 *          private SubSession subSession;
	 *          private double totalCostForSession;
	 */
	public SubSessionDTO subSession(String email, String serviceChosen, String sessionChosen, String comment, String time) {
		int index = memberOperation.memberState(email);

		if (index == 1)  {
			int serviceIndex = CRUDOperation.find(new Service(serviceChosen));
			if (serviceIndex < 0) {
				return new SubSessionDTO(-3);
			} else {
				Service serviceToBeSub = listService.get(serviceIndex);
				int maxCap = serviceToBeSub.getMaxCap();
				int sessionIndex = sessionOperation.findSession(sessionChosen, serviceToBeSub.getListSessionsAffiliated());

				if(sessionIndex < 0) {
					return new SubSessionDTO(-4);
				} else {
					Session sessionToBeSub = serviceToBeSub.getListSessionsAffiliated().get(sessionIndex);
					if (sessionToBeSub.getParticipantsId().size() >= maxCap) {
						return new SubSessionDTO(-5);
					} else {
						int indexMemberInMemberList = CRUDOperation.find(new Member(email));
						String id = listMember.get(indexMemberInMemberList).getId();
						sessionToBeSub.getParticipantsId().add(id);

						//-------- Create SubSession -------------------------------------
						String startDate = time;

						String endSession = sessionToBeSub.getEndDate();
						double costPerSession = serviceToBeSub.getCost();
						String serviceCodeSub = serviceToBeSub.getServiceCode();
						String sessionCodeSub = sessionToBeSub.getSessionId();
						String proId = serviceToBeSub.getProId();

						SubSession subSession = new SubSession(
								startDate,
								endSession,
								costPerSession,
								comment,
								serviceCodeSub,
								sessionCodeSub,
								id,
								proId);
						listSubSession.add(subSession);
						//----------------------------------------------------------------

						double totalCostForSession = subSession.calculateTotalCost(sessionToBeSub.getDayOfWeek());

						SubSessionDTO subSessionDTO = new SubSessionDTO(
								index,
								sessionToBeSub.getParticipantsId().size(),
								subSession,
								totalCostForSession
						);

						return subSessionDTO;
					}
				}
			}
		}
		return new SubSessionDTO(index);
	}
}
