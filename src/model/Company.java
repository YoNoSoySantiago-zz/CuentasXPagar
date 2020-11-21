package model;

import java.util.ArrayList;

public class Company {
	
	private ArrayList<Debt> myDebts;
	private String compName;
	
	public Company(String name) {
		compName = name;
		myDebts = new ArrayList<Debt>();
		
	}
	
	public void addDebt(Debt a) {
		myDebts.add(a);
		
	}
	public ArrayList<Debt> getMyDebts() {
		return myDebts;
	}

	public String getCompName() {
		return compName;
	}

	public void setMyDebts(ArrayList<Debt> myDebts) {
		this.myDebts = myDebts;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public void deleteDebt(int index) {
		myDebts.remove(index);
		
	}
	

}
