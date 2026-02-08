package jsp.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prescriptionId;
	private  String medicine;
	private String dosage;
	private String Instruction;
	@OneToOne(mappedBy = "prescription")
	private MedicalRecord medicalRecord;
	public synchronized int getPrescriptionId() {
		return prescriptionId;
	}
	public synchronized void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public synchronized String getMedicine() {
		return medicine;
	}
	public synchronized void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public synchronized String getDosage() {
		return dosage;
	}
	public synchronized void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public synchronized String getInstruction() {
		return Instruction;
	}
	public synchronized void setInstruction(String instruction) {
		Instruction = instruction;
	}
	public synchronized MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}
	public synchronized void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}


}
