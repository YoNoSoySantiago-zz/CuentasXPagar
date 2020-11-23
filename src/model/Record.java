package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Record {
	private Debt debt;
	private String action;
	private LocalDateTime time;
	
	public Record(Debt debt,String action) {
		this.debt = debt.clone();
		time = LocalDateTime.now();
		this.action = action;
	}
	
	public double getAmountToPay() {
		return debt.getAmountToPay();
	}
	public String getDebtCode() {
		return debt.getDebtCode();
	}
	public String getProvider() {
		return debt.getProvider();
	}
	public LocalDate getDateToPay() {
		return debt.getDateToPay();
	}
	public boolean getPaidStatus() {
		return debt.getPaidStatus();
	}
	public int getNumberOrder() {
		return debt.getNumberOrder();
	}
	public Debt getDebt() {
		return debt;
	}
	public LocalDateTime getTime() {
		return time;
	}
	
	public String getAction() {
		return action;
	}
}
