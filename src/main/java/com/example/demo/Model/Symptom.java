package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "symptoms")
public class Symptom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String speciality;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "Symptom [id=" + id + ", name=" + name + ", speciality=" + speciality + "]";
	}

	public Symptom(Long id, @NotBlank String name, @NotBlank String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.speciality = speciality;
	}

	public Symptom() {
		super();
		// TODO Auto-generated constructor stub
	}

}
