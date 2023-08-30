package com.app.raven.service;

import com.app.raven.model.Bill;

public interface BillServicable {
	Bill saveBill(Bill newBill);

	Bill findBillById(Long id);
}
