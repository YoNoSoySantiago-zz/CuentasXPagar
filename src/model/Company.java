package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Company implements Serializable{
	
	private ArrayList<Debt> myDebts;
	private String compName;
	private int currentNumber;
	
	public Company(String name) {
		compName = name;
		myDebts = new ArrayList<Debt>();
		currentNumber = 1;
	}
	
	public void addDebt(double amountToPay,String debtCode, String provider, LocalDate dateToPay) {
		Debt debt = new Debt(amountToPay,currentNumber,debtCode,provider,dateToPay);
		myDebts.add(debt);
		currentNumber++;
		
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

	public int getCurrentNumber() {
		return currentNumber;
	}
	
	public ArrayList<Debt> thirtyDaysDebts(){
		LocalDate date = LocalDate.now().plusDays(31);
		ArrayList<Debt>  result = new ArrayList<>();
		for (int i = 0; i < myDebts.size(); i++) {
			if(myDebts.get(i).getDateToPay().isBefore(date)) {
				result.add(myDebts.get(i));
			}
		}
		return result;
	}
	public ArrayList<Debt> sixtyDaysDebts(){
		LocalDate date = LocalDate.now().plusDays(61);
		ArrayList<Debt>  result = new ArrayList<>();
		for (int i = 0; i < myDebts.size(); i++) {
			if(myDebts.get(i).getDateToPay().isBefore(date)) {
				result.add(myDebts.get(i));
			}
		}
		return result;
	}
	public ArrayList<Debt> ninetyDaysDebts(){
		LocalDate date = LocalDate.now().plusDays(91);
		ArrayList<Debt>  result = new ArrayList<>();
		for (int i = 0; i < myDebts.size(); i++) {
			if(myDebts.get(i).getDateToPay().isBefore(date)) {
				result.add(myDebts.get(i));
			}
		}
		return result;
	}

}
