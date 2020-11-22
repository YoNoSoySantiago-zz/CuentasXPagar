package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Debt implements Serializable{
	
	private double amountToPay;
	private int numberOrder;
	private String debtCode;
	private String provider;
	private LocalDate dateToPay;
	private boolean paidStatus;
	
	
	public Debt(double amountToPay,int numberOrder, String debtCode, String provider, LocalDate dateToPay) {
		
		super();
		this.amountToPay = amountToPay;
		this.debtCode = debtCode;
		this.provider = provider;
		this.dateToPay = dateToPay;
		this.numberOrder = numberOrder;
		paidStatus = false;
	}
	public double getAmountToPay() {
		return amountToPay;
	}
	public String getDebtCode() {
		return debtCode;
	}
	public String getProvider() {
		return provider;
	}
	public LocalDate getDateToPay() {
		return dateToPay;
	}
	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}
	public void setDebtCode(String debtCode) {
		this.debtCode = debtCode;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setDateToPay(LocalDate dateToPay) {
		this.dateToPay = dateToPay;
	}
	public void setPaidStatus(boolean d) {
		paidStatus = d;
	}
	public boolean getPaidStatus() {
		return paidStatus;
	}
	public int getNumberOrder() {
		return numberOrder;
	}
	
	
	
	
	

}
