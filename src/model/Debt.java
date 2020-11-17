package model;

import java.util.Date;

public class Debt {
	
	private double amountToPay;
	private int debtCode;
	private String provider;
	private Date dateToPay;
	private boolean paidStatus;
	
	
	public Debt(double amountToPay, int debtCode, String provider, Date dateToPay) {
		
		super();
		this.amountToPay = amountToPay;
		this.debtCode = debtCode;
		this.provider = provider;
		this.dateToPay = dateToPay;
		paidStatus = false;
	}
	public double getAmountToPay() {
		return amountToPay;
	}
	public int getDebtCode() {
		return debtCode;
	}
	public String getProvider() {
		return provider;
	}
	public Date getDateToPay() {
		return dateToPay;
	}
	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}
	public void setDebtCode(int debtCode) {
		this.debtCode = debtCode;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setDateToPay(Date dateToPay) {
		this.dateToPay = dateToPay;
	}
	public void setPaidStatus(boolean d) {
		paidStatus = d;
	}
	public boolean getPaidStatus() {
		return paidStatus;
	}
	
	
	
	
	

}
