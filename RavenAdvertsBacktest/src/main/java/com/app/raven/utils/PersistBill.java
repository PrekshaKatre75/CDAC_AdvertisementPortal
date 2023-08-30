package com.app.raven.utils;

import com.app.raven.model.Bill;
import com.app.raven.model.Customer;

public class PersistBill {
	public Bill customerBill(Customer cust) {
		Bill bill = new Bill();
		bill.setCompanyName(cust.getCompDet().getCompanyName());
		bill.setTotalCost(cust.getPack().getPCost());
		bill.setPaymentDate(cust.getPackageStartDate());
		return bill;
	}
}
