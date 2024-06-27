package com.example.demo.Repository;
import com.example.demo.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long>{

}
