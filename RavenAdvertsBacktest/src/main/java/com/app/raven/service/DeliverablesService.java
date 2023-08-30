package com.app.raven.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.raven.model.Deliverables;
import com.app.raven.repository.DeliverablesRepository;

@Service
@Transactional
public class DeliverablesService implements DeliverablesServicable {
	private DeliverablesRepository delRepo;
	
	public DeliverablesService(DeliverablesRepository delRepo) {
		this.delRepo = delRepo;
	}

	@Override
	public Deliverables saveDeliverables(Deliverables newDelivery) {
		this.delRepo.save(newDelivery);
		return newDelivery;
	}

	@Override
	public Deliverables findDeliverablesById(Long id) {
		return this.delRepo.findById(id).orElse(null);
	}

}
