package com.app.raven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.raven.model.PackageDetails;

//DAO of Package
/*
 * JpaRepository is used to manage data in spring boot application.
 * It is used to perform basic CRUD operations 
 * This will enable Spring Data to find this interface and automatically create an implementation for it.
*/
@Repository
public interface PackageRepository extends JpaRepository<PackageDetails, Long> {}
