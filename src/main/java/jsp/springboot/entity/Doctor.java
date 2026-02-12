package jsp.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	private String doctorName;
	private String specialization;
	  @ElementCollection
	    @CollectionTable(
	        name = "doctor_available_days",
	        joinColumns = @JoinColumn(name = "doctor_id")
	    )
	    @Column(name = "available_day")
	private List<String> availableDays;
	  
	  @ManyToOne
	  @JoinColumn(name= "department_id")
	 
	  private Department department;

	  @OneToMany(mappedBy = "doctor")
	  @JsonIgnore
	  private List<Appointment> appointments;
	  @OneToMany(mappedBy = "doctor")
	  @JsonIgnore
	  private List<MedicalRecord> medicalRecords;
	  public synchronized Integer getDoctorId() {
		  return doctorId;
	  }
	  public synchronized void setDoctorId(Integer doctorId) {
		  this.doctorId = doctorId;
	  }
	  public synchronized String getDoctorName() {
		  return doctorName;
	  }
	  public synchronized void setDoctorName(String doctorName) {
		  this.doctorName = doctorName;
	  }
	  public synchronized String getSpecialization() {
		  return specialization;
	  }
	  public synchronized void setSpecialization(String specialization) {
		  this.specialization = specialization;
	  }
	  public synchronized List<String> getAvailableDays() {
		  return availableDays;
	  }
	  public synchronized void setAvailableDays(List<String> availableDays) {
		  this.availableDays = availableDays;
	  }
	  public synchronized Department getDepartment() {
		  return department;
	  }
	  public synchronized void setDepartment(Department department) {
		  this.department = department;
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
