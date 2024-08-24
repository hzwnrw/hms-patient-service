package com.hzwn.hms.patient.repository;

import com.hzwn.hms.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
