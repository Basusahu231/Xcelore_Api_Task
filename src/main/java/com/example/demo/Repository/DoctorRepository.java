package com.example.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.*;
public interface DoctorRepository  extends JpaRepository<Doctor, Long> {
    List<Doctor> findByCityAndSpeciality(String city, String speciality);


}