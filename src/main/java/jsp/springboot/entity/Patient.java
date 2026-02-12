package jsp.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="patient", uniqueConstraints = {@UniqueConstraint(columnNames = "email")
,@UniqueConstraint(columnNames = "phoneNumber")
})
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer patientId;
	private String patientName;
	private int age;
	private String gender;
	@Column(nullable = false)
	private String	 phoneNumber;
	@Column(nullable = false)
	private String email;
	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Appointment> appointments;
	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<MedicalRecord> medicalRecords;
	public synchronized Integer getPatientId() {
		return patientId;
	}
	public synchronized void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public synchronized String getPatientName() {
		return patientName;
	}
	public synchronized void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public synchronized int getAge() {
		return age;
	}
	public synchronized void setAge(int age) {
		this.age = age;
	}
	public synchronized String getGender() {
		return gender;
	}
	public synchronized void setGender(String gender) {
		this.gender = gender;
	}
	public synchronized String getPhoneNumber() {
		return phoneNumber;
	}
	public synchronized void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public synchronized String getEmail() {
		return email;
	}
	public synchronized void setEmail(String email) {
		this.email = email;
	}
	public synchronized List<Appointment> getAppointments() {
		return appointments;
	}
	public synchronized void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public synchronized List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}
	public synchronized void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	


	

}
