package com.app.raven.service;

import com.app.raven.model.Deliverables;

public interface DeliverablesServicable {
	Deliverables saveDeliverables(Deliverables newDelivery);

	Deliverables findDeliverablesById(Long id);
}
