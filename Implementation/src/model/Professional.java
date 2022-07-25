package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Professional extends Client implements Serializable {
	private static final long serialVersionUID = -6311707104511987245L;
	private static LinkedList<Professional> proList = new LinkedList<>();


	public Professional(String email) {
		super(email);
	}
    public Professional(String name, String email, String province, String city, 
    		String zipCode, String address) {
        super(name, email, province, city, zipCode, address);
    }
    public Professional(String id, String name, String email, String province, 
    		String city, String zipCode, String address) {
    	super(id, name, email, province, city, zipCode, address); 
    }


    public String toString() {
    	return super.toString() + String.format("\r\n");
    }
	public static LinkedList<Professional> getProList() {
		return proList;
	}
	public static void setProList(LinkedList<Professional> newList) {
		proList = newList;
	}
}
