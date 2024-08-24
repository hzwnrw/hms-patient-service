package com.hzwn.hms.patient.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hzwn.hms.component.KafkaProducerComponent;
import com.hzwn.hms.patient.model.Patient;
import com.hzwn.hms.patient.repository.PatientRepository;

@Service
public class PatientService {
	
	private static Logger log = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
	ApplicationContext applicationContext;

    private final String topic = "patient-events";


	public Patient savePatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
      
        applicationContext.getBean(KafkaProducerComponent.class).publish(topic, savedPatient);
        
        return savedPatient;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
