package com.app.raven.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.raven.model.Bill;
import com.app.raven.repository.BillRepository;

@Service
@Transactional
public class BillService implements BillServicable {

	private BillRepository billRepo;
	
	public BillService(BillRepository billRepo) {
		this.billRepo = billRepo;
	}
	
	@Override
	public Bill saveBill(Bill newBill) {
		this.billRepo.save(newBill);
		return newBill;
	}

	@Override
	public Bill findBillById(Long id) {
		return this.billRepo.findById(id).orElse(null);
	}

}
