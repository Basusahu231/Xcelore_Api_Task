package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.*;
import com.example.demo.Model.*;
import java.util.List;
import com.example.demo.Exception.*;
@Service
public class SuggestionService {
	 @Autowired
	    private DoctorRepository doctorRepository;

	    @Autowired
	    private PatientRepository patientRepository;

	    public List<Doctor> suggestDoctors(Long patientId) {
	        Patient patient = patientRepository.findById(patientId)
	                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
	        
	        String city = patient.getCity();
	        String symptom = patient.getSymptom();
	        String speciality = getSpecialityForSymptom(symptom);

	        if (speciality == null) {
	            throw new IllegalArgumentException("Invalid symptom");
	        }

	        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(city, speciality);
	        if (doctors.isEmpty()) {
	            if (!isCitySupported(city)) {
	                throw new IllegalArgumentException("We are still waiting to expand to your location");
	            } else {
	                throw new IllegalArgumentException("There isn’t any doctor present at your location for your symptom");
	            }
	        }

	        return doctors;
	    }

	    private String getSpecialityForSymptom(String symptom) {
	        switch (symptom.toLowerCase()) {
	            case "arthritis":
	            case "back pain":
	            case "tissue injuries":
	                return "Orthopaedic";
	            case "dysmenorrhea":
	                return "Gynecology";
	            case "skin infection":
	            case "skin burn":
	                return "Dermatology";
	            case "ear pain":
	                return "ENT specialist";
	            default:
	                return null;
	        }
	    }

	    private boolean isCitySupported(String city) {
	        return city.equalsIgnoreCase("Delhi") ||
	               city.equalsIgnoreCase("Noida") ||
	               city.equalsIgnoreCase("Faridabad");
	    }
}
