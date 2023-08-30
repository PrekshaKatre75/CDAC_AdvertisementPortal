package com.app.raven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.raven.model.Deliverables;

@Repository
public interface DeliverablesRepository  extends JpaRepository<Deliverables, Long> {}
