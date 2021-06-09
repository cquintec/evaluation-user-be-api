package com.bci.evaluation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bci.evaluation.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
