package model;

import java.util.LinkedList;

public class  TEF {
	private static LinkedList<TEF> tefList = new LinkedList<>();
    private String name;
    private String id;
    private double pay;


    public TEF(String name, String id, double pay) {
    	this.name = name;
    	this.id = id;
    	this.pay = pay;
    }
    @Override
    public String toString() {
        return "TEF{" +
                "Nom du professionnel ='" + name + '\'' +
                ", Numéro du professionnel ='" + id + '\'' +
                ", paiement =" + pay +
                '}';
    }
	public static LinkedList<TEF> getTefList() {
		return tefList;
	}
}
