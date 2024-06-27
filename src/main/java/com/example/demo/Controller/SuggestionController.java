 package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.*;
import com.example.demo.Services.DoctorService;
import com.example.demo.Services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/{patientId}")
    public ResponseEntity<?> suggestDoctor(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            return ResponseEntity.badRequest().body("Invalid patient ID");
        }

        String city = patient.getCity();
        String symptom = patient.getSymptom();
        String speciality = getSpecialityForSymptom(symptom);

        if (speciality == null) {
            return ResponseEntity.badRequest().body("Invalid symptom");
        }

        List<Doctor> doctors = doctorService.findDoctorsByCityAndSpeciality(city, speciality);
        if (doctors.isEmpty()) {
            if (isCitySupported(city)) {
                return ResponseEntity.ok("There isn’t any doctor present at your location for your symptom");
            } else {
                return ResponseEntity.ok("We are still waiting to expand to your location");
            }
        }

        return ResponseEntity.ok(doctors);
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
