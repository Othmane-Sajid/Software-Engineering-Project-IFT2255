package model;

import utils.UID;
import java.io.*;

public class Client implements Comparable<Client>, Serializable {
	private static final long serialVersionUID = -6987333599569164350L;
	private String id;
    private String name;
    private String email; 
    private String province;
    private String city;
    private String zipCode;
    private String address;


	public Client(String email) {
		this.email = email;
	}
    public Client(String name, String email, String province, String city, 
    		String zipCode, String address) {  	
        this.id = generateId();
        this.name = name;
        this.email = email; 
        this.province = province;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address; 
    }
    public Client(String id, String name, String email, String province, 
    		String city, String zipCode, String address) {
    	this.id = id;
    	this.name = name;
    	this.email = email; 
    	this.province = province;
    	this.city = city;
    	this.zipCode = zipCode;
    	this.address = address; 
    }


    private String generateId() {
    	return UID.generateUID(9);
    }


    public String getId() {
    	return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String newName) {
    	this.name = newName;
    }
    public String getEmail() {
    	return email;
    }
    public void setEmail(String newEmail) {
    	this.email = newEmail; 
    }
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int compareTo(Client o) {
		return email.compareTo(o.getEmail());
	}
	@Override
    public String toString() {
        return String.format("ID: %s    Nom: %s    Courriel: %s   Province: %s   "
        		+ "Ville: %s   Code Postal: %s    Adresse: %s    Type: %s   \r\n", 
        		id, name, email, province, city, zipCode, address, 
        		this.getClass().getSimpleName());
    }
}
