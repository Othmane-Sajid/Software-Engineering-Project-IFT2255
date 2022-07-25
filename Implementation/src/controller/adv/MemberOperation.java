package controller.adv;

import controller.crud.CRUDOperation;
import controller.mainOperation;
import model.Member;
import model.enums.AccountState;

public class MemberOperation extends mainOperation {

    /**
     *
     * Check if a member's access attribute is valid or not.
     *
     * @param email
     * @return AccountState
     */
    public AccountState memberAccess(String email) {
        AccountState state;
        int index = CRUDOperation.find(new Member(email));
        if (index >= 0) {
            listMember.get(index).getState();
            state = listMember.get(index).getState();
            return state;
        }
        else
            return null;
    }

    /**
     *
     * Check a member's state from his email.
     *
     * @param email
     * @return int
     *         -1 -> invalid
     *          1 -> valid
     *          0 -> suspended
     */
    public int memberState(String email) {
        int index = CRUDOperation.find(new Member(email)); 
        if (index >= 0) {
            AccountState state = listMember.get(index).getState();
            switch(state) {
                case invalid:
                    return -1;
                case valid:
                    return 1;
                case suspended:
                    return 0;
                default:
                    return -2;
            }
        } else {
            return -2;
        }
    }
}
