package jsp.springboot.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.springboot.AppointmentStatus;
import jsp.springboot.entity.Appointment;
import jsp.springboot.entity.Doctor;
import jsp.springboot.entity.MedicalRecord;
import jsp.springboot.entity.Patient;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoCompletedAppointmentException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.repository.AppointmentRepository;
import jsp.springboot.repository.DoctorRepository;
import jsp.springboot.repository.MedicalRecordRepository;
import jsp.springboot.repository.PatientRepository;

@Repository
public class MedicalRecordDao {
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public MedicalRecord createRecord(MedicalRecord record) {
        Integer patientId = record.getPatient().getPatientId();
        Integer doctorId = record.getDoctor().getDoctorId();
        
        Optional<Patient> opt = patientRepository.findById(patientId);
        if (opt.isEmpty()) {
            throw new IdNotFoundException("Patient id not found");
        }
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            throw new IdNotFoundException("Doctor id not found");
        }

     
        List<Appointment> completed = appointmentRepository.findByPatient_PatientIdAndStatus(patientId, AppointmentStatus.COMPLETED);
        if (completed.isEmpty()) {
            throw new NoCompletedAppointmentException("Cannot create record: patient has no completed appointment");
        }
        record.setPatient(opt.get());
       record.setDoctor(doctorOpt.get());
       
        return medicalRecordRepository.save(record);
    }
    
    public List<MedicalRecord> fetchAllRecords()
    {
    	List<MedicalRecord> records = medicalRecordRepository.findAll();
    	if(records.isEmpty())
    	{
    		throw new NoRecordAvailableException("No record available in the database");
    		
    	}
    	else return records;
    }
    public MedicalRecord  fetchRecordById(Integer id)
    {
    	Optional<MedicalRecord> opt = medicalRecordRepository.findById(id);
    	if(opt.isPresent())
    	{
    		return opt.get();
    	}
    	else throw new IdNotFoundException("Id not found in the database");
    }
    
    public List<MedicalRecord> fetchRecordByPatient(Integer id) {
        Optional<Patient> opt = patientRepository.findById(id);
        if (opt.isEmpty()) {
            throw new IdNotFoundException("Patient not found with ID: " + id);
        }

        List<MedicalRecord> records = medicalRecordRepository.findByPatientId(id);
        if (records.isEmpty()) {
            throw new NoRecordAvailableException("No medical records found for patient ID: " + id);
        }

        return records;
    }
    public List<MedicalRecord> fetchRecordByAppointment(Integer appointmentId) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isEmpty()) {
            throw new IdNotFoundException("Appointment not found with ID: " + appointmentId);
        }

        Appointment appointment = appointmentOpt.get();

       
        if (appointment.getStatus() != AppointmentStatus.COMPLETED) {
            throw new NoRecordAvailableException("Medical record cannot exist: Appointment is not completed");
        }

        List<MedicalRecord> records = medicalRecordRepository.findByPatientId(appointment.getPatient().getPatientId());

        if (records.isEmpty()) {
            throw new NoRecordAvailableException("No medical records found for this appointment");
        }

        return records;
    }
    public List<MedicalRecord> fetchRecordByDoctor(Integer id) {
        Optional<Doctor> opt = doctorRepository.findById(id);
        if (opt.isEmpty()) {
            throw new IdNotFoundException("Doctor not found with ID: " + id);
        }

        List<MedicalRecord> records = medicalRecordRepository.findByDoctorId(id);
        if (records.isEmpty()) {
            throw new NoRecordAvailableException("No medical records found for doctor ID: " + id);
        }

        return records;
    }
    public List<MedicalRecord> fetchRecordByVisitDate(LocalDate visitDate) {
        List<MedicalRecord> records = medicalRecordRepository.findByVisitDate(visitDate);
        if (records.isEmpty()) {
            throw new NoRecordAvailableException("No medical records found for visit date: " + visitDate);
        }
        return records;
    }

    
}
