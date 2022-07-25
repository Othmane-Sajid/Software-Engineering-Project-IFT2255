package model.dto;

import model.ConfirmPresence;
import model.Member;

import java.util.HashMap;
import java.util.LinkedList;

public class BillsDTO {
    private Member member;
    private LinkedList<ConfirmPresence> listForMember;
    private HashMap<String, String> proId2proName;


    public BillsDTO(Member member, LinkedList<ConfirmPresence> listForMember, HashMap<String, String> proId2proName) {
        this.member = member;
        this.listForMember = listForMember;
        this.proId2proName = proId2proName;
    }


    public Member getMember() {
        return member;
    }
    public LinkedList<ConfirmPresence> getListForMember() {
        return listForMember;
    }
    public HashMap<String, String> getProId2proName() {
        return proId2proName;
    }
}
