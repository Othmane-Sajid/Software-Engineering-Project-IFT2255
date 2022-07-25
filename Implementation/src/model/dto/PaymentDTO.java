package model.dto;

import model.ConfirmPresence;
import model.Member;
import model.Professional;

import java.util.HashMap;
import java.util.LinkedList;

public class PaymentDTO {
    private Professional pro;
    private LinkedList<ConfirmPresence> listForPro;
    private HashMap<String, String> memberId2memberName;


    public PaymentDTO(Professional pro,
                      LinkedList<ConfirmPresence> listForPro,
                      HashMap<String, String> memberId2memberName) {
        this.pro = pro;
        this.listForPro = listForPro;
        this.memberId2memberName = memberId2memberName;
    }


    public Professional getPro() {
        return pro;
    }
    public LinkedList<ConfirmPresence> getListForPro() {
        return listForPro;
    }
    public HashMap<String, String> getMemberId2memberName() {
        return memberId2memberName;
    }
}
