package model;
import java.util.LinkedList;

import model.enums.AccountState;
import java.io.*;

public class Member extends Client implements Serializable {
	private static final long serialVersionUID = -7944126829346008444L;
	private AccountState state;
	private static LinkedList<Member> memberList = new LinkedList<>();


	public Member(String email) {
		super(email);
	}
    public Member(String name, String email, String province, String city, 
    		String zipCode, String address, AccountState state) {
        super(name, email, province, city, zipCode, address);
        this.state = state;
    }
    public Member(String id, String name, String email, String province, 
    		String city, String zipCode, String address, AccountState state) {
    	super(id, name, email, province, city, zipCode, address); 
    	this.state = state;
    }


    public AccountState getState() {
    	return this.state;
    }
	public void setState(AccountState newState) {
		this.state = newState;
	}
	public String toString() {
    	return super.toString() + String.format("   State: %s \r\n", state);
    }
	public static LinkedList<Member> getMemberList() {
		return memberList;
	}
	public static void setMemberList(LinkedList<Member> newList) {
		memberList =  newList;
	}
}
