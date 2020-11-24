package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import custom_exception.ValuesIsEmptyException;

@SuppressWarnings("serial")
public class Company implements Serializable{
	
	private ArrayList<Debt> myDebts;
	private ArrayList<Record> records;
	private String compName;
	private int currentNumber;
	
	public static final String ORDER ="Orden";
	public static final String CODE ="Codigo";
	public static final String PROVIDER ="Proveedor";
	public static final String DATE ="Fecha limite";
	
	public Company(String name) {
		compName = name;
		myDebts = new ArrayList<Debt>();
		records = new ArrayList<Record>();
		currentNumber = 1;
	}
	
	public void addDebt(double amountToPay,String debtCode, String provider, LocalDate dateToPay) {
		Debt debt = new Debt(amountToPay,currentNumber,debtCode,provider,dateToPay);
		myDebts.add(debt);
		currentNumber++;
		Record record = new Record(debt, "Crear");
		records.add(record);
	}
	public void delateDebt(Debt debt) {
		int index = myDebts.indexOf(debt);
		myDebts.remove(index);
	}
	public void modDebt(Debt debt,double amountToPay,String debtCode, String provider, LocalDate dateToPay) {
		debt.setAmountToPay(amountToPay);
		debt.setDebtCode(debtCode);
		debt.setProvider(provider);
		debt.setDateToPay(dateToPay);
		if(amountToPay==0) {
			delateDebt( debt);
		}
		Record record = new Record(debt, "Modificar");
		records.add(record);
	}
	public ArrayList<Debt> getMyDebts() {
		return myDebts;
	}

	public String getCompName() {
		return compName;
	}

	public void deleteDebt(int index) {
		myDebts.remove(index);
		
	}
	public ArrayList<Debt> search(String type, String toSearch) throws ValuesIsEmptyException{
		ArrayList<Debt> debts = new ArrayList<>();
		switch(type) {
		
		case ORDER:
			for (int i = 0; i < myDebts.size(); i++) {
				try {
					if(myDebts.get(i).getNumberOrder()==Integer.parseInt(toSearch)) {
						debts.add(myDebts.get(i));
						break;
					}	
				}catch(NumberFormatException e) {
					throw new ValuesIsEmptyException();
				}
				
			}
			break;
		case CODE:
			for(int i = 0; i < myDebts.size(); i++) {
				if(myDebts.get(i).getDebtCode().equals(toSearch)) {
					debts.add(myDebts.get(i));
				}
			}
			break;
		case PROVIDER:
			for (int i = 0; i < myDebts.size(); i++) {
				if(myDebts.get(i).getProvider().equals(toSearch)) {
					debts.add(myDebts.get(i));
				}
			}
			break;
		case DATE:
			LocalDate date = LocalDate.parse(toSearch).plusDays(1);
			
			for (int i = 0; i < myDebts.size(); i++) {
				if(myDebts.get(i).getDateToPay().isBefore(date)) {
					debts.add(myDebts.get(i));
				}
			}
		}
		
		
		return debts;
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
		LocalDate date2 = LocalDate.now().plusDays(30);
		ArrayList<Debt>  result = new ArrayList<>();
		for (int i = 0; i < myDebts.size(); i++) {
			if(myDebts.get(i).getDateToPay().isBefore(date) && myDebts.get(i).getDateToPay().isAfter(date2)) {
				result.add(myDebts.get(i));
			}
		}
		return result;
	}
	public ArrayList<Debt> ninetyDaysDebts(){
		//LocalDate date = LocalDate.now().plusDays(91);
		LocalDate date2 = LocalDate.now().plusDays(60);
		ArrayList<Debt>  result = new ArrayList<>();
		for (int i = 0; i < myDebts.size(); i++) {
			if(/*myDebts.get(i).getDateToPay().isBefore(date)&& */myDebts.get(i).getDateToPay().isAfter(date2)) {
				result.add(myDebts.get(i));
			}
		}
		return result;
	}

	public ArrayList<Record> getRecord() {
		return records;
	}

}
