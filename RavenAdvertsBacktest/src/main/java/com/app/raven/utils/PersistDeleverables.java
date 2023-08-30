package com.app.raven.utils;

import com.app.raven.model.Deliverables;

public class PersistDeleverables {
	public Deliverables addLocationToDeleverables(Deliverables delivered, String productLocation) {
		delivered.setLocation(productLocation);
		return delivered;
	}
}
