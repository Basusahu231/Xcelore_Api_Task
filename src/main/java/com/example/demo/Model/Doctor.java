package com.example.demo.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 3)
	private String name;

	@NotBlank
	@Size(max = 20)
	private String city;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Pattern(regexp = "\\d{10,}")
	private String phoneNumber;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", speciality=" + speciality + "]";
	}

	public Doctor(Long id, @NotBlank @Size(min = 3) String name, @NotBlank @Size(max = 20) String city,
			@Email @NotBlank String email, @NotBlank @Pattern(regexp = "\\d{10,}") String phoneNumber,
			@NotBlank String speciality) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.speciality = speciality;
	}

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}


